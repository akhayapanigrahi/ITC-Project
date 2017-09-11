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

public class DeleteVendorPage extends BasePageObject {

	public DeleteVendorPage(WebDriver driver) {
		super(driver);
	}

	public static String excelPath = System.getProperty("user.dir")
			+ "\\src\\test\\resources\\testdata\\testDataSheet.xlsx";
	public static Logger logger = Logger.getLogger(DeleteVendorPage.class);
	boolean flag = false;
	boolean flag1 = false;
	boolean titleflag = false;

	/* Web elements */
	By ExpensesTab = By.id("tmpli");
	By ListVendorLink = By.xpath("//a[contains(text(),'List of Vendors')]");
	By successDialog = By.xpath("//div[@class='alert alert-success alert-dismissable']");
	By errorDialog = By.xpath("//div[@class='alert alert-danger alert-dismissable']");
	By deleteConfBtn = By.id("deleteButton");

	NewVendorPage vendor = new NewVendorPage(driver);

	public void clickOnListVendor() throws Exception {
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
				logger.error("Expense tab is not displayed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void vendorDeletion1() {

		try {
			String vendorName = getValFromExcel(1, 2);
			By dropdown = By.xpath("//table[contains(@class,'data-table')]//tr//a[contains(.,'" + vendorName
					+ "')]/preceding::td[1]//a[contains(@class,'toggle-table-edit')]");
			By deleteBtn = By.xpath("//table[contains(@class,'data-table')]//tr//a[contains(.,'" + vendorName
					+ "')]/preceding::ul[@role='menu'][1]//button[contains(.,'Delete')]");
			setElement(dropdown).click();
			setElement(deleteBtn).click();
			AynaxUtil.explicitWait(3000);
			setElement(deleteConfBtn).click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void vendorDeletion2() {

		try {
			String vendorName = getValFromExcel(2, 2);
			By dropdown = By.xpath("//table[contains(@class,'data-table')]//tr//a[contains(.,'" + vendorName
					+ "')]/preceding::td[1]//a[contains(@class,'toggle-table-edit')]");
			By deleteBtn = By.xpath("//table[contains(@class,'data-table')]//tr//a[contains(.,'" + vendorName
					+ "')]/preceding::ul[@role='menu'][1]//button[contains(.,'Delete')]");
			setElement(dropdown).click();
			setElement(deleteBtn).click();
			AynaxUtil.explicitWait(3000);
			setElement(deleteConfBtn).click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String isTitleDisplayed() {

		return getPageTitle();
		// return titleflag;
	}

	
	public boolean isResultPageDisplayed() throws Exception {
		try {
			flag1 = isElementPresent(successDialog);
			if (flag1) {
				logger.info("Success page of vendor deletion is displayed");
				String expectedtitle = getPageTitle();
				logger.info("Title is "+expectedtitle);
				Assert.assertTrue(expectedtitle.contains("List of Vendors:: Aynax.com"));
				String text = getText(successDialog);
				logger.info("Success text" + text);
				Assert.assertTrue(text.contains("Vendor successfully deleted!"));

			} else {
				logger.info("Error page of vendor deletion is displayed");
				String expectedtitle = getPageTitle();
				Assert.assertTrue(expectedtitle.contains("List of Vendors:: Aynax.com"));
				String text = getText(errorDialog);
				logger.info("The error text is" + text);
				Assert.assertTrue(text.contains("There are expenses recorded on this vendor. Please delete all expenses before you delete the vendor. "));
			}
		} catch (Exception e) {
			throw new Exception("New Vendor page is displayed::" + isResultPageDisplayed() + e.getLocalizedMessage());
		}
		return flag1;
	}

	public static String getValFromExcel(int row, int col) throws Exception {

		ExcelutilObject.setExcelFile(excelPath, "EditVendorData");
		return ExcelutilObject.getCellData(row, col);
	}

}
