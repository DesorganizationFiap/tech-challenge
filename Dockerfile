FROM maven:3.8.5-openjdk-17-slim
MAINTAINER Grupo 10
COPY . /usr/bin
WORKDIR /usr/bin
RUN mvn clean install -DskipTests -Pdocker -q
ENTRYPOINT java -jar target/fiap-fast-food-1.0.jar
EXPOSE 8080

# Copie o jar do seu aplicativo para o contêiner
COPY target/fiap-fast-food-1.0.jar /usr/app/fiap-fast-food-1.0.jar

# Comando para executar seu aplicativo
CMD ["java", "-jar", "/usr/app/fiap-fast-food-1.0.jar"]
