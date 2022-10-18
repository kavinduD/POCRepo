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

public class LIB_Authentication {

	//launch the application
	public String LaunchApp(String application) {
		Mobile.startExistingApplication(application, FailureHandling.STOP_ON_FAILURE)
	}


	/**
	 * TA-Activation: Using FSM credentials-Invalid service URL
	 */
	@Keyword
	public void bf_Activation_InvalidServiceURL(String serviceUrl) {
		LaunchApp('com.ifs.FSM-Mobile-Push.InHouse')
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'FSM Mobile Service URL']), serviceUrl, 3)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName',[('idf_ButtonName') : "Connect"]), 0,0)
		Mobile.delay(5)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Authentication/PG_Activation/lbl_ActivateDeviceErrorMessage'), 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'OK']), 0)
		Thread.sleep(2000)
	}

	/**
	 * TA-Activation: Using FSM credentials
	 */
	@Keyword
	public void bf_Activation_UsingFSMCredentials(String serviceUrl,String username,String password) {
		LaunchApp('com.ifs.FSM-Mobile-Push.InHouse')
		Mobile.setText(findTestObject('iOS/PG_Common/tf_CommonByUpperLabelName', [('idf_LabelName') : 'FSM Mobile Service URL']), serviceUrl, 3)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName',[('idf_ButtonName') : "Connect"]), 0,0)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Login/chk_ShowPassword'), 0,0)
		Mobile.setText(findTestObject('iOS/PG_Authentication/PG_Activation/tf_Person_ID'), username, 0)
		Mobile.delay(5)
		Mobile.setText(findTestObject('iOS/PG_Login/tf_Password'), password, 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Sync Monitor"]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Your device is being initialized. Please wait.']), 0)
		Thread.sleep(25000)
		Mobile.verifyElementNotExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : 'Your device is being initialized. Please wait.']), 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Hello TA USER1!"]), 0)
		Thread.sleep(2000)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_HamburgerMenu'), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stocks']), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_HamburgerMenu'), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Customers']), 0, 0)
	}

	/**
	 * TA-Activation: Login-Require Login disabled
	 */
	@Keyword
	public void bf_Activation_RequireLogindisabled() {
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/switch_CommonByUpperLabelName',[('idf_LabelName') : "Require Login"]), 0,0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_HamburgerMenu'), 0, 0)
		 int y = Mobile.getDeviceHeight()
		 int x = Mobile.getDeviceWidth()
		 int xNew = x - 10 ;
		 int yNew = (y/2);
		 Mobile.swipe(xNew, yNew, xNew , yNew - 200)
		 Mobile.delay(5)
		 Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Close App']), 0, 0)
		 Mobile.delay(5)
		 Mobile.delay(5)
		LaunchApp('com.ifs.FSM-Mobile-Push.InHouse')
		Mobile.delay(5)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Hello TA USER1!"]), 0)
		Mobile.verifyElementNotExist(findTestObject('iOS/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Password"]), 0)
	}


	/**
	 * TA-Password Change: Change PW from FSM Mobile Admin Screen-Invalid old PW
	 */
	@Keyword
	public void bf_PasswordChange_ChangePasswordfromMobileAdminScreenInvaliOldPW(String oldPassword,String newPassword) {
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Change Password']), 0,0)
		Mobile.setText(findTestObject('iOS/PG_Authentication/PG_PasswordChange/tf_SecureTextCommonByUpperLabelName', [('idf_LabelName') : 'Old password']), oldPassword, 0)
		Mobile.setText(findTestObject('iOS/PG_Authentication/PG_PasswordChange/tf_SecureTextCommonByUpperLabelName', [('idf_LabelName') : 'New password']), newPassword, 0)
		Mobile.setText(findTestObject('iOS/PG_Authentication/PG_PasswordChange/tf_SecureTextCommonByUpperLabelName', [('idf_LabelName') : 'Confirm password']), newPassword, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Receiving/btn_Done'), 0,0)
		Mobile.delay(10)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Authentication/PG_PasswordChange/lbl_LoginErrorMessage'), 2)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'OK']), 0,0)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Cancel']), 0,0)
	}


	/**
	 * Password Change: Change PW from FSM Mobile Admin Screen-Invalid PW confirmation
	 */
	@Keyword
	public void bf_PasswordChange_ChangePWfromMobileAdminScreenInvalidoldPWConfirmation(String oldPassword,String newPassword,String confirmPassword) {
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Change Password']), 0,0)
		Mobile.setText(findTestObject('iOS/PG_Authentication/PG_PasswordChange/tf_SecureTextCommonByUpperLabelName', [('idf_LabelName') : 'Old password']), oldPassword, 0)
		Mobile.setText(findTestObject('iOS/PG_Authentication/PG_PasswordChange/tf_SecureTextCommonByUpperLabelName', [('idf_LabelName') : 'New password']), newPassword, 0)
		Mobile.setText(findTestObject('iOS/PG_Authentication/PG_PasswordChange/tf_SecureTextCommonByUpperLabelName', [('idf_LabelName') : 'Confirm password']), confirmPassword, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Receiving/btn_Done'), 0,0)
		Mobile.delay(10)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_ErrorMessage'), 2)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'OK']), 0,0)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Cancel']), 0,0)
	}


	/**
	 * TA-Password Change: Change PW from FSM Mobile Admin screen- Invalid new PW
	 */
	@Keyword
	public void bf_PasswordChange_ChangePWfromAdminScreenInvalidNewPW(String oldPassword,String newPassword) {
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Change Password']), 0,0)
		Mobile.setText(findTestObject('iOS/PG_Authentication/PG_PasswordChange/tf_SecureTextCommonByUpperLabelName', [('idf_LabelName') : 'Old password']), oldPassword, 0)
		Mobile.setText(findTestObject('iOS/PG_Authentication/PG_PasswordChange/tf_SecureTextCommonByUpperLabelName', [('idf_LabelName') : 'New password']), newPassword, 0)
		Mobile.setText(findTestObject('iOS/PG_Authentication/PG_PasswordChange/tf_SecureTextCommonByUpperLabelName', [('idf_LabelName') : 'Confirm password']), newPassword, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Receiving/btn_Done'), 0,0)
		Mobile.delay(10)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_ErrorMessage'), 2)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'OK']), 0,0)
		Mobile.delay(5)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : 'Cancel']), 0,0)
	}


}





