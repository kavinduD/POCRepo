package com.ifs.fsm.android

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import ch.qos.logback.core.joran.conditional.IfAction
import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import java.util.Calendar


class LIB_DebriefSteps {

	/**
	 * Task step is set to complete
	 */
	@Keyword
	public void bf_TaskSteps_CompleteStep() {
		Mobile.checkElement(findTestObject('Android/PG_Common/chk_FirstCheckBoxFromList'), 0)

		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Thread.sleep(3000)

		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 10)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Steps']), 10)

		Mobile.verifyElementChecked(findTestObject('Android/PG_Common/chk_FirstCheckBoxFromList'), 10)
	}

	/**
	 * Task step is set to complete with other details
	 */
	@Keyword
	public void bf_TaskSteps_CompleteStepWithDetail(String stepName, String note) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : stepName]),0)

		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Note']), note, 3)

		Mobile.checkElement(findTestObject('Android/PG_DebriefSteps/chk_Completed'), 0)

		Mobile.tap(findTestObject('Android/PG_WorkList/btn_Update'), 0)

		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Thread.sleep(3000)

		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 10)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Steps']), 10)

		Mobile.verifyElementChecked(findTestObject('Android/PG_Common/chk_FirstCheckBoxFromList'), 10)
	}

	/**
	 * Task step is set to incomplete 
	 */
	@Keyword
	public void bf_TaskSteps_IncompleteStep() {
		Mobile.uncheckElement(findTestObject('Android/PG_Common/chk_FirstCheckBoxFromList'), 0)

		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Thread.sleep(3000)

		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 10)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Steps']), 10)

		Mobile.verifyElementNotChecked(findTestObject('Android/PG_Common/chk_FirstCheckBoxFromList'), 10)
	}

	/**
	 * Task step is set to incomplete with by clicking step name 
	 */
	@Keyword
	public void bf_TaskSteps_IncompleteStepWithDetail(String stepName) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : stepName]),0)

		Mobile.uncheckElement(findTestObject('Android/PG_DebriefSteps/chk_Completed'), 0)

		Mobile.tap(findTestObject('Android/PG_WorkList/btn_Update'), 0)

		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Thread.sleep(3000)

		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 10)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Steps']), 10)

		Mobile.verifyElementNotChecked(findTestObject('Android/PG_Common/chk_FirstCheckBoxFromList'), 10)
	}

	/**
	 * Task step is set to complete with attachment
	 */
	@Keyword
	public void bf_TaskSteps_CompleteStepWithAttachment(String stepName, String note) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : stepName]),0)

		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "Attachments", ('idf_ButtonNameCapital') : "ATTACHMENTS"]),5)

		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Note']), note, 3)

		Mobile.checkElement(findTestObject('Android/PG_DebriefSteps/chk_Completed'), 0)

		Mobile.tap(findTestObject('Android/PG_WorkList/btn_Update'), 0)

		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Thread.sleep(3000)

		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 10)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Steps']), 10)

		Mobile.verifyElementChecked(findTestObject('Android/PG_Common/chk_FirstCheckBoxFromList'), 10)
	}

	/**
	 * Validate signature required error message in task step 
	 */
	@Keyword
	public void bf_TaskSteps_ValidateSignature(String stepName) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : stepName]),0)

		Mobile.checkElement(findTestObject('Android/PG_DebriefSteps/chk_Completed'), 0)

		Mobile.tap(findTestObject('Android/PG_WorkList/btn_Update'), 0)
		Thread.sleep(3000)

		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "Yes", ('idf_ButtonNameCapital') : "YES"]),5)
		Thread.sleep(3000)

		Mobile.verifyElementNotChecked(findTestObject('Android/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_Number') : '3']), 0)
	}

	/**
	 * Task step is set to complete with signature
	 */
	@Keyword
	public void bf_TaskSteps_CompleteStepWithSignature(String stepName) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : stepName]),0)

		Mobile.tap(findTestObject('Android/PG_Signature/img_Signature'), 0)
		Thread.sleep(3000)

		int startingX = ( Mobile.getElementWidth(findTestObject('Android/PG_Signature/ele_SignaturePad'), 0) / 2 )
		int endingX = startingX + 50
		int startingY = startingX
		int endingY = startingX + 50
		Mobile.swipe(200, 200, 250, 200)

		Mobile.tap(findTestObject('Android/PG_Signature/btn_Accept'), 0)
		Thread.sleep(3000)

		Mobile.checkElement(findTestObject('Android/PG_DebriefSteps/chk_Completed'), 0)

		Mobile.tap(findTestObject('Android/PG_WorkList/btn_Update'), 0)
		Thread.sleep(3000)

		Mobile.verifyElementChecked(findTestObject('Android/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_Number') : '3']), 0)

		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : stepName]),0)

		String name = Mobile.getText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Signer']), 0)
	}

	/**
	 * Task step signature is cleared and set to incomplete
	 */
	@Keyword
	public void bf_TaskSteps_IncompleteStepAndRemoveSignature(String stepName) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : stepName]),0)

		Mobile.tapAndHold(findTestObject('Android/PG_Signature/img_Signature'), 5, 5)

		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByTextCapitalORSimple',[('idf_ButtonNameSimple') : "Yes", ('idf_ButtonNameCapital') : "YES"]),5)
		Thread.sleep(3000)

		Mobile.uncheckElement(findTestObject('Android/PG_DebriefSteps/chk_Completed'), 0)

		Mobile.tap(findTestObject('Android/PG_WorkList/btn_Update'), 0)
		Thread.sleep(3000)

		Mobile.verifyElementNotChecked(findTestObject('Android/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_Number') : '3']), 0)
	}

	/**
	 * Task step is set to complete without adding a signature
	 */
	@Keyword
	public void bf_TaskSteps_CompleteStepWithoutSignature() {
		Mobile.checkElement(findTestObject('Android/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_Number') : '3']), 0)

		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Thread.sleep(3000)

		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Overview']), 5)

		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 10)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Steps']), 10)

		Mobile.verifyElementChecked(findTestObject('Android/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_Number') : '3']), 0)
	}

	/**
	 * Task status is set to complete without completing a task step 
	 */
	@Keyword
	public void bf_TaskSteps_CompleteTaskValidation() {
		Mobile.verifyElementNotChecked(findTestObject('Android/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_Number') : '2']), 0)

		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Thread.sleep(3000)

		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Overview']), 5)

		Mobile.tap(findTestObject('Android/PG_WorkList/btn_TaskStatus'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Completed']), 0)
		Thread.sleep(500)

		String message = Mobile.getText(findTestObject('Android/PG_WorkList/lbl_ErrorMessageForRequiredFields'),10)
		KeywordUtil.markPassed("Error Message : " + message)
	}

	/**
	 * Task status is set to complete after setting all the task steps to complete
	 */
	@Keyword
	public void bf_TaskSteps_CompleteTask() {
		Mobile.checkElement(findTestObject('Android/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_Number') : '1']), 0)

		Mobile.checkElement(findTestObject('Android/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_Number') : '2']), 0)

		Mobile.checkElement(findTestObject('Android/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_Number') : '3']), 0)

		Mobile.tap(findTestObject('Android/PG_Common/btn_Next'), 0)
		Thread.sleep(3000)

		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Overview']), 5)

		Mobile.tap(findTestObject('Android/PG_WorkList/btn_TaskStatus'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Completed']), 0)
		Thread.sleep(500)
	}

	/**
	 * Check Step category is not available in the context menu in a different task
	 */
	@Keyword
	public void bf_TaskSteps_ValidateStepNotExist() {
		Mobile.tap(findTestObject('Android/PG_Common/ele_OverflowIcon'), 10)
		Mobile.verifyElementNotExist(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Steps']), 10)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Overview']), 10)
	}

	/**
	 * Check task step is set to Completed in the products list for each record in products screen
	 */
	@Keyword
	public void bf_UnitSteps_ProductStatusVerify() {
		for (int i=1; i<2; i++) {
			boolean elementExist = Mobile.verifyElementExist(findTestObject('Android/PG_DebriefSteps/PG_UnitSteps/lbl_TaskStepStatus', [('idf_number') : i]), 0);
			if(elementExist) {
				boolean flag = Mobile.verifyElementText(findTestObject('Android/PG_DebriefSteps/PG_UnitSteps/lbl_TaskStepStatus', [('idf_number') : i]),'Completed') ;
				if(!flag) {
					KeywordUtil.markFailed("Task step status not set to completed!")
				}
			} else {
				KeywordUtil.markPassed("Products end!")
				break;
			}
		}
	}

	/**
	 * Task unit step is set to Completed in products screen
	 */
	@Keyword
	public void bf_UnitSteps_CompleteStep(String productName) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : productName]),0)

		Mobile.checkElement(findTestObject('Android/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_Number') : '1']), 0)

		Mobile.tap(findTestObject('Android/PG_WorkList/btn_Update'), 0)
		Thread.sleep(3000)

		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Products']), 5)

		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : productName]),0)
		Mobile.verifyElementChecked(findTestObject('Android/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_Number') : '1']), 0)
	}

	/**
	 * Task unit step is set to complete with other details
	 */
	@Keyword
	public void bf_UnitSteps_CompleteStepWithDetail(String productName, String stepName, String note) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : productName]),0)

		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : stepName]),0)

		Mobile.setText(findTestObject('Android/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Note']), note, 3)

		Mobile.checkElement(findTestObject('Android/PG_DebriefSteps/chk_Completed'), 0)

		Mobile.tap(findTestObject('Android/PG_WorkList/btn_Update'), 0)
		Thread.sleep(3000)

		Mobile.tap(findTestObject('Android/PG_WorkList/btn_Update'), 0)
		Thread.sleep(3000)

		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : productName]),0)
		Mobile.verifyElementChecked(findTestObject('Android/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_Number') : '1']), 0)
	}

	/**
	 * Task unit step is set to incomplete
	 */
	@Keyword
	public void bf_UnitSteps_IncompleteStep(String productName) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : productName]),0)

		Mobile.uncheckElement(findTestObject('Android/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_Number') : '1']), 0)

		Mobile.tap(findTestObject('Android/PG_WorkList/btn_Update'), 0)
		Thread.sleep(3000)

		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Products']), 5)

		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : productName]),0)
		Mobile.verifyElementNotChecked(findTestObject('Android/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_Number') : '1']), 0)
	}

	/**
	 * Task unit step is set to incomplete with by clicking step name
	 */
	@Keyword
	public void bf_UnitSteps_IncompleteStepWithDetail(String productName, String stepName) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : productName]),0)

		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : stepName]),0)

		Mobile.uncheckElement(findTestObject('Android/PG_DebriefSteps/chk_Completed'), 0)

		Mobile.tap(findTestObject('Android/PG_WorkList/btn_Update'), 0)
		Thread.sleep(3000)

		Mobile.tap(findTestObject('Android/PG_WorkList/btn_Update'), 0)
		Thread.sleep(3000)

		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : productName]),0)
		Mobile.verifyElementNotChecked(findTestObject('Android/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_Number') : '1']), 0)
	}

	/**
	 * All task unit steps are set to incomplete and check for 'not started' status
	 */
	@Keyword
	public void bf_UnitSteps_ProductStatusNotStartedCheck(String productName) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : productName]),0)

		Mobile.uncheckElement(findTestObject('Android/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_Number') : '1']), 0)

		Mobile.uncheckElement(findTestObject('Android/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_Number') : '2']), 0)

		Mobile.uncheckElement(findTestObject('Android/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_Number') : '3']), 0)

		Mobile.tap(findTestObject('Android/PG_WorkList/btn_Update'), 0)
		Thread.sleep(3000)

		Mobile.verifyElementText(findTestObject('Android/PG_DebriefSteps/PG_UnitSteps/lbl_TaskStepStatus', [('idf_number') : 1]),'Not Started') ;
	}

	/**
	 * At least one task unit step is set to complete and check for 'In process' status
	 */
	@Keyword
	public void bf_UnitSteps_ProductStatusInProcessCheck(String productName) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : productName]),0)

		Mobile.checkElement(findTestObject('Android/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_Number') : '1']), 0)

		Mobile.tap(findTestObject('Android/PG_WorkList/btn_Update'), 0)
		Thread.sleep(3000)

		Mobile.verifyElementText(findTestObject('Android/PG_DebriefSteps/PG_UnitSteps/lbl_TaskStepStatus', [('idf_number') : 1]),'In Process') ;

		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : productName]),0)

		Mobile.uncheckElement(findTestObject('Android/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_Number') : '1']), 0)

		Mobile.tap(findTestObject('Android/PG_WorkList/btn_Update'), 0)
		Thread.sleep(3000)
	}

	/**
	 * All required task unit steps are set to complete and check for 'Required Completed' status
	 */
	@Keyword
	public void bf_UnitSteps_ProductStatusRequiredCompletedCheck(String productName) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : productName]),0)

		Mobile.verifyElementNotChecked(findTestObject('Android/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_Number') : '1']), 0)
		Mobile.checkElement(findTestObject('Android/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_Number') : '2']), 0)
		Mobile.checkElement(findTestObject('Android/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_Number') : '3']), 0)

		Mobile.tap(findTestObject('Android/PG_WorkList/btn_Update'), 0)
		Thread.sleep(3000)

		Mobile.verifyElementText(findTestObject('Android/PG_DebriefSteps/PG_UnitSteps/lbl_TaskStepStatus', [('idf_number') : 1]),'Required Completed') ;

		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : productName]),0)

		Mobile.uncheckElement(findTestObject('Android/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_Number') : '2']), 0)
		Mobile.uncheckElement(findTestObject('Android/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_Number') : '3']), 0)

		Mobile.tap(findTestObject('Android/PG_WorkList/btn_Update'), 0)
		Thread.sleep(3000)
	}

	/**
	 * All task unit steps are set to complete and check for 'Completed' status
	 */
	@Keyword
	public void bf_UnitSteps_ProductStatusCompletedCheck(String productName) {
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : productName]),0)

		Mobile.checkElement(findTestObject('Android/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_Number') : '1']), 0)
		Mobile.checkElement(findTestObject('Android/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_Number') : '2']), 0)
		Mobile.checkElement(findTestObject('Android/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_Number') : '3']), 0)

		Mobile.tap(findTestObject('Android/PG_WorkList/btn_Update'), 0)
		Thread.sleep(3000)

		Mobile.verifyElementText(findTestObject('Android/PG_DebriefSteps/PG_UnitSteps/lbl_TaskStepStatus', [('idf_number') : 1]),'Completed') ;

		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : productName]),0)

		Mobile.uncheckElement(findTestObject('Android/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_Number') : '1']), 0)
		Mobile.uncheckElement(findTestObject('Android/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_Number') : '2']), 0)
		Mobile.uncheckElement(findTestObject('Android/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_Number') : '3']), 0)

		Mobile.tap(findTestObject('Android/PG_WorkList/btn_Update'), 0)
		Thread.sleep(3000)
	}
}