# Utiliser une image de base OpenJDK
FROM openjdk:17-jdk-slim

# Ajouter un argument pour spécifier le nom du fichier JAR
ARG JAR_FILE=target/*.jar

# Copier le fichier JAR dans l'image
COPY ${JAR_FILE} app.jar

# Créer le répertoire pour les fichiers uploadés
RUN mkdir -p /app/uploads

# Ajouter un script d'attente pour MySQL
COPY wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh

# Exposer le port sur lequel l'application s'exécute
EXPOSE 8080

# Démarrer l'application
ENTRYPOINT ["/wait-for-it.sh", "db:3306", "--", "java", "-jar", "/app.jar"]
