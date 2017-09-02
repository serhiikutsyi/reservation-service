FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD build/libs/reservation-service-0.0.1-SNAPSHOT.jar reservation-service.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djdocker ava.security.egd=file:/dev/./urandom -jar /reservation-service.jar" ]