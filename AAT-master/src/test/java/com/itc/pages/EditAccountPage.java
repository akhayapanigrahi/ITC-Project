package com.itc.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.itc.common.AynaxUtil;
import com.itc.util.BasePageObject;
import com.itc.util.ExcelutilObject;

public class EditAccountPage extends BasePageObject {

	public EditAccountPage(WebDriver driver) {
		super(driver);
	}

	boolean flag = false;
	boolean nameflag = false;
	boolean AccTypeflag = false;
	boolean amountflag = false;
	boolean titleflag = false;
	boolean flag1 = false;
	boolean dateflag = false;
	public static String excelPath = System.getProperty("user.dir")+"\\src\\test\\resources\\testdata\\testDataSheet.xlsx";
	public static Logger logger = Logger.getLogger(EditAccountPage.class);

	/* Web elements */

	By AccountingTab = By.xpath(".//*[@id='top-menu']//a[contains(.,'Accounting')]");
	By chartAccount = By.xpath(".//*[@id='top-menu']//a[contains(.,'Chart of Accounts')]");
	By Name = By.name("p_name");
	By date = By.id("p_date");
	By AcctTypeDropdown = By.name("p_type");
	By startBal = By.xpath("//div[@id='starting_balance']/input");
	By SaveButton = By.xpath("//button[@data-name='submitButton']");
	By successDialog=By.xpath("//div[@class='alert alert-success alert-dismissable']");
	By errorDialog=By.xpath("//div[@class='alert alert-danger alert-dismissable']");

	public void clickOnChartAccount() throws Exception {
		try {
			flag = isElementPresent(AccountingTab);
			if (flag) {
				logger.info("New Accounting tab is displayed");
				WebElement accTab = driver.findElement(AccountingTab);
				WebElement acclink = driver.findElement(chartAccount);
				Actions act = new Actions(driver);
				act.moveToElement(accTab).click(accTab).click(acclink).doubleClick().build().perform();
				AynaxUtil.explicitWait(3000);
				act.click().build().perform();
				Assert.assertTrue(flag, "Accounting link is not displayed");
				isTitleDisplayed();
				AynaxUtil.explicitWait(3000);

		}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void clickOnAccountLink1() throws Exception {
		try {
			String accountName =getValFromExcel(1,2);
			By accountNameLink=By.xpath("//table[contains(@class,'data-table margin-top')]//tr//a[contains(.,'"+accountName+"')]");
			logger.info("Account name"+accountName);
			if (isElementPresent(accountNameLink)) 
			{
			setElement(accountNameLink).click();
			//Assert.assertTrue(isElementPresent(VendorNameLink), " Vendor Link is not displayed");
			
			}
			else{
				logger.info("Account Link is not displayed");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void clickOnAccountLink2() throws Exception {
		try {
			String accountName =getValFromExcel(2,2);
			By accountNameLink=By.xpath("//table[contains(@class,'data-table margin-top')]//tr//a[contains(.,'"+accountName+"')]");
			logger.info("Account name"+accountName);
			if (isElementPresent(accountNameLink)) 
			{
			setElement(accountNameLink).click();
			//Assert.assertTrue(isElementPresent(VendorNameLink), " Vendor Link is not displayed");
			
			}
			else{
				logger.info("Account Link is not displayed");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void editAccountName(String accName) throws Exception {
		if (isElementPresent(Name)) {
			
			clearAndEnterValueInTextBox(Name,accName);
		}
	}

	public void editStartingBalance(String bal) throws Exception {
		try {
			amountflag = isElementPresent(startBal);
			if (amountflag) {
				logger.info("Starting Balance textarea is displayed");
				clearAndEnterValueInTextBox(startBal,bal);
				Assert.assertTrue(amountflag, " Starting Balance is not displayed");

			} else {
				logger.info("Starting Balance is not displayed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void selectAccType() throws Exception {
		try {
			AccTypeflag = isElementPresent(AcctTypeDropdown);
			if (AccTypeflag) {
				logger.info("Account type dropdown is displayed");
				selectDropDown(AcctTypeDropdown, "Bank Account");
				Assert.assertTrue(AccTypeflag, " Category dropdown is not displayed");

			} else {
				logger.info("New Expense tab is not displayed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void enterDate() throws Exception {
		try {
			dateflag = isElementPresent(date);
			if (dateflag) {
				logger.info("Date calender is displayed");
				setElement(date).click();
				Assert.assertTrue(dateflag, " Date calender  is not displayed");

			} else {
				logger.info("Date calender  is not displayed");
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

	public void editExpense(String accName, String bal) throws Exception {
		try {
			editAccountName(accName);
			selectAccType();
			editStartingBalance(bal);
			clickSave();
			AynaxUtil.explicitWait(3000);

		} catch (Exception e) {
			throw new Exception("New Expense link is not present in Expense page::");
		}
	}

public String isTitleDisplayed(){
		
		return getPageTitle();
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
			Assert.assertTrue(expectedtitle.contains("Edit Account :: Aynax.com"));
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

public static String getValFromExcel(int row,int col) throws Exception{
	
	ExcelutilObject.setExcelFile(excelPath, "Account");
	return ExcelutilObject.getCellData(row, col);
}


}