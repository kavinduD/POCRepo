package com.ifs.fsm.ios
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
import io.appium.java_client.MobileElement;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.Dimension;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;

import io.appium.java_client.AppiumDriver
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption

import org.openqa.selenium.JavascriptExecutor

class LIB_Common {
	// This method scroll and click on any element in a picker wheel
	public static void clickElementFromPickerWheel(String labelName) {
		TestObject pickerWheel = new TestObject();
		pickerWheel.addProperty("xpath", ConditionType.EQUALS, "//XCUIElementTypePickerWheel");
		MobileBuiltInKeywords.sendKeys(pickerWheel, labelName);
	}

	//This method captures the coordinates of the passed label name and performs a right to left swipe on the element
	public static void swipeDelete(String labelName) {
		Thread.sleep(3000)
		int startingX = Mobile.getDeviceWidth() - 5
		int endingX = Mobile.getElementLeftPosition(findTestObject('iOS/PG_Common/lbl_FirstElementFromList', [('idf_LabelName') : labelName]), 0)
		int y = Mobile.getElementTopPosition(findTestObject('iOS/PG_Common/lbl_FirstElementFromList', [('idf_LabelName') : labelName]), 0)

		AppiumDriver driver = MobileDriverFactory.getDriver()
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Map<String, Object> params = new HashMap<>();
		params.put("duration", 1.0);
		params.put("fromX", startingX);
		params.put("fromY", y);
		params.put("toX", endingX);
		params.put("toY", y);
		js.executeScript("mobile: dragFromToForDuration", params);
	}

	/**
	 * Check if element present in timeout
	 * @param to Katalon test object
	 * @param timeout time to wait for element to show up
	 * @return true if element present, otherwise false
	 */
	@Keyword
	def isElementPresent_Mobile(TestObject to, int timeout){
		try {
			KeywordUtil.logInfo("Finding element with id:" + to.getObjectId())

			WebElement element = MobileElementCommonHelper.findElement(to, timeout)
			if (element != null) {
				KeywordUtil.markPassed("Object " + to.getObjectId() + " is present")
			}
			return true
		} catch (Exception e) {
			KeywordUtil.markFailed("Object " + to.getObjectId() + " is not present")
		}
		return false;
	}

	/**
	 * Get mobile driver for current session
	 * @return mobile driver for current session
	 */
	@Keyword
	def WebDriver getCurrentSessionMobileDriver() {
		return MobileDriverFactory.getDriver();
	}

	/**
	 * Click on Hamburger Menu Item
	 */
	@Keyword
	public static void bf_ClickHamburgerMenuOption(String menuOptionName) {
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_HamburgerMenu'), 0, 0)
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/lbl_CommonByText', [('idf_LabelName') : menuOptionName]), 0, 0)
	}

	/**
	 * Click on Context Menu option.
	 */
	@Keyword
	public static void bf_ClickContextMenuOption(String contextMenuOptionName) {
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_DropDownIcon'), 0, 0)

		int y = Mobile.getDeviceHeight()
		int x = Mobile.getDeviceWidth()

		int xNew = (x/2);
		int yNew = (y/2);

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
	 * Select a element from JumpTo menu bar
	 */
	@Keyword
	public static void bf_SelectMenuItem(String menuItem) {
		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_DropDownIcon'), 0, 0)

		Mobile.tapAndHold(findTestObject('iOS/PG_Common/btn_CommonByName', [('idf_ButtonName') : menuItem]), 0, 0)
	}
}