echo "-----------------------------------------"
echo "Start tomcat instance"
echo "-----------------------------------------"

SET CATALINA_HOME="C:\Formation\servers\apache-tomcat-8.0.39"

echo "Using tomcat %CATALINA_HOME%"

%CATALINA_HOME%\bin\catalina.bat start

echo "-----------------------------------------"
echo "tomcat is starting         			   "

timeout 5

echo "tomcat started            			   "
echo "-----------------------------------------"
