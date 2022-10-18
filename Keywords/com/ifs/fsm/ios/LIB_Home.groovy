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

public class LIB_Home {
	/**
	 * TA-Home: Verify available tiles in baseline design
	 */
	@Keyword
	public void bf_Home_VerifyAvailableTiles() {
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Jobs']), 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Customers']), 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock']), 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Team']), 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Receiving']), 0)
	}

	/**
	 * TA-Home: Verify navigation to relevant screen from home screen tiles
	 */
	@Keyword
	public void bf_Home_VerifyNavigationtoRelevantScreenfromHomeScreenTiles() {
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Jobs']), 0, 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Work List']), 0)
		LIB_Common.bf_ClickHamburgerMenuOption('Home')
		
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Customers']), 0, 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Customers']), 0)
		LIB_Common.bf_ClickHamburgerMenuOption('Home')
		
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock']), 0, 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock']), 0)
		LIB_Common.bf_ClickHamburgerMenuOption('Home')
		
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Team']), 0, 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Team Members']), 0)
		LIB_Common.bf_ClickHamburgerMenuOption('Home')
		
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Receiving']), 0, 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Receiving List']), 0)
	}

	/**
	 * TA-Home: Verify the counts displayed on home screen tiles
	 */
	@Keyword
	public void bf_Home_VerifytheCountsDisplayedonHomeScreenTiles() {
		//Cannot get the list count
	}

	/**
	 * TA-Home: Important Information-New Jobs
	 */
	@Keyword
	public void bf_Home_ImportantInformationNewJobs() {
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByContainText', [('idf_LabelName') : 'new jobs!']), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Open']), 0, 0)
	}

	/**
	 * TA-Home: Important Information-Overdue Jobs
	 */
	@Keyword
	public void bf_Home_ImportantInformationOverdueJobs() {
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByContainText', [('idf_LabelName') : 'overdue!']), 0, 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Work List']), 0)
	}

	/**
	 * TA-Home: Important Information Commitments
	 */
	@Keyword
	public void bf_Home_ImportantInformationCommitments() {
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByContainText', [('idf_LabelName') : 'commitments!']), 0, 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Commitments']), 0)
	}

	/**
	 * TA-Home: Important Information Escalations
	 */
	@Keyword
	public void bf_Home_ImportantInformationEscalations() {
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByContainText', [('idf_LabelName') : 'escalations!']), 0, 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Escalation List']), 0)
	}

	/**
	 * TA-Home: Important Information stock counts
	 */
	@Keyword
	public void bf_Home_ImportantInformationStockCounts() {
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByContainText', [('idf_LabelName') : 'stock']), 0, 0)
		Mobile.verifyElementExist(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : 'Stock']), 0)
	}
}
