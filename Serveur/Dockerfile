FROM payara/server-full:latest as deps

FROM openjdk:8-jdk-alpine as builder

# Clone
RUN mkdir -p /code
COPY . /code/
WORKDIR /code

COPY --from=deps /opt/payara/appserver/ ./appserver/

ENV CLASSPATH=".:./appserver/glassfish/lib/javaee.jar:./appserver/glassfish/lib/appserv-rt.jar:./appserver/glassfish/lib/gf-client.jar"

# Compile
RUN javac -classpath $CLASSPATH example/*.java
RUN rm -rf example/*_.java example/*_.class
RUN jar cvf Beans.jar example/*.class META-INF/*.xml

FROM payara/server-full:latest
COPY --from=builder /code/Beans.jar $HOME_DIR

COPY ./postgresql-42.2.18.jar $PAYARA_DIR/glassfish/domains/production/lib/postgresql-42.2.18.jar
RUN echo "create-jdbc-connection-pool --datasourceclassname org.postgresql.ds.PGSimpleDataSource --restype javax.sql.ConnectionPoolDataSource --property portNumber=5432:password=bookshelf:user=bookshelf:serverName=db:databaseName=biblio biblioPool" > $POSTBOOT_COMMANDS
RUN echo "create-jdbc-resource --connectionpoolid biblioPool jdbc/biblio" >> $POSTBOOT_COMMANDS
RUN echo "deploy --force Beans.jar" >> $POSTBOOT_COMMANDS