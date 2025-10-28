pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                echo ' Fetching frontend code from GitHub...'
                deleteDir()
                git branch: 'main', url: 'https://github.com/kaviya-sharon14/onlyfrontend.git'
            }
        }

        stage('Deploy Frontend') {
            steps {
                echo ' Copying frontend files to Jenkins userContent folder...'
                bat '''
                if not exist "C:\\ProgramData\\Jenkins\\.jenkins\\userContent" mkdir "C:\\ProgramData\\Jenkins\\.jenkins\\userContent"
                xcopy * "C:\\ProgramData\\Jenkins\\.jenkins\\userContent" /E /Y
                '''
            }
        }

        stage('Access Link') {
            steps {
                echo ' Your frontend is ready!'
                echo 'Open in browser: http://localhost:8080/userContent/index.html'
            }
        }
    }

    post {
        failure {
            echo ' Pipeline failed. Please check the logs.'
        }
    }
}
