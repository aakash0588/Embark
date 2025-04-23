package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.LoggerUtil;

public class LocationPage {
	WebDriver driver;
	WebDriverWait wait;

	public LocationPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	By longitudeInput = By.xpath("//input[@placeholder='Enter Longitude']");
	By saveButton = By.xpath("//button[text()='Save']");

	public void submitForm() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(longitudeInput));
		Thread.sleep(2000);
		LoggerUtil.log.info("Clicking the 'Save' button to submit the form.");
		driver.findElement(saveButton).click();
		LoggerUtil.log.info("Form submitted successfully.");
	}

}
