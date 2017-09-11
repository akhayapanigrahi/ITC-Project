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

public class ReciptPage extends BasePageObject {

	public ReciptPage(WebDriver driver) {
		super(driver);
	}

	boolean flag = false;
	boolean flag1 = false;
	boolean flag2 = false;
	boolean titleflag = false;
	public static Logger logger = Logger.getLogger(ReciptPage.class);
	/* Web elements */

	By expensesTab = By.xpath(".//*[@id='top-menu']//a[contains(.,'Expenses')]");
	By reciptLink = By.xpath(".//*[@id='top-menu']//a[contains(.,'List of Receipts')]");
	By uploadLink = By.linkText("Upload Receipt");
	By uploadBtn = By.name("uploadfile");
	By successDialog = By.id("no_vendor");
	By errorDialog = By.id("no_vendor");
	By browseBtn = By.id("uploadFileButton");
	By attachLink = By.linkText("Attach to expense");
	By deleteConfBtn = By.id("deleteButton");
	//By rowSelect = By.xpath("//table[@class='table-tablist jSelectableRow']//tr[1]");
	By closeBtn=By.xpath("//h4[contains(.,'Attach Receipt to Expense')]/preceding-sibling::button");
	By saveAndAttachBtn = By.id("saveAttachedListReceipt");
	By newExpenseSelect = By.id("newExpenseLabel");
	By billdate = By.id("billDateReceipt");
	By dueDate = By.id("dueDateReceipt");
	By vendorDropdown = By.xpath("//div[@id='vendor_dropdown_div']");
	By newVendor = By.id("new_vendor");
	By amount = By.id("amountReceipt");
	By categoryDropdown = By.xpath("//select[@id='categoryReceipt']");
	By paidDropdown = By.xpath("//select[@id='paidReceipt']");
	By logOut=By.linkText("Log out");
	public static String excelPath = System.getProperty("user.dir")+"\\src\\test\\resources\\testdata\\testDataSheet.xlsx";
	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\testdata\\SampleDoc.docx";

	public void clickOnLisRecipt() throws Exception {
		try {
			flag = isElementPresent(expensesTab);
			if (flag) {
				logger.info("Expense tab is displayed");
				WebElement expTab = driver.findElement(expensesTab);
				WebElement reciptlink = driver.findElement(reciptLink);
				Actions act = new Actions(driver);
				act.moveToElement(expTab).click(expTab).click(reciptlink).doubleClick().build().perform();
				AynaxUtil.explicitWait(3000);
				act.click().build().perform();
				AynaxUtil.explicitWait(3000);

			} else {
				logger.info("Expense tab is not displayed");

			}
		} catch (Exception e) {
			// e.printStackTrace();
			throw new Exception("Expense tab is displayed::" + e.getLocalizedMessage());
		}
		// return flag;
	}

	public void uploadRecipt() throws Exception {

		try {
			setElement(uploadLink).click();
			driver.findElement(browseBtn).sendKeys(filePath);
			setElement(uploadBtn).click();
			setElement(attachLink).click();
			String vendorName = getValFromExcel(3, 2);
			By rowSelect = By.xpath("//td[contains(.,'"+vendorName+"')]");
			setElement(rowSelect).click();
			if (isElementPresent(saveAndAttachBtn)) {
				setElement(saveAndAttachBtn).click();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void uploadReciptUsingAutoIt() throws Exception {

			setElement(uploadLink).click();
			setElement(browseBtn).click();
			Runtime.getRuntime().exec(System.getProperty("user.dir") +"\\src\\test\\resources\\testdata\\SampleUpload.exe");
			Thread.sleep(6000);
			setElement(uploadBtn).click();
			setElement(attachLink).click();
			Thread.sleep(3000);
			String vendorName = getValFromExcel(3, 2);
			By rowSelect = By.xpath("//td[contains(.,'"+vendorName+"')]");
			setElement(rowSelect).click();
			if (isElementPresent(saveAndAttachBtn)) {
				setElement(saveAndAttachBtn).click();
				Thread.sleep(10000);
			}
			if (isElementPresent(closeBtn)) {
				setElement(closeBtn).click();
			}
			/*By listReciptDeleteDrop=By.xpath("//div[@class='dropdown open']/ul[@class='dropdown-menu listreceipt-dropdown-menu']/li/button[@data-target='#confirmDelete']");
			Thread.sleep(10000);
			setElement(listReciptDeleteDrop).click();
			setElement(deleteConfBtn).click();*/
			
	}
	
	public void logout() throws Exception{
		setElement(logOut).click();
		
		
	}
	public boolean isTitleDisplayed() {

		getPageTitle();
		return titleflag;
	}

	public boolean isResultPageDisplayed() throws Exception {
		try {
			flag1 = isElementPresent(successDialog);
			if (flag1) {
				logger.info("Success page of upload Recipt is displayed");

				String expectedtitle = getPageTitle();
				logger.info(expectedtitle);

				Assert.assertTrue(expectedtitle.contains("List of Receipts:: Aynax.com"));
				String text = getText(successDialog);
				logger.info("Success text" + text);
				Assert.assertTrue(text.contains("×"));

			} else {
				logger.info("New upload Recipt  error page  is displayed");
				String expectedtitle = getPageTitle();
				Assert.assertTrue(expectedtitle.contains("List of Receipts:: Aynax.com"));
				String text = getText(errorDialog);
				logger.info("The error text is" + text);
				Assert.assertTrue(text.contains("×"));
			}
		} catch (Exception e) {
			throw new Exception(
					"upload Recipt page is displayed::" + isResultPageDisplayed() + e.getLocalizedMessage());
		}
		return flag1;
	}
	public static String getValFromExcel(int row, int col) throws Exception {

		ExcelutilObject.setExcelFile(excelPath, "VendorData");
		return ExcelutilObject.getCellData(row, col);
	}
}
