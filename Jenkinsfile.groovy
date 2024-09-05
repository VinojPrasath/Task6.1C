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
            post {
                success {
                    script {
                        archiveArtifacts artifacts: 'unit_test_results.log'
                    }
                    mail to: 'vinoj.prasath23@gmail.com',
                    subject: "Unit and Integration Testing - Success",
                    body: "The Unit and Integration Testing stage has passed.",
                    attachmentsPattern: 'unit_test_results.log'
                }
                failure {
                    script {
                        archiveArtifacts artifacts: 'unit_test_results.log'
                    }
                    mail to: 'vinoj.prasath23@gmail.com',
                    subject: "Unit and Integration Testing - Failed",
                    body: "The Unit and Integration Testing stage has failed.",
                    attachmentsPattern: 'unit_test_results.log'
                }
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
           post {
                success {
                    script {
                        archiveArtifacts artifacts: 'security_scan.log'
                    }
                    mail to: 'vinoj.prasath23@gmail.com',
                    subject: "Security Scan - Success",
                    body: "The Security Scan stage has passed.",
                    attachmentsPattern: 'security_scan.log'
                }
                failure {
                    script {
                        archiveArtifacts artifacts: 'security_scan.log'
                    }
                    mail to: 'vinoj.prasath23@gmail.com',
                    subject: "Security Scan - Failed",
                    body: "The Security Scan stage has failed.",
                    attachmentsPattern: 'security_scan.log'
                }
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
            mail to: 'vinoj.prasath23@gmail.com',
            subject: "Pipeline Success",
            body:"All stages are running sucessfully"
        }
    }
}