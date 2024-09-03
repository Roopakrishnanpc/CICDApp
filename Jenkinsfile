pipeline {
    agent any
    environment{
		VERSION = "${env.BUILD_ID}"
	}
    stages {
    stage("Sonar java install") {
		agent {
	        docker {
	            image 'openjdk:21'  // Use the appropriate Docker image
	            args '-u 0:0'  // Optional: Run as root if needed
	            // Optional: Add Docker options if necessary
	            // args '-v /root/.m2:/root/.m2' // Example for persisting Maven cache
	        }
        }
        steps {
              script {
                    
                    withSonarQubeEnv(credentialsId: 'sonarqbe-token') {
						sh 'java -version'
						sh 'chmod +x mvnw'
						//sh './mvnw clean install'
                        sh './mvnw sonar:sonar'
                    }
                                        timeout(time: 1, unit: 'HOURS')
                    {
						def a=waitForQualityGate()
						if(a.status!='OK')
						{
							error "Pipleline aborted due to quality  gate failure: ${a.status}"
						} 
					}
                }
        }
     }
//        stage('Build') {
//            steps {
//                script {
//                    // Ensure Maven wrapper has executable permissions and run the Maven build
//                    sh 'chmod +x mvnw'
//                    sh './mvnw clean install'
//                }
//            }
//        }

//        stage('SonarQube Analysis') {
//           steps {
//                script {
//                    // Run SonarQube analysis
//                    withSonarQubeEnv(credentialsId: 'sonarqbe-token') {
//						sh 'chmod +x mvnw'
//                        sh './mvnw sonar:sonar'
//                    }
//                                        timeout(time: 1, unit: 'HOURS')
//                    {
//						def a=waitForQualityGate()
//						if(a.status!='OK')
//						{
//							error "Pipleline aborted due to quality  gate failure: ${a.status}"
//						} 
//					}
//                }
//            }
//        } 
 
        stage('Print Environment Variables') {
            steps {
                script {
                    sh 'printenv'
                }
            }
        }

//        stage('Build and Test') {
//            steps {
//                script {
//                    sh 'chmod +x mvnw'
//                    sh './mvnw clean test -X'  // Run Maven with debug logging
//                }
//            }
//        }
        //docker pass nexus is the token created in nexus
        stage("docker build & docker push")
        {
			steps{
				script{
					withCredentials([string(credentialsId: 'docker-pass-nexus', variable: 'docker_password')]) {
					sh '''
						docker build -t 34.168.178.176:8083/springapp:${VERSION} .
						docker login -u admin -p ${docker_password} 34.168.178.176:8083
						docker push 34.168.178.176:8083/springapp:${VERSION}
						docker rmi 34.168.178.176:8083/springapp:${VERSION} 
					'''
					//springapp:latest can also be given and VERSION IS GIVEN ABOVE 
					}
				}
			}
		}
		stage("Identifying misconfig using datree in helm charts"){
			steps{
				script{
					dir('kubernetes/') {
                       withEnv(['DATREE_TOKEN=GJdx2cP2TCDyUY3EhQKgTc']) {
							 // sh 'helm datree config set offline local'
                              sh 'helm datree test CICDApp/'
                       }
                    }
				}
			}
		}
stage("pushing the helm charts to nexus"){
            steps{
                script{
                    withCredentials([string(credentialsId: 'docker-pass-nexus', variable: 'docker_password')]) {
                          dir('kubernetes/') {
                             sh '''
                                 helmversion=$( helm show chart CICDApp | grep version | cut -d: -f 2 | tr -d ' ')
                                 tar -czvf  CICDApp-${helmversion}.tgz CICDApp/
                                 curl -u admin:$docker_password http://34.168.178.176:8081/repository/helm-hosted/ --upload-file CICDApp-${helmversion}.tgz -v
                            '''
                          }
                    }
                }
            }
        }
stage('Deploying application on k8s cluster') {
            steps {
                script {
                    // Using withCredentials to bind the kubeconfig file
                    withCredentials([file(credentialsId: 'kubeconfig-file', variable: 'KUBECONFIG_FILE')]) {
                        dir('kubernetes/') {
                            // Export the KUBECONFIG variable and use helm to deploy
                            sh '''
                                export KUBECONFIG=$KUBECONFIG_FILE
                                helm upgrade --install --set image.repository="34.168.178.176:8083/springapp" --set image.tag="${VERSION}" myjavaapp CICDApp/
                            '''
                        }
                    }
                }
            }
        }

//        stage('Verifying app deployment') {
//            steps {
//                script {
//                    withCredentials([file(credentialsId: 'kubeconfig-file', variable: 'KUBECONFIG_FILE')]) {
//                        // Set the KUBECONFIG environment variable and run the kubectl command
//                        sh '''
//                            export KUBECONFIG=$KUBECONFIG_FILE
//                            kubectl run curl --image=curlimages/curl -i --rm --restart=Never -- curl http://CICDApp-CICDApp:5000
//                        '''
//                    }
//                }
//            }
//        }
//    }
   }
    
    post {
        always {
            echo 'Pipeline execution completed.'
            mail bcc: '', body: "<br>Project: ${env.JOB_NAME} <br>Build Number: ${env.BUILD_NUMBER} <br> URL de build: ${env.BUILD_URL}", cc: '', charset: 'UTF-8', from: '', mimeType: 'text/html', replyTo: '', subject: "${currentBuild.result} CI: Project name -> ${env.JOB_NAME}", to: "roopa.sri.kittukrishnan@gmail.com";  
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
