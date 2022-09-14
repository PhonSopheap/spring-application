pipeline {
    agent any
    environment{
        DOCKER_TAG = "${BUILD_NUMBER}"
    }
    tools {
        maven 'maven3.8.6'
    }
    stages{
        stage ('Build spring') {
            steps {
                echo 'building'
                sh "mvn clean install -Dmaven.test.skip=true"
            }
        }
        stage('Build Docker Image'){
            steps{
                sh "docker build . -t s0pheap/demo-jenkins:${DOCKER_TAG}"
            }
        }
        stage('DockerHub Push'){
            steps{
                withCredentials([usernamePassword(credentialsId: 'AmSopheap', usernameVariable: 'dockerUser', passwordVariable: 'dockerHubPwd')]) {
			sh 'docker login -u ${dockerUser} -p ${dockerHubPwd}'
                    sh 'docker push s0pheap/demo-jenkins:${DOCKER_TAG}'
                }
            }
        }
        stage("Deploy to K8s"){
            steps{
                sshagent(['ssh-key']){
					sh 'scp -r -o StrictHostKeyChecking=no deployment.yaml deploy.sh s0pheap@172.104.44.15:/home/s0pheap/jenkins'
					script{
						try{
							sh 'ssh s0pheap@172.104.44.15 chmod +x /home/s0pheap/jenkins/deploy.sh'
							sh "ssh s0pheap@172.104.44.15 /home/s0pheap/jenkins/deploy.sh ${DOCKER_TAG}"
						}catch(error){
                            echo error
						}
					}
				}
            }
        }
    }
}
