pipeline {
	agent any
	tools {
		jdk 'jdk-8u162'
		gradle 'gradle-4.5'
	}
	parameters {
		string(name: 'CF_API', defaultValue: 'api.run.pivotal.io', description: 'API endpoint used to target a Cloud Foundry foundation.')
		string(name: 'CF_DOMAIN', defaultValue: 'cfapps.io', description: 'Applications sub-domain; APP_NAME will typically prefix this value.')
		string(name: 'CF_ORGANIZATION', defaultValue: 'zoo-labs', description: 'A pre-existing organization.')
		string(name: 'CF_SPACE', defaultValue: 'test', description: 'A pre-existing space; equivalent to an environment.')
	}
	stages {
		stage('Build project') {
			steps {
				sh 'gradle clean build'
			}
		}
		stage('SonarQube Analysis') {
			environment {
				SONARQUBE_HOST = credentials('SONARQUBE_HOST')
				SONARQUBE_TOKEN = credentials('SONARQUBE_TOKEN')
			}
			steps {
				withSonarQubeEnv('sonarqube-lts') {
					sh "gradle sonarqube -Dsonar.host.url=$SONARQUBE_HOST -Dsonar.login=$SONARQUBE_TOKEN"
				}
			}
		}
		stage('Publish artifact to Artifactory') {
			when {
				branch "master"
			}
			steps {
				script {
					def server = Artifactory.server "artifactory"
					def rtGradle = Artifactory.newGradleBuild()
					rtGradle.tool = "gradle-4.5"
					rtGradle.deployer repo:'gradle-dev', server: server
					rtGradle.resolver repo:'gradle-dev', server: server
					def buildInfo = rtGradle.run buildFile: 'build.gradle', tasks: 'artifactoryPublish'
					server.publishBuildInfo buildInfo
				}
			}
		}
		stage('Deploy artifact to Cloud Foundry') {
			when {
				branch "master"
			}
			environment {
				CF_USERNAME = credentials('CF_USERNAME')
				CF_PASSWORD = credentials('CF_PASSWORD')
			}
			steps {
				script {
					def artifactName = sh script: "gradle properties | grep name: | cut -d':' -f2 | tr -d '[:space:]'", returnStdout: true
					def projectVersion = sh script: "gradle properties | grep version: | cut -d':' -f2 | tr -d '[:space:]'", returnStdout: true
					sh "gradle cf-push -Pcf.host=${params.APP_NAME}-${params.CF_SPACE} -Pcf.ccHost=${params.CF_API} -Pcf.domain=${params.CF_DOMAIN} -Pcf.ccUser=${CF_USERNAME} -Pcf.ccPassword=${CF_PASSWORD} -Pcf.org=${params.CF_ORGANIZATION} -Pcf.space=${params.CF_SPACE} -PfilePath=${WORKSPACE}/build/libs/${artifactName}-${projectVersion}.jar"
				}
			}
		}
	}
        
}
