FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app

COPY ./mvc ./mvc

WORKDIR /app/mvc

RUN mvn clean package -DskipTests

FROM openjdk:17-slim
WORKDIR /app

COPY --from=build /app/mvc/target/mvc-1.0-SNAPSHOT.jar mvc.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "mvc.jar"]

HEALTHCHECK --interval=30s --timeout=10s --retries=3 CMD curl --fail http://localhost:8080/actuator/health || exit 1
