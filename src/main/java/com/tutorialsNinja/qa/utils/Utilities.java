package com.tutorialsNinja.qa.utils;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utilities {

	public static final int implicitWait = 20;

	public static String getDateTime() {

		Date date = new Date();
		String getRandomEmailID = date.toString().replace(" ", "_").replace(":", "_");
		System.out.println(getRandomEmailID);
		return getRandomEmailID;

	}
	
	public static Object[][] getTestDataFromExcelFile(String sheetName) throws InvalidFormatException, IOException
	{
		File file = new File(System.getProperty("user.dir")+"/src/main/java/com/tutorialsNinja/qa/testdata/testData1.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(file);
		XSSFSheet sheet = wb.getSheet(sheetName);
		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(0).getLastCellNum();
		
		Object[][] data = new Object[rows][cols];
		for(int i=0;i<rows;i++)
		{
			XSSFRow row = sheet.getRow(i+1);
			for(int j=0;j<cols;j++)
			{
				XSSFCell cell = row.getCell(j);
				CellType cellType =cell.getCellType();
				
				switch(cellType)
				{
				case STRING:
					data[i][j]=cell.getStringCellValue();
					break;
				
				case NUMERIC:
					data[i][j]=Integer.toString((int)cell.getNumericCellValue());
					break;
					
				case BOOLEAN:
					data[i][j]=cell.getBooleanCellValue();
					break;
				}
			}
		}
		
		return data;
		
	}
	
	
	public static String captureScreenShot(WebDriver driver, String testName)
	{
		File srcScreenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destScreenShotPath = System.getProperty("user.dir")+"/ScreenShots/"+testName+Utilities.getDateTime()+".png";
		try {
			FileHandler.copy(srcScreenshot, new File (destScreenShotPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return destScreenShotPath;
	}

}
