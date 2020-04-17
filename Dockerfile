FROM adoptopenjdk/openjdk11:alpine-jre
WORKDIR /opt/app

ARG JAR_FILE=target/Homeweb-1.0.jar

# cp Homeweb-1.0.jar /opt/app/app.jar
COPY ${JAR_FILE} app.jar

# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","app.jar"]
