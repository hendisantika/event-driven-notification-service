# Build stage
FROM maven:3.9-eclipse-temurin-25-alpine AS build
LABEL authors="hendisantika"
WORKDIR /app

# Copy full project for Maven multi-module build (parent pom requires all modules)
COPY pom.xml .
COPY shared/ shared/
COPY api-service/ api-service/
COPY worker-service/ worker-service/

# Build worker-service and its dependencies (shared)
RUN mvn -B package -pl worker-service -am -DskipTests

# Runtime stage
FROM eclipse-temurin:25-jre-alpine-3.23
LABEL authors="hendisantika"
WORKDIR /app

RUN addgroup -S appgroup && adduser -S appuser -G appgroup
USER appuser

COPY --from=build /app/worker-service/target/worker-service-*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
