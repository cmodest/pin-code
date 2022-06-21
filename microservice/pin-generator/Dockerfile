FROM openjdk:11
VOLUME /tmp
COPY target/*.jar pin-generator.jar
ENTRYPOINT ["java", "-jar","/pin-generator.jar"]