FROM maven:3.9-eclipse-temurin-17-alpine AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:resolve dependency:resolve-plugins
# RUN mvn verify --fail-never

COPY . .
RUN mvn package -DskipTests


FROM eclipse-temurin:17-jre-alpine AS runtime
WORKDIR /app

COPY --from=build /app/target/emailSender-0.0.1-SNAPSHOT.jar .

EXPOSE 7443

LABEL org.opencontainers.image.source=https://github.com/Git-Commit-Therapy/java-email-sender


ENTRYPOINT ["java", "-jar", "./emailSender-0.0.1-SNAPSHOT.jar"]
