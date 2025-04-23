package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import utils.DriverFactory;
import utils.ExtentManager;

public class BaseTest {
    public WebDriver driver;
    public ExtentReports extent;
    public ExtentTest test;

    @BeforeSuite
    public void setupReport() {
        extent = ExtentManager.getInstance();
    }

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    @AfterSuite
    public void flushReport() {
        extent.flush();
    }
}
