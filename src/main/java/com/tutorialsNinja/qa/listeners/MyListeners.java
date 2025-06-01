package com.tutorialsNinja.qa.listeners;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsNinja.qa.utils.ExtentReporter;
import com.tutorialsNinja.qa.utils.Utilities;

public class MyListeners implements ITestListener {

	ExtentReports extentReport;
	ExtentTest extentTest;
	String testName;

	public void onStart(ITestContext context) {
		extentReport = ExtentReporter.generateExtentReports();
	}

	public void onTestStart(ITestResult result) {
		testName = result.getName();
//		System.out.println(testName + " started executing.");
		extentTest = extentReport.createTest(testName);
		extentTest.log(Status.INFO, testName + " started executing.");

	}

	public void onTestSuccess(ITestResult result) {
		testName = result.getName();
		extentTest.log(Status.PASS, testName + " got successfully executed.");
	}

	public void onTestFailure(ITestResult result) {
		testName = result.getName();

		System.out.println("Screenshot taken");
		WebDriver driver=null;
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String destScreenShotPath = Utilities.captureScreenShot(driver, testName);
		
		extentTest.addScreenCaptureFromPath(destScreenShotPath);
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, testName + " got failed.");
	}

	public void onTestSkipped(ITestResult result) {
		testName = result.getName();
//		System.out.println(testName + " got failed.");
//		System.out.println(result.getThrowable());

		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, testName + " got skipped.");

	}

	public void onFinish(ITestContext context) {
		System.out.println("Project execution finished");
		extentReport.flush();
		
		//auto launching extent report after execution
		
	}

}
