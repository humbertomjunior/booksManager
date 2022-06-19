FROM adoptopenjdk/openjdk11:latest 
ADD target/booksManager-0.0.1-SNAPSHOT.jar booksManager-0.0.1-SNAPSHOT.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "booksManager-0.0.1-SNAPSHOT.jar"]
