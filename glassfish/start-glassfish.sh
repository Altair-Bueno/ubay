#!/bin/bash

export ADMIN_USER=admin
export ADMIN_PASSWORD=admin
export DOMAIN=domain1

# set credentials to admin/admin

echo "AS_ADMIN_PASSWORD=
AS_ADMIN_NEWPASSWORD=$ADMIN_PASSWORD"> /opt/tmpfile

echo "AS_ADMIN_PASSWORD=$ADMIN_PASSWORD"> /opt/pwdfile


$GLASSFISH_HOME/bin/asadmin start-domain $DOMAIN && \
 $GLASSFISH_HOME/bin/asadmin --user $ADMIN_USER --passwordfile=/opt/tmpfile change-admin-password && \
 $GLASSFISH_HOME/bin/asadmin --user $ADMIN_USER --passwordfile=/opt/pwdfile enable-secure-admin && \
 rm -fr $GLASSFISH_HOME/glassfish/domains/$DOMAIN/autodeploy/.autodeploystatus \
 $GLASSFISH_HOME/bin/asadmin restart-domain $DOMAIN

tail -f /glassfish6/glassfish/domains/domain1/logs/server.log