FROM openjdk:8-jre-alpine
LABEL maintainer="info@cryptobalancecheck.com"
ADD ./target/cryptobalancecheck*.jar /app.jar
ENTRYPOINT ["java","-agentlib:jdwp=transport=dt_socket,address=8000,server=y,suspend=n","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]