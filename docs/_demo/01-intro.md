---
title: Connected Services Demo
permalink: /
layout: single
toc: true
---

This tutorial will lead you through extending the [Basic Kafka Streams demo](/basic-kafka-streams-demo) with a second
microservice, which will consume the output Kafka topic of `handle-occurrence-service` service written in the previous
demo. This topic contains occurrences of Twitter handles, e.g. `@elonmusk`, in tweets and the new service will use this
to build a leaderboard of the most mentioned Twitter handles.

**Note:** This is a deliberately simplistic service, allowing the tutorial to focus on demonstrating Creek's features.
{: .notice--warning}

## Features covered

By the end of this tutorial you should know:
  todo

## Prerequisites

The tutorial requires the following:

* A [GitHub](https://github.com/join) account.
* [Git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git) installed for source code control.
* [Docker desktop](https://docs.docker.com/desktop/) installed for running containerised system tests.
* (Optional) [IntelliJ IDE](https://www.jetbrains.com/help/idea/installation-guide.html) installed for code development.

## Design

todo

## Complete solution

The completed tutorial can be viewed [on GitHub][demoOnGh].

[<i class="fab fa-fw fa-github"/>&nbsp; View on GitHub][demoOnGh]{: .btn .btn--success}

[demoOnGh]: https://github.com/creek-service/connected-services-demo