#!groovy
node {
    def git
    def commitHash

    stage('Checkout') {
        git = checkout scm
        commitHash = git.GIT_COMMIT
    }

    stage('Test') {
        sh 'gradle test || true'
    }

    stage('Build') {
        sh 'gradle build -x test'

    }

    stage('Build Docker Image') {
        def image = docker.build("spring-cloud-config", "./")
        }

//        stage('Archive') {
//            parallel (
//
//                    "Archive Artifacts" : {
//                        archiveArtifacts artifacts: '**/build/libs/*.jar', fingerprint: true
//                    },
//
//                    "Docker Image Push" : {
//                        sh './gradlew dockerPush'
//                    }
//
//            )
//        }



}
