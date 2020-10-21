set CLASSPATH=".;D:\nguye\Desktop\Projets\DB\glassfish5\glassfish\lib\javaee.jar;D:\nguye\Desktop\Projets\DB\glassfish5\glassfish\lib\gf-client.jar;D:\nguye\Desktop\Projets\DB\glassfish5\glassfish\lib\appserv-rt.jar"

javac -classpath %CLASSPATH% example\ExampleBean.java
javac -classpath %CLASSPATH% example\ExampleBeanRemote.java
jar cvf Beans.jar example/ExampleBean.class example/ExampleBeanRemote.class