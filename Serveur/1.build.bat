set CLASSPATH=".;D:\nguye\Desktop\Projets\DB\payara5\glassfish\lib\javaee.jar;D:\nguye\Desktop\Projets\DB\payara5\glassfish\lib\appserv-rt.jar;D:\nguye\Desktop\Projets\DB\payara5\glassfish\lib\gf-client.jar"

del example\*.class example\entities\*.class example\exceptions\*.class
javac -classpath %CLASSPATH% example\*.java
javac -classpath %CLASSPATH% example\entities\*.java
javac -classpath %CLASSPATH% example\exceptions\*.java
del example\*_.java example\*_.class
del example\entities\*_.java example\entities\*_.class
del example\exceptions\*_.java example\exceptions\*_.class
jar cvf Beans.jar example\*.class example\exceptions\*.class example\entities\*.class META-INF\*.xml
pause