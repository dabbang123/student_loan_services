pipeline {
    agent any

    environment {
        JAVA_OPTS = "-Dorg.jenkinsci.plugins.durabletask.BourneShellScript.HEARTBEAT_CHECK_INTERVAL=86400"
    }

    stages {
        stage('Checkout Source') {
            steps {
                git url: 'https://github.com/dabbang123/student_loan_services.git', branch: 'main'
            }
        }

        stage('Build with Maven') {
            steps {
                dir('SD_StudentLoanServices') {
                    sh '''
                        echo "🚀 Starting Maven Build"
                        mvn clean package -X -e -DskipTests
                    '''
                }
            }
        }

        stage('Build Docker Image') {
            when {
                expression { currentBuild.result == null || currentBuild.result == 'SUCCESS' }
            }
            steps {
                echo "✅ Docker build stage goes here"
                // Add your docker build commands if needed
            }
        }

        stage('Run Trivy Scans') {
            when {
                expression { currentBuild.result == null || currentBuild.result == 'SUCCESS' }
            }
            steps {
                echo "🛡️ Trivy scan stage goes here"
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
