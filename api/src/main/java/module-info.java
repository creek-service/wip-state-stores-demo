module connected.services.demo.api {
    requires transitive creek.kafka.metadata;
    requires com.fasterxml.jackson.annotation;
    requires creek.base.annotation;

    exports io.github.creek.service.connected.services.demo.api;
    exports io.github.creek.service.connected.services.demo.api.model;
    exports io.github.creek.service.connected.services.demo.internal to
            connected.services.demo.services,
            connected.services.demo.service;

    // Required so Jackson (used by the JSON serde) can reflectively access the record's canonical
    // constructor and component accessors at runtime.
    opens io.github.creek.service.connected.services.demo.api.model;
}
