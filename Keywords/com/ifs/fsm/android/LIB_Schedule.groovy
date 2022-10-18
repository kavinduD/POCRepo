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

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import io.appium.java_client.AppiumDriver as AppiumDriver
import org.openqa.selenium.remote.RemoteWebDriver;

import com.kms.katalon.core.configuration.RunConfiguration

import com.kms.katalon.core.util.KeywordUtil

public class LIB_Schedule {
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
	 * Select a Task
	 */
	@Keyword
	public void bf_SelectTask(String taskStatus) {
		//Mobile.tapAndHold(findTestObject('Android/PG_Common/lbl_TextScrollable', [('idf_TextName') : taskStatus]), 5,5)

		Mobile.longPress(findTestObject('Android/PG_Common/lbl_TextScrollable', [('idf_TextName') : taskStatus]), 3)

		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonContainsByText', [('idf_LabelName') : "Schedule Job"]), 0)
	}

	/**
	 *Add a new request
	 */
	@Keyword
	public void bf_Request_AddNewRequest(String placeID) {
		Mobile.setText(findTestObject('Android/PG_Schedule/PG_Request/tf_PlaceID'), placeID,
				0)

		Mobile.setText(findTestObject('Android/PG_Schedule/PG_Request/tf_PO'), '01', 0)

		Mobile.setText(findTestObject('Android/PG_Schedule/PG_Request/tf_Description'), 'TEST DESC', 0)

		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)

		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Contacts']), 5)
	}

	/**
	 * 
	 */
	@Keyword
	public void bf_Request_ValidateFields() {
		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)

		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_ErrorMessageForRequiredFields'), 0)
	}

	/**
	 * 
	 */
	@Keyword
	public void bf_Contacts_AddContact() {
		Mobile.setText(findTestObject('Android/PG_Schedule/PG_Contacts/tf_FirstName'), 'TestFirstName', 0)

		Mobile.setText(findTestObject('Android/PG_Schedule/PG_Contacts/tf_LastName'), 'TestLastName', 0)

		Mobile.setText(findTestObject('Android/PG_Schedule/PG_Contacts/tf_Phone'), '01111111111', 0)

		Mobile.setText(findTestObject('Android/PG_Schedule/PG_Contacts/tf_Email'), 'testEmail@gmail.com', 0)

		Mobile.tap(findTestObject('Android/PG_Common/btn_Add'), 0)

		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Contacts']), 0)

		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)

		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Products']), 0)
	}

	/**
	 * 
	 */
	@Keyword
	public void bf_Contacts_AddContactFromPlaceContacts(String contactSeq) {
		int elementStartingPoint = Mobile.getElementLeftPosition(findTestObject('Android/PG_Schedule/PG_Contacts/tf_FirstName'), 0)
		int elementWidth = Mobile.getElementWidth(findTestObject('Android/PG_Schedule/PG_Contacts/tf_FirstName'), 0)

		int x = elementStartingPoint + elementWidth - 5
		int y = Mobile.getElementTopPosition(findTestObject('Android/PG_Schedule/PG_Contacts/tf_FirstName'), 0)

		Mobile.tapAtPosition(x, y)

		//		Mobile.setText(findTestObject('Android/PG_Common/tf_SearchBar'), contactSeq, 0)
		//
		//		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable', [('idf_TextName') : contactSeq]),0)

		Mobile.tap(findTestObject('Android/PG_Common/lbl_FirstItemFromListWithSeperator'), 0)

		Mobile.tap(findTestObject('Android/PG_WorkList/btn_Save'), 0)

		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Contacts']), 0)
		Thread.sleep(5000)

	}

	/**
	 * 
	 */
	@Keyword
	public void bf_Contacts_DuplicateContactsValidate() {
		int elementStartingPoint = Mobile.getElementLeftPosition(findTestObject('Android/PG_Schedule/PG_Contacts/tf_FirstName'), 0)
		int elementWidth = Mobile.getElementWidth(findTestObject('Android/PG_Schedule/PG_Contacts/tf_FirstName'), 0)

		int x = elementStartingPoint + elementWidth - 5
		int y = Mobile.getElementTopPosition(findTestObject('Android/PG_Schedule/PG_Contacts/tf_FirstName'), 0)

		for(int i=0; i<5; i++) {
			boolean flag = Mobile.waitForElementPresent(findTestObject('Android/PG_Common/lbl_SyncErrorPopUp'), 5)
			if(flag) {
				break;
			}else {
				Thread.sleep(15000)
				Mobile.tapAtPosition(x, y)
				Mobile.pressBack()
			}
		}
		Mobile.tap(findTestObject('Android/PG_Common/lbl_SyncErrorPopUp'), 0)
		Thread.sleep(2000)

		Thread.sleep(2000)
		Mobile.tapAndHold(findTestObject('Android/PG_Common/lbl_SyncErrorMessage'), 5,5)
		Thread.sleep(5000)

		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Delete']), 0)
		Thread.sleep(2000)

		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']), 0)
	}

	/**
	 * 
	 */
	@Keyword
	public void bf_Contacts_ValidateMandatoryFields() {
		Mobile.setText(findTestObject('Android/PG_Schedule/PG_Contacts/tf_FirstName'), 'TestFirstName', 0)

		Mobile.setText(findTestObject('Android/PG_Schedule/PG_Contacts/tf_LastName'), 'TestLastName', 0)

		Mobile.setText(findTestObject('Android/PG_Schedule/PG_Contacts/tf_Phone'), '01111111111', 0)

		Mobile.tap(findTestObject('Android/PG_Common/btn_Add'), 0)

		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_ErrorMessageForRequiredFields'), 0)
	}

	/**
	 * Add note and continue in schedule workflow
	 */
	@Keyword
	public void bf_Notes_AddNote(String textLineCode, String textBlock, String text) {
		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Thread.sleep(2000)

		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Thread.sleep(2000)

		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Thread.sleep(2000)

		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Notes']), 0)

		Mobile.tap(findTestObject('Android/PG_Schedule/PG_Notes/dd_TextLineCode'), 0)

		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable', [('idf_TextName') : textLineCode]),
		0)

		Mobile.setText(findTestObject('Android/PG_Schedule/PG_Notes/tf_Text'), text, 0)

		Mobile.tap(findTestObject('Android/PG_Common/btn_Add'), 0)
		Thread.sleep(2000)

		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Notes']), 0)

		Mobile.tap(findTestObject('Android/PG_Schedule/PG_Notes/dd_TextBlock'), 0)

		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable', [('idf_TextName') : textBlock]),
		0)

		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Thread.sleep(2000)

		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Task']), 0)
	}

	/**
	 * Add task from schedule workflow
	 */
	@Keyword
	public void bf_Task_AddTask(String duration) {
		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Thread.sleep(2000)

		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Thread.sleep(2000)

		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Thread.sleep(2000)

		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Thread.sleep(2000)

		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Task']), 0)

		Mobile.setText(findTestObject('Android/PG_Schedule/PG_Task/tf_Duration'), duration, 0)
		Thread.sleep(2000)

		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Thread.sleep(2000)

		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Work List']), 0)
	}

	/**
	 * Add task from schedule workflow without mandatory fields
	 */
	@Keyword
	public void bf_Task_ValidateMandatoryFields() {
		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Thread.sleep(2000)

		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Thread.sleep(2000)

		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Thread.sleep(2000)

		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Thread.sleep(2000)

		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Task']), 0)

		Thread.sleep(2000)

		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)

		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_ErrorMessageForRequiredFields'), 0)
	}

	/**
	 * Add part need from schedule workflow
	 */
	@Keyword
	public void bf_PartNeeds_AddPart(String placeID, String location, String serialTrackedPartID, String nonTrackedPartID, String lotTrackedPartID, String serialLotTrackedPartID) {
		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Thread.sleep(2000)

		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Thread.sleep(2000)

		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Part Needs']), 0)

		//Serial part Id
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Part ID']), serialTrackedPartID, 0)

		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Place ID']), placeID, 0)

		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Location']), location, 0)

		Mobile.tap(findTestObject('Android/PG_Common/btn_Add'), 0)
		Thread.sleep(2000)

		//non serial part id
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Part ID']), nonTrackedPartID, 0)

		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Place ID']), placeID, 0)

		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Location']), location, 0)

		Mobile.tap(findTestObject('Android/PG_Common/btn_Add'), 0)
		Thread.sleep(2000)

		//lot part id
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Part ID']), lotTrackedPartID, 0)

		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Place ID']), placeID, 0)

		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Location']), location, 0)

		Mobile.tap(findTestObject('Android/PG_Common/btn_Add'), 0)
		Thread.sleep(2000)

		//serial lot tracked part id
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Part ID']), serialLotTrackedPartID, 0)

		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Place ID']), placeID, 0)

		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Location']), location, 0)

		Mobile.tap(findTestObject('Android/PG_Common/btn_Add'), 0)
		Thread.sleep(2000)

		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
	}

	/**
	 * Add part need from schedule workflow
	 */
	@Keyword
	public void bf_PartNeeds_AddPartFromInvalidStock(String serialTrackedPartID, String placeID, String location) {
		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Thread.sleep(2000)

		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Thread.sleep(2000)

		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Part Needs']), 0)

		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Part ID']), serialTrackedPartID, 0)

		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Place ID']), placeID, 0)

		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Location']), location, 0)

		Mobile.tap(findTestObject('Android/PG_Common/btn_Add'), 0)
		Thread.sleep(2000)

		for(int i=0; i<5; i++) {
			boolean flag = Mobile.waitForElementPresent(findTestObject('Android/PG_Common/lbl_SyncErrorPopUp'), 5)
			if(flag) {
				break;
			}else {
				Thread.sleep(15000)
				Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
				Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Home']), 0)
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
	 * Request unit adding in schedule workflow
	 */
	@Keyword
	public void bf_Products_AddProduct(String modelID, String serialID) {
		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Thread.sleep(2000)

		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Products']), 0)

		//Selecting model ID
		tapLookUpIcon('Model ID');
		Thread.sleep(2000)

		//Mobile.setText(findTestObject('Android/PG_Common/tf_SearchBar'), modelID, 2)
		Thread.sleep(2000)

		Mobile.tap(findTestObject('Android/PG_Common/lbl_FirstItemFromListWithSeperator'), 0)
		Thread.sleep(2000)

		//Selecting part ID
		tapLookUpIcon('Part ID');
		Thread.sleep(2000)

		Mobile.tap(findTestObject('Android/PG_Common/lbl_FirstItemFromListWithSeperator'), 0)
		Thread.sleep(2000)

		//Selecting serial ID
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Serial ID']), serialID, 0)
		Thread.sleep(2000)

		Mobile.tap(findTestObject('Android/PG_Common/btn_Add'), 0)
		Thread.sleep(2000)
	}

	/**
	 * Request unit selecting in schedule workflow
	 */
	@Keyword
	public void bf_Products_SelectProduct(String modelID, String serialID) {
		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Thread.sleep(2000)

		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Products']), 0)

		//Selecting model ID
		tapLookUpIcon('Model ID');
		Thread.sleep(2000)

		//KeywordUtil.markPassed("Testcase ID : " + MobileDriverFactory.getDriver().getPageSource() + " ")

		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Select a model."]), 0)
		Thread.sleep(2000)

		//Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable', [('idf_TextName') : modelID]),0)
		//Mobile.tap(findTestObject('Android/PG_Common/tf_SearchBar'), 0)

		Thread.sleep(2000)
		//Mobile.setText(findTestObject('Android/PG_Common/tf_SearchBar'), modelID, 2)
		Thread.sleep(2000)

		Mobile.tap(findTestObject('Android/PG_Common/lbl_FirstItemFromListWithSeperator'), 0)
		Thread.sleep(2000)

		//Selecting part ID
		tapLookUpIcon('Part ID');
		Thread.sleep(2000)

		Mobile.tap(findTestObject('Android/PG_Common/lbl_FirstItemFromListWithSeperator'), 0)
		Thread.sleep(2000)

		//Selecting serial ID
		tapLookUpIcon('Serial ID');
		Thread.sleep(2000)

		//Mobile.setText(findTestObject('Android/PG_Common/tf_SearchBar'), serialID, 2)
		Thread.sleep(2000)

		Mobile.tap(findTestObject('Android/PG_Common/lbl_FirstItemFromListWithSeperator'), 0)
		Thread.sleep(2000)

		Mobile.tap(findTestObject('Android/PG_Common/btn_Add'), 0)
		Thread.sleep(2000)
	}
}
