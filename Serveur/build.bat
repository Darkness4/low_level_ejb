set CLASSPATH=".;D:\nguye\Desktop\Projets\DB\glassfish5\glassfish\lib\javaee.jar;D:\nguye\Desktop\Projets\DB\glassfish5\glassfish\lib\gf-client.jar;D:\nguye\Desktop\Projets\DB\glassfish5\glassfish\lib\appserv-rt.jar"

javac -classpath %CLASSPATH% example/*.java
del example\*_.java example\*_.class
jar cvf Beans.jar example/*.class META-INF\*.xml
asadmin deploy --force Beans.jar