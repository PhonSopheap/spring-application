FROM openjdk:18
WORKDIR /app
ENV DB_HOST=db:5432 \
    DB_NAME=demo \
    DB_PASSWORD=password \
    DB_USER=postgres
COPY ./target/demo-0.0.1-SNAPSHOT.jar ./ROOT.jar
EXPOSE 8080
CMD ["java","-jar","ROOT.jar"]
