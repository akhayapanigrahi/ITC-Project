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

public class NewInvoicePage extends BasePageObject {

	public NewInvoicePage(WebDriver driver) {
		super(driver);
	}

	boolean flag = false;
	boolean custTypeflag = false;
	boolean toCustflag = false;
	boolean toCustAddrflag = false;
	boolean itemTypeflag = false;
	boolean titleflag = false;
	boolean flag1 = false;
	public static Logger logger = Logger.getLogger(NewInvoicePage.class);
	public static String excelPath = System.getProperty("user.dir")
			+ "\\src\\test\\resources\\testdata\\testDataSheet.xlsx";
	/* Web elements */

	By salesTab = By.xpath(".//*[@id='top-menu']//a[contains(.,'Sales')]");
	By newInvoice = By.xpath(".//*[@id='top-menu']//a[contains(.,'New Invoice')]");
	By name = By.id("from_name");
	By address = By.id("from_address");
	By heading = By.id("invoice_heading");
	By custDropdown = By.id("to_name");
	By custName = By.id("to_new_customer");
	By custAddr = By.id("to_address");
	By itemDropdown = By.xpath("//select[@id='itemSelect0']");
	By invoiceNotes = By.id("invoice_notes");
	By description = By.xpath("//textarea[@id='description[]']");
	By unitPrice = By.xpath("//td[@class='invoiceDataPriceOrQty']/input[@id='unit_price[]']");
	By quantity = By.xpath("//td[@class='invoiceDataPriceOrQty']/input[@id='qty[]']");
	By SaveButton = By.id("btnSave");
	By successDialog = By.xpath("//font[contains(.,'has been saved')]");
	By errorDialog = By.xpath("//font[contains(.,'Please enter')]");

	public void clickOnNewInvoice() throws Exception {
		try {
			flag = isElementPresent(salesTab);
			if (flag) {
				logger.info("New Accounting tab is displayed");
				WebElement expTab = driver.findElement(salesTab);
				WebElement vendorlink = driver.findElement(newInvoice);
				Actions act = new Actions(driver);
				act.moveToElement(expTab).click(expTab).click(vendorlink).doubleClick().build().perform();
				AynaxUtil.explicitWait(3000);
				act.click().build().perform();
				Assert.assertTrue(flag, "Accounting link is not displayed");
				String title = getPageTitle();
				Assert.assertTrue(title.contains("New Invoice :: Aynax.com"));
				AynaxUtil.explicitWait(3000);
			}
		} catch (Exception e) {
			throw new Exception("Expense tab is displayed::"+e.getLocalizedMessage());
		}
	}

	public void enterFromName(String frmname) throws Exception {
		if (isElementPresent(name)) {
			clearAndEnterValueInTextBox(name,frmname);
		

		}
	}

	public void enterFromAddress(String frmaddress) throws Exception {
		if (isElementPresent(address)) {
			clearAndEnterValueInTextBox(address,frmaddress);

		}
	}

	public void selectCustTypeNew() throws Exception {
		try {
			custTypeflag = isElementPresent(custDropdown);
			if (custTypeflag) {
				logger.info("Customer type dropdown is displayed");
				selectDropDown(custDropdown, "New Customer");
				Assert.assertTrue(custTypeflag, " Category dropdown is not displayed");

			} else {

				logger.info("New Invoice tab is not displayed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void selectCustTypeExisting() throws Exception {
		try {
			custTypeflag = isElementPresent(custDropdown);
			if (custTypeflag) {
				logger.info("Customer type dropdown is displayed");
				String customerName =getValFromExcel(1,2);
				
				selectDropDown(custDropdown, customerName);
				Assert.assertTrue(custTypeflag, " Category dropdown is not displayed");

			} else {

				logger.info("New Invoice tab is not displayed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void enterToCustName(String cust) throws Exception {
		try {
			toCustflag = isElementPresent(custName);
			if (toCustflag) {
				logger.info("Customer name textarea is displayed");
				clearAndEnterValueInTextBox(custName,cust);
				Assert.assertTrue(toCustflag, " Customer name is not displayed");

			} else {

				System.out.println("Customer name is not displayed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void enterToCustAddr(String addr) throws Exception {
		try {
			toCustAddrflag = isElementPresent(custAddr);
			if (toCustAddrflag) {
				logger.info("Customer address is displayed");
				clearAndEnterValueInTextBox(custAddr,addr);

				Assert.assertTrue(toCustAddrflag, " Customer address is not displayed");

			} else {

				logger.info("Customer address is not displayed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void selectItemType1() throws Exception {
		try {
			itemTypeflag = isElementPresent(itemDropdown);
			if (itemTypeflag) {
				logger.info("Item type dropdown is displayed");
					selectDropDown(itemDropdown, "Product");
					Assert.assertTrue(itemTypeflag, " Item dropdown is not displayed");

			} else {

				logger.info("New Invoice tab is not displayed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void selectItemType2() throws Exception {
		try {
			itemTypeflag = isElementPresent(itemDropdown);
			if (itemTypeflag) {
				logger.info("Item type dropdown is displayed");
					selectDropDown(itemDropdown, "Hours");
					Assert.assertTrue(itemTypeflag, " Item dropdown is not displayed");
			} else {

				logger.info("New Invoice tab is not displayed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void enterDescription(String des) throws Exception {
		itemTypeflag = isElementPresent(description);
		if (itemTypeflag) {
				clearAndEnterValueInTextBox(description,des);
				Assert.assertTrue(itemTypeflag, " Item dropdown is not displayed");

		}
	}
	

	public void enterUnitPrice(String price) throws Exception {
		if (isElementPresent(unitPrice)) {
				clearAndEnterValueInTextBox(unitPrice,price);

		}
	}

	public void enterQuantity(String quan) throws Exception {
		if (isElementPresent(quantity)) {
				clearAndEnterValueInTextBox(quantity,quan);

		}
	}
	public void enterInvoiceNotes(String notes) throws Exception {
		if (isElementPresent(invoiceNotes)) {
			clearAndEnterValueInTextBox(invoiceNotes,notes);

		}
	}

	public void clickSave() throws Exception {
		if (isElementPresent(SaveButton)) {
			setElement(SaveButton).click();
		}
	}

	public void newInvoiceCreation(String fromname, String fromaddress, String custname, String address, String desc,
			String Uniprice, String Itemquan, String invnotes) throws Exception {
		try {
			enterFromName(fromname);
			enterFromAddress(fromaddress);
			selectCustTypeNew();
			enterToCustName(custname);
			enterToCustAddr(address);
			selectItemType1();
			enterDescription(desc);
			enterUnitPrice(Uniprice);
			enterQuantity(Itemquan);
			enterInvoiceNotes(invnotes);
			clickSave();

		} catch (Exception e) {
			throw new Exception("New Expense link is not present in Expense page::");
		}
	}
	public void newInvoiceCreation1(String fromname, String fromaddress, String address, String desc,
			String Uniprice, String Itemquan, String invnotes) throws Exception {
		try {
			enterFromName(fromname);
			enterFromAddress(fromaddress);
			selectCustTypeExisting();
			//enterToCustName(custname);
			enterToCustAddr(address);
			selectItemType1();
			enterDescription(desc);
			enterUnitPrice(Uniprice);
			enterQuantity(Itemquan);
			enterInvoiceNotes(invnotes);
			clickSave();

		} catch (Exception e) {
			throw new Exception("New Expense link is not present in Expense page::");
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
				logger.info("Success page of Invoice creation is displayed");
				String expectedtitle = getPageTitle();
				logger.info("Title is "+expectedtitle);
				Assert.assertTrue(expectedtitle.contains("Edit Invoice :: Aynax.com"));
				String text = getText(successDialog);
				logger.info("Success text"+text);
				
				Assert.assertTrue(text.startsWith("Invoice"));

			} else {

				logger.info("New Invoice creation error page is displayed");
				String expectedtitle = getPageTitle();
				Assert.assertTrue(expectedtitle.contains("New Invoice :: Aynax.com"));
				String text = getText(errorDialog);
				logger.info("The error text is" + text);
				Assert.assertTrue(text.startsWith("Customer name cannot be blan"));

			}
		} catch (Exception e) {
			throw new Exception("New Journal page is displayed::" + isResultPageDisplayed() + e.getLocalizedMessage());
		}
		return flag1;
	}
public static String getValFromExcel(int row,int col) throws Exception{
		
		ExcelutilObject.setExcelFile(excelPath, "Customer");
		return ExcelutilObject.getCellData(row, col);
	}
}
