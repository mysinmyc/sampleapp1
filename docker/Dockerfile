FROM maven as buildStage

RUN git clone https://github.com/mysinmyc/sampleapp1.git

RUN cd sampleapp1 \
	&& echo "git.version=$(git describe --tags)"  > frontend/src/main/resources/static/version.properties \
	&& mvn versions:set -DnewVersion=$(git describe --tags) \
	&& mvn -Pproduction-mode,mariadb -Dmaven.test.skip=true package 


FROM openjdk:8

EXPOSE 8080

COPY --from=buildStage /sampleapp1/frontend/target/frontend*.jar /

ADD entrypoint.sh /

ENV APPUSERS_PASSWORD t0polino

ENV JAVA_ADDITIONALARGS "-Dvaadin.productionMode=true"

CMD ["sh", "/entrypoint.sh"]
