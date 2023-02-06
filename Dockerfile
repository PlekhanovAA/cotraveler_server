#FROM maven:3.8.6-openjdk-18 AS builder
#COPY . /cotraveler
#WORKDIR /cotraveler
#RUN mvn install "-X" "-Dmaven.test.skip=true"

FROM openjdk:18-slim
ENV TZ=Europe/Moscow
RUN echo "Europe/Moscow" > /etc/timezone
#COPY --from=builder cotraveler/target/coTraveler.jar coTraveler.jar
COPY target/coTraveler.jar coTraveler.jar
ENTRYPOINT ["java","-jar","/coTraveler.jar"]
EXPOSE 8080
