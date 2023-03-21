package com.selenium.cdp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    protected static WebDriver driver;

    @BeforeTest(alwaysRun = true)
    protected void setup() {
        driver = new ChromeDriver();
    }

    public static WebDriver getDriver() {
        return driver;
    }

    @AfterTest(alwaysRun = true)
    protected void tearDown() {
        //driver.quit();
    }
}

