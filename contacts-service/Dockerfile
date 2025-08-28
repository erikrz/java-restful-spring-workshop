FROM eclipse-temurin:21-jre-alpine
COPY contacts-service/target/ContactsService.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
