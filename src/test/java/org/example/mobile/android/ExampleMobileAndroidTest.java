package org.example.mobile.android;


import io.qameta.allure.*;
import org.example.base.BaseTest;
import org.example.base.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

import static io.qameta.allure.SeverityLevel.CRITICAL;

public class ExampleMobileAndroidTest  extends BaseTest {

    private WebDriver driver;


    @BeforeClass
    public void initialize() throws MalformedURLException {
        driver = DriverFactory.getDriver();
    }

    @Test
    @Description("Description Test Here")
    @Severity(CRITICAL)
    @Owner("Indra Bayu Sudirman")
    @Link(name = "Website", url = "https://dev.example.com/")
    @Issue("https://jira-ticket.com")
    @TmsLink("https://sample.testrail.io")
    @Story("Story Here")
    public void test_exampleMobileAndroidTest() {

    }
}
