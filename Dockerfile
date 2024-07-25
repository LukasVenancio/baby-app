FROM maven:3.9.6-amazoncorretto-21

RUN mkdir /app
WORKDIR /app
COPY . /app

EXPOSE 8080

RUN ["mvn", "install", "-Dmaven.test.skip=true"]

CMD "mvn" "exec:java"

ENV PROFILE=dev
ENV DB_PASSWORD=
ENV DB_USERNAME=
ENV DB_URL=
ENV BASE_URL=