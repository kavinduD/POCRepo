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
import io.appium.java_client.MultiTouchAction as MultiTouchAction
import io.appium.java_client.AppiumDriver
import io.appium.java_client.ios.IOSDriver
import com.kms.katalon.core.webui.driver.DriverFactory

import static org.openqa.selenium.By.name

import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import io.appium.java_client.TouchAction
import io.appium.java_client.touch.WaitOptions
import io.appium.java_client.touch.offset.PointOption
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory

import org.openqa.selenium.JavascriptExecutor

public class LIB_MobileDesigner {


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
	 * TA-Add Design: Verify validations for mandatory fields
	 */
	@Keyword
	public void bf_AddDesign_VerifyValidationsforMandatoryFields() {
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'ADD DESIGN']),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Thread.sleep(2000)
		//toast continue
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)

	}

	/**
	 * TA-Add Design: Add new design
	 */
	@Keyword
	public void bf_AddDesign_AddNewDesign(String name,String description) {
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'ADD DESIGN']),0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Name']), name, 3)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_MobileDesigner/tf_Description'),description,0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']),0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_ErrorMessageWithContinue'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Designs"]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : name]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable', [('idf_TextName') : name]),0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonContainsByText',[('idf_LabelName') : description]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
	}

	/**
	 * TA-Add Design: Copy from specific Design/ Revision
	 */
	@Keyword
	public void bf_AddDesign_CopyfromSpecificDesignRevision(String name,String description,String design,String revision) {
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'ADD DESIGN']),0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Name']), name, 3)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_MobileDesigner/tf_Description'),description,0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Design']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : design]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Revision']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : revision]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']),0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_ErrorMessageWithContinue'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Designs"]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : name]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable', [('idf_TextName') : name]),0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonContainsByText',[('idf_LabelName') : description]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
	}

	/**
	 * TA-Revision List: Add a new revision
	 */
	@Keyword
	public void bf_RevisionList_AddNewRevision(String description) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'TA-DESIGN2']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'ADD REVISION']),0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Revision Description (optional)']), description, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'OK']),0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_ErrorMessageWithContinue'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Categories"]), 0)
		Thread.sleep(2000)
		Mobile.pressBack()
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : description]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
	}


	/**
	 * TA-Revision List: Publish a pending revision
	 */
	@Keyword
	public void bf_RevisionList_PublishPendingRevision() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'TA-DESIGN2']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PENDING"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'PUBLISH REVISION']),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']),0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Revisions']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementNotExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'PENDING']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'PUBLISHED']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
	}


	/**
	 * TA-Revision List: Revision Load
	 */
	@Keyword
	public void bf_RevisionList_RevisionLoad() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'TA-DESIGN2']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Revision 2"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']),0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_ErrorMessageWithContinue'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Categories"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
	}

	/**
	 * TA-Theme List: Delete a theme
	 */
	@Keyword
	public void bf_ThemeList_DeleteTheme() {
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Themes']), 0)
		Mobile.tapAndHold(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'TA-THEME1']), 0,0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']),0)
		Thread.sleep(2000)
		Mobile.verifyElementNotExist(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'TA-THEME1']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
	}

	/**
	 * TA-Add Theme: Verify validation for mandatory fields
	 */
	@Keyword
	public void bf_AddTheme_VerifyValidationformandatoryfields() {
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Themes']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'ADD THEME']),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Thread.sleep(2000)
		//toast continue
	}

	/**
	 * TA-Add Theme: Add a new theme-without a source theme
	 */
	@Keyword
	public void bf_AddTheme_AddNewThemeWithoutSourceTheme(String name) {
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Themes']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'ADD THEME']),0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Name']), name, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']),0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_ErrorMessageWithContinue'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Themes"]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : name]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
	}

	/**
	 * TA-Add Theme: Add a new theme- with a source theme
	 */
	@Keyword
	public void bf_AddTheme_AddNewThemeWithSourceTheme(String name,String sourcetheme,String description) {
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Themes']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'ADD THEME']),0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Name']), name, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Source Theme']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : sourcetheme]), 0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_MobileDesigner/tf_Description'),description,0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']),0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_ErrorMessageWithContinue'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Themes"]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : name]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : description]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
	}

	/**
	 * TA-Colors: Set colors to a theme
	 */
	@Keyword
	public void bf_Colors_SetColorstoaTheme(String secondaryColor,String secondGradient) {
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Themes']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'TA-TESTDESIGN']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/btn_Primary'), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/lbl_Color'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_MobileDesigner/lbl_PrimaryColorPreview'), 0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Secondary']), secondaryColor, 3)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_MobileDesigner/lbl_SecondaryColorPreview'), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/btn_HyperlinkColor'), 0)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/lbl_Color'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_MobileDesigner/lbl_HyperlinkColorPreview'), 0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_MobileDesigner/tf_FirstGradient'), secondaryColor, 3)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_MobileDesigner/tf_SecondGradient'), secondGradient, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Colors"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'VIEW IMAGES']),0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Images"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
	}

	/**
	 * TA-Images: Set an image for Small Icon
	 */
	@Keyword
	public void bf_Images_SetanImageforSmallIcon() {
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Themes']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'TA-TESTDESIGN']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'VIEW IMAGES']),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SELECT']),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/img_SmallIcon'),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Images"]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_MobileDesigner/lbl_SmallIconPreview'), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'FINISH']),0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Themes"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
	}

	/**
	 * TA-Images: Set an image for Large Icon
	 */
	@Keyword
	public void bf_Images_SetImageforLargeIcon() {
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Themes']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'TA-TESTDESIGN']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'VIEW IMAGES']),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/btn_CommonByUpperLabelName', [('idf_LabelName') : 'Large Icon']),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/img_SmallIcon'),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Images"]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_MobileDesigner/lbl_LargeIconPreview'), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'FINISH']),0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Themes"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
	}

	/**
	 * TA-Images: Clear images set for Small/ Large Icon
	 */
	@Keyword
	public void bf_Images_ClearImagesSetforSmallLargeIcon() {
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Themes']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'TA-TESTDESIGN']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'VIEW IMAGES']),0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_MobileDesigner/lbl_LargeIconPreview'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_MobileDesigner/lbl_SmallIconPreview'), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'CLEAR']),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/btn_Clear'),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Images"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'FINISH']),0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Themes"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
	}

	/**
	 * TA-Theme Assignment: Select a theme for a pending revision
	 */
	@Keyword
	public void bf_ThemeAssignment_SelectaThemeforPendingRevision(String theme) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'TA-DESIGN1']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PENDING"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Theme']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : theme]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_MobileDesigner/lbl_ThemeName'),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
	}

	/**
	 * TA-Theme Assignment: Save a theme for a pending revision
	 */
	@Keyword
	public void bf_ThemeAssignment_SaveThemeforPendingRevision(String theme) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'TA-DESIGN1']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PENDING"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Theme']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : theme]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_MobileDesigner/lbl_ThemeName'),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE THEME']),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
	}

	/**
	 * TA-Theme Assignment: Preview a pending revision
	 */
	@Keyword
	public void bf_ThemeAssignment_PreviewPendingRevision() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'TA-DESIGN1']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PENDING"]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_MobileDesigner/lbl_FirstGradientTheme'), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Preview']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']),0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Hello TA USER1!"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'About']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonContainsByText',[('idf_LabelName') : "TA-DESIGN1"]), 0)
		Thread.sleep(2000)

		//continue will look after
	}

	/**
	 * TA-Menu List: Verify the list items available on the Menu List screen
	 */
	@Keyword
	public void bf_MenuList_VerifytheListItemsAvailableonMenuListScreen() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'TA-DESIGN1']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PENDING"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Menus"]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Global"]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Home"]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Quote Workflow"]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Debrief Workflow"]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonContainsByText',[('idf_LabelName') : "Debrief"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
	}

	/**
	 * TA-Global Enabling: Enable/Disable global menu items
	 */
	@Keyword
	public void bf_GlobalEnabling_EnableDisableGlobalMenuItems() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'TA-DESIGN1']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PENDING"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Menus"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Global"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_WorkList/PG_PartsUsed/chk_FirstCheckBoxFromList'), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'ADD']),0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Add Global Menu Item"]), 0)
		Thread.sleep(2000)
		Mobile.pressBack()
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Global Menu"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'NEXT']),0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Global Menu Order"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
	}

	/**
	 * TA-Global Enabling: Enable/ Disable global menu items-preview changes
	 */
	@Keyword
	public void bf_GlobalEnabling_EnableDisableGlobalMenuItemsPreviewChanges() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'TA-DESIGN1']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PENDING"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Menus"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Global"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_WorkList/PG_PartsUsed/chk_FirstCheckBoxFromList'), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'ADD']),0)
		Thread.sleep(2000)
		Mobile.pressBack()
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'NEXT']),0)
		Thread.sleep(2000)
		Mobile.pressBack()
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Preview']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']),0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Hello TA USER1!"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "About"]), 0)
		Thread.sleep(2000)
	}

	/**
	 * TA-Add Global: Verify validations for Save
	 */
	@Keyword
	public void bf_AddGlobal_VerifyValidationsforSave() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'TA-DESIGN1']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PENDING"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Menus"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Global"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'ADD']),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Thread.sleep(2000)
		//toast
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
	}

	/**
	 * TA-Add Global: Verify validation for unique Name value
	 */
	@Keyword
	public void bf_AddGlobal_VerifyValidationforUniqueNameValue(String name) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'TA-DESIGN1']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PENDING"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Menus"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Global"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'ADD']),0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Name']),name, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Thread.sleep(2000)
		//toast
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
	}


	/**
	 * TA-Add Global: Add global menu item-Accept save confirmation
	 */
	@Keyword
	public void bf_AddGlobal_AddGlobalMenuItemAcceptSaveConfirmation(String name) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'TA-DESIGN1']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PENDING"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Menus"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Global"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'ADD']),0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Name']),name, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']),0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_ErrorMessageWithContinue'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Global Menu"]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : name]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
	}

	/**
	 * TA-Global Properties: Add/ update global properties
	 */
	@Keyword
	public void bf_GlobalProperties_AddUpdateGlobalProperties(String countScript,String iconName,String label,String tapEvent) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'TA-DESIGN1']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PENDING"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Menus"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Global"]), 0)
		Thread.sleep(2000)
		Mobile.tapAndHold(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "About"]), 0,0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']),0)
		Thread.sleep(2000)
		tapLookUpIcon('Count Script')
		Mobile.setText(findTestObject('Android/PG_Stocks/tf_SearchField', [('idf_LabelName') : 'Search']),countScript , 3)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : countScript]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Icon Name']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : iconName]), 0)
		Thread.sleep(2000)
		tapLookUpIcon('Label')
		Mobile.setText(findTestObject('Android/PG_Stocks/tf_SearchField', [('idf_LabelName') : 'Search']),label , 3)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : label]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Screen']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/lbl_Screen'), 0)
		Thread.sleep(2000)
		tapLookUpIcon('Tap Event')
		Mobile.setText(findTestObject('Android/PG_Stocks/tf_SearchField', [('idf_LabelName') : 'Search']),tapEvent , 3)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : tapEvent]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonContainsByText',[('idf_LabelName') : "Properties"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'FINISH']),0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Global Menu"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
	}

	/**
	 * TA-Global Properties: Preview global properties
	 */
	@Keyword
	public void bf_GlobalProperties_PreviewGlobalProperties() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'TA-DESIGN1']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PENDING"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Preview']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'About']), 0)
		Thread.sleep(2000)
		//later continue


	}

	/**
	 * TA-Global Order: Modify the global order
	 */
	@Keyword
	public void bf_GlobalOrder_ModifytheGlobalOrder() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'TA-DESIGN1']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PENDING"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Menus"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Global"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'NEXT']),0)
		Thread.sleep(2000)
		//reordering


		/*AppiumDriver<?> driver = MobileDriverFactory.getDriver()
		 WebElement l_draggable = driver.findElement(By.xpath("//android.widget.TextView[@text = 'Team']"))
		 WebElement l_droppable = driver.findElement(By.xpath("//android.widget.TextView[@text='Jobs']"))
		 TouchAction action = new TouchAction(driver)
		 action.press(l_draggable).perform()
		 action = new TouchAction(driver);
		 action.press(l_draggable).moveTo(l_droppable).release().perform();*/

		//		int startingX = Mobile.getDeviceWidth() - 5
		//		int endingX = Mobile.getElementLeftPosition(findTestObject('iOS/PG_Common/lbl_FirstElementFromList', [('idf_LabelName') : labelName]), 0)
		//		int y = Mobile.getElementTopPosition(findTestObject('iOS/PG_Common/lbl_FirstElementFromList', [('idf_LabelName') : labelName]), 0)*/
		//

		//
		AppiumDriver driver = MobileDriverFactory.getDriver()
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Map<String, Object> params = new HashMap<>();
		//params.put("duration", 3.0);
		params.put("startX", 50);
		params.put("startY", 989);
		params.put("endX", 50);
		params.put("endY", 485);
		params.put("speed",2500*0.5)
		//js.executeScript("mobile: dragFromToForDuration", params);


		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", params)



		//Mobile.dragAndDrop(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Quotes"]), findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Team"]),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Global Menu Order"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'FINISH']),0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Categories"]), 0)
		Thread.sleep(2000)
		//continue
	}


	/**
	 * TA-Home Enabling: Enable/Disable home items
	 */
	@Keyword
	public void bf_HomeEnabling_EnableDisableHomeItems() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'TA-DESIGN1']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PENDING"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Menus"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Home"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_WorkList/PG_PartsUsed/chk_FirstCheckBoxFromList'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'ADD']),0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Add Home Menu Item"]), 0)
		Thread.sleep(2000)
		Mobile.pressBack()
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Home Menu"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'NEXT']),0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Home Menu Order"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
	}

	/**
	 * TA-Home Enabling: Enable/ Disable home items-verifying validation for the min number of items
	 */
	@Keyword
	public void bf_HomeEnabling_EnableDisableHomeItemsVerifyingValidationforMinNumberofItems() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'TA-DESIGN1']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PENDING"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Menus"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Home"]), 0)
		Thread.sleep(2000)
		//Toast continue
	}

	/**
	 * TA-Add Home: Verify validations for Save
	 */
	@Keyword
	public void bf_AddHome_VerifyValidationsforSave() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'TA-DESIGN1']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PENDING"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Menus"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Home"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'ADD']),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Thread.sleep(2000)
		//Toast
	}


	/**
	 * TA-Add Home: Verify validation for unique Name value
	 */
	@Keyword
	public void bf_AddHome_VerifyValidationforUniqueNameValue() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'TA-DESIGN1']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PENDING"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Menus"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Home"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'ADD']),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Thread.sleep(2000)
		Mobile.takeScreenshot('Screenshots/MobileDesigner_AddHome_VerifyValidationforUniqueNameValue.png', FailureHandling.STOP_ON_FAILURE)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
		//Toast
	}

	/**
	 * TA-Add Home:Add home menu item-Accept save confirmation
	 */
	@Keyword
	public void bf_AddHome_AddHomeMenuItemAcceptSaveConfirmation(String name) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'TA-DESIGN1']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PENDING"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Menus"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Home"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'ADD']),0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Name']), name, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']),0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_ErrorMessageWithContinue'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Home Menu"]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : name]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
	}

	/**
	 * TA-Home Properties: Add/ update home properties
	 */
	@Keyword
	public void bf_HomeProperties_AddUpdateHomeProperties(String countScript,String label,String tapEvent) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'TA-DESIGN1']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PENDING"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Menus"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Home"]), 0)
		Thread.sleep(2000)
		Mobile.tapAndHold(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Document"]), 0,0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']),0)
		Thread.sleep(2000)
		tapLookUpIcon('Count Script')
		Mobile.setText(findTestObject('Android/PG_Stocks/tf_SearchField', [('idf_LabelName') : 'Search']),countScript , 3)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : countScript]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SELECT']),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/img_SmallIcon'),0)
		Thread.sleep(2000)
		tapLookUpIcon('Label')
		Mobile.setText(findTestObject('Android/PG_Stocks/tf_SearchField', [('idf_LabelName') : 'Search']),label , 3)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : label]), 0)
		Thread.sleep(2000)
		tapLookUpIcon('Tap Event')
		Mobile.setText(findTestObject('Android/PG_Stocks/tf_SearchField', [('idf_LabelName') : 'Search']),tapEvent , 3)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : tapEvent]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonContainsByText',[('idf_LabelName') : "Properties"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'FINISH']),0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Home Menu"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
	}

	/**
	 * TA-Home Properties: Preview home properties
	 */
	@Keyword
	public void bf_HomeProperties_PreviewHomeProperties() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'TA-DESIGN1']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PENDING"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Menus"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Home"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Preview']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']),0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "About"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "About"]), 0)
		Thread.sleep(2000)
		Mobile.pressBack()
	}

	/**
	 * TA-Home Order: Modify the home order
	 */
	@Keyword
	public void bf_HomeOrder_ModifytheHomeOrder() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'TA-DESIGN1']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PENDING"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Menus"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Home"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'NEXT']),0)
		Thread.sleep(2000)
		//Mobile.dragAndDrop(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Quotes"]), findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Team"]),0)
		Mobile.dragAndDrop(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Jobs"]), findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Team"]), 0, FailureHandling.STOP_ON_FAILURE)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonContainsByText',[('idf_LabelName') : "Properties"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'FINISH']),0)
		Thread.sleep(2000)

		//reorder
	}

	/**
	 * TA-Home Order: Preview home order
	 */
	@Keyword
	public void bf_HomeOrder_PreviewHomeOrder() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'TA-DESIGN1']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PENDING"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Preview']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']),0)
		Thread.sleep(2000)
		//Take a Screenshot and verify
		Mobile.takeScreenshot('Screenshots/MobileDesigner_HomeOrder_PreviewHomeOrder.png', FailureHandling.STOP_ON_FAILURE)
		Thread.sleep(2000)

	}

	/**
	 * TA-Workflow List: Verify the list items available on the Workflows screen
	 */
	@Keyword
	public void bf_WorkflowList_VerifytheListItemsAvailableonWorkflowsScreen() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'TA-DESIGN1']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PENDING"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Workflows"]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Debrief"]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Quote"]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Schedule"]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_MobileDesigner/lbl_Description'), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
	}

	/**
	 * TA-Workflow List: Delete a custom workflow
	 */
	@Keyword
	public void bf_WorkflowList_DeleteCustomWorkflow() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'TA-DESIGN1']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PENDING"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Workflows"]), 0)
		Thread.sleep(2000)
		Mobile.tapAndHold(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Debrief ANDROID"]), 0,0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']),0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Workflows"]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementNotExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Debrief ANDROID"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
	}

	/**
	 * TA-Add Workflow: Verify validations for Save
	 */
	@Keyword
	public void bf_AddWorkflow_VerifyValidationsforSave() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'TA-DESIGN1']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PENDING"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Workflows"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'ADD WORKFLOW']),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Thread.sleep(2000)
		//toast
	}

	/**
	 * TA-Add Workflow: Verify validations for Save
	 */
	@Keyword
	public void bf_AddWorkflow_AddCustomDebriefWorkflow(String baseWorkflow,String type,String description) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'TA-DESIGN1']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PENDING"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Workflows"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'ADD WORKFLOW']),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Base Workflow']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : baseWorkflow]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Type']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : type]), 0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_MobileDesigner/tf_Description'),description,0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']),0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_ErrorMessageWithContinue'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Workflows"]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : description]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonContainsByText',[('idf_LabelName') : baseWorkflow]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
	}

	/**
	 * TA-Workflow Enabling: Enable/ Disable workflow screens-custom Debrief WF
	 */
	@Keyword
	public void bf_WorkflowEnabling_EnableDisableWorkflowScreensCustomDebriefWF() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'TA-DESIGN1']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PENDING"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Workflows"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Debrief"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_WorkList/PG_PartsUsed/chk_FirstCheckBoxFromList'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Debrief Screens']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'NEXT']),0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Debrief Screen Order']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
	}

	/**
	 * TA-Workflow Order: Modify the workflow order-custom Debrief WF
	 */
	@Keyword
	public void bf_WorkflowOrder_ModifytheWorkflowOrderCustomDebriefWF() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'TA-DESIGN1']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PENDING"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Workflows"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'NEXT']),0)
		Thread.sleep(2000)
		//reorder
	}

	/**
	 * TA-WF Menu Enabling: Enable/ Disable WF menu items-custom Debrief WF
	 */
	@Keyword
	public void bf_WFMenuEnabling_EnableDisableWFMenuItemsCustomDebriefWF() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'TA-DESIGN1']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PENDING"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Menus"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonContainsByText',[('idf_LabelName') : "Debrief"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_WorkList/PG_PartsUsed/chk_FirstCheckBoxFromList'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Debrief Menu']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'NEXT']),0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Debrief Menu Order']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
	}

	/**
	 * TA-TA-WF Menu Order: Modify WF menu order-custom Debrief WF
	 */
	@Keyword
	public void bf_WFMenuOrder_ModifyWFMenuOrderCustomDebriefWF() {
		//reorder continue
	}

	/**
	 * TA-WF Menu Order: Preview the WF menu order-custom Debrief WF
	 */
	@Keyword
	public void bf_WFMenuOrder_PreviewtheWFMenuOrderCustomDebriefWF() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'TA-DESIGN1']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PENDING"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Workflows"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Preview']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']),0)
		Thread.sleep(2000)
		//continue there is a issue ask from gayani akka
	}

	/**
	 * TA-WF Menu Order: Preview the WF menu order-baseline Debrief WF
	 */
	@Keyword
	public void bf_WFMenuOrder_PreviewtheWFMenuOrderBaselineDebriefWF() {
		//Continue
	}

	/**
	 * TA-Workflow Order: Preview the order of WF screens-custom Debrief WF
	 */
	@Keyword
	public void bf_WorkflowOrder_PreviewtheOrderofWFScreensCustomDebriefWF() {
		//Continue
	}

	/**
	 * TA-Screens List: Verify the list items available on the Screens List screen
	 */
	@Keyword
	public void bf_ScreensList_VerifytheListItemsAvailableonScreensListScreen() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'TA-DESIGN1']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PENDING"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Screens"]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'About']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Commitment']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'CalendarException']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
	}

	/**
	 * TA-Screens List: Delete a custom screen
	 */
	@Keyword
	public void bf_ScreensList_DeleteCustomScreen() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'TA-DESIGN1']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PENDING"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Screens"]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : "Document"]),0)
		Thread.sleep(2000)
		Mobile.takeScreenshot('Screenshots/Screens.png', FailureHandling.STOP_ON_FAILURE)
		Mobile.tapAndHold(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : "Document"]),0, 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']),0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementNotExist(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Document']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
	}

	/**
	 * TA-Add Screen: Verify validations for save
	 */
	@Keyword
	public void bf_AddScreen_VerifyValidationsforSave() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'TA-DESIGN1']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PENDING"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Screens"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'ADD SCREEN']),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Thread.sleep(2000)
		//toast
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
	}

	/**
	 * TA-Add Screen: Verify validation for a unique name
	 */
	@Keyword
	public void bf_AddScreen_VerifyValidationforUniqueName(String name) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'TA-DESIGN1']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PENDING"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Screens"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'ADD SCREEN']),0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Name']), name, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Thread.sleep(2000)
		//toast
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
	}

	/**
	 * TA-Add Screen: Verify validation for a unique name
	 */
	@Keyword
	public void bf_AddScreen_AddNewScreenWorkflow(String name,String table,String workflow,String description) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'TA-DESIGN1']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PENDING"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Screens"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'ADD SCREEN']),0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Name']), name, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Table']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : table]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Workflow']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : workflow]), 0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Object Repository/Android/PG_MobileDesigner/tf_Description'), description, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']),0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Object Repository/Android/PG_Common/lbl_ErrorMessageWithContinue'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : name]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
	}

	/**
	 * TA-Add Screen: Basic Data Population-Standard Workflow screen
	 */
	@Keyword
	public void bf_AddScreen_BasicDataPopulationStandardWorkflowScreen(String name,String description) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'TA-DESIGN1']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PENDING"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Screens"]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : name]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : description]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : "Commitment"]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonContainsByText',[('idf_LabelName') : "Properties"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'VIEW FIELDS']),0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonContainsByText',[('idf_LabelName') : "TIME"]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonContainsByText',[('idf_LabelName') : "GLOBAL"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Categories']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Workflows"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Debrief"]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : name]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'NEXT']),0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : name]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Categories']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Workflows"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Debrief"]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : name]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementChecked(findTestObject('Object Repository/Android/PG_MobileDesigner/chk_Document'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'NEXT']),0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : name]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
	}

	/**
	 * TA-Add Screen: Menu Options- Global & Home
	 */
	@Keyword
	public void bf_AddScreen_MenuOptionsGlobalandHome(String name,String menuOption){
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'TA-DESIGN1']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PENDING"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Screens"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'ADD SCREEN']),0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Name']), name, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Menu Option']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : menuOption]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Categories']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Menus"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Global"]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : name]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementChecked(findTestObject('Object Repository/Android/PG_MobileDesigner/chk_ListItem'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'NEXT']),0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : name]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Categories']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Menus"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Home"]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : name]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementChecked(findTestObject('Object Repository/Android/PG_MobileDesigner/chk_ListItem'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'NEXT']),0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : name]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
	}

	/**
	 * TA-Add Screen: Tab Screen Handling-Add tab children
	 */
	@Keyword
	public void bf_AddScreen_TabScreenHandlingAddTabChildren(String name,String screentype,String name1,String name2) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'TA-DESIGN1']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PENDING"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Screens"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'ADD SCREEN']),0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Name']), name, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Screen Type']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : screentype]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'ADD SCREEN']),0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Name']), name1, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Object Repository/Android/PG_MobileDesigner/chk_TabChild'),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Tab Parent']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : name]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'ADD SCREEN']),0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Name']), name2, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Object Repository/Android/PG_MobileDesigner/chk_TabChild'),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Tab Parent']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : name]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']),0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : name]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : name1]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : name2]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
	}

	/**
	 * TA-Add Screen: Tab Screen Handling-Delete tab parent
	 */
	@Keyword
	public void bf_AddScreen_TabScreenHandlingDeleteTabParent(String name,String name1,String name2) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'TA-DESIGN1']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PENDING"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Screens"]), 0)
		Thread.sleep(2000)
		Mobile.tapAndHold(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : name]), 0,0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']),0)
		Thread.sleep(2000)
		Mobile.verifyElementNotExist(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : name]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementNotExist(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : name1]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementNotExist(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : name2]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
	}


	/**
	 * TA-Screen Properties: Disable Delete/Modify
	 */
	@Keyword
	public void bf_ScreenProperties_DisableDeleteorModify() {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'TA-DESIGN1']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PENDING"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Screens"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'DebriefLaborList']), 0)
		Thread.sleep(2000)
		Mobile.uncheckElement(findTestObject('Android/PG_MobileDesigner/chk_CommonByUpperLabelName',[('idf_LabelName') : 'Allow Delete']), 0)
		Thread.sleep(2000)
		Mobile.uncheckElement(findTestObject('Android/PG_MobileDesigner/chk_CommonByUpperLabelName',[('idf_LabelName') : 'Allow Modify']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Preview']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Jobs']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Arrived"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Labor']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "List", ('idf_ButtonNameCapital') : "LIST"]),5)
		Mobile.tapAndHold(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "10"]), 0,0)
		Thread.sleep(2000)
		Mobile.verifyElementNotExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Modify or Delete']), 0)
		Thread.sleep(2000)
	}

	/**
	 * TA-Screen Properties: Modify Force Order-First
	 */
	@Keyword
	public void bf_ScreenProperties_ModifyForceOrderFirst(String forceorder) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'TA-DESIGN1']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PENDING"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Screens"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'DebriefLabor']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Force Order']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : forceorder]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']),0)
		Thread.sleep(2000)
		Mobile.pressBack()
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'DebriefOverview']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Force Order']), 0)
		Thread.sleep(2000)
		Mobile.takeScreenshot('Screenshots/MobileDesigner_DebriefOverview.png', FailureHandling.STOP_ON_FAILURE)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Categories']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Workflows"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Debrief']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementChecked(findTestObject('Android/PG_MobileDesigner/chk_DebriefOverview'), 0)
		Thread.sleep(2000)
		Mobile.takeScreenshot('Screenshots/MobileDesigner_DebriefScreenFirst.png', FailureHandling.STOP_ON_FAILURE)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "DebriefLabor(First screen)"]), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'NEXT']),0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "DebriefLabor(First screen)"]), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
	}

	/**
	 * TA-Screen Properties: Modify Force Order-Last
	 */
	@Keyword
	public void bf_ScreenProperties_ModifyForceOrderLast(String forceorder) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'TA-DESIGN1']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PENDING"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Screens"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'DebriefLabor']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Force Order']), 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : forceorder]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']),0)
		Thread.sleep(2000)
		Mobile.pressBack()
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'DebriefCustomerReview']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName', [('idf_LabelName') : 'Force Order']), 0)
		Thread.sleep(2000)
		Mobile.takeScreenshot('Screenshots/MobileDesigner_DebriefCustomerReview.png', FailureHandling.STOP_ON_FAILURE)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Categories']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Workflows"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Debrief']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementChecked(findTestObject('Android/PG_MobileDesigner/chk_DebriefCustomerReview'), 0)
		Thread.sleep(2000)
		Mobile.takeScreenshot('Screenshots/MobileDesigner_DebriefScreenLast.png', FailureHandling.STOP_ON_FAILURE)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "DebriefLabor(Last screen)"]), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'NEXT']),0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "DebriefLabor(Last screen)"]), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
	}

	/**
	 * TA-Screen Properties: Help/Label/Tip
	 */
	@Keyword
	public void bf_ScreenProperties_HelpLabelTip(String help,String label,String tip) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'TA-DESIGN1']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PENDING"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Screens"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'DebriefLabor']), 0)
		Thread.sleep(2000)
		tapLookUpIcon('Help')
		Mobile.setText(findTestObject('Android/PG_Stocks/tf_SearchField', [('idf_LabelName') : 'Search']),help , 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : help]), 0)
		Thread.sleep(2000)
		tapLookUpIcon('Label')
		Mobile.setText(findTestObject('Android/PG_Stocks/tf_SearchField', [('idf_LabelName') : 'Search']),label , 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : label]), 0)
		tapLookUpIcon('Tip')
		Mobile.setText(findTestObject('Android/PG_Stocks/tf_SearchField', [('idf_LabelName') : 'Search']),tip , 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : tip]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Preview']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Jobs']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Arrived"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'DebriefLabor']), 0)
		//Ask
	}

	/**
	 * TA-WF Menu Order: Modify WF menu order-custom Debrief WF
	 */
	@Keyword
	public void bf_WFMenuOrder_ModifyWFMenuOrderCustomDebriefWF(String designName, String revisionName,String menusLabel,
			String debriefLabel,String previewScreen,String jobInput) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : designName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : revisionName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : menusLabel]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonContainsByText',[('idf_LabelName') : debriefLabel]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Debrief ECO Menu']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'NEXT']),0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Debrief ECO Menu Order']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/lbl_PreviewOption',[('idf_LabelName') : previewScreen]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Jobs']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/lbl_JobItem', [('idf_LabelName') : jobInput]),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'),0)
		Thread.sleep(2000)
		Mobile.pressBack()
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Home']), 0)
		Thread.sleep(2000)
		//Cannot to the reorder process.
	}



	/**
	 * TC-2343 Preview the WF menu order-custom Debrief WF
	 */
	@Keyword
	public void bf_WFMenuOrder_PreviewTheWFMenuOrderCustomDebriefWF(String designName, String revisionName,String menusLabel,
			String debriefLabel,String previewScreen,String jobInput) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : designName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : revisionName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : menusLabel]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonContainsByText',[('idf_LabelName') : debriefLabel]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Debrief ECO Menu']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'NEXT']),0)
		Thread.sleep(2000)
		//Screen shot eka gnna one
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Debrief ECO Menu Order']), 0)
		Mobile.takeScreenshot('Screenshots/WFMenuOrderCustomMenuItemList.png', FailureHandling.STOP_ON_FAILURE)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/lbl_PreviewOption',[('idf_LabelName') : previewScreen]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Jobs']), 0)
		Thread.sleep(2000)
		//		Mobile.tap(findTestObject('Android/PG_MobileDesigner/btn_Jobs'),0)
		//		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/lbl_JobItem', [('idf_LabelName') : jobInput]),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'),0)
		Thread.sleep(2000)
		//Context menu eka thyeddi ss ekak gnna one
		Mobile.takeScreenshot('Screenshots/ContextMenuItems.png', FailureHandling.STOP_ON_FAILURE)
		Thread.sleep(2000)
		Mobile.pressBack()
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Home']), 0)
		Thread.sleep(2000)
	}

	/**
	 *TA-WF Menu Order: Preview the WF menu order-baseline Debrief WF
	 */
	@Keyword
	public void bf_WFmenuorder_PreviewTheWFMenuOrderBaselineDebriefWF(String designName, String revisionName,String menusLabel,
			String debriefLabel,String previewScreen,String jobInput) {

		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : designName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : revisionName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : menusLabel]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonContainsByText',[('idf_LabelName') : debriefLabel]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Debrief ECO Menu']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'NEXT']),0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Debrief ECO Menu Order']), 0)
		Mobile.takeScreenshot('Screenshots/MobielDesigner_WFMenuBaseLineCustomMenuItemList.png', FailureHandling.STOP_ON_FAILURE)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/lbl_PreviewOption',[('idf_LabelName') : previewScreen]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']),0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Jobs']), 0)
		Thread.sleep(2000)
		//		Mobile.tap(findTestObject('Android/PG_MobileDesigner/btn_Jobs'),0)
		//		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/lbl_JobItem', [('idf_LabelName') : jobInput]),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'),0)
		Thread.sleep(2000)
		//Context menu eka thyeddi ss ekak gnna one
		Mobile.takeScreenshot('Screenshots/MobielDesigner_WFMenuBaseLineContextMenuItems.png', FailureHandling.STOP_ON_FAILURE)
		Thread.sleep(2000)
		Mobile.pressBack()
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Home']), 0)
		Thread.sleep(2000)
	}

	/**
	 *TA-Workflow Order: Preview the order of WF screens-custom Debrief WF
	 */
	@Keyword
	public void bf_WorkflowOrder_PreviewTheOrderOfWFScreensCustomDebriefWF(String designName, String revisionName,String menusLabel,
			String debriefLabel,String previewScreen,String jobInput) {

		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : designName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : revisionName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : menusLabel]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonContainsByText',[('idf_LabelName') : debriefLabel]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Debrief ECO Menu']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'NEXT']),0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Debrief ECO Menu Order']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/lbl_PreviewOption',[('idf_LabelName') : previewScreen]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']),0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Jobs']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/lbl_JobItem', [('idf_LabelName') : jobInput]),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'),0)
		Thread.sleep(2000)
		Mobile.pressBack()
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Parts Used']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Home']), 0)
		Thread.sleep(2000)
		//Cannot verify because of the check box issue
	}

	/**
	 *TA-Design List: Verify available designs on FSM Mobile
	 */
	@Keyword
	public void bf_DesignList_VerifyAvailableDesignsonFSMMobile(String verifyMenuName, String closeButton) {
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : verifyMenuName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : closeButton]), 0)
		Thread.sleep(2000)
		//Cannot be done the verification because it is Sync with FSM Client
	}

	/**
	 *TA-Design List: Design Refresh-Decline confirmation
	 */
	@Keyword
	public void bf_DesignList_DesignRefreshDeclineConfirmation(String designName, String revisionName, String themeName,
			String baseWorkFlow, String designerType, String categoriesLabel, String closeButton, String description) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : designName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : revisionName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/lbl_ThemeSpinner'), 0)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/lbl_ThemeValue',[('idf_LabelName') : themeName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Workflows']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Workflows']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'ADD WORKFLOW']),0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Add Workflow']), 0)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/lbl_WorkflowSpinner'), 0)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/lbl_ThemeValue',[('idf_LabelName') : baseWorkFlow]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/lbl_WorkflowType'), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/lbl_ThemeValue',[('idf_LabelName') : designerType]), 0)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/tf_Description'), 0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_MobileDesigner/tf_Description'), description, 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') :'SAVE']),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') :'YES']),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : categoriesLabel]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Menus']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Menus']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Global']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Global Menu']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/btn_CheckBox1'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : categoriesLabel]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Workflows']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Workflows']), 0)
		Thread.sleep(2000)
		Mobile.longPress(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Debrief FIX']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']),0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : closeButton]), 0)
		Thread.sleep(2000)
		//Cannot do because we want two devices and there is a sync process between two devices
	}

	/**
	 *TA- Design List: Design refresh
	 */
	@Keyword
	public void bf_DesignList_DesignRefresh(String designName, String revisionName, String themeName,
			String baseWorkFlow, String designerType, String categoriesLabel, String closeButton, String description) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : designName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : revisionName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/lbl_ThemeSpinner'), 0)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/lbl_ThemeValue',[('idf_LabelName') : themeName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Workflows']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Workflows']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'ADD WORKFLOW']),0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Add Workflow']), 0)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/lbl_WorkflowSpinner'), 0)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/lbl_ThemeValue',[('idf_LabelName') : baseWorkFlow]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/lbl_WorkflowType'), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/lbl_ThemeValue',[('idf_LabelName') : designerType]), 0)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/tf_Description'), 0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_MobileDesigner/tf_Description'), description, 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') :'SAVE']),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') :'YES']),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : categoriesLabel]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Menus']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Menus']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Global']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Global Menu']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/btn_CheckBox1'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : categoriesLabel]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Workflows']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Workflows']), 0)
		Thread.sleep(2000)
		Mobile.longPress(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Debrief FIX']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']),0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : closeButton]), 0)
		Thread.sleep(2000)
		//Cannot do because we want two devices and there is a sync process between two devices
	}

	/**
	 *TA-Tab Order: Modify tab order
	 */
	@Keyword
	public void bf_TabOrder_ModifyTabOrder(String designName,String revisionName, String customerTabScreen, String previewScreen,String cusInput) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : designName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : revisionName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : customerTabScreen]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'CustomerTabs Properties']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'MODIFY']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'CustomerTabs Screen Tab Order']), 0)
		//tap and hold and change steps have to write
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/lbl_PreviewOption',[('idf_LabelName') : previewScreen]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']),0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Customers"]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Customers']), 0)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/lbl_JobItem', [('idf_LabelName') : cusInput]),0)
		Thread.sleep(2000)
		//Cannot complete the test case because the cant capture the tap and hold process
	}



	/**
	 *TA-Item List: Verify the items available on the Item List screen
	 */
	@Keyword
	public void bf_ItemList_VerifyTheItemsAvailableOnTheItemListScreen(String designName,String revisionName, String customerTabScreen, String closeButton) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : designName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : revisionName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : customerTabScreen]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'NewDebriefTestScreen Properties']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'VIEW ITEMS']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'NewDebriefTestScreen Items']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/lbl_PreviewOption',[('idf_LabelName') : closeButton]), 0)
		Thread.sleep(2000)
		//reorder continue
	}

	/**
	 *TA-Item List: Delete a screen item
	 */
	@Keyword
	public void bf_ItemList_DeleteAScreenItem(String designName,String revisionName, String customerTabScreen, String closeButton) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : designName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : revisionName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : customerTabScreen]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'NewDebriefTestScreen Properties']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'VIEW ITEMS']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'NewDebriefTestScreen Items']), 0)
		Mobile.longPress(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'BUTTON_ADD']),0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']),0)
		Mobile.verifyElementNotExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'BUTTON_ADD']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/lbl_PreviewOption',[('idf_LabelName') : closeButton]), 0)
		Thread.sleep(2000)
	}

	/**
	 *TA-Add Item: Verify validations for Save
	 */
	@Keyword
	public void bf_AddItem_VerifyValidationsForSave(String designName,String revisionName, String customerTabScreen, String closeButton) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : designName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : revisionName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : customerTabScreen]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'NewDebriefTestScreen Properties']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'VIEW ITEMS']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'NewDebriefTestScreen Items']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'ADD SCREEN ITEM']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Add Screen Item']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Mobile.takeScreenshot('Screenshots/MobileDesigner_AddItemScreenItemError.png', FailureHandling.STOP_ON_FAILURE)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/lbl_PreviewOption',[('idf_LabelName') : closeButton]), 0)
		Thread.sleep(2000)
		//reorder continue
	}

	/**
	 *TA-Add Item: Add a new screen item-Add button
	 */
	@Keyword
	public void bf_AddItem_AddANewScreenItemAddButton(String designName,String revisionName, String customerTabScreen, String closeButton
			,String itemName) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : designName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : revisionName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : customerTabScreen]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'NewDebriefTestScreen Properties']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'VIEW ITEMS']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'ADD SCREEN ITEM']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Add Screen Item']), 0)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/lbl_ItemSpinner'), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/lbl_items', [('idf_LabelName') : itemName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']),0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_ErrorMessageWithContinue'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'BUTTON_ADD']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/lbl_PreviewOption',[('idf_LabelName') : closeButton]), 0)
		Thread.sleep(2000)
	}

	/**
	 *TA-Add Item: Add a new screen item-Next button (With Event Type & Event)
	 */

	@Keyword
	public void bf_AddItem_AddANewScreenItemNextButtonWithEventTypeAndEvent(String designName,String revisionName, String customerTabScreen, String closeButton
			,String itemName, String eventName, String event, String description, String previewScreen, String jobInput) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : designName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : revisionName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : customerTabScreen]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'NewDebriefTestScreen Properties']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'VIEW ITEMS']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'ADD SCREEN ITEM']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Add Screen Item']), 0)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/lbl_ItemSpinner'), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/lbl_items', [('idf_LabelName') : itemName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/lbl_EventSpinner'), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/lbl_items', [('idf_LabelName') : eventName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/ele_EditText'), 0)
		Mobile.setText(findTestObject('Android/PG_MobileDesigner/ele_AutoCompleteTextView'), event, 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/lbl_Event',[('idf_LabelName') : event]), 0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_MobileDesigner/tf_Description'), description, 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']),0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_ErrorMessageWithContinue'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'BUTTON_NEXT']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/lbl_PreviewOption',[('idf_LabelName') : previewScreen]), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']),0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Jobs']), 0)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/lbl_JobItem', [('idf_LabelName') : jobInput]),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'NewDebriefTestScreen']), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'NewDebriefTestScreen']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'NewDebriefTestScreen']), 0)
		Mobile.takeScreenshot('Screenshots/MobileDesigner_AddItem_AddANewScreenItemNextButtonWithEventTypeAndEvent.png', FailureHandling.STOP_ON_FAILURE)
		Thread.sleep(2000)
		//Deleting the newly added item for testing purposes
		//		Mobile.longPress(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'BUTTON_NEXT']),0)
		//		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']),0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Home']), 0)
		Thread.sleep(2000)
	}



	/**
	 *TA-Add Screen: Verify validations for save
	 */
	@Keyword
	public void bf_AddScreen_VerifyValidationsForSave(String designName, String revisionName ) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : designName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : revisionName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'ADD SCREEN']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Mobile.takeScreenshot('Screenshots/MobileDesigner_AddScreenVerifyImage.png', FailureHandling.STOP_ON_FAILURE)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
		Thread.sleep(2000)
	}

	/**
	 *TA-Add Screen: Verify validation for a unique name
	 */
	@Keyword
	public void bf_AddScreen_VerifyValidationForAUniqueName(String designName, String revisionName, String addScreenName ) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : designName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : revisionName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'ADD SCREEN']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/tf_AddScreenName'), 0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_MobileDesigner/tf_AddScreenName'), addScreenName, 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Mobile.takeScreenshot('Screenshots/MobileDesigner_AddScreenVerifyUniqueName.png', FailureHandling.STOP_ON_FAILURE)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
		Thread.sleep(2000)
		//reorder continue
	}

	/**
	 *TA-Add Screen: Add a new screen-Workflow
	 */
	@Keyword
	public void bf_AddScreen_AddANewScreenWorkflow(String designName, String revisionName, String addScreenName, String tableName, String workFlowName ) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : designName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : revisionName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'ADD SCREEN']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/tf_AddScreenName'), 0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_MobileDesigner/tf_AddScreenName'), addScreenName, 0)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/ele_TableSpinner'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : tableName]), 0)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/ele_WorkFlowName'), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/ele_TableName',[('idf_LabelName') : workFlowName]), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']),0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_ErrorMessageWithContinue'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'NewDebriefTestScreen2']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
		Thread.sleep(2000)
	}

	/**
	 *TA-Add Screen: Basic Data Population-Standard Workflow screen
	 */
	@Keyword
	public void bf_AddScreen_BasicDataPopulationStandardWorkflowScreen(String designName, String revisionName, String addScreenName) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : designName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : revisionName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : addScreenName]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'NewDebriefTestScreen Properties']), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'TASK']), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Standard']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'VIEW FIELDS']),0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'NewDebriefTestScreen Fields']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'TASK.METRIX_ROW_ID']), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Field Properties']), 0)
		Mobile.pressBack()
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'TASK.TASK_ID']), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Field Properties']), 0)
		Mobile.pressBack()
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Categories']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Workflows']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Workflows']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Debrief']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Debrief Screens']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : addScreenName]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'NewDebriefTestScreen']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'NEXT']),0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Debrief Screen Order']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
		Thread.sleep(2000)
	}

	/**
	 *TA-Add Screen: Menu Options- Global & Home
	 */
	@Keyword
	public void bf_AddScreen_MenuOptionsGlobalandHome(String designName, String revisionName, String addScreenName, String menuType) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : designName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : revisionName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'ADD SCREEN']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/tf_AddScreenName'), 0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_MobileDesigner/tf_AddScreenName'), addScreenName, 0)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/ele_MenuSpinner'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : menuType]), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']),0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_ErrorMessageWithContinue'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'NewDebriefTestScreen3']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Categories']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Menus']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Menus']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Global']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Global Menu']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : addScreenName]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : addScreenName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Categories']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Menus']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Menus']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Home']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Home Menu']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : addScreenName]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : addScreenName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'NEXT']),0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Home Menu Order']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : addScreenName]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : addScreenName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
		Thread.sleep(2000)
	}

	/**
	 *TA-Add Screen: Tab Screen Handling-Add tab children
	 */
	@Keyword
	public void bf_AddScreen_TabScreenHandlingAddTabChildren(String designName, String revisionName, String addScreenName, String screenType, String addScreenName2) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : designName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : revisionName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'ADD SCREEN']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/tf_AddScreenName'), 0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_MobileDesigner/tf_AddScreenName'), addScreenName, 0)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/ele_ScreenType'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : screenType]), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']),0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'NewDebriefTestScreen4']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'ADD SCREEN']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/tf_AddScreenName'), 0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_MobileDesigner/tf_AddScreenName'), addScreenName2, 0)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/btn_CheckBoxTabChild'), 0)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/ele_TabParent'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : addScreenName]), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : addScreenName]), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']),0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'NewDebriefTestScreen5']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
		Thread.sleep(2000)
	}

	/**
	 *TA-Add Screen: Tab Screen Handling-Delete tab parent
	 */
	@Keyword
	public void bf_AddScreen_TabScreenHandlingDeleteTabParent(String designName, String revisionName, String addScreenName,String addScreenName2) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : designName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : revisionName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.longPress(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : addScreenName]), 0)
		Mobile.verifyElementNotExist(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : addScreenName]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementNotExist(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : addScreenName2]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']),0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
		Thread.sleep(2000)
	}

	/**
	 *TA-Item Properties: Modify item properties- Next button Event
	 */
	@Keyword
	public void bf_ItemProperties_ModifyItemPropertiesNextButtonEvent(String designName,String revisionName, String customerTabScreen
			,String event, String previewScreen, String jobInput) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : designName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : revisionName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : customerTabScreen]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'NewDebriefTestScreen Properties']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'VIEW ITEMS']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'BUTTON_NEXT']),0)
		Thread.sleep(2000)
		tapLookUpIcon('Event')
		//Mobile.tap(findTestObject('Android/PG_MobileDesigner/tf_EventEdit'), 0)
		//Mobile.tap(findTestObject('Android/PG_MobileDesigner/tf_EventEdit'), 0)
		Thread.sleep(2000)
		//Mobile.tap(findTestObject('Android/PG_MobileDesigner/ele_AutoCompleteTextView'), 0)
		Mobile.setText(findTestObject('Android/PG_MobileDesigner/ele_AutoCompleteTextView'), event, 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/lbl_Event',[('idf_LabelName') : event]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'FINISH']),0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/lbl_PreviewOption',[('idf_LabelName') : previewScreen]), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']),0)
		//		Mobile.tap(findTestObject('Android/PG_MobileDesigner/btn_Jobs'),0)
		//		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Jobs']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/lbl_JobItem', [('idf_LabelName') : jobInput]),0)
		Thread.sleep(2000)
		//		Mobile.tap(findTestObject('Android/PG_MobileDesigner/lbl_JobItem', [('idf_LabelName') : jobInput]),0)
		//		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'NewDebriefTestScreen']), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'NewDebriefTestScreen']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		//lABEL1
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'TA-Screen Label1']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Home']), 0)
		Thread.sleep(2000)
	}

	/**
	 *TA-Item Properties: Modify item properties-Active flag
	 */
	@Keyword
	public void bf_ItemProperties_ModifyItemPropertiesActiveFlag(String designName,String revisionName, String customerTabScreen
			,String event, String previewScreen, String jobInput) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : designName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : revisionName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : customerTabScreen]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'NewDebriefTestScreen Properties']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'VIEW ITEMS']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'BUTTON_NEXT']),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/chk_ActiveFlag'), 0)
		tapLookUpIcon('Event')
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_MobileDesigner/ele_AutoCompleteTextView'), event, 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/lbl_Event',[('idf_LabelName') : event]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Validation']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'FINISH']),0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/lbl_PreviewOption',[('idf_LabelName') : previewScreen]), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']),0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Jobs']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/lbl_JobItem', [('idf_LabelName') : jobInput]),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'NewDebriefTestScreen']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'TA-Screen Label1']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Home']), 0)
		Thread.sleep(2000)
	}

	/**
	 *TA-Field List: Verify the items available on the Field List screen 
	 */
	@Keyword
	public void bf_FieldList_VerifyTheItemsAvailableOnTheFieldListScreen(String designName,String revisionName, String customerTabScreen) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : designName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : revisionName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : customerTabScreen]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'DebriefLabor Properties']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'VIEW FIELDS']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'DebriefLabor Fields']), 0)
		Mobile.takeScreenshot('Screenshots/MobileDesigner_FieldListVerifyItems.png', FailureHandling.STOP_ON_FAILURE)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
		Thread.sleep(2000)
	}

	/**
	 *TA-Field List: Delete a field on the Field List screen
	 */
	@Keyword
	public void bf_FieldList_DeleteAFieldOnTheFieldListScreen(String designName,String revisionName, String customerTabScreen) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : designName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : revisionName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : customerTabScreen]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'DebriefLabor Properties']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'VIEW FIELDS']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'DebriefLabor Fields']), 0)
		Mobile.longPress(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'NON_PART_USAGE.ACCESS_GROUP']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']),0)
		Mobile.verifyElementNotExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'NON_PART_USAGE.ACCESS_GROUP']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
		Thread.sleep(2000)
	}

	/**
	 *TA-Add Field: Verify validations for the Save on the Add Field Screen-Custom field
	 */
	@Keyword
	public void bf_AddField_VerifyValidationsForTheSaveOnTheAddFieldScreenCustomField(String designName,String revisionName, String customerTabScreen) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : designName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : revisionName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : customerTabScreen]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'DebriefLabor Properties']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'VIEW FIELDS']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'DebriefLabor Fields']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'ADD FIELD']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/chk_AddFieldCustomCheckBox'), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Mobile.takeScreenshot('Screenshots/MobileDesigner_AddFieldVerifyValidationForSaveCustomField.png', FailureHandling.STOP_ON_FAILURE)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
		Thread.sleep(2000)
	}

	/**
	 *TA-Add Field: Verify validations for the Save on the Add Field screen-Field for a table column
	 */
	@Keyword
	public void bf_AddField_VerifyValidationsForTheSaveOnTheAddFieldScreenFieldForATableColumn(String designName,String revisionName, String customerTabScreen) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : designName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : revisionName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : customerTabScreen]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'DebriefLabor Properties']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'VIEW FIELDS']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'DebriefLabor Fields']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'ADD FIELD']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementNotChecked(findTestObject('Android/PG_MobileDesigner/chk_AddFieldCustomCheckBox'), 0)
		//Mobile.tap(findTestObject('Android/PG_MobileDesigner/chk_AddFieldCustomCheckBox'), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Mobile.takeScreenshot('Screenshots/MobileDesigner_AddFieldVerifyValidationForSaveScreenField.png', FailureHandling.STOP_ON_FAILURE)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
		Thread.sleep(2000)
	}

	/**
	 *TA-Add Field: Add a custom field
	 */
	@Keyword
	public void bf_AddField_AddACustomField(String designName,String revisionName, String customerTabScreen, String columnName, String description) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : designName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : revisionName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : customerTabScreen]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'DebriefLabor Properties']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'VIEW FIELDS']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'DebriefLabor Fields']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'ADD FIELD']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/chk_AddFieldCustomCheckBox'), 0)
		Thread.sleep(2000)
		//Button uncheck
		Mobile.verifyElementNotChecked(findTestObject('Android/PG_MobileDesigner/chk_IsBuuton'), 0)
		//Name
		Mobile.setText(findTestObject('Android/PG_MobileDesigner/tf_CustomColumnName'), columnName, 0)
		//Description
		Mobile.setText(findTestObject('Android/PG_MobileDesigner/tf_Description'), description, 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']),0)
		//Waiting
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_ErrorMessageWithContinue'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'CUSTOM.TA_CUSTOM_COLUMN_NAME']), 0)
		Mobile.takeScreenshot('Screenshots/MobileDesigner_AddACustomField.png', FailureHandling.STOP_ON_FAILURE)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
		Thread.sleep(2000)
	}



	/**
	 *TA-Add Field: Add a field for a table column
	 */
	@Keyword
	public void bf_AddField_AddaFieldForaTableColumn(String designName,String revisionName, String customerTabScreen,String description) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : designName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : revisionName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : customerTabScreen]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'DebriefLabor Properties']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'VIEW FIELDS']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'DebriefLabor Fields']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'ADD FIELD']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementNotChecked(findTestObject('Android/PG_MobileDesigner/chk_AddFieldCustomCheckBox'), 0)
		Thread.sleep(2000)
		//Select table
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/ele_TableDropDown'), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/ele_TableName',[('idf_LabelName') : 'NON_PART_USAGE']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/chk_Field1'), 0)
		Thread.sleep(2000)
		//		Mobile.tap(findTestObject('Android/PG_MobileDesigner/chk_Field3',[('idf_LabelName') : 'APPROVAL_STATUS']), 0)
		//		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/chk_Field2'), 0)
		Thread.sleep(2000)
		Mobile.tapAndHold(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'ACCESS_GROUP']),4, 0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Description (ACCESS_GROUP)']), description, 0)
		Thread.sleep(2000)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'OK']),0)
		Thread.sleep(2000)
		Mobile.tapAndHold(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'APPROVAL_STATUS']),4, 0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Description (APPROVAL_STATUS)']), description, 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'OK']),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']),0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_ErrorMessageWithContinue'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'NON_PART_USAGE.ACCESS_GROUP']), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'NON_PART_USAGE.APPROVAL_STATUS']), 0)
		Mobile.takeScreenshot('Screenshots/MobileDesigner_AddAFieldForaTableColumn.png', FailureHandling.STOP_ON_FAILURE)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
		Thread.sleep(2000)
	}

	/**
	 *TA-Add Field: Add a custom button
	 */
	@Keyword
	public void bf_AddField_AddaCustomButton(String designName,String revisionName, String customerTabScreen,String columnName,String description) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : designName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : revisionName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : customerTabScreen]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'DebriefLabor Properties']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'VIEW FIELDS']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'DebriefLabor Fields']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'ADD FIELD']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/chk_AddFieldCustomCheckBox'), 0)
		Thread.sleep(2000)
		//Button uncheck
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/chk_IsBuuton'), 0)
		//Name
		Mobile.setText(findTestObject('Android/PG_MobileDesigner/tf_CustomColumnName'), columnName, 0)
		//Description
		Mobile.setText(findTestObject('Android/PG_MobileDesigner/tf_Description'), description, 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']),0)
		//Waiting
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_ErrorMessageWithContinue'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'CUSTOM.TA_CUSTOM_COLUMN_NAME']), 0)
		Mobile.takeScreenshot('Screenshots/MobileDesigner_AddaCustomButton.png', FailureHandling.STOP_ON_FAILURE)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
		Thread.sleep(2000)
	}

	/**
	 *TA-Field Order: Modify field order-standard screen
	 */
	@Keyword
	public void bf_FieldOrder_ModifyFieldOrderStandardScreen(String designName,String revisionName, String customerTabScreen) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : designName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : revisionName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : customerTabScreen]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'DebriefExpense Properties']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'VIEW FIELDS']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'DebriefExpense Fields']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'FIELD ORDER']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'DebriefExpense Field Order']), 0)
		//Reorder -  tap and hold
		Mobile.takeScreenshot('Screenshots/MobileDesigner_FieldOrder_ModifyFieldOrderStandardScreenBefore.png', FailureHandling.STOP_ON_FAILURE)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'FINISH']),0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'DebriefExpense Fields']), 0)
		Mobile.takeScreenshot('Screenshots/MobileDesigner_FieldOrder_ModifyFieldOrderStandardScreenAfter.png', FailureHandling.STOP_ON_FAILURE)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
		Thread.sleep(2000)
		//Cannot to tap and hold step -  reorder continue
	}

	/**
	 *TA-Field Order: Preview field order modifications-Standard screen
	 */
	@Keyword
	public void bf_FieldOrder_PreviewFieldOrderModificationsStandardScreen(String designName,String revisionName, String customerTabScreen, String jobInput) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : designName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : revisionName]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Categories']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Preview']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Jobs']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/lbl_JobItem', [('idf_LabelName') : jobInput]),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/lbl_JobItem', [('idf_LabelName') : 'Expenses']),0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Expenses']), 0)
		Mobile.takeScreenshot('Screenshots/MobileDesigner_FieldOrder_PreviewFieldOrderModificationsStandardScreen.png', FailureHandling.STOP_ON_FAILURE)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Home']), 0)
		Thread.sleep(2000)
		//Cannot do the test script correctly -  reorder issue
	}


	/**
	 *TA-Field Order: Modify field order-list screen
	 */
	@Keyword
	public void bf_FieldOrder_ModifyFieldOrderListScreen(String designName,String revisionName, String customerTabScreen, String jobInput) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : designName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : revisionName]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Categories']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : customerTabScreen]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'JobList Properties']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'VIEW FIELDS']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'JobList Fields']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'FIELD ORDER']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'JobList Field Order']), 0)
		Mobile.takeScreenshot('Screenshots/MobileDesigner_FieldOrder_ModifyFieldOrderListScreenBefore.png', FailureHandling.STOP_ON_FAILURE)
		Thread.sleep(2000)
		//Reorder -  tap and hold
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'FINISH']),0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'JobList Fields']), 0)
		Mobile.takeScreenshot('Screenshots/MobileDesigner_FieldOrder_ModifyFieldOrderListScreenAfter.png', FailureHandling.STOP_ON_FAILURE)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Preview']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Jobs']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/lbl_JobItem', [('idf_LabelName') : jobInput]),0)
		Thread.sleep(2000)
		Mobile.takeScreenshot('Screenshots/MobileDesigner_FieldOrder_PreviewFieldOrderModifyFieldOrderListScreen.png', FailureHandling.STOP_ON_FAILURE)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Home']), 0)
		Thread.sleep(2000)
		//Cannot to tap and hold step -  reorder continue
	}

	/**
	 *TA-Field Prop (STD): Control Type-Checkbox tc-2516
	 */
	@Keyword
	public void bf_FieldPropSTD_ControlTypeCheckbox(String designName,String revisionName, String customerTabScreen, String controlType, String fieldType) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : designName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : revisionName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : customerTabScreen]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'DebriefLabor Properties']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'VIEW FIELDS']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'DebriefLabor Fields']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'ADD FIELD']), 0)
		Thread.sleep(2000)

		//Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Add Field']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName',[('idf_LabelName') : 'Table']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByTextDropDownItem',[('idf_LabelName') : 'NON_PART_USAGE']), 0)
		Thread.sleep(2000)

		//		//Scroll to text try 1
		//		Mobile.scrollToText(fieldType)

		//Scroll to text try 2
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : fieldType]), 0)
		Thread.sleep(2000)


		Mobile.tap(findTestObject('Android/PG_MobileDesigner/chk_Field3'), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']),0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_ErrorMessageWithContinue'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'DebriefLabor Fields']), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'NON_PART_USAGE.USER_DEF1']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'NON_PART_USAGE.USER_DEF1']), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Field Properties']), 0)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/ele_ControlTypeSpinner'), 0)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/ele_TableName',[('idf_LabelName') : controlType]), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Field Properties']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'FINISH']),0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'DebriefLabor Fields']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
		Thread.sleep(2000)
	}

	//353-368 have to develop
	/**
	 *TA-Field Prop (STD): Value Changed-List field
	 */
	@Keyword
	public void bf_FieldPropSTD_ValueChangedListField(String designName,String revisionName, String customerTabScreen, String event, String jobInput) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : designName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : revisionName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : customerTabScreen]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'VIEW FIELDS']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'NON_PART_USAGE.LINE_CODE']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Value Changed']), 0)
		Thread.sleep(2000)
		tapLookUpIcon('Value Changed')
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_MobileDesigner/ele_AutoCompleteTextView'), event, 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : event]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'FINISH']),0)
		Mobile.takeScreenshot('Screenshots/MobileDesigner_FieldPropSTD_ValueChangedListField.png', FailureHandling.STOP_ON_FAILURE)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Preview']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']),0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Jobs']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/lbl_JobItem', [('idf_LabelName') : jobInput]),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Labor']), 0)
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName',[('idf_LabelName') : 'Line Code']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByTextDropDownItem',[('idf_LabelName') : 'Meeting']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
		Thread.sleep(2000)
		//Didnt success the test because after the preview the labor part was missed in the context menu
	}

	//370 have to do

	/**371
	 *TA-Field Prop (Button): Control Event-Modify and preview changes
	 */
	@Keyword
	public void bf_FieldPropButton_ControlEventModifyAndPreviewChanges(String designName,String revisionName, String customerTabScreen, String columnName, String description, String event) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : designName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : revisionName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : customerTabScreen]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'VIEW FIELDS']), 0)
		Thread.sleep(2000)

		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'ADD FIELD']), 0)
		Thread.sleep(2000)

		Mobile.tap(findTestObject('Android/PG_MobileDesigner/chk_AddFieldCustomCheckBox'), 0)
		Thread.sleep(2000)
		//Button uncheck
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/chk_IsBuuton'), 0)
		//Name
		Mobile.setText(findTestObject('Android/PG_MobileDesigner/tf_CustomColumnName'), columnName, 0)
		//Description
		Mobile.setText(findTestObject('Android/PG_MobileDesigner/tf_Description'), description, 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']),0)
		Thread.sleep(2000)
		//Waiting
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_ErrorMessageWithContinue'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'CUSTOM.TA_CUSTOM_COLUMN_NAME']), 0)
		Mobile.takeScreenshot('Screenshots/MobileDesigner_FieldPropButton_ControlEventModifyAndPreviewChanges.png', FailureHandling.STOP_ON_FAILURE)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'CUSTOM.TA_CUSTOM_COLUMN_NAME']), 0)
		tapLookUpIcon('Control Event')
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_MobileDesigner/ele_AutoCompleteTextView'), event, 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : event]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'FINISH']),0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Preview']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']),0)
		//		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		//		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Jobs']), 0)
		//		Thread.sleep(2000)
		//		Mobile.tap(findTestObject('Android/PG_MobileDesigner/lbl_JobItem', [('idf_LabelName') : jobInput]),0)
		//		Thread.sleep(2000)
		//		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
		//		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Labor']), 0)
	}


	/**372
	 *TA-Field Prop (Button): Label-Modify and preview changes
	 */
	@Keyword
	public void bf_FieldPropButton_LabelModifyAndPreviewChanges(String designName,String revisionName, String customerTabScreen, String columnName, String description, String event, String jobInput) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : designName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : revisionName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : customerTabScreen]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'VIEW FIELDS']), 0)
		Thread.sleep(2000)

		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'ADD FIELD']), 0)
		Thread.sleep(2000)

		Mobile.tap(findTestObject('Android/PG_MobileDesigner/chk_AddFieldCustomCheckBox'), 0)
		Thread.sleep(2000)
		//Button uncheck
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/chk_IsBuuton'), 0)
		//Name
		Mobile.setText(findTestObject('Android/PG_MobileDesigner/tf_CustomColumnName'), columnName, 0)
		//Description
		Mobile.setText(findTestObject('Android/PG_MobileDesigner/tf_Description'), description, 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']),0)
		Thread.sleep(2000)
		//Waiting
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_ErrorMessageWithContinue'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'CUSTOM.TA_CUSTOM_COLUMN_NAME']), 0)
		Mobile.takeScreenshot('Screenshots/MobileDesigner_FieldPropButton_ControlEventModifyAndPreviewChanges.png', FailureHandling.STOP_ON_FAILURE)
		Thread.sleep(2000)
		//name eka change wenna one
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'CUSTOM.TA_CUSTOM_COLUMN_NAME']), 0)
		tapLookUpIcon('Lookup')
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_MobileDesigner/ele_AutoCompleteTextView'), event, 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : event]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'FINISH']),0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Preview']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']),0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Jobs']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/lbl_JobItem', [('idf_LabelName') : jobInput]),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Overview']), 0)
		//custom button eke hoygnna one label eka hoygnna one
	}

	/**373
	 *TA-Field Prop (Button): Visible-Modify and preview changes
	 */
	@Keyword
	public void bf_FieldPropButton_VisibleModifyAndPreviewChanges(String designName,String revisionName, String customerTabScreen, String columnName, String description, String event, String jobInput) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : designName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : revisionName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : customerTabScreen]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'VIEW FIELDS']), 0)
		Thread.sleep(2000)

		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'ADD FIELD']), 0)
		Thread.sleep(2000)

		Mobile.tap(findTestObject('Android/PG_MobileDesigner/chk_AddFieldCustomCheckBox'), 0)
		Thread.sleep(2000)
		//Button uncheck
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/chk_IsBuuton'), 0)
		//Name
		Mobile.setText(findTestObject('Android/PG_MobileDesigner/tf_CustomColumnName'), columnName, 0)
		//Description
		Mobile.setText(findTestObject('Android/PG_MobileDesigner/tf_Description'), description, 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']),0)
		Thread.sleep(2000)
		//Waiting
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_ErrorMessageWithContinue'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'CUSTOM.TA_CUSTOM_COLUMN_NAME_2']), 0)
		Mobile.takeScreenshot('Screenshots/MobileDesigner_FieldPropButton_ControlEventModifyAndPreviewChanges.png', FailureHandling.STOP_ON_FAILURE)
		Thread.sleep(2000)
		//name eka change wenna one
		//checkbox seen eka
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'CUSTOM.TA_CUSTOM_COLUMN_NAME']), 0)
		//		tapLookUpIcon('Lookup')
		//		Thread.sleep(2000)
		//		Mobile.setText(findTestObject('Android/PG_MobileDesigner/ele_AutoCompleteTextView'), event, 0)
		//		Thread.sleep(2000)
		//		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : event]), 0)
		//		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']),0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'FINISH']),0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Preview']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']),0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Jobs']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_MobileDesigner/lbl_JobItem', [('idf_LabelName') : jobInput]),0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Overview']), 0)
		//have to find the custom button and the label. After continue the session
	}

	/**374
	 *TA-Field Prop (List): Display Style-Modify and preview changes
	 */
	@Keyword
	public void bf_FieldPropList_DisplayStyleModifyAndPreviewChanges(String designName,String revisionName, String customerTabScreen) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : designName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : revisionName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : customerTabScreen]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'VIEW FIELDS']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'TASK.TASK_STATUS']), 0)

		// continue 
	}
	
	//Have to develop tc_375

	/**376
	 *TA-Add Lookup: Verify validations for Save on Add Field Lookup screen
	 */
	@Keyword
	public void bf_AddLookup_VerifyValidationsForSaveOnAddFieldLookupScreen(String designName,String revisionName, String customerTabScreen) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : designName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : revisionName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : customerTabScreen]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'VIEW FIELDS']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'ADD FIELD']), 0)
		Thread.sleep(2000)
	//Continue with user def
	}

	/**377
	 *TA-Add Lookup: Add a field lookup
	 */
	@Keyword
	public void bf_AddLookup_AddaFieldLookup(String designName,String revisionName, String customerTabScreen) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : designName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : revisionName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : customerTabScreen]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'VIEW FIELDS']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'ADD FIELD']), 0)
		Thread.sleep(2000)
		//Continue with user def
	}


	/**378
	 *TA-Add Lookup: Delete a field lookup
	 */
	@Keyword
	public void bf_AddLookup_DeleteaFieldLookup(String designName,String revisionName, String customerTabScreen) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : designName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : revisionName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : customerTabScreen]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'VIEW FIELDS']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'ADD FIELD']), 0)
		Thread.sleep(2000)
		//Continue with user def
	}


	/**379
	 *TA-Lookup Tables List: Verify list items available on the Lookup Tables screen
	 */
	@Keyword
	public void bf_LookupTablesList_VerifyListItemsAvailableOnTheLookupTableScreen(String designName,String revisionName, String customerTabScreen) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : designName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : revisionName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : customerTabScreen]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'VIEW FIELDS']), 0)
		Thread.sleep(2000)
		//User def lookup eka tap krnda one
		//Screenshot for grey dot
		Mobile.takeScreenshot('Screenshots/MobileDesigner_LookupTablesList_VerifyTableNameGreyDot.png', FailureHandling.STOP_ON_FAILURE)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'MODIFY']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'TABLES']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "REQUEST_CONTACT"]), 0)
		Thread.sleep(2000)
		//Screenshot for table name in bold
		Mobile.takeScreenshot('Screenshots/MobileDesigner_LookupTablesList_VerifyTableName.png', FailureHandling.STOP_ON_FAILURE)
		Thread.sleep(2000)
		//Screenshot for blue dot
		Mobile.takeScreenshot('Screenshots/MobileDesigner_LookupTablesList_VerifyTableNameBlueDot.png', FailureHandling.STOP_ON_FAILURE)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
		Thread.sleep(2000)
	}


	/**380
	 *TA-Lookup Tables List:  Delete a lookup table
	 */
	@Keyword
	public void bf_LookupTablesList_DeleteaLookupTable(String designName,String revisionName, String customerTabScreen) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : designName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : revisionName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : customerTabScreen]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'VIEW FIELDS']), 0)
		Thread.sleep(2000)
		//User def lookup eka tap krnda one
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'MODIFY']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'TABLES']), 0)
		Thread.sleep(2000)
		Mobile.tapAndHold(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "REQUEST_CONTACT"]), 3, 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementNotExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "REQUEST_CONTACT"]), 0)
		Thread.sleep(2000)
		//thawa verification ekak thiyenwa
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
		Thread.sleep(2000)
	}

	/**381
	 *TA-Add Lookup Table: Verify validations for save
	 */
	@Keyword
	public void bf_AddLookupTable_VerifyValidationsForSave(String designName,String revisionName, String customerTabScreen) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : designName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : revisionName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : customerTabScreen]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'VIEW FIELDS']), 0)
		Thread.sleep(2000)
		//User def lookup eka tap krnda one
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'MODIFY']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'TABLES']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'ADD TABLE']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']), 0)
		Thread.sleep(2000)
		//Screen shot for the verification
		Mobile.takeScreenshot('Screenshots/MobileDesigner_AddLookupTable_VerifyValidationsForSave.png', FailureHandling.STOP_ON_FAILURE)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
		Thread.sleep(2000)
	}

	/**382
	 *TA-Add Lookup Table: Add a lookup table
	 */
	@Keyword
	public void bf_AddLookupTable_AddaLookupTable(String designName,String revisionName, String customerTabScreen, String columnName) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : designName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : revisionName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : customerTabScreen]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'VIEW FIELDS']), 0)
		Thread.sleep(2000)
		//User def lookup eka tap krnda one
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'MODIFY']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'TABLES']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'ADD TABLE']), 0)
		Thread.sleep(2000)
		//Add table process
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName',[('idf_LabelName') : 'Table']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByTextDropDownItem',[('idf_LabelName') : 'CONTRACT']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName',[('idf_LabelName') : 'Parent Table']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByTextDropDownItem',[('idf_LabelName') : 'REQUEST_CONTRACT']), 0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Parent Columns']), columnName, 3)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Child Columns']), columnName, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_ErrorMessageWithContinue'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'CONTACT']), 0)
		//Screen shot for the blue dot
		Mobile.takeScreenshot('Screenshots/MobileDesigner_AddLookupTable_AddaLookupTable.png', FailureHandling.STOP_ON_FAILURE)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
		Thread.sleep(2000)
	}

	/**383
	 *TA-Lookup Table Properties: Verify validation for Parent Table
	 */
	@Keyword
	public void bf_LookupTableProperties_VerifyValidationForParentTable(String designName,String revisionName, String customerTabScreen) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : designName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : revisionName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : customerTabScreen]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'VIEW FIELDS']), 0)
		Thread.sleep(2000)
		//User def lookup eka tap krnda one
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'MODIFY']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'TABLES']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'CONTACT']), 0)
		Thread.sleep(2000)
		//Add table process
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName',[('idf_LabelName') : 'Parent Table']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByTextDropDownItem',[('idf_LabelName') : 'CONTACT']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']), 0)
		Thread.sleep(2000)
		//Screenshot for error message
		Mobile.takeScreenshot('Screenshots/MobileDesigner_LookupTableProperties_VerifyValidationForParentTable.png', FailureHandling.STOP_ON_FAILURE)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
		Thread.sleep(2000)
	}



	/**384
	 *TA-Lookup Table Properties: Modify lookup table properties
	 */
	@Keyword
	public void bf_LookupTableProperties_ModifyLookupTableProperties(String designName,String revisionName, String customerTabScreen, String columnName) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : designName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : revisionName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : customerTabScreen]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'VIEW FIELDS']), 0)
		Thread.sleep(2000)
		//User def lookup eka tap krnda one
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'MODIFY']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'TABLES']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'CONTACT']), 0)
		Thread.sleep(2000)
		//Add table process
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Child Columns']), columnName, 3)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Parent Columns']), columnName, 3)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'VIEW COLUMNS']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Lookup Columns']), 0)
		Mobile.takeScreenshot('Screenshots/MobileDesigner_LookupTableProperties_VerifyValidationForParentTable.png', FailureHandling.STOP_ON_FAILURE)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
		Thread.sleep(2000)
	}

	/**385
	 *TA-Lookup Column List: Verify the list items available on the Lookup Columns screen
	 */
	@Keyword
	public void bf_LookupColumnList_VerifyTheListItemsAvailableOnTheLookupColumnsScreen(String designName,String revisionName, String customerTabScreen) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : designName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : revisionName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : customerTabScreen]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'VIEW FIELDS']), 0)
		Thread.sleep(2000)
		//User def lookup eka tap krnda one
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'MODIFY']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'TABLES']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'REQUEST_CONTACT']), 0)
		Thread.sleep(2000)
		//verify process
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'VIEW COLUMNS']), 0)
		Thread.sleep(2000)
		Mobile.takeScreenshot('Screenshots/MobileDesigner_LookupTableProperties_VerifyTheListItemsAvailableOnTheLookupColumnsScreen.png', FailureHandling.STOP_ON_FAILURE)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
		Thread.sleep(2000)
	}

	/**386
	 *TA-Lookup Column List: Delete a lookup column
	 */
	@Keyword
	public void bf_LookupColumnList_DeleteaLookupColumn(String designName,String revisionName, String customerTabScreen) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : designName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : revisionName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : customerTabScreen]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'VIEW FIELDS']), 0)
		Thread.sleep(2000)
		//User def lookup eka tap krnda one
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'MODIFY']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'TABLES']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'REQUEST_CONTACT']), 0)
		Thread.sleep(2000)
		//verify process
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'VIEW COLUMNS']), 0)
		Thread.sleep(2000)
		//long press a list item
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']), 0)
		Thread.sleep(2000)
		//verify the element deleted
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
		Thread.sleep(2000)
	}
	
	/**387
	 *TA-Add Lookup Column: Verify validations for Save
	 */
	@Keyword
	public void bf_AddLookupColumn_VerifyValidationsForSave(String designName,String revisionName, String customerTabScreen) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : designName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : revisionName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : customerTabScreen]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'VIEW FIELDS']), 0)
		Thread.sleep(2000)
		//Tap lookup column
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'MODIFY']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'TABLES']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'REQUEST_CONTACT']), 0)
		Thread.sleep(2000)

		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'VIEW COLUMNS']), 0)
		Thread.sleep(2000)

		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']), 0)
		Thread.sleep(2000)
		Mobile.takeScreenshot('Screenshots/MobileDesigner_AddLookupColumn_VerifyValidationsForSave.png', FailureHandling.STOP_ON_FAILURE)
		Thread.sleep(2000)

		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
		Thread.sleep(2000)
	}
	
	/**388
	 *TA-Add Lookup Column: Add a lookup column
	 */
	@Keyword
	public void bf_AddLookupColumn_AddaLookupColumn(String designName,String revisionName, String customerTabScreen, String columnName) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : designName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : revisionName]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Screens']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : customerTabScreen]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'VIEW FIELDS']), 0)
		Thread.sleep(2000)
		//Tap lookup column
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'MODIFY']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'TABLES']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'REQUEST_CONTACT']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'VIEW COLUMNS']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'ADD COLUMN']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/dd_CommonByUpperLabelName',[('idf_LabelName') : 'Column']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByTextDropDownItem',[('idf_LabelName') : columnName]), 0)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'SAVE']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']), 0)
		Thread.sleep(2000)		
		Mobile.takeScreenshot('Screenshots/MobileDesigner_AddLookupColumn_VerifyValidationsForSave.png', FailureHandling.STOP_ON_FAILURE)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_ErrorMessageWithContinue'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : columnName]), 0)
		//Blue dot verification
		Mobile.takeScreenshot('Screenshots/MobileDesigner_AddLookupColumn_AddaLookupColumn.png', FailureHandling.STOP_ON_FAILURE)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Close']), 0)
		Thread.sleep(2000)
	}



}


