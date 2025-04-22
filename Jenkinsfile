pipeline {
    agent any  // This specifies the pipeline can run on any available agent

     environment {
            GOOGLE_CREDENTIALS = credentials('gs-account-id') // Jenkins credentials for the service account JSON key
            PROJECT_ID = 'jenkins-tf-pro-2025'
            IMAGE_NAME = "asia-east1-docker.pkg.dev/jenkins-tf-pro-2025/my-docker-repo/try-first-project" // Update with your Artifact Registry path
            IMAGE_TAG = "latest" // Tag for the image, you can use branch name or commit hash if needed
        }

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

         stage('Build and Push Docker Image') {
                    steps {
                        script {
                            def dockerImageTag = "${IMAGE_NAME}:latest" // or your desired tag
                            docker.build(dockerImageTag, "-f Dockerfile .")
                            docker.withRegistry('https://asia-east1-docker.pkg.dev', 'github-access-id') {
                                docker.image(dockerImageTag).push()
                            }
                        }
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