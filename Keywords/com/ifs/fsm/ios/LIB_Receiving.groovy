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

import com.kms.katalon.core.util.KeywordUtil

import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import io.appium.java_client.ios.IOSDriver
import io.appium.java_client.AppiumDriver
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.testobject.ConditionType as ConditionType

import java.time.LocalDate;

import org.openqa.selenium.JavascriptExecutor


import internal.GlobalVariable

public class LIB_Receiving {

	//This method generate random serial numbers
	public String generateRan() {
		Random random = new Random();
		String ranLetter1 = "ABCDEFGHIJKLMNOPQRSTUVWZYZ".toCharArray()[random.nextInt("ABCDEFGHIJKLMNOPQRSTUVWZYZ".toCharArray().length)];
		String ranLetter2 = "ABCDEFGHIJKLMNOPQRSTUVWZYZ".toCharArray()[random.nextInt("ABCDEFGHIJKLMNOPQRSTUVWZYZ".toCharArray().length)];
		String ranNumber = Integer.toString(random.nextInt(1000));

		String serial = ranLetter1 + ranLetter2 + "-" + ranNumber;
		return serial;
	}

	//get the receiving count in the global menu
	public String getReceivingCount() {
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_HamburgerMenu'), 0, 0)
		String count = Mobile.getText(findTestObject('iOS/PG_Common/lbl_CommonByUpperLabelName', [('idf_LabelName') : 'Receiving']), 0)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_HamburgerMenu'), 0)
		return count;
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


	//This method captures the coordinates of the passed label name and performs a right to left swipe on the element
	public static void swipeDelete(String labelName) {
		Thread.sleep(3000)
		int startingX = Mobile.getDeviceWidth() - 5
		int endingX = Mobile.getElementLeftPosition(findTestObject('iOS/PG_Common/lbl_FirstElementFromList', [('idf_LabelName') : labelName]), 0)
		int y = Mobile.getElementTopPosition(findTestObject('iOS/PG_Common/lbl_FirstElementFromList', [('idf_LabelName') : labelName]), 0)

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
	 * TA-Receiving List: Availability of the Receiving global menu item
	 */
	@Keyword
	public void bf_ReceivingList_AvailabilityOfReceivingGlobalMenuItem() {
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_HamburgerMenu'), 0, 0)
		if(Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Receiving']), 0)) {
			Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Receiving']), 0)
			String count = Mobile.getText(findTestObject('iOS/PG_Common/lbl_CommonByUpperLabelName', [('idf_LabelName') : 'Receiving']), 0);
			if(count == 0) {
				Mobile.verifyElementNotExist(findTestObject('Object Repository/iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Receiving']), 0)
			}
		}else {
			Mobile.verifyElementNotExist(findTestObject('Object Repository/iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Receiving']), 0)
		}
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_HamburgerMenu'), 0, 0)
		Mobile.delay(5)
	}


	/**
	 * TA-Receiving List: Verify data against the FSM Client
	 */
	@Keyword
	public void bf_ReceivingList_VerifyDataAgainstFSMClient() {
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_HamburgerMenu'), 0, 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByUpperLabelName', [('idf_LabelName') : 'Receiving']), 3)
		Thread.sleep(2000)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_HamburgerMenu'), 0, 0)
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Receiving']),  0)
		String ReceivingListCount = getReceivingCount()
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByContainText',[('idf_LabelName') :ReceivingListCount ]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Home']), 0, 0)
		Thread.sleep(2000)
		//String count = Mobile.getText(findTestObject('iOS/PG_Common/lbl_CommonByUpperLabelName', [('idf_LabelName') : 'Receiving']), 0)
		//count =getReceivingCount()
	}


	/**
	 * TA-Receiving List:Verify navigation from the Receiving List
	 */
	@Keyword
	public void bf_ReceivingList_VerifyNavigationfromReceivingList() {
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'SHIP']), 0)
		Mobile.delay(5)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Part List"]), 0)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_Back'), 0,0)
		Mobile.delay(5)
		bf_SelectTask('PO')
		Mobile.delay(5)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PO Part List"]), 0)
	}


	/**
	 * TA-Receiving Detail List: Verify available data
	 */
	@Keyword
	public void bf_DetailList_VerifyAvailableData() {
		bf_SelectTask('PO')
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "TA-NON-PART1"]), 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "0"]), 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByContainText',[('idf_LabelName') : "10"]), 0)
	}

	/**
	 * TA-Receiving Detail List: Process-without adding any receiving units
	 */
	@Keyword
	public void bf_DetailList_ProcesswithoutAddingReceivingUnits() {
		bf_SelectTask('11922')
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName',[('idf_ButtonName') : "Process"]), 0,0)
		Mobile.delay(5)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_ErrorMessage'), 0)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName',[('idf_ButtonName') : "OK"]), 0,0)
	}

	/**
	 * TA-Receiving Detail List: Verify navigation to the ReceivingUnitPO screen
	 */
	@Keyword
	public void bf_DetailList_VerifyNavigationtoReceivingUnitPOScreen() {
		bf_SelectTask('PO')
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "TA-NON-PART1"]), 0,0)
		Mobile.delay(5)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PO Receiving Unit"]), 0)
	}

	/**
	 * TA-Receiving Unit PO: Add serial tracked units-verify the Finish button behavior
	 */
	@Keyword
	public void bf_UnitPO_AddSerialTrackedUnitsVerifyFinishButtonBehavior() {
		bf_SelectTask('PO')
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "TA-SER-PART1"]), 0,0)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName',[('idf_ButtonName') : "Finish"]), 0,0)
		Mobile.delay(5)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PO Part List"]), 0)
	}

	/**
	 * TA-Receiving Unit PO: Add serial tracked units
	 */
	@Keyword
	public void bf_UnitPO_AddSerialTrackedUnits() {
		bf_SelectTask('PO')
		String beforevalue = Mobile.getText(findTestObject('iOS/PG_Receiving/lbl_SerialPartQty'),0)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "TA-SER-PART1"]), 0,0)
		Mobile.delay(5)
		String listValuesBefore = Mobile.getText(findTestObject('iOS/PG_Common/btn_CommonByContainText', [('idf_ButtonName') : 'List']), 0)
		String listValuesBeforeSubstring = listValuesBefore.substring(listValuesBefore.indexOf("(") + 1, listValuesBefore.indexOf(")"));
		KeywordUtil.markPassed("No of List Values before adding a part " + listValuesBeforeSubstring + " ")
		String serial1 = generateRan();
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Serial ID']), serial1, 3)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Receiving/btn_Done'), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName',[('idf_ButtonName') : "Add"]), 0,0)
		Mobile.delay(5)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PO Receiving Unit"]), 0)
		String listValuesAfter = Mobile.getText(findTestObject('iOS/PG_Common/btn_CommonByContainText', [('idf_ButtonName') : 'List']), 0)
		String listValuesAfterSubstring = listValuesAfter.substring(listValuesAfter.indexOf("(") + 1, listValuesAfter.indexOf(")"));
		KeywordUtil.markPassed("No of List Values after adding a new part " + listValuesAfterSubstring + " ")
		Mobile.delay(5)
		Boolean listvaluesresult = Mobile.verifyGreaterThan(listValuesAfterSubstring, listValuesBeforeSubstring)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByContainText',[('idf_ButtonName') : 'List']),0,0)
		Mobile.delay(5)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : serial1]), 0)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_Back'), 0,0)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName',[('idf_ButtonName') : "Finish"]), 0,0)
		Mobile.delay(5)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PO Part List"]), 0)
		Mobile.delay(5)
		String aftervalue=Mobile.getText(findTestObject('iOS/PG_Receiving/lbl_SerialPartQty'),0)
		Mobile.verifyGreaterThan(aftervalue,beforevalue)
	}

	/**
	 * TA-Receiving Unit PO: Add lot identified units
	 */
	@Keyword
	public void bf_UnitPO_AddLotIdentifiedUnits(String quantity,String LotID) {
		bf_SelectTask('PO')
		String beforevalue=Mobile.getText(findTestObject('iOS/PG_Receiving/lbl_LotPartQty'),0)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "TA-LOT-PART1"]), 0,0)
		Mobile.delay(5)
		String listValuesBefore = Mobile.getText(findTestObject('iOS/PG_Common/btn_CommonByContainText', [('idf_ButtonName') : 'List']), 0)
		String listValuesBeforeSubstring = listValuesBefore.substring(listValuesBefore.indexOf("(") + 1, listValuesBefore.indexOf(")"));
		KeywordUtil.markPassed("No of List Values before adding a part " + listValuesBeforeSubstring + " ")
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), quantity, 3)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName',[('idf_ButtonName') : "Done"]), 0,0)
		Mobile.delay(5)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Lot ID']), LotID, 3)
		Mobile.tapAndHold(findTestObject('iOS/PG_Receiving/btn_Done'), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName',[('idf_ButtonName') : "Add"]), 0,0)
		Mobile.delay(5)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PO Receiving Unit"]), 0)
		String listValuesAfter = Mobile.getText(findTestObject('iOS/PG_Common/btn_CommonByContainText', [('idf_ButtonName') : 'List']), 0)
		String listValuesAfterSubstring = listValuesAfter.substring(listValuesAfter.indexOf("(") + 1, listValuesAfter.indexOf(")"));
		KeywordUtil.markPassed("No of List Values after adding a new part " + listValuesAfterSubstring + " ")
		Boolean listvaluesresult = Mobile.verifyGreaterThan(listValuesAfterSubstring, listValuesBeforeSubstring)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByContainText',[('idf_ButtonName') : 'List']),0,0)
		Mobile.delay(5)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : LotID]), 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_Back'), 0,0)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName',[('idf_ButtonName') : "Finish"]), 0,0)
		Mobile.delay(5)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PO Part List"]), 0)
		String aftervalue=Mobile.getText(findTestObject('iOS/PG_Receiving/lbl_LotPartQty'),0)
		Mobile.verifyGreaterThan(aftervalue,beforevalue)
	}


	/**
	 * TA-Receiving Unit PO: Add lot identified and serialized units
	 */
	@Keyword
	public void bf_UnitPO_AddLotIdentifiedSerializedUnits(String lotID) {
		bf_SelectTask('PO')
		String beforevalue=Mobile.getText(findTestObject('iOS/PG_Receiving/lbl_SerialLotPartQty'),0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "TA-SER-LOT-PART1"]), 0,0)
		String listValuesBefore = Mobile.getText(findTestObject('iOS/PG_Common/btn_CommonByContainText', [('idf_ButtonName') : 'List']), 0)
		String listValuesBeforeSubstring = listValuesBefore.substring(listValuesBefore.indexOf("(") + 1, listValuesBefore.indexOf(")"));
		KeywordUtil.markPassed("No of List Values before adding a part " + listValuesBeforeSubstring + " ")
		Thread.sleep(2000)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Lot ID']), lotID, 3)
		Mobile.tapAndHold(findTestObject('iOS/PG_Receiving/btn_Done'), 0, 0)
		String serial = generateRan();
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Serial ID']), serial, 3)
		Mobile.tapAndHold(findTestObject('iOS/PG_Receiving/btn_Done'), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName',[('idf_ButtonName') : "Add"]), 0,0)
		Mobile.delay(5)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PO Receiving Unit"]), 0)
		String listValuesAfter = Mobile.getText(findTestObject('iOS/PG_Common/btn_CommonByContainText', [('idf_ButtonName') : 'List']), 0)
		String listValuesAfterSubstring = listValuesAfter.substring(listValuesAfter.indexOf("(") + 1, listValuesAfter.indexOf(")"));
		KeywordUtil.markPassed("No of List Values after adding a new part " + listValuesAfterSubstring + " ")
		Boolean listvaluesresult = Mobile.verifyGreaterThan(listValuesAfterSubstring, listValuesBeforeSubstring)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByContainText',[('idf_ButtonName') : 'List']),0,0)
		Mobile.delay(5)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : serial]), 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_Back'), 0,0)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName',[('idf_ButtonName') : "Finish"]), 0,0)
		Mobile.delay(5)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PO Part List"]), 0)
		String aftervalue=Mobile.getText(findTestObject('iOS/PG_Receiving/lbl_SerialLotPartQty'),0)
		Mobile.delay(5)
		Mobile.verifyGreaterThan(aftervalue,beforevalue)
	}

	/**
	 * TA-Receiving Unit PO: Modify Quantities: Non-tracked parts
	 */
	@Keyword
	public void bf_UnitPO_ModifyQuantitiesNonTrackedParts(String quantity) {
		bf_SelectTask('PO')
		String beforevalue=Mobile.getText(findTestObject('iOS/PG_Receiving/lbl_NonPartQty'),0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "TA-NON-PART1"]), 0,0)
		String listValuesBefore = Mobile.getText(findTestObject('iOS/PG_Common/btn_CommonByContainText', [('idf_ButtonName') : 'List']), 0)
		String listValuesBeforeSubstring = listValuesBefore.substring(listValuesBefore.indexOf("(") + 1, listValuesBefore.indexOf(")"));
		KeywordUtil.markPassed("No of List Values before adding a part " + listValuesBeforeSubstring + " ")
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), quantity, 3)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName',[('idf_ButtonName') : "Done"]), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName',[('idf_ButtonName') : "Add"]), 0,0)
		Mobile.delay(5)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PO Receiving Unit"]), 0)
		String listValuesAfter = Mobile.getText(findTestObject('iOS/PG_Common/btn_CommonByContainText', [('idf_ButtonName') : 'List']), 0)
		String listValuesAfterSubstring = listValuesAfter.substring(listValuesAfter.indexOf("(") + 1, listValuesAfter.indexOf(")"));
		KeywordUtil.markPassed("No of List Values after adding a new part " + listValuesAfterSubstring + " ")
		Boolean listvaluesresult = Mobile.verifyGreaterThan(listValuesAfterSubstring, listValuesBeforeSubstring)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByContainText',[('idf_ButtonName') : 'List']),0,0)
		Mobile.delay(5)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : quantity]), 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_Back'), 0,0)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName',[('idf_ButtonName') : "Finish"]), 0,0)
		Mobile.delay(5)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PO Part List"]), 0)
		String aftervalue=Mobile.getText(findTestObject('iOS/PG_Receiving/lbl_NonPartQty'),0)
		Mobile.delay(5)
		Mobile.verifyGreaterThan(aftervalue,beforevalue)
	}


	/**
	 * TA-Receiving Unit PO List: Verify navigation from the ReceivingUnitPO screen
	 */
	@Keyword
	public void bf_UnitPO_VerifyNavigationfromReceivingUnitPOScreen() {
		bf_SelectTask('PO')
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "TA-NON-PART1"]), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByContainText',[('idf_ButtonName') : 'List']),0,0)
		Mobile.delay(5)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PO Receiving Unit List"]), 0)
	}


	/**
	 * TA-Receiving Unit PO List: Verify count and data available on a list item on the ReceivingUnitPOList screen
	 */
	@Keyword
	public void bf_UnitPO_VerifyCountAvailableonListItemonReceivingUnitPOListScreen() {
		bf_SelectTask('PO')
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "TA-SER-LOT-PART1"]), 0,0)
		String listValue = Mobile.getText(findTestObject('iOS/PG_Common/btn_CommonByContainText', [('idf_ButtonName') : 'List']), 0)
		String listValueSubstring = listValue.substring(listValue.indexOf("(") + 1, listValue.indexOf(")"));
		KeywordUtil.markPassed("No of List Values" + listValueSubstring + " ")
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByContainText',[('idf_ButtonName') : 'List']),0,0)
		Mobile.delay(5)
		AppiumDriver driver = MobileDriverFactory.getDriver()
		driver.findElements(By.xpath("//XCUIElementTypeStaticText[contains(@label,'TA')]")).size()
		int x =	driver.findElements(By.xpath("//XCUIElementTypeStaticText[contains(@label,'TA')]")).size()
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'TA-SER-LOT-PART1']), 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : '1']), 0)
		Mobile.verifyEqual(listValueSubstring, x)
	}



	/**
	 * TA-Receiving Unit PO List: Modify Units-Serialized parts
	 */
	@Keyword
	public void bf_UnitPO_ModifyUnitsSerializedParts() {
		bf_SelectTask('PO')
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "TA-SER-PART1"]), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByContainText',[('idf_ButtonName') : 'List']),0,0)
		Mobile.delay(5)
		String value= Mobile.getText(findTestObject('iOS/PG_Common/lbl_CommonByUpperLabelName', [('idf_LabelName') : 'TA-SER-PART1']), 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_FirstElementFromList',[('idf_LabelName') : "TA-SER-PART1"]), 0,0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Receiving/tf_SerialID',[('idf_LabelName') : value]), 0)
		String serial2 = generateRan();
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Serial ID']), serial2, 3)
		Mobile.tapAndHold(findTestObject('iOS/PG_Receiving/btn_Done'), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName',[('idf_ButtonName') : "Save"]), 0,0)
		Mobile.delay(5)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PO Receiving Unit List"]), 0)
		Mobile.delay(3)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : serial2]), 0)
	}


	/**
	 * TA-Receiving Unit PO List: Modify Units- Lot identified parts
	 */
	@Keyword
	public void bf_UnitPO_ModifyUnitsLotIdentifiedParts(String lotID,String quantity) {
		bf_SelectTask('PO')
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "TA-LOT-PART1"]), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByContainText',[('idf_ButtonName') : 'List']),0,0)
		Mobile.delay(5)
		String value= Mobile.getText(findTestObject('iOS/PG_Receiving/lbl_NonPartQty'), 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_FirstElementFromList',[('idf_LabelName') : "TA-LOT-PART1"]), 0,0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Receiving/tf_SerialID',[('idf_LabelName') : value]), 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Lot ID']), lotID, 3)
		Mobile.tapAndHold(findTestObject('iOS/PG_Receiving/btn_Done'), 0, 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), quantity, 3)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName',[('idf_ButtonName') : "Done"]), 0,0)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Expiration Date']), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName',[('idf_ButtonName') : "Done"]), 0,0)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName',[('idf_ButtonName') : "Save"]), 0,0)
		Mobile.delay(3)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PO Receiving Unit List"]), 0)
		Mobile.delay(3)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : lotID]), 0)
	}

	/**
	 * TA-Receiving Unit PO List: Modify Units- Serialized lot identified parts
	 */
	@Keyword
	public void bf_UnitPO_ModifyUnitsSerializedLotIdentifiedParts(String lotID) {
		bf_SelectTask('PO')
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "TA-SER-LOT-PART1"]), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByContainText',[('idf_ButtonName') : 'List']),0,0)
		Mobile.delay(5)
		String value= Mobile.getText(findTestObject('iOS/PG_Common/lbl_CommonByUpperLabelName', [('idf_LabelName') : 'TA-SER-LOT-PART1']), 0)
		String LotIdvalue= Mobile.getText(findTestObject('iOS/PG_Receiving/lbl_NonPartQty'), 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_FirstElementFromList',[('idf_LabelName') : "TA-SER-LOT-PART1"]), 0,0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Receiving/tf_SerialID',[('idf_LabelName') : value]), 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Receiving/tf_SerialID',[('idf_LabelName') : LotIdvalue]), 0)
		String serial3 = generateRan();
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Serial ID']), serial3, 3)
		Mobile.tapAndHold(findTestObject('iOS/PG_Receiving/btn_Done'), 0, 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Lot ID']), lotID, 3)
		Mobile.tapAndHold(findTestObject('iOS/PG_Receiving/btn_Done'), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Expiration Date']), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName',[('idf_ButtonName') : "Done"]), 0,0)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName',[('idf_ButtonName') : "Save"]), 0,0)
		Mobile.delay(3)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PO Receiving Unit List"]), 0)
		Mobile.delay(3)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : lotID]), 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : serial3]), 0)
	}


	/**
	 * TA-Receiving Unit PO List: Modify Units- Non-tracked parts
	 */
	@Keyword
	public void bf_UnitPO_ModifyUnitsNonTrackedParts(String quantity) {
		bf_SelectTask('PO')
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "TA-NON-PART1"]), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByContainText',[('idf_ButtonName') : 'List']),0,0)
		Mobile.delay(5)
		String value= Mobile.getText(findTestObject('iOS/PG_Receiving/lbl_Quantity'), 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_FirstElementFromList',[('idf_LabelName') : "TA-NON-PART1"]), 0,0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Receiving/tf_SerialID',[('idf_LabelName') : value]), 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), quantity, 3)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName',[('idf_ButtonName') : "Done"]), 0,0)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName',[('idf_ButtonName') : "Save"]), 0,0)
		Mobile.delay(3)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PO Receiving Unit List"]), 0)
		Mobile.delay(3)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : quantity]), 0)
	}

	/**
	 * TA-Receiving Unit PO List: Delete Units- Serialized parts
	 */
	@Keyword
	public void bf_UnitPO_DeleteUnitsSerializedParts(String serialTrackedPartID) {
		bf_SelectTask('PO')
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "TA-SER-PART1"]), 0,0)
		String listValuesBefore = Mobile.getText(findTestObject('iOS/PG_Common/btn_CommonByContainText', [('idf_ButtonName') : 'List']), 0)
		String listValuesBeforeSubstring = listValuesBefore.substring(listValuesBefore.indexOf("(") + 1, listValuesBefore.indexOf(")"));
		KeywordUtil.markPassed("No of List Values before deleting a part " + listValuesBeforeSubstring + " ")
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByContainText',[('idf_ButtonName') : 'List']),0,0)
		Mobile.delay(5)
		AppiumDriver driver = MobileDriverFactory.getDriver();
		driver.findElements(By.xpath("//XCUIElementTypeStaticText[contains(@label,'TA')]")).size()
		int beforeValue =driver.findElements(By.xpath("//XCUIElementTypeStaticText[contains(@label,'TA')]")).size()
		Mobile.delay(5)
		swipeDelete(serialTrackedPartID)
		Mobile.delay(60)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PO Receiving Unit List"]), 0)
		driver.findElements(By.xpath("//XCUIElementTypeStaticText[contains(@label,'TA')]")).size()
		int afterValue =driver.findElements(By.xpath("//XCUIElementTypeStaticText[contains(@label,'TA')]")).size()
		Mobile.delay(5)
		Mobile.verifyLessThan(afterValue,beforeValue)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_Back'),0,0)
		Mobile.delay(5)
		String listValuesAfter = Mobile.getText(findTestObject('iOS/PG_Common/btn_CommonByContainText', [('idf_ButtonName') : 'List']), 0)
		String listValuesAfterSubstring = listValuesAfter.substring(listValuesAfter.indexOf("(") + 1, listValuesAfter.indexOf(")"));
		KeywordUtil.markPassed("No of List Values after deleting a part " + listValuesAfterSubstring + " ")
		Boolean listvaluesresult = Mobile.verifyLessThan(listValuesAfterSubstring, listValuesBeforeSubstring)
	}

	/**
	 * TA-Receiving Unit PO List: Delete Units- Lot identified parts
	 */
	@Keyword
	public void bf_UnitPO_DeleteUnitsLotIdentifiedParts() {
		bf_SelectTask('PO')
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "TA-LOT-PART1"]), 0,0)
		String listValuesBefore = Mobile.getText(findTestObject('iOS/PG_Common/btn_CommonByContainText', [('idf_ButtonName') : 'List']), 0)
		String listValuesBeforeSubstring = listValuesBefore.substring(listValuesBefore.indexOf("(") + 1, listValuesBefore.indexOf(")"));
		KeywordUtil.markPassed("No of List Values before deleting a part " + listValuesBeforeSubstring + " ")
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByContainText',[('idf_ButtonName') : 'List']),0,0)
		Mobile.delay(5)
		AppiumDriver driver = MobileDriverFactory.getDriver();
		driver.findElements(By.xpath("//XCUIElementTypeStaticText[contains(@label,'TA')]")).size()
		int beforeValue =driver.findElements(By.xpath("//XCUIElementTypeStaticText[contains(@label,'TA')]")).size()
		Mobile.delay(5)
		swipeDelete('TA-LOT-PART1');
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PO Receiving Unit List"]), 0)
		driver.findElements(By.xpath("//XCUIElementTypeStaticText[contains(@label,'TA')]")).size()
		int afterValue =driver.findElements(By.xpath("//XCUIElementTypeStaticText[contains(@label,'TA')]")).size()
		Mobile.delay(5)
		Mobile.verifyLessThan(afterValue,beforeValue)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_Back'),0,0)
		Mobile.delay(5)
		String listValuesAfter = Mobile.getText(findTestObject('iOS/PG_Common/btn_CommonByContainText', [('idf_ButtonName') : 'List']), 0)
		String listValuesAfterSubstring = listValuesAfter.substring(listValuesAfter.indexOf("(") + 1, listValuesAfter.indexOf(")"));
		KeywordUtil.markPassed("No of List Values after adding a part " + listValuesAfterSubstring + " ")
		Boolean listvaluesresult = Mobile.verifyLessThan(listValuesAfterSubstring, listValuesBeforeSubstring)
	}


	/**
	 * TA-Receiving Unit PO List: Delete Units- Serialized lot identified parts
	 */
	@Keyword
	public void bf_UnitPO_DeleteUnitsSerializedLotIdentifiedParts() {
		bf_SelectTask('PO')
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "TA-SER-LOT-PART1"]), 0,0)
		String listValuesBefore =  Mobile.getText(findTestObject('iOS/PG_Common/btn_CommonByContainText', [('idf_ButtonName') : 'List']), 0)
		String listValuesBeforeSubstring = listValuesBefore.substring(listValuesBefore.indexOf("(") + 1, listValuesBefore.indexOf(")"));
		KeywordUtil.markPassed("No of List Values before deleting a part " + listValuesBeforeSubstring + " ")
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByContainText',[('idf_ButtonName') : 'List']),0,0)
		Mobile.delay(5)
		AppiumDriver driver = MobileDriverFactory.getDriver()
		driver.findElements(By.xpath("//XCUIElementTypeStaticText[contains(@label,'TA')]")).size()
		int beforeValue =driver.findElements(By.xpath("//XCUIElementTypeStaticText[contains(@label,'TA')]")).size()
		swipeDelete('TA-SER-LOT-PART1');
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PO Receiving Unit List"]), 0)
		driver.findElements(By.xpath("//XCUIElementTypeStaticText[contains(@label,'TA')]")).size()
		int afterValue =driver.findElements(By.xpath("//XCUIElementTypeStaticText[contains(@label,'TA')]")).size()
		Mobile.delay(5)
		Mobile.verifyLessThan(afterValue,beforeValue)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_Back'),0,0)
		Mobile.delay(5)
		String listValuesAfter = Mobile.getText(findTestObject('iOS/PG_Common/btn_CommonByContainText', [('idf_ButtonName') : 'List']), 0)
		String listValuesAfterSubstring = listValuesAfter.substring(listValuesAfter.indexOf("(") + 1, listValuesAfter.indexOf(")"));
		KeywordUtil.markPassed("No of List Values after deleting a part " + listValuesAfterSubstring + " ")
		Boolean listvaluesresult = Mobile.verifyLessThan(listValuesAfterSubstring, listValuesBeforeSubstring)
	}


	/**
	 * TA-Receiving Unit PO List: Delete Units- Non-tracked parts
	 */
	@Keyword
	public void bf_UnitPO_DeleteUnitsNonTrackedParts() {
		bf_SelectTask('PO')
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "TA-SER-LOT-PART1"]), 0,0)
		String listValuesBefore =   Mobile.getText(findTestObject('iOS/PG_Common/btn_CommonByContainText', [('idf_ButtonName') : 'List']), 0)
		String listValuesBeforeSubstring = listValuesBefore.substring(listValuesBefore.indexOf("(") + 1, listValuesBefore.indexOf(")"));
		KeywordUtil.markPassed("No of List Values before deleting a part " + listValuesBeforeSubstring + " ")
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByContainText',[('idf_ButtonName') : 'List']),0,0)
		Mobile.delay(5)
		AppiumDriver driver = MobileDriverFactory.getDriver()
		driver.findElements(By.xpath("//XCUIElementTypeStaticText[contains(@label,'TA')]")).size()
		int beforeValue =driver.findElements(By.xpath("//XCUIElementTypeStaticText[contains(@label,'TA')]")).size()
		Mobile.delay(5)
		swipeDelete('TA-SER-LOT-PART1');
		Mobile.delay(60)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PO Receiving Unit List"]), 0)
		driver.findElements(By.xpath("//XCUIElementTypeStaticText[contains(@label,'TA')]")).size()
		int afterValue =driver.findElements(By.xpath("//XCUIElementTypeStaticText[contains(@label,'TA')]")).size()
		Mobile.delay(5)
		Mobile.verifyLessThan(afterValue,beforeValue)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_Back'),0,0)
		Mobile.delay(5)
		String listValuesAfter =  Mobile.getText(findTestObject('iOS/PG_Common/btn_CommonByContainText', [('idf_ButtonName') : 'List']), 0)
		String listValuesAfterSubstring = listValuesAfter.substring(listValuesAfter.indexOf("(") + 1, listValuesAfter.indexOf(")"));
		KeywordUtil.markPassed("No of List Values after deleting a  part " + listValuesAfterSubstring + " ")
		Boolean listvaluesresult = Mobile.verifyLessThan(listValuesAfterSubstring, listValuesBeforeSubstring)
	}


}
