package com.itc.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.itc.common.AynaxUtil;
import com.itc.util.BasePageObject;

public class HomePage extends BasePageObject


{
	WebElement element=null;
	
	//Constructor
		public HomePage(WebDriver driver) 
		{
		super(driver);
		}
		
	/*Variables*/
		
	boolean flag = false;
	List<WebElement> list=null;
	public static Logger logger = Logger.getLogger(DeleteVendorPage.class);

	/*Web elements*/
	
	By imgSiteLogo = By.xpath("//img[@alt='Aynax.com Logo']");
	By lnkLogin = By.linkText("Login");
	
	public boolean isSiteLogoDisplayed() throws Exception
	{
		try 
		{
			flag = isElementPresent(imgSiteLogo);
			if (flag) 
			{
			System.out.println("Site logo is displayed in Home Page");	
			}			
			else
			{
				System.out.println("Site logo is not displayed in Home Page");
			}
		} 
		catch (Exception e) 
		{
			throw new Exception("Site logo is not present in Home page::"+isSiteLogoDisplayed()+e.getLocalizedMessage());
		}
		return flag;
	}
	
	
	
	
	/**
	 * This method helps us click on Login
	 * @return boolean
	 * @param No param
	 * @throws Exception
	 */
	public LoginPage clickOnLogin() throws Exception
	{
		try 
		{
			flag = isElementPresent(lnkLogin);
			Assert.assertTrue(flag, "Logn link is not displayed");
			setElement(lnkLogin).click();
			AynaxUtil.explicitWait(3000);
			
		} 
		catch (Exception e) 
		{
			throw new Exception("Login link is not present in Home page::"+clickOnLogin()+e.getLocalizedMessage());
		}
		return new LoginPage(driver);
	}
	
}
