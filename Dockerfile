FROM gradle:jdk11 as build
COPY . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build -x check --no-daemon

FROM openjdk:11-jre-slim
EXPOSE 8080
COPY --from=build /home/gradle/src/build/libs/*.jar /app/doggy-back.jar

ENTRYPOINT ["java", "-jar","/app/doggy-back.jar"]