import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

CustomKeywords.'com.ifs.fsm.ios.LIB_Login.bf_Login'(GlobalVariable.var_AppIdiOS, GlobalVariable.var_Username, GlobalVariable.var_Password)

CustomKeywords.'com.ifs.fsm.ios.LIB_Common.bf_ClickHamburgerMenuOption'('Admin')

<<<<<<<< HEAD:IFS_FSM_MobileAutomation/Scripts/iOS/TS_Authentication/PasswordChange/tc_261/Script1661150681252.groovy
CustomKeywords.'com.ifs.fsm.ios.LIB_Authentication.bf_PasswordChange_ChangePWfromAdminScreenInvalidNewPW'(GlobalVariable.var_Password,GlobalVariable.var_DebriefOverview_Duration)
========
//CustomKeywords.'com.ifs.fsm.android.LIB_MobileDesigner.bf_AddScreen_MenuOptionsGlobalandHome'(GlobalVariable.var_Expenses_LineCode,GlobalVariable.var_MobileDesign_MenuOption)

CustomKeywords.'com.ifs.fsm.android.LIB_MobileDesigner.bf_AddScreen_MenuOptionsGlobalandHome'(GlobalVariable.var_MobileDesigner_Design, GlobalVariable.var_MobileDesigner_Revision,
	GlobalVariable.var_MobileDesigner_AddScreeName4, GlobalVariable.var_MobileDesigner_AddScreenMenuType)
>>>>>>>> SupportBranch01:IFS_FSM_MobileAutomation/Scripts/Android/TS_MobileDesigner/Add Screen/tc_328/Script1659427332960.groovy

CustomKeywords.'com.ifs.fsm.ios.LIB_Login.bf_Logout'()