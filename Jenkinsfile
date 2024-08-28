pipeline {
    agent {
        docker {
            image 'openjdk:17'  // Use the appropriate Docker image
            args '-u 0:0'  // Optional: Run as root if needed
            // Optional: Add Docker options if necessary
            // args '-v /root/.m2:/root/.m2' // Example for persisting Maven cache
        }
    }
    
    stages {
        stage('Check Java Version') {
            steps {
                script {
                    sh 'java -version'
                }
            }
        }
        
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

        stage('Build and Test') {
            steps {
                sh './mvnw clean test'
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