FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY . /app

RUN ./mvnw clean package

EXPOSE 8080

CMD ["java", "-jar", "target/pet-grooming-0.0.1-SNAPSHOT.jar"]
