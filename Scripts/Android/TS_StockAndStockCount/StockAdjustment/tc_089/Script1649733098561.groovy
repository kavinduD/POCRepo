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

CustomKeywords.'com.ifs.fsm.android.LIB_Login.bf_Login'(GlobalVariable.var_AppIdAndroid, GlobalVariable.var_Username, GlobalVariable.var_Password)

CustomKeywords.'com.ifs.fsm.android.LIB_Common.bf_ClickHamburgerMenuOption'('Stock')

CustomKeywords.'com.ifs.fsm.android.LIB_StockAndStockCount.bf_Stock_SwapPartTransferParts'(GlobalVariable.var_Stock_AdjustmentReason4,GlobalVariable.var_PartNeeds_partId, GlobalVariable.var_Stock_Quantity,
	GlobalVariable.var_PartsUsed_PlaceID, GlobalVariable.var_Stock_Location,GlobalVariable.var_PartNeeds_placeIdFromAfterModify, GlobalVariable.var_PartNeeds_partIdAfterModify,GlobalVariable.var_PartsUsed_lotPartId,
	 GlobalVariable.var_Stock_LotID,GlobalVariable.var_Schedule_PartNeeds_SerialLotTrackedPartID,GlobalVariable.var_Stock_Location1)

CustomKeywords.'com.ifs.fsm.android.LIB_Login.bf_Logout'()