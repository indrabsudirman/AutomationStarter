package org.example.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;

public class BaseTest {

    WebDriver driver = null;

    @BeforeTest
    public WebDriver initDriver() throws MalformedURLException {
        driver = DriverFactory.initializeDriver(ConfigFileReader.getInstance().getProperty("DRIVER"));
        return this.driver;
    }

    @AfterTest
    public void closeDriver() {
        if (this.driver != null) {
            DriverFactory.cleanUpThreadLocal();
            this.driver.quit();
        }
    }
}
