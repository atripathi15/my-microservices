FROM openjdk:8-jdk-alpine
EXPOSE 8080
ARG JAR_FILE=target/user-service-0.0.1.jar
ADD ${JAR_FILE} user-service-0.0.1.jar
ENTRYPOINT ["java","-jar","/user-service-0.0.1.jar"]