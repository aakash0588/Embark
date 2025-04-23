package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.LoggerUtil;

public class OverviewPage {
	WebDriver driver;
	WebDriverWait wait;

	public OverviewPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

//    overview 

	By titleInput = By.xpath("//input[@placeholder='Enter the name of your tour']");
	By categoryDropdown1 = By.xpath("//label[text()='Category']/..//div[contains(@class, 'multiselect__select')]");
	String categoryOption = "//span[text()='%s']"; // Placeholder for category name
	By summaryInput = By.xpath("//label[text()='Summary']/..//textarea");
	By includedInput = By.xpath("//label[text()='Included']/..//textarea");
	By notIncludedInput = By.xpath("//label[text()='Not included']/..//textarea");
	By saveButton = By.xpath("//button[contains(text(),'Save')]");
	By successMessage = By.xpath("//*[text()='successMessage']");
	By groupSizeMinValidationMsg = By.xpath("//span[contains(text(), 'The group size min field is required.')]");
	By groupSizeMaxValidationMsg = By.xpath("//span[contains(text(), 'The group size max field is required.')]");

	public void fillOutOverviewForm(String title, String category, String summary, String included, String notIncluded)
			throws InterruptedException {
		 LoggerUtil.log.info("Waiting for page to fully load before filling overview form...");
		wait.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd)
				.executeScript("return document.readyState").equals("complete"));

		 LoggerUtil.log.info("Filling out Overview form fields...");
		wait.until(ExpectedConditions.elementToBeClickable(titleInput));
		Thread.sleep(3000);
		driver.findElement(titleInput).sendKeys(title);
		driver.findElement(summaryInput).sendKeys(summary);
		driver.findElement(includedInput).sendKeys(included);
		driver.findElement(notIncludedInput).sendKeys(notIncluded);
		wait.until(ExpectedConditions.elementToBeClickable(categoryDropdown1));
		driver.findElement(categoryDropdown1).click();
		driver.findElement(By.xpath(String.format(categoryOption.toString(), category))).click();
	}

	public void submitForm() {
		LoggerUtil.log.info("Clicking the Save button on the Overview form...");
		driver.findElement(saveButton).click();
		LoggerUtil.log.info("Form submitted.");
	}

	public String getSuccessMessage() {
		LoggerUtil.log.info("Waiting for success message...");
		wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
		return driver.findElement(successMessage).getText();
	}

}
