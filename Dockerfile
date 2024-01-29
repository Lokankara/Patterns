FROM maven:3.8.4-openjdk-17-slim AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-slim
COPY --from=build /target/structural-1.0-SNAPSHOT.jar structural.jar

EXPOSE 8080 8443

ENTRYPOINT ["java", "-jar", "structural.jar"]
