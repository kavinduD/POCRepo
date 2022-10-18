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

CustomKeywords.'com.ifs.fsm.ios.LIB_Common.bf_ClickHamburgerMenuOption'('Jobs')

<<<<<<<< HEAD:IFS_FSM_MobileAutomation/Scripts/iOS/TS_Debrief/Debrief_Overview/tc_121/Script1653275720301.groovy
CustomKeywords.'com.ifs.fsm.ios.LIB_Worklist.bf_SelectTask'('Accepted')

CustomKeywords.'com.ifs.fsm.ios.LIB_Worklist.bf_Overview_VerifyHyperlinks'()

CustomKeywords.'com.ifs.fsm.ios.LIB_Login.bf_Logout'()
========
CustomKeywords.'com.ifs.fsm.android.LIB_MobileDesigner.bf_AddScreen_VerifyValidationForAUniqueName'(GlobalVariable.var_MobileDesigner_Design, GlobalVariable.var_MobileDesigner_Revision,
	GlobalVariable.var_MobileDesigner_AddScreeName)
>>>>>>>> SupportBranch01:IFS_FSM_MobileAutomation/Scripts/Android/TS_MobileDesigner/Add Screen/tc_325/Script1658721965538.groovy

