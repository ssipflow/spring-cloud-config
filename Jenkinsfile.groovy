#!groovy
node {
    def git
    def commitHash

    stage('Checkout') {
        git = checkout scm
        commitHash = git.GIT_COMMIT
    }

    stage('Test') {
        sh './gradlew test || true'
    }

    stage('Build') {
        try {
            sh './gradlew build -x test'
        } catch(e) {
            mail subject: "Jenkins Job '${env.JOB_NAME}' (${env.BUILD_NUMBER}) failed with ${e.message}",
                    to: 'codingman@outlook.kr',
                    body: "Please go to $env.BUILD_URL."
        }
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
