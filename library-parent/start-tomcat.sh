echo "-----------------------------------------"
echo "Start tomcat instance"
echo "-----------------------------------------"

export CATALINA_HOME="/Users/houahidi/Servers/apache-tomcat-8.5.8"

$CATALINA_HOME/bin/catalina.sh start

echo "-----------------------------------------"
echo "tomcat is starting         			   "

sleep 3

echo "tomcat started            			   "
echo "-----------------------------------------"
