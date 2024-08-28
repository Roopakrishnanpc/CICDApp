pipeline {
    agent any
    stages {
        stage("sonar quality check") {
            agent {
                docker {
                    image 'openjdk:21'
                }
            }
            steps {
                script {
                    withSonarQubeEnv(credentialsId: 'sonarqbe-token') {
                        sh 'chmod +x mvnw'
                        sh './mvnw sonar:sonar'
                    }
                }
            }
        }
    }
}
