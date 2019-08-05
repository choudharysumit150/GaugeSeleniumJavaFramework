# GaugeSeleniumJavaFramework
This is a test automation framework developed for the problem statement given by Nintex for writing test cases for the below scenario.

Hybrid Automation Framework
This is a test automation framework developed for the problem statement given by Nintex for writing test cases for the below scenario.
Scenario
The trainline.com website (https://www.thetrainline.com) has implemented a feature to enable one-way train booking. As a user, I would like to be able to search for one way tickets from one city to another so that I can evaluate the departure time and also ticket prices.

About Framework
 I have designed a Hybrid Automation Framework to test the above scenario to design this framework I am using Thoughworks Gauge + Selenium WebDriver libraries.

Prerequisites 
•	Install Java
•	Configure Maven
•	Install Gauge 
•	Install Gauge plugins
	Java
	html-report
	screenshot

Installation
1.	Install Gauge
Download the zip installer and the run following command in powershell.
PS> Expand-Archive -Path gauge-1.0.0-windows.x86_64.zip -DestinationPath custom_path
Make sure Gauge added in $PATH
2.	Install Gauge plugin
•	gauge install java
•	gauge install html-report
•	gauge install screenshot
For any help on installation please refer link

Running the tests
All test cases are under the specs folder.
Run below commands to execute all specification in specs directory.
mvn test -P{profile} -DspecsDir=specs{optional} -Denv={chrome/firefox/ie}{optional}
Launch using Chrome(default option) with minimum options
mvn test –Pdev
Launch using Firefox
mvn test -Pdev -Denv=firefox -DspecsDir=specs
Launch using IE
mvn test -Pdev -Denv=ie -DspecsDir=specs
**I have only tested the scripts on Chrome.
Execute specs In parallel
mvn test -Pdev -DspecsDir=specs -DinParallel=true -Dnodes=<No, of Nodes>
Execute specifications by tags
mvn test -Pdev -DspecsDir=specs -Dtags="logIn"

Framework details:
•	Specs (Specifications): In Gauge, we write our test cases as .spec files, I have created 2 test cases(spec):
1. TrainLineHomePage.spec
•	Verifying the mandatory fields of Search form
•	Verifying the visibility logic and functionality of Swap location button
•	Verifying the Past date should not be allowed
•	Verifying the minimum and maximum no of passengers allowed
•	Verifying that age of child should be greater than 4 if travelling single
•	Getting broken links information if any on Home Page

2.	TrainLineSearchResults.spec
•	Verify user able to perform Search and validating the search results
•	Getting broken links information if any on Search Result page



•	 Repository:  
Instead of using typical POM for designing framework. I am maintaining objects, divided by pages in repository.xml

•	Drivers :  
Driver folder contains DriverFactory. It allows us to test application with different browsers like Chrome, Firefox and IE. However, I have tested my scripts only with Chrome.

User can pass browser using -Denv={browser_name} property and configure driver in /env/{browser_name}/browser.properties folder.

•	TestData :  
I have separated my test data from the scripts to make them more re-usable.
TestData is available under src/test/resources
•	Reports :  
Html reports are available in report folder
All failed test steps will contain snapshot of the failure
 

•	Logs :  
Generating custom logs under hf_logs/ folders with below 3 logs.
	testhfInfo.log: Contains Info logs
	testhfRegression.log: Contains Debug logs
	testhfError.log: Contains Error, Warn and Fatal
Author
Sumit Choudhary








