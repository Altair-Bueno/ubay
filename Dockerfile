FROM ubuntu as unzip
RUN apt update
RUN apt install unzip
ADD https://www.eclipse.org/downloads/download.php?file=/ee4j/glassfish/glassfish-6.2.3.zip ./glassfish-6.2.3.zip
RUN unzip ./glassfish-6.2.3.zip

FROM adoptopenjdk/openjdk11
COPY --from=unzip glassfish6 glassfish6
COPY glassfish/domain.xml /glassfish6/glassfish/domains/domain1/config/domain.xml
COPY target/*.war /glassfish6/glassfish/domains/domain1/autodeploy/
ENV GLASSFISH_HOME /glassfish6
ADD https://jdbc.postgresql.org/download/postgresql-42.3.3.jar  /glassfish6/glassfish/lib/postgresql-42.3.3.jar
ADD glassfish/start-glassfish.sh /
RUN chmod +x /start-glassfish.sh
ENTRYPOINT ["/start-glassfish.sh"]