pipeline {
    agent any  // This specifies the pipeline can run on any available agent

    stages {
        stage('Checkout') {
            steps {
                // Pull the code from GitHub
                echo 'Cloning the repository...'
                git  branch: 'main', credentialsId: 'github-access-id', url: 'https://github.com/Ravindra-87/Try-FirsT-Project.git'
            }
        }

        stage('Build') {
            steps {
                // Example build steps (could be Maven, Gradle, etc.)
                echo 'Building the project...'

                // Assuming it's a Maven project
                sh '''
                    echo "Building with Maven..."
                    mvn clean install -DskipTests=true
                '''
            }
        }
    }

    post {
        success {
            // This block will run if the build succeeds
            echo 'Build and deploy successful! The application is now deployed.'
        }
        failure {
            // This block will run if the build fails
            echo 'Build or deployment failed. Please check the logs.'
        }
        always {
            // This block will always run after the pipeline finishes
            echo 'Pipeline execution complete.'
        }
    }
}