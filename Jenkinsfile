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
        DOCKER_CLI_EXPERIMENTAL = 'enabled'
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
        stage('Install Docker Buildx') {
            steps {
                script {
                    // Create the directory for Docker CLI plugins
                    sh 'mkdir -vp ~/.docker/cli-plugins/'

                    // Download Buildx binary
                    sh 'curl --silent -L "https://github.com/docker/buildx/releases/download/v0.3.0/buildx-v0.3.0.linux-amd64" > ~/.docker/cli-plugins/docker-buildx'

                    // Make the binary executable
                    sh 'chmod a+x ~/.docker/cli-plugins/docker-buildx'

                    // Verify Buildx installation
                    sh 'docker buildx version || echo "Docker Buildx installation failed"'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    sh '''
                             
                        export DOCKER_CONFIG=/tmp/docker-empty-config
                        mkdir -p $DOCKER_CONFIG
                        echo '{}' > $DOCKER_CONFIG/config.json
                        
                        #Build Docker image using Dockerfile in the repository    
                        export DOCKER_CLI_EXPERIMENTAL=enabled                      
                        docker buildx build --platform linux/amd64,linux/arm64  -t asia-east1-docker.pkg.dev/jenkins-gke-project-457719/gc-artifact-repo/jenkins-gke-project:latest .                             

                    '''
                }
            }
        }

        stage('Push to Artifact Registry') {
            steps {
                script {
                    // Log in to Artifact Registry (using the Google Cloud credentials)
                    sh 'gcloud auth activate-service-account --key-file=$GOOGLE_CREDENTIALS'
                    sh 'gcloud auth configure-docker asia-east1-docker.pkg.dev --quiet'

                    // Push the Docker image to Artifact Registry
                    sh 'docker push  asia-east1-docker.pkg.dev/jenkins-gke-project-457719/gc-artifact-repo/jenkins-gke-project:latest'

                }
            }
        }

        // Stage 4: Deploy to GKE using kubectl
        stage('Deploy to GKE') {
            steps {
                script {
                    // Ensure kubectl is configured to use the correct GKE context
                    sh """
                        kubectl config set-context --current --namespace=$KSA_NAMESPACE
                    
                      # Apply all Kubernetes files in the project root directory
                        kubectl apply -f ./kubernetes/deployment.yaml
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

