package com.arabam.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected WebDriverWait longWait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.longWait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    protected WebElement waitForElement(By locator) {
        try {
            Thread.sleep(2000); // Wait for page to stabilize
            return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    protected WebElement waitForClickableElement(By locator) {
        try {
            Thread.sleep(2000); // Wait for page to stabilize
            return wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    protected void clickElement(By locator) {
        try {
            waitForOverlaysToDisappear();
            // First try normal click
            WebElement element = waitForClickableElement(locator);
            element.click();
        } catch (ElementClickInterceptedException e) {
            // If normal click fails, try JavaScript click
            WebElement element = waitForElement(locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    protected void sendKeys(By locator, String text) {
        waitForElement(locator).sendKeys(text);
    }

    protected boolean isElementPresent(By locator) {
        try {
            waitForElement(locator);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    protected void waitForOverlaysToDisappear() {
        try {
            Thread.sleep(2000); // Wait for overlays to appear
            By overlayLocator = By.cssSelector("#ins-frameless-overlay, .modal-backdrop, .loading-overlay");
            longWait.until(ExpectedConditions.invisibilityOfElementLocated(overlayLocator));
        } catch (TimeoutException | InterruptedException ignored) {
            // If overlay is not present or already gone, continue
            if (ignored instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
