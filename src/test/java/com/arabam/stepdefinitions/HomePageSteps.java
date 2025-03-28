package com.arabam.stepdefinitions;

import com.arabam.pages.HomePage;
import com.arabam.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

public class HomePageSteps {
    private WebDriver driver;
    private HomePage homePage;

    @Before
    public void setup() {
        driver = Driver.getDriver();
        homePage = new HomePage(driver);
    }

    @After
    public void tearDown() {
        Driver.closeDriver();
    }

    @Given("user is on the arabam.com homepage")
    public void userIsOnTheArabamComHomepage() {
        homePage.navigateToHomePage();
    }

    @When("cookies notification appears")
    public void cookiesNotificationAppears() {
        // This step is handled in the next step
    }

    @Then("user accepts cookies")
    public void userAcceptsCookies() {
        homePage.acceptCookiesIfPresent();
    }

    @When("user searches for {string}")
    public void userSearchesFor(String searchText) {
        homePage.searchVehicle(searchText);
    }

    @Then("search results should be displayed")
    public void searchResultsShouldBeDisplayed() {
        // Add verification logic here
    }
}
