node {
    def server = Artifactory.server "artifactory-local"
    def rtGradle = Artifactory.newGradleBuild()

    stage 'Clone'
        git url: 'https://github.com/pacphi/track-shipments.git'

    stage 'Prepare'
        rtGradle.tool = "Gradle-4.4.1"
        rtGradle.deployer repo:'gradle-dev', server: server
        rtGradle.resolver repo:'gradle-dev', server: server

    stage 'Build'
        def buildInfo = rtGradle.run buildFile: 'build.gradle', tasks: 'clean artifactoryPublish'

    stage 'Publish artifact'
        server.publishBuildInfo buildInfo
}