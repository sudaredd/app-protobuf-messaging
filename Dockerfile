FROM openjdk:11
ADD target/app-protobuf-messaging-0.0.1-SNAPSHOT.jar app-protobuf-messaging-0.0.1-SNAPSHOT.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app-protobuf-messaging-0.0.1-SNAPSHOT.jar" ]
