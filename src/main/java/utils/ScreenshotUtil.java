package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class ScreenshotUtil {
    public static String captureScreenshot(WebDriver driver, String name) throws Exception {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = "test-output/screenshots/" + name + "_" + System.currentTimeMillis() + ".png";
        File dest = new File(path);
        dest.getParentFile().mkdirs(); // ensure folder exists
        Files.copy(src.toPath(), dest.toPath());
        return dest.getAbsolutePath();
    }
}
