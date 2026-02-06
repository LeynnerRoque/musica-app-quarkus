# Estágio 1: Build
FROM maven:3.9.6-eclipse-temurin-17 AS build
COPY . /usr/src/app
WORKDIR /usr/src/app
# Compila o projeto gerando a estrutura do Quarkus
RUN mvn clean package -DskipTests

# Estágio 2: Runtime
FROM eclipse-temurin:17-jre
ENV LANGUAGE='en_US:en'

# Cria o diretório da aplicação
WORKDIR /deployments

# Copia apenas o necessário do estágio de build
COPY --from=build /usr/src/app/target/quarkus-app/lib/ /deployments/lib/
COPY --from=build /usr/src/app/target/quarkus-app/*.jar /deployments/
COPY --from=build /usr/src/app/target/quarkus-app/app/ /deployments/app/
COPY --from=build /usr/src/app/target/quarkus-app/quarkus/ /deployments/quarkus/

EXPOSE 8080
USER 185

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-Dquarkus.http.host=0.0.0.0", "-Djava.util.logging.manager=org.jboss.logmanager.LogManager", "-jar", "/deployments/quarkus-run.jar"]