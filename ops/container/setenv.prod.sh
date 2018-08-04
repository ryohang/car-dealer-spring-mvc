export CATALINA_OPTS="$CATALINA_OPTS -ea"
export CATALINA_OPTS="$CATALINA_OPTS -Xms1g"
export CATALINA_OPTS="$CATALINA_OPTS -Dspring.profiles.active=prod"
export CATALINA_OPTS="$CATALINA_OPTS -Daws.region=$REGION"