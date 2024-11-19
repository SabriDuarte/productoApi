# Usa una imagen de Java como base
FROM openjdk:23-jdk-slim

# Establece el directorio de trabajo en el contenedor
WORKDIR /app

# Copia el archivo JAR de la aplicación al contenedor
COPY target/demo-0.0.1-SNAPSHOT.jar /app/demo.jar

# Expón el puerto que usará la aplicación
EXPOSE 8089

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/demo.jar"]
