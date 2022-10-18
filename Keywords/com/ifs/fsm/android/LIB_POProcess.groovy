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
import io.appium.java_client.AppiumDriver
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory




public class LIB_POProcess {

	public void LongPressStockListItem(String good,String longpressstockListItem) {
		Mobile.tapAndHold(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : good]) ,0,0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : longpressstockListItem]), 0)
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

	//Select any menu option from global menu
	public void jumpToGlobalMenuOption(String menuOptionName) {
		Mobile.tapAndHold(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0, 0)
		Mobile.tapAndHold(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : menuOptionName]), 0, 0)
	}

	//get the purchase order count in the global menu
	public String getPurchaseOrderCount() {
		Mobile.tapAndHold(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0, 0)
		String count = Mobile.getText(findTestObject('Android/PG_Common/lbl_CommonByUpperLabelName', [('idf_LabelName') : 'Purchase Orders']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenuBack'), 0)
		return count;

	}

	//Get the total cost
	public String getTotalCost() {
		String totalcost = Mobile.getText(findTestObject('Android/PG_Common/lbl_CommonByUpperLabelName', [('idf_LabelName') : 'Total Cost']), 0)
		return totalcost;
	}

	//Common values
	public void commonValues(String partID,String quantity) {
		tapLookUpIcon("Part ID")
		Mobile.setText(findTestObject('Android/PG_Stocks/tf_SearchField', [('idf_LabelName') : 'Search']),partID , 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : partID]), 0)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), quantity, 3)
		Thread.sleep(2000)
	}

	//common values in verify total
	public void commonValuesVerifyTotal(String location,String partID,String quantity) {
		tapLookUpIcon("Location")
		Mobile.clearText(findTestObject('Android/PG_Stocks/tf_SearchField'), 3)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : location]), 0)
		tapLookUpIcon("Part ID")
		Mobile.setText(findTestObject('Android/PG_Stocks/tf_SearchField', [('idf_LabelName') : 'Search']),partID , 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : partID]), 0)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), quantity, 3)
		Thread.sleep(2000)
	}

	//Add PO common values
	public void AddPOCommonValues(String supplierID,String placeID,String externalRef) {
		tapLookUpIcon("Supplier ID")
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : supplierID]), 0)
		Thread.sleep(2000)
		tapLookUpIcon("Ship To")
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : placeID]), 0)
		Thread.sleep(2000)
		tapLookUpIcon("Wanted Date")
		Mobile.tap(findTestObject('Android/PG_DebriefOverview/btn_date'), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'OK' ]), 0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'External Ref']), externalRef, 3)
		Thread.sleep(2000)
	}


	/**
	 * Add  a new PO
	 */
	@Keyword
	public void bf_POProcess_AddNewPO(String supplierID,String placeID,String externalRef) {

		LongPressStockListItem("APPROVED","Add Purchase Order");
		Thread.sleep(2000)
		String beforePurchaseOrderCount = getPurchaseOrderCount()

		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Purchase Order"]), 0)
		Thread.sleep(2000)
		AddPOCommonValues(supplierID,placeID,externalRef)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Purchase Detail"]), 0)
		String afterPurchaseOrderCount = getPurchaseOrderCount()
		Mobile.verifyGreaterThan(afterPurchaseOrderCount, beforePurchaseOrderCount)
	}

	/**
	 * modify a po
	 */
	@Keyword
	public void bf_POProcess_ModifyPO(String supplierID,String placeID1) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'APPROVED']), 0)
		tapLookUpIcon("Supplier ID")
		Mobile.clearText(findTestObject('Android/PG_Stocks/tf_SearchField'), 3)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : supplierID]), 0)
		Thread.sleep(2000)
		tapLookUpIcon("Ship To")
		Mobile.clearText(findTestObject('Android/PG_Stocks/tf_SearchField'), 3)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable', [('idf_TextName') : placeID1]), 0)
		Thread.sleep(2000)
		tapLookUpIcon("Wanted Date")
		Mobile.tap(findTestObject('Android/PG_POProcess/tv_date'), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'OK' ]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Purchase Detail"]), 0)
		Mobile.pressBack()
		Mobile.pressBack()
	}


	/**
	 * verify validation for mandatory fields
	 */
	@Keyword
	public void bf_POProcess_Validation() {
		LongPressStockListItem("APPROVED","Add Purchase Order");
		Mobile.clearText(findTestObject('Android/PG_POProcess/tf_shipTo'), 3)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Thread.sleep(2000)
		def message = Mobile.getText(findTestObject('Android/PG_WorkList/lbl_ErrorMessageForRequiredFields'),10)
		KeywordUtil.markPassed("Error Message : " + message)
		Thread.sleep(5000)
	}


	/**
	 * Add PO lines
	 */
	@Keyword
	public void bf_POProcess_AddPOLines(String location,String partID,String quantity,String partID1,String quantity1,String partID2,String partID3) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'PENDING']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		tapLookUpIcon("Location")
		Mobile.clearText(findTestObject('Android/PG_Stocks/tf_SearchField'), 3)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : location]), 0)
		Thread.sleep(2000)
		commonValues(partID,quantity)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Add'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Purchase Detail"]), 0)

		commonValues(partID1,quantity1)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Add'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Purchase Detail"]), 0)

		commonValues(partID2,quantity)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Add'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Purchase Detail"]), 0)

		commonValues(partID3,quantity1)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Purchase Order Summary"]), 0)

	}


	/**
	 * Verify validations for mandatory fields
	 */
	@Keyword
	public void bf_POProcess_VerifyValidations() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'PENDING']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Mobile.clearText(findTestObject('Android/PG_POProcess/tf_Quantity'), 3)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Add'), 0)
		def message = Mobile.getText(findTestObject('Android/PG_WorkList/lbl_ErrorMessageForRequiredFields'),10)
		KeywordUtil.markPassed("Error Message : " + message)
		Thread.sleep(5000)
	}

	/**
	 * Modify PO Line
	 */
	@Keyword
	public void bf_POProcess_ModifyPOLine(String location,String partID1,String quantity1,String unitCost) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'PENDING']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		String beforeTotalCost = getTotalCost()
		Mobile.tap(findTestObject('Android/PG_POProcess/lbl_Purchaseorder'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Modify or Delete"]), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "Modify", ('idf_ButtonNameCapital') : "MODIFY"]), 0)
		tapLookUpIcon("Location")
		Mobile.clearText(findTestObject('Android/PG_Stocks/tf_SearchField'), 3)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : location]), 0)
		commonValues(partID1,quantity1)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Unit Cost']), unitCost, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Purchase Order Summary"]), 0)
		String afterTotalCost = getTotalCost()
		Mobile.verifyGreaterThan(afterTotalCost, beforeTotalCost)

	}

	/**
	 * Delete PO Line
	 */
	@Keyword
	public void bf_POProcess_DeletePOLine() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'PENDING']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		String beforeTotalCost = getTotalCost()
		Mobile.tap(findTestObject('Android/PG_POProcess/lbl_Purchaseorder'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Modify or Delete"]), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "Delete", ('idf_ButtonNameCapital') : "DELETE"]), 0)
		String afterTotalCost = getTotalCost()
		Mobile.verifyLessThan(afterTotalCost, beforeTotalCost)

	}

	/**
	 * Verify data sync and Process button behavior
	 */
	@Keyword
	public void bf_POProcess_VerifyDataSyncAndProcessButton(String supplierID,String placeID,String externalRef,String location,String partID1,String quantity1){
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : "Admin"]), 0)
		Mobile.tap(findTestObject('Android/PG_POProcess/chk_PauseSync'), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/btn_Add'), 0)
		LongPressStockListItem("PENDING","Add Purchase Order");
		AddPOCommonValues(supplierID,placeID,externalRef)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		tapLookUpIcon("Location")
		Mobile.clearText(findTestObject('Android/PG_Stocks/tf_SearchField'), 3)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : location]), 0)
		commonValues(partID1,quantity1)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Process']), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_ErrorMessageForRequiredFields'), 5)
	}


	/**
	 * Verify total PO cost
	 */
	@Keyword
	public void bf_POProcess_VerifyTotalPOCost(String location,String partID,String quantity,String partID1,String quantity1,String partID2,String partID3,String unitCost) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'PENDING']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		commonValuesVerifyTotal(location,partID,quantity)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Add'), 0)
		Thread.sleep(2000)
		commonValuesVerifyTotal(location,partID1,quantity1)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Unit Cost']), unitCost, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Add'), 0)
		Thread.sleep(2000)
		commonValuesVerifyTotal(location,partID2,quantity)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Add'), 0)
		Thread.sleep(2000)
		commonValuesVerifyTotal(location,partID3,quantity1)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Unit Cost']), unitCost, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)

		int aftervalue = MobileDriverFactory.getDriver().findElementsByXPath("//androidx.recyclerview.widget.RecyclerView//android.widget.LinearLayout").size()
		System.out.println("aftervalue = " + aftervalue);
		int total = 0;
		for(int i = 1 ; i<= aftervalue;i++) {

			String x = Mobile.getText(findTestObject('Android/PG_POProcess/lbl_UnitCost', [('idf_ColumnNo') : i, ('idf_RowNo') : 2 ]), 0)
			String y = Mobile.getText(findTestObject('Android/PG_POProcess/lbl_UnitCost', [('idf_ColumnNo') : i, ('idf_RowNo') : 3 ]), 0)
			System.out.println("x value = " + x);
			int aftervalueofx = Integer.parseInt(x);
			int aftervalueofy = Integer.parseInt(y);
			int z = (aftervalueofx * aftervalueofy)
			total = total + z;
		}
		System.out.println("total = " + total);
	}

	/**
	 * Submit PO
	 */
	@Keyword
	public void bf_POProcess_SubmitPO() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'PENDING']), 0)

		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Thread.sleep(4000)

		String navBarString = Mobile.getAttribute(findTestObject('Android/PG_POProcess/navBar_CommonByLabelName', [('idf_LabelName') : 'Purchase Order']), 'name', 0)
		String[] result = navBarString.split("Purchase Order");
		String navBarSubString = result[1].trim();

		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Process']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Submit Purchase Order']), 0)
		jumpToGlobalMenuOption('Purchase Orders');
		Mobile.verifyElementExist(findTestObject('Android/PG_POProcess/lbl_ApproveStatus', [('idf_LabelName') : navBarSubString, ('idf_LabelName1') : 'SUBMITTED']),0)

	}

	/**
	 * Approve PO
	 */
	@Keyword
	public void bf_POProcess_ApprovePO() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'SUBMITTED']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Thread.sleep(2000)

		String navBarString = Mobile.getAttribute(findTestObject('Android/PG_POProcess/navBar_CommonByLabelName', [('idf_LabelName') : 'Purchase Order']), 'name', 0)
		String[] result = navBarString.split("Purchase Order");
		String navBarSubString = result[1].trim();

		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Process']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Approve Purchase Order']), 0)
		jumpToGlobalMenuOption('Purchase Orders');
		Mobile.verifyElementExist(findTestObject('Android/PG_POProcess/lbl_ApproveStatus', [('idf_LabelName') : navBarSubString, ('idf_LabelName1') : 'SUBMITTED']),0)

	}

	/**
	 * Post PO
	 */
	@Keyword
	public void bf_POProcess_PostPO() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'APPROVED']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Thread.sleep(5000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Process']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Post Purchase Order']), 0)
		Thread.sleep(5000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Posted"]), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Purchase Order List"]), 0)
	}

	/**
	 *  Approve PO-When simple approvals are enabled
	 */
	@Keyword
	public void bf_Process_ApprovePOSimpleApprovals() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'SUBMITTED']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Thread.sleep(2000)

		String BeforePOtotalCost = Mobile.getText(findTestObject('Android/PG_Common/lbl_CommonByUpperLabelName', [('idf_LabelName') : 'Total Cost']), 0)
		int total = Integer.parseInt(BeforePOtotalCost);

		if(total != 0 && total < 1000) {
			Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Process']), 0)
			Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Approve Purchase Order']), 0)
			Thread.sleep(2000)
		}else {
			KeywordUtil.markFailed('total cost is zero or greater than approval limit')
		}
	}




}
