package com.itc.test;

import org.testng.annotations.Test;

import com.itc.pages.NewInvoicePage;
import com.itc.util.BaseTestObject;
import com.itc.util.ExcelutilObject;

public class NewInvoicePageTest extends BaseTestObject {

	NewInvoicePage objNewInvoicePage = null;
	LoginPageTest login = new LoginPageTest();
	boolean flag = false;
	boolean flag1 = false;
	boolean titleflag = false;
	public static String excelPath = System.getProperty("user.dir")+"\\src\\test\\resources\\testdata\\testDataSheet.xlsx";

	//@Parameters({ "browserType"})
	//@Test(priority = 0, enabled = true,groups={"SanityTest","Regression"})
	public void Login() throws Exception {
		login.verifyLoginPage();

	}


	@Test(priority = 0, enabled = true,groups="SanityTest")
	public void verifyInvoiceCreation1() throws Exception {
		try {
			objNewInvoicePage = new NewInvoicePage(driver);
			objNewInvoicePage.clickOnNewInvoice();
			String fromname =getValFromExcel(1,2);
		    String fromaddress =getValFromExcel(1,3);
		    String custname =getValFromExcel(1,4);
		    String address =getValFromExcel(1,5);
		    String desc =getValFromExcel(1,6);
		    String price =getValFromExcel(1,7);
		    String Itemquan =getValFromExcel(1,8);
		    String invnotes =getValFromExcel(1,9);
		    
			objNewInvoicePage.newInvoiceCreation(fromname, fromaddress, custname, address, desc, price, Itemquan, invnotes);
			 objNewInvoicePage.isResultPageDisplayed();
			//Assert.assertTrue(flag1);
			titleflag = objNewInvoicePage.isTitleDisplayed();

		}

		catch (Exception e) {
			throw new Exception("FAILED CLICK ON SITELOGO AND VERFIY PAGETITLE TESTCASE"
					+ "\n clickOnSiteLogoAndCheckThePageTitle" + e.getLocalizedMessage());
		}

	}
	@Test(priority = 1, enabled = true,groups="SanityTest")
	public void verifyInvoiceCreation2() throws Exception {
		try {
			objNewInvoicePage = new NewInvoicePage(driver);
			objNewInvoicePage.clickOnNewInvoice();
			String fromname =getValFromExcel(2,2);
		    String fromaddress =getValFromExcel(2,3);
		    String address =getValFromExcel(2,5);
		    String desc =getValFromExcel(2,6);
		    String price =getValFromExcel(2,7);
		    String Itemquan =getValFromExcel(2,8);
		    String invnotes =getValFromExcel(2,9);
		    
			objNewInvoicePage.newInvoiceCreation1(fromname, fromaddress,address, desc, price, Itemquan, invnotes);
			 objNewInvoicePage.isResultPageDisplayed();
			//Assert.assertTrue(flag1);
			titleflag = objNewInvoicePage.isTitleDisplayed();

		}

		catch (Exception e) {
			throw new Exception("FAILED CLICK ON SITELOGO AND VERFIY PAGETITLE TESTCASE"
					+ "\n clickOnSiteLogoAndCheckThePageTitle" + e.getLocalizedMessage());
		}

	}
	@Test(priority = 2, enabled = true,groups="Regression")
	public void verifyInvoiceCreation3() throws Exception {
		try {
			//login.verifyLoginPage();
			objNewInvoicePage = new NewInvoicePage(driver);
			objNewInvoicePage.clickOnNewInvoice();
			String fromname =getValFromExcel(2,2);
		    String fromaddress =getValFromExcel(2,3);
		    String custname =getValFromExcel(5,6);
		    String address =getValFromExcel(2,5);
		    String desc =getValFromExcel(2,6);
		    String price =getValFromExcel(2,7);
		    String Itemquan =getValFromExcel(2,8);
		    String invnotes =getValFromExcel(2,9);
		    
			objNewInvoicePage.newInvoiceCreation(fromname, fromaddress, custname, address, desc, price, Itemquan, invnotes);
			 objNewInvoicePage.isResultPageDisplayed();
			//Assert.assertTrue(flag1);
			titleflag = objNewInvoicePage.isTitleDisplayed();

		}

		catch (Exception e) {
			throw new Exception("FAILED CLICK ON SITELOGO AND VERFIY PAGETITLE TESTCASE"
					+ "\n clickOnSiteLogoAndCheckThePageTitle" + e.getLocalizedMessage());
		}

	}
public static String getValFromExcel(int row,int col) throws Exception{
		
		ExcelutilObject.setExcelFile(excelPath, "Invoice");
		return ExcelutilObject.getCellData(row, col);
	}
}
