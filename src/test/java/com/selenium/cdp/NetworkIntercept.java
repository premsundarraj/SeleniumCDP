package com.selenium.cdp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.fetch.Fetch;

import java.util.Optional;

public class NetworkIntercept {
    public static DevTools devTool;

    public static void createDevToolSession() {
        WebDriver driver = BaseTest.getDriver();
        devTool = ((ChromeDriver) driver).getDevTools();
        devTool.createSession();
    }

    public static void enableFetch() {
        devTool.send(Fetch.enable(Optional.empty(), Optional.empty()));
    }


}
