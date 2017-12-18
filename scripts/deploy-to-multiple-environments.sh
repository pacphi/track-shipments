#!/bin/bash

# Deploy to multiple environments
# Will execute a gradle cf-push with user/password to org and spaces configured below!

if [ "$#" -ne 2 ]
then
echo "Usage: ./deploy-to-multiple-environments.sh {CF_HOST} {CF_DOMAIN}"
  exit 1
fi

APP=track-shipments
ORG=scm
SPACE1=test
SPACE2=staging
SPACE3=production
CF_USER=joedev
CF_PASSWORD=passw0rd
CF_HOST=$1
CF_DOMAIN=$2

# Do not change anything beyond here unless you know what you're doing.

cd ..

# Push to test
gradle cf-push -Pcf.host=$APP-$SPACE1 -Pcf.ccHost=$CF_HOST -Pcf.domain=$CF_DOMAIN -Pcf.ccUser=$CF_USER -Pcf.ccPassword=$CF_PASSWORD -Pcf.org=$ORG -Pcf.space=$SPACE1
# Blue-green push to staging
gradle cf-push-blue-green -Pcf.host=$APP-$SPACE2 -Pcf.ccHost=$CF_HOST -Pcf.domain=$CF_DOMAIN -Pcf.ccUser=$CF_USER -Pcf.ccPassword=$CF_PASSWORD -Pcf.org=$ORG -Pcf.space=$SPACE2
# Blue-green push to production
gradle cf-push-blue-green -Pcf.host=$APP-$SPACE3 -Pcf.ccHost=$CF_HOST -Pcf.domain=$CF_DOMAIN -Pcf.ccUser=$CF_USER -Pcf.ccPassword=$CF_PASSWORD -Pcf.org=$ORG -Pcf.space=$SPACE3
