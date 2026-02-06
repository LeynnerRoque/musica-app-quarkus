# Estágio 1: Build
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /usr/src/app

# Copia explicitamente o arquivo de configuração primeiro (ajuda no cache e evita erros)
COPY pom.xml .
# Copia a pasta de código-fonte
COPY src ./src

# Agora o comando NÃO TEM como não encontrar o pom.xml
RUN mvn clean package -DskipTests

# Estágio 2: Runtime
FROM eclipse-temurin:17-jre
WORKDIR /deployments

# Copia os artefatos do estágio de build
COPY --from=build /usr/src/app/target/quarkus-app/lib/ /deployments/lib/
COPY --from=build /usr/src/app/target/quarkus-app/*.jar /deployments/
COPY --from=build /usr/src/app/target/quarkus-app/app/ /deployments/app/
COPY --from=build /usr/src/app/target/quarkus-app/quarkus/ /deployments/quarkus/

EXPOSE 8080
USER 185

ENTRYPOINT ["java", "-Dquarkus.http.host=0.0.0.0", "-Djava.util.logging.manager=org.jboss.logmanager.LogManager", "-jar", "/deployments/quarkus-run.jar"]