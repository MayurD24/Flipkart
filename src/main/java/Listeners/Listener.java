package Listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import BaseClass.Base1;

public class Listener implements ITestListener {

	ExtentTest extentTest;

	public void onTestStart(ITestResult result) {
		extentTest = Base1.getAlreadyExistingExtentTest();
		extentTest.log(Status.INFO, "Test Started : "+result.getName());
		System.out.println("Test Started : " + result.getName());
		Reporter.log("Test Started");
	}

	public void onTestSuccess(ITestResult result) {
		extentTest.log(Status.PASS, "Test Passed : "+ result.getName());
		System.out.println("Test Passed : " + result.getName());
		Reporter.log("Test Passed");
	}

	public void onTestFailure(ITestResult result) {
		extentTest.log(Status.FAIL, "Test Failed : "+ result.getName());
		System.out.println("Test Failed : " + result.getName());
		Reporter.log("Test Failed");
	}

	public void onTestSkipped(ITestResult result) {
		extentTest.log(Status.SKIP, "Test Skipped : "+ result.getName());
		System.out.println("Test Skipped : " + result.getName());
		Reporter.log("Test Skipped");
	}
}
