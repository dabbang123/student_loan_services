pipeline {
    agent any

    environment {
        IMAGE_NAME = "student-loan-app"
        DOCKER_TAG = "latest"
    }

    stages {
        stage('Build with Maven') {
            steps {
                dir('SD_StudentLoanServices') {
                    sh 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t $IMAGE_NAME:$DOCKER_TAG -f Dockerfile SD_StudentLoanServices'
            }
        }

        stage('Run Trivy Scans') {
            steps {
                sh 'chmod +x scripts/run_trivy.sh'
                sh './scripts/run_trivy.sh SD_StudentLoanServices'
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
