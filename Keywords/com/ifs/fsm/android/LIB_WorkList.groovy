package com.ifs.fsm.android

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import ch.qos.logback.core.joran.conditional.IfAction
import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException

import internal.GlobalVariable
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile

import java.lang.*;
import java.util.*;
import java.text.*;

import java.util.Calendar

import io.appium.java_client.MultiTouchAction as MultiTouchAction
import io.appium.java_client.TouchAction
import io.appium.java_client.touch.LongPressOptions
import io.appium.java_client.AppiumDriver as AppiumDriver

class LIB_WorkList {
	// This method returns the number of records inside a 'List (x)' in some screens in debrief workflow
	public String returnListValue() {
		String listValue = Mobile.getText(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),30)
		String listValueSubstring = listValue.substring(listValue.indexOf("(") + 1, listValue.indexOf(")"));

		KeywordUtil.markPassed("No of List Values : " + listValueSubstring + " ")

		return listValueSubstring;
	}



	// This method will tap the lookup icon inside a text field
	// Takes upper label name as the parameter
	public void tapLookUpIcon(String labelName) {
		int elementStartingPoint = Mobile.getElementLeftPosition(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : labelName]), 0)
		int elementWidth = Mobile.getElementWidth(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : labelName]), 0)

		int x = elementStartingPoint + elementWidth - 5
		int y = Mobile.getElementTopPosition(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : labelName]), 0)

		Mobile.tapAtPosition(x, y)
	}


	// This method will tap the icon and set the time
	public void SetTime(String hour,String minute,String time) {
		Mobile.tap(findTestObject('Android/PG_DebriefOverview/btn_Clock'), 0)
		Mobile.setText(findTestObject('Android/PG_DebriefOverview/tf_Hour', [('idf_LabelName') : 'hour']), hour, 3)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_DebriefOverview/tf_Minute', [('idf_LabelName') : 'minute']), minute, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByTextDropDownItem', [('idf_LabelName') : 'am']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : time]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_DebriefOverview/btn_Ok'), 0)
	}
	/**
	 * Check if element present in timeout
	 * @param to Katalon test object
	 * @param timeout time to wait for element to show up
	 * @return true if element present, otherwise false
	 */
	@Keyword
	def isElementPresent_Mobile(TestObject to, int timeout){
		try {
			KeywordUtil.logInfo("Finding element with id:" + to.getObjectId())

			WebElement element = MobileElementCommonHelper.findElement(to, timeout)
			if (element != null) {
				KeywordUtil.markPassed("Object " + to.getObjectId() + " is present")
			}
			return true
		} catch (Exception e) {
			KeywordUtil.markFailed("Object " + to.getObjectId() + " is not present")
		}
		return false;
	}

	/**
	 * Get mobile driver for current session
	 * @return mobile driver for current session
	 */
	@Keyword
	def WebDriver getCurrentSessionMobileDriver() {
		return MobileDriverFactory.getDriver();
	}

	/**
	 * Select a Task
	 */
	@Keyword
	public void bf_SelectTask(String taskStatus) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : taskStatus]), 0)
	}

	/**
	 * Delete First Record from the List
	 */
	@Keyword
	public void bf_DeleteFirstListOfRecordEntered() {
		Mobile.tap(findTestObject('Android/PG_WorkList/lbl_FirstListOfRecordEntered'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Modify or Delete"]), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "Delete", ('idf_ButtonNameCapital') : "DELETE"]), 0)
	}

	/**
	 * Delete First Record from the Contact List
	 */
	@Keyword
	public void bf_DeleteFirstContactListOfRecordEntered() {
		Mobile.tap(findTestObject('Android/PG_WorkList/PG_Jobs_Contacts/lbl_FirstListOfRecordEntered'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Confirm Delete"]), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "Yes", ('idf_ButtonNameCapital') : "YES"]), 0)
	}

	/**
	 * Delete First Record from the Parts Used List
	 */
	@Keyword
	public void bf_DeleteFirstPartsUsedListOfRecordEntered() {
		Mobile.tap(findTestObject('Android/PG_WorkList/lbl_FirstListOfRecordEntered'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Confirm Delete"]), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "Yes", ('idf_ButtonNameCapital') : "YES"]), 0)
		Thread.sleep(5000);
	}

	/**
	 * Enter contact details for a job and Verify List count has increased or not
	 */
	@Keyword
	public void bf_Contacts_AddNewContactAndVerify(String lastName, String phone) {
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Contacts"]), 10)
		String listValuesBefore = Mobile.getText(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		String listValuesBeforeSubstring = listValuesBefore.substring(listValuesBefore.indexOf("(") + 1, listValuesBefore.indexOf(")"));
		KeywordUtil.markPassed("No of List Values before adding a new contact " + listValuesBeforeSubstring + " ")
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_WorkList/PG_Jobs_Contacts/tf_LastName'), lastName, 0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_WorkList/PG_Jobs_Contacts/tf_Phone'), phone, 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Add'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Contacts"]), 10)
		String listValuesAfter = Mobile.getText(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		String listValuesAfterSubstring = listValuesAfter.substring(listValuesAfter.indexOf("(") + 1, listValuesAfter.indexOf(")"));
		KeywordUtil.markPassed("No of List Values after adding a new contact " + listValuesAfterSubstring + " ")
		Boolean listvaluesresult = Mobile.verifyGreaterThan(listValuesAfterSubstring, listValuesBeforeSubstring)
		if (listvaluesresult) {
			KeywordUtil.markPassed("List values have increased to " + listValuesAfter + " ")
			Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
			Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : lastName]), 0)
		}
		else {
			KeywordUtil.markFailed("List values hasn't increased")
		}
	}

	/**
	 * Add a contact using first name by clicking lookup icon
	 */
	@Keyword
	public void bf_Contacts_addNewContactByFirstNameAndVerify(String firstName) {
		String listValuesBefore = Mobile.getText(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		String listValuesBeforeSubstring = listValuesBefore.substring(listValuesBefore.indexOf("(") + 1, listValuesBefore.indexOf(")"));
		KeywordUtil.markPassed("No of List Values before adding a new contact " + listValuesBeforeSubstring + " ")

		int elementStartingPoint = Mobile.getElementLeftPosition(findTestObject('Android/PG_WorkList/PG_Jobs_Contacts/tf_FirstName'), 0)
		int elementWidth = Mobile.getElementWidth(findTestObject('Android/PG_WorkList/PG_Jobs_Contacts/tf_FirstName'), 0)

		int x = elementStartingPoint + elementWidth - 5
		int y = Mobile.getElementTopPosition(findTestObject('Android/PG_WorkList/PG_Jobs_Contacts/tf_FirstName'), 0)

		Mobile.tapAtPosition(x, y)

		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable', [('idf_TextName') : firstName]),0)

		Mobile.tap(findTestObject('Android/PG_WorkList/btn_Save'), 0)

		String listValuesAfter = Mobile.getText(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		String listValuesAfterSubstring = listValuesAfter.substring(listValuesAfter.indexOf("(") + 1, listValuesAfter.indexOf(")"));
		KeywordUtil.markPassed("No of List Values after adding a new contact " + listValuesAfterSubstring + " ")
		Boolean listvaluesresult = Mobile.verifyGreaterThan(listValuesAfterSubstring, listValuesBeforeSubstring)
		if (listvaluesresult) {
			KeywordUtil.markPassed("List values have increased to " + listValuesAfter + " ")
			Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		}
		else {
			KeywordUtil.markFailed("List values hasn't increased")
		}
	}

	/**
	 * Add a contact using first name by clicking lookup icon. Repeating the same action to verify contact already exist. 
	 */
	@Keyword
	public void bf_Contacts_addNewContactByFirstNameRepeatAndVerify() {
		int elementStartingPoint = Mobile.getElementLeftPosition(findTestObject('Android/PG_WorkList/PG_Jobs_Contacts/tf_FirstName'), 0)
		int elementWidth = Mobile.getElementWidth(findTestObject('Android/PG_WorkList/PG_Jobs_Contacts/tf_FirstName'), 0)

		int x = elementStartingPoint + elementWidth - 5
		int y = Mobile.getElementTopPosition(findTestObject('Android/PG_WorkList/PG_Jobs_Contacts/tf_FirstName'), 0)

		Mobile.tapAtPosition(x, y)

		Mobile.tap(findTestObject('Android/PG_Common/lbl_FirstItemFromListWithSeperator'), 0)

		Mobile.tap(findTestObject('Android/PG_WorkList/btn_Save'), 0)
		Thread.sleep(3000)

		Mobile.tapAtPosition(x, y)
		Thread.sleep(3000)

		Mobile.tap(findTestObject('Android/PG_Common/lbl_FirstItemFromListWithSeperator'), 0)

		Mobile.tap(findTestObject('Android/PG_WorkList/btn_Save'), 0)
		Thread.sleep(500)

		def message = Mobile.getText(findTestObject('Android/PG_WorkList/lbl_ErrorMessageForRequiredFields'),10)
		KeywordUtil.markPassed("Error Message : " + message)
		Thread.sleep(5000)

		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
	}

	/**
	 * Enter Expense details for a job and Verify List count has increased or not
	 */
	@Keyword
	public void bf_Expenses_AddNewExpenseAndVerify(String LineCode, String Amount, String Currency) {
		//Generate Current system date
		SimpleDateFormat formDate = new SimpleDateFormat("dd MMMM yyyy");
		String workDate = formDate.format(new Date());

		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_TitleHeader',[('idf_TitleHeaderName') : 'Expenses']), 10)

		String listValuesBefore = Mobile.getText(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		String listValuesBeforeSubstring = listValuesBefore.substring(listValuesBefore.indexOf("(") + 1, listValuesBefore.indexOf(")"));
		KeywordUtil.markPassed("No of List Values before adding a new contact " + listValuesBeforeSubstring + " ")

		Mobile.tap(findTestObject('Android/PG_WorkList/PG_Jobs_Expenses/dd_LineCode'), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : LineCode]), 0)

		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_WorkList/PG_Jobs_Expenses/tf_Amount'), Amount, 0)
		Thread.sleep(2000)

		Mobile.tap(findTestObject('Android/PG_WorkList/PG_Jobs_Expenses/dd_Currency'), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : Currency]), 0)

		Mobile.tap(findTestObject('Android/PG_WorkList/PG_Jobs_Expenses/tf_WorkDate'), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_WorkList/PG_Jobs_Expenses/ele_Day',[('idf_Day') : workDate]), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/btn_CommonByText',[('idf_ButtonName') : "OK"]), 0)

		Mobile.tap(findTestObject('Android/PG_Common/btn_Add'), 0)
		Thread.sleep(2000)

		SimpleDateFormat formDate_1 = new SimpleDateFormat("dd/MM/yyyy")
		String workDate_1 = formDate_1.format(new Date())
		KeywordUtil.markPassed("workDate_1 " + workDate_1 + " ")
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_TitleHeader',[('idf_TitleHeaderName') : "Expenses"]), 10)
		String listValuesAfter = Mobile.getText(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		String listValuesAfterSubstring = listValuesAfter.substring(listValuesAfter.indexOf("(") + 1, listValuesAfter.indexOf(")"));
		KeywordUtil.markPassed("No of List Values after adding a new contact " + listValuesAfterSubstring + " ")

		Boolean listvaluesresult = Mobile.verifyGreaterThan(listValuesAfterSubstring, listValuesBeforeSubstring)
		if (listvaluesresult) {
			KeywordUtil.markPassed("List values have increased to " + listValuesAfter + " ")
			Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]), 0)
			Thread.sleep(2000)
			Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : LineCode]), 10)
		}
		else {
			KeywordUtil.markFailed("List values hasn't increased")
		}
	}


	/**
	 * Enter Labor details without Amount for a job and Verify List count has not increased
	 * 
	 */
	@Keyword
	public void bf_Labor_AddLaborWithoutAmount(String lineCode) {
		Mobile.tap(findTestObject('Android/PG_WorkList/PG_Jobs_Labor/dd_LineCode'), 0)
		Thread.sleep(2000)

		Mobile.tap(findTestObject('Android/PG_WorkList/PG_Jobs_Labor/lbl_LineCodeItem', [('idf_CheckedTextName') : lineCode]), 0)

		//Generate Current system date
		SimpleDateFormat formDate = new SimpleDateFormat("dd MMMM yyyy");
		String workDate = formDate.format(new Date());

		//datepicker

		Mobile.tap(findTestObject('Android/PG_WorkList/PG_Jobs_Expenses/tf_WorkDate'), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_WorkList/PG_Jobs_Expenses/ele_Day',[('idf_Day') : workDate]), 0)

		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'OK' ]), 0)

		Mobile.tap(findTestObject('Android/PG_Common/btn_Add'), 0)
		Thread.sleep(500)

		def message = Mobile.getText(findTestObject('Android/PG_WorkList/lbl_ErrorMessageForRequiredFields'),10)
		KeywordUtil.markPassed("Error Message : "+message)
		Thread.sleep(5000)

		//Moving to LIST screen in order to close app
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'LIST']), 0)
	}


	/**
	 * Enter Labor details for a job and Verify List count has increased or not
	 */
	@Keyword
	public void bf_Labor_AddNewLaborAndVerify(String amount, String lineCode) {
		// Saving item count before adding element
		String listValuesBefore = Mobile.getText(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]), 5)
		String listValuesBeforeSubstring = listValuesBefore.substring(listValuesBefore.indexOf("(") + 1, listValuesBefore.indexOf(")"));

		KeywordUtil.markPassed("No of List Values before adding a new Labor " + listValuesBefore + " ")
		Thread.sleep(2000)

		Mobile.tap(findTestObject('Android/PG_WorkList/PG_Jobs_Labor/dd_LineCode'), 0)
		Thread.sleep(2000)

		Mobile.tap(findTestObject('Android/PG_WorkList/PG_Jobs_Labor/lbl_LineCodeItem', [('idf_CheckedTextName') : lineCode]), 0)

		Mobile.setText(findTestObject('Android/PG_WorkList/PG_Jobs_Labor/tf_Amount'), amount, 0)

		//Generate Current system date
		SimpleDateFormat formDate = new SimpleDateFormat("dd MMMM yyyy");
		String workDate = formDate.format(new Date());

		Mobile.tap(findTestObject('Android/PG_WorkList/PG_Jobs_Expenses/tf_WorkDate'), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_WorkList/PG_Jobs_Expenses/ele_Day',[('idf_Day') : workDate]), 0)

		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'OK' ]), 0)

		Mobile.tap(findTestObject('Android/PG_Common/btn_Add'), 0)
		Thread.sleep(2000)

		//Saving new item count after adding element
		String listValuesAfter = Mobile.getText(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]), 10)
		String listValuesAfterSubstring = listValuesAfter.substring(listValuesAfter.indexOf("(") + 1, listValuesAfter.indexOf(")"));
		KeywordUtil.markPassed("No of List Values before adding a new Labor " + listValuesAfter + " ")

		//checking if item count has decreased after deletion
		Boolean listvaluesresult = Mobile.verifyGreaterThan(listValuesAfterSubstring, listValuesBeforeSubstring)
		if (listvaluesresult) {
			KeywordUtil.markPassed("List values have increased to " + listValuesAfter + " ")
			Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]), 0)
			Thread.sleep(2000)
			Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : lineCode]), 10)
		}
		else {
			KeywordUtil.markFailed("List values hasn't increased")
		}

	}

	/**
	 * Modify Labor record
	 *
	 */
	@Keyword
	public void bf_Labor_ModifyRecordAndVerify(String lineCodeBeforeModify, String lineCodeAfterModify, String amountAfterModify) {
		//Generate Current system date
		SimpleDateFormat formDate = new SimpleDateFormat("dd MMMM yyyy");
		String workDate = formDate.format(new Date());

		Mobile.tap(findTestObject('Android/PG_WorkList/PG_Jobs_Labor/tf_LaborListItem', [('idf_LaborListItemName') : lineCodeBeforeModify]), 0)

		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "Modify", ('idf_ButtonNameCapital') : "MODIFY"]), 0)

		//Modifying data
		Mobile.tap(findTestObject('Android/PG_WorkList/PG_Jobs_Labor/dd_LineCode'), 0)

		Mobile.tap(findTestObject('Android/PG_WorkList/PG_Jobs_Labor/lbl_LineCodeItem', [('idf_CheckedTextName') : lineCodeAfterModify]), 0)

		Mobile.setText(findTestObject('Android/PG_WorkList/PG_Jobs_Labor/tf_Amount'), amountAfterModify, 0)

		//datepicker

		//		Mobile.tap(findTestObject('Android/PG_WorkList/PG_Jobs_Labor/tf_WorkDate'), 0)
		//
		//		Mobile.tap(findTestObject('Android/PG_WorkList/PG_Jobs_Labor/ele_Date', [('idf_Date') : workDate]), 0)
		//
		//		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'OK' ]), 0)

		//Saving update
		Mobile.tap(findTestObject('Android/PG_WorkList/btn_Update'), 0)

		Thread.sleep(2000)

		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonContainsByText',[('idf_LabelName') : amountAfterModify]), 5)

	}


	/**
	 * Modify Expenses record
	 *
	 */
	@Keyword
	public void bf_Expenses_ModifyRecordAndVerify(String LineCodeBeforeModify, String LineCodeAfterModify, String AmountAfterModify, String CurrencyAfterModify) {
		//Generate Current system date
		SimpleDateFormat formDate = new SimpleDateFormat("dd MMMM yyyy");
		String workDate = formDate.format(new Date());


		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable', [('idf_TextName') : LineCodeBeforeModify]), 0)

		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "Modify", ('idf_ButtonNameCapital') : "MODIFY"]), 0)

		//Modifying data
		Mobile.tap(findTestObject('Android/PG_WorkList/PG_Jobs_Expenses/dd_LineCode'), 0)
		Thread.sleep(2000)

		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : LineCodeAfterModify]), 0)
		Thread.sleep(2000)

		Mobile.setText(findTestObject('Android/PG_WorkList/PG_Jobs_Expenses/tf_Amount'), AmountAfterModify, 0)
		Thread.sleep(2000)

		Mobile.tap(findTestObject('Android/PG_WorkList/PG_Jobs_Expenses/dd_Currency'), 0)
		Thread.sleep(2000)

		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : CurrencyAfterModify]), 0)

		//Android detepicker should be updated
		//		Mobile.tap(findTestObject('Android/PG_WorkList/PG_Jobs_Expenses/tf_WorkDate'), 0)
		//		Thread.sleep(2000)
		//
		//		Mobile.tap(findTestObject('Android/PG_WorkList/PG_Jobs_Expenses/ele_Day',[('idf_Day') : workDate]), 0)
		//
		//		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/btn_CommonByText',[('idf_ButtonName') : "OK"]), 0)

		Mobile.tap(findTestObject('Android/PG_WorkList/btn_Update'), 0)
		Thread.sleep(2000)

		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : LineCodeAfterModify]), 5)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonContainsByText',[('idf_LabelName') : AmountAfterModify]), 5)
	}

	/**
	 * Enter Expense details without Amount for a job and Verify the Error Message
	 *
	 */
	@Keyword
	public void bf_Expense_AddExpenseWithoutAmount(String lineCode, String Currency) {
		Mobile.tap(findTestObject('Android/PG_WorkList/PG_Jobs_Labor/dd_LineCode'), 0)
		Thread.sleep(2000)

		Mobile.tap(findTestObject('Android/PG_WorkList/PG_Jobs_Labor/lbl_LineCodeItem', [('idf_CheckedTextName') : lineCode]), 0)

		Mobile.tap(findTestObject('Android/PG_WorkList/PG_Jobs_Expenses/dd_Currency'), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : Currency]), 0)

		//Generate Current system date
		SimpleDateFormat formDate = new SimpleDateFormat("dd MMMM yyyy");
		String workDate = formDate.format(new Date());

		Mobile.tap(findTestObject('Android/PG_WorkList/PG_Jobs_Expenses/tf_WorkDate'), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_WorkList/PG_Jobs_Expenses/ele_Day',[('idf_Day') : workDate]), 0)

		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'OK' ]), 0)

		Mobile.tap(findTestObject('Android/PG_Common/btn_Add'), 0)
		Thread.sleep(500)

		def message = Mobile.getText(findTestObject('Android/PG_WorkList/lbl_ErrorMessageForRequiredFields'),10)
		KeywordUtil.markPassed("Error Message : "+message)
		Thread.sleep(5000)

		//Moving to LIST screen in order to close app
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'LIST']), 0)
	}


	/**
	 * Add note and verify 
	 */
	@Keyword
	public void bf_Notes_AddNoteAndVerify(String textLineCodeValue, String textBlockValue, String text) {

		Mobile.tap(findTestObject('Android/PG_WorkList/PG_Jobs_Notes/btn_AddNote'), 0)

		Mobile.tap(findTestObject('Android/PG_WorkList/PG_Jobs_Notes/dd_TextLineCode'), 0)

		Mobile.tap(findTestObject('Android/PG_WorkList/PG_Jobs_Notes/lbl_TextLineCodeValue', [('idf_TextLineCodeValue') : textLineCodeValue]),0)

		Mobile.tap(findTestObject('Android/PG_WorkList/PG_Jobs_Notes/dd_TextBlock'), 0)

		Mobile.tap(findTestObject('Android/PG_WorkList/PG_Jobs_Notes/lbl_TextBlockValue', [('idf_TextBlockValue') : textBlockValue]),0)

		Mobile.setText(findTestObject('Android/PG_WorkList/PG_Jobs_Notes/tf_Text'), text, 0)

		Mobile.tap(findTestObject('Android/PG_WorkList/btn_Update'), 0)

		Mobile.verifyElementExist(findTestObject('Android/PG_WorkList/PG_Jobs_Notes/lbl_VerifyNote', [('idf_TextLineCodeValue') : textLineCodeValue, ('idf_TextValue') : text]), 0)

	}


	/**
	 * Edit the finally added note
	 */
	@Keyword
	public void bf_Notes_ModifyNoteAndVerify(String textLineCodeValue, String text) {

		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable', [('idf_TextName') : 'Solution']), 0)

		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'MODIFY']), 0)

		Mobile.tap(findTestObject('Android/PG_WorkList/PG_Jobs_Notes/dd_TextLineCode'), 0)

		Mobile.tap(findTestObject('Android/PG_WorkList/PG_Jobs_Notes/lbl_TextLineCodeValue', [('idf_TextLineCodeValue') : textLineCodeValue]),0)

		Mobile.setText(findTestObject('Android/PG_WorkList/PG_Jobs_Notes/tf_Text'), text, 0)

		Mobile.tap(findTestObject('Android/PG_WorkList/btn_Update'), 0)

		Mobile.verifyElementExist(findTestObject('Android/PG_WorkList/PG_Jobs_Notes/lbl_VerifyNote', [('idf_TextLineCodeValue') : textLineCodeValue, ('idf_TextValue') : text]), 0)
	}

	/**
	 * Add a part need and verify
	 */
	@Keyword
	public void bf_PartNeeds_AddPartAndVerify(String partId, String placeIdFrom, String locationFrom) {

		//Saving new item count before adding element
		String listValuesBefore = Mobile.getText(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		String listValuesBeforeSubstring = listValuesBefore.substring(listValuesBefore.indexOf("(") + 1, listValuesBefore.indexOf(")"));
		KeywordUtil.markPassed("No of List Values before adding a new contact " + listValuesBeforeSubstring + " ")

		Mobile.setText(findTestObject('Android/PG_WorkList/PG_PartNeeds/tf_PartID'), partId, 0)
		Mobile.setText(findTestObject('Android/PG_WorkList/PG_PartNeeds/tf_PlaceIDFrom'), placeIdFrom, 0)
		Mobile.setText(findTestObject('Android/PG_WorkList/PG_PartNeeds/tf_LocationFrom'), locationFrom, 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Add'), 0)
		Thread.sleep(2000)

		//Saving new item count after adding element
		String listValuesAfter = Mobile.getText(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]), 10)
		String listValuesAfterSubstring = listValuesAfter.substring(listValuesAfter.indexOf("(") + 1, listValuesAfter.indexOf(")"));
		KeywordUtil.markPassed("No of List Values before adding a new Labor " + listValuesAfter + " ")

		//checking if item count has decreased after deletion
		Boolean listvaluesresult = Mobile.verifyGreaterThan(listValuesAfterSubstring, listValuesBeforeSubstring)
		if (listvaluesresult) {
			KeywordUtil.markPassed("List values have increased to " + listValuesAfter + " ")
			Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		}
		else {
			KeywordUtil.markFailed("List values hasn't increased")
		}

	}

	/**
	 * Verifying validations for mandatory fields
	 */
	@Keyword
	public void bf_PartNeeds_AddPartWithoutMandatoryFields() {

		Mobile.tap(findTestObject('Android/PG_Common/btn_Add'), 0)
		Thread.sleep(500)

		def message = Mobile.getText(findTestObject('Android/PG_WorkList/lbl_ErrorMessageForRequiredFields'),10)
		KeywordUtil.markPassed("Error Message : "+message)
	}

	/**
	 * Modifying a part need record on mobile
	 *
	 */
	@Keyword
	public void bf_PartNeeds_ModifyPartAndVerify(String partIdAfterModify, String placeIdFromAfterModify, String locationFromAfterModify) {

		Mobile.tap(findTestObject('Android/PG_WorkList/lbl_FirstListOfRecordEntered'), 0)
		Thread.sleep(2000)

		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'MODIFY']), 0)
		Thread.sleep(2000)

		Mobile.setText(findTestObject('Android/PG_WorkList/PG_PartNeeds/tf_PartID'), partIdAfterModify, 0)

		Mobile.setText(findTestObject('Android/PG_WorkList/PG_PartNeeds/tf_PlaceIDFrom'), placeIdFromAfterModify, 0)

		Mobile.setText(findTestObject('Android/PG_WorkList/PG_PartNeeds/tf_LocationFrom'), locationFromAfterModify, 0)
		Thread.sleep(2000)

		Mobile.tap(findTestObject('Android/PG_WorkList/btn_Update'), 0)
		Thread.sleep(2000)


	}

	/**
	 * Verifying validations for stock location field in part needs
	 */
	@Keyword
	public void bf_PartNeeds_AddPartLocationFieldValidation(String partId, String placeIdFrom, String locationFrom) {

		Mobile.setText(findTestObject('Android/PG_WorkList/PG_PartNeeds/tf_PartID'), partId, 0)

		Mobile.setText(findTestObject('Android/PG_WorkList/PG_PartNeeds/tf_PlaceIDFrom'), placeIdFrom, 0)

		Mobile.setText(findTestObject('Android/PG_WorkList/PG_PartNeeds/tf_LocationFrom'), locationFrom, 0)

		Mobile.tap(findTestObject('Android/PG_Common/btn_Add'), 0)

		for(int i=0; i<5; i++) {
			boolean flag = Mobile.waitForElementPresent(findTestObject('Android/PG_Common/lbl_SyncErrorPopUp'), 5)
			if(flag) {
				break;
			}else {
				Thread.sleep(15000)
				Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
				Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Part Needs']), 0)
			}
		}

		def message = Mobile.getText(findTestObject('Android/PG_Common/lbl_SyncErrorPopUp'),10)
		KeywordUtil.markPassed("Error Message : "+message)

		Mobile.tap(findTestObject('Android/PG_Common/lbl_SyncErrorPopUp'), 0)

		Thread.sleep(4000)
		//Mobile.longPress(findTestObject('Android/PG_Common/lbl_SyncErrorMessage'), 5)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_SyncErrorMessage'), 0)

		int elementStartingPoint = Mobile.getElementLeftPosition(findTestObject('Android/PG_Common/lbl_SyncErrorMessage'), 0)
		int elementWidth = Mobile.getElementWidth(findTestObject('Android/PG_Common/lbl_SyncErrorMessage'), 0)

		int x = elementStartingPoint + elementWidth - 15
		int y = Mobile.getElementTopPosition(findTestObject('Android/PG_Common/lbl_SyncErrorMessage'), 0)

		//Mobile.tapAtPosition(x, y)
		Mobile.tapAndHoldAtPosition(x, y, 10)
		//Mobile.tapAndHold(findTestObject('Android/PG_Common/lbl_SyncErrorMessage'),5, 5)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Delete']), 0)
		Thread.sleep(2000)

		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']), 0)
		Thread.sleep(2000)
	}

	/**
	 * Adding parts from my stock to the list
	 */
	@Keyword
	public void bf_PartsUsed_AddPartsFromMyStock(String serialTrackedPartId, String nonTrackedPartId, String lotTrackedPartId) {
		//Selecting serial tracked part
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonContainsByText', [('idf_LabelName') : 'Parts from my stock']), 0)

		Mobile.setText(findTestObject('Android/PG_Common/tf_SearchBar'), serialTrackedPartId, 2)

		Mobile.tap(findTestObject('Android/PG_WorkList/lbl_FirstRecordCommonByText', [('idf_LabelName') : serialTrackedPartId]),
		0)

		Mobile.checkElement(findTestObject('Android/PG_WorkList/PG_PartsUsed/chk_FirstCheckBoxFromList'), 0)

		Mobile.tap(findTestObject('Android/PG_WorkList/PG_PartsUsed/btn_AddSelectedCheckBoxes'), 2)

		//Selecting non tracked part
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonContainsByText', [('idf_LabelName') : 'Parts from my stock']), 0)

		Mobile.setText(findTestObject('Android/PG_Common/tf_SearchBar'), nonTrackedPartId, 2)

		Mobile.tap(findTestObject('Android/PG_WorkList/lbl_FirstRecordCommonByText', [('idf_LabelName') : nonTrackedPartId]),
		2)

		Mobile.setText(findTestObject('Android/PG_WorkList/PG_PartsUsed/tf_NonTrackedPartQuantity'), '1', 0)

		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple', [('idf_ButtonNameSimple') : 'Add', ('idf_ButtonNameCapital') : 'ADD']),
		2)

		//Selecting lot tracked part
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonContainsByText', [('idf_LabelName') : 'Parts from my stock']), 0)

		Mobile.setText(findTestObject('Android/PG_Common/tf_SearchBar'), lotTrackedPartId, 2)

		Mobile.tap(findTestObject('Android/PG_WorkList/lbl_FirstRecordCommonByText', [('idf_LabelName') : lotTrackedPartId]),
		2)

		Mobile.tap(findTestObject('Android/PG_WorkList/PG_PartsUsed/chk_FirstCheckBoxFromList'), 2)

		Mobile.tap(findTestObject('Android/PG_WorkList/PG_PartsUsed/btn_Tick'), 0)

		Mobile.setText(findTestObject('Android/PG_WorkList/PG_PartsUsed/tf_LotTrackedPartQuantity'), '1', 0)

		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple', [('idf_ButtonNameSimple') : 'Add', ('idf_ButtonNameCapital') : 'ADD']),
		2)

		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
	}

	/**
	 * Adding part from parts ordered
	 */
	@Keyword
	public void bf_PartsUsed_AddPartsFromPartsOrdered(String serialTrackedPartId, String nonTrackedPartId, String lotTrackedPartId) {
		//Serial part Id
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonContainsByText', [('idf_LabelName') : 'Parts ordered']), 0)

		Mobile.setText(findTestObject('Android/PG_Common/tf_SearchBar'), serialTrackedPartId, 2)

		Mobile.tap(findTestObject('Android/PG_WorkList/lbl_FirstRecordCommonByText', [('idf_LabelName') : serialTrackedPartId]),
		0)

		//non serial part id
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonContainsByText', [('idf_LabelName') : 'Parts ordered']), 0)

		Mobile.setText(findTestObject('Android/PG_Common/tf_SearchBar'), nonTrackedPartId, 2)

		Mobile.tap(findTestObject('Android/PG_WorkList/lbl_FirstRecordCommonByText', [('idf_LabelName') : nonTrackedPartId]),
		2)

		//lot part id
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonContainsByText', [('idf_LabelName') : 'Parts ordered']), 0)

		Mobile.setText(findTestObject('Android/PG_Common/tf_SearchBar'), lotTrackedPartId, 2)

		Mobile.tap(findTestObject('Android/PG_WorkList/lbl_FirstRecordCommonByText', [('idf_LabelName') : lotTrackedPartId]),
		2)

		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
	}

	/**
	 * Adding parts from miscellaneous parts 
	 */
	@Keyword
	public void bf_PartsUsed_AddPartsFromMiscellaneousParts(String lineCode, String placeID, String location, String serialTrackedPartId, String nonTrackedPartId, String lotTrackedPartId, String controlledPartID, String code, String serialID) {
		//Serial part Id
		String beforeListValue = returnListValue();

		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonContainsByText', [('idf_LabelName') : 'Miscellaneous parts']), 0)

		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Line Code']), 0)

		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByTextDropDownItem', [('idf_LabelName') : lineCode]), 0)

		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Part ID']), serialTrackedPartId, 0)

		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Place ID']), placeID, 0)

		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Location']), location, 0)

		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Thread.sleep(2000)

		Mobile.tap(findTestObject('Android/PG_Common/lbl_FirstItemFromListWithSeperator'), 0)
		Thread.sleep(2000)

		String afterListValue = returnListValue();
		Mobile.verifyGreaterThan(afterListValue, beforeListValue)

		//non serial part id
		String beforeListValue_1 = returnListValue();

		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonContainsByText', [('idf_LabelName') : 'Miscellaneous parts']), 0)

		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Line Code']), 0)

		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByTextDropDownItem', [('idf_LabelName') : lineCode]), 0)

		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Part ID']), nonTrackedPartId, 0)

		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Place ID']), placeID, 0)

		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Location']), location, 0)

		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Thread.sleep(2000)

		String afterListValue_1 = returnListValue();
		Mobile.verifyGreaterThan(afterListValue_1, beforeListValue_1)

		//lot part id
		String beforeListValue_2 = returnListValue();

		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonContainsByText', [('idf_LabelName') : 'Miscellaneous parts']), 0)

		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Line Code']), 0)

		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByTextDropDownItem', [('idf_LabelName') : lineCode]), 0)

		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Part ID']), lotTrackedPartId, 0)

		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Place ID']), placeID, 0)

		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Location']), location, 0)

		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Thread.sleep(2000)

		Mobile.tap(findTestObject('Android/PG_Common/lbl_FirstItemFromListWithSeperator'), 0)
		Thread.sleep(2000)

		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Thread.sleep(2000)

		String afterListValue_2 = returnListValue();
		Mobile.verifyGreaterThan(afterListValue_2, beforeListValue_2)

		//controlled part id
		String beforeListValue_3 = returnListValue();

		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonContainsByText', [('idf_LabelName') : 'Miscellaneous parts']), 0)

		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Line Code']), 0)

		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByTextDropDownItem', [('idf_LabelName') : lineCode]), 0)

		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Part ID']), controlledPartID, 0)

		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Place ID']), placeID, 0)

		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Location']), location, 0)

		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Thread.sleep(2000)

		Mobile.tap(findTestObject('Android/PG_Common/lbl_FirstItemFromListWithSeperator'), 0)
		Thread.sleep(2000)

		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Code']), 0)

		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByTextDropDownItem', [('idf_LabelName') : code]), 0)

		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Serial ID']), serialID, 0)

		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Thread.sleep(2000)

		String afterListValue_3 = returnListValue();
		Mobile.verifyGreaterThan(afterListValue_3, beforeListValue_3)

		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
	}

	/**
	 * Adding meter readings 
	 */
	@Keyword
	public void bf_MeterReadings_AddReading(String meterName, String meterValue) {
		String beforeListValue = returnListValue();

		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Meter Name']), 3)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByTextDropDownItem', [('idf_LabelName') : meterName]), 3)

		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Meter Value']), meterValue, 3)

		Mobile.tap(findTestObject('Android/PG_Common/btn_Add'), 0)
		Thread.sleep(2000)

		if(Mobile.verifyElementNotExist(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Meters']), 5)) {
			String afterListValue = returnListValue();
			Mobile.verifyGreaterThan(afterListValue, beforeListValue)

			Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Meter Name']), 3)
			Mobile.verifyElementNotExist(findTestObject('Android/PG_Common/lbl_CommonByTextDropDownItem', [('idf_LabelName') : meterName]), 3)
			Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByTextDropDownItem', [('idf_LabelName') : '']), 3)
		}
		Thread.sleep(5000)
	}


	/**
	 * Modifying a meter reading
	 */
	@Keyword
	public void bf_MeterReadings_ModifyReading(String meterName, String meterValue) {
		String beforeListValue = returnListValue();

		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		Mobile.tap(findTestObject('Android/PG_WorkList/lbl_FirstListOfRecordEntered'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "Modify", ('idf_ButtonNameCapital') : "MODIFY"]),5)
		Thread.sleep(2000)

		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Meter Name']), 3)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByTextDropDownItem', [('idf_LabelName') : meterName]), 3)

		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Meter Value']), meterValue, 3)

		Mobile.tap(findTestObject('Android/PG_WorkList/btn_Update'), 0)
		Thread.sleep(2000)

		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Meter Readings']), 0)
		Thread.sleep(3000)

		String afterListValue = returnListValue();
		Mobile.verifyEqual(afterListValue, beforeListValue)

		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Meter Name']), 3)
		Mobile.verifyElementNotExist(findTestObject('Android/PG_Common/lbl_CommonByTextDropDownItem', [('idf_LabelName') : meterName]), 3)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByTextDropDownItem', [('idf_LabelName') : '']), 3)

		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
	}

	/**
	 * Complete a task status without adding meter readings and validate
	 */
	@Keyword
	public void bf_MeterReadings_ValidateReadingsRequired(String meterName, String meterValue) {
		Mobile.tap(findTestObject('Android/PG_WorkList/btn_TaskStatus'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Completed']), 0)
		Thread.sleep(500)

		def message = Mobile.getText(findTestObject('Android/PG_WorkList/lbl_ErrorMessageForRequiredFields'),10)
		KeywordUtil.markPassed("Error Message : "+ message)
		Thread.sleep(5000)

		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Meter Readings']), 0)
		Thread.sleep(3000)

		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Meter Name']), 3)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByTextDropDownItem', [('idf_LabelName') : meterName]), 3)

		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Meter Value']), meterValue, 3)

		Mobile.tap(findTestObject('Android/PG_Common/btn_Add'), 0)
		Thread.sleep(3000)

		Mobile.tap(findTestObject('Android/PG_WorkList/btn_TaskStatus'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Completed']), 0)
		Thread.sleep(3000)

		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Work List']), 5)
	}

	/**
	 *  Validations for meters with the count type Increasing, Positive Change are verified by this method
	 */
	@Keyword
	public void bf_MeterReadings_ValidateMeterName(String meterName, String meterValue) {
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Meter Name']), 3)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByTextDropDownItem', [('idf_LabelName') : meterName]), 3)

		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Meter Value']), meterValue, 3)

		Mobile.tap(findTestObject('Android/PG_Common/btn_Add'), 0)
		Thread.sleep(500)

		def message = Mobile.getText(findTestObject('Android/PG_WorkList/lbl_ErrorMessageForRequiredFields'),10)
		KeywordUtil.markPassed("Error Message : "+ message)
		Thread.sleep(5000)
	}

	/**
	 *  Add a cash payment
	 */
	@Keyword
	public void bf_Payment_AddCashPayment(String paymentMethod, String amount, String currency) {
		String beforeListValue = returnListValue();
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Payment Method']), 3)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByTextDropDownItem', [('idf_LabelName') : paymentMethod]), 3)

		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Amount']), amount, 3)

		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Currency']), 3)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByTextDropDownItem', [('idf_LabelName') : currency]), 3)

		Mobile.tap(findTestObject('Android/PG_Common/btn_Add'), 0)
		Thread.sleep(2000)

		String afterListValue = returnListValue();
		Mobile.verifyGreaterThan(afterListValue, beforeListValue)

		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
	}

	/**
	 *  Add a check payment
	 */
	@Keyword
	public void bf_Payment_AddCheckPayment(String paymentMethod, String amount, String currency, String reference) {
		String beforeListValue = returnListValue();
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Payment Method']), 3)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByTextDropDownItem', [('idf_LabelName') : paymentMethod]), 3)

		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Amount']), amount, 3)

		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Currency']), 3)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByTextDropDownItem', [('idf_LabelName') : currency]), 3)

		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Reference']), reference, 3)

		Mobile.tap(findTestObject('Android/PG_Common/btn_Add'), 0)
		Thread.sleep(2000)

		String afterListValue = returnListValue();
		Mobile.verifyGreaterThan(afterListValue, beforeListValue)

		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
	}

	/**
	 *  Add a card payment
	 */
	@Keyword
	public void bf_Payment_AddCardPayment(String paymentMethod, String amount, String currency, String cardType, String name, String cardNumber, String cardVerification, String month, String year) {
		String beforeListValue = returnListValue();
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Payment Method']), 3)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByTextDropDownItem', [('idf_LabelName') : paymentMethod]), 3)

		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Amount']), amount, 3)

		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Currency']), 3)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByTextDropDownItem', [('idf_LabelName') : currency]), 3)

		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Card Type']), 3)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByTextDropDownItem', [('idf_LabelName') : cardType]), 3)

		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Name']), name, 3)

		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Card Number']), cardNumber, 3)

		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Card Verification Number']), cardVerification, 3)

		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Month']), 3)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByTextDropDownItem', [('idf_LabelName') : month]), 3)

		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Year']), 3)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByTextDropDownItem', [('idf_LabelName') : year]), 3)

		Mobile.tap(findTestObject('Android/PG_Common/btn_Add'), 0)
		Thread.sleep(30000)

		String afterListValue = returnListValue();
		Mobile.verifyGreaterThan(afterListValue, beforeListValue)
	}

	/**
	 *  Add a invalid card number for card payment
	 */
	@Keyword
	public void bf_Payment_ValidateCardPayment(String paymentMethod, String amount, String currency, String cardType, String name, String cardNumber, String cardVerification, String month, String year) {
		String beforeListValue = returnListValue();
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Payment Method']), 3)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByTextDropDownItem', [('idf_LabelName') : paymentMethod]), 3)

		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Amount']), amount, 3)

		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Currency']), 3)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByTextDropDownItem', [('idf_LabelName') : currency]), 3)

		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Card Type']), 3)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByTextDropDownItem', [('idf_LabelName') : cardType]), 3)

		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Name']), name, 3)

		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Card Number']), cardNumber, 3)

		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Card Verification Number']), cardVerification, 3)

		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Month']), 3)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByTextDropDownItem', [('idf_LabelName') : month]), 3)

		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Year']), 3)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByTextDropDownItem', [('idf_LabelName') : year]), 3)

		Mobile.tap(findTestObject('Android/PG_Common/btn_Add'), 0)
		Thread.sleep(500)

		def message = Mobile.getText(findTestObject('Android/PG_WorkList/lbl_ErrorMessageForRequiredFields'),10)
		KeywordUtil.markPassed("Error Message : "+ message)
		Thread.sleep(50000)

		String afterListValue = returnListValue();
		Mobile.verifyEqual(afterListValue, beforeListValue)
	}

	/**
	 *  Validate outstanding payment
	 */
	@Keyword
	public void bf_Payment_ValidateOutstandingValue(String amount) {
		double outstandingAmount = Double.parseDouble(amount) + Double.parseDouble(amount);
		String text = Mobile.getText(findTestObject('Android/PG_Common/lbl_CommonByUpperLabelName', [('idf_LabelName') : 'Outstanding']), 0)

		if(text.contentEquals('NA')) {
			KeywordUtil.markPassed("Outstanding value : " + text)
		}else {
			String textSubstring = text.substring(4);
			Mobile.verifyEqual(textSubstring, textSubstring)
		}
	}

	/**
	 *  Add a Quality Code
	 */

	@Keyword
	public void bf_QualityCodes_AddNewQuality(String symptom,String problem,String resolution,String text) {
		String beforeListValue = returnListValue();
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_TitleHeader',[('idf_TitleHeaderName') : 'Quality']), 10)

		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Symptom']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : symptom]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Problem']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : problem]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Resolution']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : resolution]), 0)
		Thread.sleep(2000)

		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Text']), text, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Add'), 0)
		Thread.sleep(2000)

		String afterListValue = returnListValue();
		Mobile.verifyGreaterThan(afterListValue, beforeListValue)


	}

	/**
	 *  Modify a Quality Code
	 */

	@Keyword
	public void bf_QualityCodes_ModifyRecordAndVerify(String symptomAfterModify,String problemAfterModify, String resolutionAfterModify, String textAfterModify) {
		String beforeListValue = returnListValue();
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)

		Mobile.tap(findTestObject('Android/PG_WorkList/lbl_FirstListOfRecordEntered'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "Modify", ('idf_ButtonNameCapital') : "MODIFY"]),5)


		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Symptom']), 3)
		Thread.sleep(2000)

		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : symptomAfterModify]), 0)
		Thread.sleep(2000)

		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Problem']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : problemAfterModify]), 0)
		Thread.sleep(2000)

		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Resolution']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : resolutionAfterModify]), 0)
		Thread.sleep(2000)

		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Text']), textAfterModify, 3)
		Thread.sleep(2000)

		Mobile.tap(findTestObject('Android/PG_WorkList/btn_Update'), 0)
		Thread.sleep(2000)

		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Quality']), 0)
		Thread.sleep(3000)


		String afterListValue = returnListValue();
		Mobile.verifyEqual(afterListValue, beforeListValue)

		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
	}

	/**
	 * TA-debrief overview- Modify available details
	 */

	@Keyword
	public void bf_DebriefOverviewModifyAvailableDetails(String taskType,String priority,String duration,String description) {
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Task Type']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : taskType]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Priority']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : priority]), 0)
		Thread.sleep(2000)

		tapLookUpIcon("Plan Start")
		Mobile.tap(findTestObject('Android/PG_DebriefOverview/btn_date'), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_DebriefOverview/btn_Time'), 0)
		SetTime("3","30","pm")
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Duration']), duration, 3)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Description']), description, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_WorkList/btn_Update'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Mobile.pressBack()
		Mobile.pressBack()
	}

	/**
	 * TA-debrief overview-Verify full address
	 */

	@Keyword
	public void bf_DebriefOverviewVerifyFullAddress() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : '505 Wells St Delafield WI 53018']), 0)
		Mobile.pressBack()
	}

	/**
	 * TA-debrief overview-Verify navigation from DebriefOverview
	 */

	@Keyword
	public void bf_DebriefOverviewVerifyNavigationFromDebriefOverview() {
		//Mobile.tap(findTestObject('Android/PG_DebriefOverview/tf_Address'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'TA-CUSTOMER PLACE1']), 0)
		Mobile.pressBack()
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : '34153']), 0)
		Mobile.pressBack()
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : '0000000001']), 0)
		Thread.sleep(2000)
		Mobile.pressBack()
		Mobile.pressBack()
		Mobile.pressBack()
		//Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'TA-MODEL1']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'TA-MODEL1']), 0)
		Mobile.pressBack()
		Mobile.pressBack()
	}




	/**
	 * TA-debrief overview-Verify the behavior of DebriefOverview map
	 */

	@Keyword
	public void bf_DebriefOverviewVerifyBehaviorOfDebriefOverviewmap() {
		Mobile.tap(findTestObject('Android/PG_DebriefOverview/btn_GetDirections'), 0)
	}




}
