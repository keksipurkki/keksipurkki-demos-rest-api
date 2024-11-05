FROM alpine
ARG APP_JAR
RUN mkdir /app
COPY $APP_JAR /app
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
