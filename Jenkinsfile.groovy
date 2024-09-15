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
                echo 'Running unit test with JUnit'
                echo 'Running integration test using TestNG'
            }
            post {
                success {
                    script {
                        if (fileExists('unit_test_results.log')) {
                            emailext subject: "Unit and Integration Testing - Success",
                                     body: "The Unit and Integration Testing stage has passed.",
                                     to: 'vinoj.prasath23@gmail.com',
                                     attachmentsPattern: 'unit_test_results.log',
                                     attachLog: true
                        } else {
                            echo "unit_test_results.log not found!"
                        }
                    }
                }
                failure {
                    script {
                        if (fileExists('unit_test_results.log')) {
                            emailext subject: "Unit and Integration Testing - Failed",
                                     body: "The Unit and Integration Testing stage has failed.",
                                     to: 'vinoj.prasath23@gmail.com',
                                     attachmentsPattern: 'unit_test_results.log',
                                     attachLog: true
                        } else {
                            echo "unit_test_results.log not found!"
                        }
                    }
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
                echo 'Performing Security Scan using OWASP ZAP'
            }
            post {
                success {
                    script {
                        if (fileExists('security_scan.log')) {
                            emailext subject: "Security Scan - Success",
                                     body: "The Security Scan stage has passed.",
                                     to: 'vinoj.prasath23@gmail.com',
                                     attachmentsPattern: 'security_scan.log',
                                     attachLog: true
                        } else {
                            echo "security_scan.log not found!"
                        }
                    }
                }
                failure {
                    script {
                        if (fileExists('security_scan.log')) {
                            emailext subject: "Security Scan - Failed",
                                     body: "The Security Scan stage has failed.",
                                     to: 'vinoj.prasath23@gmail.com',
                                     attachmentsPattern: 'security_scan.log',
                                     attachLog: true
                        } else {
                            echo "security_scan.log not found!"
                        }
                    }
                }
            }
        }
        stage('Deploy to Staging') {
            steps {
                echo 'Deploying to the staging server on AWS EC2'
            }
        }
        stage('Integration Test on Staging') {
            steps {
                echo 'Running integration tests on Staging'
            }
        }
        stage('Deploy to Production') {
            steps {
                echo 'Deploying to production using AWS EC2'
            }
        }
    }
    post {
        success {
            emailext subject: "Pipeline Success",
                     body: "All stages ran successfully.",
                     to: 'vinoj.prasath23@gmail.com',
                     attachLog: true
        }
        failure {
            emailext subject: "Pipeline Failed",
                     body: "One or more stages have failed. Please check the Jenkins logs.",
                     to: 'vinoj.prasath23@gmail.com',
                     attachLog: true
        }
    }
}
