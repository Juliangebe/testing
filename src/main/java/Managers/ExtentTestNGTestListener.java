package Managers;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class ExtentTestNGTestListener implements ITestListener {

    private static ExtentReports extent = ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();


    @Override
    public void onStart(ITestContext context) {
        System.out.println("Start of execution of test -> " +context.getName());
        extent.setSystemInfo("Operating System", System.getProperty("os.name"));
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));

        //Add more if necessary

    }

    public void logTestStep(String message) {
        test.get().info(message);
    }

    @Override
    public void onTestStart(ITestResult result){
        System.out.println("Test started "+ result.getName());

        Method method = result.getMethod().getConstructorOrMethod().getMethod();
        Test testAnnotation = method.getAnnotation(Test.class);
        String testDescription = testAnnotation.description();

        test.set(extent.createTest(result.getMethod().getMethodName(), testDescription));
        test.set(extent.createTest(result.getMethod().getMethodName(),testAnnotation.description()));

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test Pass->"+result.getName());
        test.get().log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test Failed->"+result.getName());
        test.get().fail(result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test Skipped->"+result.getName());
        test.get().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("END Of Execution(TEST)-> " + context.getName());
        // Flush all log events for the test in the thread
        extent.flush();
    }













}
