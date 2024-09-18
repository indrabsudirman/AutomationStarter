package org.example.base;

import io.appium.java_client.android.AndroidDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverFactory() {

    }



    public static WebDriver initializeDriver(String webDriver) throws MalformedURLException {

        switch (webDriver) {
            case "chrome":
                WebDriverManager.chromedriver().setup();

                ChromeOptions chromeOptions = new ChromeOptions();

                chromeOptions.addArguments("--remote-allow-origins=*");
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.addArguments("start-maximized");
                chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                driver.set(new ChromeDriver(chromeOptions));
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();

                EdgeOptions edgeOptions = new EdgeOptions();

                edgeOptions.addArguments("--remote-allow-origins=*");
                edgeOptions.addArguments("--no-sandbox");
                edgeOptions.addArguments("--disable-dev-shm-usage");
                edgeOptions.addArguments("start-maximized");
                edgeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                driver.set(new EdgeDriver(edgeOptions));
                break;
            case "android":
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability("appium:platformName", "Android");
                capabilities.setCapability("appium:deviceName", "0732625232000406");
                capabilities.setCapability("appium:automationName", "uiautomator2");
                capabilities.setCapability("appium:platformVersion", "11");

                capabilities.setCapability("appPackage", "fill_package_name");
                capabilities.setCapability("appActivity", "fill_package_name_then_mainActivity");

                AndroidDriver androidDriver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), capabilities);
                driver.set(androidDriver);
                break;

            default:
                throw new IllegalArgumentException("Invalid browser not in the lists");
        }


        return driver.get();
    }

    public static WebDriver getDriver() throws MalformedURLException {
        if (driver.get() == null) {
            driver.set(initializeDriver(ConfigFileReader.getInstance().getProperty("DRIVER")));
        }
        return driver.get();
    }

    public static void cleanUpThreadLocal() {
        driver.remove();
    }

}
