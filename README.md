# HybridFramework with RestAssured API Testing Automation Framework
This Test Automation Framework is created using Java + Selenium Web Driver + TestNG + RestAssured. Maven is used for dependency management and continuous development, it uses RestAssured -API testing library, TestNG - Third-party free library for Running tests, and for Reporting, Extent Reports (library for interactive and detailed reports for tests). This framework can be used for any Restful application to create API tests


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
* [Installation](#install)
	* [Steps to follow to setup API automation in local system](#steps)
* [Simple Example Test](#example)
	* [1) Create a base API](#1)
	* [2) Write an Actual Test](#2)
	* [3) Extent HTML report](#3)


# Framework Details<a name="FrameworkDetails"></a>
#### Prerequisites<a name="prerequisites"></a>
> *	Java jdk-1.8 or higher
*	Apache Maven 3 or higher
* IDE for execution (Eclipse or Intellij)

#### Framework - What and Why?<a name="ww"></a>
> it can be used across different web based applications.
In this approach, the endeavor is to build a lot of applications independent reusable keyword components so that they can directly used for another web application without spending any extra effort.
With this framework in place, whenever we need to automate a web based application, we would not need to start from scratch, but use the application independent keyword components to the extent possible and create application specific components for the specific needs.
      
#### Project structure<a name="structure"></a>
> This project uses a standard Maven Java project with standard java folder structure and POM.xml

#### Properties<a name="properties"></a>
> `src/main/resources/constants.properties` is a simple constants properties file to store various constants like application URL, DB & SSH details




