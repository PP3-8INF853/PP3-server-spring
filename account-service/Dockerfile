FROM maven:3.8.3-openjdk-17 AS Builder
WORKDIR app/
COPY pom.xml .
RUN mvn -e -B dependency:resolve -DskipTests=true
COPY src ./src
RUN mvn clean -e -B package -DskipTests=true

FROM amazoncorretto:17-alpine-jdk
WORKDIR app/
ARG JAR_FILE=target/*.jar
COPY --from=Builder /app/${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]