FROM openjdk:17-slim
#container içerisinde çalışacağımız klasor
WORKDIR /app
#expose: bilgi verme amaçlıdır. kullanılan portları gösterir.
EXPOSE 8080
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /app/edu.jar
# Uygulamanın çalıştırılmasını sağlayan komutu belirtir
ENTRYPOINT ["java", "-jar", "/app/edu.jar"]