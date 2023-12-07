package com.DemoLogin;

import Utilities.BaseClass;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BaseClassTest extends BaseClass {

    @Test(description = "Testing a failing test")
    public void failingTest(){

        driver.get("https://www.google.com.co/");

        if (driver.getCurrentUrl().contains("youtube")){
            test.log(Status.FAIL, "URL is not correct");
        }

        Assert.assertTrue(driver.getCurrentUrl().contains("youtube"));

    }
}
