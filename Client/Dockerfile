FROM payara/server-full:latest as builder

FROM openjdk:8-jdk-alpine

# Clone

RUN mkdir -p /code
COPY . /code/
WORKDIR /code

COPY --from=builder /opt/payara/appserver/ ./appserver/

ENV CLASSPATH=".:/code/Beans.jar:./appserver/glassfish/lib/javaee.jar:./appserver/glassfish/lib/appserv-rt.jar:./appserver/glassfish/lib/gf-client.jar"

# Compile
RUN javac -classpath $CLASSPATH Client.java

CMD java Client