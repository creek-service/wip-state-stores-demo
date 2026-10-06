import io.github.creek.service.wip.state.stores.demo.services.HandleOccurrenceServiceDescriptor;
import org.creekservice.api.platform.metadata.ComponentDescriptor;

module wip.state.stores.demo.services {
    requires transitive wip.state.stores.demo.api;

    exports io.github.creek.service.wip.state.stores.demo.services;

    provides ComponentDescriptor with
            io.github.creek.service.wip.state.stores.demo.services
                    .HandleScoreboardServiceDescriptor,
            HandleOccurrenceServiceDescriptor;
}
