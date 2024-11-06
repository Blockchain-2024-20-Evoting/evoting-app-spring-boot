# IMAGEN MODELO
FROM eclipse-temurin:21.0.5_11-jdk

ARG JAR_FILE=target/evoting-api-spring-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} app_evoting_api.jar

# PUERTO DE EJECUCION DEL CONTENEDOR -> INFORMATIVO
EXPOSE 8080

# CONSTRUIR APLICACION CUANDO EL CONTENEDOR INICIE
ENTRYPOINT ["java", "-jar", "app_evoting_api.jar"]






