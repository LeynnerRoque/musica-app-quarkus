# Estágio 1: Build (Usando JDK 21)
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /project
COPY . .
RUN mvn clean package -DskipTests

# Estágio 2: Runtime (Usando JRE 21)
FROM eclipse-temurin:21-jre
ENV LANGUAGE='en_US:en'
WORKDIR /deployments

COPY --from=build /project/target/quarkus-app/lib/ /deployments/lib/
COPY --from=build /project/target/quarkus-app/*.jar /deployments/
COPY --from=build /project/target/quarkus-app/app/ /deployments/app/
COPY --from=build /project/target/quarkus-app/quarkus/ /deployments/quarkus/

EXPOSE 8080
USER 185

ENTRYPOINT ["java", "-Dquarkus.http.host=0.0.0.0", "-Djava.util.logging.manager=org.jboss.logmanager.LogManager", "-jar", "/deployments/quarkus-run.jar"]