# Buildar a aplicação
FROM maven:3.8-openjdk-17 AS builder

WORKDIR /app

COPY . /app

RUN mvn clean install -DskipTests

# Resposavel por realizar o deploy da aplicação 
FROM openjdk:17

LABEL maintainer="Grupo 47"

WORKDIR /app

COPY --from=builder /app/target/fast-food-api-0.0.1-SNAPSHOT.jar /app/fast-food-api-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "fast-food-api-0.0.1-SNAPSHOT.jar"]

EXPOSE 8080