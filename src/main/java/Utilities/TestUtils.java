package Utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestUtils {

    public static String takeScreenshot(WebDriver driver, String testName) {
        // Format the file name and create a path where the screenshot will be saved
        String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        String fileName = testName + "_" + date + ".png";
        String directory = "test-output/screenshots/";
        String filePath = directory + fileName;

        // Take the screenshot
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Create the directory if it does not exist
        new File(directory).mkdirs();

        // Copy the screenshot file to the specified location
        try {
            FileUtils.copyFile(scrFile, new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Return the file path
        return filePath;
    }

}
