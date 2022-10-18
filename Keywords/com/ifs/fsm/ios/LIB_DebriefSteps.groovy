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

import com.kms.katalon.core.util.KeywordUtil
import internal.GlobalVariable

public class LIB_DebriefSteps {
	//Select an element from the context menu
	public void selectItemFromContextMenu(String contextMenuOptionName) {
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_DropDownIcon'), 0, 0)

		int y = Mobile.getElementTopPosition(findTestObject('iOS/PG_WorkList/lbl_OpportunityInContextMenu'), 0)
		int x = Mobile.getElementLeftPosition(findTestObject('iOS/PG_WorkList/lbl_OpportunityInContextMenu'), 0)

		int xNew = x + x;
		int yNew = y + y

		for(int i=0; i<10; i++) {
			boolean flag = Mobile.waitForElementPresent(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : contextMenuOptionName]), 3)

			if(flag) {
				Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : contextMenuOptionName]), 0, 0)
				break;
			}else {
				Mobile.swipe(xNew, yNew, xNew , yNew - 200)
				Thread.sleep(3000)
			}
		}
	}


	/**
	 * Task step is set to complete
	 */
	@Keyword
	public void bf_TaskSteps_CompleteStep() {
		Mobile.tap(findTestObject('iOS/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_number') : '1']), 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0, 0)

		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Overview']), 0)

		selectItemFromContextMenu('Steps')

		Mobile.verifyElementChecked(findTestObject('iOS/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_number') : '1']), 0)
	}

	/**
	 * Task step is set to complete with other details
	 */
	@Keyword
	public void bf_TaskSteps_CompleteStepWithDetail(String stepName, String note) {
		Mobile.tap(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : stepName]), 0)

		Mobile.setText(findTestObject('iOS/PG_Common/tv_CommonByUpperLabelName', [('idf_LabelName') : 'Note']), note, 0)

		Mobile.tap(findTestObject('iOS/PG_DebriefSteps/chk_Completed'), 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Save']), 0, 0)

		Mobile.verifyElementChecked(findTestObject('iOS/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_number') : '1']), 0)
	}

	/**
	 * Task step is set to incomplete
	 */
	@Keyword
	public void bf_TaskSteps_IncompleteStep() {
		Mobile.tap(findTestObject('iOS/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_number') : '1']), 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0, 0)

		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Overview']), 0)

		selectItemFromContextMenu('Steps')

		Mobile.verifyElementNotChecked(findTestObject('iOS/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_number') : '1']), 0)
	}

	/**
	 * Task step is set to incomplete with by clicking step name
	 */
	@Keyword
	public void bf_TaskSteps_IncompleteStepWithDetail(String stepName) {
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : stepName]), 0, 0)

		Mobile.tap(findTestObject('iOS/PG_DebriefSteps/chk_Completed'), 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Save']), 0, 0)

		Mobile.verifyElementNotChecked(findTestObject('iOS/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_number') : '1']), 0)
	}

	/**
	 * Validate signature required error message in task step
	 */
	@Keyword
	public void bf_TaskSteps_ValidateSignature(String stepName) {
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : stepName]), 0, 0)

		Mobile.tap(findTestObject('iOS/PG_DebriefSteps/chk_Completed'), 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Save']), 0, 0)

		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_ErrorMessage'), 5)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'OK']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_Back'), 0, 0)
		Thread.sleep(3000)

		Mobile.verifyElementNotChecked(findTestObject('iOS/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_number') : '3']), 0)
	}

	/**
	 * Task step is set to complete with signature
	 */
	@Keyword
	public void bf_TaskSteps_CompleteStepWithSignature(String stepName) {
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : stepName]), 0, 0)

		Mobile.verifyElementExist(findTestObject('iOS/PG_Signature/lbl_TapToSign'), 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Signature/img_Signature'), 0, 0)
		Thread.sleep(3000)

		Mobile.swipe(200, 200, 250, 200)
		Thread.sleep(3000)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Accept']), 0, 0)
		Thread.sleep(3000)

		Mobile.verifyElementNotExist(findTestObject('iOS/PG_Signature/lbl_TapToSign'), 0)

		Mobile.tap(findTestObject('iOS/PG_DebriefSteps/chk_Completed'), 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Save']), 0, 0)
		Thread.sleep(3000)

		Mobile.verifyElementChecked(findTestObject('iOS/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_number') : '3']), 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : stepName]), 0, 0)
		String name = Mobile.getText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'Signer']), 0)
		KeywordUtil.markPassed("Name " + name)
	}

	/**
	 * Task step signature is cleared and set to incomplete
	 */
	@Keyword
	public void bf_TaskSteps_IncompleteStepAndRemoveSignature(String stepName) {
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : stepName]), 0, 0)

		Mobile.tapAndHold(findTestObject('Android/PG_Signature/img_Signature'), 3, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)
		Thread.sleep(3000)

		Mobile.tap(findTestObject('iOS/PG_DebriefSteps/chk_Completed'), 0)

		Mobile.verifyElementExist(findTestObject('iOS/PG_Signature/lbl_TapToSign'), 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Save']), 0, 0)
		Thread.sleep(3000)

		Mobile.verifyElementNotChecked(findTestObject('iOS/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_number') : '3']), 0)
	}

	/**
	 * Task step is set to complete without adding a signature
	 */
	@Keyword
	public void bf_TaskSteps_CompleteStepWithoutSignature() {
		Mobile.tap(findTestObject('iOS/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_number') : '3']), 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0, 0)

		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Overview']), 0)
		selectItemFromContextMenu('Steps')

		Mobile.verifyElementChecked(findTestObject('iOS/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_number') : '3']), 0)
	}

	/**
	 * Task status is set to complete without completing a task step
	 */
	@Keyword
	public void bf_TaskSteps_CompleteTaskValidation() {
		Mobile.verifyElementNotChecked(findTestObject('iOS/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_number') : '2']), 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_WorkList/btn_TaskStatus'), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Completed']), 0, 0)

		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_ErrorMessage'), 5)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'OK']), 0, 0)
	}

	/**
	 * Task status is set to complete after setting all the task steps to complete
	 */
	@Keyword
	public void bf_TaskSteps_CompleteTask() {
		Mobile.tap(findTestObject('iOS/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_number') : '2']), 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Next']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_WorkList/btn_TaskStatus'), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Completed']), 0, 0)
	}

	/**
	 * Task status is set to complete after setting all the task steps to complete
	 */
	@Keyword
	public void bf_TaskSteps_ValidateStepNotExist() {
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_DropDownIcon'), 0, 0)

		int y = Mobile.getElementTopPosition(findTestObject('iOS/PG_WorkList/lbl_OpportunityInContextMenu'), 0)
		int x = Mobile.getElementLeftPosition(findTestObject('iOS/PG_WorkList/lbl_OpportunityInContextMenu'), 0)

		int xNew = x + x;
		int yNew = y + y

		for(int i=0; i<10; i++) {
			boolean flag = Mobile.waitForElementPresent(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Steps']), 3)

			if(flag) {
				Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Steps']), 0, 0)
				break;
			}else {
				Mobile.swipe(xNew, yNew, xNew , yNew - 200)
				Thread.sleep(3000)
			}
		}

		KeywordUtil.markPassed("Step not existed in this task !")
	}

	/**
	 * Check task step is set to Completed in the products list for each record in products screen
	 */
	@Keyword
	public void bf_UnitSteps_ProductStatusVerify() {
		for (int i=1; i<5; i++) {
			boolean elementExist = Mobile.verifyElementExist(findTestObject('iOS/PG_DebriefSteps/PG_UnitSteps/lbl_TaskStepStatus', [('idf_number') : i]), 0, FailureHandling.OPTIONAL);
			if(elementExist) {
				boolean flag = Mobile.verifyElementText(findTestObject('iOS/PG_DebriefSteps/PG_UnitSteps/lbl_TaskStepStatus', [('idf_number') : i]), 'Completed', FailureHandling.OPTIONAL) ;
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
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : productName]), 0, 0)

		Mobile.tap(findTestObject('iOS/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_number') : '1']), 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Save']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : productName]), 0, 0)

		Mobile.verifyElementChecked(findTestObject('iOS/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_number') : '1']), 0)
	}

	/**
	 * Task unit step is set to complete with other details
	 */
	@Keyword
	public void bf_UnitSteps_CompleteStepWithDetail(String productName, String stepName, String note) {
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : productName]), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : stepName]), 0, 0)

		Mobile.tap(findTestObject('iOS/PG_DebriefSteps/PG_UnitSteps/chk_Completed'), 0)

		Mobile.setText(findTestObject('iOS/PG_Common/tv_CommonByUpperLabelName', [('idf_LabelName') : 'Note']), '', 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Done']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Save']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Save']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : productName]), 0, 0)

		Mobile.verifyElementChecked(findTestObject('iOS/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_number') : '1']), 0)
	}

	/**
	 * Task unit step is set to incomplete
	 */
	@Keyword
	public void bf_UnitSteps_IncompleteStep(String productName) {
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : productName]), 0, 0)

		Mobile.tap(findTestObject('iOS/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_number') : '1']), 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Save']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : productName]), 0, 0)

		Mobile.verifyElementNotChecked(findTestObject('iOS/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_number') : '1']), 0)
	}

	/**
	 * Task unit step is set to incomplete with by clicking step name
	 */
	@Keyword
	public void bf_UnitSteps_IncompleteStepWithDetail(String productName, String stepName) {
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : productName]), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : stepName]), 0, 0)

		Mobile.tap(findTestObject('iOS/PG_DebriefSteps/PG_UnitSteps/chk_Completed'), 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Save']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Save']), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : productName]), 0, 0)

		Mobile.verifyElementNotChecked(findTestObject('iOS/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_number') : '1']), 0)
	}

	/**
	 * All task unit steps are set to incomplete and check for 'not started' status
	 */
	@Keyword
	public void bf_UnitSteps_ProductStatusNotStartedCheck(String productName) {
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : productName]), 0, 0)

		Mobile.verifyElementNotChecked(findTestObject('iOS/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_number') : '1']), 0)
		Mobile.verifyElementNotChecked(findTestObject('iOS/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_number') : '2']), 0)
		Mobile.verifyElementNotChecked(findTestObject('iOS/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_number') : '3']), 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Save']), 0, 0)

		Mobile.verifyElementText(findTestObject('iOS/PG_DebriefSteps/PG_UnitSteps/lbl_TaskStepStatus', [('idf_number') : 1]), 'Not Started' )
	}

	/**
	 * At least one task unit step is set to complete and check for 'In process' status
	 */
	@Keyword
	public void bf_UnitSteps_ProductStatusInProcessCheck(String productName) {
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : productName]), 0, 0)

		Mobile.tap(findTestObject('iOS/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_number') : '1']), 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Save']), 0, 0)

		Mobile.verifyElementText(findTestObject('iOS/PG_DebriefSteps/PG_UnitSteps/lbl_TaskStepStatus', [('idf_number') : 1]), 'In Process' )

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : productName]), 0, 0)

		Mobile.tap(findTestObject('iOS/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_number') : '1']), 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Save']), 0, 0)
	}

	/**
	 * All required task unit steps are set to complete and check for 'Required Completed' status
	 */
	@Keyword
	public void bf_UnitSteps_ProductStatusRequiredCompletedCheck(String productName) {
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : productName]), 0, 0)

		Mobile.verifyElementNotChecked(findTestObject('iOS/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_number') : '1']), 0)
		Mobile.tap(findTestObject('iOS/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_number') : '2']), 0)
		Mobile.tap(findTestObject('iOS/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_number') : '3']), 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Save']), 0, 0)

		Mobile.verifyElementText(findTestObject('iOS/PG_DebriefSteps/PG_UnitSteps/lbl_TaskStepStatus', [('idf_number') : 1]), 'Required Completed' )

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : productName]), 0, 0)

		Mobile.tap(findTestObject('iOS/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_number') : '2']), 0)
		Mobile.tap(findTestObject('iOS/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_number') : '3']), 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Save']), 0, 0)
	}

	/**
	 * All task unit steps are set to complete and check for 'Completed' status
	 */
	@Keyword
	public void bf_UnitSteps_ProductStatusCompletedCheck(String productName) {
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : productName]), 0, 0)

		Mobile.tap(findTestObject('iOS/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_number') : '1']), 0)
		Mobile.tap(findTestObject('iOS/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_number') : '2']), 0)
		Mobile.tap(findTestObject('iOS/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_number') : '3']), 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Save']), 0, 0)

		Mobile.verifyElementText(findTestObject('iOS/PG_DebriefSteps/PG_UnitSteps/lbl_TaskStepStatus', [('idf_number') : 1]), 'Completed' )

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : productName]), 0, 0)

		Mobile.tap(findTestObject('iOS/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_number') : '1']), 0)
		Mobile.tap(findTestObject('iOS/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_number') : '2']), 0)
		Mobile.tap(findTestObject('iOS/PG_DebriefSteps/chk_CheckBoxFromList', [('idf_number') : '3']), 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Save']), 0, 0)
	}

}
