FROM eclipse-temurin:17-jdk-jammy AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8989
ENTRYPOINT ["java","-jar","app.jar"]
