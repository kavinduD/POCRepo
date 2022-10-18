package com.ifs.fsm.android

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

public class LIB_Home {

	//This method returns the Home Job tile counter badge count
	public String getJobCount() {
		Thread.sleep(2000)
		String count = Mobile.getText(findTestObject('Android/PG_Common/lbl_CommonByUpperLabelName', [('idf_LabelName') : 'Jobs']), 0)
		return count;
	}

	//This method returns the Home Customers tile counter badge count
	public String getCustomersCount() {
		Thread.sleep(2000)
		String count = Mobile.getText(findTestObject('Android/PG_Common/lbl_CommonByUpperLabelName', [('idf_LabelName') : 'Customers']), 0)
		return count;
	}

	//This method returns the Home Stock tile counter badge count
	public String getStockCount() {
		Thread.sleep(2000)
		String count = Mobile.getText(findTestObject('Android/PG_Common/lbl_CommonByUpperLabelName', [('idf_LabelName') : 'Stock']), 0)
		return count;
	}

	//This method returns the Home Team tile counter badge count
	public String getTeamCount() {
		Thread.sleep(2000)
		String count = Mobile.getText(findTestObject('Android/PG_Common/lbl_CommonByUpperLabelName', [('idf_LabelName') : 'Team']), 0)
		return count;
	}

	//This method returns the Home Receiving tile counter badge count
	public String getReceivingCount() {
		Thread.sleep(2000)
		String count = Mobile.getText(findTestObject('Android/PG_Common/lbl_CommonByUpperLabelName', [('idf_LabelName') : 'Receiving']), 0)
		return count;
	}

	/**
	 * TA-Home: Verify available tiles in baseline design
	 */
	@Keyword
	public void bf_Home_VerifyAvailableTiles() {
		Thread.sleep(2000)
		Mobile.takeScreenshot('Screenshots/home.png', FailureHandling.STOP_ON_FAILURE)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByUpperLabelName', [('idf_LabelName') : 'Jobs']), 3)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByUpperLabelName', [('idf_LabelName') : 'Customers']), 3)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByUpperLabelName', [('idf_LabelName') : 'Stock']), 3)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByUpperLabelName', [('idf_LabelName') : 'Team']), 3)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByUpperLabelName', [('idf_LabelName') : 'Receiving']), 3)
		Thread.sleep(2000)
	}

	/**
	 * TA-Home: Verify navigation to relevant screen from home screen tiles
	 */
	@Keyword
	public void bf_Home_VerifyNavigationtoRelevantScreenfromHomeScreenTiles() {
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Jobs']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Work List"]), 0)
		Mobile.pressBack()
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Customers']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Customers"]), 0)
		Mobile.pressBack()
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Stock"]), 0)
		Mobile.pressBack()
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Team']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Team"]), 0)
		Mobile.pressBack()
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Receiving']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Receiving"]), 0)
	}

	/**
	 * TA-Home: Verify the counts displayed on home screen tiles
	 */
	@Keyword
	public void bf_Home_VerifytheCountsDisplayedonHomeScreenTiles() {
		String JobListCount = getJobCount()
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Jobs']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonContainsByText',[('idf_LabelName') :JobListCount ]), 0)
		Mobile.pressBack()

		String CustomersListCount = getCustomersCount()
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Customers']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonContainsByText',[('idf_LabelName') :CustomersListCount ]), 0)
		Mobile.pressBack()

		String StockListCount = getStockCount()
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonContainsByText',[('idf_LabelName') :StockListCount ]), 0)
		Mobile.pressBack()

		String TeamListCount = getTeamCount()
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Team']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonContainsByText',[('idf_LabelName') :TeamListCount ]), 0)
		Mobile.pressBack()

		String ReceivingListCount = getReceivingCount()
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Receiving']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonContainsByText',[('idf_LabelName') :ReceivingListCount ]), 0)
	}

	/**
	 * TA-Home: Important Information-New Jobs
	 */
	@Keyword
	public void bf_Home_ImportantInformationNewJobs() {
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Important Information"]), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonContainsByText', [('idf_LabelName') : 'new jobs!']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Work List"]), 0)
	}

	/**
	 * TA-Home: Important Information-Overdue Jobs
	 */
	@Keyword
	public void bf_Home_ImportantInformationOverdueJobs() {
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Important Information"]), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonContainsByText', [('idf_LabelName') : 'jobs overdue!']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Work List"]), 0)
	}

	/**
	 * TA-Home: Important Information Commitments
	 */
	@Keyword
	public void bf_Home_ImportantInformationCommitments() {
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Important Information"]), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonContainsByText', [('idf_LabelName') : 'overdue commitments!']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Commitents"]), 0)
	}

	/**
	 * TA-Home: Important Information Escalations
	 */
	@Keyword
	public void bf_Home_ImportantInformationEscalations() {
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Important Information"]), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonContainsByText', [('idf_LabelName') : 'escalation!']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Escalation List"]), 0)
	}

	/**
	 * TA-Home: Important Information stock counts
	 */
	@Keyword
	public void bf_Home_ImportantInformationStockCounts() {
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Important Information"]), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonContainsByText', [('idf_LabelName') : 'new stock count!']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Stock Count"]), 0)
	}

}

