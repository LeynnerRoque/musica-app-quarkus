# Estágio 1: Build
FROM maven:3.9.6-eclipse-temurin-17 AS build
# Define o diretório de trabalho logo no início
WORKDIR /usr/src/app
# Copia todos os arquivos da raiz do projeto para dentro do WORKDIR
COPY . .
# Agora o mvn clean package será executado onde o pom.xml foi copiado
RUN mvn clean package -DskipTests

# Estágio 2: Runtime
FROM eclipse-temurin:17-jre
ENV LANGUAGE='en_US:en'
WORKDIR /deployments

# Copia os arquivos gerados no estágio de build
# O caminho aqui deve ser relativo ao WORKDIR do estágio anterior
COPY --from=build /usr/src/app/target/quarkus-app/lib/ /deployments/lib/
COPY --from=build /usr/src/app/target/quarkus-app/*.jar /deployments/
COPY --from=build /usr/src/app/target/quarkus-app/app/ /deployments/app/
COPY --from=build /usr/src/app/target/quarkus-app/quarkus/ /deployments/quarkus/

EXPOSE 8080
USER 185

ENTRYPOINT ["java", "-Dquarkus.http.host=0.0.0.0", "-Djava.util.logging.manager=org.jboss.logmanager.LogManager", "-jar", "/deployments/quarkus-run.jar"]