pipeline {
    agent { docker { image 'gradle:4.8.1' } }
    stages {
        stage('build') {
            steps {
                sh 'mvn --version'
            }
        }
    }
}