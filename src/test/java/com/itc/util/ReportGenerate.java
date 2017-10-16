package com.itc.test;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.itc.util.ReportGenerator;

public class ReportGenerate extends ReportGenerator {
	
	/*public ReportGenerate(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}*/
	public String chromeDriverPath = System.getProperty("user.dir")+"\\src\\test\\resources\\drivers\\chromedriver.exe";

	@Test
	public void generateReport() {
		System.setProperty("webdriver.chrome.driver",chromeDriverPath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
		runTestReport("http://localhost:8080/job/ITC-Project/", "find the report", "chrome 55");
		
	}

}
