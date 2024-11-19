FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ./target/librarian-tool-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 18000
ENTRYPOINT ["java","-jar","/app.jar"]