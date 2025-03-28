package com.arabam.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    // Locators
    private final By acceptCookiesButton = By.id("onetrust-accept-btn-handler");
    private final By searchBox = By.cssSelector("input[placeholder='Kelime, galeri ya da ilan no ile ara']");
    private final By searchButton = By.cssSelector("button.search-button");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void navigateToHomePage() {
        driver.get("https://www.arabam.com");
    }

    public void acceptCookiesIfPresent() {
        if (isElementPresent(acceptCookiesButton)) {
            clickElement(acceptCookiesButton);
        }
    }

    public void searchVehicle(String searchText) {
        sendKeys(searchBox, searchText);
        clickElement(searchButton);
    }
}
