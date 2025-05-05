package com.arabam.stepdefinitions;

import com.arabam.pages.HomePage;
import com.arabam.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import static org.junit.Assert.assertTrue;

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
        // Wait for cookies notification
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Then("user accepts cookies")
    public void userAcceptsCookies() {
        homePage.acceptCookiesIfPresent();
    }

    @When("user searches for {string}")
    public void userSearchesFor(String searchText) {
        homePage.searchVehicle(searchText);
    }

    @When("user clicks on Otomobil section")
    public void userClicksOnOtomobilSection() {
        homePage.clickOtomobilSection();
    }

    @When("user clicks on Karavan section")
    public void userClicksOnKaravanSection() {
        homePage.clickKaravanSection();
    }

    @Then("search results should be displayed")
    public void searchResultsShouldBeDisplayed() {
        // Add verification logic here
    }

    @When("user clicks on tractor section")
    public void userClicksOnTractorSection() {
        homePage.clickTractorSection();
    }

    @Then("tractor listings should be displayed")
    public void tractorListingsShouldBeDisplayed() {
        assertTrue(homePage.isTractorListingsDisplayed());
    }

    @Then("otomobil listings should be displayed")
    public void otomobilListingsShouldBeDisplayed() {
        assertTrue(homePage.isOtomobilListingsDisplayed());
    }

    @Then("karavan listings should be displayed")
    public void karavanListingsShouldBeDisplayed() {
        assertTrue(homePage.isKaravanListingsDisplayed());
    }
}
