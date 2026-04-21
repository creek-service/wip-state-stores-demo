[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![codecov](https://codecov.io/gh/creek-service/wip-state-stores-demo/branch/main/graph/badge.svg)](https://codecov.io/gh/creek-service/wip-state-stores-demo)
[![build](https://github.com/creek-service/connected-services-demo/actions/workflows/build.yml/badge.svg)](https://github.com/creek-service/connected-services-demo/actions/workflows/build.yml)
[![CodeQL](https://github.com/creek-service/connected-services-demo/actions/workflows/codeql.yml/badge.svg)](https://github.com/creek-service/connected-services-demo/actions/workflows/codeql.yml)

# Kafka Streams State Stores Demo

Repo containing the completed [Kafka Streams State Stores Demo](https://www.creekservice.org/ks-state-stores-demo)
and associated [docs](docs/README.md).

## WORK IN PROGRESS

This demo is not complete yet!

### Gradle commands

* `./gradlew` should be the go-to local command to run when developing.
              It will run `./gradlew format`, `./gradlew static` and `./gradlew check`.
* `./gradlew format` will format the code using [Spotless][spotless].
* `./gradlew static` will run static code analysis, i.e. [Spotbugs][spotbugs] and [Checkstyle][checkstyle].
* `./gradlew check` will run all checks and tests.
* `./gradlew jacocoTestReport` will generate [Jacoco][jacoco] coverage reports for each module.

[spotless]: https://github.com/diffplug/spotless
[spotbugs]: https://spotbugs.github.io/
[checkstyle]: https://checkstyle.sourceforge.io/
[jacoco]: https://www.jacoco.org/jacoco/trunk/doc/
