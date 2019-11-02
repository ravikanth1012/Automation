import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import groovy.json.JsonSlurper

def response = WS.sendRequest(findTestObject('Organization/Organizations List'))

WS.verifyResponseStatusCode(response, 200)

def responseBodyConent = response.getResponseBodyContent()

JsonSlurper jsParser = new JsonSlurper()

def responseBodyConentJSN = jsParser.parseText(responseBodyConent)

def ListofOrganizations = responseBodyConentJSN.organizations;

String[] orgNames = ListofOrganizations.name;

String[] orgIds = ListofOrganizations.hashid;

String[] orgPlanNames = ListofOrganizations.plan_name;

for(i=0; i<orgNames.length; i++)
{
	WS.verifyNotEqual(orgNames[i],null);
	
	WS.verifyNotEqual(orgIds[i],null);
	
	WS.verifyNotEqual(orgPlanNames[i], null);
	
	println "Organization is " + orgNames[i] + " with the Organization Id " + orgIds[i] + "with plan" + orgPlanNames[i];

}









