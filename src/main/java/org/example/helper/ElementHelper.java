package org.example.helper;

import org.example.base.ConfigFileReader;
import org.example.base.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.List;

public class ElementHelper {

    protected int defaultElementTimeout = Integer.parseInt(ConfigFileReader.getInstance().getProperty("DEFAULT_TIMEOUT_ELEMENT_IN_SECONDS"));
    protected int waitElementFiveSeconds = Integer.parseInt(ConfigFileReader.getInstance().getProperty("DEFAULT_WAIT_ELEMENT_IN_SECONDS"));

    protected WebDriver driver = DriverFactory.getDriver();

    public ElementHelper() throws MalformedURLException {
    }


    public WebElement waitPresenceOfElement(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public WebElement waitVisibilityOfElement(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement findElementById(String id) {
        return driver.findElement(By.id(id));
    }

    public WebElement findElementByClassName(String className) {
        return driver.findElement(By.className(className));
    }

    public WebElement findElementByTagName(String tagName) {
        return driver.findElement(By.tagName(tagName));
    }

    public List<WebElement> findElementsByClassName(String className) {
        return driver.findElements(By.className(className));
    }

    public void clickOn(By locator, int timeoutInSeconds){
        WebElement element = waitPresenceOfElement(locator, timeoutInSeconds);
        element.click();
    }

    public void sendKeysOn(By locator, int timeoutInSeconds, String inputText){
        WebElement element = waitPresenceOfElement(locator, timeoutInSeconds);
        element.sendKeys(inputText);
    }
}
