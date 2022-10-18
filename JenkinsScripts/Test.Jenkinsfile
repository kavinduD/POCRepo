#!groovy

pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building..'   
                lock(label: 'katalon', quantity: 1, resource: null) {
                
                } 
            }
        }
        stage('Test1') {
            steps {
                echo 'Testing..'
                executeKatalon version: "8.0.0", executeArgs: "-retry=0 -projectPath=\"${env.WORKSPACE}/IFS_FSM_MobileAutomation/IFS_FSM_MobileAutomation.prj\" -testSuitePath=\"Test Suites/Android/Debrief/Debrief_Contacts/TS_Debrief_Contacts\" -executionProfile=\"default\"  -browserType=\"galaxy_s20_5g\" -apiKey=\"8aeba134-53fb-45fd-9209-32e79a9b4039\" -orgID=45619 -licenseRelease=true", location: "", x11Display: "", xvfbConfiguration: ""
            }
        }
        stage('Test2') {
            steps {
                echo 'Testing..'
                executeKatalon version: "8.0.0", executeArgs: "-retry=0 -projectPath=\"${env.WORKSPACE}/IFS_FSM_MobileAutomation/IFS_FSM_MobileAutomation.prj\" -testSuitePath=\"Test Suites/Android/Debrief/Debrief_Contacts/TS_Debrief_Contacts\" -executionProfile=\"default\"  -browserType=\"oneplus_6\" -apiKey=\"8aeba134-53fb-45fd-9209-32e79a9b4039\" -orgID=45619 -licenseRelease=true", location: "", x11Display: "", xvfbConfiguration: ""
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
                //echo "ZIP"
                //sh 'zip myfile.zip ${env.WORKSPACE}/IFS_FSM_MobileAutomation/Reports'
                //echo "END - ZIP"
            }
        }
    }
    post {
        always {
            junit '${env.WORKSPACE}/IFS_FSM_MobileAutomation/Reports/*/Debrief/Debrief_Contacts/TS_Debrief_Contacts/*/JUnit_Report.xml'
        }
    }
}