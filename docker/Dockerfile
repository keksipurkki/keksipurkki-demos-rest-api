FROM eclipse-temurin:23-jdk-alpine
ARG APP_JAR
RUN mkdir /app
COPY $APP_JAR /app
COPY ./api.yaml /app
WORKDIR /app
ENTRYPOINT ["java", "-jar", "/app/app.jar"]