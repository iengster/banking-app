pipeline {
    agent any
    environment {
        DOCKERHUB_CREDS = credentials('dockerhub-creds')
        IMAGE_NAME = "<your-dockerhub-username>/bankingapp"
        IMAGE_TAG  = "${BUILD_NUMBER}"
    }
    tools {
        maven 'Maven3'
        jdk 'JDK21'
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/iengster/banking-app.git'
            }
        }
        stage('Build with Maven') {
            steps { sh 'mvn clean package -DskipTests' }
        }
        stage('Test') {
            steps { sh 'mvn test' }
            post {
                always { junit 'target/surefire-reports/*.xml' }
            }
        }
        stage('Archive Artifact') {
            steps { archiveArtifacts artifacts: 'target/*.jar', fingerprint: true }
        }
        stage('Build Docker Image') {
            steps {
                sh "docker build -t ${IMAGE_NAME}:${IMAGE_TAG} ."
                sh "docker tag ${IMAGE_NAME}:${IMAGE_TAG} ${IMAGE_NAME}:latest"
            }
        }
        stage('Push to Docker Hub') {
            steps {
                sh "echo ${DOCKERHUB_CREDS_PSW} | docker login -u ${DOCKERHUB_CREDS_USR} --password-stdin"
                sh "docker push ${IMAGE_NAME}:${IMAGE_TAG}"
                sh "docker push ${IMAGE_NAME}:latest"
            }
        }
        stage('Deploy via Ansible') {
            steps {
                sh """
                ansible-playbook -i /home/ubuntu/ansible/inventory.ini \
                  ansible/deploy.yml \
                  --extra-vars "dockerhub_user=<your-dockerhub-username> image_tag=${IMAGE_TAG}"
                """
            }
        }
        stage('Verify Deployment') {
            steps {
                sh 'sleep 15'
                sh 'curl -f http://172.31.20.121:8989/bank-api/swagger-ui.html || exit 1'
            }
        }
    }
    post {
        success { echo "✅ Deployment successful!" }
        failure { echo "❌ Pipeline failed. Check logs." }
    }
}
