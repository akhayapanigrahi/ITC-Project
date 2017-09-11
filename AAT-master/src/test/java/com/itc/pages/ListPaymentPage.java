package com.itc.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.itc.common.AynaxUtil;
import com.itc.util.BasePageObject;

public class ListPaymentPage extends BasePageObject{
	
	public ListPaymentPage(WebDriver driver) {
		super(driver);
	}

	boolean flag = false;
	boolean flag1 = false;
	boolean flag2=false;
	boolean titleflag=false;
	public static Logger logger = Logger.getLogger(ListPaymentPage.class);

	/*Web elements*/
	
	By expensesTab = By.xpath(".//*[@id='top-menu']//a[contains(.,'Expenses')]");
	By listPaymentLink = By.xpath(".//*[@class='hover-menu-parent ']//a[contains(.,'List of Payments')]");
	By listPaymenttext=By.xpath("//div[@class='page-header']/h1");
	By errorDialog=By.id("no_vendor");

	public void  clickOnListPayment() throws Exception
	{
		try 
		{
			flag = isElementPresent(expensesTab);
			if (flag) 
			{
				logger.info("Expense tab is displayed");
			WebElement expTab=driver.findElement(expensesTab);
			WebElement paymentlink = driver.findElement(listPaymentLink);
			Actions act=new Actions(driver);
			act.moveToElement(expTab).click(expTab).click(paymentlink).doubleClick().build().perform();
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
			//throw new Exception("Expense tab is displayed::"+isExpenseTabDisplayed()+e.getLocalizedMessage());
		}
	}	
	
	
	
	public String  isTitleDisplayed(){
		
		return getPageTitle();
		
	}
	public boolean isResultPageDisplayed() throws Exception
	{
		try 
		{
			logger.info("Success page of List Payment Page is displayed");
			System.out.println();
			String expectedtitle = getPageTitle();
			logger.info("Title is "+expectedtitle);
			Assert.assertTrue(expectedtitle.startsWith("List of Outgoing"));
			String text = getText(listPaymenttext);
			logger.info("Success text"+text);
			Assert.assertTrue(text.startsWith("List of Outgoing Payments"));
			
		} 
		catch (Exception e) 
		{
			throw new Exception("List Payment page is displayed::"+isResultPageDisplayed()+e.getLocalizedMessage());
		}
		return flag1;
	}
	
	
}
