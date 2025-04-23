package pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.LoggerUtil;

public class DetailsPage {
	WebDriver driver;
	WebDriverWait wait;

	public DetailsPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	// Duration
	By durationInput = By.xpath("//label[text()='Duration']/following-sibling::div//input[@type='number']");
	By durationUnitDropdown = By
			.xpath("//label[text()='Duration']/following-sibling::div//div[contains(@class,'inline-select')]");
	By durationUnitOptionDays = By.xpath("//span[text()='Days']");

	// Difficulty
	By difficultyDropdown = By
			.xpath("//label[text()='Difficulty']/following-sibling::div//div[contains(@class,'select-embark')]");
	By difficultyOption = By.xpath("//span[text()='Moderate']");

	// Group Size
	By groupSizeMin = By.xpath("//label[text()='Group Size']/following-sibling::div//input[@placeholder='Min']");
	By groupSizeMax = By.xpath("//label[text()='Group Size']/following-sibling::div//input[@placeholder='Max']");

	// Price
	By priceInput = By.xpath("//input[@placeholder='Price']");
	By currencyDropdown = By.xpath("//span[contains(text(),'Currency')]");
	By currencyUSD = By.xpath("//span[text()='USD']");
	By priceTypePerPerson = By.xpath("//span[text()='Per Person']");
	By priceTypePerPersonDropdown = By.xpath("(//label[text()='Price']/..//div[@class=\"multiselect__select\"])[2]");

	// Textareas
	By availability = By.xpath("//label[text()='Availability']/following-sibling::div/textarea");
	By qualifications = By
			.xpath("//label[text()='What qualifies you to give this tour?']/following-sibling::div/textarea");
	By additionalInfo = By.xpath("//label[text()='Additional info']/following-sibling::div/textarea");
	By cancellation = By.xpath("//label[text()='Cancellation']/following-sibling::div/textarea");

	// Save Button
	By saveButton = By.xpath("//div[@class='submit-form']//button[text()='Save']");

	// Methods
	public void enterTourDetails(String duration, String groupMin, String groupMax, String price,
			String availabilityText, String qualificationText, String additionalInfoText, String cancellationText)
			throws InterruptedException {
		LoggerUtil.log.info("Enter tour details....");
		wait.until(ExpectedConditions.visibilityOfElementLocated(durationInput));
		Thread.sleep(3000);
		driver.findElement(durationInput).clear();
		driver.findElement(durationInput).sendKeys(duration);
		driver.findElement(durationUnitDropdown).click();
		driver.findElement(durationUnitOptionDays).click();

		driver.findElement(difficultyDropdown).click();
		driver.findElement(difficultyOption).click();

		driver.findElement(groupSizeMin).clear();
		driver.findElement(groupSizeMin).sendKeys(groupMin);
		driver.findElement(groupSizeMax).clear();
		driver.findElement(groupSizeMax).sendKeys(groupMax);

		driver.findElement(priceInput).sendKeys(price);
		driver.findElement(currencyDropdown).click();
		driver.findElement(currencyUSD).click();
		driver.findElement(priceTypePerPersonDropdown).click();
		driver.findElement(priceTypePerPerson).click();

		driver.findElement(availability).sendKeys(availabilityText);
		driver.findElement(qualifications).sendKeys(qualificationText);
		driver.findElement(additionalInfo).sendKeys(additionalInfoText);
		driver.findElement(cancellation).sendKeys(cancellationText);
	}

	public void clickSave() {
		LoggerUtil.log.info("click on save button....");
		driver.findElement(saveButton).click();
	}

}
