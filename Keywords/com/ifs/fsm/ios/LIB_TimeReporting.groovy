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
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords


import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException
import java.time.LocalDate;



public class LIB_TimeReporting {
	// This method scroll and click on any element in a picker wheel
	public void clickElementFromPickerWheel(String labelName) {
		TestObject pickerWheel = new TestObject();
		pickerWheel.addProperty("xpath", ConditionType.EQUALS, "//XCUIElementTypePickerWheel");
		MobileBuiltInKeywords.sendKeys(pickerWheel, labelName);
	}

	/**
	 * Add labor record on AddLabor screen
	 */
	@Keyword
	public void bf_TimeReporting_AddLaborRecordOnAddLaborScreen(String amount) {
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : '24']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add Time']), 0, 0)

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Amount']), amount, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']), 0, 0)

		//Mobile.verifyElementText(findTestObject('iOS/PG_Common/lbl_CommonByUpperLabelName', [('idf_LabelName') : 'Entered Hours']), '50')

	}

	/**
	 * Add labor record on Assigned task
	 */
	@Keyword
	public void bf_TimeReporting_AddLaborRecordOnAsssignedTask(String amount) {
		LocalDate currentdate = LocalDate.now();
		int currentDay = currentdate.getDayOfMonth();

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Amount']), amount, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']), 0, 0)
		Mobile.delay(60)

		LIB_Common.bf_ClickHamburgerMenuOption("Time Reporting")

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : currentDay]), 0, 0)

		//Mobile.verifyElementText(findTestObject('iOS/PG_Common/lbl_CommonByUpperLabelName', [('idf_LabelName') : 'Entered Hours']), '50')
	}

	/**
	 * Modify labor record
	 */
	@Keyword
	public void bf_TimeReporting_ModifyLaborRecord(String lineCode, String amount) {
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : '24']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : lineCode]), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Modify']), 0, 0)

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Amount']), amount, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Save']), 0, 0)

		//Mobile.verifyElementText(findTestObject('iOS/PG_Common/lbl_CommonByUpperLabelName', [('idf_LabelName') : 'Entered Hours']), '50')
	}

	/**
	 * Modify labor record connected to a task-AddLabor screen
	 */
	@Keyword
	public void bf_TimeReporting_ModifyLaborRecordToTaskAddLaborScreen(String lineCode, String newLineCode, String amount) {
		LocalDate currentdate = LocalDate.now();
		int currentDay = currentdate.getDayOfMonth();

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : currentDay]), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : lineCode]), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Modify']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Line Code']), 0, 0)
		clickElementFromPickerWheel(newLineCode)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Amount']), amount, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Save']), 0, 0)


		//Mobile.verifyElementText(findTestObject('iOS/PG_Common/lbl_CommonByUpperLabelName', [('idf_LabelName') : 'Entered Hours']), '50')
	}


	/**
	 * Modify labor record connected to a task- DebriefLabor screen
	 */
	@Keyword
	public void bf_TimeReporting_ModifyLaborRecordConnectedToTaskDebriefLaborScreen() {
		LocalDate currentdate = LocalDate.now();
		int currentDay = currentdate.getDayOfMonth();

		LIB_Common.bf_ClickHamburgerMenuOption("Time Reporting")

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : currentDay]), 0, 0)

		//Mobile.verifyElementText(findTestObject('iOS/PG_Common/lbl_CommonByUpperLabelName', [('idf_LabelName') : 'Entered Hours']), '50')
	}


	/**
	 * Delete labor record
	 */
	@Keyword
	public void bf_TimeReporting_DeleteLaborRecord(String lineCode) {
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : '24']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : lineCode]), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Delete']), 0, 0)
	}

	/**
	 * Delete labor record connected to a task-Day overview screen
	 */
	@Keyword
	public void bf_TimeReporting_DeleteLaborRecordConnectedToTaskDayOverviewScreen(String lineCode){
		LocalDate currentdate = LocalDate.now();
		int currentDay = currentdate.getDayOfMonth();

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : currentDay]), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : lineCode]), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Delete']), 0, 0)
	}

	/**
	 * Delete labor record connected to a task-Debrief Labor list screen
	 */
	@Keyword
	public void bf_TimeReporting_DeleteLaborRecordConnectedToTaskDebriefLaborListscreen() {
		LocalDate currentdate = LocalDate.now();
		int currentDay = currentdate.getDayOfMonth();

		LIB_Common.bf_ClickHamburgerMenuOption("Time Reporting")

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : currentDay]), 0, 0)

		Mobile.verifyElementNotExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Entered Hours']), 0)

		//Mobile.verifyElementText(findTestObject('iOS/PG_Common/lbl_CommonByUpperLabelName', [('idf_LabelName') : 'Entered Hours']), '50')
	}

	/**
	 * Verify validations on mandatory fields
	 */
	@Keyword
	public void bf_TimeReporting_VerifyValidationsOnMandatoryFields() {
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : '24']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add Time']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']), 0, 0)
		Mobile.delay(3)
		
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'OK']), 0, 0)

	}

	/**
	 *Add a calendar exception
	 */
	@Keyword
	public void bf_TimeReporting_AddCalendarException() {
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : '24']), 0, 0)
	}

	/**
	 *Modify calendar exception
	 */
	@Keyword
	public void bf_TimeReporting_ModifyCalendarException(String type) {
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : '24']), 0, 0)
	}

	/**
	 *Delete calendar exception
	 */
	@Keyword
	public void bf_TimeReporting_DeleteCalendarException() {
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : '24']), 0, 0)
	}
}
