module connected.services.demo.service {
    requires connected.services.demo.services;
    requires creek.service.context;
    requires creek.kafka.streams.extension;
    requires org.apache.logging.log4j;
}
