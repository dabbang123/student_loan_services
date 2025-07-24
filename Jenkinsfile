pipeline {
    agent any  // Run on any available Jenkins agent

    environment {
        IMAGE_NAME = "student-loan-app"
        DOCKER_TAG = "latest"
    }

    stages {
        stage('Build with Maven') {
			steps {
				sh 'mvn clean package -DskipTests'
			}
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t $IMAGE_NAME:$DOCKER_TAG .'
            }
        }

        stage('Run Trivy Scans') {
            steps {
                sh 'chmod +x scripts/run_trivy.sh'
                sh './scripts/run_trivy.sh'
            }
        }

        stage('Filter Vulnerabilities') {
            steps {
                sh 'chmod +x scripts/filter_trivy.sh'
                sh './scripts/filter_trivy.sh'
            }
        }

        stage('Archive Reports') {
            steps {
                archiveArtifacts artifacts: '**/*.json', allowEmptyArchive: true
            }
        }
    }

    post {
        failure {
            echo '❌ Build failed due to security issues.'
        }
        success {
            echo '✅ Build passed and is secure.'
        }
    }
}
