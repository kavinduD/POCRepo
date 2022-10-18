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
import com.kms.katalon.core.util.KeywordUtil

import internal.GlobalVariable

public class LIB_TimeReporting {


	// This method will tap the lookup icon inside a text field
	// Takes upper label name as the parameter
	public void tapLookUpIcon(String labelName) {
		int elementStartingPoint = Mobile.getElementLeftPosition(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : labelName]), 0)
		int elementWidth = Mobile.getElementWidth(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : labelName]), 0)

		int x = elementStartingPoint + elementWidth - 5
		int y = Mobile.getElementTopPosition(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : labelName]), 0)
		Mobile.tapAtPosition(x, y)
	}


	/**
	 * Add labor record on AddLabor screen
	 */
	@Keyword
	public void bf_TimeReporting_AddLaborRecordOnAddLaborScreen(String amount) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "16"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText',[('idf_ButtonName') : "ADD TIME"]), 0)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Amount']), amount, 3)
		Thread.sleep(2000)
		String laboramount =Mobile.getAttribute(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Amount']), 'name', 0)
		System.out.println("laboramount= " +laboramount)
		double laboramountvalue = Double.parseDouble(laboramount);
		Mobile.tap(findTestObject('Android/PG_Common/btn_Add'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Expected"]), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Labor"]), 0)
		String enteredvalue =Mobile.getAttribute(findTestObject('Android/PG_TimeReporting/lbl_EnteredValue', [('idf_LabelName') : 'Entered']), 'name', 0)
		double Entervalue = Double.parseDouble(enteredvalue);
		System.out.println("Entervalue= " +Entervalue)
		if( laboramountvalue == Entervalue ) {
			KeywordUtil.markPassed('Entered value updated successfully!')
		}else {
			KeywordUtil.markFailed('not updated!')
		}

	}

	/**
	 * Add labor record on Assigned task
	 */
	@Keyword
	public void bf_TimeReporting_AddLaborRecordOnAsssignedTask(String amount) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Accepted']), 0)
		Thread.sleep(2000)
		String navBarString = Mobile.getAttribute(findTestObject('Android/PG_Common/lbl_navBarCommonByLabelName', [('idf_LabelName') : 'Task']), 'name', 0)
		String[] result = navBarString.split(" ");
		String navBarSubString = result[1].trim();
		Thread.sleep(2000)

		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Labor']), 0)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Amount']), amount, 3)
		Thread.sleep(2000)
		String laboramount =Mobile.getAttribute(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Amount']), 'name', 0)
		double laboramountvalue = Double.parseDouble(laboramount);
		Mobile.tap(findTestObject('Android/PG_Common/btn_Add'), 0)
		Thread.sleep(3000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') :'Sync']), 0)
		Thread.sleep(15000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') :'Time Reporting']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "2"]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : navBarSubString]),0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Labor"]), 0)
		String enteredvalue =Mobile.getAttribute(findTestObject('Android/PG_TimeReporting/lbl_EnteredValue', [('idf_LabelName') : 'Entered']), 'name', 0)
		double Entervalue = Double.parseDouble(enteredvalue);
		if( laboramountvalue == Entervalue ) {
			KeywordUtil.markPassed('Entered value updated successfully!')
		}else {
			KeywordUtil.markFailed('not updated!')
		}
	}

	/**
	 * Modify labor record
	 */
	@Keyword
	public void bf_TimeReporting_ModifyLaborRecord(String lineCode,String amount) {
		Thread.sleep(2000)
		Mobile.takeScreenshot('Screenshots/Time_ReportingBeforeModify.png', FailureHandling.STOP_ON_FAILURE)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "16"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Labor"]), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText',[('idf_ButtonName') : "MODIFY"]), 0)
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Line Code']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : lineCode]), 0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Amount']), amount, 3)
		Thread.sleep(2000)
		tapLookUpIcon('Work Date')
		Mobile.tap(findTestObject('Android/PG_POProcess/tv_date'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText',[('idf_ButtonName') : "OK"]), 0)
		Mobile.tap(findTestObject('Android/PG_WorkList/btn_Update'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Expected"]), 0)
		Mobile.pressBack()
		Thread.sleep(2000)
		Mobile.takeScreenshot('Screenshots/Time_ReportingAfterModify.png', FailureHandling.STOP_ON_FAILURE)
	}

	/**
	 * Modify labor record connected to a task-AddLabor screen
	 */
	@Keyword
	public void bf_TimeReporting_ModifyLaborRecordToTaskAddLaborScreen(String lineCode,String amount) {
		Thread.sleep(2000)
		Mobile.takeScreenshot('Screenshots/Time_ReportingBeforeAddLaborModify.png', FailureHandling.STOP_ON_FAILURE)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "10"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Labor"]), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText',[('idf_ButtonName') : "MODIFY"]), 0)
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Line Code']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : lineCode]), 0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Amount']), amount, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_WorkList/btn_Update'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Expected"]), 0)
		Mobile.pressBack()
		Mobile.takeScreenshot('Screenshots/Time_ReportingAfterAddLaborModify.png', FailureHandling.STOP_ON_FAILURE)
	}


	/**
	 * Modify labor record connected to a task- DebriefLabor screen
	 */
	@Keyword
	public void bf_TimeReporting_ModifyLaborRecordConnectedTotaskDebriefLaborscreen(String lineCode,String amount) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Accepted']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Labor']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByUpperLabelName',[('idf_LabelName') : "Labor"]), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText',[('idf_ButtonName') : "MODIFY"]), 0)
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Line Code']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : lineCode]), 0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Amount']), amount, 3)
		Thread.sleep(2000)
		tapLookUpIcon('Work Date')
		Mobile.tap(findTestObject('Android/PG_POProcess/tv_date'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText',[('idf_ButtonName') : "OK"]), 0)
		Mobile.tap(findTestObject('Android/PG_WorkList/btn_Update'), 0)
		Mobile.pressBack()
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') :'Sync']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') :'Time Reporting']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "6"]), 0)
	}


	/**
	 * Delete labor record
	 */
	@Keyword
	public void bf_TimeReporting_DeleteLaborRecord() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "6"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Labor"]), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText',[('idf_ButtonName') : "DELETE"]), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Entered"]), 0)
	}

	/**
	 * Delete labor record connected to a task-Day overview screen
	 */
	@Keyword
	public void bf_TimeReporting_DeleteLaborRecordConnectedToTaskDayOverviewScreen(){
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "5"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Labor"]), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText',[('idf_ButtonName') : "DELETE"]), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Expected"]), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') :'Jobs']), 0)
		Thread.sleep(15000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Accepted']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Labor']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		Mobile.verifyElementNotExist(findTestObject('Android/PG_TimeReporting/lbl_Date'), 0)
	}

	/**
	 * Delete labor record connected to a task-Debrief Labor list screen
	 */
	@Keyword
	public void bf_TimeReporting_DeleteLaborRecordConnectedToTaskDebriefLaborListscreen() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Accepted']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Labor']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		String workdate = Mobile.getText(findTestObject('Android/PG_TimeReporting/lbl_Date', [('idf_LabelName') : 'Labor']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByUpperLabelName',[('idf_LabelName') : "Labor"]), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText',[('idf_ButtonName') : "DELETE"]), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') :'Time Reporting']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "2"]), 0)
		Mobile.verifyElementNotExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Entered"]), 0)
	}

	/**
	 * Verify validations on mandatory fields
	 */
	@Keyword
	public void bf_TimeReporting_VerifyValidationsOnMandatoryFields() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "5"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText',[('idf_ButtonName') : "ADD TIME"]), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Add'), 0)
		def message = Mobile.getText(findTestObject('Android/PG_WorkList/lbl_ErrorMessageForRequiredFields'),10)
		KeywordUtil.markPassed("Error Message : "+message)
		Thread.sleep(5000)
	}

	/**
	 *Add a calendar exception
	 */
	@Keyword
	public void bf_TimeReporting_AddCalendarException() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "3"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText',[('idf_ButtonName') : "ADD EXCEPTION"]), 0)
		Mobile.verifyElementNotChecked(findTestObject('Android/PG_Stocks/chk_Usable1'), 10)
		tapLookUpIcon('End')
		Mobile.tap(findTestObject('Android/PG_POProcess/tv_date'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText',[('idf_ButtonName') : "OK"]), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Add'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Expected"]), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Personal"]), 0)
	}

	/**
	 *Modify calendar exception
	 */
	@Keyword
	public void bf_TimeReporting_ModifyCalendarException(String type) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "7"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Personal"]), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText',[('idf_ButtonName') : "MODIFY"]), 0)
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Type']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : type]), 0)
		Thread.sleep(2000)
		tapLookUpIcon('End')
		Mobile.tap(findTestObject('Android/PG_POProcess/tv_date'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText',[('idf_ButtonName') : "OK"]), 0)
		Mobile.tap(findTestObject('Android/PG_WorkList/btn_Update'), 0)
	}

	/**
	 *Delete calendar exception
	 */
	@Keyword
	public void bf_TimeReporting_DeleteCalendarException() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "7"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Personal"]), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText',[('idf_ButtonName') : "DELETE"]), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Expected"]), 0)
	}
}
