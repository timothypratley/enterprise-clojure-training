FROM openjdk:8-jre-alpine
RUN mkdir -p /app
COPY ./target/*standalone.jar /app
WORKDIR /app
CMD ["/bin/sh", "-c", "java -jar /app/*standalone.jar"]
