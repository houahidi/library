echo "-----------------------------------------"
echo "Stop tomcat instance"
echo "-----------------------------------------"

SET CATALINA_HOME="C:\Formation\servers\apache-tomcat-8.0.39"

echo "Using tomcat %CATALINA_HOME%"

%CATALINA_HOME%\bin\catalina.bat stop

echo "-----------------------------------------"
echo "tomcat is stoping         			   "

timeout 5

echo "tomcat stoped            			   "
echo "-----------------------------------------"
