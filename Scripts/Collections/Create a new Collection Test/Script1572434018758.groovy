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

def collectionName = CustomKeywords.'lib.Lib.getRandomString'(10) 

println "Random collectionName: " + collectionName

response = WS.sendRequest(findTestObject('Collections/CreateNewCollection', ["collectionName": collectionName]))

WS.verifyResponseStatusCode(response, 200)

def responseBodyConent = response.getResponseBodyContent()

JsonSlurper jsParser = new JsonSlurper()

def responseBodyConentJSN = jsParser.parseText(responseBodyConent) 

WS.verifyEqual(responseBodyConentJSN.metadata.message, "Collection created successfully")

/// Verify updated Collection Name
WS.verifyEqual(responseBodyConentJSN.name, collectionName) 

WS.verifyEqual(responseBodyConentJSN.email, GlobalVariable.username)

/// Verify Shared URL
def expectedCollectionSharedURL = GlobalVariable.url + "/collections/" + responseBodyConentJSN.id + "/" +collectionName
WS.verifyEqual(responseBodyConentJSN.share_url, expectedCollectionSharedURL)

/// Verify item count in the collecton 
WS.verifyEqual(responseBodyConentJSN.items_count, "0")
	
/// Verify security.sharing setting in the collecton
WS.verifyEqual(responseBodyConentJSN.security.sharing, "un")

/// Verify collection id is not null
WS.verifyNotEqual(responseBodyConentJSN.id, null)

return responseBodyConentJSN.id


