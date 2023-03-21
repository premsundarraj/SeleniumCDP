package com.selenium.cdp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v110.fetch.Fetch;
import org.openqa.selenium.devtools.v110.fetch.model.RequestPattern;

import org.openqa.selenium.devtools.v110.fetch.model.RequestStage;
import org.openqa.selenium.devtools.v110.network.Network;
import org.openqa.selenium.devtools.v110.network.model.ResourceType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NetworkIntercept {
    public static DevTools devTool;

    public static void createDevToolSession() {
        WebDriver driver = BaseTest.getDriver();
        devTool = ((ChromeDriver) driver).getDevTools();
        devTool.createSession();
    }

    public static void enableFetch() {
        List<RequestPattern> patterns = new ArrayList<>();
        RequestPattern responsePattern = new RequestPattern(Optional.empty(),Optional.of(ResourceType.XHR),Optional.empty());
        patterns.add(responsePattern);

        devTool.send(Fetch.enable(Optional.of(patterns), Optional.empty()));
    }

    public static void enableNetwork() {
        devTool.send(Network.enable(Optional.empty(),Optional.empty(), Optional.empty()));
    }


}
