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

public class LIB_StockAndStockCount {

	//This method generate random serial numbers
	public String generateRan() {
		Random random = new Random();
		String ranLetter = "ABCDEFGHIJKLMNOPQRSTUVWZYZ".toCharArray()[random.nextInt("ABCDEFGHIJKLMNOPQRSTUVWZYZ".toCharArray().length)];
		String ranNumber = Integer.toString(random.nextInt(1000));

		String serial = ranLetter + "-" + ranNumber;
		return serial;
	}

	//This method long tap on the stock list and show the button list


	public void LongPressStockListItem(String good,String longpressstockListItem) {
		Mobile.tapAndHold(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : good]) ,0,0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : longpressstockListItem]), 0)
	}

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

	public void AddPartCommonValues(String partID,String quantity,String placeID,String location) {
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Part ID']), partID, 3)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), quantity, 3)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Place ID To']), placeID, 3)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Location To']), location, 3)
		Thread.sleep(2000)
	}


	public void RemovePartCommonValues(String partID,String quantity,String placeID,String location) {
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Part ID']), partID, 3)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), quantity, 3)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Place ID From']), placeID, 3)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Location From']), location, 3)
		Thread.sleep(2000)
	}

	public void SwapPartCommonValues(String partID,String quantity,String placeID,String location,String placeID4,String location1) {
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Part ID']), partID, 3)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), quantity, 3)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Place ID From']), placeID, 3)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Location From']), location, 3)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Place ID To']), placeID4, 3)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Location To']), location1, 3)
		Thread.sleep(2000)
	}

	/**
	 * Add  a part-Receipt
	 */
	@Keyword
	public void bf_Stock_AddpartReceiptAndVerify(String adjustmentReason,String partID,String quantity,String placeID,String location,String part_ID,String partID1,String LotID,String partID2){

		LongPressStockListItem("GOOD","Add Part");
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Adjustment Reason']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : adjustmentReason]), 0)
		Thread.sleep(2000)
		AddPartCommonValues(partID,quantity,placeID,location)
		String serial = generateRan();
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Serial ID']), serial, 3)
		Thread.sleep(2000)

		Mobile.tap(findTestObject('Android/PG_Stocks/btn_AddSerial'), 0)
		Thread.sleep(2000)
		String serial1 = generateRan();
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Serial ID']), serial1, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_AddSerial'), 0)
		String quantityValue = Mobile.getText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), 0)
		Mobile.verifyEqual(quantityValue,'2')


		Mobile.tap(findTestObject('Android/PG_Stocks/btn_Confirm'), 0)

		Thread.sleep(2000)
		LongPressStockListItem("GOOD","Add Part");
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Adjustment Reason']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : adjustmentReason]), 0)
		Thread.sleep(2000)
		AddPartCommonValues(part_ID,quantity,placeID,location)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_Confirm'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock']), 0)

		LongPressStockListItem("GOOD","Add Part");
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Adjustment Reason']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : adjustmentReason]), 0)
		Thread.sleep(2000)
		AddPartCommonValues(partID1,quantity,placeID,location)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Lot ID']), LotID, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_Confirm'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock']), 0)

		LongPressStockListItem("GOOD","Add Part");
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Adjustment Reason']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : adjustmentReason]), 0)
		Thread.sleep(2000)
		AddPartCommonValues(partID2,quantity,placeID,location)
		String serial3 = generateRan();
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Serial ID']), serial3, 3)
		Thread.sleep(2000)


		//Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Lot ID']), LotID, 3)
		tapLookUpIcon('Lot ID')
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : LotID]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_AddSerial'), 0)
		quantityValue = Mobile.getText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), 0)
		Mobile.verifyEqual(quantityValue,'1')
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_Confirm'), 0)
	}

	/**
	 * Add  a part-Bad in Receiving
	 */
	@Keyword
	public void bf_Stock_AddPartBadinReceiving(String adjustmentReason1,String partID,String quantity,String placeID,String location,String partID1,String quantity1,String part_ID,String quantity2,String LotID,String partID2) {
		LongPressStockListItem("GOOD","Add Part");
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Adjustment Reason']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : adjustmentReason1]), 0)
		Thread.sleep(2000)
		AddPartCommonValues(partID,quantity,placeID,location)
		String serial4 = generateRan();
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Serial ID']), serial4, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_AddSerial'), 0)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_Confirm'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock']), 0)

		LongPressStockListItem("GOOD","Add Part");
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Adjustment Reason']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : adjustmentReason1]), 0)
		Thread.sleep(2000)
		AddPartCommonValues(partID1,quantity,placeID,location)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_Confirm'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock']), 0)

		LongPressStockListItem("GOOD","Add Part");
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Adjustment Reason']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : adjustmentReason1]), 0)
		Thread.sleep(2000)
		AddPartCommonValues(part_ID,quantity,placeID,location)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Lot ID']), LotID, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_Confirm'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock']), 0)

		LongPressStockListItem("GOOD","Add Part");
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Adjustment Reason']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : adjustmentReason1]), 0)
		Thread.sleep(2000)
		AddPartCommonValues(partID2,quantity,placeID,location)
		String serial5 = generateRan();
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Serial ID']), serial5, 3)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Lot ID']), LotID, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_AddSerial'), 0)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_Confirm'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock']), 0)
	}

	/**
	 * Add part-Deletion of series
	 */
	@Keyword
	public void bf_Stock_AddPartDeletionOfSerials(String adjustmentReason,String partID,String quantity,String placeID,String location) {
		LongPressStockListItem("GOOD","Add Part");
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Adjustment Reason']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : adjustmentReason]), 0)
		Thread.sleep(2000)
		AddPartCommonValues(partID,quantity,placeID,location)
		String serial5 = generateRan();
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Serial ID']), serial5, 3)
		Thread.sleep(2000)

		Mobile.tap(findTestObject('Android/PG_Stocks/btn_AddSerial'), 0)
		Thread.sleep(2000)
		String serial6 = generateRan();
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Serial ID']), serial6, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_AddSerial'), 0)
		String quantityValue = Mobile.getText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), 0)
		Mobile.verifyEqual(quantityValue,'2')

		Mobile.checkElement(findTestObject('Android/PG_Stocks/chk_SerialID'), 0)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_Delete'), 0)
		String quantitynewValue = Mobile.getText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), 0)
		Mobile.verifyEqual(quantitynewValue,'1')
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_Confirm'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock']), 0)
	}

	/**
	 * validate serial addition
	 */
	@Keyword
	public void bf_Stock_AddpartValidateSerialAddition(String adjustmentReason,String partID,String quantity,String placeID,String location) {
		LongPressStockListItem("GOOD","Add Part");
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Adjustment Reason']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : adjustmentReason]), 0)
		Thread.sleep(2000)
		AddPartCommonValues(partID,quantity,placeID,location)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_Confirm'), 0)
		def message = Mobile.getText(findTestObject('Android/PG_WorkList/lbl_ErrorMessageForRequiredFields'),10)
		KeywordUtil.markPassed("Error Message : "+message)
		Thread.sleep(5000)
	}

	/**
	 * validate Lot ID addition
	 */
	@Keyword
	public void bf_Stock_AddpartValidateLotIDAddition(String adjustmentReason,String partID1,String quantity,String placeID,String location) {
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Adjustment Reason']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : adjustmentReason]), 0)
		Thread.sleep(2000)
		AddPartCommonValues(partID1,quantity,placeID,location)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_Confirm'), 0)
		def message = Mobile.getText(findTestObject('Android/PG_WorkList/lbl_ErrorMessageForRequiredFields'),10)
		KeywordUtil.markPassed("Error Message : "+message)
		Thread.sleep(5000)

	}

	/**
	 * Remove part issue
	 */
	@Keyword
	public void bf_Stock_RemovePartIssue(String adjustmentReason2,String partID,String quantity,String placeID,String location,String part_ID,String quantity2,String partID1,String LotID,String partID2) {
		LongPressStockListItem("GOOD","Remove Part")
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Adjustment Reason']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : adjustmentReason2]), 0)
		Thread.sleep(2000)
		RemovePartCommonValues(partID,quantity,placeID,location)
		String serial8 = generateRan();
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Serial ID']), serial8, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_AddSerial'), 0)
		Thread.sleep(2000)
		String serial1 = generateRan();
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Serial ID']), serial1, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_AddSerial'), 0)
		String quantityValue = Mobile.getText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), 0)
		Mobile.verifyEqual(quantityValue,'2')
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_Confirm'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock']), 0)

		LongPressStockListItem("GOOD","Remove Part")
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Adjustment Reason']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : adjustmentReason2]), 0)
		Thread.sleep(2000)
		RemovePartCommonValues(part_ID,quantity2,placeID,location)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_Confirm'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock']), 0)

		LongPressStockListItem("GOOD","Remove Part")
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Adjustment Reason']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : adjustmentReason2]), 0)
		Thread.sleep(2000)
		RemovePartCommonValues(partID1,quantity2,placeID,location)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Lot ID']), LotID, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_Confirm'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock']), 0)

		LongPressStockListItem("GOOD","Remove Part")
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Adjustment Reason']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : adjustmentReason2]), 0)
		Thread.sleep(2000)
		RemovePartCommonValues(partID2,quantity2,placeID,location)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Lot ID']), LotID, 3)
		Thread.sleep(2000)
		String serial10 = generateRan();
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Serial ID']), serial10, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_AddSerial'), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_Confirm'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock']), 0)
	}

	/**
	 * Remove part scrap
	 */
	@Keyword
	public void bf_Stock_RemovePartScrap(String adjustmentReason3,String partID,String quantity,String placeID,String location,String part_ID,String quantity2,String partID1,String LotID,String adjustmentReason2,String partID2) {
		LongPressStockListItem("GOOD","Remove Part")
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Adjustment Reason']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : adjustmentReason3]), 0)
		Thread.sleep(2000)
		RemovePartCommonValues(partID,quantity,placeID,location)
		String serial9 = generateRan();
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Serial ID']), serial9, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_AddSerial'), 0)
		String quantityValue = Mobile.getText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), 0)
		Mobile.verifyEqual(quantityValue,'1')
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_Confirm'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock']), 0)


		LongPressStockListItem("GOOD","Remove Part")
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Adjustment Reason']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : adjustmentReason3]), 0)
		Thread.sleep(2000)
		RemovePartCommonValues(part_ID,quantity2,placeID,location)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_Confirm'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock']), 0)

		LongPressStockListItem("GOOD","Remove Part")
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Adjustment Reason']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : adjustmentReason3]), 0)
		Thread.sleep(2000)
		RemovePartCommonValues(partID1,quantity2,placeID,location)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Lot ID']), LotID, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_Confirm'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock']), 0)

		LongPressStockListItem("GOOD","Remove Part")
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Adjustment Reason']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : adjustmentReason2]), 0)
		Thread.sleep(2000)
		RemovePartCommonValues(partID2,quantity2,placeID,location)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Lot ID']), LotID, 3)
		Thread.sleep(2000)
		String serial10 = generateRan();
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Serial ID']), serial10, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_AddSerial'), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_Confirm'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock']), 0)
	}

	/**
	 * Remove part deletion of serials
	 */
	@Keyword
	public void bf_Stock_RemovePartDeletionOfSerials(String adjustmentReason2,String partID,String quantity,String placeID,String location) {
		LongPressStockListItem("GOOD","Remove Part")
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Adjustment Reason']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : adjustmentReason2]), 0)
		Thread.sleep(2000)
		RemovePartCommonValues(partID,quantity,placeID,location)
		String serial12 = generateRan();
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Serial ID']), serial12, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_AddSerial'), 0)
		Thread.sleep(2000)
		String serial13 = generateRan();
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Serial ID']), serial13, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_AddSerial'), 0)
		String quantityValue = Mobile.getText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), 0)
		Mobile.verifyEqual(quantityValue,'2')
		Mobile.checkElement(findTestObject('Android/PG_Stocks/chk_SerialID'), 0)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_Delete'), 0)
		String quantityafterValue = Mobile.getText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), 0)
		Mobile.verifyEqual(quantityafterValue,'1')
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_Confirm'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock']), 0)
	}

	/**
	 * Remove part validate serial addition
	 */
	@Keyword
	public void bf_Stock_RemovePartValidateSerialAddition(String adjustmentReason2,String partID,String quantity,String placeID,String location) {
		LongPressStockListItem("GOOD","Remove Part")
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Adjustment Reason']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : adjustmentReason2]), 0)
		Thread.sleep(2000)
		RemovePartCommonValues(partID,quantity,placeID,location)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_Confirm'), 0)
		def message = Mobile.getText(findTestObject('Android/PG_WorkList/lbl_ErrorMessageForRequiredFields'),10)
		KeywordUtil.markPassed("Error Message : "+message)
		Thread.sleep(5000)
	}

	/**
	 * Remove part validate lot ID addition
	 */
	@Keyword
	public void bf_Stock_RemovePartValidateLotIDAddition(String adjustmentReason2,String partID1,String quantity,String placeID,String location) {
		//LongPressStockListItem("GOOD","Remove Part")
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Adjustment Reason']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : adjustmentReason2]), 0)
		Thread.sleep(2000)
		RemovePartCommonValues(partID1,quantity,placeID,location)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_Confirm'), 0)
		def message = Mobile.getText(findTestObject('Android/PG_WorkList/lbl_ErrorMessageForRequiredFields'),10)
		KeywordUtil.markPassed("Error Message : "+message)
		Thread.sleep(5000)

	}

	/**
	 * Swap part-Transfer Parts
	 */
	@Keyword
	public void bf_Stock_SwapPartTransferParts(String adjustmentReason4,String partID,String quantity,String placeID,String location,String placeID4,String part_ID,String partID1,String LotID,String partID2,String location1) {
		LongPressStockListItem("GOOD","Swap Part")
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Adjustment Reason']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : adjustmentReason4]), 0)
		Thread.sleep(2000)
		SwapPartCommonValues(partID,quantity,placeID,location,placeID4,location1)
		String serial14 = generateRan();
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Serial ID']), serial14, 3)
		Thread.sleep(2000)

		Mobile.tap(findTestObject('Android/PG_Stocks/btn_AddSerial'), 0)
		Thread.sleep(2000)
		String serial15 = generateRan();
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Serial ID']), serial15, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_AddSerial'), 0)
		String quantityValue = Mobile.getText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), 0)
		Mobile.verifyEqual(quantityValue,'2')
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_Confirm'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock']), 0)

		LongPressStockListItem("GOOD","Swap Part")
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Adjustment Reason']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : adjustmentReason4]), 0)
		Thread.sleep(2000)
		SwapPartCommonValues(part_ID,quantity,placeID,location,placeID4,location1)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_Confirm'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock']), 0)

		LongPressStockListItem("GOOD","Swap Part")
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Adjustment Reason']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : adjustmentReason4]), 0)
		Thread.sleep(2000)
		SwapPartCommonValues(partID1,quantity,placeID,location,placeID4,location1)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Lot ID']), LotID, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_Confirm'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock']), 0)

		LongPressStockListItem("GOOD","Swap Part")
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Adjustment Reason']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : adjustmentReason4]), 0)
		Thread.sleep(2000)
		SwapPartCommonValues(partID2,quantity,placeID,location,placeID4,location1)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Lot ID']), LotID, 3)
		Thread.sleep(2000)
		String serial16 = generateRan();
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Serial ID']), serial16, 3)
		Thread.sleep(2000)

		Mobile.tap(findTestObject('Android/PG_Stocks/btn_AddSerial'), 0)
		String quantityafterValue = Mobile.getText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), 0)
		Mobile.verifyEqual(quantityafterValue,'1')
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_Confirm'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock']), 0)

	}

	/**
	 * Swap parts unusable to unusable
	 */
	@Keyword
	public void bf_Stock_SwapPartUnusabletoUnusable(String adjustmentReason5,String partID,String quantity,String placeID,String location,String placeID4,String location1,String serialID,String serialID1,String part_ID,String partID1,String LotID,String partID2) {
		LongPressStockListItem("GOOD","Swap Part")
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Adjustment Reason']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : adjustmentReason5]), 0)
		Thread.sleep(2000)
		SwapPartCommonValues(partID,quantity,placeID,location,placeID4,location1)
		tapLookUpIcon("Serial ID")
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : serialID]), 0)
		Thread.sleep(2000)

		Mobile.tap(findTestObject('Android/PG_Stocks/btn_AddSerial'), 0)
		Thread.sleep(2000)
		tapLookUpIcon("Serial ID")
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : serialID1]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_AddSerial'), 0)
		String quantityValue = Mobile.getText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), 0)
		Mobile.verifyEqual(quantityValue,'2')
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_Confirm'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock']), 0)

		LongPressStockListItem("GOOD","Swap Part")
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Adjustment Reason']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : adjustmentReason5]), 0)
		Thread.sleep(2000)
		SwapPartCommonValues(part_ID,quantity,placeID,location,placeID4,location1)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_Confirm'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock']), 0)

		LongPressStockListItem("GOOD","Swap Part")
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Adjustment Reason']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : adjustmentReason5]), 0)
		Thread.sleep(2000)
		SwapPartCommonValues(partID1,quantity,placeID,location,placeID4,location1)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Lot ID']), LotID, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_Confirm'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock']), 0)

		LongPressStockListItem("GOOD","Swap Part")
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Adjustment Reason']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : adjustmentReason5]), 0)
		Thread.sleep(2000)
		SwapPartCommonValues(partID2,quantity,placeID,location,placeID4,location1)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Lot ID']), LotID, 3)
		Thread.sleep(2000)
		tapLookUpIcon("Serial ID")
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : serialID1]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_AddSerial'), 0)
		String quantityafterValue = Mobile.getText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), 0)
		Mobile.verifyEqual(quantityafterValue,'1')
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_Confirm'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock']), 0)

	}

	/**
	 * Swap parts unusable to usable
	 */
	@Keyword
	public void bf_Stock_SwapPartUnusabletoUsable(String adjustmentReason6,String partID,String quantity,String placeID,String location,String placeID4,String location1,String serialID2,String serialID3,String part_ID,String partID1,String LotID,String partID2,String serialID4) {
		LongPressStockListItem("GOOD","Swap Part")
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Adjustment Reason']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : adjustmentReason6]), 0)
		Thread.sleep(2000)
		SwapPartCommonValues(partID,quantity,placeID,location,placeID4,location1)
		tapLookUpIcon("Serial ID")
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : serialID3]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_AddSerial'), 0)
		tapLookUpIcon("Serial ID")
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : serialID2]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_AddSerial'), 0)
		String quantityValue = Mobile.getText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), 0)
		Mobile.verifyEqual(quantityValue,'2')
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_Confirm'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock']), 0)

		LongPressStockListItem("GOOD","Swap Part")
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Adjustment Reason']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : adjustmentReason6]), 0)
		Thread.sleep(2000)
		SwapPartCommonValues(part_ID,quantity,placeID,location,placeID4,location1)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_Confirm'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock']), 0)

		LongPressStockListItem("GOOD","Swap Part")
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Adjustment Reason']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : adjustmentReason6]), 0)
		Thread.sleep(2000)
		SwapPartCommonValues(partID1,quantity,placeID,location,placeID4,location1)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Lot ID']), LotID, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_Confirm'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock']), 0)

		LongPressStockListItem("GOOD","Swap Part")
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Adjustment Reason']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : adjustmentReason6]), 0)
		Thread.sleep(2000)
		SwapPartCommonValues(partID2,quantity,placeID,location,placeID4,location1)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Lot ID']), LotID, 3)
		Thread.sleep(2000)
		tapLookUpIcon("Serial ID")
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : serialID4]), 0)
		Thread.sleep(2000)

		Mobile.tap(findTestObject('Android/PG_Stocks/btn_AddSerial'), 0)
		String quantityafterValue = Mobile.getText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), 0)
		Mobile.verifyEqual(quantityafterValue,'1')
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_Confirm'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock']), 0)
	}

	/**
	 * Swap parts usable to unusable
	 */
	@Keyword
	public void bf_Stock_SwapPartUsabletoUnusable(String adjustmentReason7,String partID,String quantity,String placeID,String location,String placeID4,String location1,String serialID6,String serialID7,String part_ID,String partID1,String LotID,String partID2,String serialID5) {
		LongPressStockListItem("GOOD","Swap Part")
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Adjustment Reason']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : adjustmentReason7]), 0)
		Thread.sleep(2000)
		SwapPartCommonValues(partID,quantity,placeID,location,placeID4,location1)
		tapLookUpIcon("Serial ID")
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : serialID6]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_AddSerial'), 0)
		tapLookUpIcon("Serial ID")
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : serialID7]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_AddSerial'), 0)
		String quantityValue = Mobile.getText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), 0)
		Mobile.verifyEqual(quantityValue,'2')
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_Confirm'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock']), 0)

		LongPressStockListItem("GOOD","Swap Part")
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Adjustment Reason']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : adjustmentReason7]), 0)
		Thread.sleep(2000)
		SwapPartCommonValues(part_ID,quantity,placeID,location,placeID4,location1)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_Confirm'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock']), 0)

		LongPressStockListItem("GOOD","Swap Part")
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Adjustment Reason']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : adjustmentReason7]), 0)
		Thread.sleep(2000)
		SwapPartCommonValues(partID1,quantity,placeID,location,placeID4,location1)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Lot ID']), LotID, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_Confirm'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock']), 0)

		LongPressStockListItem("GOOD","Swap Part")
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Adjustment Reason']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : adjustmentReason7]), 0)
		Thread.sleep(2000)
		SwapPartCommonValues(partID2,quantity,placeID,location,placeID4,location1)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Lot ID']), LotID, 3)
		Thread.sleep(2000)
		tapLookUpIcon("Serial ID")
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : serialID5]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_AddSerial'), 0)
		String quantityafterValue = Mobile.getText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), 0)
		Mobile.verifyEqual(quantityafterValue,'2')
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_Confirm'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock']), 0)
	}

	/**
	 * Swap parts for sale
	 * 
	 */
	@Keyword
	public void bf_Stock_SwapPartForSale(String adjustmentReason8,String partID,String quantity,String placeID,String location,String placeID4,String location1,String serialID8,String serialID9,String part_ID,String partID1,String LotID,String partID2,String serialID5) {
		LongPressStockListItem("GOOD","Swap Part")
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Adjustment Reason']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : adjustmentReason8]), 0)
		Thread.sleep(2000)
		SwapPartCommonValues(partID,quantity,placeID,location,placeID4,location1)
		tapLookUpIcon("Serial ID")
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : serialID8]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_AddSerial'), 0)
		tapLookUpIcon("Serial ID")
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : serialID9]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_AddSerial'), 0)
		String quantityValue = Mobile.getText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), 0)
		Mobile.verifyEqual(quantityValue,'2')
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_Confirm'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock']), 0)

		LongPressStockListItem("GOOD","Swap Part")
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Adjustment Reason']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : adjustmentReason8]), 0)
		Thread.sleep(2000)
		SwapPartCommonValues(part_ID,quantity,placeID,location,placeID4,location1)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_Confirm'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock']), 0)

		LongPressStockListItem("GOOD","Swap Part")
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Adjustment Reason']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : adjustmentReason8]), 0)
		Thread.sleep(2000)
		SwapPartCommonValues(partID1,quantity,placeID,location,placeID4,location1)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Lot ID']), LotID, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_Confirm'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock']), 0)

		LongPressStockListItem("GOOD","Swap Part")
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Adjustment Reason']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : adjustmentReason8]), 0)
		Thread.sleep(2000)
		SwapPartCommonValues(partID2,quantity,placeID,location,placeID4,location1)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Lot ID']), LotID, 3)
		Thread.sleep(2000)
		tapLookUpIcon("Serial ID")
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : serialID5]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_AddSerial'), 0)
		String quantityafterValue = Mobile.getText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), 0)
		Mobile.verifyEqual(quantityafterValue,'1')
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_Confirm'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock']), 0)

	}

	/**
	 * Swap parts deletion of serials
	 */
	@Keyword
	public void bf_Stock_SwapPartDeletionOfSerials(String adjustmentReason9,String partID,String quantity,String placeID,String location,String placeID4,String location1,String serialID10,String serialID11) {
		LongPressStockListItem("GOOD","Swap Part")
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Adjustment Reason']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : adjustmentReason9]), 0)
		Thread.sleep(2000)
		SwapPartCommonValues(partID,quantity,placeID,location,placeID4,location1)
		tapLookUpIcon("Serial ID")
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : serialID10]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_AddSerial'), 0)
		tapLookUpIcon("Serial ID")
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : serialID11]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_AddSerial'), 0)
		String quantityValue = Mobile.getText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), 0)
		Mobile.verifyEqual(quantityValue,'2')
		Mobile.checkElement(findTestObject('Android/PG_Stocks/chk_SerialID'), 0)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_Delete'), 0)
		String quantityafterValue = Mobile.getText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), 0)
		Mobile.verifyEqual(quantityafterValue,'1')
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_Confirm'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock']), 0)


	}

	/**
	 * Swap parts validate serial addition
	 */
	@Keyword
	public void bf_Stock_SwapPartValidateSerialAddition(String adjustmentReason9,String partID,String quantity,String placeID,String location,String placeID4,String location1) {
		LongPressStockListItem("GOOD","Swap Part")
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Adjustment Reason']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : adjustmentReason9]), 0)
		Thread.sleep(2000)
		SwapPartCommonValues(partID,quantity,placeID,location,placeID4,location1)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_Confirm'), 0)
		def message = Mobile.getText(findTestObject('Android/PG_WorkList/lbl_ErrorMessageForRequiredFields'),10)
		KeywordUtil.markPassed("Error Message : "+message)
		Thread.sleep(5000)
	}

	/**
	 * Swap parts validate lotID addition
	 */
	@Keyword
	public void bf_Stock_SwapPartValidateLotIDAddition(String adjustmentReason9,String partID1,String quantity,String placeID,String location,String placeID4,String location1) {
		LongPressStockListItem("GOOD","Swap Part")
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Adjustment Reason']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : adjustmentReason9]), 0)
		Thread.sleep(2000)
		SwapPartCommonValues(partID1,quantity,placeID,location,placeID4,location1)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_Confirm'), 0)
		def message = Mobile.getText(findTestObject('Android/PG_WorkList/lbl_ErrorMessageForRequiredFields'),10)
		KeywordUtil.markPassed("Error Message : "+message)
		Thread.sleep(5000)
	}

	/**
	 * Remove part validate stock location
	 */
	@Keyword
	public void bf_Stock_RemovePartValidateStockLocation(String adjustmentReason2,String part_ID,String quantity,String placeID,String location) {
		LongPressStockListItem("GOOD","Remove Part")
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Adjustment Reason']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : adjustmentReason2]), 0)
		Thread.sleep(2000)
		RemovePartCommonValues(part_ID,quantity,placeID,location)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_Confirm'), 0)

		Mobile.tapAndHold(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0, 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Home']), 0)
		for(int i=0; i<5; i++) {
			boolean flag = Mobile.waitForElementPresent(findTestObject('Android/PG_Common/lbl_SyncErrorPopUp'), 5)
			if(flag) {
				break;
			}else {
				Thread.sleep(15000)
				Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
				Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Home']), 0)
			}
		}
		Mobile.tap(findTestObject('Android/PG_Stocks/lbl_SyncError'), 0)
		Mobile.tapAndHold(findTestObject('Android/PG_Stocks/lbl_StockAdjustmentSyncError'), 0,4)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Delete']), 0)
	}

	/**
	 * Add stock count details-Serial tracked part
	 */
	@Keyword
	public void bf_Stock_AddStockCountDetailsSerialTrackedPart(String serialId) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : '6221']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'TA-SER-PART1']), 0)

		String usableBefore = Mobile.getText(findTestObject('Android/PG_Common/lbl_CommonByUpperLabelName', [('idf_LabelName') : 'Usable Actual']), 0)
		String unusableBefore = Mobile.getText(findTestObject('Android/PG_Common/lbl_CommonByUpperLabelName', [('idf_LabelName') : 'Unusable Actual']), 0)
		int usableBeforeInt = Integer.parseInt(usableBefore)
		int unusableBeforeInt = Integer.parseInt(unusableBefore)

		String serial = generateRan();
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Serial ID']), serial, 3)
		Thread.sleep(2000)

		Mobile.tap(findTestObject('Android/PG_Stocks/btn_StockAdd'), 0)
		Thread.sleep(2000)
		String serial1 = generateRan();
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Serial ID']), serial1, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_StockAdd'), 0)
		Thread.sleep(2000)
		String serial2 = generateRan();
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Serial ID']), serial2, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_StockAdd'), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Stocks/chk_Usable1'), 0)
		String serial3 = generateRan();
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Serial ID']), serial3, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_StockAdd'), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Stocks/chk_Usable1'), 0)
		String serial4 = generateRan();
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Serial ID']), serial4, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_StockAdd'), 0)

		String usableAfter = Mobile.getText(findTestObject('Android/PG_Common/lbl_CommonByUpperLabelName', [('idf_LabelName') : 'Usable Actual']), 0)
		String unusableAfter = Mobile.getText(findTestObject('Android/PG_Common/lbl_CommonByUpperLabelName', [('idf_LabelName') : 'Unusable Actual']), 0)

		Mobile.verifyEqual(usableAfter, usableBeforeInt + 3)
		Mobile.verifyEqual(unusableAfter, unusableBeforeInt + 2)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_StockConfirm'), 0)
	}


	/**
	 * Add stock count details-Non tracked part
	 */
	@Keyword
	public void bf_Stock_AddStockCountDetailsNonTrackedPart(String usableActual,String unusableActual) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : '6221']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'TA-NON-PART1']), 0)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Usable Actual']), usableActual, 3)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Unusable Actual']), unusableActual, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_StockConfirm'), 0)
	}




	/**
	 * Add stock count details Lot tracked part
	 */
	@Keyword
	public void bf_Stock_AddStockCountDetailsLotTrackedPart(String LotID,String usable,String unusable,String LotID1,String LotID2) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : '6221']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'TA-LOT-PART1']), 0)

		String usableBefore = Mobile.getText(findTestObject('Android/PG_Common/lbl_CommonByUpperLabelName', [('idf_LabelName') : 'Usable Actual']), 0)
		String unusableBefore = Mobile.getText(findTestObject('Android/PG_Common/lbl_CommonByUpperLabelName', [('idf_LabelName') : 'Unusable Actual']), 0)
		int usableBeforeInt = Integer.parseInt(usableBefore)
		int unusableBeforeInt = Integer.parseInt(unusableBefore)

		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Lot ID']), LotID, 3)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Usable']), usable, 3)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Unusable']), unusable, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_StockAdd'), 0)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Lot ID']), LotID1, 3)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Usable']), usable, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_StockAdd'), 0)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Lot ID']), LotID2, 3)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Unusable']), unusable, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_StockAdd'), 0)

		String usableAfter = Mobile.getText(findTestObject('Android/PG_Common/lbl_CommonByUpperLabelName', [('idf_LabelName') : 'Usable Actual']), 0)
		String unusableAfter = Mobile.getText(findTestObject('Android/PG_Common/lbl_CommonByUpperLabelName', [('idf_LabelName') : 'Unusable Actual']), 0)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_StockConfirm'), 0)

	}

	/**
	 * Add stock count details Serial Lot tracked part
	 */
	@Keyword
	public void bf_Stock_AddStockCountDetailsSerialLotTrackedPart(String LotID,String LotID1) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : '6221']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'TA-SER-LOT-PART1']), 0)

		String usableBefore = Mobile.getText(findTestObject('Android/PG_Common/lbl_CommonByUpperLabelName', [('idf_LabelName') : 'Usable Actual']), 0)
		String unusableBefore = Mobile.getText(findTestObject('Android/PG_Common/lbl_CommonByUpperLabelName', [('idf_LabelName') : 'Unusable Actual']), 0)
		int usableBeforeInt = Integer.parseInt(usableBefore)
		int unusableBeforeInt = Integer.parseInt(unusableBefore)

		String serial5 = generateRan();
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Serial ID']), serial5, 3)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Lot ID']), LotID, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_StockAdd'), 0)
		Thread.sleep(2000)
		String serial6 = generateRan();
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Serial ID']), serial6, 3)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Lot ID']), LotID, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_StockAdd'), 0)
		Mobile.tap(findTestObject('Android/PG_Stocks/chk_Usable1'), 0)
		String serial7 = generateRan();
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Serial ID']), serial7, 3)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Lot ID']), LotID1, 3)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_StockAdd'), 0)

		String usableAfter = Mobile.getText(findTestObject('Android/PG_Common/lbl_CommonByUpperLabelName', [('idf_LabelName') : 'Usable Actual']), 0)
		String unusableAfter = Mobile.getText(findTestObject('Android/PG_Common/lbl_CommonByUpperLabelName', [('idf_LabelName') : 'Unusable Actual']), 0)
		Mobile.verifyEqual(usableAfter, usableBeforeInt +  2)
		Mobile.verifyEqual(unusableAfter, unusableBeforeInt + 2)

		Mobile.tap(findTestObject('Android/PG_Stocks/btn_StockConfirm'), 0)

	}

	/**
	 * stock count serial deletion
	 */
	@Keyword
	public void bf_Stock_StockCountSerialDeletion() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : '6241']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'TA-SER-PART1']), 0)
		String usableBefore = Mobile.getText(findTestObject('Android/PG_Common/lbl_CommonByUpperLabelName', [('idf_LabelName') : 'Usable Actual']), 0)
		String unusableBefore = Mobile.getText(findTestObject('Android/PG_Common/lbl_CommonByUpperLabelName', [('idf_LabelName') : 'Unusable Actual']), 0)
		int usableBeforeInt = Integer.parseInt(usableBefore)
		int unusableBeforeInt = Integer.parseInt(unusableBefore)

		String serial8 = generateRan();
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Serial ID']), serial8, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_StockAdd'), 0)
		String serial9 = generateRan();
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Serial ID']), serial9, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_StockAdd'), 0)
		String serial10 = generateRan();
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Serial ID']), serial10, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_StockAdd'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Stocks/chk_Serialdelete', [('idf_SerialID') : 'G-33']), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Stocks/chk_Serialdelete', [('idf_SerialID') : 'G-899']), 0)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_SerialDelete'), 0)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_StockConfirm'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'TA-SER-PART1']), 0)

	}

	/**
	 * Validate repeated serials
	 */
	@Keyword
	public void bf_Stock_ValidateRepeatedSerials(String serialID10){
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : '6241']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'TA-SER-PART1']), 0)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Serial ID']), serialID10, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_StockAdd'), 0)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Serial ID']), serialID10, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_StockAdd'), 0)
		def message = Mobile.getText(findTestObject('Android/PG_WorkList/lbl_ErrorMessageForRequiredFields'),10)
		KeywordUtil.markPassed("Error Message : "+message)
		Thread.sleep(5000)
	}


	/**
	 * Validate repeated LotID
	 */

	@Keyword
	public void bf_Stock_ValidateRepeatedLots(String LotID,String usable,String unusable){
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : '6241']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'TA-LOT-PART1']), 0)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Lot ID']), LotID, 3)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Usable']), usable, 3)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Unusable']), unusable, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_StockAdd'), 0)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Lot ID']), LotID, 3)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Usable']), usable, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_StockAdd'), 0)
		def message = Mobile.getText(findTestObject('Android/PG_WorkList/lbl_ErrorMessageForRequiredFields'),10)
		KeywordUtil.markPassed("Error Message : "+message)
		Thread.sleep(5000)
	}

	/**
	 * Reset Stock Count Details
	 */

	@Keyword
	public void bf_Stock_ResetStockCountDetails(String usableActual,String unusableActual) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : '6241']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'TA-NON-PART1']), 0)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Usable Actual']), usableActual, 3)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Unusable Actual']), unusableActual, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_Reset'), 0)
		String usableAfter = Mobile.getText(findTestObject('Android/PG_Common/lbl_CommonByUpperLabelName', [('idf_LabelName') : 'Usable Actual']), 0)
		String unusableAfter = Mobile.getText(findTestObject('Android/PG_Common/lbl_CommonByUpperLabelName', [('idf_LabelName') : 'Unusable Actual']), 0)

		Mobile.verifyEqual(usableAfter, '')
		Mobile.verifyEqual(unusableAfter, '')
	}

	/**
	 * Post stock count
	 */

	@Keyword
	public void bf_Stock_PostStockCount() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : '6241']), 0)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_Stockpost'), 0)
	}

	/**
	 * Stock list verify search
	 */
	@Keyword
	public void bf_Stock_StockListVerifySearch(String partID,String quantity1,String placeID,String location,String part_ID,String partID1,String partID2) {
		Mobile.setText(findTestObject('Android/PG_Stocks/tf_SearchField', [('idf_LabelName') : 'Search']),partID , 3)
		Thread.sleep(2000)
		Mobile.clearText(findTestObject('Android/PG_Stocks/tf_SearchField'), 3)
		Mobile.setText(findTestObject('Android/PG_Stocks/tf_SearchField', [('idf_LabelName') : 'Search']),quantity1 , 3)
		Thread.sleep(2000)
		Mobile.clearText(findTestObject('Android/PG_Stocks/tf_SearchField'), 10)
		Mobile.setText(findTestObject('Android/PG_Stocks/tf_SearchField', [('idf_LabelName') : 'Search']),placeID , 3)
		Thread.sleep(2000)
		Mobile.clearText(findTestObject('Android/PG_Stocks/tf_SearchField'), 10)
		Mobile.setText(findTestObject('Android/PG_Stocks/tf_SearchField', [('idf_LabelName') : 'Search']),location , 3)
		Thread.sleep(2000)
		Mobile.clearText(findTestObject('Android/PG_Stocks/tf_SearchField'), 10)
		Mobile.setText(findTestObject('Android/PG_Stocks/tf_SearchField', [('idf_LabelName') : 'Search']), part_ID, 3)
		Thread.sleep(2000)
		Mobile.clearText(findTestObject('Android/PG_Stocks/tf_SearchField'), 10)
		Mobile.setText(findTestObject('Android/PG_Stocks/tf_SearchField', [('idf_LabelName') : 'Search']), partID1, 3)
		Thread.sleep(2000)
		Mobile.clearText(findTestObject('Android/PG_Stocks/tf_SearchField'), 10)
		Mobile.setText(findTestObject('Android/PG_Stocks/tf_SearchField', [('idf_LabelName') : 'Search']), partID2, 3)
		Thread.sleep(2000)
		Mobile.clearText(findTestObject('Android/PG_Stocks/tf_SearchField'), 10)
	}


	/**
	 * Stock Adjustment-find part
	 */

	@Keyword
	public void bf_Stock_StockAdjustmentFindPart(String partID6,String radius,String results,String quantity) {
		LongPressStockListItem("GOOD","Find Part");
		Thread.sleep(2000)
		tapLookUpIcon("Part ID")
		Thread.sleep(2000)
		tapLookUpIcon("Part ID")
		Mobile.setText(findTestObject('Android/PG_Stocks/tf_SearchField', [('idf_LabelName') : 'Search']), partID6, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : partID6]), 0)
		Thread.sleep(2000)

		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Radius']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : radius]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Results']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : results]), 0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), quantity, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Stocks/btn_Search'), 0)
		Thread.sleep(15000)
		Mobile.takeScreenshot('Screenshots/Debrief_Overview_maps.png', FailureHandling.STOP_ON_FAILURE)
		Thread.sleep(2000)
		Mobile.pressBack()

	}





}
