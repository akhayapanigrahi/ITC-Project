package com.itc.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.itc.common.AynaxUtil;
import com.itc.util.BasePageObject;

public class NewAccountPage extends BasePageObject {
	NewAccountPage objNewExpensePage = null;

	public NewAccountPage(WebDriver driver) {
		super(driver);
	}

	boolean flag = false;
	boolean nameflag = false;
	boolean AccTypeflag = false;
	boolean amountflag = false;
	boolean titleflag = false;
	boolean flag1 = false;
	boolean dateflag = false;
	public static Logger logger = Logger.getLogger(NewAccountPage.class);

	/* Web elements */

	By AccountingTab = By.xpath(".//*[@id='top-menu']//a[contains(.,'Accounting')]");
	By NewAccount = By.xpath(".//*[@id='top-menu']//a[contains(.,'New Account')]");
	By Name = By.id("p_name");
	By date = By.className("datepicker dropdown-menu");
	By AcctTypeDropdown = By.id("p_type");
	By startBal = By.id("p_balance");
	By SaveButton = By.xpath("//button[@data-name='submitButton']");
	By successDialog=By.xpath("//div[@class='alert alert-success alert-dismissable']");
	By errorDialog=By.xpath("//div[@class='alert alert-danger alert-dismissable']");
	By nextBtn=By.xpath("//table[@class=' table-condensed']//th[@class='next']");
	
	
	public void clickOnNewAccount() throws Exception {
		try {
			flag = isElementPresent(AccountingTab);
			if (flag) {
				logger.info("New Accounting tab is displayed");
				WebElement accTab = driver.findElement(AccountingTab);
				WebElement acclink = driver.findElement(NewAccount);
				Actions act = new Actions(driver);
				act.moveToElement(accTab).click(accTab).click(acclink).doubleClick().build().perform();
				AynaxUtil.explicitWait(3000);
				act.click().build().perform();
				Assert.assertTrue(flag, "Accounting link is not displayed");
				isTitleDisplayed();
				AynaxUtil.explicitWait(3000);

			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void enterAccName(String accName) throws Exception {
		if (isElementPresent(Name)) {
			setElement(Name).sendKeys(accName);

		}
	}

	public void enterStartingBalance(String bal) throws Exception {
		try {
			amountflag = isElementPresent(startBal);
			if (amountflag) {
				logger.info("Starting Balance textarea is displayed");
				//bal=Integer.parseInt(bal);
				setElement(startBal).sendKeys(bal);

				Assert.assertTrue(amountflag, " Starting Balance is not displayed");

			} else {

				logger.info("Starting Balance is not displayed");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void selectAccType() throws Exception {
		try {
			AccTypeflag = isElementPresent(AcctTypeDropdown);
			if (AccTypeflag) {
				logger.info("Account type dropdown is displayed");
				selectDropDown(AcctTypeDropdown, "Cash");
				Assert.assertTrue(AccTypeflag, " Category dropdown is not displayed");

			} else {

				System.out.println("New Expense tab is not displayed");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void enterDate() throws Exception {
		try {
			dateflag = isElementPresent(date);
			if (dateflag) {
				logger.info("Date calender is displayed");
				
				setElement(date).click();
				/*Thread.sleep(2000);
				
				javaScriptClick(nextBtn);
				WebElement datepicker = driver.findElement(By.className(" table-condensed"));  
				 List<WebElement> rows=datepicker.findElements(By.tagName("tr"));  
				List<WebElement> columns=datepicker.findElements(By.tagName("td"));  
				    
				for (WebElement cell: columns){  
				//Select 20th Date   
				if (cell.getText().equals("12")){  
				cell.findElement(By.linkText("12")).click();  
				break;  
				 }  
				}   */
				Assert.assertTrue(dateflag, " Date calender  is not displayed");

			} else {

				System.out.println("Date calender  is not displayed");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void clickSave() throws Exception {
		if (isElementPresent(SaveButton)) {
			setElement(SaveButton).click();
		}
	}

	public void newExpense(String accName, String bal) throws Exception {
		try {
			enterAccName(accName);
			enterDate();
			selectAccType();
			enterStartingBalance(bal);
			clickSave();
			AynaxUtil.explicitWait(3000);

		} catch (Exception e) {
			throw new Exception("New Expense link is not present in Expense page::");
		}
		// return new LoginPage(driver);
	}

	public boolean isTitleDisplayed() {

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
				logger.info("Success page of Account creation is displayed");
			String expectedtitle = getPageTitle();
			logger.info("Title is "+expectedtitle);
			Assert.assertTrue(expectedtitle.contains("Edit Account :: Aynax.com"));
			String text = getText(successDialog);
			logger.info("Success text"+text);
			Assert.assertTrue(text.contains("Account successfully saved!"));
			
			}			
			else
			{
				logger.info("Error page of Account creation is displayed");
				String expectedtitle = getPageTitle();
				Assert.assertTrue(expectedtitle.contains("New Account :: Aynax.com"));
				String text = getText(errorDialog);
				logger.info("The error text is" + text);
				Assert.assertTrue(text.startsWith("×"));
			}
		} 
		catch (Exception e) 
		{
			throw new Exception("New Account creation page is displayed::"+isResultPageDisplayed()+e.getLocalizedMessage());
		}
		return flag1;
	}
}
