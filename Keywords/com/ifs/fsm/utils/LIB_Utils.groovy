package com.ifs.fsm.utils
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.context.TestSuiteContext
import com.kms.katalon.core.logging.KeywordLogger
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

import internal.GlobalVariable

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

import java.util.*;
import java.text.*;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import org.w3c.dom.*;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.text.SimpleDateFormat;
import java.util.Date;

class LIB_Utils {
	/**
	 * Refresh browser
	 */
	@Keyword
	def refreshBrowser() {
		KeywordUtil.logInfo("Refreshing")
		WebDriver webDriver = DriverFactory.getWebDriver()
		webDriver.navigate().refresh()
		KeywordUtil.markPassed("Refresh successfully")
	}

	/**
	 * Click element
	 * @param to Katalon test object
	 */
	@Keyword
	def clickElement(TestObject to) {
		try {
			WebElement element = WebUiBuiltInKeywords.findWebElement(to);
			KeywordUtil.logInfo("Clicking element")
			element.click()
			KeywordUtil.markPassed("Element has been clicked")
		} catch (WebElementNotFoundException e) {
			KeywordUtil.markFailed("Element not found")
		} catch (Exception e) {
			KeywordUtil.markFailed("Fail to click on element")
		}
	}

	/**
	 * Get all rows of HTML table
	 * @param table Katalon test object represent for HTML table
	 * @param outerTagName outer tag name of TR tag, usually is TBODY
	 * @return All rows inside HTML table
	 */
	@Keyword
	def List<WebElement> getHtmlTableRows(TestObject table, String outerTagName) {
		WebElement mailList = WebUiBuiltInKeywords.findWebElement(table)
		List<WebElement> selectedRows = mailList.findElements(By.xpath("./" + outerTagName + "/tr"))
		return selectedRows
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
	 * Send request and verify status code
	 * @param request request object, must be an instance of RequestObject
	 * @param expectedStatusCode
	 * @return a boolean to indicate whether the response status code equals the expected one
	 */
	@Keyword
	def verifyStatusCode(TestObject request, int expectedStatusCode) {
		if (request instanceof RequestObject) {
			RequestObject requestObject = (RequestObject) request
			ResponseObject response = WSBuiltInKeywords.sendRequest(requestObject)
			if (response.getStatusCode() == expectedStatusCode) {
				KeywordUtil.markPassed("Response status codes match")
			} else {
				KeywordUtil.markFailed("Response status code not match. Expected: " +
						expectedStatusCode + " - Actual: " + response.getStatusCode() )
			}
		} else {
			KeywordUtil.markFailed(request.getObjectId() + " is not a RequestObject")
		}
	}

	/**
	 * Add Header basic authorization field,
	 * this field value is Base64 encoded token from user name and password
	 * @param request object, must be an instance of RequestObject
	 * @param username username
	 * @param password password
	 * @return the original request object with basic authorization header field added
	 */
	@Keyword
	def addBasicAuthorizationProperty(TestObject request, String username, String password) {
		if (request instanceof RequestObject) {
			String authorizationValue = username + ":" + password
			authorizationValue = "Basic " + authorizationValue.bytes.encodeBase64().toString()

			// Find available basic authorization field and change its value to the new one, if any
			List<TestObjectProperty> headerProperties = request.getHttpHeaderProperties()
			boolean fieldExist = false
			for (int i = 0; i < headerProperties.size(); i++) {
				TestObjectProperty headerField = headerProperties.get(i)
				if (headerField.getName().equals('Authorization')) {
					KeywordUtil.logInfo("Found existent basic authorization field. Replacing its value.")
					headerField.setValue(authorizationValue)
					fieldExist = true
					break
				}
			}

			if (!fieldExist) {
				TestObjectProperty authorizationProperty = new TestObjectProperty("Authorization",
						ConditionType.EQUALS, authorizationValue, true)
				headerProperties.add(authorizationProperty)
			}
			KeywordUtil.markPassed("Basic authorization field has been added to request header")
		} else {
			KeywordUtil.markFailed(request.getObjectId() + "is not a RequestObject")
		}
		return request
	}

	/**
	 * Add Global Variable at Runtime,
	 * @param name, variable name. Ex: "var_ObjectCount"
	 * @param value 
	 */
	@Keyword
	static void addGlobalVariable(String name, def value) {
		GroovyShell shell1 = new GroovyShell()
		MetaClass mc = shell1.evaluate("internal.GlobalVariable").metaClass
		String getterName = "get" + name.capitalize()
		mc.'static'."$getterName" = { -> return value }
		mc.'static'."$name" = value

		//		.then invoke it and verify:
		//
		//		CustomKeywords.'com.becelever.util.GlobalVariableUtils.addGlobalVariable'('localURL', 'katalon.com')
		//
		//		println(GlobalVariable.localURL)
		//		println(GlobalVariable.getLocalURL())

	}

	/**
	 * Generate Current TimeStamp at Runtime,
	 * @param dateFormat, Ex: "dd-MM-yyyy"
	 * @return generated Current TimeStamp to the given time format
	 */
	public static String getCurrentTimeStamp(String dateFormat) {
		SimpleDateFormat formDate = new SimpleDateFormat(dateFormat);

		// String strDate = formDate.format(System.currentTimeMillis()); // option 1
		String strDate = formDate.format(new Date()); // option 2
		return strDate;
	}

	public static final String xmlFilePath = System.getProperty("user.dir")+"\\xmlfile.xml";

	/**
	 * Creat XML File
	 * @author Kavindu Dilshan
	 */
	@Keyword
	public void createXML() {
		try {

			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

			Document document = documentBuilder.newDocument();

			// root element
			Element root = document.createElement("Result");
			document.appendChild(root);
			Attr attr = document.createAttribute("documentVersion");
			attr.setValue("1.0");
			root.setAttributeNode(attr);

			Element ToolName = document.createElement("ToolName");
			ToolName.appendChild(document.createTextNode("Katalon UI Test"));
			root.appendChild(ToolName);

			Element UtcEndTime = document.createElement("UtcEndTime");
			UtcEndTime.appendChild(document.createTextNode("Utc End Time"));
			root.appendChild(UtcEndTime);

			Element Scope = document.createElement("Scope");
			Scope.appendChild(document.createTextNode("High Level"));
			root.appendChild(Scope);

			Element Components = document.createElement("Components");
			root.appendChild(Components);

			// create the xml file
			//transform the DOM Object to an XML File
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(new File(xmlFilePath));

			// If you use
			// StreamResult result = new StreamResult(System.out);
			// the output will be pushed to the standard output ...
			// You can use that for debugging

			transformer.transform(domSource, streamResult);

			System.out.println("Done creating XML File");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}

	/**
	 * Creat XML File
	 * @author Kavindu Dilshan
	 */
	@Keyword
	public void createNodes() {
		println 'Test xmlFilePath:' +xmlFilePath
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory
				.newDocumentBuilder();
		Document document = documentBuilder.parse(xmlFilePath);
		Element root = document.getDocumentElement();
		Element rootElement = document.getDocumentElement();

		Collection<ComponentDetails> svr = new ArrayList<ComponentDetails>();
		svr.add(new ComponentDetails());
		
		for (ComponentDetails i : svr) {

			Node Components = document.getElementsByTagName("Components").item(0);
			Element Component = document.createElement("Component");
			Components.appendChild(Component);

			Element ComponentName = document.createElement("ComponentName");
			ComponentName.appendChild(document.createTextNode(i.getComponentName()));
			Component.appendChild(ComponentName);
				
			Element Total = document.createElement("Total");
			Total.appendChild(document.createTextNode(Integer.toString(i.getTotal())));
			Component.appendChild(Total);

			Element Passed = document.createElement("Passed");
			Passed.appendChild(document.createTextNode(Integer.toString(i.getPassed())));
			Component.appendChild(Passed);

			Element Failed = document.createElement("Failed");
			Failed.appendChild(document.createTextNode(Integer.toString(i.getFailed())));
			Component.appendChild(Failed);

			Element Incomplete = document.createElement("Incomplete");
			Incomplete.appendChild(document.createTextNode(Integer.toString(i.getIncomplete())));
			Component.appendChild(Incomplete);

			Element Skipped = document.createElement("Skipped");
			Skipped.appendChild(document.createTextNode(Integer.toString(i.getSkipped())));
			Component.appendChild(Skipped);

			Element Duration = document.createElement("Duration");
			Duration.appendChild(document.createTextNode(i.getDuration()));
			Component.appendChild(Duration);

			Element LastExecutionTime = document.createElement("LastExecutionTime");
			LastExecutionTime.appendChild(document.createTextNode(i.getLastExecutionTime()));
			Component.appendChild(LastExecutionTime);

			Element FrameworkVersion = document.createElement("FrameworkVersion");
			FrameworkVersion.appendChild(document.createTextNode(i.getFrameworkVersion()));
			Component.appendChild(FrameworkVersion);

			Element OperatingSystem = document.createElement("OperatingSystem");
			OperatingSystem.appendChild(document.createTextNode(i.getOperatingSystem()));
			Component.appendChild(OperatingSystem);

			Element Browser = document.createElement("Browser");
			Browser.appendChild(document.createTextNode(i.getBrowser()));
			Component.appendChild(Browser);

			root.appendChild(Components);
		}

		DOMSource source = new DOMSource(document);

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		StreamResult result = new StreamResult(xmlFilePath);
		transformer.transform(source, result);
	}

	class ComponentDetails {

		public String getComponentName(){
			return GlobalVariable.componentName;
		}

		public Integer getTotal() {
			return 12345;
		}

		public Integer getPassed() {
			return GlobalVariable.numOfPasses;
		}

		public Integer getFailed() {
			return GlobalVariable.numOfFails;
		}

		public Integer getIncomplete(){
			return GlobalVariable.numofIncomplete;
		}

		public Integer getSkipped() {
			return 12345;
		}

		public String getDuration() {
			return GlobalVariable.duration;
		}

		public String getLastExecutionTime() {
			return GlobalVariable.lastExecutionTime;
		}

		public String getFrameworkVersion() {
			return GlobalVariable.frameworkVersion;
		}

		public String getOperatingSystem() {
			return GlobalVariable.operatingSystem;
		}

		public String getBrowser() {
			return GlobalVariable.browser;
		}

	}
	
	/**
	 * get last execution time
	 */
	@Keyword
	public void getLastExecutionTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));
		
		KeywordLogger log = new KeywordLogger()
		log.logInfo("LastExecutionTime = " + dtf.format(now).toString())
		
		GlobalVariable.lastExecutionTime = dtf.format(now).toString();		
	}
	
	
	/**
	 * get Start execution time
	 */
	@Keyword
	public void getStartExecutionTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));
		
		KeywordLogger log = new KeywordLogger()
		log.logInfo("StartExecutionTime = " + dtf.format(now).toString())
		
		GlobalVariable.startExecutionTime = dtf.format(now).toString();
	}
	
	/**
	 * Calculate Test Suite Execution Duration
	 */
	@Keyword
	public void getDurationInSeconds() {
		//SimpleDateFormat  dtf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		LocalDateTime now = LocalDateTime.now();
//		System.out.println(dtf.format(now));
		
		Date d1 = sdf.parse(GlobalVariable.startExecutionTime);
		Date d2 = sdf.parse(GlobalVariable.lastExecutionTime);
		
		long difference_In_Time = d2.getTime() - d1.getTime();
		long difference_In_Seconds = (difference_In_Time/ 1000)% 60;
		
		GlobalVariable.duration = difference_In_Seconds.toString();
	}

}