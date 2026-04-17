# Cura Healthcare Selenium Project 
A test automation project using Selenium WebDriver.

## 🌐 Test Environment
**URL:** [Cura Healthcare Service](https://katalon-demo-cura.herokuapp.com/)

## 🛠 Tools
- **Framework:** [Selenium WebDriver, TestNG]
- **Language:** [Java]
- **Build Automation System:** [Maven]
- **CI/CD:** [Github]

## 📋 Project Note
This project implement the [Page Object Models (POM)](https://www.selenium.dev/documentation/test_practices/encouraged/page_object_models/) 
to maintain each page locators inside a class. Each page inherits the ***WaitAction*** class which has several actions: e.g., ```click()```, ```sendKeys()``` and ```getText()```. 
Each of the action are using the [Wait Strategies](https://www.selenium.dev/documentation/webdriver/waits/) to ensure each element is in a state to be executed.
The state itself uses [Expected Conditions](https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/support/ui/ExpectedConditions.html) class where the automation waits until the expected condition met before executing the action. 
This approach avoid error caused by element not found which happened because slow loading from the web element.

## 📥 Installation
