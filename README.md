# spring-cloud-stream-with-reactive

[Spring Cloud Stream, Reactorを使ってノンブロッキングなストリーム処理を実装する - Zenn](https://zenn.dev/abekoh/articles/4b898e6bc744fa)

## Overview

![overview](docs/overview.png)

## Setup

Dependencies are:

- Java 11
- Maven
- [gcloud tool](https://cloud.google.com/sdk/gcloud)

After install gcloud tool, please
setup [Cloud Pub/Sub emulator](https://cloud.google.com/pubsub/docs/emulator)

```bash
gcloud components install pubsub-emulator
gcloud components update
```

## Run

Prepare 4 terminals (#1-#4)

```bash
# build
mvn package

# run emulator (#1)
gcloud beta emulators pubsub start --project=reactive-sample --host-port=localhost:8090

# run supplier (#2)
java -jar spring-cloud-stream-with-reactive-supplier/target/spring-cloud-stream-with-reactive-supplier.jar

# run consumer (#3)
java -jar spring-cloud-stream-with-reactive-consumer/target/spring-cloud-stream-with-reactive-consumer.jar
```

## Request

```bash
# request HTTP/POST with curl (#4)
curl -X POST -H 'Content-Type: application/json' \
        -d '{"firstName":"Kotaro", "lastName":"Abe"}' \
        http://localhost:8080/api/v1/users
# -> 200 OK
```

supplier log (#1)

```
receive UserRequest(firstName=Taro, lastName=Yamada)
send User(userId=9a7108d2-0bf2-4ffe-ac24-2f26326c1640, firstName=Taro, lastName=Yamada)
```

consumer log (#2)

```
receive User(userId=9a7108d2-0bf2-4ffe-ac24-2f26326c1640, firstName=Taro, lastName=Yamada)
```
