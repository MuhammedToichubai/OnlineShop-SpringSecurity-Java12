FROM openjdk:21 as build
WORKDIR /app
COPY . ./
RUN microdnf install findutils
RUN ./gradlew build -x test

FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=build /app/build/libs/dto-java12-0.0.1-SNAPSHOT.jar .
CMD ["java", "-jar", "dto-java12-0.0.1-SNAPSHOT.jar"]
EXPOSE 2023
