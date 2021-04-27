FROM registry.ap-southeast-1.aliyuncs.com/kxch/centos-jdk11:v1

ENV  LANG C.UTF-8

ADD target/*-SNAPSHOT.jar app.jar
RUN touch /app.jar
CMD ["java", "-jar", "/app.jar"]