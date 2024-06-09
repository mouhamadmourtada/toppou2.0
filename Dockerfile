# Utiliser une image Maven pour la construction
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app

# Copier le pom.xml pour résoudre les dépendances
COPY pom.xml .
RUN mvn dependency:go-offline

# Copier le reste des sources et construire le projet
COPY src ./src
RUN mvn package

# Utiliser une image de base OpenJDK pour l'exécution
FROM openjdk:17-jdk-slim

# Copier le jar construit à partir de la phase de construction précédente
COPY --from=build /app/target/*.jar /app/app.jar

# Exposer le port sur lequel l'application s'exécute
EXPOSE 8080

# Démarrer l'application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
