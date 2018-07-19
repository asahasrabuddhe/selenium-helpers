package com.perennial.selenium;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;

public class ScreenshotHelper {
    public static void captureScreenshot(WebDriver driver, String destinationPath) {
        // Initialize Javascript Executor to allow us to execute JS code on the webpage.
        JavascriptExecutor je = (JavascriptExecutor)driver;
        // Return the actual height of the page using JS
        Long height = (Long)je.executeScript("return document.body.scrollHeight;");
        // Backup the existing dimension to reset before function exist
        Dimension oldDimension = driver.manage().window().getSize();
        // Set new dimension = full height x 1280
        Dimension d = new Dimension(1280, height.intValue());
        driver.manage().window().setSize(d);

        // Initialize Screenshot Driver
        TakesScreenshot ts = (TakesScreenshot)driver;
        // Take a screenshot and store as a file
        File source = ts.getScreenshotAs(OutputType.FILE);
        // Create destination file
        File destination = new File(destinationPath);
        // Copy file to destination
        try {
            FileUtils.copyFile(source, destination);
        } catch( Exception e) {
            System.out.println(e.getMessage());
        }
        // Reset dimension
        driver.manage().window().setSize(oldDimension);
    }
}
