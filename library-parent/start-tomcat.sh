echo "-----------------------------------------"
echo "Start tomcat instance"
echo "-----------------------------------------"

set CATALINA_HOME="/Users/houahidi/Servers/apache-tomcat-8.0.39"
set PATH=$CATALINA_HOME/bin:$PATH
./catalina.sh start
