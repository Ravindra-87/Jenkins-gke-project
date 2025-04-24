pipeline {
    agent any
    options {
        skipDefaultCheckout()
    }
    tools {
        maven 'maven-3.9.9' // Use the exact version form tool configuration
    }

    environment {
        GOOGLE_CREDENTIALS = credentials('gcp-service-account')   // Credential ID for the Google Service Account
        GITHUB_CREDENTIALS = credentials('github-access-id') // Credential ID for GitHub token
    }

    stages {
        stage('Clone Repository.........') {
            steps {
                git branch: 'main', credentialsId: 'github-access-id', url: 'https://github.com/Ravindra-87/Jenkins-gke-project.git'
            }
        }

        stage('Build with Maven.........') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    // Build Docker image using Dockerfile in the repository
                    sh 'docker build -t asia-east1-docker.pkg.dev/jenkins-gke-project-457719/gc-artifact-repo/jenkins-gke-project:latest .'
                }
            }
        }
//
//        stage('Push to Artifact Registry') {
//            steps {
//                script {
//                    // Log in to Artifact Registry (using the Google Cloud credentials)
//                    sh 'gcloud auth activate-service-account --key-file=${GOOGLE_CREDENTIALS}'
//                    sh 'gcloud auth configure-docker gcr.io --quiet'
//
//                    // Push the Docker image to Artifact Registry
//                    sh 'docker push gcr.io/your-project-id/your-image:latest'
//                }
//            }
//        }
//
//        stage('Deploy to GKE') {
//            steps {
//                script {
//                    // Set the Kubernetes credentials
//                    sh 'gcloud container clusters get-credentials your-cluster --zone your-zone --project your-project-id'
//
//                    // Deploy the Docker image to GKE (adjust kubectl and deployment YAML as needed)
//                    sh 'kubectl set image deployment/your-deployment your-container=gcr.io/your-project-id/your-image:latest'
//                }
//            }
//        }
    }
    post {
        always {
            echo 'Pipeline finished. Check logs above for success or failure........'
        }
    }
}
