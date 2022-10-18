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

import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable
import java.text.SimpleDateFormat
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

public class LIB_Schedule {
	// This method scroll and click on any element in a picker wheel
	public void clickElementFromPickerWheel(String labelName) {
		TestObject pickerWheel = new TestObject();
		pickerWheel.addProperty("xpath", ConditionType.EQUALS, "//XCUIElementTypePickerWheel[@index='0']");
		MobileBuiltInKeywords.sendKeys(pickerWheel, labelName);
	}

	/**
	 * Select a Task
	 */
	@Keyword
	public void bf_SelectTask(String taskStatus) {
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_DropDownIcon'), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : taskStatus]), 0, 0)
	}


	/**
	 * Add a new request
	 */
	@Keyword
	public void bf_Request_AddNewRequest(String placeID, String priorityNo, String description) {
		int x = Mobile.getElementLeftPosition(findTestObject('iOS/PG_Schedule/PG_Request/tf_PlaceID'), 0)
		int width = Mobile.getElementWidth(findTestObject('iOS/PG_Schedule/PG_Request/tf_PlaceID'), 0)
		int y = Mobile.getElementTopPosition(findTestObject('iOS/PG_Schedule/PG_Request/tf_PlaceID'), 0)

		int xNew = ((x/2) + width) - 5;
		int yNew = y + 5;
		KeywordUtil.markPassed("x point" + xNew + " ")
		KeywordUtil.markPassed("y point" + yNew + " ")

		Mobile.tapAtPosition(xNew, yNew )
		Thread.sleep(3000)

		Mobile.setText(findTestObject('iOS/PG_Common/tf_Search'), placeID, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_FirstElementFromList', [('idf_LabelName') : placeID]), 0, 0)

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'PO #']), priorityNo, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tv_CommonByUpperLabelName', [('idf_LabelName') : 'Description']), 0, 0)

		Mobile.setText(findTestObject('iOS/PG_Common/tv_CommonByUpperLabelName', [('idf_LabelName') : 'Description']), description,0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Request']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0, 0)

		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Contacts']), 0)
	}


	/**
	 * Add a new request without required fields
	 */
	@Keyword
	public void bf_Request_ValidateFields() {
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0, 0)
		Thread.sleep(500)

		Mobile.takeScreenshot('Screenshots/Schedule_Request_MustFillRequiredFields.png', FailureHandling.STOP_ON_FAILURE)

		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Request']), 0)
	}


	/**
	 * Add a new contact
	 */
	@Keyword
	public void bf_Contacts_AddContact(String firstName, String lastName, String phone, String email) {
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Contacts']), 0)

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'First Name']), firstName, 0)

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Last Name']), lastName, 0)

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Phone']), phone, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Contacts']), 0, 0)

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Email']), email, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Contacts']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0, 0)

		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Products']), 0)
	}



	/**
	 * Add a new contact by first name using lookup icon
	 */
	@Keyword
	public void bf_Contacts_AddContactFromPlaceContacts(String contactSeq) {
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Contacts']), 0)

		int x = Mobile.getElementLeftPosition(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'First Name']), 0)
		int width = Mobile.getElementWidth(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'First Name']), 0)
		int y = Mobile.getElementTopPosition(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'First Name']), 0)

		int xNew = ((x/2) + width) - 5;
		int yNew = y + 5;
		KeywordUtil.markPassed("x point" + xNew + " ")
		KeywordUtil.markPassed("y point" + yNew + " ")

		Mobile.tapAtPosition(xNew, yNew )
		Thread.sleep(5000)

		Mobile.setText(findTestObject('iOS/PG_Common/tf_Search'), contactSeq, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_FirstElementFromList', [('idf_LabelName') : contactSeq]), 0, 0)
		Thread.sleep(3000)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']), 0, 0)

		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Contacts']), 0)
	}


	/**
	 * Add same contact twice by first name using lookup icon and validate
	 */
	@Keyword
	public void bf_Contacts_DuplicateContactsValidate() {
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_HamburgerMenu'), 0, 0)

		for(int i=0; i<10; i++) {
			boolean flag = Mobile.waitForElementPresent(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Sync Error']), 5)
			if(flag) {
				break;
			}else {
				Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_HamburgerMenu'), 0, 0)
				Thread.sleep(15000)
				Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_HamburgerMenu'), 0, 0)
			}
		}

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Sync Error']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Schedule/PG_Contacts/lbl_ScheduleContactsSyncError'), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Delete']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Yes']), 0, 0)
	}


	/**
	 * Add contact without mandatory fields
	 */
	@Keyword
	public void bf_Contacts_ValidateMandatoryFields(String firstName, String lastName, String phone) {
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Contacts']), 0)

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'First Name']), firstName, 0)

		Mobile.hideKeyboard()

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Last Name']), lastName, 0)

		Mobile.hideKeyboard()

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Phone']), phone, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Contacts']), 0 , 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']), 0, 0)

		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_ErrorMessage'), 5)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'OK']), 0, 0)
	}


	/**
	 * Add note and continue in schedule workflow
	 */
	@Keyword
	public void bf_Notes_AddNote(String textLineCode, String textBlock) {
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0, 0)

		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Notes']), 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Text Line Code']), 0, 0)
		clickElementFromPickerWheel(textLineCode)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Text Block']), 0, 0)
		clickElementFromPickerWheel(textBlock)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0, 0)

		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Task']), 0)
	}

	/**
	 * Add task from schedule workflow
	 */
	@Keyword
	public void bf_Task_AddTask(String duration) {
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0, 0)
		Thread.sleep(3000)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0, 0)
		Thread.sleep(3000)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0, 0)
		Thread.sleep(3000)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0, 0)
		Thread.sleep(3000)

		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Task']), 0)

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Duration']), duration, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0, 0)
	}

	/**
	 * Add task from schedule workflow without mandatory fields
	 */
	@Keyword
	public void bf_Task_ValidateMandatoryFields() {
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0, 0)
		Thread.sleep(3000)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0, 0)
		Thread.sleep(3000)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0, 0)
		Thread.sleep(3000)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0, 0)
		Thread.sleep(3000)

		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Task']), 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0, 0)

		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_ErrorMessage'), 5)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'OK']), 0, 0)
	}
}
