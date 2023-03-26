FROM jasondennis12138/java-base as builder

ARG JAR_FILE=target/*.jar

ADD ${JAR_FILE} app.jar

EXPOSE 10005

ENTRYPOINT ["java","-jar","/app.jar"]
