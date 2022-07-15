FROM java:8-jre
WORKDIR /usr/src

COPY ./target/Food-0.0.1-SNAPSHOT.jar Food-0.0.1-SNAPSHOT.jar
#EXPOSE 8001
ENTRYPOINT ["java","-jar","/usr/src/Food-0.0.1-SNAPSHOT.jar"]
