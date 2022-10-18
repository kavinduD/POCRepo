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

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import io.appium.java_client.AppiumDriver
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory


import org.openqa.selenium.JavascriptExecutor

import internal.GlobalVariable

public class LIB_MobileDesigner {

	/**
	 * Click on Hamburger Menu Item
	 */
	@Keyword
	public static void bf_ClickHamburgerMenuOption(String taskStatus) {
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_HamburgerMenu'), 0, 0)

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


	// This method scroll and click on any element in a picker wheel
	public void clickElementFromPickerWheel(String labelName) {
		TestObject pickerWheel = new TestObject();
		pickerWheel.addProperty("xpath", ConditionType.EQUALS, "//XCUIElementTypePickerWheel");
		MobileBuiltInKeywords.sendKeys(pickerWheel, labelName);
	}

	/**
	 * Select a Task from  list
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





	/**
	 * TA-Add Design: Verify validations for mandatory fields
	 */
	@Keyword
	public void bf_AddDesign_VerifyValidationsforMandatoryFields() {
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add Design']),0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Save']),0,0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_ErrorMessage'), 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'OK']),0,0)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_HamburgerMenu'), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Close']), 0, 0)
	}


	/**
	 * TA-Add Design: Add new design
	 */
	@Keyword
	public void bf_AddDesign_AddNewDesign(String name,String description) {
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add Design']),0,0)
		Mobile.delay(5)
		Mobile.setText(findTestObject('iOS/PG_MobileDesigner/Add Design/tf_Name'), name, 3)
		Mobile.setText(findTestObject('iOS/PG_Common/tv_CommonByUpperLabelName', [('idf_LabelName') : 'Description:']),description,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']),0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Save']),0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Yes']),0,0)
		Mobile.delay(3)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Design addition in progress. Please wait…"]), 0)
		Mobile.delay(1200)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Designs"]), 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : name]), 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : name]),0,0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByContainText',[('idf_LabelName') : description]), 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_HamburgerMenu'), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Close']), 0, 0)
	}


	/**
	 * TA-Add Design: Copy from specific Design/ Revision
	 */
	@Keyword
	public void bf_AddDesign_CopyfromSpecificDesignRevision(String name,String description,String design,String revision) {
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add Design']),0,0)
		Mobile.delay(5)
		Mobile.setText(findTestObject('iOS/PG_MobileDesigner/Add Design/tf_Name'), name, 3)
		Mobile.setText(findTestObject('iOS/PG_Common/tv_CommonByUpperLabelName', [('idf_LabelName') : 'Description:']),description,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']),0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Design:']), 0, 0)
		clickElementFromPickerWheel(design)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']),0,0)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_MobileDesigner/Add Design/tf_Revision'), 0, 0)
		clickElementFromPickerWheel(revision)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']),0,0)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Save']),0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Yes']),0,0)
		Mobile.delay(5)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Design addition in progress. Please wait…"]), 0)
		Mobile.delay(300)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Designs"]), 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : name]), 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : name]),0,0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByContainText',[('idf_LabelName') : description]), 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_HamburgerMenu'), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Close']), 0, 0)
	}


	/**
	 * TA-Revision List: Add a new revision
	 */
	@Keyword
	public void bf_RevisionList_AddNewRevision(String description) {
		bf_SelectTask('TA-DESIGN2')
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add Revision']),0,0)
		Mobile.setText(findTestObject('iOS/PG_MobileDesigner/Add Design/tf_Name'), description, 3)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'OK']),0,0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Revision addition in progress. Please wait…"]), 0)
		Mobile.delay(15)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Categories"]), 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_Back'), 0, 0)
		Mobile.delay(5)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : description]), 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_HamburgerMenu'), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Close']), 0, 0)
	}


	/**
	 * TA-Revision List: Publish a pending revision
	 */
	@Keyword
	public void bf_RevisionList_PublishPendingRevision() {
		bf_SelectTask('TA-DESIGN2')
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PENDING"]), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Publish Revision']),0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Yes']),0,0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Revisions']), 0)
		Mobile.verifyElementNotExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'PENDING']), 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'PUBLISHED']), 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_HamburgerMenu'), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Close']), 0, 0)
	}

	/**
	 * TA-Revision List: Revision Load
	 */
	@Keyword
	public void bf_RevisionList_RevisionLoad() {
		bf_SelectTask('TA-DESIGN2')
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PUBLISHED"]), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Yes']),0,0)
		Thread.sleep(5000)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Categories"]), 0)
		Thread.sleep(2000)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_HamburgerMenu'), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Close']), 0, 0)
	}

	/**
	 * TA-Theme List: Delete a theme
	 */
	@Keyword
	public void bf_ThemeList_DeleteTheme() {
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_HamburgerMenu'), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Themes']), 0, 0)
		bf_SelectTask('TA-TESTDESIGN')
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_Back'),0,0)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'TA-TESTDESIGN']), 4, 4)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Yes']),0,0)
		Mobile.verifyElementNotExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'TA-TESTDESIGN']), 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_HamburgerMenu'), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Close']), 0, 0)
	}


	/**
	 * TA-Add Theme: Verify validation for mandatory fields
	 */
	@Keyword
	public void bf_AddTheme_VerifyValidationformandatoryfields() {
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_HamburgerMenu'), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Themes']), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add Theme']),0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Save']),0,0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Information"]), 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'OK']),0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_HamburgerMenu'), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Close']), 0, 0)

	}


	/**
	 * TA-Add Theme: Add a new theme-without a source theme
	 */
	@Keyword
	public void bf_AddTheme_AddNewThemeWithoutSourceTheme(String name) {
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_HamburgerMenu'), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Themes']), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add Theme']),0,0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Name:']), name, 3)
		Mobile.tapAndHold(findTestObject('iOS/PG_Receiving/btn_Done'), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Save']),0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Yes']),0,0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Theme addition in progress. Please wait…"]), 0)
		Mobile.delay(50)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Themes"]), 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : name]),0)
		Mobile.takeScreenshot('Screenshots/MobileDesigner_AddThemeWithoutSourceTheme.png', FailureHandling.STOP_ON_FAILURE)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_HamburgerMenu'), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Close']), 0, 0)
	}


	/**
	 * TA-Add Theme: Add a new theme- with a source theme
	 */
	@Keyword
	public void bf_AddTheme_AddNewThemeWithSourceTheme(String name,String sourcetheme,String description) {
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_HamburgerMenu'), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Themes']), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add Theme']),0,0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Name:']), name, 3)
		Mobile.tapAndHold(findTestObject('iOS/PG_Receiving/btn_Done'), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Source:']),0,0)
		clickElementFromPickerWheel(sourcetheme)
		Mobile.delay(5)
		Mobile.setText(findTestObject('iOS/PG_Common/tv_CommonByUpperLabelName', [('idf_LabelName') : 'Description:']),description,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']),0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Save']),0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Yes']),0,0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Theme addition in progress. Please wait…"]), 0)
		Mobile.delay(50)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Themes"]), 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : name]),0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : description]),0)
		Mobile.takeScreenshot('Screenshots/MobileDesigner_AddThemeWithSourceTheme.png', FailureHandling.STOP_ON_FAILURE)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_HamburgerMenu'), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Close']), 0, 0)
	}

	/**
	 * TA-Colors: Set colors to a theme
	 */
	@Keyword
	public void bf_Colors_SetColorstoaTheme(String secondaryColor,String secondGradient) {
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_HamburgerMenu'), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Themes']), 0, 0)
		bf_SelectTask("TA-TESTDESIGN")
		Mobile.tapAndHold(findTestObject('iOS/PG_MobileDesigner/Colors/btn_PrimaryColorPicker'), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_MobileDesigner/Colors/btn_Color'), 0,0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Secondary:']), secondaryColor, 3)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Hyperlink:"]), 0,0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Hyperlink:']), secondGradient, 3)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Secondary:"]), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_MobileDesigner/Colors/btn_FirstGradient'), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_MobileDesigner/Colors/btn_Color'), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_MobileDesigner/Colors/btn_FirstGradient'), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_MobileDesigner/Colors/btn_Color'), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_MobileDesigner/Colors/btn_SecondGradient'), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_MobileDesigner/Colors/btn_Color'), 0,0)
		Mobile.takeScreenshot('Screenshots/MobileDesigner_colors.png', FailureHandling.STOP_ON_FAILURE)
		Mobile.tap(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Save']),0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Colors"]), 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'View Images']),0,0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Images"]), 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_HamburgerMenu'), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Close']), 0, 0)
	}


	/**
	 * TA-Images: Set an image for Small Icon
	 */
	@Keyword
	public void bf_Images_SetanImageforSmallIcon() {
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_HamburgerMenu'), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Themes']), 0, 0)
		bf_SelectTask("TA-TESTDESIGN")
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'View Images']),0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Select']),0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_MobileDesigner/Images/img_SmallIcon'),0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Save']),0,0)
		Mobile.delay(5)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Images"]), 0)
		Mobile.takeScreenshot('Screenshots/MobileDesigner_SmallIconImages.png', FailureHandling.STOP_ON_FAILURE)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Finish']),0,0)
		Mobile.delay(5)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Themes"]), 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_HamburgerMenu'), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Close']), 0, 0)
	}

	/**
	 * TA-Images: Set an image for Large Icon
	 */
	@Keyword
	public void bf_Images_SetImageforLargeIcon() {
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_HamburgerMenu'), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Themes']), 0, 0)
		bf_SelectTask("TA-TESTDESIGN")
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'View Images']),0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_SecondCommonByName', [('idf_ButtonName') : 'Select']),0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_MobileDesigner/Images/img_SmallIcon'),0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Save']),0,0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Images"]), 0)
		Mobile.takeScreenshot('Screenshots/MobileDesigner_LargeIconImages.png', FailureHandling.STOP_ON_FAILURE)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Finish']),0,0)
		Mobile.delay(5)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Themes"]), 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_HamburgerMenu'), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Close']), 0, 0)
	}


	/**
	 * TA-Images: Clear images set for Small/ Large Icon
	 */
	@Keyword
	public void bf_Images_ClearImagesSetforSmallLargeIcon() {
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_HamburgerMenu'), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Themes']), 0, 0)
		bf_SelectTask("TA-TESTDESIGN")
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'View Images']),0,0)
		Mobile.takeScreenshot('Screenshots/MobileDesigner_Images.png', FailureHandling.STOP_ON_FAILURE)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Clear']),0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_SecondCommonByName', [('idf_ButtonName') : 'Clear']),0,0)
		Mobile.takeScreenshot('Screenshots/MobileDesigner_ClearImages.png', FailureHandling.STOP_ON_FAILURE)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Save']),0,0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Images"]), 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Finish']),0,0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Themes"]), 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_HamburgerMenu'), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Close']), 0, 0)
	}


	/**
	 * TA-Theme Assignment: Select a theme for a pending revision
	 */
	@Keyword
	public void bf_ThemeAssignment_SelectaThemeforPendingRevision(String theme) {
		bf_SelectTask("TA-DESIGN1")
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PENDING"]), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Theme:']), 0, 0)
		clickElementFromPickerWheel(theme)
		Mobile.takeScreenshot('Screenshots/MobileDesigner_Themes.png', FailureHandling.STOP_ON_FAILURE)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_HamburgerMenu'), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Close']), 0, 0)
	}

	/**
	 * TA-Theme Assignment: Save a theme for a pending revision
	 */
	@Keyword
	public void bf_ThemeAssignment_SaveThemeforPendingRevision(String theme) {
		bf_SelectTask("TA-DESIGN1")
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PENDING"]), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Theme:']), 0, 0)
		clickElementFromPickerWheel(theme)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']),0,0)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Save Theme']),0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Yes']),0,0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Categories"]), 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_HamburgerMenu'), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Close']), 0, 0)
	}


	/**
	 * TA-Theme Assignment: Preview a pending revision
	 */
	@Keyword
	public void bf_ThemeAssignment_PreviewPendingRevision() {
		bf_SelectTask("TA-DESIGN1")
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PENDING"]), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_HamburgerMenu'), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Preview']), 0, 0)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Yes']),0,0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Hello TA USER1!"]), 0)
		Mobile.takeScreenshot('Screenshots/MobileDesigner_Home.png', FailureHandling.STOP_ON_FAILURE)
		Mobile.delay(5)
		Mobile.doubleTap(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "About"]), 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByContainText',[('idf_LabelName') : "TA-DESIGN1"]), 0)
	}

	/**
	 * TA-Menu List: Verify the list items available on the Menu List screen
	 */
	@Keyword
	public void bf_MenuList_VerifytheListItemsAvailableonMenuListScreen() {
		bf_SelectTask("TA-DESIGN1")
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PENDING"]), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Menus"]), 0,0)
		Mobile.delay(5)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Global"]), 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Home"]), 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Quote Workflow"]), 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Debrief Workflow"]), 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByContainText',[('idf_LabelName') : "Debrief"]), 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_HamburgerMenu'), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Close']), 0, 0)
	}


	/**
	 * TA-Global Enabling: Enable/Disable global menu items
	 */
	@Keyword
	public void bf_GlobalEnabling_EnableDisableGlobalMenuItems() {
		bf_SelectTask("TA-DESIGN1")
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PENDING"]), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Menus"]), 0,0)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Global"]), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/chk_CommonByUpperLabelName',[('idf_LabelName') : "About"]), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/chk_CommonByUpperLabelName',[('idf_LabelName') : "Customers"]), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/chk_CommonByUpperLabelName',[('idf_LabelName') : "Escalations"]), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']),0,0)
		Mobile.delay(5)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Add Global Menu Item"]), 0)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_Back'), 0, 0)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Save']),0,0)
		Mobile.delay(5)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Global Menu"]), 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']),0,0)
		Mobile.delay(5)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Global Menu Order"]), 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_HamburgerMenu'), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Close']), 0, 0)
	}

	/**
	 * TA-Global Enabling: Enable/ Disable global menu items-preview changes
	 */
	@Keyword
	public void bf_GlobalEnabling_EnableDisableGlobalMenuItemsPreviewChanges() {
		bf_SelectTask("TA-DESIGN1")
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PENDING"]), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Menus"]), 0,0)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Global"]), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/chk_CommonByUpperLabelName',[('idf_LabelName') : "About"]), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/chk_CommonByUpperLabelName',[('idf_LabelName') : "Customers"]), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/chk_CommonByUpperLabelName',[('idf_LabelName') : "Escalations"]), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']),0,0)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_Back'), 0, 0)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Save']),0,0)
		Mobile.delay(5)
		Mobile.takeScreenshot('Screenshots/MobileDesigner_BeforePreview.png', FailureHandling.STOP_ON_FAILURE)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']),0,0)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_HamburgerMenu'), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Preview']), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Yes']),0,0)
		Mobile.delay(5)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Hello TA USER1!"]), 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_HamburgerMenu'), 0, 0)
		Mobile.takeScreenshot('Screenshots/MobileDesigner_AftewrPreview.png', FailureHandling.STOP_ON_FAILURE)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_HamburgerMenu'), 0, 0)
	}


	/**
	 * TA-Add Global: Verify validations for Save
	 */
	@Keyword
	public void bf_AddGlobal_VerifyValidationsforSave() {
		bf_SelectTask("TA-DESIGN1")
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PENDING"]), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Menus"]), 0,0)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Global"]), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']),0,0)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Save']),0,0)
		Mobile.delay(5)
		Mobile.verifyElementExist(findTestObject('iOS/PG_MobileDesigner/Add Global/lbl_ErrorMessage',[('idf_LabelName') : "Information"]), 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'OK']),0,0)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_HamburgerMenu'), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Close']), 0, 0)
	}

	/**
	 * TA-Add Global: Verify validation for unique Name value
	 */
	@Keyword
	public void bf_AddGlobal_VerifyValidationforUniqueNameValue(String name) {
		bf_SelectTask("TA-DESIGN1")
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PENDING"]), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Menus"]), 0,0)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Global"]), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']),0,0)
		Mobile.delay(5)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Name:']),name, 3)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'return']),0,0)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Save']),0,0)
		Mobile.delay(5)
		Mobile.verifyElementExist(findTestObject('iOS/PG_MobileDesigner/Add Global/lbl_ErrorMessage',[('idf_LabelName') : "Information"]), 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'OK']),0,0)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_HamburgerMenu'), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Close']), 0, 0)
	}

	/**
	 * TA-Add Global: Add global menu item-Accept save confirmation
	 */
	@Keyword
	public void bf_AddGlobal_AddGlobalMenuItemAcceptSaveConfirmation(String name) {
		bf_SelectTask("TA-DESIGN1")
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PENDING"]), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Menus"]), 0,0)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Global"]), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']),0,0)
		Mobile.delay(5)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Name:']),name, 3)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'return']),0,0)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Save']),0,0)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Yes']),0,0)
		Mobile.delay(5)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Global Menu"]), 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : name]), 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_HamburgerMenu'), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Close']), 0, 0)
	}

	/**
	 * TA-Global Properties: Add/ update global properties
	 */
	@Keyword
	public void bf_GlobalProperties_AddUpdateGlobalProperties(String countScript,String iconName,String label,String tapEvent) {
		bf_SelectTask("TA-DESIGN1")
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PENDING"]), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Menus"]), 0,0)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Global"]), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Document"]), 10,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Yes']),0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_MobileDesigner/lbl_CommonByLookUpImage', [('idf_LabelName') : 'Count Script:']),0,0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_Search'),countScript , 3)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : countScript]), 0,0)
		Mobile.uncheckElement(findTestObject('iOS/PG_Common/chk_CommonByUpperLabelName', [('idf_LabelName') : 'Hide if Zero:']), 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Icon Name:']), 0, 0)
		clickElementFromPickerWheel(iconName)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']),0,0)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_MobileDesigner/lbl_CommonByLookUpImage', [('idf_LabelName') : 'Label:']),0,0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_Search'),label , 3)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_FirstElementFromList',[('idf_LabelName') : label]), 0,0)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_MobileDesigner/lbl_CommonByLookUpImage', [('idf_LabelName') : 'Tap Event:']),0,0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_Search'),tapEvent , 3)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : tapEvent]), 3,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Save']),0,0)
		Mobile.delay(5)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByContainText',[('idf_LabelName') : "Properties"]), 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Finish']),0,0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Global Menu"]), 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_HamburgerMenu'), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Close']), 0, 0)
	}


	/**
	 * TA-Global Properties: Preview global properties
	 */
	@Keyword
	public void bf_GlobalProperties_PreviewGlobalProperties() {
		bf_SelectTask("TA-DESIGN1")
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PENDING"]), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_HamburgerMenu'), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Preview']), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Yes']),0,0)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_HamburgerMenu'), 0, 0)
		Mobile.takeScreenshot('Screenshots/MobileDesigner_GlobalProperties.png', FailureHandling.STOP_ON_FAILURE)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Jobs']), 0, 0)
	}


	/**
	 * TA-Global Order: Modify the global order
	 */
	@Keyword
	public void bf_GlobalOrder_ModifytheGlobalOrder() {
		bf_SelectTask("TA-DESIGN1")
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PENDING"]), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Menus"]), 0,0)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Global"]), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']),0,0)
		Mobile.dragAndDrop(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Reorder Quotes']), findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Reorder Team']), 0)
		Mobile.delay(5)
		//reordering

		/*AppiumDriver driver = MobileDriverFactory.getDriver()
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Map<String, Object> params = new HashMap<>();
		params.put("duration", 1.0);
		params.put("fromX", 760);
		params.put("fromY", 982);
		params.put("toX", 760);
		params.put("toY", 1075);
		js.executeScript("mobile: dragFromToForDuration", params);*/


		//Mobile.dragAndDrop(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Quotes"]), findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Team"]),0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Save']),0,0)
		Mobile.delay(5)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Global Menu Order"]), 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Finish']),0,0)
		Mobile.delay(5)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Categories"]), 0)
		//continue
	}


	/**
	 * TA-Global Order: Preview global order
	 */
	@Keyword
	public void bf_GlobalOrder_PreviewGlobalOrder() {
		bf_SelectTask("TA-DESIGN1")
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PENDING"]), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Menus"]), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Global"]), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']),0,0)
		//continue
		
	}



	/**
	 * TA-Home Enabling: Enable/Disable home items
	 */
	@Keyword
	public void bf_HomeEnabling_EnableDisableHomeItems() {
		bf_SelectTask("TA-DESIGN1")
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PENDING"]), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Menus"]), 0,0)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Home"]), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/chk_CommonByUpperLabelName',[('idf_LabelName') : "About"]), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/chk_CommonByUpperLabelName',[('idf_LabelName') : "Customers"]), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/chk_CommonByUpperLabelName',[('idf_LabelName') : "Escalations"]), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']),0,0)
		Mobile.delay(5)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Add Home Menu Item"]), 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_Back'), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Save']),0,0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Home Menu"]), 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']),0,0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Home Menu Order"]), 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_HamburgerMenu'), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Close']), 0, 0)
	}


	/**
	 * TA-Home Enabling: Enable/ Disable home items-verifying validation for the min number of items
	 */
	@Keyword
	public void bf_HomeEnabling_EnableDisableHomeItemsVerifyingValidationforMinNumberofItems() {
		bf_SelectTask("TA-DESIGN1")
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PENDING"]), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Menus"]), 0,0)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Home"]), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/chk_CommonByUpperLabelName',[('idf_LabelName') : "About"]), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/chk_CommonByUpperLabelName',[('idf_LabelName') : "Customers"]), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Save']),0,0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_ErrorMessage'), 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'OK']),0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']),0,0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_ErrorMessage'), 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'OK']),0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']),0,0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_ErrorMessage'), 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'OK']),0,0)
	}
	

	/**
	 * TA-Add Home: Verify validations for Save
	 */
	@Keyword
	public void bf_AddHome_VerifyValidationsforSave() {
		bf_SelectTask("TA-DESIGN1")
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PENDING"]), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Menus"]), 0,0)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Home"]), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']),0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Save']),0,0)
		Mobile.delay(5)
		Mobile.verifyElementExist(findTestObject('iOS/PG_MobileDesigner/Add Global/lbl_ErrorMessage',[('idf_LabelName') : "Information"]), 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'OK']),0,0)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_HamburgerMenu'), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Close']), 0, 0)
	}

	/**
	 * TA-Add Home: Verify validation for unique Name value
	 */
	@Keyword
	public void bf_AddHome_VerifyValidationforUniqueNameValue(String name) {
		bf_SelectTask("TA-DESIGN1")
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PENDING"]), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Menus"]), 0,0)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Home"]), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']),0,0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Name:']),name, 3)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'return']),0,0)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Save']),0,0)
		Mobile.delay(5)
		Mobile.verifyElementExist(findTestObject('iOS/PG_MobileDesigner/Add Global/lbl_ErrorMessage',[('idf_LabelName') : "Information"]), 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'OK']),0,0)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_HamburgerMenu'), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Close']), 0, 0)
	}
	
	/**
	 * TA-Add Home:Add home menu item-Accept save confirmation
	 */
	@Keyword
	public void bf_AddHome_AddHomeMenuItemAcceptSaveConfirmation(String name) {
		bf_SelectTask("TA-DESIGN1")
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PENDING"]), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Menus"]), 0,0)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Home"]), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Add']),0,0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Name:']),name, 3)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'return']),0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Save']),0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Yes']),0,0)
		Mobile.delay(3)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Home menu item addition in progress. Please wait…"]), 0)
		Mobile.delay(50)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Home Menu"]), 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : name]), 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_HamburgerMenu'), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Close']), 0, 0)
	}

	
	/**
	 * TA-Home Properties: Add/ update home properties
	 */
	@Keyword
	public void bf_HomeProperties_AddUpdateHomeProperties(String countScript,String label,String tapEvent) {
		bf_SelectTask("TA-DESIGN1")
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PENDING"]), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Menus"]), 0,0)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Home"]), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Centavo"]),3, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Yes']),0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_MobileDesigner/lbl_CommonByLookUpImage', [('idf_LabelName') : 'Count Script:']),0,0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_Search'),countScript , 3)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : countScript]), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Select']),0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_MobileDesigner/Images/img_SmallIcon'),0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_MobileDesigner/lbl_CommonByLookUpImage', [('idf_LabelName') : 'Label:']),0,0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_Search'),label , 3)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_FirstElementFromList',[('idf_LabelName') : label]), 0,0)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_MobileDesigner/lbl_CommonByLookUpImage', [('idf_LabelName') : 'Tap Event:']),0,0)
		Mobile.setText(findTestObject('iOS/PG_Common/tf_Search'),tapEvent , 3)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : tapEvent]), 3,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Save']),0,0)
		Mobile.delay(5)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByContainText',[('idf_LabelName') : "Properties"]), 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Finish']),0,0)
		Mobile.delay(5)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Home Menu"]), 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_HamburgerMenu'), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Close']), 0, 0)
	}

	
	/**
	 * TA-Home Properties: Preview home properties
	 */
	@Keyword
	public void bf_HomeProperties_PreviewHomeProperties() {
		bf_SelectTask("TA-DESIGN1")
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "PENDING"]), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Menus"]), 0,0)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Home"]), 0,0)
		Mobile.takeScreenshot('Screenshots/MobileDesigner_HomeProperties.png', FailureHandling.STOP_ON_FAILURE)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_HamburgerMenu'), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Preview']), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Yes']),0,0)
		Mobile.takeScreenshot('Screenshots/MobileDesigner_PreviewHomeProperties.png', FailureHandling.STOP_ON_FAILURE)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "About"]), 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "About"]), 0,0)
	}
	

}
