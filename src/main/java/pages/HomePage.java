package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.LoggerUtil;

public class HomePage {
	WebDriver driver;
	WebDriverWait wait;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	By hostAdventureLink = By.xpath("//a[contains(text(), 'Host an Adventure')]");
	By userMenu = By.xpath("(//div[@id=\"navExplore\"])[2]");
	By logoutButton = By.xpath("(//a[contains(text(), 'Logout')])[2]");
	By bookAnAdvanture = By.xpath("//a[text()='Book an adventure']");

	public void clickHostAdventure() {
		LoggerUtil.log.info("Clicking on 'Host an Adventure'...");
		driver.findElement(hostAdventureLink).click();
	}

	public void logout() {
		LoggerUtil.log.info("Logging out...");
		driver.findElement(userMenu).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(logoutButton));
		driver.findElement(logoutButton).click();
	}

	public void verifyLogout() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(bookAnAdvanture));
	}
}
