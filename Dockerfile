FROM openjdk:17-jdk-oraclelinux8
RUN groupadd -r spring && adduser -r spring -g spring
USER spring:spring
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]