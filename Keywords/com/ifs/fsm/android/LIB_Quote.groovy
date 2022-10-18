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

public class LIB_Quote {

	//This method will long press on an item
	public void LongPressListItem(String good,String longpressstockListItem) {
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

	//get the purchase order count in the global menu
	public String getQuoteCount() {
		Mobile.tapAndHold(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0, 0)
		String count = Mobile.getText(findTestObject('Android/PG_Common/lbl_CommonByUpperLabelName', [('idf_LabelName') : 'Quotes']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenuBack'), 0)
		return count;
	}

	/**
	 * Verify available quote count
	 */ 
	@Keyword
	public void bf_QuoteList_VerifyAvailableQuoteCount() {
		String beforeQuoteCount = getQuoteCount()
		Mobile.verifyElementExist(findTestObject('Android/PG_Quote/lbl_Count',[('idf_LabelName') : beforeQuoteCount]), 0)
	}

	/**
	 * Verify available quote 
	 */
	@Keyword
	public void bf_QuoteList_VerifyAvailableQuote() {
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "TA-CUSTOMER PLACE1"]), 0)
	}

	/**
	 * Verify search
	 */
	@Keyword
	public void bf_QuoteList_VerifySearch(String placename,String quoteId,String quotename) {
		Mobile.setText(findTestObject('Android/PG_Stocks/tf_SearchField', [('idf_LabelName') : 'Search']), placename, 3)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Stocks/tf_SearchField', [('idf_LabelName') : 'Search']), quoteId, 3)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Stocks/tf_SearchField', [('idf_LabelName') : 'Search']), quotename, 3)
		Thread.sleep(2000)
	}

	/**
	 * Verify available information on quote list items
	 */
	@Keyword
	public void bf_QuoteList_VerifyAvailableInformationQuoteListItems(String quoteID,String quotename) {
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "TA-CUSTOMER PLACE1"]), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : quoteID]), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : quotename]), 0)
	}

	/**
	 * Add a new quote
	 */
	@Keyword
	public void bf_QuoteOverview_AddNewQuote(String name,String placeID) {
		String beforeQuoteCount = getQuoteCount()
		LongPressListItem('TA-CUSTOMER PLACE1','Add Quote')
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Name']), name, 3)
		Thread.sleep(2000)
		tapLookUpIcon('Place ID')
		Mobile.setText(findTestObject('Android/PG_Stocks/tf_SearchField', [('idf_LabelName') : 'Search']),placeID , 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : placeID]), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Parts"]), 0)
		String afterQuoteCount = getQuoteCount()
		Thread.sleep(3000)
		Mobile.verifyGreaterThan(afterQuoteCount,beforeQuoteCount)
	}

	/**
	 * Add a new quote-verify validations for mandatory fields
	 */
	@Keyword
	public void bf_QuoteOverview_AddNewQuoteVerifyValidationMandatoryFields() {
		LongPressListItem('TA-CUSTOMER PLACE1','Add Quote')
		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		def message = Mobile.getText(findTestObject('Android/PG_WorkList/lbl_ErrorMessageForRequiredFields'),10)
		KeywordUtil.markPassed("Error Message : " + message)
		Thread.sleep(5000)
	}

	/**
	 * Modify an available quote
	 */
	@Keyword
	public void bf_QuoteOverview_ModifyAvailableQuote(String name,String placeID) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'TA-CUSTOMER PLACE1']), 0)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Name']), name, 3)
		Thread.sleep(2000)
		tapLookUpIcon('Place ID')
		Mobile.clearText(findTestObject('Android/PG_Stocks/tf_SearchField'), 10)
		Mobile.setText(findTestObject('Android/PG_Stocks/tf_SearchField', [('idf_LabelName') : 'Search']),placeID , 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : placeID]), 0)
		tapLookUpIcon('Start')
		Mobile.tap(findTestObject('Android/PG_POProcess/tv_date'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText',[('idf_ButtonName') : "OK"]), 0)
		tapLookUpIcon('Expiration')
		Mobile.tap(findTestObject('Android/PG_POProcess/tv_date'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText',[('idf_ButtonName') : "OK"]), 0)
		Mobile.tap(findTestObject('Android/PG_WorkList/btn_Update'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Overview"]), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Parts"]), 0)
	}

	/**
	 * Verify available information on an available quote
	 */
	@Keyword
	public void bf_QuoteOverview_VerifyAvailableInformationAvailableQuote() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'TA-CUSTOMER PLACE1']), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "TA-MOBPLACE2"]), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "New"]), 0)
	}

	/**
	 * Add a new quote invalid place ID
	 */
	@Keyword
	public void bf_QuoteOverview_AddNewQuoteInvalidPlaceID(String name,String placeID,String addressID) {
		LongPressListItem('TA-CUSTOMER PLACE1','Add Quote')
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Name']), name, 3)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Place ID']), placeID, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Address ID']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : addressID]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Parts"]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_SyncErrorMessage'), 0)
	}

	/**
	 * Verify the navigation to QuoteNonPartUsage screen
	 */
	@Keyword
	public void bf_QuotePartUsage_VerifyNavigationtoQuoteNonPartUsageScreen() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'TA-CUSTOMER PLACE1']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Parts']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Labor"]), 0)
	}

	/**
	 *  Verify validations for mandatory fields
	 */
	@Keyword
	public void bf_QuotePartUsage_VerifyValidationsMandatoryFields() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'TA-CUSTOMER PLACE1']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Parts']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Part Line Code']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Quote/lbl_PartLineCode'), 0)
		Thread.sleep(2000)
		Mobile.clearText(findTestObject('Android/PG_WorkList/PG_PartNeeds/tf_Quantity'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_ErrorMessageWithContinue'), 0)
	}

	/**
	 * Add a quote part usage
	 */
	@Keyword
	public void bf_QuotePartUsage_AddQuotePartusage(String partID,String quantity,String billPrice) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'TA-CUSTOMER PLACE1']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Parts']), 0)
		String listValuesBefore = Mobile.getText(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		String listValuesBeforeSubstring = listValuesBefore.substring(listValuesBefore.indexOf("(") + 1, listValuesBefore.indexOf(")"));
		KeywordUtil.markPassed("No of List Values before adding a part " + listValuesBeforeSubstring + " ")
		Thread.sleep(2000)
		tapLookUpIcon('Part ID')
		Mobile.setText(findTestObject('Android/PG_Stocks/tf_SearchField', [('idf_LabelName') : 'Search']),partID , 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : partID]), 0)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), quantity, 3)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Bill Price']), billPrice, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Add'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Parts"]), 0)
		String listValuesAfter = Mobile.getText(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		String listValuesAfterSubstring = listValuesAfter.substring(listValuesAfter.indexOf("(") + 1, listValuesAfter.indexOf(")"));
		KeywordUtil.markPassed("No of List Values after adding a new part " + listValuesAfterSubstring + " ")
		Boolean listvaluesresult = Mobile.verifyGreaterThan(listValuesAfterSubstring, listValuesBeforeSubstring)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Labor"]), 0)

	}

	/**
	 * Verify the available information on a QuotePartUsageList item
	 */
	@Keyword
	public void bf_QuotePartUsage_VerifyAvailableInformationQuotePartUsageListItem() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'TA-CUSTOMER PLACE1']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Parts']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Billable Part"]), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "TA-SER-PART1"]), 0)
	}

	/**
	 * Modify a quote part usage
	 */
	@Keyword
	public void bf_QuotePartUsage_ModifyQuotePartUsage(String partLineCode,String partID,String quantity,String billPrice) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'TA-CUSTOMER PLACE1']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Parts']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'TA-Serial Tracked Part1']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText',[('idf_ButtonName') : "MODIFY"]), 0)
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Part Line Code']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : partLineCode]), 0)
		Thread.sleep(2000)
		tapLookUpIcon('Part ID')
		Mobile.clearText(findTestObject('Android/PG_Stocks/tf_SearchField'), 10)
		Mobile.setText(findTestObject('Android/PG_Stocks/tf_SearchField', [('idf_LabelName') : 'Search']),partID , 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : partID]), 0)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), quantity, 3)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Bill Price']), billPrice, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_WorkList/btn_Update'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Parts"]), 0)
	}

	/**
	 * Delete a quote part usage
	 */
	@Keyword
	public void bf_QuotePartUsage_DeleteQuotePartUsage() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'TA-CUSTOMER PLACE1']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Parts']), 0)
		String listValuesBefore = Mobile.getText(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		String listValuesBeforeSubstring = listValuesBefore.substring(listValuesBefore.indexOf("(") + 1, listValuesBefore.indexOf(")"));
		KeywordUtil.markPassed("No of List Values before deleting" + listValuesBeforeSubstring + " ")
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'TA-Non Tracked Part1']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText',[('idf_ButtonName') : "DELETE"]), 0)
		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Parts']), 0)
		String listValuesAfter = Mobile.getText(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		String listValuesAfterSubstring = listValuesAfter.substring(listValuesAfter.indexOf("(") + 1, listValuesAfter.indexOf(")"));
		KeywordUtil.markPassed("No of List Values after deleting " + listValuesAfterSubstring + " ")
		Boolean listvaluesresult = Mobile.verifyLessThan(listValuesAfterSubstring, listValuesBeforeSubstring)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Parts"]), 0)

	}

	/**
	 * Verify the bill price
	 */
	@Keyword
	public void bf_QuotePartUsage_VerifyBillPrice(String partID,String partID1,String billPrice,String partID2) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'TA-CUSTOMER PLACE1']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Parts']), 0)
		Thread.sleep(2000)
		tapLookUpIcon('Part ID')
		Mobile.setText(findTestObject('Android/PG_Stocks/tf_SearchField', [('idf_LabelName') : 'Search']),partID , 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : partID]), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Add'), 0)
		Thread.sleep(2000)
		tapLookUpIcon('Part ID')
		Mobile.setText(findTestObject('Android/PG_Stocks/tf_SearchField', [('idf_LabelName') : 'Search']),partID1 , 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : partID1]), 0)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Bill Price']), billPrice, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Add'), 0)
		Thread.sleep(2000)
		tapLookUpIcon('Part ID')
		Mobile.setText(findTestObject('Android/PG_Stocks/tf_SearchField', [('idf_LabelName') : 'Search']),partID2 , 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : partID2]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Add'), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByUpperLabelName',[('idf_LabelName') : "Billable Part"]), 0)
	}

	/**
	 * Verify the navigation to QuoteNotes screen
	 */
	@Keyword
	public void bf_QuoteNonPartUsage_VerifyNavigationQuoteNotesScreen() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'TA-CUSTOMER PLACE1']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Labor']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Notes"]), 0)
	}

	/**
	 * TA-Quote Non-Part Usage: Verify validations for mandatory fields
	 */
	@Keyword
	public void bf_QuoteNonPartUsage_QuoteNonPartUsageVerifyValidationsMandatoryFields(String LineCode) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'TA-CUSTOMER PLACE1']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Labor']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Line Code']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : LineCode]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Add'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_ErrorMessageForRequiredFields'), 0)
	}

	/**
	 * Add a quote non-part usage
	 */
	@Keyword
	public void bf_QuoteNonPartUsage_AddQuoteNonPartUsage(String quantity,String billPrice) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'TA-CUSTOMER PLACE1']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Labor']), 0)
		Thread.sleep(2000)
		String listValuesBefore = Mobile.getText(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		String listValuesBeforeSubstring = listValuesBefore.substring(listValuesBefore.indexOf("(") + 1, listValuesBefore.indexOf(")"));
		KeywordUtil.markPassed("No of List Values before adding quote" + listValuesBeforeSubstring + " ")
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), quantity, 3)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Bill Price']), billPrice, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Add'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Labor"]), 0)
		String listValuesAfter = Mobile.getText(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		String listValuesAfterSubstring = listValuesAfter.substring(listValuesAfter.indexOf("(") + 1, listValuesAfter.indexOf(")"));
		KeywordUtil.markPassed("No of List Values after adding quote" + listValuesAfterSubstring + " ")
		Boolean listvaluesresult = Mobile.verifyGreaterThan(listValuesAfterSubstring, listValuesBeforeSubstring)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Labor"]), 0)
		Mobile.pressBack()
		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Notes"]), 0)

	}

	/**
	 * Verify the available information on a QuoteNonPartUsageList item
	 */
	@Keyword
	public void bf_QuoteNonPartUsage_VerifyAvailableInformationQuoteNonPartUsageListItem() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'TA-CUSTOMER PLACE1']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Labor']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Meeting"]), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "2"]), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByUpperLabelName',[('idf_LabelName') : "2"]), 0)
	}

	/**
	 * Modify a quote non-part usage
	 */
	@Keyword
	public void bf_QuoteNonPartUsage_ModifyQuoteNonPartUsage(String LineCode,String quantity,String billPrice) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'TA-CUSTOMER PLACE1']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Labor']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Meeting']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText',[('idf_ButtonName') : "MODIFY"]), 0)
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Line Code']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : LineCode]), 0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), quantity, 3)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Bill Price']), billPrice, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_WorkList/btn_Update'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Labor"]), 0)
	}



	/**
	 *  Delete a quote non-part usage
	 */
	@Keyword
	public void bf_QuoteNonPartUsage_DeleteQuoteNonPartUsage() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'TA-CUSTOMER PLACE1']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Labor']), 0)
		Thread.sleep(2000)
		String listValuesBefore = Mobile.getText(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		String listValuesBeforeSubstring = listValuesBefore.substring(listValuesBefore.indexOf("(") + 1, listValuesBefore.indexOf(")"));
		KeywordUtil.markPassed("No of List Values before deleting" + listValuesBeforeSubstring + " ")
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Labor"]), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Meeting']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText',[('idf_ButtonName') : "DELETE"]), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Labor"]), 0)
		Mobile.verifyElementNotExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Meeting"]), 0)
		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Labor']), 0)
		String listValuesAfter = Mobile.getText(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		String listValuesAfterSubstring = listValuesAfter.substring(listValuesAfter.indexOf("(") + 1, listValuesAfter.indexOf(")"));
		KeywordUtil.markPassed("No of List Values after deleting " + listValuesAfterSubstring + " ")
		Boolean listvaluesresult = Mobile.verifyLessThan(listValuesAfterSubstring, listValuesBeforeSubstring)
	}


	/**
	 *  TA-Quote Non-Part Usage: Verify the bill price
	 */
	@Keyword
	public void bf_QuoteNonPartUsage_NonPartUsageVerifyBillPrice(String quantity,String LineCode,String billPrice,String Linecode,String quantity1){
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'TA-CUSTOMER PLACE1']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Labor']), 0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), quantity, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Add'), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Line Code']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : LineCode]), 0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), quantity, 3)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Bill Price']),billPrice , 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Add'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : Linecode]), 0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Quantity']), quantity1, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Add'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
	}

	/**
	 *  TA-Quote Notes: Verify navigation to the QuoteContact screen
	 */
	@Keyword
	public void bf_QuoteNotes_VerifyNavigationtoQuoteContactScreen() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'TA-CUSTOMER PLACE1']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Notes']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Contacts"]), 0)
	}

	/**
	 *  Add a new quote note
	 */
	@Keyword
	public void bf_QuoteNotes_AddNewQuoteNote(String TextLineCode,String text) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'TA-CUSTOMER PLACE1']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Notes']), 0)
		Thread.sleep(2000)
		String listValuesBefore = Mobile.getText(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		String listValuesBeforeSubstring = listValuesBefore.substring(listValuesBefore.indexOf("(") + 1, listValuesBefore.indexOf(")"));
		KeywordUtil.markPassed("No of List Values before adding quote" + listValuesBeforeSubstring + " ")
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Text Line Code']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : TextLineCode]), 0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Text']), text, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Add'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Notes"]), 0)
		String listValuesAfter = Mobile.getText(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		String listValuesAfterSubstring = listValuesAfter.substring(listValuesAfter.indexOf("(") + 1, listValuesAfter.indexOf(")"));
		KeywordUtil.markPassed("No of List Values after adding quote " + listValuesAfterSubstring + " ")
		Boolean listvaluesresult = Mobile.verifyGreaterThan(listValuesAfterSubstring, listValuesBeforeSubstring)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Solution"]), 0)
		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Notes']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Contacts"]), 0)
	}

	/**
	 *  TA-Quote Notes: Verify validations for mandatory fields
	 */
	@Keyword
	public void bf_QuoteNotes_QuoteNotesVerifyValidationsMandatoryFields(String TextLineCode) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'TA-CUSTOMER PLACE1']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Notes']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Text Line Code']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : TextLineCode]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Add'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_ErrorMessageForRequiredFields'), 0)
	}

	/**
	 *  TA-Quote Notes: Verify available information on a QuoteNotesList item
	 */
	@Keyword
	public void bf_QuoteNotes_VerifyAvailableInformationQuoteNotesListItem() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'TA-CUSTOMER PLACE1']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Notes']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Solution"]), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "TA USER1"]), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Test text"]), 0)
	}

	/**
	 *  TA-Quote Notes: Modify a quote note
	 */
	@Keyword
	public void bf_QuoteNotes_ModifyQuoteNote(String TextLineCode,String text) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'TA-CUSTOMER PLACE1']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Notes']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'TA USER1']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText',[('idf_ButtonName') : "MODIFY"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Text Line Code']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : TextLineCode]), 0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Text']), text, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_WorkList/btn_Update'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Notes"]), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Description"]), 0)
	}

	/**
	 *  TA-Quote Notes: Delete a quote note
	 */
	@Keyword
	public void bf_QuoteNotes_DeleteQuoteNote() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'TA-CUSTOMER PLACE1']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Notes']), 0)
		Thread.sleep(2000)
		String listValuesBefore = Mobile.getText(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		String listValuesBeforeSubstring = listValuesBefore.substring(listValuesBefore.indexOf("(") + 1, listValuesBefore.indexOf(")"));
		KeywordUtil.markPassed("No of List Values before deleting" + listValuesBeforeSubstring + " ")
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'TA USER1']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText',[('idf_ButtonName') : "DELETE"]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Notes"]), 0)
		Mobile.verifyElementNotExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Info"]), 0)
		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Notes']), 0)
		String listValuesAfter = Mobile.getText(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		String listValuesAfterSubstring = listValuesAfter.substring(listValuesAfter.indexOf("(") + 1, listValuesAfter.indexOf(")"));
		KeywordUtil.markPassed("No of List Values after deleting " + listValuesAfterSubstring + " ")
		Boolean listvaluesresult = Mobile.verifyLessThan(listValuesAfterSubstring, listValuesBeforeSubstring)
	}

	/**
	 *  TA-Quote Contact: Verify navigation to the QuoteAttachmentList screen
	 */
	@Keyword
	public void bf_QuoteContact_VerifyNavigationtoQuoteAttachmentListScreen() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'TA-CUSTOMER PLACE1']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Contacts']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Attachments"]), 0)
		Thread.sleep(2000)
		Mobile.pressBack()
	}


	/**
	 *  TA-Quote Contact: Verify validations for mandatory fields
	 */
	@Keyword
	public void bf_QuoteContact_QuoteContactVerifyValidationsMandatoryFields(String mobilePhone) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'TA-CUSTOMER PLACE1']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Contacts']), 0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Mobile Phone']), mobilePhone, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Add'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_ErrorMessageForRequiredFields'), 0)
	}


	/**
	 *  TA-Quote Contact: Add a quote contact: place contact
	 */
	@Keyword
	public void bf_QuoteContact_AddQuoteContactPlaceContact() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'TA-CUSTOMER PLACE1']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Contacts']), 0)
		Thread.sleep(2000)
		String listValuesBefore = Mobile.getText(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		String listValuesBeforeSubstring = listValuesBefore.substring(listValuesBefore.indexOf("(") + 1, listValuesBefore.indexOf(")"));
		KeywordUtil.markPassed("No of List Values before adding quote" + listValuesBeforeSubstring + " ")
		Thread.sleep(2000)
		tapLookUpIcon('First Name')
		Mobile.tap(findTestObject('Android/PG_Common/lbl_FirstItemFromListWithSeperator'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Add'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Contacts"]), 0)
		String listValuesAfter = Mobile.getText(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		String listValuesAfterSubstring = listValuesAfter.substring(listValuesAfter.indexOf("(") + 1, listValuesAfter.indexOf(")"));
		KeywordUtil.markPassed("No of List Values after adding quote " + listValuesAfterSubstring + " ")
		Boolean listvaluesresult = Mobile.verifyGreaterThan(listValuesAfterSubstring, listValuesBeforeSubstring)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Attachments"]), 0)
		Mobile.pressBack()
	}

	/**
	 *  TA-Quote Contact: Add a new quote contact
	 */
	@Keyword
	public void bf_QuoteContact_AddNewQuoteContact(String firstName,String lastName,String phone,String extension,String email) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'TA-CUSTOMER PLACE1']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Contacts']), 0)
		Thread.sleep(2000)
		String listValuesBefore = Mobile.getText(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		String listValuesBeforeSubstring = listValuesBefore.substring(listValuesBefore.indexOf("(") + 1, listValuesBefore.indexOf(")"));
		KeywordUtil.markPassed("No of List Values before adding quote" + listValuesBeforeSubstring + " ")
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'First Name']), firstName, 3)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Last Name']), lastName, 3)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Phone']), phone, 3)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Extension']), extension, 3)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Email']), email, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Add'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Contacts"]), 0)
		String listValuesAfter = Mobile.getText(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		String listValuesAfterSubstring = listValuesAfter.substring(listValuesAfter.indexOf("(") + 1, listValuesAfter.indexOf(")"));
		KeywordUtil.markPassed("No of List Values after adding quote " + listValuesAfterSubstring + " ")
		Boolean listvaluesresult = Mobile.verifyGreaterThan(listValuesAfterSubstring, listValuesBeforeSubstring)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Attachments"]), 0)
		Mobile.pressBack()
	}

	/**
	 *  TA-Quote Contact: Verify available information on a QuoteContactList item
	 */
	@Keyword
	public void bf_QuoteContact_VerifyAvailableInformationQuoteContactListItem() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'TA-CUSTOMER PLACE1']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Contacts']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "David Peterson"]), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "234-784-789"]), 0)
	}


	/**
	 *  TA-Quote Contact: Modify a quote contact
	 */
	@Keyword
	public void bf_QuoteContact_ModifyQuoteContact(String firstName,String lastName,String phone,String email,String mobilePhone) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'TA-CUSTOMER PLACE1']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Contacts']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "David Peterson"]), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText',[('idf_ButtonName') : "MODIFY"]), 0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'First Name']), firstName, 3)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Last Name']), lastName, 3)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Phone']), phone, 3)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Email']), email, 3)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Mobile Phone']), mobilePhone, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_WorkList/btn_Update'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Contacts"]), 0)
	}


	/**
	 *  Quote Attachment List: Verify the navigation to the QuoteSignature screen
	 */
	@Keyword
	public void bf_QuoteAttachmentList_VerifyNavigationtoQuoteSignatureScreen() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'TA-CUSTOMER PLACE1']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Attachments']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Tech Summary"]), 0)
	}


	/**
	 *  TA-Quote Attachment List: Add a static attachment
	 */
	@Keyword
	public void bf_QuoteAttachmentList_AddStaticAttachment(String description,String attachmentType) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'TA-CUSTOMER PLACE1']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Attachments']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_AttachFile'), 0)
		Thread.sleep(7000)
		Mobile.tap(findTestObject('Android/PG_Quote/lbl_Image'), 0)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Description']), description, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Attachment Type']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : attachmentType]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Quote/btn_Save'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Attachments"]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Solution"]), 0)
		Mobile.pressBack()
	}


	/**
	 *  TA-Quote Attachment List: Add a photo attachment
	 */
	@Keyword
	public void bf_QuoteAttachmentList_AddPhotoAttachment(String description,String attachmentType) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'TA-CUSTOMER PLACE1']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Attachments']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Camera'), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Quote/btn_CameraCapture'), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Quote/btn_Confirm'), 0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Description']), description, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Attachment Type']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : attachmentType]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Quote/btn_Save'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Attachments"]), 0)
		Thread.sleep(2000)
		Mobile.pressBack()
	}

	/**
	 *  TA-Quote Attachment List: Add a video attachment
	 */
	@Keyword
	public void bf_QuoteAttachmentList_AddVideoAttachment(String description,String attachmentType) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'TA-CUSTOMER PLACE1']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Attachments']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_VideoCamera'), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Quote/btn_CameraCapture'), 0)
		Thread.sleep(1000)
		Mobile.tap(findTestObject('Android/PG_Quote/btn_CameraCapture'), 0)
		Thread.sleep(1000)
		Mobile.tap(findTestObject('Android/PG_Quote/btn_Confirm'), 0)
		Thread.sleep(1000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Description']), description, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Attachment Type']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : attachmentType]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Quote/btn_Save'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Attachments"]), 0)
		Thread.sleep(2000)
		Mobile.pressBack()
	}

	/**
	 *  TA-Quote Attachment List: Attachment Full Screen
	 */
	@Keyword
	public void bf_QuoteAttachmentList_AttachmentFullScreen() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'TA-CUSTOMER PLACE1']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Attachments']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Solution']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Attachments"]), 0)
		Mobile.takeScreenshot('Screenshots/FullScreen.png', FailureHandling.STOP_ON_FAILURE)
		Thread.sleep(2000)
		Mobile.swipe(200, 200, 250, 200)
		Thread.sleep(2000)
		Mobile.pressBack()
		Mobile.pressBack()
		//to be continued
	}

	/**
	 *  TA-Quote Attachment List: Delete an attachment
	 */
	@Keyword
	public void bf_QuoteAttachmentList_DeleteAttachment() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'TA-CUSTOMER PLACE1']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Attachments']), 0)
		Thread.sleep(2000)
		Mobile.tapAndHold(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Solution']) ,0,0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText',[('idf_ButtonName') : "YES"]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementNotExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Solution']) ,0,0)
		Thread.sleep(2000)
		Mobile.pressBack()
	}


	/**
	 *  TA-Quote Attachment List: Attachment Card-Modify Description/ Type
	 */
	@Keyword
	public void bf_QuoteAttachmentList_AttachmentCardModifyDescription(String description,String attachmentType) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'TA-CUSTOMER PLACE1']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Attachments']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Solution']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Quote/btn_ViewCard'), 0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Description']), description, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Attachment Type']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : attachmentType]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Quote/btn_Save'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Attachments"]), 0)
		Mobile.pressBack()
		Mobile.pressBack()
	}

	/**
	 *  TA-Quote Attachment List: Edit a photo
	 */
	@Keyword
	public void bf_QuoteAttachmentList_EditPhoto(String text) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'TA-CUSTOMER PLACE1']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Attachments']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Solution']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Quote/btn_EditAttachment'), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'Text']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Quote/btn_PhotoEditor'), 0)
		Mobile.setText(findTestObject('Android/PG_Quote/tf_text'), text, 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'OK']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Quote/btn_Apply'), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Quote/btn_ApplyPhotoEditor'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Attachments"]), 0)
		Mobile.pressBack()
		Mobile.pressBack()
	}

	/**
	 *  TA-Quote Attachment List: Edit a photo-Clear modifications
	 */
	@Keyword
	public void bf_QuoteAttachmentList_EditphotoClearModifications(String text) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'TA-CUSTOMER PLACE1']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Attachments']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Solution']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Quote/btn_EditAttachment'), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'Text']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Quote/btn_PhotoEditor'), 0)
		Mobile.setText(findTestObject('Android/PG_Quote/tf_text'), text, 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'OK']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Quote/btn_Cancel'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Photo Editor"]), 0)
		Mobile.pressBack()
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'Exit']), 0)
		Mobile.pressBack()
		Mobile.pressBack()
	}

	/**
	 *  TA-Quote Signature: Verify information on QuoteSignature screen
	 */
	@Keyword
	public void bf_QuoteSignature_VerifyInformationOnQuoteSignatureScreen() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'TA-CUSTOMER PLACE1']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Tech Summary']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Place ID"]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Name"]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Start Date"]), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Parts"]), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Labor"]), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Total Parts"]), 0)
	}

	/**
	 *  TA-Quote Signature: Verify navigation to the QuoteCustomerSignature screen
	 */
	@Keyword
	public void bf_QuoteSignature_VerifyNavigationtoQuoteCustomerSignatureScreen() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'TA-CUSTOMER PLACE1']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Tech Summary']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Customer Summary"]), 0)
	}

	/**
	 *  TA-Quote Signature: Verify signature required validation
	 */
	@Keyword
	public void bf_QuoteSignature_VerifySignatureRequiredValidation() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'TA-CUSTOMER PLACE1']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Tech Summary']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Add'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_ErrorMessageForRequiredFields'), 0)
	}

	/**
	 *  TA-Quote Signature: Add tech signature
	 */
	@Keyword
	public void bf_QuoteSignature_AddTechSignature() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'TA-CUSTOMER PLACE1']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Tech Summary']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Quote/lbl_SignaturePreview'), 0)

		int startingX = ( Mobile.getElementWidth(findTestObject('Android/PG_Quote/lbl_SignturePad'), 0) / 2 )
		int endingX = startingX + 50
		int startingY = startingX
		int endingY = startingX + 50
		Mobile.swipe(200, 200, 250, 200)

		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Quote/btn_Accept'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Tech Summary"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Add'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Tech Summary"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Customer Summary"]), 0)
		Thread.sleep(2000)
	}

	/**
	 *  TA-Quote Signature: FSMI- Clear tech signature
	 */
	@Keyword
	public void bf_QuoteSignature_ClearTechSignature() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'TA-CUSTOMER PLACE1']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Tech Summary']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Quote/lbl_SignaturePreview'), 0)
		int startingX = ( Mobile.getElementWidth(findTestObject('Android/PG_Quote/lbl_SignturePad'), 0) / 2 )
		int endingX = startingX + 50
		int startingY = startingX
		int endingY = startingX + 50
		Mobile.swipe(200, 200, 250, 200)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Quote/btn_Accept'), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Quote/btn_ClearSignature'), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES' ]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Tech Summary"]), 0)
	}

	/**
	 *  TA-Quote Customer Signature: Verify information on QuoteCustomerSignature screen
	 */
	@Keyword
	public void bf_QuoteCustomerSignature_VerifyInformatiQuoteCustomerSignatureScreen() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'TA-CUSTOMER PLACE1']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Customer Summary']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Place ID"]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Name"]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Start Date"]), 0)
	}

	/**
	 *  TA-Quote Customer Signature: Add customer signature
	 */
	@Keyword
	public void bf_QuoteCustomerSignature_AddCustomerSignature() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'TA-CUSTOMER PLACE1']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Customer Summary']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Quote/lbl_SignaturePreview'), 0)
		//tapSignaturePad()
		int startingX = ( Mobile.getElementWidth(findTestObject('Android/PG_Quote/lbl_SignturePad'), 0) / 2 )
		int endingX = startingX + 50
		int startingY = startingX
		int endingY = startingX + 50
		Mobile.swipe(200, 200, 250, 200)

		Mobile.tap(findTestObject('Android/PG_Quote/btn_Accept'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Customer Summary"]), 0)
	}


	/**
	 *  TA-Quote Customer Signature: Clear customer signature
	 */
	@Keyword
	public void bf_QuoteCustomerSignature_ClearCustomerSignature() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'TA-CUSTOMER PLACE1']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Customer Summary']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Quote/lbl_SignaturePreview'), 0)

		int startingX = ( Mobile.getElementWidth(findTestObject('Android/PG_Quote/lbl_SignturePad'), 0) / 2 )
		int endingX = startingX + 50
		int startingY = startingX
		int endingY = startingX + 50
		Mobile.swipe(200, 200, 250, 200)

		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Quote/btn_Accept'), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Quote/btn_ClearSignature'), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES' ]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Customer Summary"]), 0)
	}

	/**
	 *  TA-Quote Customer Signature: Accept a quote
	 */
	@Keyword
	public void bf_QuoteCustomerSignature_AcceptQuote() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'TA-CUSTOMER PLACE1']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Customer Summary']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "My Quotes"]), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'TA-CUSTOMER PLACE1']), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Accepted"]), 0)
	}

}
