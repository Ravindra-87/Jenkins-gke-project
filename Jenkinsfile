pipeline {
    agent any

    environment {
        GOOGLE_CREDENTIALS = credentials('google-cloud-jenkins')  // Credential ID for the Google Service Account
        GITHUB_CREDENTIALS = credentials('github-token')         // Credential ID for GitHub token
    }

    stages {
        stage('Clone Repository') {
            steps {
                git credentialsId: 'github-token', url: 'https://github.com/your-org/your-repo.git'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    // Build Docker image using Dockerfile in the repository
                    sh 'docker build -t gcr.io/your-project-id/your-image:latest .'
                }
            }
        }

        stage('Push to Artifact Registry') {
            steps {
                script {
                    // Log in to Artifact Registry (using the Google Cloud credentials)
                    sh 'gcloud auth activate-service-account --key-file=${GOOGLE_CREDENTIALS}'
                    sh 'gcloud auth configure-docker gcr.io --quiet'

                    // Push the Docker image to Artifact Registry
                    sh 'docker push gcr.io/your-project-id/your-image:latest'
                }
            }
        }

        stage('Deploy to GKE') {
            steps {
                script {
                    // Set the Kubernetes credentials
                    sh 'gcloud container clusters get-credentials your-cluster --zone your-zone --project your-project-id'

                    // Deploy the Docker image to GKE (adjust kubectl and deployment YAML as needed)
                    sh 'kubectl set image deployment/your-deployment your-container=gcr.io/your-project-id/your-image:latest'
                }
            }
        }
    }
}