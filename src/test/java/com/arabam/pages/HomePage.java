package com.arabam.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {
    // Locators
    private final By acceptCookiesButton = By.id("onetrust-accept-btn-handler");
    private final By searchBox = By.cssSelector("input[type='text'], input.search-input, input[placeholder*='ara']");
    private final By searchButton = By.cssSelector("button.search-button, button[type='submit']");
    private final By tractorSection = By.cssSelector("a[href*='traktor'], a[title*='Traktör'], a:contains('Traktör')");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void navigateToHomePage() {
        driver.get("https://www.arabam.com");
        waitForOverlaysToDisappear();
    }

    public void acceptCookiesIfPresent() {
        waitForOverlaysToDisappear();
        if (isElementPresent(acceptCookiesButton)) {
            clickElement(acceptCookiesButton);
            waitForOverlaysToDisappear();
        }
    }

    public void searchVehicle(String searchText) {
        waitForOverlaysToDisappear();
        sendKeys(searchBox, searchText);
        clickElement(searchButton);
    }

    public void clickTractorSection() {
        waitForOverlaysToDisappear();
        try {
            // Try normal click first
            clickElement(tractorSection);
        } catch (Exception e) {
            // If normal click fails, try JavaScript approach
            JavascriptExecutor js = (JavascriptExecutor) driver;
            // Try different ways to find the tractor link
            String[] jsScripts = {
                "return document.querySelector('a[href*=\"traktor\"]')",
                "return document.querySelector('a[title*=\"Traktör\"]')",
                "return Array.from(document.getElementsByTagName('a')).find(el => el.textContent.includes('Traktör'))",
                "return document.evaluate('//a[contains(text(),\"Traktör\")]', document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue"
            };

            WebElement tractorLink = null;
            for (String script : jsScripts) {
                tractorLink = (WebElement) js.executeScript(script);
                if (tractorLink != null) break;
            }

            if (tractorLink != null) {
                js.executeScript("arguments[0].click();", tractorLink);
            } else {
                throw new RuntimeException("Traktör linki bulunamadı");
            }
        }
    }

    public boolean isTractorListingsDisplayed() {
        return driver.getCurrentUrl().contains("/traktor");
    }
}
