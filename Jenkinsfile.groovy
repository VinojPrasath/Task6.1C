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
                    archiveArtifacts artifacts: 'unit_test_results.log'
                    emailext subject: "Unit and Integration Testing - Success",
                             body: "The Unit and Integration Testing stage has passed. \nCheck the results: ${http://localhost:8080/job/Task6.1C_Pipeline/25/
}artifact/unit_test_results.log",
                             to: 'vinoj.prasath23@gmail.com',
                             attachmentsPattern: 'unit_test_results.log',
                             attachLog: true
                }
                failure {
                    archiveArtifacts artifacts: 'unit_test_results.log'
                    emailext subject: "Unit and Integration Testing - Failed",
                             body: "The Unit and Integration Testing stage has failed. \nCheck the results: ${http://localhost:8080/job/Task6.1C_Pipeline/25/
}artifact/unit_test_results.log",
                             to: 'vinoj.prasath23@gmail.com',
                             attachmentsPattern: 'unit_test_results.log',
                             attachLog: true
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
                    archiveArtifacts artifacts: 'security_scan.log'
                    emailext subject: "Security Scan - Success",
                             body: "The Security Scan stage has passed. \nCheck the results: ${http://localhost:8080/job/Task6.1C_Pipeline/25/
}artifact/security_scan.log",
                             to: 'vinoj.prasath23@gmail.com',
                             attachmentsPattern: 'security_scan.log',
                             attachLog: true
                }
                failure {
                    archiveArtifacts artifacts: 'security_scan.log'
                    emailext subject: "Security Scan - Failed",
                             body: "The Security Scan stage has failed. \nCheck the results: ${http://localhost:8080/job/Task6.1C_Pipeline/25/
}artifact/security_scan.log",
                             to: 'vinoj.prasath23@gmail.com',
                             attachmentsPattern: 'security_scan.log',
                             attachLog: true
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
        always {
            emailext subject: "Pipeline Completion Notification",
                     body: "All stages have been executed successfully. \nCheck the full build log: ${http://localhost:8080/job/Task6.1C_Pipeline/25/
}console",
                     to: 'vinoj.prasath23@gmail.com',
                     attachLog: true
        }
    }
}
