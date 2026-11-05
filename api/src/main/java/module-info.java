import io.github.creek.service.wip.state.stores.demo.api.WipStateStoresDemoAggregateDescriptor;
import org.creekservice.api.platform.metadata.ComponentDescriptor;

module wip.state.stores.demo.api {
    requires transitive creek.kafka.metadata;

    exports io.github.creek.service.wip.state.stores.demo.api;
    exports io.github.creek.service.wip.state.stores.demo.internal to
            wip.state.stores.demo.services,
            wip.state.stores.demo.handle.occurrence.service;

    provides ComponentDescriptor with
            WipStateStoresDemoAggregateDescriptor;
}
