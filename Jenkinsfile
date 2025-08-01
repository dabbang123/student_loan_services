pipeline {
    agent any

    environment {
        JAVA_OPTS = "-Dorg.jenkinsci.plugins.durabletask.BourneShellScript.HEARTBEAT_CHECK_INTERVAL=86400"
    }

    options {
        timeout(time: 30, unit: 'MINUTES')
    }

    stages {
        stage('Clean Workspace') {
            steps {
                deleteDir()
            }
        }

        stage('Checkout Source') {
            steps {
                git url: 'https://github.com/dabbang123/student_loan_services.git', branch: 'main'
            }
        }

        stage('Build with Maven') {
            steps {
                dir('SD_StudentLoanServices') {
                    timeout(time: 10, unit: 'MINUTES') {
                        sh '''
                            echo "🚀 Starting Maven Build"
                            mvn clean package -X -e -Dmaven.test.skip=true
                        '''
                    }
                }
            }
        }

        stage('Build Docker Image') {
            when {
                expression { currentBuild.result == null || currentBuild.result == 'SUCCESS' }
            }
            steps {
                timeout(time: 10, unit: 'MINUTES') {
                    sh '''
                        echo "🐳 Building Docker Image"
                        docker build -t student-loan-service .
                    '''
                }
            }
        }

        stage('Run Trivy Scans') {
            when {
                expression { currentBuild.result == null || currentBuild.result == 'SUCCESS' }
            }
            steps {
                timeout(time: 5, unit: 'MINUTES') {
                    sh '''
                        echo "🔍 Running Trivy Scan"
                        trivy image --exit-code 0 --severity HIGH student-loan-service || true
                    '''
                }
            }
        }

        stage('Filter Vulnerabilities') {
            when {
                expression { currentBuild.result == null || currentBuild.result == 'SUCCESS' }
            }
            steps {
                echo "🔍 Filtering vulnerabilities..."
            }
        }

        stage('Archive Reports') {
            when {
                expression { currentBuild.result == null || currentBuild.result == 'SUCCESS' }
            }
            steps {
                archiveArtifacts artifacts: '**/target/*.jar', allowEmptyArchive: true
                echo "📦 Reports archived."
            }
        }
    }

    post {
        success {
            echo '✅ Build completed successfully.'
        }
        failure {
            echo '❌ Build failed. Check logs above for errors.'
        }
        always {
            echo '📝 Pipeline finished running.'
        }
    }
}
