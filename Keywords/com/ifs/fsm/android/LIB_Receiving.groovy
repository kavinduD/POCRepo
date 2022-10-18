package com.ifs.fsm.android

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import org.openqa.selenium.By

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
import io.appium.java_client.ios.IOSDriver
import io.appium.java_client.AppiumDriver
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import org.openqa.selenium.WebElement
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory


public class LIB_Receiving {

	//This method generate random serial numbers
	public String generateRan() {
		Random random = new Random();
		String ranLetter = "ABCDEFGHIJKLMNOPQRSTUVWZYZ".toCharArray()[random.nextInt("ABCDEFGHIJKLMNOPQRSTUVWZYZ".toCharArray().length)];
		String ranNumber = Integer.toString(random.nextInt(1000));

		String serial = ranLetter + "-" + ranNumber;
		return serial;
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


	//get the receiving count in the global menu
	public String getReceivingCount() {
		Mobile.tapAndHold(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0, 0)
		String count = Mobile.getText(findTestObject('Android/PG_Receiving/lbl_ReceivingCount', [('idf_LabelName') : 'Receiving']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenuBack'), 0)
		return count;
	}

	//This method returns the Home Receiving tile counter badge count
	public String getReceivingCountinTile() {
		Thread.sleep(2000)
		String count = Mobile.getText(findTestObject('Android/PG_Common/lbl_CommonByUpperLabelName', [('idf_LabelName') : 'Receiving']), 0)
		return count;
	}

	/**
	 * TA-Receiving List: Availability of the Receiving global menu item
	 */
	@Keyword
	public void bf_ReceivingList_AvailabilityOfReceivingGlobalMenuItem() {
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.verifyElementExist(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Receiving']), 0)
		Thread.sleep(2000)

		String count = Mobile.getText(findTestObject('Android/PG_Common/lbl_CommonByUpperLabelName', [('idf_LabelName') : 'Receiving']), 0)
		if(count == 0) {
			Mobile.verifyElementNotExist(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Receiving']), 0)
		}
		Mobile.pressBack()

	}

	/**
	 * TA-Receiving List: Verify data against the FSM Client
	 */
	@Keyword
	public void bf_ReceivingList_VerifyDataAgainstFSMClient() {
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByUpperLabelName', [('idf_LabelName') : 'Receiving']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenuBack'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByUpperLabelName', [('idf_LabelName') : 'Receiving']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Receiving']), 0)

		String ReceivingListCount = getReceivingCount()
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonContainsByText',[('idf_LabelName') :ReceivingListCount ]), 0)
		Thread.sleep(2000)

		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Home']), 0)
		Thread.sleep(2000)
		String count = Mobile.getText(findTestObject('Android/PG_Common/lbl_CommonByUpperLabelName', [('idf_LabelName') : 'Receiving']), 0)
		count =getReceivingCount()
	}


	/**
	 * TA-Receiving List:Verify navigation from the Receiving List
	 */
	@Keyword
	public void bf_ReceivingList_VerifyNavigationfromReceivingList() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'SHIP']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Part List"]), 0)
		Mobile.pressBack()
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable', [('idf_TextName') : 'PO']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PO Part List"]), 0)

	}

	/**
	 * TA-Receiving Unit List: Receive complete shipment: non-tracked part
	 */
	@Keyword
	public void bf_UnitList_ReceiveCompleteShipmentNonTrackedPart() {
		Thread.sleep(2000)
		String beforeCount = getReceivingCount()
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : '11801']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementChecked(findTestObject('Android/PG_Common/chk_FirstCheckBoxFromList'), 0)
		Mobile.tap(findTestObject('Android/PG_Receiving/btn_Process'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Receiving"]), 0)
		Mobile.verifyElementNotExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "11801"]), 0)
		Thread.sleep(3000)
		String afterCount = getReceivingCount()
		Thread.sleep(3000)
		Mobile.verifyLessThan(afterCount,beforeCount)
		Thread.sleep(3000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonContainsByText',[('idf_LabelName') :afterCount ]), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Home']), 0)
		Thread.sleep(2000)
		afterCount=getReceivingCountinTile()
	}

	/**
	 * TA-Receiving Unit List: Receive partial shipment: non-tracked part
	 */
	@Keyword
	public void bf_UnitList_ReceivePartialShipmentNonTrackedPart(String quantity) {
		String beforeCount = getReceivingCount()
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : '11806']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementChecked(findTestObject('Android/PG_Common/chk_FirstCheckBoxFromList'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'TA-Non Tracked Part1']), 0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Receiving/tf_Quantity'),quantity , 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'UPDATE']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementChecked(findTestObject('Android/PG_Common/chk_FirstCheckBoxFromList'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : '1']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Receiving/btn_Process'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Receiving"]), 0)
		Mobile.verifyElementNotExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "11806"]), 0)
		String afterCount = getReceivingCount()
		Thread.sleep(3000)
		Mobile.verifyLessThan(afterCount,beforeCount)
		Thread.sleep(3000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonContainsByText',[('idf_LabelName') :afterCount ]), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Home']), 0)
		Thread.sleep(2000)
		afterCount=getReceivingCountinTile()
	}

	/**
	 * TA-Receiving Unit List: Receive complete shipment: non-tracked parts with multiple receiving units
	 */
	@Keyword
	public void bf_UnitList_ReceiveCompleteShipmentNonTrackedPartsWithMultipleReceivingUnits() {
		String beforeCount = getReceivingCount()
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : '11811']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementChecked(findTestObject('Android/PG_Common/chk_FirstCheckBoxFromList'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : '1']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Receiving/btn_Process'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Receiving"]), 0)
		Mobile.verifyElementNotExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "11811"]), 0)
		String afterCount = getReceivingCount()
		Thread.sleep(3000)
		Mobile.verifyLessThan(afterCount,beforeCount)
		Thread.sleep(3000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonContainsByText',[('idf_LabelName') :afterCount ]), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Home']), 0)
		Thread.sleep(2000)
		afterCount=getReceivingCountinTile()
	}

	/**
	 * TA-Receiving Unit List: Receive partial shipment: non-tracked parts with multiple receiving units
	 */
	@Keyword
	public void bf_UnitList_ReceivePartialShipmentnNonTrackedPartsMultipleReceivingUnits() {
		String beforeCount = getReceivingCount()
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : '11814']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementChecked(findTestObject('Android/PG_Common/chk_FirstCheckBoxFromList'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : '1']), 0)
		Thread.sleep(2000)
		Mobile.uncheckElement(findTestObject('Android/PG_Common/chk_FirstCheckBoxFromList'), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Receiving/btn_Process'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Receiving"]), 0)
		Mobile.verifyElementNotExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "11814"]), 0)
		String afterCount = getReceivingCount()
		Thread.sleep(3000)
		Mobile.verifyLessThan(afterCount,beforeCount)
		Thread.sleep(3000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonContainsByText',[('idf_LabelName') :afterCount ]), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Home']), 0)
		Thread.sleep(2000)
		afterCount=getReceivingCountinTile()
	}

	/**
	 * TA-Receiving Unit List: Receive complete shipment: serial tracked part
	 */
	@Keyword
	public void bf_UnitList_ReceiveCompleteShipmentSerialTrackedPart() {
		String beforeCount = getReceivingCount()
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable', [('idf_TextName') : '11818']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementChecked(findTestObject('Android/PG_Common/chk_FirstCheckBoxFromList'), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Receiving/btn_Process'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Receiving"]), 0)
		Mobile.verifyElementNotExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "11818"]), 0)
		String afterCount = getReceivingCount()
		Thread.sleep(3000)
		Mobile.verifyLessThan(afterCount,beforeCount)
		Thread.sleep(3000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonContainsByText',[('idf_LabelName') :afterCount ]), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Home']), 0)
		Thread.sleep(2000)
		afterCount=getReceivingCountinTile()
	}

	/**
	 * TA-Receiving Unit List: Receive partial shipment: serial tracked part
	 */
	@Keyword
	public void bf_UnitList_ReceivePartialShipmentSerialtrackedPart() {
		String beforeCount = getReceivingCount()
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable', [('idf_TextName') : '11822']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementChecked(findTestObject('Android/PG_Common/chk_FirstCheckBoxFromList'), 0)
		Thread.sleep(2000)
		Mobile.uncheckElement(findTestObject('Android/PG_Common/chk_FirstCheckBoxFromList'), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Receiving/btn_Process'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Receiving"]), 0)
		Mobile.verifyElementNotExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "11822"]), 0)
		String afterCount = getReceivingCount()
		Thread.sleep(3000)
		Mobile.verifyLessThan(afterCount,beforeCount)
		Thread.sleep(3000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonContainsByText',[('idf_LabelName') :afterCount ]), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Home']), 0)
		Thread.sleep(2000)
		afterCount=getReceivingCountinTile()
	}

	/**
	 * TA-Receiving Unit List: Receive complete shipment: lot-tracked part with multiple receiving units
	 */
	@Keyword
	public void bf_UnitList_ReceiveCompleteShipmentLottrackedPart() {
		String beforeCount = getReceivingCount()
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable', [('idf_TextName') : '11826']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementChecked(findTestObject('Android/PG_Common/chk_FirstCheckBoxFromList'), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Receiving/btn_Process'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Receiving"]), 0)
		Mobile.verifyElementNotExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "11826"]), 0)
		String afterCount = getReceivingCount()
		Thread.sleep(3000)
		Mobile.verifyLessThan(afterCount,beforeCount)
		Thread.sleep(3000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonContainsByText',[('idf_LabelName') :afterCount ]), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Home']), 0)
		Thread.sleep(2000)
		afterCount=getReceivingCountinTile()
	}

	/**
	 * TA-Receiving Unit List: Receive partial shipment: lot-tracked part
	 */
	@Keyword
	public void bf_UnitList_ReceivePartialShipmentLotTrackedPart(String quantity) {
		String beforeCount = getReceivingCount()
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable', [('idf_TextName') : '11900']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementChecked(findTestObject('Android/PG_Common/chk_FirstCheckBoxFromList'), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'TA-LOT-PART1']), 0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Receiving/tf_Quantity'),quantity , 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'UPDATE']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementChecked(findTestObject('Android/PG_Common/chk_FirstCheckBoxFromList'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : '1']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Receiving/btn_Process'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Receiving"]), 0)
		Mobile.verifyElementNotExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "11900"]), 0)
		String afterCount = getReceivingCount()
		Thread.sleep(3000)
		Mobile.verifyLessThan(afterCount,beforeCount)
		Thread.sleep(3000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonContainsByText',[('idf_LabelName') :afterCount ]), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Home']), 0)
		Thread.sleep(2000)
		afterCount=getReceivingCountinTile()
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Sync']), 0)
		Thread.sleep(7000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Stock']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'TA-LOT-PART1']), 0)
		Thread.sleep(2000)
	}

	/**
	 * TA-Receiving Detail List: Verify available data
	 */
	@Keyword
	public void bf_DetailList_VerifyAvailableData() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable', [('idf_TextName') : 'PO']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "TA-NON-PART1"]), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "0"]), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonContainsByText',[('idf_LabelName') : "10"]), 0)
	}

	/**
	 * TA-Receiving Detail List: Process-without adding any receiving units
	 */
	@Keyword
	public void bf_DetailList_ProcesswithoutAddingReceivingUnits() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable', [('idf_TextName') : '11922']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Receiving/btn_Process'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_ErrorMessageForRequiredFields'), 0)
	}

	/**
	 * TA-Receiving Detail List: Verify navigation to the ReceivingUnitPO screen
	 */
	@Keyword
	public void bf_DetailList_VerifyNavigationtoReceivingUnitPOScreen() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable', [('idf_TextName') : 'PO']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "TA-NON-PART1"]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PO Receiving Unit"]), 0)
	}

	/**
	 * TA-Receiving Unit PO: Add serial tracked units-FSMa: verify validation for serial ID
	 */
	@Keyword
	public void bf_UnitPO_AddSerialTrackedUnitsVerifyValidationSerialID() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable', [('idf_TextName') : 'PO']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "TA-SER-PART1"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Add'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_ErrorMessageForRequiredFields'), 0)
	}

	/**
	 * TA-Receiving Unit PO: Add serial tracked units-verify the Finish button behavior
	 */
	@Keyword
	public void bf_UnitPO_AddSerialTrackedUnitsVerifyFinishButtonBehavior() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable', [('idf_TextName') : 'PO']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "TA-SER-PART1"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Receiving/btn_Finish'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PO Part List"]), 0)
	}

	/**
	 * TA-Receiving Unit PO: Add serial tracked units
	 */
	@Keyword
	public void bf_UnitPO_AddSerialTrackedUnits() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable', [('idf_TextName') : 'PO']), 0)
		Thread.sleep(2000)
		String beforevalue=Mobile.getText(findTestObject('Android/PG_Receiving/lbl_QtyReceived'),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "TA-SER-PART1"]), 0)
		Thread.sleep(2000)
		String listValuesBefore = Mobile.getText(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		String listValuesBeforeSubstring = listValuesBefore.substring(listValuesBefore.indexOf("(") + 1, listValuesBefore.indexOf(")"));
		KeywordUtil.markPassed("No of List Values before adding a part " + listValuesBeforeSubstring + " ")
		Thread.sleep(2000)
		String serial1 = generateRan();
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Serial ID']), serial1, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Add'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PO Receiving Unit"]), 0)
		String listValuesAfter = Mobile.getText(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		String listValuesAfterSubstring = listValuesAfter.substring(listValuesAfter.indexOf("(") + 1, listValuesAfter.indexOf(")"));
		KeywordUtil.markPassed("No of List Values after adding a new part " + listValuesAfterSubstring + " ")
		Thread.sleep(2000)
		Boolean listvaluesresult = Mobile.verifyGreaterThan(listValuesAfterSubstring, listValuesBeforeSubstring)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : serial1]), 0)
		Thread.sleep(2000)
		Mobile.pressBack()
		Mobile.tap(findTestObject('Android/PG_Receiving/btn_Finish'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PO Part List"]), 0)
		Thread.sleep(2000)
		String aftervalue=Mobile.getText(findTestObject('Android/PG_Receiving/lbl_QtyReceived'),0)
		Thread.sleep(2000)
		Mobile.verifyGreaterThan(aftervalue,beforevalue)
	}

	/**
	 * TA-Receiving Unit PO: Add lot tracked units-FSMa: verify validations for mandatory fields
	 */
	@Keyword
	public void bf_UnitPO_AddLotTrackedUnitsVerifyValidationsMandatoryFields() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable', [('idf_TextName') : 'PO']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "TA-LOT-PART1"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Add'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_ErrorMessageForRequiredFields'), 0)
	}

	/**
	 * TA-Receiving Unit PO: Add lot identified units
	 */
	@Keyword
	public void bf_UnitPO_AddLotIdentifiedUnits(String quantity,String LotID) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable', [('idf_TextName') : 'PO']), 0)
		Thread.sleep(2000)
		String beforevalue=Mobile.getText(findTestObject('Android/PG_Receiving/lbl_LotPartQty'),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "TA-LOT-PART1"]), 0)
		Thread.sleep(2000)
		String listValuesBefore = Mobile.getText(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		String listValuesBeforeSubstring = listValuesBefore.substring(listValuesBefore.indexOf("(") + 1, listValuesBefore.indexOf(")"));
		KeywordUtil.markPassed("No of List Values before adding a part " + listValuesBeforeSubstring + " ")
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), quantity, 3)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Lot ID']), LotID, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Add'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PO Receiving Unit"]), 0)
		String listValuesAfter = Mobile.getText(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		String listValuesAfterSubstring = listValuesAfter.substring(listValuesAfter.indexOf("(") + 1, listValuesAfter.indexOf(")"));
		KeywordUtil.markPassed("No of List Values after adding a new part " + listValuesAfterSubstring + " ")
		Boolean listvaluesresult = Mobile.verifyGreaterThan(listValuesAfterSubstring, listValuesBeforeSubstring)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : LotID]), 0)
		Thread.sleep(2000)
		Mobile.pressBack()
		Mobile.tap(findTestObject('Android/PG_Receiving/btn_Finish'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PO Part List"]), 0)
		Thread.sleep(2000)
		String aftervalue=Mobile.getText(findTestObject('Android/PG_Receiving/lbl_LotPartQty'),0)
		Thread.sleep(2000)
		Mobile.verifyGreaterThan(aftervalue,beforevalue)
	}

	/**
	 * TA-Receiving Unit PO: Add lot identified and serialized units-FSMa: verify validations for mandatory fields
	 */
	@Keyword
	public void bf_UnitPO_AddLotIdentifiedSerializedUnitsVerifyValidationsMandatoryFields() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable', [('idf_TextName') : 'PO']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "TA-SER-LOT-PART1"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Add'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_ErrorMessageForRequiredFields'), 0)
	}

	/**
	 * TA-Receiving Unit PO: Add lot identified and serialized units
	 */
	@Keyword
	public void bf_UnitPO_AddLotIdentifiedSerializedUnits(String lotID) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable', [('idf_TextName') : 'PO']), 0)
		Thread.sleep(2000)
		String beforevalue=Mobile.getText(findTestObject('Android/PG_Receiving/lbl_SerialLotQty'),0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "TA-SER-LOT-PART1"]), 0)
		Thread.sleep(2000)
		String listValuesBefore = Mobile.getText(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		String listValuesBeforeSubstring = listValuesBefore.substring(listValuesBefore.indexOf("(") + 1, listValuesBefore.indexOf(")"));
		KeywordUtil.markPassed("No of List Values before adding a part " + listValuesBeforeSubstring + " ")
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Lot ID']), lotID, 3)
		Thread.sleep(2000)
		String serial = generateRan();
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Serial ID']), serial, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Add'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PO Receiving Unit"]), 0)
		String listValuesAfter = Mobile.getText(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		String listValuesAfterSubstring = listValuesAfter.substring(listValuesAfter.indexOf("(") + 1, listValuesAfter.indexOf(")"));
		KeywordUtil.markPassed("No of List Values after adding a new part " + listValuesAfterSubstring + " ")
		Boolean listvaluesresult = Mobile.verifyGreaterThan(listValuesAfterSubstring, listValuesBeforeSubstring)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : serial]), 0)
		Thread.sleep(2000)
		Mobile.pressBack()
		Mobile.tap(findTestObject('Android/PG_Receiving/btn_Finish'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PO Part List"]), 0)
		Thread.sleep(2000)
		String aftervalue=Mobile.getText(findTestObject('Android/PG_Receiving/lbl_SerialLotQty'),0)
		Thread.sleep(2000)
		Mobile.verifyGreaterThan(aftervalue,beforevalue)

	}

	/**
	 * TA-Receiving Unit PO: Modify Quantities: Non-tracked parts-FSMa: verify validations for mandatory fields
	 */
	@Keyword
	public void bf_UnitPO_NonTrackedPartsVerifyvalidationsMandatoryFields() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable', [('idf_TextName') : 'PO']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "TA-NON-PART1"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Add'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_ErrorMessageForRequiredFields'), 0)
	}

	/**
	 * TA-Receiving Unit PO: Modify Quantities: Non-tracked parts
	 */
	@Keyword
	public void bf_UnitPO_ModifyQuantitiesNonTrackedParts(String quantity) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable', [('idf_TextName') : 'PO']), 0)
		Thread.sleep(2000)
		String beforevalue=Mobile.getText(findTestObject('Android/PG_Receiving/lbl_NonPartQty'),0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "TA-NON-PART1"]), 0)
		Thread.sleep(2000)
		String listValuesBefore = Mobile.getText(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		String listValuesBeforeSubstring = listValuesBefore.substring(listValuesBefore.indexOf("(") + 1, listValuesBefore.indexOf(")"));
		KeywordUtil.markPassed("No of List Values before adding a part " + listValuesBeforeSubstring + " ")
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), quantity, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Add'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PO Receiving Unit"]), 0)
		String listValuesAfter = Mobile.getText(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		String listValuesAfterSubstring = listValuesAfter.substring(listValuesAfter.indexOf("(") + 1, listValuesAfter.indexOf(")"));
		KeywordUtil.markPassed("No of List Values after adding a new part " + listValuesAfterSubstring + " ")
		Boolean listvaluesresult = Mobile.verifyGreaterThan(listValuesAfterSubstring, listValuesBeforeSubstring)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : quantity]), 0)
		Thread.sleep(2000)
		Mobile.pressBack()
		Mobile.tap(findTestObject('Android/PG_Receiving/btn_Finish'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PO Part List"]), 0)
		Thread.sleep(2000)
		String aftervalue=Mobile.getText(findTestObject('Android/PG_Receiving/lbl_NonPartQty'),0)
		Thread.sleep(2000)
		Mobile.verifyGreaterThan(aftervalue,beforevalue)
	}

	/**
	 * TA-Receiving Unit PO List: Verify navigation from the ReceivingUnitPO screen
	 */
	@Keyword
	public void bf_UnitPO_VerifyNavigationfromReceivingUnitPOScreen() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable', [('idf_TextName') : 'PO']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "TA-NON-PART1"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PO Receiving Unit List"]), 0)
	}

	/**
	 * TA-Receiving Unit PO List: Verify count and data available on a list item on the ReceivingUnitPOList screen
	 */
	@Keyword
	public void bf_UnitPO_VerifyCountAvailableonListItemonReceivingUnitPOListScreen() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable', [('idf_TextName') : 'PO']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "TA-SER-LOT-PART1"]), 0)
		Thread.sleep(2000)
		String listValue = Mobile.getText(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		String listValueSubstring = listValue.substring(listValue.indexOf("(") + 1, listValue.indexOf(")"));
		KeywordUtil.markPassed("No of List Values" + listValueSubstring + " ")
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		Thread.sleep(2000)
		AppiumDriver driver = MobileDriverFactory.getDriver()
		driver.findElements(By.xpath("//android.widget.TextView[contains(@text,'TA')]")).size()
		int x =	driver.findElements(By.xpath("//android.widget.TextView[contains(@text,'TA')]")).size()
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'TA-SER-LOT-PART1']), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : '1']), 0)
		Mobile.verifyEqual(listValueSubstring, x)
	}

	/**
	 * TA-Receiving Unit PO List: Modify Units-Serialized parts
	 */
	@Keyword
	public void bf_UnitPO_ModifyUnitsSerializedParts() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable', [('idf_TextName') : 'PO']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "TA-SER-PART1"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "TA-SER-PART1"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText',[('idf_ButtonName') : "MODIFY"]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "TA-SER-PART1"]), 0)
		Thread.sleep(2000)
		String serial2 = generateRan();
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Serial ID']), serial2, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_WorkList/btn_Update'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PO Receiving Unit List"]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : serial2]), 0)
	}

	/**
	 * TA-Receiving Unit PO List: Modify Units- Lot identified parts
	 */
	@Keyword
	public void bf_UnitPO_ModifyUnitsLotIdentifiedParts(String lotID,String quantity) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable', [('idf_TextName') : 'PO']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "TA-LOT-PART1"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "TA-LOT-PART1"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText',[('idf_ButtonName') : "MODIFY"]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "TA-LOT-PART1"]), 0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Lot ID']), lotID, 3)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), quantity, 3)
		Thread.sleep(2000)
		tapLookUpIcon('Expiration Date')
		Mobile.tap(findTestObject('Android/PG_POProcess/tv_date'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText',[('idf_ButtonName') : "OK"]), 0)
		Mobile.tap(findTestObject('Android/PG_WorkList/btn_Update'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PO Receiving Unit List"]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : lotID]), 0)
	}

	/**
	 * TA-Receiving Unit PO List: Modify Units- Serialized lot identified parts
	 */
	@Keyword
	public void bf_UnitPO_ModifyUnitsSerializedLotIdentifiedParts(String lotID) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable', [('idf_TextName') : 'PO']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "TA-SER-LOT-PART1"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "TA-SER-LOT-PART1"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText',[('idf_ButtonName') : "MODIFY"]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "TA-SER-LOT-PART1"]), 0)
		Thread.sleep(2000)
		String serial3 = generateRan();
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Serial ID']), serial3, 3)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Lot ID']), lotID, 3)
		Thread.sleep(2000)
		tapLookUpIcon('Expiration Date')
		Mobile.tap(findTestObject('Android/PG_POProcess/tv_date'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText',[('idf_ButtonName') : "OK"]), 0)
		Mobile.tap(findTestObject('Android/PG_WorkList/btn_Update'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PO Receiving Unit List"]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : lotID]), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : serial3]), 0)
	}

	/**
	 * TA-Receiving Unit PO List: Modify Units- Non-tracked parts
	 */
	@Keyword
	public void bf_UnitPO_ModifyUnitsNonTrackedParts(String quantity) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable', [('idf_TextName') : 'PO']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "TA-NON-PART1"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "TA-NON-PART1"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText',[('idf_ButtonName') : "MODIFY"]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "TA-NON-PART1"]), 0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), quantity, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_WorkList/btn_Update'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PO Receiving Unit List"]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : quantity]), 0)
	}

	/**
	 * TA-Receiving Unit PO List: Delete Units- Serialized parts
	 */
	@Keyword
	public void bf_UnitPO_DeleteUnitsSerializedParts() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable', [('idf_TextName') : 'PO']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "TA-SER-PART1"]), 0)
		Thread.sleep(2000)
		String listValuesBefore = Mobile.getText(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		String listValuesBeforeSubstring = listValuesBefore.substring(listValuesBefore.indexOf("(") + 1, listValuesBefore.indexOf(")"));
		KeywordUtil.markPassed("No of List Values before adding a part " + listValuesBeforeSubstring + " ")
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		Thread.sleep(2000)
		AppiumDriver driver = MobileDriverFactory.getDriver()
		driver.findElements(By.xpath("//android.widget.TextView[contains(@text,'TA')]")).size()
		int beforeValue =	driver.findElements(By.xpath("//android.widget.TextView[contains(@text,'TA')]")).size()
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "TA-SER-PART1"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText',[('idf_ButtonName') : "DELETE"]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PO Receiving Unit List"]), 0)
		Thread.sleep(2000)
		driver.findElements(By.xpath("//android.widget.TextView[contains(@text,'TA')]")).size()
		int afterValue =	driver.findElements(By.xpath("//android.widget.TextView[contains(@text,'TA')]")).size()
		Mobile.verifyLessThan(afterValue,beforeValue)
		Mobile.pressBack()
		String listValuesAfter = Mobile.getText(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		String listValuesAfterSubstring = listValuesAfter.substring(listValuesAfter.indexOf("(") + 1, listValuesAfter.indexOf(")"));
		KeywordUtil.markPassed("No of List Values after adding a new part " + listValuesAfterSubstring + " ")
		Boolean listvaluesresult = Mobile.verifyLessThan(listValuesAfterSubstring, listValuesBeforeSubstring)
		Thread.sleep(2000)
	}

	/**
	 * TA-Receiving Unit PO List: Delete Units- Lot identified parts
	 */
	@Keyword
	public void bf_UnitPO_DeleteUnitsLotIdentifiedParts() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable', [('idf_TextName') : 'PO']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "TA-LOT-PART1"]), 0)
		Thread.sleep(2000)
		String listValuesBefore = Mobile.getText(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		String listValuesBeforeSubstring = listValuesBefore.substring(listValuesBefore.indexOf("(") + 1, listValuesBefore.indexOf(")"));
		KeywordUtil.markPassed("No of List Values before adding a part " + listValuesBeforeSubstring + " ")
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		Thread.sleep(2000)
		AppiumDriver driver = MobileDriverFactory.getDriver()
		driver.findElements(By.xpath("//android.widget.TextView[contains(@text,'TA')]")).size()
		int beforeValue =	driver.findElements(By.xpath("//android.widget.TextView[contains(@text,'TA')]")).size()
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "TA-LOT-PART1"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText',[('idf_ButtonName') : "DELETE"]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PO Receiving Unit List"]), 0)
		Thread.sleep(2000)
		driver.findElements(By.xpath("//android.widget.TextView[contains(@text,'TA')]")).size()
		int afterValue =	driver.findElements(By.xpath("//android.widget.TextView[contains(@text,'TA')]")).size()
		Mobile.verifyLessThan(afterValue,beforeValue)
		Mobile.pressBack()
		String listValuesAfter = Mobile.getText(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		String listValuesAfterSubstring = listValuesAfter.substring(listValuesAfter.indexOf("(") + 1, listValuesAfter.indexOf(")"));
		KeywordUtil.markPassed("No of List Values after adding a new part " + listValuesAfterSubstring + " ")
		Boolean listvaluesresult = Mobile.verifyLessThan(listValuesAfterSubstring, listValuesBeforeSubstring)
		Thread.sleep(2000)
	}

	/**
	 * TA-Receiving Unit PO List: Delete Units- Serialized lot identified parts
	 */
	@Keyword
	public void bf_UnitPO_DeleteUnitsSerializedLotIdentifiedParts() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable', [('idf_TextName') : 'PO']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "TA-SER-LOT-PART1"]), 0)
		Thread.sleep(2000)
		String listValuesBefore = Mobile.getText(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		String listValuesBeforeSubstring = listValuesBefore.substring(listValuesBefore.indexOf("(") + 1, listValuesBefore.indexOf(")"));
		KeywordUtil.markPassed("No of List Values before adding a part " + listValuesBeforeSubstring + " ")
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		Thread.sleep(2000)
		AppiumDriver driver = MobileDriverFactory.getDriver()
		driver.findElements(By.xpath("//android.widget.TextView[contains(@text,'TA')]")).size()
		int beforeValue =	driver.findElements(By.xpath("//android.widget.TextView[contains(@text,'TA')]")).size()
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "TA-SER-LOT-PART1"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText',[('idf_ButtonName') : "DELETE"]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PO Receiving Unit List"]), 0)
		Thread.sleep(2000)
		driver.findElements(By.xpath("//android.widget.TextView[contains(@text,'TA')]")).size()
		int afterValue =driver.findElements(By.xpath("//android.widget.TextView[contains(@text,'TA')]")).size()
		Mobile.verifyLessThan(afterValue,beforeValue)
		Mobile.pressBack()
		String listValuesAfter = Mobile.getText(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		String listValuesAfterSubstring = listValuesAfter.substring(listValuesAfter.indexOf("(") + 1, listValuesAfter.indexOf(")"));
		KeywordUtil.markPassed("No of List Values after adding a new part " + listValuesAfterSubstring + " ")
		Boolean listvaluesresult = Mobile.verifyLessThan(listValuesAfterSubstring, listValuesBeforeSubstring)
		Thread.sleep(2000)
	}

	/**
	 * TA-Receiving Unit PO List: Delete Units- Non-tracked parts
	 */
	@Keyword
	public void bf_UnitPO_DeleteUnitsNonTrackedParts() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable', [('idf_TextName') : 'PO']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "TA-NON-PART1"]), 0)
		Thread.sleep(2000)
		String listValuesBefore = Mobile.getText(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		String listValuesBeforeSubstring = listValuesBefore.substring(listValuesBefore.indexOf("(") + 1, listValuesBefore.indexOf(")"));
		KeywordUtil.markPassed("No of List Values before adding a part " + listValuesBeforeSubstring + " ")
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		Thread.sleep(2000)
		AppiumDriver driver = MobileDriverFactory.getDriver()
		driver.findElements(By.xpath("//android.widget.TextView[contains(@text,'TA')]")).size()
		int beforeValue =	driver.findElements(By.xpath("//android.widget.TextView[contains(@text,'TA')]")).size()
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "TA-NON-PART1"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText',[('idf_ButtonName') : "DELETE"]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PO Receiving Unit List"]), 0)
		Thread.sleep(2000)
		driver.findElements(By.xpath("//android.widget.TextView[contains(@text,'TA')]")).size()
		int afterValue =	driver.findElements(By.xpath("//android.widget.TextView[contains(@text,'TA')]")).size()
		Mobile.verifyLessThan(afterValue,beforeValue)
		Mobile.pressBack()
		String listValuesAfter = Mobile.getText(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		String listValuesAfterSubstring = listValuesAfter.substring(listValuesAfter.indexOf("(") + 1, listValuesAfter.indexOf(")"));
		KeywordUtil.markPassed("No of List Values after adding a new part " + listValuesAfterSubstring + " ")
		Boolean listvaluesresult = Mobile.verifyLessThan(listValuesAfterSubstring, listValuesBeforeSubstring)
		Thread.sleep(2000)
	}

	/**
	 * TA-Receiving Detail List: Process partial receipt-Decline process confirmation
	 */
	@Keyword
	public void bf_DetailList_ProcessPartialReceiptDeclineProcessConfirmation() {
		//to be continued
	}

	/**
	 * TA-Receiving Detail List: Process partial receipt
	 */
	@Keyword
	public void bf_DetailList_ProcessPartialReceipt() {
		//to be continued
	}

	/**
	 * TA-Receiving Detail List: Process full receipt
	 */
	@Keyword
	public void bf_DetailList_ProcessFullReceipt() {
		//to be continued
	}


}
