FROM jdk8-alpine

ENV TZ=Asia/Ho_Chi_Minh

RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

WORKDIR /app
COPY /target/*.jar /app/

CMD ["java","-jar","app.jar"]
