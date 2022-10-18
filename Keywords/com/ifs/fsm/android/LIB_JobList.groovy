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

public class LIB_JobList {

	//This method will long press on an item
	public void LongPressListItem(String good,String longpressstockListItem) {
		Mobile.tapAndHold(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : good]) ,0,0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : longpressstockListItem]), 0)
	}

	/**
	 * Verify the operation of job list filters
	 */
	@Keyword
	public void bf_JobList_VerifyJobListFilters() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonContainsByText', [('idf_LabelName') : 'All']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'New']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Work List"]), 0)


		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonContainsByText', [('idf_LabelName') : 'New']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Today']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Work List"]), 0)

		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonContainsByText', [('idf_LabelName') : 'Today']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Tomorrow']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Work List"]), 0)

		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonContainsByText', [('idf_LabelName') : 'Tomorrow']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Overdue']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Work List"]), 0)

		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonContainsByText', [('idf_LabelName') : 'Overdue']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Change Orders']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Work List"]), 0)

		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonContainsByText', [('idf_LabelName') : 'Change Orders']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'PMs']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Work List"]), 0)

		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonContainsByText', [('idf_LabelName') : 'PMs']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Team Tasks']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Work List"]), 0)

		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonContainsByText', [('idf_LabelName') : 'Team Tasks']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'All']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Work List"]), 0)
	}

	/**
	 * Verify the operation of job list sorting filters
	 */
	@Keyword
	public void bf_JobList_VerifyJobListSortingFilters() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonContainsByText', [('idf_LabelName') : 'By Date']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'By Customer']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Work List"]), 0)

		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonContainsByText', [('idf_LabelName') : 'By Customer']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'By Priority']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Work List"]), 0)

		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonContainsByText', [('idf_LabelName') : 'By Priority']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'By Type']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Work List"]), 0)

		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonContainsByText', [('idf_LabelName') : 'By Type']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'By Task ID']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Work List"]), 0)

		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonContainsByText', [('idf_LabelName') : 'By Task ID']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'By Date']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Work List"]), 0)
	}

	/**
	 * Verify the navigation to DebriefOverview screen
	 */
	@Keyword
	public void bf_JobList_VerifyNavigation() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Arrived']), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Overview"]), 0)
	}

	/**
	 * Verify the search
	 */
	@Keyword
	public void bf_JobList_VerifySearch(String taskID,String placeID,String taskStatus) {
		Mobile.setText(findTestObject('Android/PG_Stocks/tf_SearchField', [('idf_LabelName') : 'Search']),taskID , 3)
		Thread.sleep(2000)
		Mobile.clearText(findTestObject('Android/PG_Stocks/tf_SearchField'), 3)
		Mobile.setText(findTestObject('Android/PG_Stocks/tf_SearchField', [('idf_LabelName') : 'Search']),placeID , 3)
		Thread.sleep(2000)
		Mobile.clearText(findTestObject('Android/PG_Stocks/tf_SearchField'), 3)
		Mobile.setText(findTestObject('Android/PG_Stocks/tf_SearchField', [('idf_LabelName') : 'Search']),taskStatus , 3)
		Thread.sleep(2000)
	}

	/**
	 * Verify properties
	 */
	@Keyword
	public void bf_JobList_VerifyProperties() {
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Arrived"]), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Break Fix"]), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Call"]), 0)
	}


	/**
	 * Verify navigation to task map
	 */
	@Keyword
	public void bf_JobList_VerifyNavigationTaskMap() {
		Mobile.verifyElementNotExist(findTestObject('Android/PG_Common/lbl_CommonContainsByText', [('idf_LabelName') : 'Team Tasks']), 5)
		LongPressListItem('Arrived','Show Map...')
		Thread.sleep(5000)
		Mobile.takeScreenshot('Screenshots/Job_List_maps.png', FailureHandling.STOP_ON_FAILURE)
		Mobile.pressBack()
	}

	/**
	 * Verify navigation to team task map
	 */
	@Keyword
	public void bf_JobList_VerifyNavigationTeamTaskMap() {
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonContainsByText', [('idf_LabelName') : 'Team Tasks']), 5)
		LongPressListItem('Open','Show Team Task Map...')
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Find Nearest Team Tasks"]), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText',[('idf_ButtonName') : 'CANCEL']), 0)

	}

	/**
	 * Accept an open task
	 */
	@Keyword
	public void bf_JobList_AcceptOpenTask() {
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonContainsByText', [('idf_LabelName') : 'All']), 5)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Open']), 0)

		String navBarString = Mobile.getAttribute(findTestObject('Android/PG_Common/lbl_navBarCommonByLabelName', [('idf_LabelName') : 'Task']), 'name', 0)
		String[] result = navBarString.split(" ");
		String navBarSubString = result[1].trim();
		Mobile.pressBack()


		Mobile.tapAndHold(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Open']) ,0,0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonContainsByText',[('idf_LabelName') : 'Accept Job']), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Work List"]), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_POProcess/lbl_ApproveStatus', [('idf_LabelName') : navBarSubString, ('idf_LabelName1') : 'Accepted']),0)
	}

	/**
	 * Reject an open task
	 */
	@Keyword
	public void bf_JobList_RejectOpenTask() {
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonContainsByText', [('idf_LabelName') : 'All']), 5)
		Mobile.tapAndHold(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Open']), 0,0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonContainsByText',[('idf_LabelName') : 'Reject Job']), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Work List"]), 0)
	}


}
