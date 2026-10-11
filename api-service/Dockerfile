# Build stage
FROM maven:3.9-eclipse-temurin-25-alpine AS build
LABEL authors="hendisantika"
WORKDIR /app

# Copy full project for Maven multi-module build (parent pom requires all modules)
COPY pom.xml .
COPY shared/ shared/
COPY api-service/ api-service/
COPY worker-service/ worker-service/

# Build api-service and its dependencies (shared)
RUN mvn -B package -pl api-service -am -DskipTests

# Runtime stage
FROM eclipse-temurin:25-jre-alpine-3.23
LABEL authors="hendisantika"
WORKDIR /app

RUN addgroup -S appgroup && adduser -S appuser -G appgroup
USER appuser

COPY --from=build /app/api-service/target/api-service-*.jar app.jar

EXPOSE 8080

HEALTHCHECK --interval=30s --timeout=3s --start-period=40s --retries=3 \
    CMD wget -qO- http://localhost:8080/actuator/health || exit 1

ENTRYPOINT ["java", "-jar", "app.jar"]
