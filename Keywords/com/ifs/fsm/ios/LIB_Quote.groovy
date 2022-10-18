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
import com.kms.katalon.core.util.KeywordUtil

public class LIB_Quote {
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

	// This method returns the number of records inside a 'List (x)' in some screens in debrief workflow
	public String returnListValue() {
		String listValue = Mobile.getText(findTestObject('iOS/PG_Common/btn_CommonByContainText', [('idf_ButtonName') : 'List']), 0)
		String listValueSubstring = listValue.substring( listValue.indexOf("(") + 1, listValue.indexOf(")") );
		KeywordUtil.markPassed("No of List Values :" + listValueSubstring + " ")

		return listValueSubstring;
	}

	public String getQuoteCountFromGlobalMenu() {
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_HamburgerMenu'), 0, 0)
		String count = Mobile.getText(findTestObject('iOS/PG_Quotes/lbl_QuotesCountOnGlobalMenu'), 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_HamburgerMenu'), 0, 0)

		return count;
	}

	/**
	 * Verify available quote count
	 */
	@Keyword
	public void bf_QuoteList_VerifyAvailableQuoteCount() {
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_HamburgerMenu'), 0, 0)
		String count = Mobile.getText(findTestObject('iOS/PG_Quotes/lbl_QuotesCountOnGlobalMenu'), 0)
		int index = 1;
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_HamburgerMenu'), 0, 0)

		while(true) {
			boolean flag = Mobile.verifyElementNotExist(findTestObject('iOS/PG_Quotes/lbl_QuotesListItem', [('idf_Number') : index]), 0, FailureHandling.CONTINUE_ON_FAILURE);
			if(flag) {
				break;
			}
			index = index + 1;
		}
		Mobile.verifyEqual(count, index)
	}

	/**
	 * Verify available quote
	 */
	@Keyword
	public void bf_QuoteList_VerifyAvailableQuote() {
		//Backend intergration required
	}

	/**
	 * Verify search
	 */
	@Keyword
	public void bf_QuoteList_VerifySearch(String placename, String quoteId, String quotename) {
		Mobile.setText(findTestObject('iOS/PG_Common/tf_Search'), placename, 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_Search'), quoteId, 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_Search'), quotename, 0)
	}

	/**
	 * Verify available information on quote list items
	 */
	@Keyword
	public void bf_QuoteList_VerifyAvailableInformationQuoteListItems() {
		Mobile.verifyElementExist(findTestObject('iOS/PG_Quotes/PG_QuoteList/lbl_PlaceNameOfTheFirstElementFromList'), 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Quotes/PG_QuoteList/lbl_QuoteIDOfTheFirstElementFromList'), 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Quotes/PG_QuoteList/lbl_QuoteNameOfTheFirstElementFromList'), 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Quotes/PG_QuoteList/lbl_StartDateOfTheFirstElementFromList'), 0)
	}

	/**
	 * Add a new quote
	 */
	@Keyword
	public void bf_QuoteOverview_AddNewQuote(String name,String placeID) {
		LIB_Common.bf_SelectMenuItem('Add Quote');

		String countBefore = getQuoteCountFromGlobalMenu();

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Name']), name, 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Place ID']), placeID, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Expiration']), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0, 0)

		Mobile.delay(60)
		String countAfter = getQuoteCountFromGlobalMenu();
		Mobile.verifyGreaterThan(countAfter, countBefore)
	}

	/**
	 * Add a new quote-verify validations for mandatory fields
	 */
	@Keyword
	public void bf_QuoteOverview_AddNewQuoteVerifyValidationMandatoryFields() {
		LIB_Common.bf_SelectMenuItem('Add Quote');

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0, 0)

		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_ErrorMessage'), 5)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'OK']), 0)
	}

	/**
	 * Modify an available quote
	 */
	@Keyword
	public void bf_QuoteOverview_ModifyAvailableQuote(String name, String placeID) {
		Mobile.tap(findTestObject('iOS/PG_Quotes/lbl_FirstElementFromQuoteList'), 0)

		String countBefore = getQuoteCountFromGlobalMenu();

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Name']), name, 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Place ID']), placeID, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Expiration']), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0, 0)

		Mobile.delay(60)
		String countAfter = getQuoteCountFromGlobalMenu();
		Mobile.verifyEqual(countAfter, countBefore)
	}

	/**
	 * Verify available information on an available quote
	 */
	@Keyword
	public void bf_QuoteOverview_VerifyAvailableInformationAvailableQuote() {
		String outsideText = Mobile.getText(findTestObject('iOS/PG_Quotes/PG_QuoteList/lbl_QuoteNameOfTheFirstElementFromList'), 0)
		Mobile.tap(findTestObject('iOS/PG_Quotes/lbl_FirstElementFromQuoteList'), 0)

		String insideText = Mobile.getText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Name']), 0)

		Mobile.verifyEqual(insideText, outsideText)
	}

	/**
	 * Add a new quote invalid place ID
	 */
	@Keyword
	public void bf_QuoteOverview_AddNewQuoteInvalidPlaceID(String name, String addressID) {
		LIB_Common.bf_SelectMenuItem('Add Quote');

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Name']), name, 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Place ID']), 'ABC123Test', 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Address ID']), 0, 0)
		LIB_Common.clickElementFromPickerWheel(addressID)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0, 0)

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
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Quote Overview']), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Delete']), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Yes']), 0, 0)
	}

	/**
	 * Verify the navigation to QuoteNonPartUsage screen
	 */
	@Keyword
	public void bf_QuotePartUsage_VerifyNavigationtoQuoteNonPartUsageScreen() {
		Mobile.tap(findTestObject('iOS/PG_Quotes/lbl_FirstElementFromQuoteList'), 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0, 0)

		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Parts']), 0)
	}

	/**
	 *  Verify validations for mandatory fields
	 */
	@Keyword
	public void bf_QuotePartUsage_VerifyValidationsMandatoryFields() {
		Mobile.tap(findTestObject('iOS/PG_Quotes/lbl_FirstElementFromQuoteList'), 0)

		LIB_Common.bf_ClickContextMenuOption('Parts')
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Parts']), 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Part Line Code']), 0, 0)
		LIB_Common.clickElementFromPickerWheel('')
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), '', 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0, 0)

		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_ErrorMessage'), 5)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'OK']), 0)
	}

	/**
	 * Add a quote part usage
	 */
	@Keyword
	public void bf_QuotePartUsage_AddQuotePartusage(String partID, String quantity) {
		Mobile.tap(findTestObject('iOS/PG_Quotes/lbl_FirstElementFromQuoteList'), 0)

		LIB_Common.bf_ClickContextMenuOption('Parts')
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Parts']), 0)

		String beforeListValue = returnListValue();

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Part ID']), partID, 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), quantity, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']), 0, 0)

		String afterListValue = returnListValue();
		verifyGreaterThan(afterListValue, beforeListValue, 1)
	}

	/**
	 * Verify the available information on a QuotePartUsageList item
	 */
	@Keyword
	public void bf_QuotePartUsage_VerifyAvailableInformationQuotePartUsageListItem() {
		Mobile.tap(findTestObject('iOS/PG_Quotes/lbl_FirstElementFromQuoteList'), 0)
		LIB_Common.bf_ClickContextMenuOption('Parts')
		Mobile.tapAndHold(findTestObject('iOS/PG_WorkList/btn_List'), 0, 0)

		Mobile.verifyElementExist(findTestObject('iOS/PG_Quotes/PG_QuotePartUsage/lbl_BillPriceFromList'), 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Quotes/PG_QuotePartUsage/lbl_DescriptionFromList'), 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Quotes/PG_QuotePartUsage/lbl_LineCodeFromList'), 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Quotes/PG_QuotePartUsage/lbl_PartIDFromList'), 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Quotes/PG_QuotePartUsage/lbl_QuantityFromList'), 0)
	}

	/**
	 * Modify a quote part usage
	 */
	@Keyword
	public void bf_QuotePartUsage_ModifyQuotePartUsage(String partLineCode, String partID, String quantity, String billPrice) {
		Mobile.tap(findTestObject('iOS/PG_Quotes/lbl_FirstElementFromQuoteList'), 0)
		LIB_Common.bf_ClickContextMenuOption('Parts')

		Mobile.tapAndHold(findTestObject('iOS/PG_WorkList/btn_List'), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_WorkList/PG_PartNeeds/lbl_FirstElementFromPartNeedsList'), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Part Line Code']), 0, 0)
		LIB_Common.clickElementFromPickerWheel(partLineCode)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Part ID']), partID, 0)

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), quantity, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Bill Price']), billPrice, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Save']), 0, 0)
	}

	/**
	 * Delete a quote part usage
	 */
	@Keyword
	public void bf_QuotePartUsage_DeleteQuotePartUsage(String partID) {
		Mobile.tap(findTestObject('iOS/PG_Quotes/lbl_FirstElementFromQuoteList'), 0)

		LIB_Common.bf_ClickContextMenuOption('Parts')

		String beforeListValue = returnListValue();

		Mobile.tapAndHold(findTestObject('iOS/PG_WorkList/btn_List'), 0, 0)
		LIB_Common.swipeDelete(partID);

		LIB_Common.bf_ClickContextMenuOption('Parts')
		String afterListValue = returnListValue();
		verifyLessThan(afterListValue, beforeListValue, 1)
	}

	/**
	 * Verify the bill price
	 */
	@Keyword
	public void bf_QuotePartUsage_VerifyBillPrice(String serialTrackedPartId, String nonTrackedPartId, String lotTrackedPartId, String billPrice) {
		Mobile.tap(findTestObject('iOS/PG_Quotes/lbl_FirstElementFromQuoteList'), 0)
		LIB_Common.bf_ClickContextMenuOption('Parts')

		String beforeListValue = returnListValue();

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Part ID']), serialTrackedPartId, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Parts']), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']), 0, 0)

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Part ID']), nonTrackedPartId, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Parts']), 0, 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Bill Price']), billPrice, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']), 0, 0)

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Part ID']), lotTrackedPartId, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Parts']), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']), 0, 0)

		String afterListValue = returnListValue();
		verifyGreaterThan(afterListValue, beforeListValue, 3)
	}

	/**
	 * Verify the navigation to QuoteNotes screen
	 */
	@Keyword
	public void bf_QuoteNonPartUsage_VerifyNavigationQuoteNotesScreen() {
		Mobile.tap(findTestObject('iOS/PG_Quotes/lbl_FirstElementFromQuoteList'), 0)
		LIB_Common.bf_ClickContextMenuOption('Labor')
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Labor']), 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0, 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Notes']), 0)
	}

	/**
	 * TA-Quote Non-Part Usage: Verify validations for mandatory fields
	 */
	@Keyword
	public void bf_QuoteNonPartUsage_VerifyValidationsMandatoryFields(String lineCode) {
		Mobile.tap(findTestObject('iOS/PG_Quotes/lbl_FirstElementFromQuoteList'), 0)
		LIB_Common.bf_ClickContextMenuOption('Labor')

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Line Code']), 0, 0)
		LIB_Common.clickElementFromPickerWheel(lineCode)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']), 0, 0)

		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_ErrorMessage'), 5)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'OK']), 0)
	}

	/**
	 * Add a quote non-part usage
	 */
	@Keyword
	public void bf_QuoteNonPartUsage_Add(String quantity, String billPrice) {
		Mobile.tap(findTestObject('iOS/PG_Quotes/lbl_FirstElementFromQuoteList'), 0)
		LIB_Common.bf_ClickContextMenuOption('Labor')
		String beforeListValue = returnListValue();

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), quantity, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Bill Price']), billPrice, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']), 0, 0)

		String afterListValue = returnListValue();
		verifyGreaterThan(afterListValue, beforeListValue, 1)
	}

	/**
	 * Verify the available information on a QuoteNonPartUsageList item
	 */
	@Keyword
	public void bf_QuoteNonPartUsage_VerifyAvailableInformatioListItem() {
		Mobile.tap(findTestObject('iOS/PG_Quotes/lbl_FirstElementFromQuoteList'), 0)
		LIB_Common.bf_ClickContextMenuOption('Labor')
		Mobile.tapAndHold(findTestObject('iOS/PG_WorkList/btn_List'), 0, 0)

		Mobile.verifyElementExist(findTestObject('iOS/PG_Quotes/PG_QuoteNonPartUsage/lbl_BillPriceOfTheFirstElementFromList'), 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Quotes/PG_QuoteNonPartUsage/lbl_LineCodeOfTheFirstElementFromList'), 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Quotes/PG_QuoteNonPartUsage/lbl_QuantityOfTheFirstElementFromList'), 0)
	}

	/**
	 * Modify a quote non-part usage
	 */
	@Keyword
	public void bf_QuoteNonPartUsage_Modify(String lineCode, String quantity, String billPrice) {
		Mobile.tap(findTestObject('iOS/PG_Quotes/lbl_FirstElementFromQuoteList'), 0)
		LIB_Common.bf_ClickContextMenuOption('Labor')
		Mobile.tapAndHold(findTestObject('iOS/PG_WorkList/btn_List'), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Quotes/PG_QuoteNonPartUsage/lbl_LineCodeOfTheFirstElementFromList'), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Line Code']), 0, 0)
		LIB_Common.clickElementFromPickerWheel(lineCode)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), quantity, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Bill Price']), billPrice, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Save']), 0, 0)
	}

	/**
	 *  Delete a quote non-part usage
	 */
	@Keyword
	public void bf_QuoteNonPartUsage_Delete() {
		Mobile.tap(findTestObject('iOS/PG_Quotes/lbl_FirstElementFromQuoteList'), 0)
		LIB_Common.bf_ClickContextMenuOption('Labor')

		String beforeListValue = returnListValue();

		Mobile.tapAndHold(findTestObject('iOS/PG_WorkList/btn_List'), 0, 0)
		String text  = Mobile.getText(findTestObject('iOS/PG_Quotes/PG_QuoteNonPartUsage/lbl_LineCodeOfTheFirstElementFromList'), 0)
		LIB_Common.swipeDelete(text);

		LIB_Common.bf_ClickContextMenuOption('Labor')
		String afterListValue = returnListValue();
		verifyLessThan(afterListValue, beforeListValue, 1)
	}


	/**
	 *  TA-Quote Non-Part Usage: Verify the bill price
	 */
	@Keyword
	public void bf_QuoteNonPartUsage_VerifyBillPrice(String quantity, String lineCode, String billPrice){
		Mobile.tap(findTestObject('iOS/PG_Quotes/lbl_FirstElementFromQuoteList'), 0)
		LIB_Common.bf_ClickContextMenuOption('Labor')

		String beforeListValue = returnListValue();

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), quantity, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Line Code']), 0, 0)
		LIB_Common.clickElementFromPickerWheel(lineCode)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), quantity, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Bill Price']), billPrice, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Line Code']), 0, 0)
		LIB_Common.clickElementFromPickerWheel(lineCode)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), quantity, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']), 0, 0)

		String afterListValue = returnListValue();
		verifyGreaterThan(afterListValue, beforeListValue, 3)
	}


	/**
	 *  TA-Quote Notes: Verify navigation to the QuoteContact screen
	 */
	@Keyword
	public void bf_QuoteNotes_VerifyNavigationtoQuoteContactScreen() {
		Mobile.tap(findTestObject('iOS/PG_Quotes/lbl_FirstElementFromQuoteList'), 0)
		LIB_Common.bf_ClickContextMenuOption('Notes')
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Notes']), 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0, 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Contacts']), 0)
	}

	/**
	 *  Add a new quote note
	 */
	@Keyword
	public void bf_QuoteNotes_Add(String textLineCode, String text) {
		Mobile.tap(findTestObject('iOS/PG_Quotes/lbl_FirstElementFromQuoteList'), 0)
		LIB_Common.bf_ClickContextMenuOption('Notes')
		String beforeListValue = returnListValue();

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Text Line Code']), 0, 0)
		LIB_Common.clickElementFromPickerWheel(textLineCode)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tv_CommonByUpperLabelName', [('idf_LabelName') : 'Text']), text, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']), 0, 0)

		String afterListValue = returnListValue();
		verifyGreaterThan(afterListValue, beforeListValue, 1)
	}

	/**
	 *  TA-Quote Notes: Verify validations for mandatory fields
	 */
	@Keyword
	public void bf_QuoteNotes_VerifyValidationsMandatoryFields(String textLineCode) {
		Mobile.tap(findTestObject('iOS/PG_Quotes/lbl_FirstElementFromQuoteList'), 0)
		LIB_Common.bf_ClickContextMenuOption('Notes')
		String beforeListValue = returnListValue();

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Text Line Code']), 0, 0)
		LIB_Common.clickElementFromPickerWheel(textLineCode)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']), 0, 0)

		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_ErrorMessage'), 5)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'OK']), 0)
	}

	/**
	 *  TA-Quote Notes: Verify available information on a QuoteNotesList item
	 */
	@Keyword
	public void bf_QuoteNotes_VerifyAvailableInformationListItem() {
		Mobile.tap(findTestObject('iOS/PG_Quotes/lbl_FirstElementFromQuoteList'), 0)
		LIB_Common.bf_ClickContextMenuOption('Notes')
		Mobile.tapAndHold(findTestObject('iOS/PG_WorkList/btn_List'), 0, 0)

		Mobile.verifyElementExist(findTestObject('iOS/PG_Quotes/PG_QuoteNotes/lbl_NoteOfTheFirstElementFromList'), 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Quotes/PG_QuoteNotes/lbl_OwnerNameOfTheFirstElementFromList'), 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Quotes/PG_QuoteNotes/lbl_TextLineCodeOfTheFirstElementFromList'), 0)
	}

	/**
	 *  TA-Quote Notes: Modify a quote note
	 */
	@Keyword
	public void bf_QuoteNotes_Modify(String textLineCode, String text) {
		Mobile.tap(findTestObject('iOS/PG_Quotes/lbl_FirstElementFromQuoteList'), 0)
		LIB_Common.bf_ClickContextMenuOption('Notes')

		Mobile.tapAndHold(findTestObject('iOS/PG_WorkList/btn_List'), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Quotes/PG_QuoteNotes/lbl_NoteOfTheFirstElementFromList'), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Text Line Code']), 0, 0)
		LIB_Common.clickElementFromPickerWheel(textLineCode)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tv_CommonByUpperLabelName', [('idf_LabelName') : 'Text']), text, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Save']), 0, 0)
	}

	/**
	 *  TA-Quote Notes: Delete a quote note
	 */
	@Keyword
	public void bf_QuoteNotes_Delete() {
		Mobile.tap(findTestObject('iOS/PG_Quotes/lbl_FirstElementFromQuoteList'), 0)
		LIB_Common.bf_ClickContextMenuOption('Notes')
		String beforeListValue = returnListValue();

		Mobile.tapAndHold(findTestObject('iOS/PG_WorkList/btn_List'), 0, 0)
		String text  = Mobile.getText(findTestObject('iOS/PG_Quotes/PG_QuoteNotes/lbl_NoteOfTheFirstElementFromList'), 0)
		LIB_Common.swipeDelete(text);

		LIB_Common.bf_ClickContextMenuOption('Notes')
		String afterListValue = returnListValue();
		verifyLessThan(afterListValue, beforeListValue, 1)
	}

	/**
	 *  TA-Quote Contact: Verify navigation to the QuoteAttachmentList screen
	 */
	@Keyword
	public void bf_QuoteContacts_VerifyNavigationToQuoteAttachmentListScreen() {
		Mobile.tap(findTestObject('iOS/PG_Quotes/lbl_FirstElementFromQuoteList'), 0)
		LIB_Common.bf_ClickContextMenuOption('Contacts')
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Contacts']), 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0, 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Attachments']), 0)
	}


	/**
	 *  TA-Quote Contact: Verify validations for mandatory fields
	 */
	@Keyword
	public void bf_QuoteContacts_VerifyValidationsMandatoryFields(String mobilePhone) {
		Mobile.tap(findTestObject('iOS/PG_Quotes/lbl_FirstElementFromQuoteList'), 0)
		LIB_Common.bf_ClickContextMenuOption('Contacts')
		String beforeListValue = returnListValue();

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Phone']), mobilePhone, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Contacts']), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']), 0, 0)

		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_ErrorMessage'), 5)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'OK']), 0)
	}


	/**
	 *  TA-Quote Contact: Add a quote contact: place contact
	 */
	@Keyword
	public void bf_QuoteContacts_AddQuoteContactPlaceContact() {
		//Lookup icon
	}

	/**
	 *  TA-Quote Contact: Add a new quote contact
	 */
	@Keyword
	public void bf_QuoteContacts_Add(String firstName, String lastName, String phone) {
		Mobile.tap(findTestObject('iOS/PG_Quotes/lbl_FirstElementFromQuoteList'), 0)
		LIB_Common.bf_ClickContextMenuOption('Contacts')
		String beforeListValue = returnListValue();

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'First Name']), firstName, 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Last Name']), lastName, 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Phone']), phone, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Contacts']), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']), 0, 0)

		String afterListValue = returnListValue();
		verifyGreaterThan(afterListValue, beforeListValue, 1)
	}

	/**
	 *  TA-Quote Contact: Verify available information on a QuoteContactList item
	 */
	@Keyword
	public void bf_QuoteContacts_VerifyAvailableInformationQuoteContactListItem() {
		Mobile.tap(findTestObject('iOS/PG_Quotes/lbl_FirstElementFromQuoteList'), 0)
		LIB_Common.bf_ClickContextMenuOption('Contacts')
		Mobile.tapAndHold(findTestObject('iOS/PG_WorkList/btn_List'), 0, 0)

		Mobile.verifyElementExist(findTestObject('iOS/PG_Quotes/PG_QuoteContacts/lbl_ContactNameFromList'), 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Quotes/PG_QuoteContacts/lbl_EmailFromList'), 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Quotes/PG_QuoteContacts/lbl_MobilePhoneFromList'), 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Quotes/PG_QuoteContacts/lbl_PhoneFromList'), 0)
	}

	/**
	 *  TA-Quote Contact: Modify a quote contact
	 */
	@Keyword
	public void bf_QuoteContacts_Modify(String firstName, String lastName, String phone, String email, String mobilePhone) {
		Mobile.tap(findTestObject('iOS/PG_Quotes/lbl_FirstElementFromQuoteList'), 0)
		LIB_Common.bf_ClickContextMenuOption('Contacts')
		Mobile.tapAndHold(findTestObject('iOS/PG_WorkList/btn_List'), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Quotes/PG_QuoteContacts/lbl_ContactNameFromList'), 0, 0)

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'First Name']), firstName, 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Last Name']), lastName, 0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Phone']), phone, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Contacts']), 0,0)

		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Email']), email, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Contacts']), 0,0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Mobile Phone']), mobilePhone, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Contacts']), 0,0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Save']), 0, 0)
	}

	/**
	 *  Quote Attachment List: Verify the navigation to the QuoteSignature screen
	 */
	@Keyword
	public void bf_QuoteAttachment_VerifyNavigationtoQuoteSignatureScreen() {
		Mobile.tap(findTestObject('iOS/PG_Quotes/lbl_FirstElementFromQuoteList'), 0)
		LIB_Common.bf_ClickContextMenuOption('Attachments')
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Attachments']), 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0, 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Tech Summary']), 0)
	}


	/**
	 *  TA-Quote Attachment List: Add a static attachment
	 */
	@Keyword
	public void bf_QuoteAttachment_AddStaticAttachment(String description,String attachmentType) {
		//Attachment issue
	}


	/**
	 *  TA-Quote Attachment List: Add a photo attachment
	 */
	@Keyword
	public void bf_QuoteAttachment_AddPhotoAttachment(String description,String attachmentType) {
		//Attachment issue
	}

	/**
	 *  TA-Quote Attachment List: Add a video attachment
	 */
	@Keyword
	public void bf_QuoteAttachment_AddVideoAttachment(String description,String attachmentType) {
		//Attachment issue
	}

	/**
	 *  TA-Quote Attachment List: Attachment Full Screen
	 */
	@Keyword
	public void bf_QuoteAttachment_AttachmentFullScreen(String description) {
		Mobile.tap(findTestObject('iOS/PG_Quotes/lbl_FirstElementFromQuoteList'), 0)
		LIB_Common.bf_ClickContextMenuOption('Attachments')

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_FirstElementFromList', [('idf_LabelName') : description]), 0, 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'View Card']), 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_Back'), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_Back'), 0, 0)
	}

	/**
	 *  TA-Quote Attachment List: Delete an attachment
	 */
	@Keyword
	public void bf_QuoteAttachment_DeleteAttachment(String description) {
		Mobile.tap(findTestObject('iOS/PG_Quotes/lbl_FirstElementFromQuoteList'), 0)
		LIB_Common.bf_ClickContextMenuOption('Attachments')

		LIB_Common.swipeDelete(description)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_Back'), 0, 0)
	}


	/**
	 *  TA-Quote Attachment List: Attachment Card-Modify Description/ Type
	 */
	@Keyword
	public void bf_QuoteAttachment_AttachmentCardModifyDescription(String oldDescription, String description, String attachmentType) {
		Mobile.tap(findTestObject('iOS/PG_Quotes/lbl_FirstElementFromQuoteList'), 0)
		LIB_Common.bf_ClickContextMenuOption('Attachments')

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_FirstElementFromList', [('idf_LabelName') : oldDescription]), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'View Card']), 0, 0)

		Mobile.setText(findTestObject('iOS/PG_Common/tv_CommonByUpperLabelName', [('idf_LabelName') : 'Description']), description, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Attachment Type']), 0,0)
		LIB_Common.clickElementFromPickerWheel(attachmentType)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)


		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Save']), 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_Back'), 0, 0)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_Back'), 0, 0)
	}

	/**
	 *  TA-Quote Attachment List: Edit a photo
	 */
	@Keyword
	public void bf_QuoteAttachment_EditPhoto(String description) {
		Mobile.tap(findTestObject('iOS/PG_Quotes/lbl_FirstElementFromQuoteList'), 0)
		LIB_Common.bf_ClickContextMenuOption('Attachments')

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_FirstElementFromList', [('idf_LabelName') : description]), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Edit']), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Attachments/PG_Edit/btn_Draw'), 0, 0)
		Mobile.delay(5)
		Mobile.swipe(200, 0, 100, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Attachments/PG_Edit/btn_Correct'), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_Back'), 0, 0)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_Back'), 0, 0)
	}

	/**
	 *  TA-Quote Attachment List: Edit a photo-Clear modifications
	 */
	@Keyword
	public void bf_QuoteAttachment_EditphotoClearModifications(String description) {
		Mobile.tap(findTestObject('iOS/PG_Quotes/lbl_FirstElementFromQuoteList'), 0)
		LIB_Common.bf_ClickContextMenuOption('Attachments')

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_FirstElementFromList', [('idf_LabelName') : description]), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Edit']), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Attachments/PG_Edit/btn_Draw'), 0, 0)
		Mobile.delay(5)
		Mobile.swipe(200, 0, 100, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Attachments/PG_Edit/btn_Cross'), 0, 0)
	}

	/**
	 *  TA-Quote Signature: Verify information on QuoteSignature screen
	 */
	@Keyword
	public void bf_QuoteSignature_VerifyInformationOnQuoteSignatureScreen() {
		Mobile.tap(findTestObject('iOS/PG_Quotes/lbl_FirstElementFromQuoteList'), 0)
		LIB_Common.bf_ClickContextMenuOption('Tech Summary')
		Mobile.delay(3)
		Mobile.takeScreenshot('Screenshots/Quote_TechSummary.png', FailureHandling.STOP_ON_FAILURE)
	}

	/**
	 *  TA-Quote Signature: Verify navigation to the QuoteCustomerSignature screen
	 */
	@Keyword
	public void bf_QuoteSignature_VerifyNavigationtoQuoteCustomerSignatureScreen() {
		Mobile.tap(findTestObject('iOS/PG_Quotes/lbl_FirstElementFromQuoteList'), 0)
		LIB_Common.bf_ClickContextMenuOption('Tech Summary')

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0, 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Customer Summary']), 0)
	}

	/**
	 *  TA-Quote Signature: Verify signature required validation
	 */
	@Keyword
	public void bf_QuoteSignature_VerifySignatureRequiredValidation() {
		Mobile.tap(findTestObject('iOS/PG_Quotes/lbl_FirstElementFromQuoteList'), 0)
		LIB_Common.bf_ClickContextMenuOption('Tech Summary')

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Save']), 0, 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_ErrorMessage'), 5)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'OK']), 0)
	}

	/**
	 *  TA-Quote Signature: Add tech signature
	 */
	@Keyword
	public void bf_QuoteSignature_AddTechSignature() {
		Mobile.tap(findTestObject('iOS/PG_Quotes/lbl_FirstElementFromQuoteList'), 0)
		LIB_Common.bf_ClickContextMenuOption('Tech Summary')

		Mobile.tapAndHold(findTestObject('iOS/PG_Quotes/PG_TechSummary/ele_SignatureBoxInTechSummary'), 0, 0)
		Mobile.delay(5)
		Mobile.swipe(100, 50, 100, 100)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Accept']), 0, 0)
		Mobile.verifyElementNotExist(findTestObject('iOS/PG_Quotes/PG_TechSummary/lbl_TextInsideTheSignatureBox'), 0)
	}

	/**
	 *  TA-Quote Signature: FSMI- Clear tech signature
	 */
	@Keyword
	public void bf_QuoteSignature_ClearTechSignature() {
		Mobile.tap(findTestObject('iOS/PG_Quotes/lbl_FirstElementFromQuoteList'), 0)
		LIB_Common.bf_ClickContextMenuOption('Tech Summary')

		Mobile.tapAndHold(findTestObject('iOS/PG_Quotes/PG_TechSummary/ele_SignatureBoxInTechSummary'), 0, 0)
		Mobile.delay(5)
		Mobile.swipe(100, 50, 100, 100)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Accept']), 0, 0)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Clear']), 0, 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Quotes/PG_TechSummary/lbl_TextInsideTheSignatureBox'), 0)
	}

	/**
	 *  TA-Quote Customer Signature: Verify information on QuoteCustomerSignature screen
	 */
	@Keyword
	public void bf_QuoteCustomerSignature_VerifyInformatiQuoteCustomerSignatureScreen() {
		Mobile.tap(findTestObject('iOS/PG_Quotes/lbl_FirstElementFromQuoteList'), 0)
		LIB_Common.bf_ClickContextMenuOption('Customer Summary')
		Mobile.delay(3)
		Mobile.takeScreenshot('Screenshots/Quote_CustomerSummary.png', FailureHandling.STOP_ON_FAILURE)
	}

	/**
	 *  TA-Quote Customer Signature: Add customer signature
	 */
	@Keyword
	public void bf_QuoteCustomerSignature_AddCustomerSignature() {
		Mobile.tap(findTestObject('iOS/PG_Quotes/lbl_FirstElementFromQuoteList'), 0)
		LIB_Common.bf_ClickContextMenuOption('Customer Summary')

		Mobile.tapAndHold(findTestObject('iOS/PG_Quotes/PG_TechSummary/ele_SignatureBoxInTechSummary'), 0, 0)
		Mobile.delay(5)
		Mobile.swipe(100, 50, 100, 100)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Accept']), 0, 0)
		Mobile.verifyElementNotExist(findTestObject('iOS/PG_Quotes/PG_TechSummary/lbl_TextInsideTheSignatureBox'), 0)
	}


	/**
	 *  TA-Quote Customer Signature: Clear customer signature
	 */
	@Keyword
	public void bf_QuoteCustomerSignature_ClearCustomerSignature() {
		Mobile.tap(findTestObject('iOS/PG_Quotes/lbl_FirstElementFromQuoteList'), 0)
		LIB_Common.bf_ClickContextMenuOption('Customer Summary')

		Mobile.tapAndHold(findTestObject('iOS/PG_Quotes/PG_TechSummary/ele_SignatureBoxInTechSummary'), 0, 0)
		Mobile.delay(5)
		Mobile.swipe(100, 50, 100, 100)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Accept']), 0, 0)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Clear']), 0, 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Quotes/PG_TechSummary/lbl_TextInsideTheSignatureBox'), 0)
	}

	/**
	 *  TA-Quote Customer Signature: Accept a quote
	 */
	@Keyword
	public void bf_QuoteCustomerSignature_AcceptQuote() {
		Mobile.tap(findTestObject('iOS/PG_Quotes/lbl_FirstElementFromQuoteList'), 0)
		LIB_Common.bf_ClickContextMenuOption('Customer Summary')

		Mobile.tapAndHold(findTestObject('iOS/PG_Quotes/PG_TechSummary/ele_SignatureBoxInTechSummary'), 0, 0)
		Mobile.delay(5)
		Mobile.swipe(100, 50, 100, 100)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Accept']), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Accept']), 0, 0)
		Mobile.tap(findTestObject('iOS/PG_Quotes/lbl_FirstElementFromQuoteList'), 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Accepted']), 0)
	}


}
