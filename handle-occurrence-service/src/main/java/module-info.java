module connected.services.demo.handle.occurrence.service {
    requires connected.services.demo.services;
    requires creek.service.context;
    requires creek.kafka.streams.extension;
    requires creek.kafka.serde.json.schema;
    requires org.apache.logging.log4j;
}
