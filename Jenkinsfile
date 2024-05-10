pipeline {
    agent any

    stages {
        stage("Compile code") {
            steps {
                bat 'mvn clean compile'
            }
        }
    }
}