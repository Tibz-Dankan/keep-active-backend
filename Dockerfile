
FROM openjdk:11-jdk-slim
COPY /target/keep-active-backend-0.0.1-SNAPSHOT.jar keep-active-backend.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","keep-active-backend.jar"]
