package TestClasses;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import BaseClass.Base1;
import PomClasses.HomePage;
import UtilClasses.Util1;

public class verifyUserCanGetLowestProductPriceFromEachPage {

	static WebDriver driver;

	ExtentHtmlReporter htmlReporter;
	ExtentReports reports;
	ExtentTest extentTest;

	HomePage hp;

	@BeforeClass
	@Parameters("browser")
	public void beforeClass(String browser) throws IOException {
		htmlReporter = Base1.getExtentHtmlReporter();
		reports = Base1.getExtentReports();
		extentTest = Base1.getExtentTest("verifyUserCanCreateNewAddress");

		driver = Base1.getDriver(browser);
	}

	@BeforeMethod
	public void beforeMethod() {
		hp = new HomePage(driver);
	}

	@Test(priority = 1)
	public void VerifyUserCanSearchProduct() {
		hp.searchProduct();
		hp = new HomePage(driver);
		String actualText = hp.getValidText();
		Assert.assertTrue(actualText.contains("Showing 1 –"), "Text is not matching");
	}

	@Test(priority = 2)
	public void verifyUserCanGetLowestProductPriceFromEachPages() {
		Map<Integer, String> map = new HashMap<>();

		for (int i = 1; i <= 5; i++) {
			if (i != 1) {
				hp.switchToPage(i);
			}
			map.put(i, hp.getLowestPriceOfProduct());
		}
		System.out.println("Lowest Price from 5 pages : " + map);
	}

	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			String path = Util1.getScreenshot(driver, result.getName());
			extentTest.log(Status.FAIL, "Test Failed : " + result.getName(),
					MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		}
	}

	@AfterClass
	public void afterClass() {
		reports.flush();
	}
}
