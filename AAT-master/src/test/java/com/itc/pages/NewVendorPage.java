package com.itc.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.itc.common.AynaxUtil;
import com.itc.util.BasePageObject;

public class NewVendorPage extends BasePageObject{
	
	public NewVendorPage(WebDriver driver) {
		super(driver);
	}

	boolean flag = false;
	boolean flag1 = false;
	boolean flag2=false;
	boolean titleflag=false;
	public static Logger logger = Logger.getLogger(NewVendorPage.class);

	
	/*Web elements*/
	
	By ExpensesTab = By.xpath(".//*[@id='top-menu']//a[contains(.,'Expenses')]");
	By NewVendorLink = By.xpath(".//*[@id='top-menu']//a[contains(.,'New Vendor')]");
	By VendorName = By.xpath(".//*[@placeholder='Vendor name']");
	By VendorAddr = By.xpath(".//*[@placeholder='Vendor address']");
	By SaveButton=By.name("submitButton");
	By successDialog=By.xpath("//div[@class='alert alert-success alert-dismissable']");
	By errorDialog=By.xpath("//div[@class='alert alert-danger alert-dismissable']");

	public void  clickOnNewVendor() throws Exception
	{
		try 
		{
			flag = isElementPresent(ExpensesTab);
			if (flag) 
			{
				logger.info("Expense tab is displayed");
			WebElement expTab=driver.findElement(ExpensesTab);
			WebElement vendorlink = driver.findElement(NewVendorLink);
			Actions act=new Actions(driver);
			act.moveToElement(expTab).click(expTab).click(vendorlink).doubleClick().build().perform();
			AynaxUtil.explicitWait(3000);
			act.click().build().perform();
			AynaxUtil.explicitWait(3000);
			
 			}			
			else
			{
				logger.info("Expense tab is not displayed");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}	
	
	
	public void enterVendorName(String vendorName) throws Exception {
		if(isElementPresent(VendorName)){
		setElement(VendorName).sendKeys(vendorName);

	}
	}

	public void enterVendorAddr(String address) throws Exception {
		if(isElementPresent(VendorAddr)){
			setElement(VendorAddr).sendKeys(address);
		}
	}

	public void clickSave() throws Exception {
		if(isElementPresent(SaveButton)){
		setElement(SaveButton).click();

	}
	}
	
	
	public void VendorCreation(String vendorName,String  address) throws Exception
	{
		try 
		{
			enterVendorName(vendorName);
			enterVendorAddr(address);
			clickSave();
			AynaxUtil.explicitWait(3000);
									
		} 
		catch (Exception e) 
		{
			throw new Exception("New Vendor link is not present in Expense page::");
		}
		//return new LoginPage(driver);
	}
	
	public boolean isTitleDisplayed(){
		
		getPageTitle();
		return titleflag;
	}
	
	public boolean isResultPageDisplayed() throws Exception
	{
		try 
		{
			flag1=isElementPresent(successDialog);
			if (flag1) 
			{
				logger.info("Success page of vendor creation is displayed");
			String expectedtitle = getPageTitle();
			logger.info("Title is "+expectedtitle);
			Assert.assertTrue(expectedtitle.contains("Edit Vendor:: Aynax.com"));
			String text = getText(successDialog);
			logger.info("Success text"+text);
			Assert.assertTrue(text.contains("Vendor successfully saved"));
			
			}			
			else
			{
				logger.info("New Vendor creation error page  is displayed");
				String expectedtitle = getPageTitle();
				Assert.assertTrue(expectedtitle.contains("New Vendor:: Aynax.com"));
				String text = getText(errorDialog);
				logger.info("The error text is" + text);
				Assert.assertTrue(text.contains("Vendor name cannot be blank"));
			}
		} 
		catch (Exception e) 
		{
			throw new Exception("New Vendor page is displayed::"+isResultPageDisplayed()+e.getLocalizedMessage());
		}
		return flag1;
	}
	
	
}

