FROM ubuntu:latest as build

RUN apt-get update && apt-get install -y \
    openjdk-17-jdk \
    maven
WORKDIR /portaria

COPY pom.xml .
RUN mvn dependency:go-offline


COPY . .

RUN mvn clean package

FROM openjdk:17-jdk-slim

WORKDIR /portaria

COPY --from=build /portaria/target/portaria-0.0.1-SNAPSHOT.jar portaria.jar
EXPOSE 8080
ENV DATA_DIR=/var/lib/data
CMD ["java","-jar","portaria.jar"]