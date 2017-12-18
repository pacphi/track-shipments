#!/bin/bash

# Destroy environments
# To be run as an administrator!

# Variables
ORG=scm
CF_ACCOUNT=joedev


# Do not change anything beyond here unless you know what you're doing.

# Delete org and spaces
cf delete-org $ORG -f

# Delete user
cf delete-user $CF_ACCOUNT -f
