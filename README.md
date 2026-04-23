# Cura Healthcare Selenium Suite 
A test automation project using Selenium WebDriver to validate the [Cura Healthcare Service](https://katalon-demo-cura.herokuapp.com/) platform.

## 🛠 Tools
- **Language:** Java
- **Framework:** 
  - *Browser Automation:* Selenium WebDriver
  - *Test Runner:* TestNG
- **Build Automation System:** Maven
- **CI/CD:** Github

## 🖧 Architecture & Design
### Page Object Model (POM)
This project is structured with [Page Object Models (POM)](https://www.selenium.dev/documentation/test_practices/encouraged/page_object_models/)
to separate test logic from page-locator. Each page represent by a Java class for a better maintainability and reduced code duplication.

### Wait Strategies
Each page inherits the custom ***WaitAction*** base class which contain several actions: 
- ```click()```:
  Clicks at the current mouse location.
- ```sendKeys()```: Sends keys to the active element.
- ```getText()```: Get the visible text of a web element.

Each action above wrapped in an [Expected Conditions](https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/support/ui/ExpectedConditions.html) check.

This ensures that elements are clickable or visible before the driver attempts an action, reducing ```NoSuchElementException```
or ```ElementNotInteractableException``` errors.

## 📥 Installation & Setup
### **Prerequisites**
- **Java 25 LTS:** [Download here](https://www.oracle.com/java/technologies/downloads/) (Check version with ```java -version```) 
- **Maven 3.9+:** [Download & Setup Guide](https://maven.apache.org/install.html) (Check version with ```mvn -v```)
- **Google Chrome:** (Latest version)

### **Project Setup**
1. Clone the repository
    ```bash
    git clone https://github.com/yourusername/CuraHealthcareSeleniumProject.git
    cd CuraHealthcareSeleniumProject
2. Verify your environment
    ```bash
    java -version
    mvn -version
3. Install project dependencies
    ```bash
    mvn clean install -DskipTests

## 🚀 Running the Automation
You can trigger the test suite directly from the terminal. 
The project is configured to use the testng.xml file for execution logic.

- Run all tests:
    ```bash
    mvn test
    ```
- Run a specific test class:
    ```bash
    mvn test -Dtest=ClassName