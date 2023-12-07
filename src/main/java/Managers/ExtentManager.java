package Managers;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String reportFileName = "test-output/extent-report_" + timeStamp + ".html";
        if (extent == null) {
            createInstance(reportFileName);
        }
        return extent;
    }

    private static ExtentReports createInstance(String fileName) {
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(fileName);
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setDocumentTitle("Automation Testing Report");
        htmlReporter.config().setReportName("Regression Testing");

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        // Add environment details or other info (optional)
        extent.setSystemInfo("Environment", "Production");
        extent.setSystemInfo("Platform", "Windows");

        return extent;
    }
}

