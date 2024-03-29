# Hybrid with RestAssured API Testing Automation Framework
This Test Automation Framework is created using Java + Selenium Web Driver + TestNG + RestAssured. Maven is used for dependency management and continuous development, it uses RestAssured -API testing library, TestNG - Third-party free library for Running tests, and for Reporting, Extent Reports (library for interactive and detailed reports for tests). This framework can be used for any web & Restful application to create Web Scenario's test and API tests as well.


# Contents
* [Framework Details](#FrameworkDetails)
	* [Prerequisites](#prerequisites)   
	* [Framework - What and Why?](#ww)
	* [Project structure](#structure)
	* [Properties](#properties)
* [Packages](#package)
	* [Main Package](#main)
	* [Test Package](#test)
	* [Reports](#reports)


# Framework Details<a name="FrameworkDetails"></a>
#### Prerequisites<a name="prerequisites"></a>
> *   Java jdk-1.8 or higher
> *   Apache Maven 3 or higher
> *   IDE for execution (Eclipse or Intellij)

#### Framework - What and Why?<a name="ww"></a>
> it can be used across different web based applications.
In this approach, the endeavor is to build a lot of applications independent reusable keyword components so that they can directly used for another web application without spending any extra effort.
With this framework in place, whenever we need to automate a web based application, we would not need to start from scratch, but use the application independent keyword components to the extent possible and create application specific components for the specific needs.
      
#### Project structure<a name="structure"></a>
> This project uses a standard Maven Java project with standard java folder structure and POM.xml

#### Properties<a name="properties"></a>
> * `src/main/resources/configuration/config.properties` is a simple config properties file to store various config's like application URL, DB & credential details.
>  * `src/main/resources/configuration/constants.properties` is a simple constants properties file to store various constants like Api related details.

# Packages<a name="package"></a>
#### Main Package<a name="main"></a>
> `src/main/java/` 
>  * this is the core package of Framework and it has various sub-packages dedicatedly for various functionalities like browser capabilities, controllers, listeners, page, pojoClasses, ApiUtils, SeleniumUtils. All sub-package details are mentioned in the following section.

> `src/main/resources/` 
> * this is the core package of Framework and it has various sub-packages dedicatedly for configuration with all properties are available this package`

#### Test Package<a name="test"></a>
> `src/test/java/` is the actual test package and the sub package `tests` holds all test classes (TestNG) related to multiple application like Efrom ,Orange Hrm ,Snapdeal.
> * OrangeHRM (OrangeHRM is a human resources management system designed to streamline and simplify HR processes)
> * E-Forms (E-Forms is an Zensar Technologies Internal platform, where the end users will request for the software/Hardware requirements)
> * Snapdeal (Snapdeal is an e-commerce website, where end users will purchase goods online at their comfort)
> * ApiWorkflowTest (it's validate all kind of api calls with post,get,put,delete methods)

>  `src/test/resources/` 
> * mentioned all json files, excel files, xml files in the test resources folder

#### Reports<a name="reports"></a>
> With the Extent library, we can create interactive and detailed reports for our API test results. We can add events, tags, devices, authors or any other relevant information we decide is essential to create an informative and stunning report. Test Reports can be found in `workingDir/Reports/ExtentReport.html`

## Main Package<a name="main"></a>
```
src/main/java
- basicApi
	 - Auth.java
	 - PlacesApi.java
- BrowserFactory
	- BrowserCapabilities.Java
	- DriverFactory
- controllers
	- ExcelDataProvider.java
	- MysqlDataProvider.java
	- SqlServerDataProvider.java
	- OracleDataProvider.java
- listeners
	- ExtentionUtils.java
	- ExtentManager.java
	- ExtentReporter.java
- page
	- AddEmployeeForHrmPage.Java
	- EformPrivilegeRequestPage.java
	- EformAdditionSoftwareRequestPage.java
	- EformAdditionSoftwareRequestPageJson.java
	- LoginEformPage.java
	- LoginForHrmPage.java
	- SnapdealScenarios.java
	- ViewStatusPage.java
- pojoClasses
	- Addplace.java
	- Location
- Utils
	- api
	    - APIResources.java
	    - ApiUtils.java
            - Helper.java
	    - HttpOperation.java
	    - ValidatorOperation.java
        - selenium
	    - CommonAction.java
	    - DBConnectionBuilder.java
            - DButils.java
            - ExcelReader.java
	    - ExcelUtils.java
	    - ExplicitWaiting.java
	    - JsonRead.java
	    - Log.java
	    - Robot
	    - XmlReader

src/main/resources
- configuration
   - config.properties
   - Constants.properties
- log4j.properties   

```
### 1. basicApi
####  Auth.class
> in this class ,we are genereated access token through Api utils
####  PlacesApi.class
> validated Ecommerce api calls like post,get,put,delete etc with respective rest-assured methodology.
```
```
### 2. BrowserFactory
####  BrowserCapabilities.class
> in this class that is used to set basic properties of browsers such as browser name, browser version, operating systems etc & Every Internet browser has settings you can change, including privacy options, security settings, search engine preferences, autofill and autocomplete behavior, and more. To access your Internet browser settings this option object will be useful.
####  DriverFactory.class
> this class is help to launch respective browser it would be on init_driver() method with user decision.
```
```
### 3. controllers
####  ExcelDataProvider.class
> this class supports to run with excel test data on Parallel DataProvider, remaining classes support DB with data provider.
```
```
### 4. listeners
#### ExtentManager Class:
> In this class, we created an `ExtentReports` object and it can be reachable via `createInstance()` method. Also, we need to set ExtentReports report HTML file location.

#### ExtentReporter
> In order to listen to test events such as passed, failed, skipped, etc. we have TestListener class which implements ITestListener.

#### ITestListener
By using ITestListener, we can change the default behaviour of your test by adding different events to the methods. It also defines a new way of logging or reporting.
The following are some methods provided by this interface:
**onStart:** This method is invoked before any test method gets executed. This can be used to get the directory from where the tests are running.

**onFinish:** This method is invoked after all tests methods gets executed. This can be used to store information of all the tests that were run.

**onTestStart:** This method is invoked before any test methods are invoked. This can be used to indicate that the particular test method has been started.

**onTestSkipped:** This method is invoked when each test method is skipped. This can be used to indicate that the particular test method has been skipped.

**onTestSuccess:** This method is invoked when any test method succeeds. This can be used to indicate that the particular test method has successfully finished its execution.

**onTestFailure:** This method is invoked when any test method fails. This can be used to indicate that the particular test method has failed. You can create an event for taking a screenshot which will show where the test has been failed.
```
```
### 5. page
> classes are supports page object model with pageFactory where we can find locators we put here and writed some automated actions
```
```
### 6. pojoClasses
>The POJO class is created to use the objects in other Java Programs. The major advantage of the POJO class is that we will not have to create objects every time in other Java programs. Simply we can access the objects by using the get() and set() methods. we are implemented this approach on AddPlace.class
```
```
### 7.Utils 
#### Utils.api
#### HttpOperation.java
 > Its a Java Enum type, which is implemented to have a set of different HttpOperation constants. Which can be used to create, read, update, and delete (or CRUD) operations, respectively.

#### ValidatorOperation.java
> Its a Java Enum type, which is implemented to have a set of different ValidatorOperation constants. Which can be used in response json assertions.

#### APIResources.java
> Its a Java Enum type, which is implemented to have a set of different end point urls constants. Which can be used in parse end point url.

#### ApiUtils.java
> Its a Java parameter concept type, which is implemented to have a set of different action. Which can be used hole api action and help of this writeing less hands on code  more  it's felxiable for developers.

#### Utils.Selenium
> under this package all Selenium,excel,Db,Json,Xml,logs,synchronization utils are availble where it will be useful just extend the class either create object and passing it.


## Test Package<a name="test"></a>
```
src/test/java
- tests
     - EfromAdditionSoftwareRequestTest.class
     - EfromAdditionSoftwareRequestTestJson.class
     - EfromLogonMultipleSetOfDataTest.class
     - EfromPrivilegeRequestTest.class
     - EfromViewStatusTest.class
     - PimHrmDbTestCases.class
     - SnapdealScenariosTest.class
     - XmlTest.class
     
- WorkflowApi
     - ApiValidateTest.class
	   

src/test/resources
- payloads 
    - addplace.json
    - Data.json.
- TestData 
    - document.xlsx
    - EnterCredentialsTest.xlsx
- student.xml 
```
### 1. tests
> in this package ,writed automate testcases on method priority level with testng. we can get data/object/method from main packge and executed
### 2. WorkflowApi
> in this package ,validated all api calls we can get data/object/method from main packge and executed.
```
