package com.selenium.cdp;

import org.openqa.selenium.devtools.v85.fetch.Fetch;
import org.testng.annotations.Test;

import java.util.Optional;

import static com.selenium.cdp.NetworkIntercept.*;

public class SampleTest extends BaseTest{

    @Test
    public void validateRequestIntercept(){
        createDevToolSession();
        enableFetch();

        devTool.addListener(Fetch.requestPaused(), request->
        {
            if(request.getRequest().getUrl().contains("entries"))
            {
                String mockURL = request.getRequest().getUrl().replace("entries","viewcart");
                System.out.println(mockURL);

                devTool.send(Fetch.continueRequest(request.getRequestId(), Optional.of(mockURL), Optional.empty()
                        , Optional.empty(), Optional.empty()));
            }
            else
            {
                devTool.send(Fetch.continueRequest(request.getRequestId(), Optional.empty(), Optional.empty()
                        , Optional.empty(), Optional.empty()));
            }

        });
        driver.get("https://www.demoblaze.com/");

    }
}
