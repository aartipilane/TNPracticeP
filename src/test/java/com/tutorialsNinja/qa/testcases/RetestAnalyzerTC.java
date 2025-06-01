package com.tutorialsNinja.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RetestAnalyzerTC {

	
	@Test(retryAnalyzer = com.tutorialsNinja.qa.retestAnalyser.RestestAnalyserImplementation.class)
	public void sample()
	{
		System.out.println("Step 1");
		System.out.println("Step 2");
		System.out.println("Step 3");

		
		if(1==0)
		{
			System.out.println("Pass");
		}
		else
		{
			System.out.println("Fail");
		}
		
		Assert.assertEquals(0, 1);
		
		System.out.println("Step 4");
		System.out.println("Step 5");
		System.out.println("Step 6");

	}
}
