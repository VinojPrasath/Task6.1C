pipeline {
    agent any

    stages {
        stage('Checkout SCM') {
            steps {
                bat 'git --version' // Ensure Git is available
                checkout scm
            }
        }
        
        stage('Build') {
            steps {
                script {
                    try {
                        // Example build command for Windows
                        bat 'echo Building the project > build.log'
                        bat 'echo Build successful >> build.log'
                    } catch (Exception e) {
                        echo "Build failed: ${e.getMessage()}"
                        bat 'echo Build failed: ${e.getMessage()} >> build.log'
                        currentBuild.result = 'FAILURE'
                        error("Build failed")
                    }
                }
            }
        }
        
        stage('Unit and Integration Tests') {
            when {
                expression { currentBuild.result == null || currentBuild.result == 'SUCCESS' }
            }
            steps {
                script {
                    try {
                        // Simulate running tests
                        bat 'echo Running unit and integration tests >> build.log'
                    } catch (Exception e) {
                        echo "Tests failed: ${e.getMessage()}"
                        bat 'echo Tests failed: ${e.getMessage()} >> build.log'
                        currentBuild.result = 'FAILURE'
                        error("Tests failed")
                    }
                }
            }
        }
        
        stage('Code Analysis') {
            when {
                expression { currentBuild.result == null || currentBuild.result == 'SUCCESS' }
            }
            steps {
                script {
                    try {
                        // Simulate code analysis
                        bat 'echo Running code analysis >> build.log'
                    } catch (Exception e) {
                        echo "Code analysis failed: ${e.getMessage()}"
                        bat 'echo Code analysis failed: ${e.getMessage()} >> build.log'
                        currentBuild.result = 'FAILURE'
                        error("Code analysis failed")
                    }
                }
            }
        }
        
        stage('Security Scan') {
            when {
                expression { currentBuild.result == null || currentBuild.result == 'SUCCESS' }
            }
            steps {
                script {
                    try {
                        // Simulate security scan
                        bat 'echo Running security scan >> build.log'
                    } catch (Exception e) {
                        echo "Security scan failed: ${e.getMessage()}"
                        bat 'echo Security scan failed: ${e.getMessage()} >> build.log'
                        currentBuild.result = 'FAILURE'
                        error("Security scan failed")
                    }
                }
            }
        }
        
        stage('Deploy to Staging') {
            when {
                expression { currentBuild.result == null || currentBuild.result == 'SUCCESS' }
            }
            steps {
                script {
                    try {
                        // Simulate deployment to staging
                        bat 'echo Deploying to staging >> build.log'
                    } catch (Exception e) {
                        echo "Staging deployment failed: ${e.getMessage()}"
                        bat 'echo Staging deployment failed: ${e.getMessage()} >> build.log'
                        currentBuild.result = 'FAILURE'
                        error("Staging deployment failed")
                    }
                }
            }
        }
        
        stage('Integration Tests on Staging') {
            when {
                expression { currentBuild.result == null || currentBuild.result == 'SUCCESS' }
            }
            steps {
                script {
                    try {
                        // Simulate integration tests on staging
                        bat 'echo Running integration tests on staging >> build.log'
                    } catch (Exception e) {
                        echo "Integration tests on staging failed: ${e.getMessage()}"
                        bat 'echo Integration tests on staging failed: ${e.getMessage()} >> build.log'
                        currentBuild.result = 'FAILURE'
                        error("Integration tests on staging failed")
                    }
                }
            }
        }
        
        stage('Deploy to Production') {
            when {
                expression { currentBuild.result == null || currentBuild.result == 'SUCCESS' }
            }
            steps {
                script {
                    try {
                        // Simulate deployment to production
                        bat 'echo Deploying to production >> build.log'
                    } catch (Exception e) {
                        echo "Production deployment failed: ${e.getMessage()}"
                        bat 'echo Production deployment failed: ${e.getMessage()} >> build.log'
                        currentBuild.result = 'FAILURE'
                        error("Production deployment failed")
                    }
                }
            }
        }
    }

    post {
        always {
            script {
                // Collect and archive logs
                archiveArtifacts artifacts: 'build.log', allowEmptyArchive: true
            }
        }
        success {
            emailext(
                subject: 'Build Successful',
                body: '''<p>Build completed successfully.</p>
                         <p>See attached logs for more details.</p>''',
                to: 'vinoj.prasath23@gmail.com',
                attachmentsPattern: 'build.log'
            )
        }
        failure {
            emailext(
                subject: 'Build Failed',
                body: '''<p>Build failed. Please check the logs.</p>
                         <p>See attached logs for more details.</p>''',
                to: 'vinoj.prasath23@gmail.com',
                attachmentsPattern: 'build.log'
            )
        }
    }
}
