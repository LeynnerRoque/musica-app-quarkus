# Estágio 1: Build
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /project

# Copia tudo para o container (incluindo o pom.xml e a pasta src)
COPY . .

# Executa o build do Quarkus
RUN mvn clean package -DskipTests

# Estágio 2: Runtime
FROM eclipse-temurin:17-jre
WORKDIR /deployments

# Copia os artefatos gerados pelo Quarkus no estágio anterior
COPY --from=build /project/target/quarkus-app/lib/ /deployments/lib/
COPY --from=build /project/target/quarkus-app/*.jar /deployments/
COPY --from=build /project/target/quarkus-app/app/ /deployments/app/
COPY --from=build /project/target/quarkus-app/quarkus/ /deployments/quarkus/

EXPOSE 8080
USER 185

ENTRYPOINT ["java", "-Dquarkus.http.host=0.0.0.0", "-Djava.util.logging.manager=org.jboss.logmanager.LogManager", "-jar", "/deployments/quarkus-run.jar"]