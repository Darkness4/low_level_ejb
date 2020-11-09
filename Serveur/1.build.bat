set CLASSPATH=".;D:\nguye\Desktop\Projets\DB\payara5\glassfish\lib\javaee.jar;D:\nguye\Desktop\Projets\DB\payara5\glassfish\lib\appserv-rt.jar;D:\nguye\Desktop\Projets\DB\payara5\glassfish\lib\gf-client.jar"

javac -classpath %CLASSPATH% example/*.java
del example\*_.java example\*_.class
jar cvf Beans.jar example/*.class example/exceptions/*.class META-INF\*.xml
pause