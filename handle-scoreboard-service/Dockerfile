FROM amazoncorretto:19@sha256:0f5f3ccad683d3ad81dfffc875c5ef6e421a879ac1ba5093ac943ddd9c7c6c9c
ARG APP_NAME
ARG APP_VERSION
ENV VERSION=$APP_VERSION

LABEL org.opencontainers.image.source=https://github.com/creek-service/connected-services-demo/tree/main/handle-scoreboard-service


RUN mkdir -p /opt/creek

COPY bin /bin
COPY log4j /log

COPY ${APP_NAME}-${APP_VERSION} /opt/creek/${APP_NAME}-${APP_VERSION}
WORKDIR /opt/creek
RUN ln -s ${APP_NAME}-${APP_VERSION} service

ENTRYPOINT ["/bin/run.sh"]