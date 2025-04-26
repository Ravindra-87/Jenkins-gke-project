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
        GOOGLE_PROJECT_ID = 'jenkins-gke-project-457719'
        GOOGLE_CLUSTER_NAME = 'dev-cluster'
        GOOGLE_CLUSTER_ZONE = 'us-central1-a'
        IMAGE_NAME = 'jenkins-gke-project'
        IMAGE_TAG = 'latest'
        IMAGE_URL = 'asia-east1-docker.pkg.dev/jenkins-gke-project-457719/gc-artifact-repo/${IMAGE_NAME}:${IMAGE_TAG}'
        GSA_EMAIL = 'jenkins-gsa@jenkins-gke-project-457719.iam.gserviceaccount.com'
        KSA_NAME = 'ksa'
        KSA_NAMESPACE = 'pro-dev'
        DOCKER_BUILDKIT = '1'
        DOCKER_CLI_EXPERIMENTAL = 'enabled'
        BUILD_NUMBER_TAG = "${BUILD_NUMBER}"
    }

    stages {
        stage('Clone Repository') {
            steps {
                git branch: 'main', credentialsId: 'github-access-id', url: 'https://github.com/Ravindra-87/Jenkins-gke-project.git'
            }
        }

        stage('Build with Maven') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                   // Create and use a builder if not already
                    sh 'docker buildx create --name jenkinsbuilders --use || echo "Builder already exists"'
                    sh 'gcloud auth activate-service-account --key-file=$GOOGLE_CREDENTIALS'
                    sh 'gcloud auth configure-docker asia-east1-docker.pkg.dev --quiet'
                   // Build the image for amd64
                    sh 'docker buildx build --platform linux/amd64 -t asia-east1-docker.pkg.dev/jenkins-gke-project-457719/gc-artifact-repo/jenkins-gke-project:${BUILD_NUMBER} --push .'

            }
        }

        //  Deploy to GKE using kubectl
        stage('Deploy to GKE') {
            steps {
                script {
                    // Ensure kubectl is configured to use the correct GKE context
                    sh """
                        kubectl config set-context --current --namespace=$KSA_NAMESPACE
                                    
                        #kubectl apply -f ./kubernetes/deployment.yaml
                
                        sed -i "s/tag_version/${BUILD_NUMBER_KEY}/" ./kubernetes/deployment.yaml                
                        kubectl apply -f ./kubernetes/service.yaml
                        kubectl apply -f ./kubernetes/secret.yaml
                    """
                }
            }
        }
    }
        post {
            always {
                echo 'Pipeline finished. Check logs above for success or failure........'
            }
        }
    }

