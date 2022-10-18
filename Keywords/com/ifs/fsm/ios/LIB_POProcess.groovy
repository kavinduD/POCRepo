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

import java.time.LocalDate;
import java.time.Month;
import com.kms.katalon.core.util.KeywordUtil


import internal.GlobalVariable

public class LIB_POProcess {
	//This method captures the coordinates of the passed label name and performs a right to left swipe on the element
	public void swipeDelete(String labelName) {
		Thread.sleep(3000)
		int startingX = Mobile.getDeviceWidth() - 5
		int endingX = Mobile.getElementLeftPosition(findTestObject('iOS/PG_Common/lbl_FirstElementFromList', [('idf_LabelName') : labelName]), 0)
		int y = Mobile.getElementTopPosition(findTestObject('iOS/PG_Common/lbl_FirstElementFromList', [('idf_LabelName') : labelName]), 0)

		Mobile.swipe(startingX, y, endingX, y)
	}

	//Select any menu option from global menu
	public void jumpToGlobalMenuOption(String menuOptionName) {
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_HamburgerMenu'), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : menuOptionName]), 0, 0)
	}

	//This method will return the count written in front of 'Purchase orders' label in the global menu
	public String getPOCountFromGloablMenu() {
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_HamburgerMenu'), 0, 0)
		Mobile.delay(2)
		String count = Mobile.getText(findTestObject('iOS/PG_Common/lbl_CommonByUpperLabelName', [('idf_LabelName') : 'Purchase Orders']), 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_HamburgerMenu'), 0, 0)
		Mobile.delay(2)

		return count;
	}

	//This method will fill the text fields on the purchase order details screen
	public void addPartCommonValuesOnPODetails(String location, String quantity, String unitCost, String partID) {
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Location']), location, 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Part ID']), partID, 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), quantity, 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Unit Cost']), unitCost, 0)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0)
	}

	/**
	 * Add a new purchase order
	 */
	@Keyword
	public void bf_POProcess_AddNewPO(String supplierID, String placeID) {
		Mobile.tap(findTestObject('iOS/PG_Common/btn_DropDownIcon'), 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add Purchase Order']), 0, 0)
		Mobile.delay(2)

		String beforeValue = getPOCountFromGloablMenu();

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Supplier ID']), supplierID, 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Ship To']), placeID, 0)
		Mobile.tap(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Wanted Date']), 0)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0)

		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0)
		Mobile.delay(2)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Purchase Detail']), 0)

		String afterValue = getPOCountFromGloablMenu();
		Mobile.verifyGreaterThan(afterValue, beforeValue)
	}

	/**
	 * Modifying a new purchase order
	 */
	@Keyword
	public void bf_POProcess_ModifyPO(String supplierID, String placeID) {
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_FirstElementFromList', [('idf_LabelName') : 'PENDING']), 0)
		String beforeValue = getPOCountFromGloablMenu();

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Supplier ID']), supplierID, 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Ship To']), placeID, 0)
		Mobile.tap(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Wanted Date']), 0)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0)

		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0)
		Mobile.delay(2)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Purchase Detail']), 0)

		String afterValue = getPOCountFromGloablMenu();
		Mobile.verifyEqual(afterValue, beforeValue)
	}

	/**
	 * Validations for mandatory fields in purchase order screen
	 */
	@Keyword
	public void bf_POProcess_Validation() {
		Mobile.tap(findTestObject('iOS/PG_Common/btn_DropDownIcon'), 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add Purchase Order']), 0, 0)
		Mobile.delay(2)

		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0)
		Mobile.delay(2)

		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_ErrorMessage'), 5)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Failed to update the record!']),0)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'OK']), 0)
	}

	/**
	 * Add PO details to a selected purchase order
	 */
	@Keyword
	public void bf_POProcess_AddPOLines(String placeID, String location, String quantity, String unitCost, String serialTrackedPartID, String nonTrackedPartID, String lotTrackedPartID, String serialLotTrackedPartID) {
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_FirstElementFromList', [('idf_LabelName') : placeID]), 0)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0)

		//Adding serial tracked part ID
		addPartCommonValuesOnPODetails(location, quantity, unitCost, serialTrackedPartID)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']), 0)

		//Adding non tracked part ID
		addPartCommonValuesOnPODetails(location, quantity, unitCost, nonTrackedPartID)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']), 0)

		//Adding lot tracked part ID
		addPartCommonValuesOnPODetails(location, quantity, unitCost, lotTrackedPartID)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']), 0)

		//Adding serial lot tracked part ID
		addPartCommonValuesOnPODetails(location, quantity, unitCost, serialLotTrackedPartID)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0)

		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Purchase Order Summary']),0)
	}

	/**
	 * Verify validations for mandatory fields
	 */
	@Keyword
	public void bf_POProcess_DetailsValidation() {
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_FirstElementFromList', [('idf_LabelName') : 'PENDING']), 0)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0)

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), '', 0)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0)

		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']), 0)

		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_ErrorMessage'), 5)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'OK']), 0)
	}

	/**
	 * Modify PO line from purchase order record
	 */
	@Keyword
	public void bf_POProcess_ModifyPOLine(String placeID, String location, String quantity, String unitCost, String serialTrackedPartID) {
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_FirstElementFromList', [('idf_LabelName') : 'PENDING']), 0)
		Mobile.verifyElementText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Ship To']), placeID)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0)
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_FirstElementFromList', [('idf_LabelName') : serialTrackedPartID]), 0)

		addPartCommonValuesOnPODetails(location, quantity, unitCost, serialTrackedPartID)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0)

		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Purchase Order Summary']),0)
	}

	/**
	 * Delete PO line from purchase order record
	 */
	@Keyword
	public void bf_POProcess_DeletePOLine(String placeID, String serialTrackedPartID) {
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_FirstElementFromList', [('idf_LabelName') : 'PENDING']), 0)
		Mobile.verifyElementText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Ship To']), placeID)

		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0)

		String totalCostBefore = Mobile.getText(findTestObject('iOS/PG_Common/tv_CommonByUpperLabelName', [('idf_LabelName') : 'Total Cost']), 0)
		double totalCostBeforeInt = Double.parseDouble(totalCostBefore);
		String lineCost = Mobile.getText(findTestObject('iOS/PG_Common/lbl_CommonByUpperLabelName', [('idf_LabelName') : serialTrackedPartID]), 0)
		double lineCostInt = Double.parseDouble(totalCostBefore);

		swipeDelete(serialTrackedPartID);
		Mobile.delay(60)

		String totalCostAfter = Mobile.getText(findTestObject('iOS/PG_Common/tv_CommonByUpperLabelName', [('idf_LabelName') : 'Total Cost']), 0)
		double totalCostAfterInt = Double.parseDouble(totalCostBefore);

		if(totalCostBeforeInt == lineCostInt + totalCostAfterInt) {
			KeywordUtil.markPassed('Total cost value calculation passed!');

		}else {
			KeywordUtil.markFailed('Total cost value calculation Failed!');
		}
	}

	/**
	 * Verify data sync and Process button behavior
	 */
	@Keyword
	public void bf_POProcess_VerifyDataSyncAndProcessButton(String supplierID, String placeID, String location, String quantity, String unitCost, String nonTrackedPartID){
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/switch_CommonByUpperLabelName', [('idf_LabelName') : 'Sync']), 0, 0)

		jumpToGlobalMenuOption('Purchase Orders');

		Mobile.tap(findTestObject('iOS/PG_Common/btn_DropDownIcon'), 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add Purchase Order']), 0, 0)
		Mobile.delay(2)

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Supplier ID']), supplierID, 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Ship To']), placeID, 0)
		Mobile.tap(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Wanted Date']), 0)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0)

		//Adding non tracked part ID
		addPartCommonValuesOnPODetails(location, quantity, unitCost, nonTrackedPartID)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']), 0)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0)

		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Process']), 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_ErrorMessage'), 5)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'OK']), 0)
	}

	/**
	 * Verify total PO cost
	 */
	@Keyword
	public void bf_POProcess_VerifyTotalPOCost(String placeID, String location, String quantity, String unitCost, String serialTrackedPartID, String nonTrackedPartID, String lotTrackedPartID, String serialLotTrackedPartID) {
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_FirstElementFromList', [('idf_LabelName') : 'PENDING']), 0)
		Mobile.verifyElementText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Ship To']), placeID)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0)

		//Adding serial tracked part ID
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Location']), location, 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Part ID']), serialTrackedPartID, 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), quantity, 0)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']), 0)

		//Adding non tracked part ID
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Location']), location, 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Part ID']), nonTrackedPartID, 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), quantity, 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Unit Cost']), unitCost, 0)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']), 0)

		//Adding lot tracked part ID
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Location']), location, 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Part ID']), lotTrackedPartID, 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), quantity, 0)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']), 0)

		//Adding serial lot tracked part ID
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Location']), location, 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Part ID']), serialLotTrackedPartID, 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), quantity, 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Unit Cost']), unitCost, 0)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0)
		//TO BE CONTINUED .......................

	}

	/**
	 * Submit PO
	 */
	@Keyword
	public void bf_POProcess_SubmitPO() {
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_FirstElementFromList', [('idf_LabelName') : 'PENDING']), 0)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0)

		String navBarString = Mobile.getAttribute(findTestObject('iOS/PG_Common/navigationBar_CommonByLabelName', [('idf_LabelName') : 'Purchase Order']), 'name', 0)
		String[] result = navBarString.split("Purchase Order");
		String navBarSubString = result[1].trim();

		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Process']), 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Submit Purchase Order']), 0, 0)

		jumpToGlobalMenuOption('Purchase Orders');
		Mobile.verifyElementExist(findTestObject('iOS/PG_POProcess/lbl_ApprovalStatus', [('idf_LabelName') : navBarSubString, ('idf_LabelName1') : 'SUBMITTED']),0)
	}

	/**
	 * Approve PO
	 */
	@Keyword
	public void bf_POProcess_ApprovePO() {
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_FirstElementFromList', [('idf_LabelName') : 'SUBMITTED']), 0)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0)

		String navBarString = Mobile.getAttribute(findTestObject('iOS/PG_Common/navigationBar_CommonByLabelName', [('idf_LabelName') : 'Purchase Order']), 'name', 0)
		String[] result = navBarString.split("Purchase Order");
		String navBarSubString = result[1].trim();

		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Process']), 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Approve Purchase Order']), 0, 0)

		jumpToGlobalMenuOption('Purchase Orders');
		Mobile.verifyElementExist(findTestObject('iOS/PG_POProcess/lbl_ApprovalStatus', [('idf_LabelName') : navBarSubString, ('idf_LabelName1') : 'APPROVED']),0)
	}

	/**
	 * Post PO
	 */
	@Keyword
	public void bf_POProcess_PostPO() {
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_FirstElementFromList', [('idf_LabelName') : 'APPROVED']), 0)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0)

		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Process']), 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Post Purchase Order']), 0, 0)
	}

	/**
	 * Approve PO-When simple approvals are enabled
	 */
	@Keyword
	public void bf_Process_ApprovePOSimpleApprovals() {
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_FirstElementFromList', [('idf_LabelName') : 'SUBMITTED']), 0)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0)

		String totalCostBefore = Mobile.getText(findTestObject('iOS/PG_Common/tv_CommonByUpperLabelName', [('idf_LabelName') : 'Total Cost']), 0)
		double totalCostBeforeInt = Double.parseDouble(totalCostBefore);
		
		if(totalCostBeforeInt != 0 && totalCostBeforeInt < 1000) {
			Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Process']), 0)
			Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Approve Purchase Order']), 0, 0)
		}else {
			KeywordUtil.markFailed('total cost is zero or greater than the approval limit!')
		}
		
	}
}
