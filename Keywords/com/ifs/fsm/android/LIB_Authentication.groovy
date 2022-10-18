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

import internal.GlobalVariable

public class LIB_Authentication {

	//launch the application
	public String LaunchApp(String application) {
		Mobile.startExistingApplication(application, FailureHandling.STOP_ON_FAILURE)
	}



	/**
	 * TA-Activation: FSMA-Denial of app permissions
	 */
	@Keyword
	public void bf_Activation_DenialAppPermissions() {
		LaunchApp('com.metrix.metrixmobile')
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'DENY']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'DENY']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'NO']), 0)
		Thread.sleep(2000)
		//continue
	}

	/**
	 * TA-Activation: FSMA-Approval of app permissions
	 */
	@Keyword
	public void bf_Activation_ApprovalOfAppPermissions() {
		LaunchApp('com.metrix.metrixmobile')
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'ALLOW']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'ALLOW']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "IFS Field Service Management"]), 0)
		Thread.sleep(2000)
	}

	/**
	 * TA-Activation: Using FSM credentials-Invalid service URL
	 */
	@Keyword
	public void bf_Activation_InvalidServiceURL(String serviceUrl) {
		LaunchApp('com.metrix.metrixmobile')
		Mobile.setText(findTestObject('Android/PG_Authentication/tf_ServiceURL'),serviceUrl,0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'CONNECT']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_ErrorMessageWithContinue'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'OK']), 0)
		Thread.sleep(2000)
	}

	/**
	 * TA-Activation: Using FSM credentials
	 */
	@Keyword
	public void bf_Activation_UsingFSMCredentials(String serviceUrl,String username,String password) {
		LaunchApp('com.metrix.metrixmobile')
		Mobile.setText(findTestObject('Android/PG_Authentication/tf_ServiceURL'),serviceUrl,0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'CONNECT']), 0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Login/tf_Username'), username, 0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Login/tf_Password'), password, 0)
		Mobile.hideKeyboard()
		Mobile.tap(findTestObject('Android/PG_Login/btn_Login'), 0)
		Thread.sleep(1000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_ErrorMessageWithContinue'), 0)
		Thread.sleep(20000)
		Mobile.verifyElementNotExist(findTestObject('Android/PG_Common/lbl_ErrorMessageWithContinue'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Hello TA USER1!"]), 0)
		Thread.sleep(2000)
	}

	/**
	 * TA-Activation: FSMA-Denial of app permission to access location information
	 */
	@Keyword
	public void bf_Activation_DenialAppPermissiontoAccessLocationInformation(String serviceUrl,String username,String password) {
		LaunchApp('com.metrix.metrixmobile')
		Mobile.setText(findTestObject('Android/PG_Authentication/tf_ServiceURL'),serviceUrl,0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'CONNECT']), 0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Login/tf_Username'), username, 0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Login/tf_Password'), password, 0)
		Mobile.hideKeyboard()
		Mobile.tap(findTestObject('Android/PG_Login/btn_Login'), 0)
		Thread.sleep(1000)
		Mobile.tap(findTestObject('Android/PG_Authentication/btn_DontAllow'), 0)
		Thread.sleep(1000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'NO']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Hello TA USER1!"]), 0)
		Thread.sleep(2000)
	}

	/**
	 * TA-Activation: FSMA-Approval of app permission to access location information
	 */
	@Keyword
	public void bf_Activation_ApprovalofAppPermissionAccessLocationInformation(String serviceUrl,String username,String password) {
		LaunchApp('com.metrix.metrixmobile')
		Mobile.setText(findTestObject('Android/PG_Authentication/tf_ServiceURL'),serviceUrl,0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'CONNECT']), 0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Login/tf_Username'), username, 0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Login/tf_Password'), password, 0)
		Mobile.hideKeyboard()
		Mobile.tap(findTestObject('Android/PG_Login/btn_Login'), 0)
		Thread.sleep(1000)
		Mobile.tap(findTestObject('Android/PG_Authentication/btn_DontAllow'), 0)
		Thread.sleep(1000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'YES']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'WHILE USING THE APP']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Hello TA USER1!"]), 0)
		Thread.sleep(2000)
	}

	/**
	 * TA-Activation: Using OIDC authentication-Invalid e-mail address
	 */
	@Keyword
	public void bf_Activation_UsingOIDCAuthenticationInvalidEmailAddress(String serviceUrl,String usernameInput) {
		LaunchApp('com.metrix.metrixmobile')
		Mobile.setText(findTestObject('Android/PG_Authentication/tf_ServiceURL'),serviceUrl,0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'CONNECT']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Use SSO"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'LOGIN TO FSM']), 0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Authentication/tf_UserNameInput'), usernameInput, 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'Sign in']), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Authentication/lbl_ErrorMessageForUserInput'), 0)
		Thread.sleep(1000)
		//continue will be doing after environment configuration
	}

	/**
	 * TA-Activation: Using OIDC authentication- With two factor authentication
	 */
	@Keyword
	public void bf_Activation_UsingOIDCAuthenticationWithTwoFactorAuthentication(String serviceUrl,String usernameInput,String password) {
		LaunchApp('com.metrix.metrixmobile')
		Mobile.setText(findTestObject('Android/PG_Authentication/tf_ServiceURL'),serviceUrl,0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'CONNECT']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Use SSO"]), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'LOGIN TO FSM']), 0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Authentication/tf_UserNameInput'), usernameInput, 0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Authentication/tf_UserNameInput'), password, 0)
		Thread.sleep(2000)
		//continue will be doing after environment configuration
	}

	/**
	 * TA-Activation: Using OIDC authentication- Without two factor authentication
	 */
	@Keyword
	public void bf_Activation_UsingOIDCAuthenticationWithoutTwoFactorAuthentication() {
		//continue will be doing after environment configuration
	}

	/**
	 * TA-Activation: Using OIDC authentication-Logout/Re-login
	 */
	@Keyword
	public void bf_Activation_UsingOIDCAuthenticationLogout() {
		//continue will be doing after environment configuration
	}

	/**
	 * TA-Activation: Login-Require Login disabled
	 */
	@Keyword
	public void bf_Activation_RequireLogindisabled() {
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Authentication/chk_RequireLogin'), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/btn_Add'), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Android/PG_Common/lbl_TextScrollable',[('idf_TextName') : 'Close App']), 0)
		Thread.sleep(2000)
		LaunchApp('com.metrix.metrixmobile')
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Hello TA USER1!"]), 0)
		Thread.sleep(2000)
		Mobile.verifyElementNotExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Password"]), 0)
		Thread.sleep(2000)
	}


	/**
	 * TA-Password Change: Change PW from FSM Mobile Admin Screen-Invalid old PW
	 */
	@Keyword
	public void bf_PasswordChange_ChangePasswordfromMobileAdminScreenInvaliOldPW(String oldPassword,String newPassword) {
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'CHANGE PASSWORD']), 0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Authentication/tf_CommonByText', [('idf_LabelName') : 'Old password']), oldPassword, 0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Authentication/tf_CommonByText', [('idf_LabelName') : 'New password']), newPassword, 0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Authentication/tf_CommonByText', [('idf_LabelName') : 'Confirm password']), newPassword, 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'Save']), 0)
		//Mobile.verifyElementExist(findTestObject('Android/PG_Authentication/lbl_ErrorMessagewithToast'), 2)
		//continue toast
	}

	/**
	 * Password Change: Change PW from FSM Mobile Admin Screen-Invalid PW confirmation
	 */
	@Keyword
	public void bf_PasswordChange_ChangePWfromMobileAdminScreenInvalidoldPWConfirmation(String oldPassword,String newPassword,String confirmPassword) {
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'CHANGE PASSWORD']), 0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Authentication/tf_CommonByText', [('idf_LabelName') : 'Old password']), oldPassword, 0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Authentication/tf_CommonByText', [('idf_LabelName') : 'New password']), newPassword, 0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Authentication/tf_CommonByText', [('idf_LabelName') : 'Confirm password']), confirmPassword, 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'Save']), 0)
		//continue toast
	}

	/**
	 * TA-Password Change: Change PW from FSM Mobile Admin screen- Invalid new PW
	 */
	@Keyword
	public void bf_PasswordChange_ChangePWfromAdminScreenInvalidNewPW(String oldPassword,String newPassword) {
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'CHANGE PASSWORD']), 0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Authentication/tf_CommonByText', [('idf_LabelName') : 'Old password']), oldPassword, 0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Authentication/tf_CommonByText', [('idf_LabelName') : 'New password']), newPassword, 0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Authentication/tf_CommonByText', [('idf_LabelName') : 'Confirm password']), newPassword, 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'Save']), 0)
		//continue toast
	}

	/**
	 * TA-Password Change: Change PW from FSM Mobile Admin screen
	 */
	@Keyword
	public void bf_PasswordChange_ChangePWfromAdminSreen(String oldPassword) {
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'CHANGE PASSWORD']), 0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Authentication/tf_CommonByText', [('idf_LabelName') : 'Old password']), oldPassword, 0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Authentication/tf_CommonByText', [('idf_LabelName') : 'New password']), oldPassword, 0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Authentication/tf_CommonByText', [('idf_LabelName') : 'Confirm password']), oldPassword, 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'Save']), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_ErrorMessageWithContinue'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Hello TA USER1!"]), 0)
		Thread.sleep(2000)
	}

	/**
	 * TA-Password Change: Change PW from FSM Mobile Admin Screen-Verify Logout/Re-login
	 */
	@Keyword
	public void bf_PasswordChange_ChangePWfromAdminScreenVerifyLogoutRelogin(String oldPassword) {
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'CHANGE PASSWORD']), 0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Authentication/tf_CommonByText', [('idf_LabelName') : 'Old password']), oldPassword, 0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Authentication/tf_CommonByText', [('idf_LabelName') : 'New password']), oldPassword, 0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Authentication/tf_CommonByText', [('idf_LabelName') : 'Confirm password']), oldPassword, 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'Save']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_HamburgerMenu'), 0)
		Mobile.tap(findTestObject('Object Repository/Android/PG_Common/lbl_SlidingMenuItemName',[('idf_SlidingMenuItemName') : 'Admin']), 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'LOGOUT AND CLOSE']), 0)
		Thread.sleep(2000)
		LaunchApp('com.metrix.metrixmobile')
		Mobile.setText(findTestObject('Android/PG_Login/tf_Password'), oldPassword, 0)
		Mobile.hideKeyboard()
		Mobile.tap(findTestObject('Android/PG_Login/btn_Login'), 0)
		Thread.sleep(2000)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Hello TA USER1!"]), 0)
		Thread.sleep(2000)
	}

	/**
	 * TA-Password Change: Change PW from FSM Mobile Login screen
	 */
	@Keyword
	public void bf_PasswordChange_ChangePWfromLoginScreen(String oldPassword) {
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'LOGOUT AND CLOSE']), 0)
		Thread.sleep(2000)
		LaunchApp('com.metrix.metrixmobile')
		Mobile.tap(findTestObject('Android/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Change Password']), 0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Authentication/tf_CommonByText', [('idf_LabelName') : 'Old password']), oldPassword, 0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Authentication/tf_CommonByText', [('idf_LabelName') : 'New password']), oldPassword, 0)
		Thread.sleep(2000)
		Mobile.setText(findTestObject('Android/PG_Authentication/tf_CommonByText', [('idf_LabelName') : 'Confirm password']), oldPassword, 0)
		Thread.sleep(2000)
		Mobile.tap(findTestObject('Android/PG_Common/btn_CommonByText', [('idf_ButtonName') : 'Save']), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_ErrorMessageWithContinue'), 0)
		Mobile.verifyElementExist(findTestObject('Android/PG_Common/lbl_CommonByText',[('idf_LabelName') : "Hello TA USER1!"]), 0)
		Thread.sleep(2000)
	}




}
