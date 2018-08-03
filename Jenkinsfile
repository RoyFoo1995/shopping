pipeline {
    agent { docker { image 'gradle:4.8.1' } }
    stages {
        stage('build') {
            steps {
                echo('test success')
            }
        }
        stage('QA') {
            steps {
                echo('QA success')
            }
        }
    }
}