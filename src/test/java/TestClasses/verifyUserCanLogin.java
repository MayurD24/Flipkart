package TestClasses;

import java.io.IOException;

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
import PomClasses.LoginPage;
import UtilClasses.Util1;

public class verifyUserCanLogin {

	static WebDriver driver;

	ExtentHtmlReporter htmlReporter;
	ExtentReports reports;
	ExtentTest extentTest;

	LoginPage lp;
	HomePage hp;

	@BeforeClass
	@Parameters("browser")
	public void beforeClass(String browser) throws IOException {
		htmlReporter = Base1.getExtentHtmlReporter();
		reports = Base1.getExtentReports();
		extentTest = Base1.getExtentTest("verifyUserCanLogin");

		driver = Base1.getDriver(browser);
	}

	@BeforeMethod
	public void beforeMethod() {
		lp = new LoginPage(driver);
		hp = new HomePage(driver);
	}

	@Test
	public void verifyUserCanLogIn() throws InterruptedException, IOException {
		lp.enterEmailID();
		lp.enterPassword();
		lp.clickLoginBtn();
		hp = new HomePage(driver);
		String profileName = hp.getProfileName();
		//Check your Profile Name
		Assert.assertEquals(profileName, "MAYUR", "Profile name is not matching");
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
