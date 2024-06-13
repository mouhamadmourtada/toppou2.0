#!/bin/bash

# Démarrer MySQL en arrière-plan
service mysql start

# Démarrer l'application Spring Boot
java -jar /app/app.jar
