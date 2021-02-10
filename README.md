# Track Shipments Sample

[![Build Status](https://travis-ci.org/pacphi/track-shipments.svg?branch=master)](https://travis-ci.org/pacphi/track-shipments) [![Known Vulnerabilities](https://snyk.io/test/github/pacphi/track-shipments/badge.svg)](https://snyk.io/test/github/pacphi/track-shipments)

This is a [Spring Boot](http://projects.spring.io/spring-boot/) application for demonstration purposes.

## Prerequisites

* Java [AdoptOpenJDK](https://adoptopenjdk.net/) 11.0.7 or better
* [Gradle](https://gradle.org) 6.8.2
	* 
* [CF CLI](https://github.com/cloudfoundry/cli#downloads) 6.51.0 or better if you want to push the application to a Cloud Foundry (CF) instance
* [Postman](https://www.getpostman.com) 7.28.0 or better to simplify interaction with API endpoints 

## Clone

```
$ git clone https://github.com/pacphi/track-shipments.git
```

## How to build

```
$ cd track-shipments
$ gradle clean install
```

## How to run

```
$ gradle bootRun
```

> Press `Ctrl+C` to exit.


## How to target (a CF instance)

```
$ cf api {CF_INSTANCE_URL}
$ cf login
```

> When prompted supply account credentials. Then execute...

```
$ cf target -o {ORG} -s {SPACE}
```

> to target an appropriate organization and space.


## How to deploy (to a CF instance)

### with manifest.yml

```
$ cf push
```

### with [pivotalservices/ya-cf-app-gradle-plugin](https://github.com/pivotalservices/ya-cf-app-gradle-plugin#using-the-plugin)

```
$ gradle cf-push -Pcf.ccHost={CF_INSTANCE_URL} -Pcf.ccUser={CF_USER} -Pcf.ccPassword={CF_PASSWORD} -Pcf.org={ORG} -Pcf.space={SPACE}
```

## Blue-green deployments (to a CF instance)

```
$ gradle cf-push-blue-green -Pcf.ccHost={CF_INSTANCE_URL} -Pcf.domain={CF_DOMAIN} -Pcf.ccUser={CF_USER} -Pcf.ccPassword={CF_PASSWORD} -Pcf.org={ORG} -Pcf.space={SPACE}
```

## How to delete the application (from a CF instance)

```
cf delete track-shipments
```

## API Endpoints

This collection is a work-in-progress:

```
POST /shipments
PUT /shipments/{tracking_number}
DELETE /shipments/{tracking_number}
GET /shipments
GET /shipments/{tracking_number}
GET /shipments/{tracking_number}/addressFrom
GET /shipments/{tracking_number}/addressTo
```

## Scripts

Found in the `/scripts` directory

| Script   |  Description |
|---|---|
| create-environments.sh  | Creates accounts, org, and spaces.  |
| destroy-environments.sh | Deletes accounts, org, and spaces that were created. |
| deploy-to-multiple-environments.sh | Executes a gradle cf-push with an account to org and spaces (as configured). |
| deploy-to-staging.sh  | Executes a gradle cf-push with an account to staging environment (org/space).   |

 
## Load testing

1. Sign up for an account on [loader.io](https://loader.io).
2. Configure a new test.
3. Before you're able to execute a load test run you will be asked to place a loader.io file token.  Download and save the file to `src/main/resources/static`.
