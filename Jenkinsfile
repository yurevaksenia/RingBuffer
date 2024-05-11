pipeline {
    agent any

    stages {
        stage("Compile code") {
            steps {
                bat 'mvn clean compile'
            }
        }
        stage("Tests") {
            when {
                branch 'feature/*'
            }
            steps {
                bat 'mvn test'
            }
        }
        stage("Static analyse") {
            when {
                branch 'develop'
            }
            steps {
                bat 'mvn checkstyle:check'
            }
        }
    }
}