FROM anapsix/alpine-java:8_server-jre_unlimited
VOLUME /tmp
MAINTAINER Draper
ENV LANG C.UTF-8
ADD dboot.jar app.jar
EXPOSE 8081
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo 'Asia/Shanghai' >/etc/timezone
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar","${SPRING_PROFILES_ACTIVE}"]