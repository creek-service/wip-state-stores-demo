/*
 * Copyright 2022-2023 Creek Contributors (https://github.com/creek-service)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.creek.service.connected.services.demo.handle.scoreboard.service.kafka.streams;

import static io.github.creek.service.connected.services.demo.services.HandleScoreboardServiceDescriptor.TweetHandleLeaderBoardChangeLog;
import static io.github.creek.service.connected.services.demo.services.HandleScoreboardServiceDescriptor.TweetHandleLeaderBoardTable;
import static io.github.creek.service.connected.services.demo.services.HandleScoreboardServiceDescriptor.TweetHandleUsageStream;
import static java.util.Objects.requireNonNull;
import static org.creekservice.api.kafka.metadata.KafkaTopicDescriptor.DEFAULT_CLUSTER_NAME;

import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.state.Stores;
import org.creekservice.api.kafka.extension.resource.KafkaTopic;
import org.creekservice.api.kafka.streams.extension.KafkaStreamsExtension;
import org.creekservice.api.kafka.streams.extension.util.Name;

public final class TopologyBuilder {

    private final KafkaStreamsExtension ext;
    private final Name name = Name.root();

    public TopologyBuilder(final KafkaStreamsExtension ext) {
        this.ext = requireNonNull(ext, "ext");
    }

    // begin-snippet: build-method
    public Topology build() {
        final StreamsBuilder builder = new StreamsBuilder();

        // Pass a topic descriptor to the Kafka Streams extension to
        // obtain a typed `KafkaTopic` instance, which provides access to serde:
        final KafkaTopic<String, Integer> input = ext.topic(TweetHandleUsageStream);
        final KafkaTopic<String, Integer> changelog = ext.topic(TweetHandleLeaderBoardChangeLog);
        final KafkaTopic<String, Integer> output = ext.topic(TweetHandleLeaderBoardTable);

        // Build a topology to build the scoreboard:
        // Consume input topic:
        builder.stream(
                        input.name(),
                        Consumed.with(input.keySerde(), input.valueSerde())
                                .withName(name.name("ingest-" + input.name())))
                // Group the input stream by the key, which is the Twitter handle:
                .groupByKey(Grouped.as(name.name("group-by-key" + input.name())))
                // Reduce to a table tha tracks the total number of occurrences, by Twitter handle:
                .reduce(
                        Integer::sum,
                        name.named("handle-scoreboard"),
                        // Todo: call out design issue with in memory store:
                        // Todo:Materialized.<String, Integer, KeyValueStore<Bytes,
                        // byte[]>>as(Stores.inMemoryKeyValueStore(changelog.storeName()))
                        Materialized.<String, Integer>as(Stores.inMemoryKeyValueStore("todo"))
                                .withKeySerde(changelog.keySerde())
                                .withValueSerde(changelog.valueSerde())
                        // Todo: .withRetention() -> get from output topic config

                        )
                .toStream(name.named("scoreboard-to-stream"))
                // Finally, produce to output:
                .to(
                        output.name(),
                        Produced.with(output.keySerde(), output.valueSerde())
                                .withName(name.name("egress-" + output.name())));

        return builder.build(ext.properties(DEFAULT_CLUSTER_NAME));
    }
    // end-snippet
}

// Todo: document design issues, i.e.
//   ever growing table
//   sum number wrapping
