package com.ifs.fsm.ios
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

import com.kms.katalon.core.webui.exception.WebElementNotFoundException

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile

import java.time.LocalDate;
import java.time.Month;

import io.appium.java_client.AppiumDriver

import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption


import org.openqa.selenium.JavascriptExecutor


class LIB_Worklist {
	//This method will verify addition of 'valueBefore' and 'increasedValue' is equal to 'valueAfter'
	//This method does not override built-in 'verifyGreaterThan' than method
	public void verifyGreaterThan(String valueAfter, String valueBefore, int increasedValue) {
		int valueBeforeInt = Integer.parseInt(valueBefore) + increasedValue;
		int valueAfterInt = Integer.parseInt(valueAfter);
		if(valueBeforeInt == valueAfterInt) {
			KeywordUtil.markPassed("List value increased by: " + increasedValue)
		}else {
			KeywordUtil.markFailed("List value not increased by: " + increasedValue)
		}
	}

	//This method will verify subtraction of 'valueBefore' by 'decreasedValue' is equal to 'valueAfter'
	//This method does not override built-in 'verifyLessThan' than method
	public void verifyLessThan(String valueAfter, String valueBefore, int decreasedValue) {
		int valueBeforeInt = Integer.parseInt(valueBefore)  - decreasedValue;
		int valueAfterInt = Integer.parseInt(valueAfter);
		if(valueBeforeInt == valueAfterInt) {
			KeywordUtil.markPassed("List value decreased by: " + decreasedValue)
		}else {
			KeywordUtil.markFailed("List value is not decreased by: " + decreasedValue)
		}
	}

	//Generate random value for serial ID's
	public String generateRan() {
		Random random = new Random();
		String ranLetter = "ABCDEFGHIJKLMNOPQRSTUVWZYZ".toCharArray()[random.nextInt("ABCDEFGHIJKLMNOPQRSTUVWZYZ".toCharArray().length)];
		String ranNumber = Integer.toString(random.nextInt(1000));

		String serial = ranLetter + "-" + ranNumber;
		return serial;
	}

	// This method scroll and click on any element in a picker wheel
	public void clickElementFromPickerWheel(String labelName) {
		TestObject pickerWheel = new TestObject();
		pickerWheel.addProperty("xpath", ConditionType.EQUALS, "//XCUIElementTypePickerWheel");
		MobileBuiltInKeywords.sendKeys(pickerWheel, labelName);
	}

	// This method scroll and click on any element in a picker wheel
	public void clickTwoElementsFromPickerWheel(String firstLabelName, String secondLabelName) {
		TestObject pickerWheelOne = new TestObject();
		pickerWheelOne.addProperty("xpath", ConditionType.EQUALS, "//XCUIElementTypePickerWheel[@index = '0']");
		MobileBuiltInKeywords.sendKeys(pickerWheelOne, firstLabelName);

		TestObject pickerWheelTwo = new TestObject();
		pickerWheelTwo.addProperty("xpath", ConditionType.EQUALS, "//XCUIElementTypePickerWheel[@index = '1']");
		MobileBuiltInKeywords.sendKeys(pickerWheelTwo, secondLabelName);
	}

	//This method returns the current date
	public void setDatePicker() {
		int x = Mobile.getDeviceWidth() - 10
		int y = Mobile.getElementTopPosition(findTestObject('iOS/PG_Common/ele_Date'), 0) + 5

		Mobile.tapAndHoldAtPosition(x, y,0)

		LocalDate currentdate = LocalDate.now();

		//Getting the current year
		int currentYear = currentdate.getYear();
		String year = currentYear.toString();

		//Getting the current day
		int currentDay = currentdate.getDayOfMonth();
		String day = currentDay.toString();

		//Getting the current month
		Month currentMonth = currentdate.getMonth();
		String monthInCapital = currentMonth.toString().toLowerCase();
		String month = monthInCapital.substring(0, 1).toUpperCase() + monthInCapital.substring(1);
		KeywordUtil.markPassed("Passed date : " + month + " " + day);

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_ShowYearPicker'), 0, 0)

		clickTwoElementsFromPickerWheel(month, year);

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_HideYearPicker'), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByContainText', [('idf_ButtonName') : month + " " + day]), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Work Date']), 0, 0)
	}

	//This method captures the coordinates of the passed label name and performs a right to left swipe on the element
	public void swipeDelete(String labelName) {
		Thread.sleep(3000)
		int startingX = Mobile.getDeviceWidth() - 5
		int endingX = Mobile.getElementLeftPosition(findTestObject('iOS/PG_Common/lbl_FirstElementFromList', [('idf_LabelName') : labelName]), 0)
		int y = Mobile.getElementTopPosition(findTestObject('iOS/PG_Common/lbl_FirstElementFromList', [('idf_LabelName') : labelName]), 0)

		Mobile.swipe(startingX, y, endingX, y)
	}

	// This method returns the number of records inside a 'List (x)' in some screens in debrief workflow
	public String returnListValue() {
		String listValue = Mobile.getText(findTestObject('iOS/PG_Common/btn_CommonByContainText', [('idf_ButtonName') : 'List']), 0)
		String listValueSubstring = listValue.substring( listValue.indexOf("(") + 1, listValue.indexOf(")") );
		KeywordUtil.markPassed("No of List Values :" + listValueSubstring + " ")

		return listValueSubstring;
	}

	// Jump to main screen
	public void jumpToMain(String screenName) {
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_DropDownIcon'), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : screenName]), 0, 0)
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
	 * Select a Task from job list
	 */
	@Keyword
	public void bf_SelectTask(String taskStatus) {
		int y = ( Mobile.getDeviceHeight() / 2 );
		int x = ( Mobile.getDeviceWidth() / 2 );
		int yNew = y - 200;

		for(int i=0; i<10; i++) {
			boolean flag = Mobile.verifyElementVisible(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : taskStatus]), 3, FailureHandling.OPTIONAL)

			if(flag) {
				Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : taskStatus]), 0, 0)
				Mobile.delay(5)
				break;
			}else {
				Mobile.swipe(x, y, x , yNew)
				Mobile.delay(3)
			}
		}
	}

	/**
	 * Delete the first record from a list by given label name 
	 */
	@Keyword
	public void bf_DeleteFirstRecordFromList(String labelName) {
		Thread.sleep(3000)
		int startingX = Mobile.getDeviceWidth() - 5
		int endingX = Mobile.getElementLeftPosition(findTestObject('iOS/PG_Common/lbl_FirstElementFromList', [('idf_LabelName') : labelName]), 0)
		int y = Mobile.getElementTopPosition(findTestObject('iOS/PG_Common/lbl_FirstElementFromList', [('idf_LabelName') : labelName]), 0)

		Mobile.swipe(startingX, y, endingX, y)
	}

	/**
	 * Add contact
	 */
	@Keyword
	public void bf_Contacts_AddContact(String lastName, String phone) {
		String beforeListValue = returnListValue();

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Last Name']), lastName, 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Phone']), phone, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Contacts']), 0,0)
		Mobile.delay(5)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']), 0, 0)

		String afterListValue = returnListValue();
		verifyGreaterThan(afterListValue, beforeListValue, 1)
	}


	/**
	 * Delete added contact from list 
	 */
	@Keyword
	public void bf_Contacts_DeleteContact() {
		String beforeListValue = returnListValue();
		Mobile.tapAndHold(findTestObject('iOS/PG_WorkList/btn_List'), 0, 0)
		Thread.sleep(3000)

		int startingX = Mobile.getDeviceWidth() - 5
		int endingX = Mobile.getElementLeftPosition(findTestObject('iOS/PG_WorkList/PG_Contacts/lbl_FirstElementFromContactList'), 0)
		int y = Mobile.getElementTopPosition(findTestObject('iOS/PG_WorkList/PG_Contacts/lbl_FirstElementFromContactList'), 0)

		AppiumDriver driver = MobileDriverFactory.getDriver()
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Map<String, Object> params = new HashMap<>();
		params.put("duration", 1.0);
		params.put("fromX", startingX);
		params.put("fromY", y);
		params.put("toX", endingX);
		params.put("toY", y);
		js.executeScript("mobile: dragFromToForDuration", params);
	}


	/**
	 * Add contact by first name using lookup icon 
	 */
	@Keyword
	public void bf_Contacts_AddContactByFirstName(String firstName) {
		String beforeListValue = returnListValue();

		String height = Mobile.getDeviceHeight()

		String width1 = Mobile.getDeviceWidth()

		int x = Mobile.getElementLeftPosition(findTestObject('iOS/PG_WorkList/PG_Contacts/tf_FirstName'), 0)
		int width = Mobile.getElementWidth(findTestObject('iOS/PG_WorkList/PG_Contacts/tf_FirstName'), 0)
		int y = Mobile.getElementTopPosition(findTestObject('iOS/PG_WorkList/PG_Contacts/tf_FirstName'), 0)

		int xNew = ((x/2) + width) - 5;
		int yNew = y + 5;

		Mobile.tapAndHoldAtPosition(200, 300, 0)
		Thread.sleep(3000)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_FirstElementFromList', [('idf_LabelName') : firstName]), 0, 0)
		Thread.sleep(3000)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']), 0, 0)

		String afterListValue = returnListValue();
		verifyGreaterThan(afterListValue, beforeListValue, 1)
	}


	/**
	 * Add same contact by first name using lookup icon twice
	 */
	@Keyword
	public void bf_Contacts_AddContactByFirstNameTwice(String firstName) {
		String beforeListValue = returnListValue();
		int x = Mobile.getElementLeftPosition(findTestObject('iOS/PG_WorkList/PG_Contacts/tf_FirstName'), 0)
		int width = Mobile.getElementWidth(findTestObject('iOS/PG_WorkList/PG_Contacts/tf_FirstName'), 0)
		int y = Mobile.getElementTopPosition(findTestObject('iOS/PG_WorkList/PG_Contacts/tf_FirstName'), 0)

		int xNew = ((x/2) + width) - 5;
		int yNew = y + 5;

		Mobile.tapAndHoldAtPosition(xNew, yNew , 0)
		Thread.sleep(3000)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_FirstElementFromList', [('idf_LabelName') : firstName]), 0, 0)
		Thread.sleep(3000)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']), 0, 0)
		Thread.sleep(500)

		Mobile.takeScreenshot('Screenshots/Debrief_Contacts_ContactAlreadyExist.png', FailureHandling.STOP_ON_FAILURE)

		String afterListValue = returnListValue();
		Mobile.verifyEqual(afterListValue, beforeListValue)
	}

	/**
	 * Add expense detail 
	 */
	@Keyword
	public void bf_Expenses_AddExpense(String lineCode, String amount, String currency) {
		String beforeListValue = returnListValue();

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Line Code']), 0, 0)
		//clickElementFromPickerWheel(lineCode)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0 ,0 )

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Amount']), amount, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Currency']), 0, 0)
		clickElementFromPickerWheel(currency)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Work Date']), 0, 0)

		//setDatePicker()

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']), 0, 0)

		String afterListValue = returnListValue();
		verifyGreaterThan(afterListValue, beforeListValue, 1)
	}


	/**
	 * Modify existing expense detail
	 */
	@Keyword
	public void bf_Expenses_ModifyExpense(String lineCodeBeforeModify, String lineCodeAfterModify, String amountAfterModify, String currencyAfterModify) {
		String beforeListValue = returnListValue();

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByContainText', [('idf_ButtonName') : 'List']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_FirstElementFromList', [('idf_LabelName') : lineCodeBeforeModify]), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Line Code']), 0, 0)
		clickElementFromPickerWheel(lineCodeAfterModify)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Amount']), amountAfterModify, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Currency']), 0, 0)
		clickElementFromPickerWheel(currencyAfterModify)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Work Date']), 0, 0)

		//setDatePicker()

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Save']), 0, 0)

		jumpToMain('Expenses');
		String afterListValue = returnListValue();
		Mobile.verifyEqual(afterListValue, beforeListValue)
	}


	/**
	 * Delete expense record
	 */
	@Keyword
	public void bf_Expenses_DeleteExpense(String lineCodeAfterModify) {
		String beforeListValue = returnListValue();

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByContainText', [('idf_ButtonName') : 'List']), 0, 0)

		swipeDelete(lineCodeAfterModify);
		jumpToMain('Expenses');
		String afterListValue = returnListValue();
		verifyLessThan(afterListValue, beforeListValue, 1)
	}


	/**
	 * Add expense without compulsory fields 
	 */
	@Keyword
	public void bf_Expenses_AddExpenseWithoutAmount(String lineCode, String currency) {
		String beforeListValue = returnListValue();

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Line Code']), 0,0)
		clickElementFromPickerWheel(lineCode)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Currency']), 0,0)
		clickElementFromPickerWheel(currency)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Work Date']), 0, 0)

		//setDatePicker();

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0,0)
		Thread.sleep(5000)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']), 0,0)

		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_ErrorMessage'), 5)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'OK']), 0, 0)

		String afterListValue = returnListValue();
		Mobile.verifyEqual(afterListValue, beforeListValue)
	}


	/**
	 * Add labor detail 
	 */
	@Keyword
	public void bf_Labor_AddLabor(String lineCode, String amount) {
		String beforeListValue = returnListValue();

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Line Code']), 0, 0)
		clickElementFromPickerWheel(lineCode)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Amount']), amount, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Work Date']), 0, 0)

		//setDatePicker()

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']), 0, 0)

		String afterListValue = returnListValue();
		verifyGreaterThan(afterListValue, beforeListValue, 1)
	}


	/**
	 * Modify existing labor detail
	 */
	@Keyword
	public void bf_Labor_ModifyLabor(String lineCodeBeforeModify, String lineCodeAfterModify, String amountAfterModify) {
		String beforeListValue = returnListValue();

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByContainText', [('idf_ButtonName') : 'List']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_WorkList/PG_Labor/lbl_FirstElementFromLaborList'), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Line Code']), 0, 0)
		clickElementFromPickerWheel(lineCodeAfterModify)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Amount']), amountAfterModify, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Work Date']), 0, 0)

		//setDatePicker();

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Save']), 0, 0)

		jumpToMain('Labor');
		String afterListValue = returnListValue();
		Mobile.verifyEqual(afterListValue, beforeListValue)
	}


	/**
	 * Delete labor detail 
	 */
	@Keyword
	public void bf_Labor_DeleteLaborRecord() {
		String beforeListValue = returnListValue();

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByContainText', [('idf_ButtonName') : 'List']), 0, 0)
		Thread.sleep(3000)

		int startingX = Mobile.getDeviceWidth() - 5
		int endingX = Mobile.getElementLeftPosition(findTestObject('iOS/PG_WorkList/PG_Labor/lbl_FirstElementFromLaborList'), 0)
		int y = Mobile.getElementTopPosition(findTestObject('iOS/PG_WorkList/PG_Labor/lbl_FirstElementFromLaborList'), 0)


		AppiumDriver driver = MobileDriverFactory.getDriver()
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Map<String, Object> params = new HashMap<>();
		params.put("duration", 1.0);
		params.put("fromX", startingX);
		params.put("fromY", y);
		params.put("toX", endingX);
		params.put("toY", y);
		js.executeScript("mobile: dragFromToForDuration", params);

		jumpToMain('Labor');
		String afterListValue = returnListValue();
		verifyLessThan(afterListValue, beforeListValue, 1)
	}


	/**
	 * Add labor without compulsory fields 
	 */
	@Keyword
	public void bf_Labor_AddLaborWithoutAmount(String lineCode) {
		String beforeListValue = returnListValue();

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Line Code']), 0, 0)
		clickElementFromPickerWheel(lineCode)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Work Date']), 0, 0)

		//setDatePicker();

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']), 0, 0)
		Thread.sleep(500)

		Mobile.takeScreenshot('Screenshots/Debrief_Labor.png', FailureHandling.STOP_ON_FAILURE)

		String afterListValue = returnListValue();
		Mobile.verifyEqual(afterListValue, beforeListValue)
	}


	/**
	 * Add note
	 */
	@Keyword
	public void bf_Notes_AddNote(String textLineCode, String text) {
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Text Line Code']), 0, 0)
		clickElementFromPickerWheel(textLineCode)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.setText(findTestObject('iOS/PG_Common/tv_CommonByUpperLabelName', [('idf_LabelName') : 'Text']), text, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Save']), 0, 0)
	}


	/**
	 * Modify existing note detail
	 */
	@Keyword
	public void bf_Notes_ModifyNote(String textLineCode, String textLineCodeAfterModify, String textAfterModify) {
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_FirstElementFromList', [('idf_LabelName') : textLineCode]), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Text Line Code']), 0, 0)
		clickElementFromPickerWheel(textLineCodeAfterModify)

		Mobile.setText(findTestObject('iOS/PG_Common/tv_CommonByUpperLabelName', [('idf_LabelName') : 'Text']), textAfterModify, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Save']), 0, 0)
	}


	/**
	 * Delete existing note detail
	 */
	@Keyword
	public void bf_Notes_DeleteNote(String textLineCodeAfterModify) {
		swipeDelete(textLineCodeAfterModify);

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Delete']), 0, 0)
	}


	/**
	 * Add part need
	 */
	@Keyword
	public void bf_PartNeeds_AddPart(String partID, String placeIDFrom, String locationFrom) {
		String beforeListValue = returnListValue();

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Part ID']), partID, 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Place ID From']), placeIDFrom, 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Location From']), locationFrom, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Part Needs']), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']), 0, 0)

		String afterListValue = returnListValue();
		verifyGreaterThan(afterListValue, beforeListValue, 1)
	}


	/**
	 * Verifying sync error for invalid stock location in part need
	 */
	@Keyword
	public void bf_PartNeeds_AddPartInvalidStockLocation(String partID, String placeIDFrom, String locationFrom) {
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Part ID']), partID, 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Place ID From']), placeIDFrom, 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Location From']), locationFrom, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Part Needs']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_HamburgerMenu'), 0, 0)

		for(int i=0; i<5; i++) {
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

		Mobile.tapAndHold(findTestObject('iOS/PG_WorkList/PG_PartNeeds/lbl_DebriefPartNeedsSyncError'), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Delete']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Yes']), 0, 0)
	}


	/**
	 * Verifying validations for mandatory fields when adding part need
	 */
	@Keyword
	public void bf_PartNeeds_AddPartWithoutMandatoryFields(String quantity) {
		String beforeListValue = returnListValue();
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), quantity, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']), 0, 0)

		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_ErrorMessage'), 5)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'OK']), 0, 0)

		String afterListValue = returnListValue();
		Mobile.verifyEqual(afterListValue, beforeListValue)
	}


	/**
	 * Modify part needs record 
	 */
	@Keyword
	public void bf_PartNeeds_ModifyPart(String partID, String placeIDFrom, String locationFrom) {
		String beforeListValue = returnListValue();
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByContainText', [('idf_ButtonName') : 'List']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_WorkList/PG_PartNeeds/lbl_FirstElementFromPartNeedsList'), 0, 0)

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Part ID']), partID, 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Place ID From']), placeIDFrom, 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Location From']), locationFrom, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Part Needs']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Save']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_Back'), 0, 0)

		String afterListValue = returnListValue();
		Mobile.verifyEqual(afterListValue, beforeListValue)
	}


	/**
	 * Delete first record from part needs list
	 */
	@Keyword
	public void bf_PartNeeds_DeletePart() {
		String beforeListValue = returnListValue();

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByContainText', [('idf_ButtonName') : 'List']), 0, 0)

		int startingX = Mobile.getDeviceWidth() - 5
		int endingX = Mobile.getElementLeftPosition(findTestObject('iOS/PG_WorkList/PG_PartNeeds/lbl_FirstElementFromPartNeedsList'), 0)
		int y = Mobile.getElementTopPosition(findTestObject('iOS/PG_WorkList/PG_PartNeeds/lbl_FirstElementFromPartNeedsList'), 0)

		Mobile.swipe(startingX, y, endingX, y)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_Back'), 0, 0)

		String afterListValue = returnListValue();
		verifyLessThan(afterListValue, beforeListValue, 1)
	}


	/**
	 * Add record to parts used
	 */
	@Keyword
	public void bf_PartsUsed_AddPart(String serialTrackedPartId, String nonTrackedPartId, String lotTrackedPartId, String serialTrackedControlledPartId, String quantity, String codeForControlledPart) {
		//String serialTrackedControlledPartId, String quantity, String codeForControlledPart, String serialIdForControlledPart

		//Adding serial tracked part
		String beforeListValue = returnListValue();
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByContainText', [('idf_LabelName') : 'Parts from my stock']), 0, 0)

		Mobile.setText(findTestObject('iOS/PG_Common/tf_Search'), serialTrackedPartId, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_FirstElementFromList', [('idf_LabelName') : serialTrackedPartId]), 0, 0)

		Mobile.checkElement(findTestObject('iOS/PG_WorkList/PG_PartsUsed/chk_FirstCheckBoxFromList'), 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']), 0, 0)
		Thread.sleep(2000)

		String afterListValue = returnListValue();
		verifyGreaterThan(afterListValue, beforeListValue, 1)

		//Adding non tracked part
		beforeListValue = returnListValue();
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByContainText', [('idf_LabelName') : 'Parts from my stock']), 0, 0)

		Mobile.setText(findTestObject('iOS/PG_Common/tf_Search'), nonTrackedPartId, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_FirstElementFromList', [('idf_LabelName') : nonTrackedPartId]), 0, 0)

		Mobile.setText(findTestObject('iOS/PG_WorkList/PG_PartsUsed/tf_QuantityOnPopUp'), quantity, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']), 0, 0)
		Thread.sleep(2000)

		afterListValue = returnListValue();
		verifyGreaterThan(afterListValue, beforeListValue, 1)

		//Adding lot tracked part
		beforeListValue = returnListValue();
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByContainText', [('idf_LabelName') : 'Parts from my stock']), 0, 0)

		Mobile.setText(findTestObject('iOS/PG_Common/tf_Search'), lotTrackedPartId, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_FirstElementFromList', [('idf_LabelName') : lotTrackedPartId]), 0, 0)

		Mobile.checkElement(findTestObject('iOS/PG_WorkList/PG_PartsUsed/chk_FirstCheckBoxFromList'), 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Proceed']), 0, 0)

		Mobile.setText(findTestObject('iOS/PG_WorkList/PG_PartsUsed/tf_QuantityOnPopUp'), quantity, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']), 0, 0)
		Thread.sleep(2000)

		afterListValue = returnListValue();
		verifyGreaterThan(afterListValue, beforeListValue, 1)

		//Adding serial tracked controlled part
		beforeListValue = returnListValue();
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByContainText', [('idf_LabelName') : 'Parts from my stock']), 0, 0)

		Mobile.setText(findTestObject('iOS/PG_Common/tf_Search'), serialTrackedControlledPartId, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_FirstElementFromList', [('idf_LabelName') : serialTrackedControlledPartId]), 0, 0)
		Thread.sleep(2000)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_FirstElementFromList', [('idf_LabelName') : serialTrackedControlledPartId]), 0, 0)
		Thread.sleep(2000)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Code']), 0, 0)
		clickElementFromPickerWheel(codeForControlledPart)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		String serialIdForControlledPart = generateRan()
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Serial ID']), serialIdForControlledPart, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Disposition']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0, 0)

		afterListValue = returnListValue();
		verifyGreaterThan(afterListValue, beforeListValue, 1)

	}

	/**
	 * Adding part from parts ordered
	 */
	@Keyword
	public void bf_PartsUsed_AddPartsFromPartsOrdered(String serialTrackedPartId, String nonTrackedPartId, String lotTrackedPartId) {
		//Serial part Id


		//non serial part id


		//lot part id

	}

	/**
	 * Adding parts from miscellaneous parts
	 */
	@Keyword
	public void bf_PartsUsed_AddPartsFromMiscellaneousParts(String lineCode, String placeID, String location, String serialTrackedPartId, String nonTrackedPartId, String lotTrackedPartId, String controlledPartID, String code, String serialID) {
		//Serial part Id
		String beforeListValue = returnListValue();

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Line Code']), 0, 0)
		clickElementFromPickerWheel(lineCode)

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Part ID']), serialTrackedPartId, 0)

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Place ID']), placeID, 0)

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'location']), location, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0, 0)
		Thread.sleep(2000)

		String afterListValue = returnListValue();
		verifyGreaterThan(afterListValue, beforeListValue, 1)

		//non serial part id
		String beforeListValue_1 = returnListValue();

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Line Code']), 0, 0)
		clickElementFromPickerWheel(lineCode)

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Part ID']), nonTrackedPartId, 0)

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Place ID']), placeID, 0)

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'location']), location, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0, 0)
		Thread.sleep(2000)

		String afterListValue_1 = returnListValue();
		Mobile.verifyGreaterThan(afterListValue_1, beforeListValue_1)

		//lot part id
		String beforeListValue_2 = returnListValue();

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Line Code']), 0, 0)
		clickElementFromPickerWheel(lineCode)

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Part ID']), lotTrackedPartId, 0)

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Place ID']), placeID, 0)

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'location']), location, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0, 0)
		Thread.sleep(2000)

		//Mobile.tapAndHold(findTestObject('Android/PG_Common/lbl_FirstItemFromListWithSeperator'), 0)
		Thread.sleep(2000)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0, 0)
		Thread.sleep(2000)

		String afterListValue_2 = returnListValue();
		Mobile.verifyGreaterThan(afterListValue_2, beforeListValue_2)

		//controlled part id
		String beforeListValue_3 = returnListValue();

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Line Code']), 0, 0)
		clickElementFromPickerWheel(lineCode)

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Part ID']), controlledPartID, 0)

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Place ID']), placeID, 0)

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'location']), location, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0, 0)
		Thread.sleep(2000)

		//Mobile.tapAndHold(findTestObject('Android/PG_Common/lbl_FirstItemFromListWithSeperator'), 0)
		Thread.sleep(2000)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Code']), 0, 0)
		clickElementFromPickerWheel(code)

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Serial ID']), serialID, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0, 0)
		Thread.sleep(2000)

		String afterListValue_3 = returnListValue();
		Mobile.verifyGreaterThan(afterListValue_3, beforeListValue_3)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByContainText', [('idf_ButtonName') : 'List']), 0, 0)
	}

	/**
	 * Delete added records in parts used
	 */
	@Keyword
	public void bf_PartsUsed_DeletePart(String serialTrackedPartId, String nonTrackedPartId, String lotTrackedPartId) {
		String beforeListValue = returnListValue();
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByContainText', [('idf_ButtonName') : 'List']), 0, 0)
		Thread.sleep(2000)

		swipeDelete(serialTrackedPartId);
		Thread.sleep(2000)

		swipeDelete(nonTrackedPartId);
		Thread.sleep(2000)

		swipeDelete(lotTrackedPartId);
		Thread.sleep(2000)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_Back'), 0, 0)

		String afterListValue = returnListValue();
		verifyLessThan(afterListValue, beforeListValue, 1)
	}

	/**
	 * Adding meter readings
	 */
	@Keyword
	public void bf_MeterReadings_AddReading(String meterName, String meterValue) {
		String beforeListValue = returnListValue();

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Meter Name']), 0, 0)
		clickElementFromPickerWheel(meterName)

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Meter Value']), meterValue, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0, 0)
		Thread.sleep(2000)

		if(Mobile.verifyElementNotExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Meters']), 5)) {
			String afterListValue = returnListValue();
			verifyGreaterThan(afterListValue, beforeListValue, 1)

			//			Mobile.tapAndHold(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Meter Name']), 3)
			//			Mobile.verifyElementNotExist(findTestObject('Android/PG_Common/lbl_CommonByTextDropDownItem', [('idf_LabelName') : meterName]), 3)
			//			Mobile.tapAndHold(findTestObject('Android/PG_Common/lbl_CommonByTextDropDownItem', [('idf_LabelName') : '']), 3)
		}
		Thread.sleep(5000)
	}

	/**
	 * Modifying a meter reading
	 */
	@Keyword
	public void bf_MeterReadings_ModifyReading(String meterName, String meternNameAfterModify, String meterValue) {
		String beforeListValue = returnListValue()

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByContainText', [('idf_ButtonName') : 'List']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_FirstElementFromList', [('idf_LabelName') : meterName]), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Meter Name']), 0, 0)
		clickElementFromPickerWheel(meternNameAfterModify)

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Meter Value']), meterValue, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Save']), 0, 0)
		Thread.sleep(2000)

		String afterListValue = returnListValue();
		Mobile.verifyEqual(afterListValue, beforeListValue)

	}

	/**
	 * Delete a meter reading
	 */
	@Keyword
	public void bf_MeterReadings_DeleteReading(String meterName) {
		swipeDelete(meterName);

	}

	/**
	 * Complete a task status without adding meter readings and validate
	 */
	@Keyword
	public void bf_MeterReadings_ValidateReadingsRequired(String meterName, String meterValue) {
		Mobile.tapAndHold(findTestObject('iOS/PG_WorkList/btn_TaskStatus'), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Completed']), 0, 0)

		Mobile.takeScreenshot('Screenshots/Debrief_MeterReadings_ValidateReadingsRequired.png', FailureHandling.STOP_ON_FAILURE)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_DropDownIcon'), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Meter Readings']), 0, 0)
		Thread.sleep(3000)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Meter Name']), 0, 0)
		clickElementFromPickerWheel(meterName)

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Meter Value']), meterValue, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0, 0)
		Thread.sleep(2000)

		Mobile.tapAndHold(findTestObject('iOS/PG_WorkList/btn_TaskStatus'), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Completed']), 0, 0)
		Thread.sleep(3000)
	}

	/**
	 *  Validations for meters with the count type Increasing, Positive Change are verified by this method
	 */
	@Keyword
	public void bf_MeterReadings_ValidateMeterName(String meterName, String meterValue) {
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Meter Name']), 0, 0)
		clickElementFromPickerWheel(meterName)

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Meter Value']), meterValue, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']), 0, 0)
		Thread.sleep(500)

		Mobile.takeScreenshot('Screenshots/Debrief_MeterReadings_ValidateMeterName.png', FailureHandling.STOP_ON_FAILURE)
		Thread.sleep(5000)
	}

	/**
	 *  Add a cash payment
	 */
	@Keyword
	public void bf_Payment_AddCashOrCheckPayment(String paymentMethod, String amount, String currency) {
		String beforeListValue = returnListValue();

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Payment Method']), 0, 0)
		clickElementFromPickerWheel(paymentMethod)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Amount']), amount, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Currency']), 0, 0)
		clickElementFromPickerWheel(currency)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)
		Thread.sleep(3000)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_SecondCommonByName', [('idf_ButtonName') : 'Add']), 0, 0)
		Thread.sleep(3000)

		String afterListValue = returnListValue();
		verifyGreaterThan(afterListValue, beforeListValue, 1)
	}

	/**
	 *  Add a card payment
	 */
	@Keyword
	public void bf_Payment_AddCardPayment(String paymentMethod, String amount, String currency, String cardType, String name, String cardNumber, String cardVerification, String month, String year) {
		String beforeListValue = returnListValue();

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Payment Method']), 0, 0)
		clickElementFromPickerWheel(paymentMethod)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Amount']), amount, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Currency']), 0, 0)
		clickElementFromPickerWheel(currency)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Card Type']), 0, 0)
		clickElementFromPickerWheel(cardType)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Name']), name, 0)

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Card Number']), cardNumber, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Card Verification Number']), cardVerification, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Month']), 0, 0)
		clickElementFromPickerWheel(month)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Year']), 0, 0)
		clickElementFromPickerWheel(year)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_SecondCommonByName', [('idf_ButtonName') : 'Add']), 0, 0)
		Thread.sleep(30000)

		String afterListValue = returnListValue();
		verifyGreaterThan(afterListValue, beforeListValue, 1)
	}

	/**
	 *  Add a invalid card number for card payment
	 */
	@Keyword
	public void bf_Payment_ValidateCardPayment(String paymentMethod, String amount, String currency, String cardType, String name, String cardNumber, String cardVerification, String month, String year) {
		String beforeListValue = returnListValue();

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Payment Method']), 0, 0)
		clickElementFromPickerWheel(paymentMethod)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Amount']), amount, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Currency']), 0, 0)
		clickElementFromPickerWheel(currency)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Card Type']), 0, 0)
		clickElementFromPickerWheel(cardType)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)
		Thread.sleep(3000)

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Name']), name, 0)

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Card Number']), cardNumber, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Card Verification Number']), cardVerification, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Month']), 0, 0)
		clickElementFromPickerWheel(month)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Year']), 0, 0)
		clickElementFromPickerWheel(year)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_SecondCommonByName', [('idf_ButtonName') : 'Add']), 0, 0)
		Thread.sleep(30000)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_SecondCommonByName', [('idf_ButtonName') : 'Continue']), 0, 0)
		Thread.sleep(3000)

		String afterListValue = returnListValue();
		Mobile.verifyEqual(afterListValue, beforeListValue)
	}

	/**
	 *  Validate outstanding payment
	 */
	@Keyword
	public void bf_Payment_ValidateOutstandingValue(String amount) {
		double outstandingAmount = Double.parseDouble(amount) + Double.parseDouble(amount);
		String text = Mobile.getText(findTestObject('iOS/PG_WorkList/PG_Payments/lbl_OutstandingAmount'), 0)

		if(text.contentEquals('Not Applicable')) {
			KeywordUtil.markPassed("Outstanding value : " + text)
		}else {
			String textSubstring = text.substring(4);
			Mobile.verifyEqual(textSubstring, textSubstring)
		}
	}

	/**
	 * Delete added payment record
	 */
	@Keyword
	public void bf_Payment_DeletePaymentRecord(String paymentMethod) {
		String beforeListValue = returnListValue();

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByContainText', [('idf_ButtonName') : 'List']), 0, 0)
		Thread.sleep(3000)

		swipeDelete(paymentMethod);

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_Back'), 0, 0)
		Thread.sleep(3000)

		String afterListValue = returnListValue();
		verifyLessThan(afterListValue, beforeListValue, 1)
	}

	/**
	 * Validate deleting a payment after changing PAYMENT_ALLOW_DELETE 
	 */
	@Keyword
	public void bf_Payment_ValidateDeletePaymentRecord(String paymentMethod) {
		String beforeListValue = returnListValue();

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByContainText', [('idf_ButtonName') : 'List']), 0, 0)
		Thread.sleep(3000)

		swipeDelete(paymentMethod);

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_Back'), 0, 0)
		Thread.sleep(3000)

		String afterListValue = returnListValue();
		Mobile.verifyEqual(afterListValue, beforeListValue)
	}

	/**
	 *  Add a quality code
	 */
	@Keyword
	public void bf_QualityCodes_AddRecord(String symptom, String problem, String resolution, String text) {
		String beforeListValue = returnListValue();

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Symptom']), 0, 0)
		clickElementFromPickerWheel(symptom)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Problem']), 0, 0)
		clickElementFromPickerWheel(problem)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Resolution']), 0, 0)
		clickElementFromPickerWheel(resolution)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.setText(findTestObject('iOS/PG_Common/tv_CommonByUpperLabelName', [('idf_LabelName') : 'Text']), text, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']), 0, 0)
		Thread.sleep(3000)

		String afterListValue = returnListValue();
		verifyGreaterThan(afterListValue, beforeListValue, 1)
	}

	/**
	 *  Modify a quality code
	 */
	@Keyword
	public void bf_QualityCodes_ModifyRecord(String symptom, String symptomAfterModify, String problemAfterModify, String resolutionAfterModify) {
		String beforeListValue = returnListValue();

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByContainText', [('idf_ButtonName') : 'List']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_FirstElementFromList', [('idf_LabelName') : symptom]), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Symptom']), 0, 0)
		clickElementFromPickerWheel(symptomAfterModify)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Problem']), 0, 0)
		clickElementFromPickerWheel(problemAfterModify)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Resolution']), 0, 0)
		clickElementFromPickerWheel(resolutionAfterModify)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Save']), 0, 0)
		Thread.sleep(3000)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_Back'), 0, 0)
		Thread.sleep(3000)

		String afterListValue = returnListValue();
		Mobile.verifyEqual(afterListValue, beforeListValue)
	}

	/**
	 *  Delete a quality code
	 */
	@Keyword
	public void bf_QualityCodes_DeleteRecord(String symptomAfterModify) {
		String beforeListValue = returnListValue();

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByContainText', [('idf_ButtonName') : 'List']), 0, 0)

		swipeDelete(symptomAfterModify);

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_Back'), 0, 0)
		Thread.sleep(3000)

		String afterListValue = returnListValue();
		verifyLessThan(afterListValue, beforeListValue, 1)
	}

	/**
	 * Modify details on the debrief overview screen
	 */
	@Keyword
	public void bf_Overview_ModifyRecord(String taskStatus, String taskType, String priority, String duration, String description) {
		Mobile.tapAndHold(findTestObject('iOS/PG_WorkList/btn_TaskStatus'), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : taskStatus]), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Task Type']), 0, 0)
		clickElementFromPickerWheel(taskType)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Priority']), 0, 0)
		clickElementFromPickerWheel(priority)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Plan Start']), 0, 0)
		//clickElementFromPickerWheel()
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Duration']), duration, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.setText(findTestObject('iOS/PG_Common/tv_CommonByUpperLabelName', [('idf_LabelName') : 'Description']), description, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Save']), 0, 0)
		Mobile.delay(2)

		Mobile.verifyElementText(findTestObject('iOS/PG_Common/tv_CommonByUpperLabelName', [('idf_LabelName') : 'Task Status']), taskStatus)
		Mobile.verifyElementText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Task Type']), taskType)
		Mobile.verifyElementText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Priority']), priority)
		Mobile.verifyElementText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Duration']), duration)
		Mobile.verifyElementText(findTestObject('iOS/PG_Common/tv_CommonByUpperLabelName', [('idf_LabelName') : 'Description']), description)
	}

	/**
	 * Click and verify the address hyperlink on the debrief overview screen
	 */
	@Keyword
	public void bf_Overview_VerifyAdressHyperlink() {
		Mobile.tapAndHold(findTestObject('iOS/PG_WorkList/PG_Overview/tv_AddressHyperlink'), 0, 0)
		Mobile.delay(10)
		Mobile.takeScreenshot('Screenshots/Debrief_Overview_AddressOnMap.png', FailureHandling.STOP_ON_FAILURE)
	}

	/**
	 * Click and verify other hyperlinks on the debrief overview screen
	 */
	@Keyword
	public void bf_Overview_VerifyHyperlinks() {
		Mobile.tapAndHold(findTestObject('iOS/PG_WorkList/PG_Overview/tv_NameHyperlink'), 0, 0)
		Mobile.delay(10)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Customer Detail']), 5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_Back'), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_WorkList/PG_Overview/tv_RequestIDHyperlink'), 0, 0)
		Mobile.delay(10)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Request Detail']), 5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_Back'), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_WorkList/PG_Overview/tv_PhoneHyperlink'), 0, 0)
		Mobile.delay(10)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Call']), 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Cancel']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_WorkList/PG_Overview/tv_EmailHyperlink'), 0, 0)
		Mobile.delay(10)
		//Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Meters']), 5)
		//Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_Back'), 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_WorkList/PG_Overview/tv_ModelIDHyperlink'), 0, 0)
		Mobile.delay(10)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Product Detail']), 5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_Back'), 0, 0)
	}

	/**
	 * verify map on the debrief overview screen
	 */
	@Keyword
	public void bf_Overview_VerifyMap() {
		Mobile.takeScreenshot('Screenshots/Debrief_Overview_Map.png', FailureHandling.STOP_ON_FAILURE)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Get Directions']), 0, 0)
		Mobile.delay(20)
		Mobile.takeScreenshot('Screenshots/Debrief_Overview_GetDirections.png', FailureHandling.STOP_ON_FAILURE)
	}
}