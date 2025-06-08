package com.tutorialsNinja.qa.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.tutorialsNinja.qa.utils.Utilities;

public class Base {

	WebDriver driver;
	public static Properties  prop;
	public static Properties prop1;
	
	public void loadPropertiesFile() throws IOException
	{
		prop=new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/tutorialsNinja/qa/config/Config.properties");
		prop.load(fis);
		
		prop1=new Properties();
		FileInputStream fis1 = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/tutorialsNinja/qa/testdata/testData.properties");
		prop1.load(fis1);
		
	}
	
	
	public WebDriver initialiseBrowserAndOpenAppURL(String browserName) throws IOException
	{
		
		if(browserName.equals("chrome"))
		{
			
			ChromeOptions options = new ChromeOptions();

            // Check if headless mode is enabled in config file
            if (Boolean.parseBoolean(prop.getProperty("headless"))) {
            	options.addArguments("--no-sandbox");
            	options.addArguments("--disable-dev-shm-usage");
            	options.addArguments("--remote-allow-origins=*");
            	options.addArguments("--disable-gpu");
            	options.addArguments("--headless"); // optional, if you're running Jenkins on a server with no GUI
            	options.addArguments("--window-size=1920,1080");
            	options.addArguments("user-data-dir=/tmp/temporary-profile-" + System.currentTimeMillis());
            }
			driver=new ChromeDriver(options);
		}
		else if(browserName.equals("firefox"))
		{
			driver=new FirefoxDriver();
		}
		else
		{
			driver=new EdgeDriver();
		}
		
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.implicitWait));
		loadPropertiesFile();
		driver.get(prop.getProperty("url"));
		return driver;
	}
}
