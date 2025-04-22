pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Pull the code from GitHub
                git credentialsId: 'github-access-id', url: 'https://github.com/Ravindra-87/Try-FirsT-Project.git'
            }
        }

        stage('Build') {
            steps {
                // Your deployment steps go here (optional)
                              echo 'building...'
            }
        }

        stage('Deploy') {
            steps {
                // Your deployment steps go here (optional)
                echo 'Deploying...'
            }
        }
    }

}