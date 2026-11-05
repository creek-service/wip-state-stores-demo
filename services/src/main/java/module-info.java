import io.github.creek.service.wip.state.stores.demo.services.HandleOccurrenceServiceDescriptor;
import org.creekservice.api.platform.metadata.ComponentDescriptor;

module wip.state.stores.demo.services {
    requires transitive wip.state.stores.demo.api;
    requires transitive com.fasterxml.jackson.annotation;
    requires creek.base.annotation;
    requires static io.swagger.v3.oas.annotations;

    exports io.github.creek.service.wip.state.stores.demo.services;
    exports io.github.creek.service.wip.state.stores.demo.services.model;

    // begin-snippet: opens-jackson
    opens io.github.creek.service.wip.state.stores.demo.services.model;

    // end-snippet

    provides ComponentDescriptor with
            io.github.creek.service.wip.state.stores.demo.services
                    .HandleScoreboardServiceDescriptor,
            HandleOccurrenceServiceDescriptor;
}
