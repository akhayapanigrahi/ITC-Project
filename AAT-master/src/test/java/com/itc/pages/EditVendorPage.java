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

public class EditVendorPage extends BasePageObject {
	public EditVendorPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public static String excelPath = System.getProperty("user.dir")
			+ "\\src\\test\\resources\\testdata\\testDataSheet.xlsx";
	public static Logger logger = Logger.getLogger(EditVendorPage.class);

	boolean flag = false;
	boolean flag1 = false;
	boolean flag2 = false;
	boolean titleflag = false;
	boolean vendorflag = false;
	/* Web elements */

	By ExpensesTab = By.xpath(".//*[@id='top-menu']//a[contains(.,'Expenses')]");
	By ListVendorLink = By.xpath(".//*[@id='top-menu']//a[contains(.,'List of Vendors')]");
	By successDialog = By.xpath("//div[@class='alert alert-success alert-dismissable']");
	By errorDialog = By.xpath("//div[@class='alert alert-danger alert-dismissable']");
	By EditVendorName = By.name("name");
	By EditVendorAddress = By.name("address");
	By saveBtn = By.name("submitButton");

	public void clickOnListVendorLink() throws Exception {
		try {
			flag = isElementPresent(ExpensesTab);
			if (flag) {
				logger.info("Expense tab is displayed");
				WebElement expTab = driver.findElement(ExpensesTab);
				WebElement vendorlink = driver.findElement(ListVendorLink);
				Actions act = new Actions(driver);
				act.moveToElement(expTab).click(expTab).click(vendorlink).doubleClick().build().perform();
				AynaxUtil.explicitWait(3000);
				act.click().build().perform();
				AynaxUtil.explicitWait(3000);
			} else {
				logger.info("Expense tab is not displayed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickOnVendorLink1() throws Exception {
		try {

			String vendorName = getValFromExcel(1, 2);
			By VendorNameLink = By
					.xpath("//table[contains(@class,'data-table')]//tr//a[contains(.,'" + vendorName + "')]");
			logger.info("Vendor name" + vendorName);
			vendorflag = isElementPresent(VendorNameLink);
			if (vendorflag) {

				setElement(VendorNameLink).click();
				Assert.assertTrue(vendorflag, " Vendor Link is not displayed");

			} else {
				logger.info("Vendor Link is not displayed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void clickOnVendorLink2() throws Exception {
		try {
			String vendorName = getValFromExcel(2, 2);
			By VendorNameLink = By
					.xpath("//table[contains(@class,'data-table')]//tr//a[contains(.,'" + vendorName + "')]");
			logger.info("Vendor name" + vendorName);
			vendorflag = isElementPresent(VendorNameLink);
			if (vendorflag) {

				setElement(VendorNameLink).click();
				Assert.assertTrue(vendorflag, " Vendor Link is not displayed");

			} else {
				logger.info("Vendor Link is not displayed");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void editVendorName(String vendorName) throws Exception {
		if (isElementPresent(EditVendorName)) {
			clearAndEnterValueInTextBox(EditVendorName, vendorName);
		}
	}

	public void editVendorAddr(String address) throws Exception {
		if (isElementPresent(EditVendorAddress)) {
			clearAndEnterValueInTextBox(EditVendorAddress, address);

		}
	}

	public void clickSave() throws Exception {
		if (isElementPresent(saveBtn)) {
			setElement(saveBtn).click();
		}
	}

	public void EditVendor(String vendorname, String address) throws Exception {
		try {
			editVendorName(vendorname);
			editVendorAddr(address);
			clickSave();
			AynaxUtil.explicitWait(3000);
			waitForAnElement(ExpensesTab, 20);

		} catch (Exception e) {
			throw new Exception("New Vendor link is not present in Expense page::");
		}
		// return new LoginPage(driver);
	}

	public String isTitleDisplayed() {

		return getPageTitle();
		// return titleflag;
	}

	public boolean isResultPageDisplayed() throws Exception {
		try {
			flag1 = isElementPresent(successDialog);
			if (flag1) {
				logger.info("Success page of Edit Vendor  is displayed");
				String expectedtitle = getPageTitle();
				logger.info("Title is "+expectedtitle);
				Assert.assertTrue(expectedtitle.contains("Edit Vendor:: Aynax.com"));
				String text = getText(successDialog);
				logger.info("Success text" + text);
				Assert.assertTrue(text.contains("Vendor successfully saved"));

			} else {
				logger.info(" Vendor Edit error page  is displayed");
				String expectedtitle = getPageTitle();
				Assert.assertTrue(expectedtitle.contains("Edit Vendor:: Aynax.com"));
				String text = getText(errorDialog);
				logger.info("The error text is" + text);
				Assert.assertTrue(text.contains("Vendor name cannot be blank"));
			}
		} catch (Exception e) {
			throw new Exception("New Vendor page is displayed::" + isResultPageDisplayed() + e.getLocalizedMessage());
		}
		return flag1;
	}

	public static String getValFromExcel(int row, int col) throws Exception {

		ExcelutilObject.setExcelFile(excelPath, "VendorData");
		return ExcelutilObject.getCellData(row, col);
	}

}
