import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject

import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile

import internal.GlobalVariable as GlobalVariable

import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.annotation.BeforeTestSuite
import com.kms.katalon.core.annotation.SetUp
import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.annotation.AfterTestSuite
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.context.TestSuiteContext
import com.kms.katalon.core.logging.KeywordLogger
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import io.appium.java_client.AppiumDriver as AppiumDriver
import org.openqa.selenium.remote.RemoteWebDriver;

import com.kms.katalon.core.configuration.RunConfiguration

import com.kms.katalon.core.util.KeywordUtil

class IFS_FSM_TestListner {
	
	/**
	 * Executes before every test case starts.
	 * @param testCaseContext related information of the executed test case.
	 */
	@BeforeTestCase
	def sampleBeforeTestCase(TestCaseContext testCaseContext) {
		Thread.sleep(120000)
		RunConfiguration.setDriverPreferencesProperty('Remote', 'sessionName', testCaseContext.getTestCaseId())
		KeywordUtil.markPassed("Testcase ID : " + testCaseContext.getTestCaseId() + " ")
		//KeywordUtil.markPassed("MobileDriverFactory Session ID : " + testCaseContext.getTestCaseVariables() + " ")
	}

	/**
	 * Executes after every test case ends.
	 * @param testCaseContext related information of the executed test case.
	 */
	@AfterTestCase
	def sampleAfterTestCase(TestCaseContext testCaseContext) {
		println testCaseContext.getTestCaseId()
		println testCaseContext.getTestCaseStatus()
		if(MobileDriverFactory.getDriver().getCapabilities().getCapability("platformName").toString()=="iOS") {
			MobileDriverFactory.getDriver().terminateApp(GlobalVariable.var_AppIdiOS)
			MobileDriverFactory.getDriver().closeApp()
			MobileDriverFactory.getDriver().quit()
		}
		else{
			//MobileDriverFactory.getDriver().terminateApp(GlobalVariable.var_AppIdAndroid)
			MobileDriverFactory.getDriver().closeApp()
			//MobileDriverFactory.getDriver().close()
			MobileDriverFactory.getDriver().quit()

		}

		KeywordUtil.markPassed("Testcase ID : " + testCaseContext.getTestCaseId() + " ")
	}

	/**
	 * Executes before every test suite starts.
	 * @param testSuiteContext: related information of the executed test suite.
	 */
	@BeforeTestSuite
	def sampleBeforeTestSuite(TestSuiteContext testSuiteContext) {
		println testSuiteContext.getTestSuiteId()
		CustomKeywords.'com.ifs.fsm.utils.LIB_Utils.getStartExecutionTime'()
	}

	/**
	 * Executes after every test suite ends.
	 * @param testSuiteContext: related information of the executed test suite.
	 */
	//	@AfterTestSuite
	//	def sampleAfterTestSuite(TestSuiteContext testSuiteContext) {
	//		println testSuiteContext.getTestSuiteId()
	//	}

	@AfterTestSuite
	def sampleAfterTestSuite(TestSuiteContext testSuiteContext) {
		KeywordLogger log = new KeywordLogger()
		def testSuiteId = testSuiteContext.getTestSuiteId()
		def testStatus = testSuiteContext.getStatus()
		log.logInfo(testSuiteId)
		log.logInfo(testStatus)
	}
	
	@AfterTestCase
	def sampleAfterTestCase_1(TestCaseContext testCaseContext) {
		if(testCaseContext.getTestCaseStatus()=='PASSED') {
			GlobalVariable.numOfPasses++
		}
		if(testCaseContext.getTestCaseStatus()=='FAILED') {
			GlobalVariable.numOfFails++
		}
//		if(testCaseContext.getTestCaseStatus()=='INCOMPLETE') {
//			GlobalVariable.numofIncomplete++
//		}
	}

	@AfterTestSuite
	def sampleAfterTestSuite_1(TestSuiteContext testSuiteContext) {
		println 'Passes:' +GlobalVariable.numOfPasses
		println 'Failures:' +GlobalVariable.numOfFails
		//GlobalVariable.testSuite = testSuiteContext.getTestSuiteId()
		//println 'Test Suite:' +testSuiteContext.getTestSuiteId()
		//getLastExecutionTime
		CustomKeywords.'com.ifs.fsm.utils.LIB_Utils.getLastExecutionTime'()
		//getDurationInSeconds
		//CustomKeywords.'com.ifs.fsm.utils.LIB_Utils.getDurationInSeconds'()
		CustomKeywords.'com.ifs.fsm.utils.LIB_Utils.createNodes'()

		
	}
	
	
}