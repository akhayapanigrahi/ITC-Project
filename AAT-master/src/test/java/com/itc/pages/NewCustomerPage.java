package com.itc.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.itc.common.AynaxUtil;
import com.itc.util.BasePageObject;

public class NewCustomerPage extends BasePageObject {

	public NewCustomerPage(WebDriver driver) {
		super(driver);
	}

	boolean flag = false;
	boolean custTypeflag = false;
	boolean toCustflag = false;
	boolean toCustAddrflag = false;
	boolean itemTypeflag = false;
	boolean titleflag = false;
	boolean flag1 = false;
	public static Logger logger = Logger.getLogger(ReciptPage.class);

	/* Web elements */

	By salesTab = By.xpath(".//*[@id='top-menu']//a[contains(.,'Sales')]");
	By newCustomer = By.xpath(".//*[@id='top-menu']//a[contains(.,'New Customer')]");
	By custname = By.xpath("//input[@value='Customer name']");
	By address = By.id("to_address");
	By phNo=By.name("to_phone");
	By faxNo = By.xpath("//input[@value='Fax #']");
	By firstName = By.xpath("//input[@value='First name']");
	By lastName = By.xpath("//input[@value='Last name']");
	By email = By.xpath("//input[@value='E-mail address']");
	By phNumber = By.name("phone[]");
	By SaveButton = By.name("submitButton");
	By successDialog = By.xpath("//font[contains(.,'Customer successfully')]");
	By errorDialog = By.xpath("//font[contains(.,'You must enter')]");

	public void clickOnNewCustomer() throws Exception {
		try {
			flag = isElementPresent(salesTab);
			if (flag) {
				logger.info("New Customer tab is displayed");
				WebElement SalesTab = driver.findElement(salesTab);
				WebElement custlink = driver.findElement(newCustomer);
				Actions act = new Actions(driver);
				act.moveToElement(SalesTab).click(SalesTab).click(custlink).doubleClick().build().perform();
				AynaxUtil.explicitWait(3000);
				act.click().build().perform();
				Assert.assertTrue(flag, "Accounting link is not displayed");
				String title = getPageTitle();
				Assert.assertTrue(title.contains("New Customer :: Aynax.com"));
				AynaxUtil.explicitWait(3000);
			}
		} catch (Exception e) {
			throw new Exception("New Customer tab is not displayed::"+e.getLocalizedMessage());
		}
	}

	public void enterCustName(String customerName) throws Exception {
		if (isElementPresent(custname)) {
			clearAndEnterValueInTextBox(custname,customerName);
		

		}
	}

	public void enterAddress(String frmaddress) throws Exception {
		if (isElementPresent(address)) {
			clearAndEnterValueInTextBox(address,frmaddress);

		}
	}

		public void enterPhoneNumber(String phone) throws Exception {
		try {
			toCustflag = isElementPresent(phNo);
			if (toCustflag) {
				logger.info("Phone number textarea is displayed");
				clearAndEnterValueInTextBox(phNo,phone);
				Assert.assertTrue(toCustflag, " Phone number is not displayed");

			} else {
				logger.error("Phone number field is not displayed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
		public void enterFaxNumber(String fax) throws Exception {
			try {
				toCustflag = isElementPresent(faxNo);
				if (toCustflag) {
					logger.info("Fax number textarea is displayed");
					clearAndEnterValueInTextBox(faxNo,fax);
					Assert.assertTrue(toCustflag, " Fax number field is not displayed");

				} else {
					logger.error("Fax number field is not displayed");

				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	public void enterFirstname(String fname) throws Exception {
		try {
			toCustAddrflag = isElementPresent(firstName);
			if (toCustAddrflag) {
				logger.info("First name field is displayed");
				clearAndEnterValueInTextBox(firstName,fname);
				Assert.assertTrue(toCustAddrflag, " First name field is not displayed");

			} else {
				logger.error("First name field is not displayed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void enterLastname(String lname) throws Exception {
		try {
			toCustAddrflag = isElementPresent(lastName);
			if (toCustAddrflag) {
				logger.info("Last name field is displayed");
				clearAndEnterValueInTextBox(lastName,lname);
				Assert.assertTrue(toCustAddrflag, " Last name field is not displayed");

			} else {
				logger.error("Last name field is not displayed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void enterEmail(String mail) throws Exception {
		try {
			toCustflag = isElementPresent(email);
			if (toCustflag) {
				logger.info("Email textarea is displayed");
				clearAndEnterValueInTextBox(email,mail);
				Assert.assertTrue(toCustflag, " Email  is not displayed");

			} else {
				logger.error("Email field is not displayed");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void enterPhoneNumberContacts(String phoneNo) throws Exception {
		try {
			toCustflag = isElementPresent(phNumber);
			if (toCustflag) {
				logger.info("Phone number textarea is displayed");
				clearAndEnterValueInTextBox(phNumber,phoneNo);
				Assert.assertTrue(toCustflag, " Phone number is not displayed");

			} else {
				logger.error("Phone number field is not displayed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void clickSave() throws Exception {
		if (isElementPresent(SaveButton)) {
			setElement(SaveButton).click();
		}
	}

	public void newCustomerCreation(String customerName, String frmaddress, String phone, String fax, String fname,
			String lname, String mail, String phoneNo) throws Exception {
		try {
			enterCustName(customerName);
			enterAddress(frmaddress);
			enterPhoneNumber(phone);
			enterFaxNumber(fax);
			enterFirstname(fname);
			enterLastname(lname);
			enterEmail(mail);
			enterPhoneNumberContacts(phoneNo);
			clickSave();

		} catch (Exception e) {
			throw new Exception("New Customer is not present in Expense page::");
		}
	}
	
	public boolean isTitleDisplayed() {

		getPageTitle();
		return titleflag;
	}

	
	public boolean isResultPageDisplayed() throws Exception {
		try {

			flag1 = isElementPresent(successDialog);
			if (flag1) {
				logger.info("Success page of Customer creation is displayed");
				String expectedtitle = getPageTitle();
				logger.info("Title displayed is"+expectedtitle);
				Assert.assertTrue(expectedtitle.startsWith("Edit Customer ::"));
				String text = getText(successDialog);
				logger.info("Success text"+text);
				Assert.assertTrue(text.startsWith("Customer"));

			} else {
				logger.info("New customer creation error page is displayed");
				String expectedtitle = getPageTitle();
				Assert.assertTrue(expectedtitle.startsWith("New Customer ::"));
				String text = getText(errorDialog);
				logger.info("The error text is" + text);
				Assert.assertTrue(text.contains("You must enter a name in the customer name field."));
			}
		} catch (Exception e) {
			throw new Exception("New Journal page is displayed::" + isResultPageDisplayed() + e.getLocalizedMessage());
		}
		return flag1;
	}

}
