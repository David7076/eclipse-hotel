FROM maven:3.9.8-eclipse-temurin-21-alpine AS build
WORKDIR /opt/app
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine
WORKDIR /opt/app
COPY --from=build /opt/app/target/*.jar /opt/app/app.jar
ENV SPRING_PROFILES_ACTIVE=dev
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]