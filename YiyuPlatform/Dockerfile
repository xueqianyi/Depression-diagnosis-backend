FROM java:8

EXPOSE 8080

ADD yiyuplatform-0.0.1-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'

# 指定环境配置文件
ENTRYPOINT["java","-jar","/app.jar","--spring.profiles.active=pro"]
