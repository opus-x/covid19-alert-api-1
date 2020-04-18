FROM gradle:jdk10

RUN mkdir /home/gradle/code
WORKDIR /home/gradle/code
ADD . /home/gradle/code/
EXPOSE 8080
ENTRYPOINT ["./gradlew", "bootRun", "-Dspring.profiles.active=docker"]