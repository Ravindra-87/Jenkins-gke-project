pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Pull the code from GitHub
                git credentialsId: 'github-pat', url: 'https://github.com/Ravindra-87/Try-FirsT-Project.git'
            }
        }

        stage('Build') {
            steps {
                // Use Maven to build the Spring Boot app
                script {
                    sh './mvnw clean package -DskipTests'
                }
            }
        }

        stage('Test') {
            steps {
                // Run unit tests
                script {
                    sh './mvnw test'
                }
            }
        }

        stage('Deploy') {
            steps {
                // Your deployment steps go here (optional)
                echo 'Deploying...'
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}