# Arabam.com Test Automation

This project contains automated tests for arabam.com website using Cucumber framework with Java and Selenium WebDriver.

## Project Structure
- Page Object Model (POM) design pattern
- Cucumber for BDD
- Selenium WebDriver for browser automation
- Maven for dependency management

## Prerequisites
- Java 11 or higher
- Maven
- Chrome or Firefox browser

## How to Run Tests
1. Clone the repository
2. Navigate to project directory
3. Run tests using Maven:
   ```bash
   mvn clean test
   ```

## Features
- Homepage navigation
- Cookie acceptance
- Vehicle search functionality

## Test Reports
After running the tests, you can find the reports in:
- HTML Report: `target/cucumber-reports/cucumber.html`
- JSON Report: `target/cucumber-reports/cucumber.json`
