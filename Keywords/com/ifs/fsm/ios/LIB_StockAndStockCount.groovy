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
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import io.appium.java_client.AppiumDriver
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.util.KeywordUtil

public class LIB_StockAndStockCount {
	//Click the lookup icon inside a EditText field
	//Takes the name of the TextField present before the EditText as the parameter
	public void clickLookUpIcon(String upperlabelName) {
		int x = Mobile.getElementLeftPosition(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : upperlabelName]), 0)
		int width = Mobile.getElementWidth(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : upperlabelName]), 0)
		int y = Mobile.getElementTopPosition(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : upperlabelName]), 0)

		int xNew = ((x/2) + width) - 5;
		int yNew = y + 5;

		Mobile.tapAtPosition(xNew, yNew )
		Mobile.delay(3)
	}

	//This method generate random serial numbers
	public String generateRan() {
		Random random = new Random();
		String ranLetter1 = "ABCDEFGHIJKLMNOPQRSTUVWZYZ".toCharArray()[random.nextInt("ABCDEFGHIJKLMNOPQRSTUVWZYZ".toCharArray().length)];
		String ranLetter2 = "ABCDEFGHIJKLMNOPQRSTUVWZYZ".toCharArray()[random.nextInt("ABCDEFGHIJKLMNOPQRSTUVWZYZ".toCharArray().length)];
		String ranNumber = Integer.toString(random.nextInt(1000));

		String serial = ranLetter1 + ranLetter2 + "-" + ranNumber;
		return serial;
	}

	// This method scroll and click on any element in a picker wheel
	public void clickElementFromPickerWheel(String labelName) {
		TestObject pickerWheel = new TestObject();
		pickerWheel.addProperty("xpath", ConditionType.EQUALS, "//XCUIElementTypePickerWheel[@index='0']");
		MobileBuiltInKeywords.sendKeys(pickerWheel, labelName);
	}

	//This method captures the coordinates of the passed label name and performs a right to left swipe on the element
	public void swipeDelete(String labelName) {
		Thread.sleep(3000)
		int startingX = Mobile.getDeviceWidth() - 5
		int endingX = Mobile.getElementLeftPosition(findTestObject('iOS/PG_Common/lbl_FirstElementFromList', [('idf_LabelName') : labelName]), 0)
		int y = Mobile.getElementTopPosition(findTestObject('iOS/PG_Common/lbl_FirstElementFromList', [('idf_LabelName') : labelName]), 0)

		Mobile.swipe(startingX, y, endingX, y)
	}

	public void addPartCommonValues(String adjustmentReason, String partID, String quantity, String placeID, String location) {
		Mobile.tap(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Adjustment Reason']), 0)
		clickElementFromPickerWheel(adjustmentReason)

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Part ID']), partID, 0)
		clickLookUpIcon('Part ID');
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_FirstElementFromList', [('idf_LabelName') : partID]), 0)
		Mobile.delay(2)

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Place ID To']), placeID, 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Location To']), location, 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), quantity, 0)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0)
		Mobile.delay(2)
	}

	public void addPartCommonValuesOnRemove(String adjustmentReason, String partID, String quantity, String placeID, String location) {
		Mobile.tap(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Adjustment Reason']), 0)
		clickElementFromPickerWheel(adjustmentReason)

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Part ID']), partID, 0)
		clickLookUpIcon('Part ID');
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_FirstElementFromList', [('idf_LabelName') : partID]), 0)
		Mobile.delay(2)

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Place ID From']), placeID, 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Location From']), location, 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), quantity, 0)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0)
		Mobile.delay(2)
	}

	public void addPartCommonValuesOnSwap(String adjustmentReason, String partID, String quantity,  String placeIDTo, String locationTo, String placeIDFrom, String locationFrom) {
		Mobile.tap(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Adjustment Reason']), 0)
		clickElementFromPickerWheel(adjustmentReason)

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Part ID']), partID, 0)
		clickLookUpIcon('Part ID');
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_FirstElementFromList', [('idf_LabelName') : partID]), 0)
		Mobile.delay(2)

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Place ID From']), placeIDFrom, 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Location From']), locationFrom, 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Place ID To']), placeIDTo, 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Location To']), locationTo, 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), quantity, 0)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0)
		Mobile.delay(2)
	}

	//Adding a serial ID in stock adjustment screens
	public void addSerialIDInStockAdjustment() {
		String serial = generateRan();
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Serial ID']), serial, 0)
		Mobile.delay(2)
		Mobile.tap(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), 0)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add Serial']), 0)
	}

	//Adding a serial ID in stock count screen
	public void addSerialIDInStockCount() {
		String serial = generateRan();
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Serial ID']), serial, 0)
		Mobile.delay(2)
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock Count Detail']), 0)
		Mobile.delay(2)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']), 0)
	}

	//Add serial ID by clicking lookup icon
	public void addSerialIDFromLoookUp(String partID) {
		clickLookUpIcon('Serial ID');
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_FirstElementFromList', [('idf_LabelName') : partID]), 0)
		Mobile.delay(2)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add Serial']), 0)
	}

	//Add two serial IDs by clicking lookup icon
	public void addTwoSerialIDsFromLoookUp(String partID) {
		clickLookUpIcon('Serial ID');
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_FirstElementFromList', [('idf_LabelName') : partID]), 0)
		Mobile.delay(2)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add Serial']), 0)

		clickLookUpIcon('Serial ID');
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_SecondElementFromList', [('idf_LabelName') : partID]), 0)
		Mobile.delay(2)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add Serial']), 0)
	}

	//Add lot ID by clicking lookup icon
	public void addLotIDFromLoookUp(String partID) {
		clickLookUpIcon('Lot ID');
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_FirstElementFromList', [('idf_LabelName') : partID]), 0)
		Mobile.delay(2)
	}

	//Select an element from the drop down in the StockList screen
	public void clickStockListMenuItem(String contextMenuOptionName) {
		Mobile.tap(findTestObject('iOS/PG_Common/btn_DropDownIcon'), 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : contextMenuOptionName]), 0, 0)
		Mobile.delay(2)
	}

	/**
	 * Add a part-Receipt
	 */
	@Keyword
	public void bf_Stock_AddpartReceiptAndVerify(String adjustmentReason, String quantity, String placeID, String location, String serialTrackedPartId, String nonTrackedPartId, String lotTrackedPartId, String serialLotTrackedPartId){
		clickStockListMenuItem('Add Part');
		//Adding serial tracked part
		addPartCommonValues(adjustmentReason, serialTrackedPartId, quantity, placeID, location);

		addSerialIDInStockAdjustment();
		addSerialIDInStockAdjustment();

		String quantityValue = Mobile.getText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), 0)
		Mobile.verifyEqual(quantityValue,'2')

		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Confirm']), 0)

		Mobile.delay(2)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock ']), 0)

		//Adding non tracked part
		clickStockListMenuItem('Add Part');
		addPartCommonValues(adjustmentReason, nonTrackedPartId, quantity, placeID, location);

		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Confirm']), 0)
		Mobile.delay(2)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock']), 0)

		//Adding lot tracked part by using lookup icon
		clickStockListMenuItem('Add Part');
		addPartCommonValues(adjustmentReason, lotTrackedPartId, quantity, placeID, location);

		clickLookUpIcon('Lot ID');
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_FirstElementFromList', [('idf_LabelName') : lotTrackedPartId]), 0)
		Mobile.delay(2)

		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Confirm']), 0)
		Mobile.delay(2)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock']), 0)

		//Adding lot tracked part
		clickStockListMenuItem('Add Part');
		addPartCommonValues(adjustmentReason, lotTrackedPartId, quantity, placeID, location);

		String lotID = generateRan();
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Lot ID']), lotID, 0)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0)
		Mobile.delay(2)

		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Confirm']), 0)
		Mobile.delay(2)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock']), 0)

		//Adding serial lot tracked part
		clickStockListMenuItem('Add Part');
		addPartCommonValues(adjustmentReason, serialLotTrackedPartId, quantity, placeID, location);

		clickLookUpIcon('Lot ID');
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_FirstElementFromList', [('idf_LabelName') : lotTrackedPartId]), 0)

		addSerialIDInStockAdjustment();

		quantityValue = Mobile.getText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), 0)
		Mobile.verifyEqual(quantityValue,'1')

		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Confirm']), 0)
		//Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock']), 5)
	}

	/**
	 * Add  a part-Bad in Receiving
	 */
	@Keyword
	public void bf_Stock_AddPartBadInReceiving(String adjustmentReason, String quantity,String placeID,String location, String serialTrackedPartId, String nonTrackedPartId, String lotTrackedPartId, String serialLotTrackedPartId) {
		clickStockListMenuItem('Add Part');
		int serialPartStockCount = 0;
		//Adding serial tracked part
		addPartCommonValues(adjustmentReason, serialTrackedPartId, quantity, placeID, location);

		String serial = generateRan();
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Serial ID']), serial, 0)
		Mobile.hideKeyboard()
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add Serial']), 0)

		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Confirm']), 0)
		serialPartStockCount += 2;
		Mobile.delay(2)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock']), 0)

		//Adding non tracked part
		clickStockListMenuItem('Add Part');
		addPartCommonValues(adjustmentReason, nonTrackedPartId, quantity, placeID, location);

		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Confirm']), 0)
		Mobile.delay(2)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock']), 0)

		//Adding lot tracked part by using lookup icon
		clickStockListMenuItem('Add Part');
		addPartCommonValues(adjustmentReason, lotTrackedPartId, quantity, placeID, location);

		clickLookUpIcon('Lot ID');
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_FirstElementFromList', [('idf_LabelName') : lotTrackedPartId]), 0)
		Mobile.delay(2)

		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Confirm']), 0)
		Mobile.delay(2)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock']), 0)

		//Adding serial lot tracked part
		clickStockListMenuItem('Add Part');
		addPartCommonValues(adjustmentReason, serialLotTrackedPartId, quantity, placeID, location);
		clickLookUpIcon('Lot ID');

		String serial1 = generateRan();
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Serial ID']), serial1, 0)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add Serial']), 0)

		clickLookUpIcon('Lot ID');
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_FirstElementFromList', [('idf_LabelName') : lotTrackedPartId]), 0)
		Mobile.delay(2)

		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Confirm']), 0)
		Mobile.delay(2)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock']), 0)
		serialPartStockCount += 1;
	}

	/**
	 * Add part-Deletion of series
	 */
	@Keyword
	public void bf_Stock_AddPartDeletionOfSerials(String adjustmentReason, String quantity, String placeID, String location, String serialTrackedPartId) {
		clickStockListMenuItem('Add Part');
		int serialPartStockCount = 0;
		//Adding serial tracked part
		addPartCommonValues(adjustmentReason, serialTrackedPartId, quantity, placeID, location);

		String serial = generateRan();
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Serial ID']), serial, 0)
		Mobile.hideKeyboard()
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add Serial']), 0)

		String serial1 = generateRan();
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Serial ID']), serial1, 0)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add Serial']), 0)

		swipeDelete(serial1);
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Delete']), 0)

		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Confirm']), 0)
		serialPartStockCount += 1;
		Mobile.delay(2)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock ']), 0)
	}

	/**
	 * validate serial addition
	 */
	@Keyword
	public void bf_Stock_AddpartValidateSerialAddition(String adjustmentReason, String quantity, String placeID, String location, String serialTrackedPartID) {
		clickStockListMenuItem('Add Part');
		//Adding serial tracked part
		addPartCommonValues(adjustmentReason, serialTrackedPartID, quantity, placeID, location);

		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Confirm']), 0)
		Mobile.delay(2)

		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_ErrorMessage'), 5)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Please enter at least one serial id.']),0)

		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'OK']), 0)
	}

	/**
	 * validate Lot ID addition
	 */
	@Keyword
	public void bf_Stock_AddpartValidateLotIDAddition(String adjustmentReason, String quantity, String placeID, String location, String lotTrackedPartID) {
		clickStockListMenuItem('Add Part');
		//Adding serial tracked part
		addPartCommonValues(adjustmentReason, lotTrackedPartID, quantity, placeID, location);

		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Confirm']), 0)
		Mobile.delay(2)

		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_ErrorMessage'), 5)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Please specify the lot id for this stock adjustment']),0)

		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'OK']), 0)
	}

	/**
	 * Remove part issue
	 */
	@Keyword
	public void bf_Stock_RemovePartIssue(String adjustmentReason, String quantity, String placeID, String location, String serialTrackedPartId, String nonTrackedPartId, String lotTrackedPartId, String serialLotTrackedPartId) {
		clickStockListMenuItem('Remove Part');
		//Adding serial tracked part
		addPartCommonValues(adjustmentReason, serialTrackedPartId, quantity, placeID, location);

		clickLookUpIcon('Serial ID');
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_FirstElementFromList', [('idf_LabelName') : serialTrackedPartId]), 0)
		Mobile.delay(2)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add Serial']), 0)

		clickLookUpIcon('Serial ID');
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_SecondElementFromList', [('idf_LabelName') : serialTrackedPartId]), 0)
		Mobile.delay(2)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add Serial']), 0)

		String quantityValue = Mobile.getText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), 0)
		Mobile.verifyEqual(quantityValue,'2')

		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Confirm']), 0)
		Mobile.delay(2)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock']), 0)

		//Adding non tracked part
		clickStockListMenuItem('Remove Part');
		addPartCommonValues(adjustmentReason, nonTrackedPartId, quantity, placeID, location);

		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Confirm']), 0)
		Mobile.delay(2)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock']), 0)

		//Adding lot tracked part by using lookup icon
		clickStockListMenuItem('Remove Part');
		addPartCommonValues(adjustmentReason, lotTrackedPartId, quantity, placeID, location);

		clickLookUpIcon('Lot ID');
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_FirstElementFromList', [('idf_LabelName') : lotTrackedPartId]), 0)
		Mobile.delay(2)

		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Confirm']), 0)
		Mobile.delay(2)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock']), 0)

		//Adding serial lot tracked part
		clickStockListMenuItem('Remove Part');
		addPartCommonValues(adjustmentReason, serialLotTrackedPartId, quantity, placeID, location);

		addSerialIDFromLoookUp(serialLotTrackedPartId);

		addLotIDFromLoookUp(serialLotTrackedPartId);

		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add Serial']), 0)

		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Confirm']), 0)
		Mobile.delay(2)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock']), 0)
	}

	/**
	 * Remove part scrap
	 */
	@Keyword
	public void bf_Stock_RemovePartScrap(String adjustmentReasonScrap, String adjustmentReasonIssue, String quantity,String placeID,String location, String serialTrackedPartId, String nonTrackedPartId, String lotTrackedPartId, String serialLotTrackedPartId) {
		//Removing serial tracked part
		clickStockListMenuItem('Remove Part');
		addPartCommonValues(adjustmentReasonScrap, serialTrackedPartId, quantity, placeID, location);

		addSerialIDFromLoookUp(serialLotTrackedPartId);

		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add Serial']), 0)

		String quantityValue = Mobile.getText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), 0)
		Mobile.verifyEqual(quantityValue,'1')

		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Confirm']), 0)
		Mobile.delay(2)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock']), 0)

		//Removing non tracked part
		clickStockListMenuItem('Remove Part');
		addPartCommonValues(adjustmentReasonScrap, nonTrackedPartId, quantity, placeID, location);

		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Confirm']), 0)
		Mobile.delay(2)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock']), 0)

		//Removing lot tracked part by using lookup icon
		clickStockListMenuItem('Remove Part');
		addPartCommonValues(adjustmentReasonScrap, lotTrackedPartId, quantity, placeID, location);

		clickLookUpIcon('Lot ID');
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_FirstElementFromList', [('idf_LabelName') : lotTrackedPartId]), 0)
		Mobile.delay(2)

		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Confirm']), 0)
		Mobile.delay(2)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock']), 0)

		//Removing serial lot tracked part
		clickStockListMenuItem('Remove Part');
		addPartCommonValues(adjustmentReasonIssue, serialLotTrackedPartId, quantity, placeID, location);

		addSerialIDFromLoookUp(serialLotTrackedPartId);

		addLotIDFromLoookUp(serialLotTrackedPartId);

		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add Serial']), 0)

		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Confirm']), 0)
		Mobile.delay(2)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock']), 0)
	}

	/**
	 * Remove part deletion of serials
	 */
	@Keyword
	public void bf_Stock_RemovePartDeletionOfSerials(String adjustmentReason, String quantity, String placeID, String location, String serialTrackedPartID) {
		//Removing serial tracked part
		clickStockListMenuItem('Remove Part');
		addPartCommonValues(adjustmentReason, serialTrackedPartID, quantity, placeID, location);

		addSerialIDFromLoookUp(serialTrackedPartID);

		clickLookUpIcon('Serial ID');
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_SecondElementFromList', [('idf_LabelName') : serialTrackedPartID]), 0)
		Mobile.delay(2)

		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add Serial']), 0)

		String quantityValue = Mobile.getText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), 0)
		Mobile.verifyEqual(quantityValue,'2')

		//swipeDelete(serial1);
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Delete']), 0)

		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Confirm']), 0)
		Mobile.delay(2)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock']), 0)

	}

	/**
	 * Remove part validate serial addition
	 */
	@Keyword
	public void bf_Stock_RemovePartValidateSerialAddition(String adjustmentReason, String quantity, String placeID, String location, String serialTrackedPartIdID) {
		clickStockListMenuItem('Remove Part');
		//Adding serial tracked part
		addPartCommonValues(adjustmentReason, serialTrackedPartIdID, quantity, placeID, location);

		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Confirm']), 0)
		Mobile.delay(2)

		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_ErrorMessage'), 5)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Please enter at least one serial id.']),0)

		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'OK']), 0)
	}

	/**
	 * Remove part validate lot ID addition
	 */
	@Keyword
	public void bf_Stock_RemovePartValidateLotIDAddition(String adjustmentReason, String quantity, String placeID, String location, String lotTrackedPartID) {
		clickStockListMenuItem('Remove Part');
		//Adding serial tracked part
		addPartCommonValues(adjustmentReason, lotTrackedPartID, quantity, placeID, location);

		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Confirm']), 0)
		Mobile.delay(2)

		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_ErrorMessage'), 5)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Please specify the lot id for this stock adjustment']),0)

		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'OK']), 0)
	}

	/**
	 * Swap part-Transfer Parts
	 */
	@Keyword
	public void bf_Stock_SwapPartTransferParts(String adjustmentReason, String quantity, String placeIDTo, String locationTo, String placeIDFrom, String locationFrom, String serialTrackedPartID, String nonTrackedPartId, String lotTrackedPartId, String serialLotTrackedPartId) {
		//Swap serial tracked part
		clickStockListMenuItem('Swap Part');
		addPartCommonValuesOnSwap(adjustmentReason, serialTrackedPartID, quantity, placeIDTo, locationTo, placeIDFrom, locationFrom);

		addTwoSerialIDsFromLoookUp(serialTrackedPartID)

		String quantityValue = Mobile.getText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), 0)
		Mobile.verifyEqual(quantityValue,'2')

		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Confirm']), 0)

		Mobile.delay(2)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock']), 0)

		//Swap non tracked part
		clickStockListMenuItem('Swap Part');
		addPartCommonValuesOnSwap(adjustmentReason, nonTrackedPartId, quantity, placeIDTo, locationTo, placeIDFrom, locationFrom);

		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Confirm']), 0)
		Mobile.delay(2)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock']), 0)

		//Swap lot tracked part by using lookup icon
		clickStockListMenuItem('Swap Part');
		addPartCommonValuesOnSwap(adjustmentReason, lotTrackedPartId, quantity, placeIDTo, locationTo, placeIDFrom, locationFrom);
		addLotIDFromLoookUp(lotTrackedPartId);

		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Confirm']), 0)
		Mobile.delay(2)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock']), 0)

		//Swap serial lot tracked part
		clickStockListMenuItem('Swap Part');
		addPartCommonValuesOnSwap(adjustmentReason, serialLotTrackedPartId, quantity, placeIDTo, locationTo, placeIDFrom, locationFrom);

		addLotIDFromLoookUp(lotTrackedPartId);
		addSerialIDFromLoookUp(lotTrackedPartId);

		quantityValue = Mobile.getText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), 0)
		Mobile.verifyEqual(quantityValue,'1')

		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Confirm']), 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock']), 5)
	}

	/**
	 * Swap parts unusable to unusable
	 */
	@Keyword
	public void bf_Stock_SwapPartUnusabletoUnusable(String adjustmentReason, String quantity, String placeIDTo, String locationTo, String placeIDFrom, String locationFrom, String serialTrackedPartID, String nonTrackedPartId, String lotTrackedPartId, String serialLotTrackedPartId) {
		//same
	}

	/**
	 * Swap parts unusable to usable
	 */
	@Keyword
	public void bf_Stock_SwapPartUnusabletoUsable(String adjustmentReason, String quantity, String placeIDTo, String locationTo, String placeIDFrom, String locationFrom, String serialTrackedPartID, String nonTrackedPartId, String lotTrackedPartId, String serialLotTrackedPartId) {
		//same
	}

	/**
	 * Swap parts usable to unusable
	 */
	@Keyword
	public void bf_Stock_SwapPartUsabletoUnusable(String adjustmentReason, String quantity, String placeIDTo, String locationTo, String placeIDFrom, String locationFrom, String serialTrackedPartID, String nonTrackedPartId, String lotTrackedPartId, String serialLotTrackedPartId) {
		//same
	}

	/**
	 * Swap parts for sale
	 *
	 */
	@Keyword
	public void bf_Stock_SwapPartForSale(String adjustmentReason, String quantity, String placeIDTo, String locationTo, String placeIDFrom, String locationFrom, String serialTrackedPartID, String nonTrackedPartId, String lotTrackedPartId, String serialLotTrackedPartId) {
		//same
	}

	/**
	 * Swap parts deletion of serials
	 */
	@Keyword
	public void bf_Stock_SwapPartDeletionOfSerials(String adjustmentReason, String quantity, String placeIDTo, String locationTo, String placeIDFrom, String locationFrom, String serialTrackedPartID) {
		clickStockListMenuItem('Swap Part');
		addPartCommonValuesOnSwap(adjustmentReason, serialTrackedPartID, quantity, placeIDTo, locationTo, placeIDFrom, locationFrom);

		addTwoSerialIDsFromLoookUp(serialTrackedPartID)

		String quantityValue = Mobile.getText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), 0)
		Mobile.verifyEqual(quantityValue,'2')

		String serialID = Mobile.getText(findTestObject('iOS/PG_StockAndStockCount/PG_StockAdjustment/lbl_FirstElementFromSerialIDList'), 0)
		swipeDelete(serialID);
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Delete']), 0)

		quantityValue = Mobile.getText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), 0)
		Mobile.verifyEqual(quantityValue,'1')

		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Confirm']), 0)
		Mobile.delay(2)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock']), 0)
	}

	/**
	 * Swap parts validate serial addition
	 */
	@Keyword
	public void bf_Stock_SwapPartValidateSerialAddition(String adjustmentReason, String quantity, String placeIDTo, String locationTo, String placeIDFrom, String locationFrom, String serialTrackedPartID) {
		clickStockListMenuItem('Swap Part');
		addPartCommonValuesOnSwap(adjustmentReason, serialTrackedPartID, quantity, placeIDTo, locationTo, placeIDFrom, locationFrom);

		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Confirm']), 0)
		Mobile.delay(2)

		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_ErrorMessage'), 5)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Please enter at least one serial id.']),0)

		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'OK']), 0)
	}

	/**
	 * Swap parts validate lotID addition
	 */
	@Keyword
	public void bf_Stock_SwapPartValidateLotIDAddition(String adjustmentReason, String quantity, String placeIDTo, String locationTo, String placeIDFrom, String locationFrom, String lotTrackedPartID) {
		clickStockListMenuItem('Swap Part');
		addPartCommonValuesOnSwap(adjustmentReason, lotTrackedPartID, quantity, placeIDTo, locationTo, placeIDFrom, locationFrom);

		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Confirm']), 0)
		Mobile.delay(2)

		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_ErrorMessage'), 5)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Please specify the lot id for this stock adjustment']),0)

		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'OK']), 0)
	}

	/**
	 * Remove part validate stock location
	 */
	@Keyword
	public void bf_Stock_RemovePartValidateStockLocation(String adjustmentReason, String quantity, String placeID, String location, String nonTrackedPartId) {
		//Removing serial tracked part
		clickStockListMenuItem('Remove Part');

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Part ID']), nonTrackedPartId, 0)
		clickLookUpIcon('Part ID');
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_FirstElementFromList', [('idf_LabelName') : nonTrackedPartId]), 0)
		Mobile.delay(2)

		Mobile.tap(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Adjustment Reason']), 0)
		clickElementFromPickerWheel(adjustmentReason)

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Place ID From']), placeID, 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Location From']), location, 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), quantity, 0)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0)
		Mobile.delay(2)

		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Confirm']), 0)
		Mobile.delay(2)

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
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Sync Error']), 0)

		Mobile.tap(findTestObject('iOS/PG_StockAndStockCount/PG_StockAdjustment/lbl_StockAdjustmentSyncError'), 0)

		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Delete']), 0)
	}

	/**
	 * Add stock count details serial tracked part
	 */
	@Keyword
	public void bf_StockCount_AddStockCountDetailsSerialTrackedPart(String stockCountRun, String serialTrackedPartID) {
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : stockCountRun]), 0)

		Mobile.tap(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : serialTrackedPartID]), 0)

		String usableBefore = Mobile.getText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Usable Actual']), 0)
		String unusableBefore = Mobile.getText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Unusable Actual']), 0)
		int usableBeforeInt = Integer.parseInt(usableBefore)
		int unusableBeforeInt = Integer.parseInt(unusableBefore)

		Mobile.verifyElementAttributeValue(findTestObject('iOS/PG_Common/chk_CommonByUpperLabelName', [('idf_LabelName') : 'Usable']), 'name', 'cb_glossy_on', 0)
		addSerialIDInStockCount();

		Mobile.verifyElementAttributeValue(findTestObject('iOS/PG_Common/chk_CommonByUpperLabelName', [('idf_LabelName') : 'Usable']), 'name', 'cb_glossy_on', 0)
		addSerialIDInStockCount();

		Mobile.verifyElementAttributeValue(findTestObject('iOS/PG_Common/chk_CommonByUpperLabelName', [('idf_LabelName') : 'Usable']), 'name', 'cb_glossy_on', 0)
		addSerialIDInStockCount();

		Mobile.tap(findTestObject('iOS/PG_Common/chk_CommonByUpperLabelName', [('idf_LabelName') : 'Usable']),0)
		Mobile.verifyElementAttributeValue(findTestObject('iOS/PG_Common/chk_CommonByUpperLabelName', [('idf_LabelName') : 'Usable']), 'name', 'cb_glossy_off', 0)
		addSerialIDInStockCount();

		Mobile.tap(findTestObject('iOS/PG_Common/chk_CommonByUpperLabelName', [('idf_LabelName') : 'Usable']),0)
		Mobile.verifyElementAttributeValue(findTestObject('iOS/PG_Common/chk_CommonByUpperLabelName', [('idf_LabelName') : 'Usable']), 'name', 'cb_glossy_off', 0)
		addSerialIDInStockCount();

		String usableAfter = Mobile.getText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Usable Actual']), 0)
		String unusableAfter = Mobile.getText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Unusable Actual']), 0)

		Mobile.verifyEqual(usableAfter, usableBeforeInt + 3)
		Mobile.verifyEqual(unusableAfter, unusableBeforeInt + 2)

		//String screenCount = Mobile.getText(findTestObject('iOS/PG_Common/lbl_CommonByContainText', [('idf_LabelName') : 'Record']), 0)

		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Confirm']), 0)
	}

	/**
	 * Add stock count details-Non tracked part
	 */
	@Keyword
	public void bf_StockCount_AddStockCountDetailsNonTrackedPart(String stockCountRun, String nonTrackedPartID, String usableCount, String unusableCount) {
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : stockCountRun]), 0)
		
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : nonTrackedPartID]), 0)
		
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Usable Actual']), '', 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Unusable Actual']), '', 0)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0)
	
		//String screenCount = Mobile.getText(findTestObject('iOS/PG_Common/lbl_CommonByContainText', [('idf_LabelName') : 'Record']), 0)
		
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Confirm']), 0)
	}

	/**
	 * Add stock count details Lot tracked part
	 */
	@Keyword
	public void bf_StockCount_AddStockCountDetailsLotTrackedPart(String stockCountRun, String lotTrackedPartID, String usableCount, String unusableCount) {
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : stockCountRun]), 0)
		
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : lotTrackedPartID]), 0)

		String usableBefore = Mobile.getText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Usable Actual']), 0)
		String unusableBefore = Mobile.getText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Unusable Actual']), 0)
		int usableBeforeInt = Integer.parseInt(usableBefore)
		int unusableBeforeInt = Integer.parseInt(unusableBefore)
		
		String lotID = generateRan();
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Lot ID']), lotID, 0)
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock Count Detail']), 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Usable']), usableCount, 0)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Unusable']), unusableCount, 0)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']), 0)
		
		lotID = generateRan();
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Lot ID']), lotID, 0)
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock Count Detail']), 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Usable']), usableCount, 0)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']), 0)
		
		lotID = generateRan();
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Lot ID']), lotID, 0)
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock Count Detail']), 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Unusable']), unusableCount, 0)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']), 0)
		
		//String screenCount = Mobile.getText(findTestObject('iOS/PG_Common/lbl_CommonByContainText', [('idf_LabelName') : 'Record']), 0)
		
		String usableAfter = Mobile.getText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Usable Actual']), 0)
		String unusableAfter = Mobile.getText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Unusable Actual']), 0)
		Mobile.verifyEqual(usableAfter, usableBeforeInt + (usableCount*2) )
		Mobile.verifyEqual(unusableAfter, unusableBeforeInt + (unusableCount*2) )

		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Confirm']), 0)
	}

	/**
	 * Add stock count details Serial Lot tracked part
	 */
	@Keyword
	public void bf_StockCount_AddStockCountDetailsSerialLotTrackedPart(String stockCountRun, String seriaLotTrackedPartID) {
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : stockCountRun]), 0)
		
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : seriaLotTrackedPartID]), 0)

		String usableBefore = Mobile.getText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Usable Actual']), 0)
		String unusableBefore = Mobile.getText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Unusable Actual']), 0)
		int usableBeforeInt = Integer.parseInt(usableBefore)
		int unusableBeforeInt = Integer.parseInt(unusableBefore)

		//Adding serialID and lotID while Usable check-box is ticked
		Mobile.verifyElementAttributeValue(findTestObject('iOS/PG_Common/chk_CommonByUpperLabelName', [('idf_LabelName') : 'Usable']), 'name', 'cb_glossy_on', 0)
		String serialID = generateRan();
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Serial ID']), serialID, 0)
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock Count Detail']), 0)
		String lotID = generateRan();
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Lot ID']), lotID, 0)
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock Count Detail']), 0)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']), 0)
		
		//Adding serialID and same lotID while Usable check-box is not ticked
		Mobile.verifyElementAttributeValue(findTestObject('iOS/PG_Common/chk_CommonByUpperLabelName', [('idf_LabelName') : 'Usable']), 'name', 'cb_glossy_on', 0)
		serialID = generateRan();
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Serial ID']), serialID, 0)
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock Count Detail']), 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Lot ID']), lotID, 0)
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock Count Detail']), 0)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']), 0)
		
		//Adding serialID and lotID while Usable check-box is Unticked twice
		Mobile.tap(findTestObject('iOS/PG_Common/chk_CommonByUpperLabelName', [('idf_LabelName') : 'Usable']),0)
		Mobile.verifyElementAttributeValue(findTestObject('iOS/PG_Common/chk_CommonByUpperLabelName', [('idf_LabelName') : 'Usable']), 'name', 'cb_glossy_off', 0)
		serialID = generateRan();
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Serial ID']), serialID, 0)
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock Count Detail']), 0)
		lotID = generateRan();
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Lot ID']), lotID, 0)
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock Count Detail']), 0)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']), 0)
		
		Mobile.tap(findTestObject('iOS/PG_Common/chk_CommonByUpperLabelName', [('idf_LabelName') : 'Usable']),0)
		Mobile.verifyElementAttributeValue(findTestObject('iOS/PG_Common/chk_CommonByUpperLabelName', [('idf_LabelName') : 'Usable']), 'name', 'cb_glossy_off', 0)
		serialID = generateRan();
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Serial ID']), serialID, 0)
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock Count Detail']), 0)
		lotID = generateRan();
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Lot ID']), lotID, 0)
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock Count Detail']), 0)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']), 0)
		
		//String screenCount = Mobile.getText(findTestObject('iOS/PG_Common/lbl_CommonByContainText', [('idf_LabelName') : 'Record']), 0)
		
		String usableAfter = Mobile.getText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Usable Actual']), 0)
		String unusableAfter = Mobile.getText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Unusable Actual']), 0)
		Mobile.verifyEqual(usableAfter, usableBeforeInt +  2)
		Mobile.verifyEqual(unusableAfter, unusableBeforeInt + 2)

		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Confirm']), 0)
	}

	/**
	 * stock count serial deletion
	 */
	@Keyword
	public void bf_StockCount_StockCountSerialDeletion(String stockCountRun, String serialTrackedPartID) {
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : stockCountRun]), 0)
		
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : serialTrackedPartID]), 0)

		String usableBefore = Mobile.getText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Usable Actual']), 0)
		String unusableBefore = Mobile.getText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Unusable Actual']), 0)
		int usableBeforeInt = Integer.parseInt(usableBefore)
		int unusableBeforeInt = Integer.parseInt(unusableBefore)
		
		Mobile.verifyElementAttributeValue(findTestObject('iOS/PG_Common/chk_CommonByUpperLabelName', [('idf_LabelName') : 'Usable']), 'name', 'cb_glossy_on', 0)
		String serialID = generateRan();
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Serial ID']), serialID, 0)
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock Count Detail']), 0)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']), 0)
		
		String serialID1 = generateRan();
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Serial ID']), serialID1, 0)
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock Count Detail']), 0)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']), 0)
		
		String serialID2 = generateRan();
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Serial ID']), serialID2, 0)
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock Count Detail']), 0)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']), 0)
		
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : serialID]), 0)
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : serialID1]), 0)
		
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Delete']), 0)
		
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Confirm']), 0)
		
	}

	/**
	 * Validate repeated serials
	 */
	@Keyword
	public void bf_StockCount_ValidateRepeatedSerials(String stockCountRun, String serialTrackedPartID){
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : stockCountRun]), 0)
		
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : serialTrackedPartID]), 0)
		
		Mobile.verifyElementAttributeValue(findTestObject('iOS/PG_Common/chk_CommonByUpperLabelName', [('idf_LabelName') : 'Usable']), 'name', 'cb_glossy_on', 0)
		String serialID = generateRan();
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Serial ID']), serialID, 0)
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock Count Detail']), 0)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']), 0)
		
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Serial ID']), serialID, 0)
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock Count Detail']), 0)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']), 0)
		
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_ErrorMessage'), 5)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'OK']), 0)
	}


	/**
	 * Validate repeated LotID
	 */
	@Keyword
	public void bf_StockCount_ValidateRepeatedLots(String stockCountRun, String lotTrackedPartID, String usableCount, String unusableCount){
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : stockCountRun]), 0)
			
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : lotTrackedPartID]), 0)
		
		String lotID = generateRan();
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Lot ID']), lotID, 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Usable']), usableCount, 0)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Unusable']), unusableCount, 0)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']), 0)
		
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Lot ID']), lotID, 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Usable']), usableCount, 0)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']), 0)
		
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_ErrorMessage'), 5)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'OK']), 0)
	}

	/**
	 * Reset Stock Count Details
	 */
	@Keyword
	public void bf_StockCount_ResetStockCountDetails(String stockCountRun, String nonTrackedPartID, String usableCount, String unusableCount) {
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : stockCountRun]), 0)
		
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : nonTrackedPartID]), 0)
		
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Usable Actual']), usableCount, 0)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Unusable Actual']), unusableCount, 0)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0)
		
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Reset']), 0)
		
		String usableAfter = Mobile.getText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Usable Actual']), 0)
		String unusableAfter = Mobile.getText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Unusable Actual']), 0)
		
		Mobile.verifyEqual(usableAfter, '')
		Mobile.verifyEqual(unusableAfter, '')
	}

	/**
	 * Post stock count
	 */
	@Keyword
	public void bf_StockCount_PostStockCount(String stockCountRun) {
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : stockCountRun]), 0)
		
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Post Stock Count']), 0)
	}

}
