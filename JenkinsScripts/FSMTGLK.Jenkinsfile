pipeline {
    agent any
    stages {
        stage('Setup') {
            steps {
                script {
                    util = load("${env.WORKSPACE}/IFS_FSM_MobileAutomation/JenkinsScripts/FSMAutomationPipelineUtil.groovy")
                }
            }
        }
        stage('Test') {
            parallel {
                stage('Executing on - google_pixel_6_pro') {
                    agent any
                    steps {
                        script {
                            def reportCollection = []
                            def executionSuccessful = util.executeKatalonTest(
                                    // All test suites that should be executed for mWO Service 10 on Android should go in the following list
                                    ['Test Suites/Android/Debrief/Debrief_Contacts/TS_Debrief_Contacts'],
                                     'default','google_pixel_6_pro')
                        }
                    }
                }
                /*
                stage('Executing on - google_pixel_6_pro') {
                                    agent any
                                    steps {
                                        script {
                                            def executionSuccessfulNew = util.executeKatalonTest(
                                                                                // All test suites that should be executed for mWO Service 10 on Android should go in the following list
                                                                                ['Test Suites/Android/Debrief/Debrief_PartNeeds/TS_Debrief_PartNeeds'],
                                                                                 'default','google_pixel_6_pro')
                                        }
                                    }
                }
                */
            }
        }
    }
}