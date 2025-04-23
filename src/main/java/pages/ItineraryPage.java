package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.LoggerUtil;

public class ItineraryPage {
	WebDriver driver;
	WebDriverWait wait;

	public ItineraryPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		LoggerUtil.info("Initialized ItineraryPage with driver.");
	}

	// Locators
	By meetupLocationInput = By.xpath("//label[text()='Meetup Location']/following-sibling::div/input");
	By itineraryDay1 = By.xpath("//p[text()='Day 1']/following-sibling::textarea");
	By itineraryDay2 = By.xpath("//p[text()='Day 2']/following-sibling::textarea");
	By itineraryDay3 = By.xpath("//p[text()='Day 3']/following-sibling::textarea");
	By saveButton = By.xpath("//div[@class='submit-form']/button[text()='Save']");
	By firstSuggestion = By.xpath("//span[text()='Ahmedabad'][1]");
	By successMessage = By.xpath("(//div[@class=\"notification-title\"])[1]");

	public void enterMeetupLocation(String location) throws InterruptedException {
		LoggerUtil.info("Entering meetup location: " + location);
		wait.until(ExpectedConditions.visibilityOfElementLocated(meetupLocationInput));
		driver.findElement(meetupLocationInput).sendKeys(location);
		Thread.sleep(2000);
		driver.findElement(meetupLocationInput).sendKeys(Keys.BACK_SPACE);

		wait.until(ExpectedConditions.visibilityOfElementLocated(firstSuggestion));
		new Actions(driver).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
		LoggerUtil.info("Meetup location selected from suggestions.");
	}

	public void enterItineraryDay1(String text) {
		LoggerUtil.info("Entering Itinerary for Day 1: " + text);
		driver.findElement(itineraryDay1).clear();
		driver.findElement(itineraryDay1).sendKeys(text);
	}

	public void enterItineraryDay2(String text) {
		LoggerUtil.info("Entering Itinerary for Day 2: " + text);
		driver.findElement(itineraryDay2).clear();
		driver.findElement(itineraryDay2).sendKeys(text);
	}

	public void enterItineraryDay3(String text) {
		LoggerUtil.info("Entering Itinerary for Day 3: " + text);
		driver.findElement(itineraryDay3).clear();
		driver.findElement(itineraryDay3).sendKeys(text);
	}

	public void clickSave() {
		LoggerUtil.info("Clicking the Save button.");
		driver.findElement(saveButton).click();
	}

	public void fillItineraryDetails(String location, String day1, String day2, String day3)
			throws InterruptedException {
		LoggerUtil.info("Filling out the itinerary form.");
		enterMeetupLocation(location);
		enterItineraryDay1(day1);
		enterItineraryDay2(day2);
		enterItineraryDay3(day3);
		clickSave();
		LoggerUtil.info("Itinerary form submitted.");
	}

	public String getSuccessMessage() {
		LoggerUtil.info("Fetching success message after saving itinerary.");
		wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
		String message = driver.findElement(successMessage).getText();
		LoggerUtil.info("Success message displayed: " + message);
		return message;
	}
}
