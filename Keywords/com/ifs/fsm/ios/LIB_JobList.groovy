package com.ifs.fsm.ios

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
	/**
	 * Verify the operation of job list filters
	 */
	@Keyword
	public void bf_JobList_VerifyJobListFilters() {
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByContainText', [('idf_ButtonName') : 'All']), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByContainText', [('idf_ButtonName') : 'Today']), 0, 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/btn_CommonByContainText', [('idf_ButtonName') : 'Today']), 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByContainText', [('idf_ButtonName') : 'Today']), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByContainText', [('idf_ButtonName') : 'Tomorrow']), 0, 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/btn_CommonByContainText', [('idf_ButtonName') : 'Tomorrow']), 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByContainText', [('idf_ButtonName') : 'Tomorrow']), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByContainText', [('idf_ButtonName') : 'Overdue']), 0, 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/btn_CommonByContainText', [('idf_ButtonName') : 'Overdue']), 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByContainText', [('idf_ButtonName') : 'Overdue']), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByContainText', [('idf_ButtonName') : 'Change Orders']), 0, 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/btn_CommonByContainText', [('idf_ButtonName') : 'Change Orders']), 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByContainText', [('idf_ButtonName') : 'Change Orders']), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByContainText', [('idf_ButtonName') : 'PMs']), 0, 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/btn_CommonByContainText', [('idf_ButtonName') : 'PMs']), 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByContainText', [('idf_ButtonName') : 'PMs']), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByContainText', [('idf_ButtonName') : 'Team Tasks']), 0, 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/btn_CommonByContainText', [('idf_ButtonName') : 'Team Tasks']), 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByContainText', [('idf_ButtonName') : 'Team Tasks']), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByContainText', [('idf_ButtonName') : 'All']), 0, 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/btn_CommonByContainText', [('idf_ButtonName') : 'All']), 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByContainText', [('idf_ButtonName') : 'Team Tasks']), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByContainText', [('idf_ButtonName') : 'All']), 0, 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/btn_CommonByContainText', [('idf_ButtonName') : 'All']), 0)
	}

	/**
	 * Verify the operation of job list sorting filters
	 */
	@Keyword
	public void bf_JobList_VerifyJobListSortingFilters() {
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/btn_CommonByContainText', [('idf_ButtonName') : 'All']), 0)
		
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'By Date']), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'By Customer']), 0, 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'By Customer']), 0)
		
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'By Customer']), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'By Priority']), 0, 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'By Priority']), 0)
		
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'By Priority']), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'By Type']), 0, 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'By Type']), 0)
		
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'By Type']), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'By Task ID']), 0, 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'By Task ID']), 0)
		
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'By Task ID']), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'By Date']), 0, 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'By Date']), 0)
	}

	/**
	 * Verify the navigation to DebriefOverview screen
	 */
	@Keyword
	public void bf_JobList_VerifyNavigation() {
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Overview']), 0)
	}

	/**
	 * Verify the search
	 */
	@Keyword
	public void bf_JobList_VerifySearch(String taskID, String placeID, String taskStatus) {
		Mobile.setText(findTestObject('iOS/PG_JobList/PG_JobList/tf_SearchBar'), taskID, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Cancel']), 0, 0)

		Mobile.setText(findTestObject('iOS/PG_JobList/PG_JobList/tf_SearchBar'), placeID, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Cancel']), 0, 0)

		Mobile.setText(findTestObject('iOS/PG_JobList/PG_JobList/tf_SearchBar'), taskStatus, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Cancel']), 0, 0)
	}

	/**
	 * Verify properties
	 */
	@Keyword
	public void bf_JobList_VerifyProperties() {
		Mobile.verifyElementExist(findTestObject('iOS/PG_JobList/PG_JobList/lbl_AddressFromList'), 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_JobList/PG_JobList/lbl_SerialIDFromList'), 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_JobList/PG_JobList/lbl_FormattedTimeFromList'), 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_JobList/PG_JobList/lbl_TaskIDFromList'), 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_JobList/PG_JobList/lbl_TaskStatusFromList'), 0)
	}

	/**
	 * Verify navigation to task map
	 */
	@Keyword
	public void bf_ShowMap_VerifyNavigationTaskMap() {
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/btn_CommonByContainText', [('idf_ButtonName') : 'All']), 0)

		LIB_Common.bf_ClickContextMenuOption('Show Map...')
		Mobile.verifyElementExist(findTestObject('iOS/PG_JobList/PG_ShowMap/ele_Map'), 0)
	}

	/**
	 * Verify navigation to team task map
	 */
	@Keyword
	public void bf_ShowMap_VerifyNavigationTeamTaskMap() {
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByContainText', [('idf_ButtonName') : 'All']), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByContainText', [('idf_ButtonName') : 'Team Tasks']), 0, 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/btn_CommonByContainText', [('idf_ButtonName') : 'Team Tasks']), 0)

		LIB_Common.bf_ClickContextMenuOption('Show Team Task Map...')
		Mobile.verifyElementExist(findTestObject('iOS/PG_JobList/PG_ShowMap/ele_Map'), 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Search']), 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_Back'), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByContainText', [('idf_ButtonName') : 'Team Tasks']), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByContainText', [('idf_ButtonName') : 'All']), 0, 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/btn_CommonByContainText', [('idf_ButtonName') : 'All']), 0)
	}

	/**
	 * Accept an open task
	 */
	@Keyword
	public void bf_AcceptRejectTask_AcceptOpenTask() {
		int y = ( Mobile.getDeviceHeight() / 2 );
		int x = ( Mobile.getDeviceWidth() / 2 );
		int yNew = y - 200;

		for(int i=0; i<10; i++) {
			boolean flag = Mobile.verifyElementVisible(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Open']), 3, FailureHandling.OPTIONAL)

			if(flag) {
				LIB_Common.swipeDelete('Open')
				Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Accept']), 0, 0)
				Mobile.delay(5)
				break;
			}else {
				Mobile.swipe(x, y, x , yNew)
				Mobile.delay(3)
			}
		}
	}

	/**
	 * Reject an open task
	 */
	@Keyword
	public void bf_AcceptRejectTask_RejectOpenTask() {
		int y = ( Mobile.getDeviceHeight() / 2 );
		int x = ( Mobile.getDeviceWidth() / 2 );
		int yNew = y - 200;

		for(int i=0; i<10; i++) {
			boolean flag = Mobile.verifyElementVisible(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Open']), 3, FailureHandling.OPTIONAL)

			if(flag) {
				LIB_Common.swipeDelete('Open')
				Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Reject']), 0, 0)
				Mobile.delay(5)
				break;
			}else {
				Mobile.swipe(x, y, x , yNew)
				Mobile.delay(3)
			}
		}
	}
}
