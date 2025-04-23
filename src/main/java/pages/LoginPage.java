package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.LoggerUtil;

public class LoginPage {
	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	By emailField = By.id("");
	By passwordField = By.id("");
	By loginButton = By.id("");

	public void loginUser(String email, String password) {
		LoggerUtil.log.info("Logging in with email: " + email);
		driver.findElement(emailField).sendKeys(email);
		driver.findElement(passwordField).sendKeys(password);
		driver.findElement(loginButton).click();
	}
}
