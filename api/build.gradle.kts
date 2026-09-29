plugins {
    `java-library`
    id("org.creekservice.schema.json")
}

dependencies {
    api("org.creekservice:creek-kafka-metadata:${property("creekVersion")}")
    api("com.fasterxml.jackson.core:jackson-annotations:${property("jacksonVersion")}")
    // Used to annotate schema constraints (e.g. minLength/minimum) for `generateJsonSchema`.
    // `compileOnlyApi` keeps it off the runtime classpath: `creek-kafka-json-serde`'s Confluent
    // schema-registry client transitively pulls in `swagger-annotations-jakarta`, which claims the
    // same JPMS module name.
    compileOnlyApi("io.swagger.core.v3:swagger-annotations:${property("swaggerAnnotationsVersion")}")
    implementation("org.creekservice:creek-base-annotation:${property("creekVersion")}")

    jsonSchemaGenerator("org.creekservice:creek-json-schema-generator:${property("creekVersion")}")

    // To avoid dependency hell downstream, avoid adding any more dependencies except Creek metadata jars and test dependencies.

    // The module descriptor's `requires static` needs this resolvable when compiling the test module patch too.
    testCompileOnly("io.swagger.core.v3:swagger-annotations:${property("swaggerAnnotationsVersion")}")
    testImplementation("org.apache.kafka:kafka-clients:${property("kafkaVersion")}")
}

creek.schema.json {
    typeScanning.moduleWhiteList(moduleName)
    subTypeScanning.moduleWhiteList(moduleName)
}
