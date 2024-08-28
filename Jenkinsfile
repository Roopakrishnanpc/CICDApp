pipeline {
    agent {
        docker {
            // Replace with the appropriate Docker image that includes JDK 17
            image 'openjdk-17'
            // Optional: If you need to set specific Docker options
            // args '-v /root/.m2:/root/.m2' // Example for persisting Maven cache
        }
    }
    stages {
        stage('Build') {
            steps {
                script {
                    // Ensure Maven wrapper has executable permissions and run the Maven build
                    sh 'chmod +x mvnw'
                    sh './mvnw clean install'
                }
            }
        }
        stage('SonarQube Analysis') {
            steps {
                script {
                    // Run SonarQube analysis
                    withSonarQubeEnv(credentialsId: 'sonarqbe-token') {
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
        success {
            echo 'Pipeline executed successfully.'
        }
        failure {
            echo 'Pipeline execution failed.'
        }
    }
}


/*
pipeline {
    agent any
    stages {
        stage('Build') {
            agent {
                docker {
                    image 'maven:3.8.5-openjdk-21' // Ensure the Docker image has Maven and JDK
                }
            }
            steps {
                script {
                    sh 'mvn clean install' // This builds the project
                }
            }
        }
        stage('Sonar Quality Check') {
            agent {
                docker {
                    image 'maven:3.8.5-openjdk-21' // Ensure the Docker image has Maven and JDK
                }
            }
            steps {
                script {
                    withSonarQubeEnv(credentialsId: 'sonarqbe-token') {
                        sh 'mvn sonar:sonar' // This runs the SonarQube analysis
                    }
                }
            }
        }
    }
    post {
        always {
            echo "Pipeline executed always"
        }
    }
}

*/