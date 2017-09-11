package com.itc.test;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.itc.pages.NewCustomerPage;
import com.itc.util.BaseTestObject;
import com.itc.util.ExcelutilObject;

public class NewCustomerPageTest extends BaseTestObject {

	NewCustomerPage objNewCustPage = null;
	LoginPageTest login = new LoginPageTest();
	boolean flag = false;
	boolean flag1 = false;
	boolean titleflag = false;
	public static String excelPath = System.getProperty("user.dir")+"\\src\\test\\resources\\testdata\\testDataSheet.xlsx";

	@Parameters({ "browserType" })
	@Test(groups={"SanityTest"})
	
	public void Login() throws Exception {
		login.verifyLoginPage();

	}

	@Test(priority = 0, enabled = true,groups="SanityTest")
	public void verifyCustomerCreation1() throws Exception {
		try {
			objNewCustPage = new NewCustomerPage(driver);
			objNewCustPage.clickOnNewCustomer();
			String customerName =getValFromExcel(1,2);
		    String frmaddress =getValFromExcel(1,3);
		    String phone =getValFromExcel(1,4);
		    String fax =getValFromExcel(1,5);
		    String fname =getValFromExcel(1,6);
		    String lname =getValFromExcel(1,7);
		    String mail =getValFromExcel(1,8);
		    String phoneNo =getValFromExcel(1,9);
		    
		    objNewCustPage.newCustomerCreation(customerName, frmaddress, phone, fax, fname, lname, mail, phoneNo);
		    objNewCustPage.isResultPageDisplayed();
			//Assert.assertTrue(flag1);
			 objNewCustPage.isTitleDisplayed();

		}

		catch (Exception e) {
			throw new Exception("FAILED CLICK ON SITELOGO AND VERFIY PAGETITLE TESTCASE"
					+ "\n clickOnSiteLogoAndCheckThePageTitle" + e.getLocalizedMessage());
		}

	}
	@Test(priority = 1, enabled = true,groups="Regression")
	public void verifyCustomerCreation2() throws Exception {
		try {
			objNewCustPage = new NewCustomerPage(driver);
			objNewCustPage.clickOnNewCustomer();
			String customerName =getValFromExcel(2,2);
		    String frmaddress =getValFromExcel(2,3);
		    String phone =getValFromExcel(2,4);
		    String fax =getValFromExcel(2,5);
		    String fname =getValFromExcel(2,6);
		    String lname =getValFromExcel(2,7);
		    String mail =getValFromExcel(2,8);
		    String phoneNo =getValFromExcel(2,9);
		    
		    objNewCustPage.newCustomerCreation(customerName, frmaddress, phone, fax, fname, lname, mail, phoneNo);
		    objNewCustPage.isResultPageDisplayed();
			//Assert.assertTrue(flag1);
			objNewCustPage.isTitleDisplayed();

		}

		catch (Exception e) {
			throw new Exception("FAILED CLICK ON SITELOGO AND VERFIY PAGETITLE TESTCASE"
					+ "\n clickOnSiteLogoAndCheckThePageTitle" + e.getLocalizedMessage());
		}

	}
	@Test(priority = 2, enabled = true,groups="Regression")
	public void verifyCustomerCreation3() throws Exception {
		try {
			
			objNewCustPage = new NewCustomerPage(driver);
			objNewCustPage.clickOnNewCustomer();
			String customerName =getValFromExcel(5,6);
		    String frmaddress =getValFromExcel(1,3);
		    String phone =getValFromExcel(1,4);
		    String fax =getValFromExcel(1,5);
		    String fname =getValFromExcel(1,6);
		    String lname =getValFromExcel(1,7);
		    String mail =getValFromExcel(1,8);
		    String phoneNo =getValFromExcel(1,9);
		    
		    objNewCustPage.newCustomerCreation(customerName, frmaddress, phone, fax, fname, lname, mail, phoneNo);
		    objNewCustPage.isResultPageDisplayed();
			//Assert.assertTrue(flag1);
			 objNewCustPage.isTitleDisplayed();

		}

		catch (Exception e) {
			throw new Exception("FAILED CLICK ON SITELOGO AND VERFIY PAGETITLE TESTCASE"
					+ "\n clickOnSiteLogoAndCheckThePageTitle" + e.getLocalizedMessage());
		}

	}
public static String getValFromExcel(int row,int col) throws Exception{
		
		ExcelutilObject.setExcelFile(excelPath, "Customer");
		return ExcelutilObject.getCellData(row, col);
	}
}
