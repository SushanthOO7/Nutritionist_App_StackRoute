FROM java:8-jre
WORKDIR /usr/src
ENV MYSQL_HOST=localhost
ENV MYSQL_DATABASE=testdb
ENV MYSQL_USER=dbuser1
ENV MYSQL_PASSWORD=pass
COPY ./target/User-0.0.1-SNAPSHOT.jar User-0.0.1-SNAPSHOT.jar
#EXPOSE 8082
ENTRYPOINT ["java","-jar","/usr/src/User-0.0.1-SNAPSHOT.jar"]
