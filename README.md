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
* [Simple Example Test](#example)
	* [1) Create a base API](#1)
	* [2) Write an Actual Test](#2)
	* [3) Extent HTML report](#3)


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
> `src/main/resources/configuration/constants.properties` is a simple config properties file to store various config's like application URL, DB & credential details
> `src/main/resources/configuration/constants.properties` is a simple constants properties file to store various constants like Api related details

# Packages<a name="package"></a>
#### Main Package<a name="main"></a>
> `src/main/java/` is the core package of Framework and it has various sub-packages dedicatedly for various functionalities like `browser capabilities`,`all utils package`,`controllers`,`listeners`,`page`,`pojoClasses`,`ApiUtils`,SeleniumUtils,. All sub-package details are mentioned in the following section.
> `src/main/resources/` is the core package of Framework and it has various sub-packages dedicatedly for configuration with all properties are available this packge

#### Test Package<a name="test"></a>
> `src/test/java/` is the actual test package and the sub package `tests` holds all test classes (TestNG) related to multiple application like [ Efrom ,Orange Hrm , Snapdeal ] 
> * `OrangeHRM​ (OrangeHRM is a human resources management system designed to streamline and simplify HR processes)​`
> * `E-Forms​ (E-Forms is an Zensar Technologies Internal platform, where the end users will request for the software/Hardware requirements)​`
> * `Snapdeal​ (Snapdeal is an e-commerce website, where end users will purchase goods online at their comfort)`
> * `ApiWorkflowTest (it's validate all kind of api calls with post,get,put,delete methods)`

>  `src/test/resources/` 
> * `mentioned all json files, excel files, xml files in the test resources folder`

#### Reports<a name="reports"></a>
> With the Extent library, we can create interactive and detailed reports for our API test results. We can add events, tags, devices, authors or any other relevant information we decide is essential to create an informative and stunning report. Test Reports can be found in `workingDir/Reports/ExtentReport.html`


