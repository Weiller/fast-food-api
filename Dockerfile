FROM openjdk:17

LABEL maintainer="Grupo 47"

WORKDIR /app

COPY target/fast-food-api-0.0.1-SNAPSHOT.jar /app/fast-food-api-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "fast-food-api-0.0.1-SNAPSHOT.jar"]

EXPOSE 8080