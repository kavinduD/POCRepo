
def executeKatalonTest(List<String> testSuites, String profile, String device, retryOnFailure = true) {
     def katalonVersion = '8.0.0'
     def retryCount = retryOnFailure ? 2 : 0
     def apiKey = '8aeba134-53fb-45fd-9209-32e79a9b4039' // TODO: This should go into jenkins credentials
     def success = true
     def deviceReady = true
     def orgId = 45619

     lock(label: 'katalon', quantity: 1) {

          timeout(time: 4, unit: 'HOURS') { // Set a max execution time on pipelines so one failing environment doesn't destroy timing of tests in other environments
               for (String testSuite : testSuites) {
                    stage('Start Building') {
                         // The loop doesn't play well with Jenkins default behaviour when aborting jobs, so we have to manually cascade the abortion to the child stages
                         executeKatalon version: katalonVersion, executeArgs: "-retry=0 -projectPath=\"${env.WORKSPACE}/IFS_FSM_MobileAutomation/IFS_FSM_MobileAutomation.prj\" -testSuitePath=\"${testSuite}\" -executionProfile=\"${profile}\"  -browserType=\"${device}\" -apiKey=${apiKey} -orgID=${orgId} -licenseRelease=true", location: "", x11Display: "", xvfbConfiguration: ""
                         junit allowEmptyResults: true, testResults: "IFS_FSM_MobileAutomation/Reports/*/Android/Debrief/Debrief_Contacts/TS_Debrief_Contacts/*/JUnit_Report.xml"
                         //publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, reportDir: '${env.WORKSPACE}/IFS_FSM_MobileAutomation/Reports/*/Android/Debrief/Debrief_Contacts/TS_Debrief_Contacts/*/*.html', reportFiles: '*.html', reportName: 'HTML Report', reportTitles: ''])
                         
                    }
               }
          }
     }
}
return this