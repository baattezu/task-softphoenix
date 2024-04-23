FROM openjdk:17-oracle
WORKDIR /app
COPY target/test-zadanie-0.0.1-SNAPSHOT.jar /app
RUN mvnw install
RUN mkdir -p /flyway/sql
COPY src/main/resources/db/migration /flyway/sql
ENV FLYWAY_USER=postgres
ENV FLYWAY_PASSWORD=temirkhan322
ENV FLYWAY_URL=jdbc:postgresql://localhost:5432/softwarephoenix?currentSchema=boot
EXPOSE 8080
CMD ["flyway", "migrate"]
CMD ["java", "-jar", "test-zadanie-0.0.1-SNAPSHOT.jar"]