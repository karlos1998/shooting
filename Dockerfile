FROM eclipse-temurin:23-jdk

ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

# Skopiowanie folderu resources do kontenera
COPY src/main/resources/ /app/resources/

ENTRYPOINT ["java","-jar","/app.jar"]
