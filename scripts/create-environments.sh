#!/bin/bash

# Create environments
# To be run as an administrator!

# Variables
ORG=scm
SPACE1=test
SPACE2=staging
SPACE3=production
CF_USER=joedev
CF_PASSWORD=passw0rd
ORG_ROLE=OrgManager
SPACE_ROLE=SpaceDeveloper


# Do not change anything beyond here unless you know what you're doing.

# Create org and spaces
cf create-org $ORG
cf create-space $SPACE1 -o $ORG
cf create-space $SPACE2 -o $ORG
cf create-space $SPACE3 -o $ORG

# Create users
cf create-user $CF_USER $CF_PASSWORD
cf set-org-role $CF_USER $ORG $ORG_ROLE
cf set-space-role $CF_USER $ORG $SPACE1 $SPACE_ROLE
cf set-space-role $CF_USER $ORG $SPACE2 $SPACE_ROLE
cf set-space-role $CF_USER $ORG $SPACE3 $SPACE_ROLE

# Target staging
cf target -o $ORG -s $SPACE2

# Create auto-scale service instance in staging
cf create-service app-autoscaler standard i-autoscale-you
