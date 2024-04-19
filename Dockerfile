FROM maven:3.8.5-openjdk-17-slim AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /target/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
