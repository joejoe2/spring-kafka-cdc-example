# spring-kafka-cdc-example

## Description

This is an example project to demonstrate how to use [debezium](https://debezium.io/) to capture
data changes as events from SpringBoot application to kafka, and consume these events at downstream application.


## Get started

1. install JDK-17, Maven, and Jmeter (recommended to use IntelliJ IDE)

2. clone the repo and run `mvn install` or `./mvnw install` in `./producer` and `./consumer`

3. install docker and docker-compose

4. execute `docker-compose up -d`

5. execute `curl -i -X POST -H "Content-Type:application/json" localhost:8083/connectors/ -d "@./connector.json"` or `curl.exe -i -X POST -H "Content-Type:application/json" localhost:8083/connectors/ -d "@./connector.json"`

6. run each application in `./producer` and `./consumer`

7. launch Jmeter and open `cdc.jmx`, then execute the test script

8. view `http://localhost:8080/`, you can see ui for kafka

9. view `http://localhost:8002/actuator/prometheus`, you can see kafka consumer metrics start with prefix `spring_kafka_`
