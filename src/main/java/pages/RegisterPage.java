package pages;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.LoggerUtil;

public class RegisterPage {
	WebDriver driver;

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
	}

	By name = By.xpath("//input[@placeholder=\"Full Name\"]");
	By username = By.xpath("//input[@placeholder=\"username09\"]");
	By email = By.xpath("//input[@placeholder='username@gmail.com']");
	By password = By.xpath("//label[text()='Password']/../input");
	By PasswordConfirmation = By.xpath("//label[text()='Password confirmation']/../input");
	By location = By.xpath("//label[text()='Location']/../input");
	By termsAndCondition = By.xpath("//input[@id='terms_and_conditions']/..");
	By registerButton = By.xpath("//button[@type='submit']");
	By registerationlink = By.xpath("//a[text()='Register']");
	By bookAnAdvanture = By.xpath("//a[text()='Book an adventure']");
	By firstSuggestion = By.xpath("//span[text()='Ahmedabad'][1]");

	public void registerUser(String uname, String mail, String pass) throws InterruptedException {
		LoggerUtil.log.info("Registering user...");
		driver.findElement(registerationlink).click();
		driver.findElement(name).sendKeys(uname);
		driver.findElement(username).sendKeys(uname);
		driver.findElement(email).sendKeys(mail);
		driver.findElement(password).sendKeys(pass);
		driver.findElement(PasswordConfirmation).sendKeys(pass);
		driver.findElement(location).sendKeys("Ahmedabad, Gujarat, India");
		Thread.sleep(2000);
		driver.findElement(location).sendKeys(Keys.BACK_SPACE);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		wait.until(ExpectedConditions.visibilityOfElementLocated(firstSuggestion));
		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();

		driver.findElement(location).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(location).sendKeys(Keys.ENTER);
		Thread.sleep(1000);

		WebElement checkbox = driver.findElement(By.id("terms_and_conditions"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].checked = true; arguments[0].dispatchEvent(new Event('change'));", checkbox);

		driver.findElement(registerButton).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(bookAnAdvanture));
	}
}
