import io.github.creek.service.connected.services.demo.services.HandleOccurrenceServiceDescriptor;
import org.creekservice.api.platform.metadata.ComponentDescriptor;

module connected.services.demo.services {
    requires transitive connected.services.demo.api;

    exports io.github.creek.service.connected.services.demo.services;

    provides ComponentDescriptor with
            io.github.creek.service.connected.services.demo.services
                    .HandleScoreboardServiceDescriptor,
            HandleOccurrenceServiceDescriptor;
}
