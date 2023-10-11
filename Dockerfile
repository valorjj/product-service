FROM eclipse-temurin:17-jdk
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} productservice.jar
ENTRYPOINT ["java", "-jar", "/productservice.jar"]
EXPOSE 8081