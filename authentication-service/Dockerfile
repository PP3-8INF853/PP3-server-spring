#FROM maven:3.8.3-openjdk-17 AS DEPS
#WORKDIR app/
#COPY ./../account-service/pom.xml account-service/pom.xml
#RUN mvn -e -B dependency:resolve
#COPY ./../account-service/src account-service/src
#RUN mvn clean -e -B package

FROM maven:3.8.3-openjdk-17 AS Builder
WORKDIR app/
#ARG JAR_FILE=target/account-service-0.0.1-SNAPSHOT.jar
#COPY --from=DEPS /app/${JAR_FILE} account-service-0.0.1-SNAPSHOT.jar
COPY pom.xml .
RUN mvn -e -B dependency:resolve
COPY src ./src
RUN mvn clean -e -B package -DskipTests=true

FROM amazoncorretto:17-alpine-jdk
WORKDIR app/
ARG JAR_FILE=target/*.jar
COPY --from=Builder /app/${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]