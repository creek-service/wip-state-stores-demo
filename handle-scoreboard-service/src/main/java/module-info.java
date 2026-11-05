module wip.state.stores.demo.handle.scoreboard.service {
    requires wip.state.stores.demo.services;
    requires creek.service.context;
    requires creek.kafka.streams.extension;
    requires creek.kafka.serde.json.schema;
    requires org.apache.logging.log4j;
}
