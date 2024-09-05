pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building project using Maven'
            }
        }
        stage('Unit and Integration Testing') {
            steps {
                echo 'Running unit test with Junit'
                echo 'Running integration test using TestNG'
            }
        }
        stage('Code Analysis') {
            steps {
                echo 'Running static code analysis using SonarQube'
            }
        }
        stage('Security Scan') {
            steps {
                echo 'Perform Security Scan using OWASP ZAP'
            }
        }
        stage('Deploy to Staging') {
            steps {
                echo 'Deploying using the staging server AWS E2C'
            }
        }
        stage('Integration Test on Staging') {
            steps {
                echo 'Running integration test on Staging'
            }
        }
        stage('Deploy to Production') {
            steps {
                echo 'Deploy to productionusing AWS E2C'
            }
        }
    }
   post {
        always {
            emailext(
                to: 'vinoj.prasath23@gmail.com',
                subject: "Build ${currentBuild.currentResult}: ${env.JOB_NAME} - ${env.BUILD_NUMBER}",
                body: "The build ${env.BUILD_NUMBER} for job ${env.JOB_NAME} has ${currentBuild.currentResult}. \n\nCheck the details here: ${env.BUILD_URL} \n\nPlease find the attached build log.",
                attachLog: true
            )
        }
    }
}