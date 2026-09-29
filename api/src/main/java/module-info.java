import io.github.creek.service.wip.state.stores.demo.api.WipStateStoresDemoAggregateDescriptor;
import org.creekservice.api.platform.metadata.ComponentDescriptor;

module wip.state.stores.demo.api {
    requires transitive creek.kafka.metadata;
    requires com.fasterxml.jackson.annotation;
    requires static io.swagger.v3.oas.annotations;
    requires creek.base.annotation;

    exports io.github.creek.service.wip.state.stores.demo.api;
    exports io.github.creek.service.wip.state.stores.demo.api.model;
    exports io.github.creek.service.wip.state.stores.demo.internal to
            wip.state.stores.demo.services,
            wip.state.stores.demo.handle.occurrence.service;

    // Required so Jackson (used by the JSON serde) can reflectively access the record's canonical
    // constructor and component accessors at runtime.
    opens io.github.creek.service.wip.state.stores.demo.api.model;

    provides ComponentDescriptor with
            WipStateStoresDemoAggregateDescriptor;
}
