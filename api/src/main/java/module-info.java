module connected.services.demo.api {
    requires transitive creek.kafka.metadata;

    exports io.github.creek.service.connected.services.demo.api;
    exports io.github.creek.service.connected.services.demo.internal to
            connected.services.demo.services,
            connected.services.demo.service;
}
