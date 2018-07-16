FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD springboot_demo-0.0.1-SNAPSHOT.jar springboot_demo.jar

RUN sh -c 'touch /springboot_demo.jar && /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime  && echo 'Asia/Shanghai' >/etc/timezone   '

    
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /springboot_demo.jar -Xms128m -Xmx256m" ]