FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD springboot_demo-0.0.1-SNAPSHOT.jar springboot_demo.jar

ENV  TIME_ZONE Asiz/Shanghai

RUN sh -c 'touch /springboot_demo.jar  && apk add --no-cache tzdata '

    
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /springboot_demo.jar -Xms128m -Xmx256m" ]