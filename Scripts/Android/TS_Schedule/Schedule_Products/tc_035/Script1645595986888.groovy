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

CustomKeywords.'com.ifs.fsm.android.LIB_Login.bf_Login'(GlobalVariable.var_AppIdAndroid, 'TAMOBU1', 'TAMOBU1')

CustomKeywords.'com.ifs.fsm.android.LIB_Common.bf_ClickHamburgerMenuOption'('Jobs')

CustomKeywords.'com.ifs.fsm.android.LIB_Schedule.bf_SelectTask'('Accepted')

CustomKeywords.'com.ifs.fsm.android.LIB_Schedule.bf_Request_AddNewRequest'(GlobalVariable.var_PartNeeds_placeIdFrom_2)

CustomKeywords.'com.ifs.fsm.android.LIB_Schedule.bf_Products_AddProduct'(GlobalVariable.var_Products_ModelID, GlobalVariable.var_PartsUsed_SerialID)

CustomKeywords.'com.ifs.fsm.android.LIB_Common.bf_ClickHamburgerMenuOption'('Home')

CustomKeywords.'com.ifs.fsm.android.LIB_Login.bf_Logout'()

