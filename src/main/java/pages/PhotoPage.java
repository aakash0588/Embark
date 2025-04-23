package pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.LoggerUtil;


public class PhotoPage {
	WebDriver driver;
	WebDriverWait wait;

	public PhotoPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	By uploadInput = By.xpath("//input[@id='upload_images']");
	By saveButton = By.xpath("//button[text()='Save']");
	By addPhotosButton = By.xpath("//div[contains(@class,'add_photo_btn')]");

	public void uploadPhotos(String photoPath) {
		LoggerUtil.log.info("Waiting for 'Add Photo' button to be visible...");
		wait.until(ExpectedConditions.visibilityOfElementLocated(addPhotosButton));

		driver.findElement(uploadInput).sendKeys(photoPath);
	}

	public void submitForm() {
		LoggerUtil.log.info("Clicking 'Save' button on Photo Page...");
		driver.findElement(saveButton).click();
	}

}
