/*
 * Copyright 2021-2023 Creek Contributors (https://github.com/creek-service)
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

package io.github.creek.service.connected.services.demo.services;

import static io.github.creek.service.connected.services.demo.internal.TopicConfigBuilder.withPartitions;
import static io.github.creek.service.connected.services.demo.internal.TopicDescriptors.internalTopic;
import static io.github.creek.service.connected.services.demo.internal.TopicDescriptors.outputTopic;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.creekservice.api.kafka.metadata.KafkaTopicInput;
import org.creekservice.api.kafka.metadata.KafkaTopicInternal;
import org.creekservice.api.kafka.metadata.OwnedKafkaTopicOutput;
import org.creekservice.api.platform.metadata.ComponentInput;
import org.creekservice.api.platform.metadata.ComponentInternal;
import org.creekservice.api.platform.metadata.ComponentOutput;
import org.creekservice.api.platform.metadata.ServiceDescriptor;

public final class HandleScoreboardServiceDescriptor implements ServiceDescriptor {

    private static final List<ComponentInput> INPUTS = new ArrayList<>();
    private static final List<ComponentInternal> INTERNALS = new ArrayList<>();
    private static final List<ComponentOutput> OUTPUTS = new ArrayList<>();

    // formatting:off
// begin-snippet: topic-resources
    // Hookup the handle-occurrence-service's output as this services, unowned, input:
    public static final KafkaTopicInput<String, Integer> TweetHandleUsageStream =
            register(HandleOccurrenceServiceDescriptor.TweetHandleUsageStream.toInput());

    public static final KafkaTopicInternal<String, Integer> TweetHandleLeaderBoardChangeLog =
            register(internalTopic(
                    "someting",  // Todo: fix this
                    String.class, // (Twitter handle)
                    Integer.class // (Summed usage count)
            ));

    // Define the output topic, conceptually owned by this service:
    public static final OwnedKafkaTopicOutput<String, Integer> TweetHandleLeaderBoardTable =
            register(outputTopic(
                    "twitter.handle.leaderboard",
                    String.class, // (Twitter handle)
                    Integer.class,  // (Summed usage count)
                    withPartitions(6)
                            // Todo: document:
                            .withRetentionTime(Duration.ofDays(1))));
// end-snippet
    // formatting:on

    // Todo: call out design issue with 6 partitions
    // Todo: call out same design issue with basic kafa streas demo
    // Todo: Test if system tests can access internal changelog topic without it being listed here.
    // Todo: Do we want to list the internal changelog topic here?
    // Todo: Windowing...

    public HandleScoreboardServiceDescriptor() {}

    @Override
    public String dockerImage() {
        return "ghcr.io/creek-service/connected-services-demo-handle-scoreboard-service";
    }

    @Override
    public Collection<ComponentInput> inputs() {
        return List.copyOf(INPUTS);
    }

    @Override
    public Collection<ComponentInternal> internals() {
        return List.copyOf(INTERNALS);
    }

    @Override
    public Collection<ComponentOutput> outputs() {
        return List.copyOf(OUTPUTS);
    }

    private static <T extends ComponentInput> T register(final T input) {
        INPUTS.add(input);
        return input;
    }

    // Todo: document uncommenting this
    private static <T extends ComponentInternal> T register(final T internal) {
        INTERNALS.add(internal);
        return internal;
    }

    private static <T extends ComponentOutput> T register(final T output) {
        OUTPUTS.add(output);
        return output;
    }
}
