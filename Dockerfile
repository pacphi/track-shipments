FROM gradle:6.6.1-jdk11 as BUILD

COPY . /app

# @see https://stackoverflow.com/questions/46792438/build-gradle-project-inside-a-docker
USER root
RUN chown -R gradle /app
USER gradle

RUN gradle -b /app/build.gradle clean build -s


FROM openjdk:11-jre-slim
ENV PORT 8080
EXPOSE 8080
WORKDIR /opt
COPY --from=BUILD /app/build/libs/app-1.0-SNAPSHOT.jar /opt/app.jar
CMD ["java", "-jar", "app.jar"]
