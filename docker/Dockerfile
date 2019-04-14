FROM maven as buildStage

RUN git clone https://github.com/mysinmyc/sampleapp1.git

RUN echo "git.version=$(git describe --tags)"  > sampleapp1/frontend/src/main/resources/static/version.properties
RUN cd sampleapp1 && mvn versions:set -DnewVersion=$(git describe --tags) && mvn -Dmaven.test.skip=true package


FROM openjdk:8

EXPOSE 8080

COPY --from=buildStage /sampleapp1/frontend/target/frontend*.jar /

ADD entrypoint.sh /

CMD ["sh", "/entrypoint.sh"]
