package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utils.LoggerUtil;

public class WishlistPage {
	WebDriver driver;

	public WishlistPage(WebDriver driver) {
		this.driver = driver;
	}

	By saveToWishlistBtn = By.id("wishlist-btn");

	public void saveToWishlist() {
		LoggerUtil.log.info("Clicking 'Save to Wishlist'...");
		driver.findElement(saveToWishlistBtn).click();
		Assert.assertTrue(driver.getPageSource().contains("Saved"), "Wishlist confirmation not found");
	}
}
