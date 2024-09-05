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
                        to: 's223839258@deakin.edu.au',
                        subject: "Test Email Notification - ${currentBuild.currentResult}",
                        body: "This is a test email. The build has ${currentBuild.currentResult}.",
                        attachLog: true
                    )
                }
            }
        }