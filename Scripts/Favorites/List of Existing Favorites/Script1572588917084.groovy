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

def response = WS.sendRequest(findTestObject('Favorites/List of Existing Favorites'))

WS.verifyResponseStatusCode(response, 200)

def responseBodyConent = response.getResponseBodyContent()

JsonSlurper jsParser = new JsonSlurper()

def responseBodyConentJSN = jsParser.parseText(responseBodyConent)

String[] fav = responseBodyConentJSN.favorite;

String[] url = responseBodyConentJSN.thumbnail_url;

String[] type = responseBodyConentJSN.item_type;

String[] durl = responseBodyConentJSN.download_url;


for(i=0; i<fav.length; i++)
{
	WS.verifyEqual(fav[i],'true')
	
	WS.verifyNotEqual(url[i],null)
	
	WS.verifyNotEqual(type[i],null)
	
	WS.verifyNotEqual(durl[i],null)
	
	println "favorite is " +fav[i] + " with the thumbnail Id " + url[i] + "with item type" + type[i] + "with download url"+durl[i];
}


	
	


