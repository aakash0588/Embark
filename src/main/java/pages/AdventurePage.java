package pages;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.LoggerUtil;

public class AdventurePage {
	WebDriver driver;
	WebDriverWait wait;

	public AdventurePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	By categoryDropdown = By.xpath("//label[text()='Category']/..//div[contains(@class, 'multiselect__select')]");
	By difficultyDropdown = By.xpath("//label[text()='Difficulty']/..//div[contains(@class, 'multiselect__select')]");
	By groupSizeFieldMin = By.xpath("//input[@placeholder=\"Min\"]");
	By groupSizeFieldMax = By.xpath("//input[@placeholder=\"Max\"]");
	By cityInput = By.cssSelector("input.pac-target-input");
	By nextButton = By.xpath("//button[text()='Next']");
	By validationMessages = By.className("help-block");
	String categoryOption = "//span[text()='%s']"; // Placeholder for category name
	By groupSizeMinValidationMsg = By.xpath("//span[contains(text(), 'The group size min field is required.')]");
	By groupSizeMaxValidationMsg = By.xpath("//span[contains(text(), 'The group size max field is required.')]");

	public void enterAdventureDetails(String category, String difficulty, String city) throws InterruptedException {
		LoggerUtil.log.info("Filling Adventure Basic Details...");
		enterCity(city);
		selectCategory(category);
		selectDifficulty(difficulty);
		clickNext();
	}

	public void selectCategory(String category) {
		LoggerUtil.log.info("Selecting category: " + category);
		driver.findElement(categoryDropdown).click();
		driver.findElement(By.xpath(String.format(categoryOption.toString(), category))).click(); // Dynamic category
																									// selection

	}

	public void selectDifficulty(String difficulty) {
		LoggerUtil.log.info("Selecting difficulty: " + difficulty);
		driver.findElement(difficultyDropdown).click();
		driver.findElement(By.xpath(String.format(categoryOption.toString(), difficulty))).click(); // Dynamic category
																									// selection

	}

	public void enterGroupSizeAndNext(String min, String max) {
		LoggerUtil.log.info("Entering group size: Min - " + min + ", Max - " + max);
		driver.findElement(groupSizeFieldMin).sendKeys(min);
		driver.findElement(groupSizeFieldMax).sendKeys(max);
		clickNext();
	}

	public void enterCity(String city) throws InterruptedException {
		LoggerUtil.log.info("Entering city: " + city);
		WebElement cityInputField = driver.findElement(cityInput);
		cityInputField.sendKeys(city);
		Thread.sleep(2000); // Replace with WebDriverWait
		cityInputField.sendKeys(Keys.ARROW_DOWN);
		cityInputField.sendKeys(Keys.ENTER);
	}

	public void clickNext() {
		LoggerUtil.log.info("Clicking 'Next' button...");
		driver.findElement(nextButton).click();
	} 

	public void verifyGroupSizeValidation() {
		LoggerUtil.log.info("Verifying group size validation...");

		wait.until(ExpectedConditions.visibilityOfElementLocated(groupSizeMinValidationMsg));
		WebElement validationMsgMin = driver.findElement(groupSizeMinValidationMsg);
		Assert.assertTrue(validationMsgMin.isDisplayed(), "'Group Size Min' validation message not displayed!");
		Assert.assertEquals(validationMsgMin.getText().trim(), "The group size min field is required.");

		WebElement validationMsgMax = driver.findElement(groupSizeMaxValidationMsg);
		Assert.assertTrue(validationMsgMax.isDisplayed(), "'Group Size Max' validation message not displayed!");
		Assert.assertEquals(validationMsgMax.getText().trim(), "The group size max field is required.");

	}

	public void printValidationMessages() {
		LoggerUtil.log.info("Checking for validation messages...");
		List<WebElement> errors = driver.findElements(validationMessages);
		for (WebElement error : errors) {
			LoggerUtil.log.info("Validation Error: " + error.getText());
		}
	}

}
