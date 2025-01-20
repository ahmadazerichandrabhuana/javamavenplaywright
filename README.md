# Test Automation using Playwroght on Java Maven
This is a simple sample for Test Automation using Playwright on Java Maven.

## Requirements 

1. Install Java, setup the JAVA_HOME, and MAVEN_HOME into your PATH file (Environment Variabels). You can check [this article](https://medium.com/@zorozeri/install-maven-by-setting-up-maven-home-abb4d158fcc6) for reference of setting up JAVA_HOME and MAVEN_HOME manually, or check [this article](https://medium.com/@zorozeri/manage-java-version-using-sdkman-including-maven-gradle-scala-kotlin-and-many-more-82532be9437e) for using SDKMAN! to install anything Java-related automatically.
2. Install any IDE or Code Editor you're comfort with. Since this is Java, for IDE I'll suggest [Intellij IDEA](https://www.jetbrains.com/idea/download). For usual Code Editor, I'll suggest [VS Code](https://code.visualstudio.com/).
3. Download this code repository into your local machine.

## Run Tests
* Run all tests : Open terminal and run this command
   ```
   mvn clean test -DtestSuites=testsuites/test.xml
   ```

* Run specific test : Open file `"/testsuites/test.xml"` and update line 6-8 (comment & un-comment) and run above command again.

## Generate report file and open it :

   ```
   allure generate --clean && allure open
   ```
*  Open report without generating report file : 

   ```
   allure serve
   ```
*  Troubleshoot Allure not recognized on PowerShell Windows :
   - Use Command Prompt instead of PowerShell, or
   - Run this command on PowerShell : 
     ```
     npm install -g allure-commandline --save-dev
     ```
   
## Short Repository Explanation

This sample Test Automation consists of 2 main folders : 

* src
   ```
   Consists of 2 more folders :
   > main   : contains web element for each specific web pages (implementation of Page Object Model patterns)
   > test   : contains test cases
   ```
* testsuites
   ```
   Contains xml file for listing which test cases want to be executed
   ```

Apart from these 3 folders, this sample also using several files on the root folder :=
   ```
   > pom.xml       : Maven's standard XML File for storing project's dependencies
   > config.yaml   : contains any config-related data for this project
   ```

## Playwright on Java Documentation
- Actions
  https://playwright.dev/java/docs/input
- Page Assertions
  https://playwright.dev/java/docs/api/class-pageassertions
- Element Assertions
  https://playwright.dev/java/docs/api/class-locatorassertions
