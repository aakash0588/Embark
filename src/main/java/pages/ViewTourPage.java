package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.LoggerUtil;

public class ViewTourPage {
	WebDriver driver;
	WebDriverWait wait;

	public ViewTourPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	By viewTourButton = By.xpath("//a[contains(text(),'View Tour') and @target='_blank']");
	By saveToWishListButton = By.xpath("(//div[@title='Save to Wish List'])[1]");

	public void viewTour() {
		 LoggerUtil.log.info("Attempting to click 'View Tour' button and switch to new tab");
		String mainWindow = driver.getWindowHandle();

		driver.findElement(viewTourButton).click();

		for (String windowHandle : driver.getWindowHandles()) {
			if (!windowHandle.equals(mainWindow)) {
				driver.switchTo().window(windowHandle);
				break;
			}
		}
	}

	public void saveToWishList() {
		LoggerUtil.log.info("Waiting for and clicking 'Save to Wish List' button");
		wait.until(ExpectedConditions.visibilityOfElementLocated(saveToWishListButton));
		driver.findElement(saveToWishListButton).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void switchToMainWindow() {
		LoggerUtil.log.info("Switching back to main window and closing tour tab");
		String mainWindow = driver.getWindowHandle();
		for (String windowHandle : driver.getWindowHandles()) {
			if (!windowHandle.equals(mainWindow)) {
				driver.switchTo().window(windowHandle);
				driver.close();
			}
		}
		driver.switchTo().window(mainWindow);
	}

	public void viewTourAndSaveToWishList() {
		LoggerUtil.log.info("Performing full flow: View Tour > Save to Wishlist  > Return");
		viewTour();
		saveToWishList();
		switchToMainWindow();
	}
}
