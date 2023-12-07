package com.DemoLogin;

import PageObjects.DemoRegisterFormPage;
import Utilities.BaseClass;
import com.aventstack.extentreports.Status;
import org.testng.annotations.Test;

public class FillFormTest extends BaseClass {

    DemoRegisterFormPage demoRegisterFormPage;

    @Test(description = "Filling form")
    public void FillForm()  {
        test = extent.createTest("Fill form test", "This test will attempt to fill the form");
        demoRegisterFormPage = new DemoRegisterFormPage();
        driver.get("https://demo.automationtesting.in/Register.html");

        demoRegisterFormPage.fillForm();
        test.log(Status.INFO, "Step 1: Filling the form");
        test.log(Status.PASS, "Form filled");
        test.log(Status.WARNING, "Form NOT filled");

        test.assignCategory("Smoke","Regression");
        test.assignAuthor("Automation tester");

        extent.setSystemInfo("Browser","Chrome");
        extent.setSystemInfo("OS","Windows 10");







    }
}
