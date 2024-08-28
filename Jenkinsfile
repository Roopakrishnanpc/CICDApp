pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                script {
                    // Run Maven build
                    sh 'chmod +x mvnw'
                    sh './mvnw clean install'
                }
            }
        }
        stage('SonarQube Analysis') {
            steps {
                script {
                    withSonarQubeEnv(credentialsId: 'sonarqbe-token') {
                        // Run SonarQube analysis
                        sh './mvnw sonar:sonar'
                    }
                }
            }
        }
    }
    post {
        always {
            echo 'Pipeline execution completed.'
        }
    }
}
