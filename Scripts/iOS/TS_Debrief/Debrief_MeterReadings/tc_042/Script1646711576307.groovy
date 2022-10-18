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

CustomKeywords.'com.ifs.fsm.ios.LIB_Worklist.bf_SelectTask'('37203')

CustomKeywords.'com.ifs.fsm.ios.LIB_Common.bf_ClickContextMenuOption'('Meter Readings')

CustomKeywords.'com.ifs.fsm.ios.LIB_Worklist.bf_MeterReadings_AddReading'(GlobalVariable.var_MeterReadings_MeterName1, GlobalVariable.var_MeterReadings_MeterValue)

CustomKeywords.'com.ifs.fsm.ios.LIB_Worklist.bf_SelectTask'('37205')

CustomKeywords.'com.ifs.fsm.ios.LIB_Common.bf_ClickContextMenuOption'('Meter Readings')

CustomKeywords.'com.ifs.fsm.ios.LIB_Worklist.bf_MeterReadings_ValidateMeterName'(GlobalVariable.var_MeterReadings_MeterName3, 
    GlobalVariable.var_MeterReadings_MeterValue)

CustomKeywords.'com.ifs.fsm.ios.LIB_Login.bf_Logout'()
