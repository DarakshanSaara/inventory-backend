# ---------- Build Stage ----------
FROM eclipse-temurin:17-jdk-alpine AS build

WORKDIR /app

# Copy Maven wrapper & pom
COPY inventory/pom.xml ./pom.xml
COPY inventory/mvnw ./mvnw
COPY inventory/.mvn ./.mvn

# Make mvnw executable
RUN chmod +x mvnw

# Download dependencies
RUN ./mvnw dependency:go-offline

# Copy source code
COPY inventory/src ./src

# Build JAR
RUN ./mvnw clean package -DskipTests


# ---------- Run Stage ----------
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
