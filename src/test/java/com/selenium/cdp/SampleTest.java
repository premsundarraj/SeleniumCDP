package com.selenium.cdp;

import org.openqa.selenium.By;

import org.openqa.selenium.devtools.v110.fetch.Fetch;
import org.openqa.selenium.devtools.v110.fetch.model.HeaderEntry;
import org.openqa.selenium.devtools.NetworkInterceptor;


import org.openqa.selenium.devtools.v110.fetch.model.RequestPattern;
import org.openqa.selenium.devtools.v110.fetch.model.RequestStage;
import org.openqa.selenium.devtools.v110.network.model.ResourceType;
import org.openqa.selenium.remote.http.Filter;
import org.openqa.selenium.remote.http.HttpHandler;
import org.openqa.selenium.remote.http.HttpResponse;
import org.openqa.selenium.remote.http.Route;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import static com.selenium.cdp.NetworkIntercept.*;

public class SampleTest extends BaseTest {

    @Test
    public void validateRequestIntercept() throws InterruptedException {
        createDevToolSession();
        enableFetch();

        devTool.addListener(Fetch.requestPaused(), request ->
        {
            if (request.getRequest().getUrl().endsWith("/entries")) {
                String mockURL = request.getRequest().getUrl().replace("entries", "viewcart");
                System.out.println(mockURL);

                devTool.send(Fetch.continueRequest(request.getRequestId(),Optional.of(mockURL),Optional.empty()
                        , Optional.empty(), Optional.empty(),Optional.empty()));
            } else {
                devTool.send(Fetch.continueRequest(request.getRequestId(), Optional.of(request.getRequest().getUrl()), Optional.empty()
                        , Optional.empty(), Optional.empty(),Optional.empty()));
            }

        });
        driver.get("https://www.demoblaze.com/");
        Thread.sleep(1000);
    }

    @Test
    public void validateResponseIntercept() throws InterruptedException, IOException {
        driver.get("https://ngpp-ui-test.ngpp.rch-cdc-axnonprod.kroger.com/contracts");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"KSWUSER\"]")).sendKeys("NGPCM2");
        driver.findElement(By.xpath("//*[@id=\"PWD\"]")).sendKeys("lQlptWFGTs6MilY8kWFY5ZnXjMzf3N");
        driver.findElement(By.xpath("//*[@id=\"loginForm\"]/ul/li[5]/input")).click();
        Thread.sleep(10000);

        createDevToolSession();
        //enableFetch();
        List<RequestPattern> patterns = new ArrayList<>();
        RequestPattern responsePattern = new RequestPattern(Optional.empty(),Optional.of(ResourceType.XHR),Optional.empty());
        patterns.add(responsePattern);


        devTool.send(Fetch.enable(Optional.of(patterns), Optional.empty()));

        devTool.addListener(Fetch.requestPaused(), request ->
        {
            if (request.getRequest().getUrl().contains("promotions-contracts/v1/search?include=contracts") && request.getRequest().getMethod().equalsIgnoreCase("POST")) {
                List<HeaderEntry> headers = new ArrayList<>();
                HeaderEntry header = new HeaderEntry("Content-Type","application/json");
                HeaderEntry header2 = new HeaderEntry("x-kroger-records-affected","10");
                HeaderEntry header3 = new HeaderEntry("Content-Type","application/json");
                HeaderEntry header4 = new HeaderEntry("X-Dns-Prefetch-Control","off");
                HeaderEntry header5 = new HeaderEntry("X-Frame-Options","SAMEORIGIN");
                HeaderEntry header6 = new HeaderEntry("Strict-Transport-Security","max-age=15724800; includeSubDomains");
                HeaderEntry header7 = new HeaderEntry("X-Download-Options","noopen");
                HeaderEntry header8 = new HeaderEntry("Cache-Control","no-store, no-cache, must-revalidate, proxy-revalidate");
                HeaderEntry header9 = new HeaderEntry("Pragma","no-cache");
                HeaderEntry header10 = new HeaderEntry("Expires","0");
                HeaderEntry header11 = new HeaderEntry("X-Content-Type-Options","nosniff");
                HeaderEntry header12 = new HeaderEntry("X-Xss-Protection","1; mode=block");
                HeaderEntry header13 = new HeaderEntry("Vary","Origin");
                HeaderEntry header14 = new HeaderEntry("Access-Control-Allow-Origin","https://ngpp-ui-test.ngpp.rch-cdc-axnonprod.kroger.com");
                HeaderEntry header15 = new HeaderEntry("Access-Control-Expose-Headers","x-kroger-records-affected");
                HeaderEntry header16 = new HeaderEntry("X-Kroger-Records-Affected","0");

                headers.add(header);
                headers.add(header2);
                headers.add(header3);
                headers.add(header4);
                headers.add(header5);
                headers.add(header6);
                headers.add(header7);
                headers.add(header8);
                headers.add(header9);
                headers.add(header10);
                headers.add(header11);
                headers.add(header12);
                headers.add(header13);
                headers.add(header14);
                headers.add(header15);
                headers.add(header16);


                String responseBody = "{\"data\":[{\"contract\":{\"id\":\"c3bd06cc-cf3c-4ccd-a34f-2eef05ad16aa\"},\"divisionCommodities\":[{\"division\":\"223\",\"commodities\":[\"563\"]},{\"division\":\"014\",\"commodities\":[\"563\"]}]},{\"contract\":{\"id\":\"ef07c34f-eaea-4839-869f-c78037d08788\"},\"divisionCommodities\":[{\"division\":\"531\",\"commodities\":[\"563\"]},{\"division\":\"708\",\"commodities\":[\"563\"]},{\"division\":\"014\",\"commodities\":[\"563\"]}]}],\"included\":{\"contracts\":[{\"id\":\"c3bd06cc-cf3c-4ccd-a34f-2eef05ad16aa\",\"date\":{\"start\":\"2021-02-17\",\"end\":\"2023-03-04\"},\"vendor\":\"555222\",\"description\":\"OCT_CTR\",\"performance\":{\"type\":\"EDLC\",\"date\":{\"start\":\"2021-02-17\",\"end\":\"2023-03-04\"}},\"type\":\"OFF_INVOICE\",\"status\":\"KROGER_REVIEW\",\"externalId\":\"2201236\",\"itemInfos\":[{\"item\":{\"gtin\":{\"case\":\"1002430003103\",\"consumer\":\"103\"}},\"priceType\":\"Amount Off\",\"locationInfos\":[{\"location\":{\"type\":\"\",\"number\":\"014\",\"name\":\"\"},\"allowance\":{\"value\":2,\"date\":{\"start\":\"2023-03-14\",\"end\":\"2023-03-14\"}},\"vendorNumber\":\"555222\"}]},{\"item\":{\"gtin\":{\"case\":\"1002430003103\",\"consumer\":\"103\"}},\"priceType\":\"Amount Off\",\"locationInfos\":[{\"location\":{\"type\":\"\",\"number\":\"014\",\"name\":\"\"},\"allowance\":{\"value\":2,\"date\":{\"start\":\"2023-03-14\",\"end\":\"2023-03-14\"}},\"vendorNumber\":\"555222\"}]},{\"item\":{\"gtin\":{\"case\":\"1002430003103\",\"consumer\":\"103\"}},\"priceType\":\"Amount Off\",\"locationInfos\":[{\"location\":{\"type\":\"\",\"number\":\"014\",\"name\":\"\"},\"allowance\":{\"value\":2,\"date\":{\"start\":\"2023-03-14\",\"end\":\"2023-03-14\"}},\"vendorNumber\":\"555222\"}]}],\"versionKey\":\"1edc272b-46a7-68b1-a9fb-cba7e03c09b9\",\"transactionType\":\"per-unit\",\"deliveryType\":\"DSD\"},{\"id\":\"ef07c34f-eaea-4839-869f-c78037d08788\",\"date\":{\"start\":\"2021-06-29\",\"end\":\"2023-02-12\"},\"vendor\":\"555222\",\"description\":\"OCT_CTR\",\"performance\":{\"type\":\"SALES_PLAN\",\"date\":{\"start\":\"2021-06-29\",\"end\":\"2023-02-12\"}},\"type\":\"SBT_OFF_INVOICE\",\"status\":\"KROGER_REVIEW\",\"externalId\":\"2201230\",\"itemInfos\":[{\"item\":{\"gtin\":{\"case\":\"1002430003103\",\"consumer\":\"103\"}},\"priceType\":\"Amount Off\",\"locationInfos\":[{\"location\":{\"type\":\"\",\"number\":\"014\",\"name\":\"\"},\"allowance\":{\"value\":2,\"date\":{\"start\":\"2023-03-14\",\"end\":\"2023-03-14\"}},\"vendorNumber\":\"555222\"}]},{\"item\":{\"gtin\":{\"case\":\"1002430003103\",\"consumer\":\"103\"}},\"priceType\":\"Amount Off\",\"locationInfos\":[{\"location\":{\"type\":\"\",\"number\":\"014\",\"name\":\"\"},\"allowance\":{\"value\":2,\"date\":{\"start\":\"2023-03-14\",\"end\":\"2023-03-14\"}},\"vendorNumber\":\"555222\"}]},{\"item\":{\"gtin\":{\"case\":\"1002430003103\",\"consumer\":\"103\"}},\"priceType\":\"Amount Off\",\"locationInfos\":[{\"location\":{\"type\":\"\",\"number\":\"014\",\"name\":\"\"},\"allowance\":{\"value\":2,\"date\":{\"start\":\"2023-03-14\",\"end\":\"2023-03-14\"}},\"vendorNumber\":\"555222\"}]}],\"versionKey\":\"1edc272b-46a7-68ab-a9fb-cba7e03c09b9\",\"transactionType\":\"per-unit\",\"deliveryType\":\"DSD\"}]},\"meta\":{\"page\":{\"offset\":0,\"size\":25}}}";
                String encodedString = Base64.getEncoder().encodeToString(responseBody.getBytes());
                Integer code = 500;

                devTool.send(Fetch.fulfillRequest(request.getRequestId(),200,Optional.of(headers),Optional.empty(),Optional.of(encodedString),Optional.of("ok")));
                //devTool.send(Fetch.continueRequest(request.getRequestId(),Optional.of(request.getRequest().getUrl()),Optional.empty(),Optional.empty(),Optional.empty(),Optional.of(true)));
            } else {
                String postData= String.valueOf(request.getRequest().getPostData());
                System.out.println("postData: "+postData);
                String encodedData = Base64.getEncoder().encodeToString(postData.getBytes());

                devTool.send(Fetch.continueRequest(request.getRequestId(),Optional.of(request.getRequest().getUrl()),Optional.of(request.getRequest().getMethod()),Optional.of(encodedData),request.getResponseHeaders(),Optional.empty()));
            }
        });

        driver.findElement(By.xpath("//*[@id=\"page\"]/ng-component/contracts-ui-element/lib-contract-list-view/div/div/lib-contracts-sliding-panel/kds-sliding-panel/div/div/div/div[3]/ers-input-text-box/div/div/input")).sendKeys("12");

        driver.findElement(By.xpath("//*[@id=\"page\"]/ng-component/contracts-ui-element/lib-contract-list-view/div/div/lib-contracts-sliding-panel/kds-sliding-panel/div/div/footer/kds-button[2]/button")).click();


        Thread.sleep(2000);
    }
}
