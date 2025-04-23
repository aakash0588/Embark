package tests;

import org.testng.annotations.*;
import pages.*;
import utils.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestResult;

public class EmbarkTest extends BaseTest {
	int testRow = 1; // Row index in Excel

	@Test
	public void embarkFlowTest() throws Exception {
		// Page objects
		RegisterPage register = new RegisterPage(driver);
		HomePage home = new HomePage(driver);
		AdventurePage adventure = new AdventurePage(driver);
		OverviewPage overviewPage = new OverviewPage(driver);
		LocationPage locationPage = new LocationPage(driver);
		PhotoPage photoPage = new PhotoPage(driver);
		DetailsPage detailsPage = new DetailsPage(driver);
		ViewTourPage viewTourPage = new ViewTourPage(driver);
		ItineraryPage itineraryPage = new ItineraryPage(driver);

		LoggerUtil.info("Test started");
		LoggerUtil.debug("Running test steps...");
		test = extent.createTest("Embark Full Journey Test");
		ExcelUtils.setExcelFile("testdata/EmbarkTestData.xlsx", "Sheet1");

		// Register
		driver.get("https://www.embark.org/?ref=madewithvuejs.com");
		String uname = "user" + commonUtils.generateRandomString(5);
		String email = uname + "@mail.com";
		String pass = "Test@1234";
		register.registerUser(uname, email, pass);
		String screenshotPath = ScreenshotUtil.captureScreenshot(driver, "MyPage");
		test.addScreenCaptureFromPath(screenshotPath);

		// Host Adventure
		home.clickHostAdventure();
		String category = ExcelUtils.getCellData(testRow, 0);
		String difficulty = ExcelUtils.getCellData(testRow, 1);
		String city = ExcelUtils.getCellData(testRow, 2);
		String groupSize = ExcelUtils.getCellData(testRow, 3);
		adventure.enterAdventureDetails(category, difficulty, city);
		adventure.verifyGroupSizeValidation();
		adventure.enterGroupSizeAndNext(groupSize, groupSize);
		test.addScreenCaptureFromPath(ScreenshotUtil.captureScreenshot(driver, "Adventure"));

		// overview
		String title = ExcelUtils.getCellData(testRow, 4);
		String category1 = ExcelUtils.getCellData(testRow, 5);
		String summary = ExcelUtils.getCellData(testRow, 6);
		String included = ExcelUtils.getCellData(testRow, 7);
		String notIncluded = ExcelUtils.getCellData(testRow, 8);
		overviewPage.fillOutOverviewForm(title, category1, summary, included, notIncluded);
		overviewPage.submitForm();
		test.addScreenCaptureFromPath(ScreenshotUtil.captureScreenshot(driver, "Overview"));

		// Location
		locationPage.submitForm();

		// Photos
		photoPage.uploadPhotos(System.getProperty("user.dir") + "/testdata/sample.jpg");
		photoPage.submitForm();
		test.addScreenCaptureFromPath(ScreenshotUtil.captureScreenshot(driver, "Photo"));

		// Details
		String duration = ExcelUtils.getCellData(testRow, 9);
		String groupMin = ExcelUtils.getCellData(testRow, 10);
		String groupMax = ExcelUtils.getCellData(testRow, 11);
		String price = ExcelUtils.getCellData(testRow, 12);
		String availability = ExcelUtils.getCellData(testRow, 13);
		String qualification = ExcelUtils.getCellData(testRow, 14);
		String additionalInfo = ExcelUtils.getCellData(testRow, 15);
		String cancellation = ExcelUtils.getCellData(testRow, 16);
		detailsPage.enterTourDetails(duration, groupMin, groupMax, price, availability, qualification, additionalInfo,
				cancellation);
		detailsPage.clickSave();
		test.addScreenCaptureFromPath(ScreenshotUtil.captureScreenshot(driver, "Details"));

		// Location
		String location = ExcelUtils.getCellData(testRow, 17); // Row testRow, Column 0
		String day1 = ExcelUtils.getCellData(testRow, 18);
		String day2 = ExcelUtils.getCellData(testRow, 19);
		String day3 = ExcelUtils.getCellData(testRow, 20);

		// Itinerary
		itineraryPage.fillItineraryDetails(location, day1, day2, day3);
		Assert.assertEquals(itineraryPage.getSuccessMessage(), "Tour updated successfully");
		test.addScreenCaptureFromPath(ScreenshotUtil.captureScreenshot(driver, "Itinerary"));

		// View Tour
		viewTourPage.viewTourAndSaveToWishList();
		test.addScreenCaptureFromPath(ScreenshotUtil.captureScreenshot(driver, "ViewTour"));

		// logout
		home.logout();
		home.verifyLogout();
		test.addScreenCaptureFromPath(ScreenshotUtil.captureScreenshot(driver, "afterLogout"));
	}

	@AfterMethod
	public void logTestResult(ITestResult result) throws Exception {
		int resultCol = 21; // Column index for result in Excel
		if (result.getStatus() == ITestResult.SUCCESS) {
			ExcelUtils.setTestStatus(testRow, resultCol, "Pass");
		} else if (result.getStatus() == ITestResult.FAILURE) {
			ExcelUtils.setTestStatus(testRow, resultCol, "Fail");
		} else {
			ExcelUtils.setTestStatus(testRow, resultCol, "Skip");
		}
	}
}
