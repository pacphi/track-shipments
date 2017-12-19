#!/bin/bash

# Deploy to staging environments
# Will execute a gradle cf-push-blue-green with user/password to org and space configured below!

if [ "$#" -ne 2 ]
then
echo "Usage: ./deploy-to-staging.sh {CF_HOST} {CF_DOMAIN}"
  exit 1
fi

APP=track-shipments
ORG=scm
SPACE=staging
CF_USER=ci-daemon
CF_PASSWORD=passw0rd
CF_HOST=$1
CF_DOMAIN=$2

# Do not change anything beyond here unless you know what you're doing.

cd ..

# Blue-green push to staging
gradle cf-push-blue-green -Pcf.host=$APP-$SPACE -Pcf.ccHost=$CF_HOST -Pcf.domain=$CF_DOMAIN -Pcf.ccUser=$CF_USER -Pcf.ccPassword=$CF_PASSWORD -Pcf.org=$ORG -Pcf.space=$SPACE
