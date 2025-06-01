package com.tutorialsNinja.qa.retestAnalyser;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;


/*
 * This class provides an implemetation to Iretryanalyzer interface to testng
 */
public class RestestAnalyserImplementation implements IRetryAnalyzer {

	
	int count =0;
	int retryCount = 3;  //manually analyzed
	
	public boolean retry(ITestResult result) {
		
		while(count<retryCount)
		{
			count++;
			return true;   //retry retry retry 
		}
		
		return false;
	}

	
	
}
