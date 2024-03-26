#FROM openjdk:21 as build
#WORKDIR /app
#COPY . ./
#RUN chmod +x mvnw
#RUN ./mvnw clean package -DskipTests
#
#FROM openjdk:21.0.2-jdk-slim
#WORKDIR /app
#COPY --from=build /app/target/DTO-Java12-0.0.1-SNAPSHOT.jar .
#CMD ["java", "-jar", "DTO-Java12-0.0.1-SNAPSHOT.jar"]
#EXPOSE 2023
# Use OpenJDK 21 as build image

FROM openjdk:21 as build
WORKDIR /app
COPY . ./
# Install Gradle
RUN apt-get update && \
    apt-get install -y wget unzip && \
    wget https://services.gradle.org/distributions/gradle-7.3-bin.zip && \
    unzip -d /opt/ gradle-7.3-bin.zip && \
    ln -s /opt/gradle-7.3/bin/gradle /usr/bin/gradle && \
    gradle -v
# Build the application using Gradle
RUN gradle build --no-daemon

# Use OpenJDK 21.0.2-jdk-slim as runtime image
FROM openjdk:15-jdk-slim
WORKDIR /app
COPY --from=build /app/build/libs/dto-java12-0.0.1-SNAPSHOT.jar ./
CMD ["java", "-jar", "dto-java12-0.0.1-SNAPSHOT.jar"]
EXPOSE 2023
