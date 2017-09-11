package com.itc.test;

import org.testng.annotations.Test;

import com.itc.pages.NewExpensePage;
import com.itc.util.BaseTestObject;
import com.itc.util.ExcelutilObject;

public class NewExpensePageTest extends BaseTestObject {

	NewExpensePage objNewExpensePage = null;
	LoginPageTest login = new LoginPageTest();
	boolean flag = false;
	boolean flag1 = false;
	boolean titleflag = false;
	public static String excelPath = System.getProperty("user.dir")+"\\src\\test\\resources\\testdata\\testDataSheet.xlsx";
	
	//@Parameters({"browserType"})
	//@Test(priority = 0, enabled = true,groups="SanityTest")
	public void Login() throws Exception {
		login.verifyLoginPage();

	}

	
	@Test(priority = 0, enabled = true,groups="SanityTest")
	public void verifyExpenseCreation1() throws Exception {
		try {
			objNewExpensePage = new NewExpensePage(driver);
			objNewExpensePage.clickOnExpenseTab();
			String name=getValFromExcel(3,2);
			String amount=getValFromExcel(1, 4);
			String notes=getValFromExcel(1, 5);
			objNewExpensePage.newExpenseWithNameInput(name,amount,notes);
			 objNewExpensePage.isResultPageDisplayed();
			//Assert.assertTrue(flag1);
			 objNewExpensePage.isTitleDisplayed();

		}

		catch (Exception e) {
			throw new Exception("FAILED CLICK ON SITELOGO AND VERFIY PAGETITLE TESTCASE"
					+ "\n clickOnSiteLogoAndCheckThePageTitle" + e.getLocalizedMessage());
		}

	}
	@Test(priority = 1, enabled = true,groups="SanityTest")
	public void verifyExpenseCreation2() throws Exception {
		try {
			objNewExpensePage = new NewExpensePage(driver);
			objNewExpensePage.clickOnExpenseTab();
			String name=getValFromExcel(4,2);
			String amount=getValFromExcel(2, 4);
			String notes=getValFromExcel(2, 5);
			objNewExpensePage.newExpenseWithNameSelection(name,amount,notes);
			 objNewExpensePage.isResultPageDisplayed();
			//Assert.assertTrue(flag1);
			objNewExpensePage.isTitleDisplayed();

		}

		catch (Exception e) {
			throw new Exception("FAILED CLICK ON SITELOGO AND VERFIY PAGETITLE TESTCASE"
					+ "\n clickOnSiteLogoAndCheckThePageTitle" + e.getLocalizedMessage());
		}
	}
		@Test(priority = 2, enabled = true,groups="Regression")
		public void verifyExpenseCreation3() throws Exception {
			try {
				objNewExpensePage = new NewExpensePage(driver);
				objNewExpensePage.clickOnExpenseTab();
				String name=getValFromExcel(4,2);
				String amount=getValFromExcel(6, 6);
				String notes=getValFromExcel(2, 5);
				objNewExpensePage.newExpenseWithNameSelection(name,amount,notes);
				 objNewExpensePage.isResultPageDisplayed();
				//Assert.assertTrue(flag1);
				 objNewExpensePage.isTitleDisplayed();

			}

			catch (Exception e) {
				throw new Exception("FAILED CLICK ON SITELOGO AND VERFIY PAGETITLE TESTCASE"
						+ "\n clickOnSiteLogoAndCheckThePageTitle" + e.getLocalizedMessage());
			}
	}
	
	
public static String getValFromExcel(int row,int col) throws Exception{
		
		ExcelutilObject.setExcelFile(excelPath, "VendorData");
		return ExcelutilObject.getCellData(row, col);
	}

	

}
