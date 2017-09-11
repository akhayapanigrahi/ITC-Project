package com.itc.test;

import org.testng.annotations.Test;

import com.itc.pages.EditExpensePage;
import com.itc.util.BaseTestObject;
import com.itc.util.ExcelutilObject;

public class EditExpensePageTest extends BaseTestObject {

	EditExpensePage objEditExpensePage = null;
	LoginPageTest login = new LoginPageTest();
	boolean flag = false;
	boolean flag1 = false;
	boolean titleflag = false;
	public static String excelPath = System.getProperty("user.dir")
			+ "\\src\\test\\resources\\testdata\\testDataSheet.xlsx";

	//@Parameters({ "browserType" })
	//@Test(priority = 0, enabled = true, groups = {"SanityTest","Regression"})
	public void Login() throws Exception {
		login.verifyLoginPage();

	}

	@Test(priority = 0, enabled = true, groups = "SanityTest")
	public void verifyEditExpenseCreation1() throws Exception {
		try {
			objEditExpensePage = new EditExpensePage(driver);
			objEditExpensePage.clickOnListExpense();
			objEditExpensePage.clickOnExpenseLink1();
			String amount = getValFromExcel(1,4);
			String notes = getValFromExcel(1,5);
			objEditExpensePage.editExpense(amount, notes);
			 objEditExpensePage.isResultPageDisplayed();
			// Assert.assertTrue(flag1);
			objEditExpensePage.isTitleDisplayed();

		}

		catch (Exception e) {
			throw new Exception("FAILED CLICK ON SITELOGO AND VERFIY PAGETITLE TESTCASE"
					+ "\n clickOnSiteLogoAndCheckThePageTitle" + e.getLocalizedMessage());
		}

	}

	@Test(priority = 1, enabled = true, groups = "SanityTest")
	public void verifyEditExpenseCreation2() throws Exception {
		try {

			objEditExpensePage.clickOnExpenseLink2();
			String amount = getValFromExcel(2, 4);
			String notes = getValFromExcel(2, 5);
			objEditExpensePage.editExpense(amount, notes);
			objEditExpensePage.isResultPageDisplayed();
			// Assert.assertTrue(flag1);
			 objEditExpensePage.isTitleDisplayed();

		}

		catch (Exception e) {
			throw new Exception("FAILED CLICK ON SITELOGO AND VERFIY PAGETITLE TESTCASE"
					+ "\n clickOnSiteLogoAndCheckThePageTitle" + e.getLocalizedMessage());
		}

	}

	//@Test(priority = 3, enabled = true, groups = "Regression")
	public void verifyEditExpenseCreation3() throws Exception {
		try {
			
			NewExpensePageTest obj=new NewExpensePageTest();
			obj.verifyExpenseCreation1();
			objEditExpensePage.clickOnExpenseLink1();
			String amount = getValFromExcel(5, 6);
			String notes = getValFromExcel(4, 5);
			objEditExpensePage.editExpense(amount, notes);
			flag1 = objEditExpensePage.isResultPageDisplayed();
			// Assert.assertTrue(flag1);
			titleflag = objEditExpensePage.isTitleDisplayed();

		}

		catch (Exception e) {
			throw new Exception("FAILED CLICK ON SITELOGO AND VERFIY PAGETITLE TESTCASE"
					+ "\n clickOnSiteLogoAndCheckThePageTitle" + e.getLocalizedMessage());
		}

	}

	//@Test(priority = 3, enabled = true, groups = "SanityTest")
	public void clearData() throws Exception {
		try {

			//objEditExpensePage.clickOnListExpense();
			objEditExpensePage.deleteExpense();
			objEditExpensePage.isExpenseDeleted();
			objEditExpensePage.isTitleDisplayed();
			objEditExpensePage.vendorDeletion1();
			objEditExpensePage.vendorDeletion2();

		}

		catch (Exception e) {
			throw new Exception("FAILED CLICK ON SITELOGO AND VERFIY PAGETITLE TESTCASE"
					+ "\n clickOnSiteLogoAndCheckThePageTitle" + e.getLocalizedMessage());
		}

	}

	public static String getValFromExcel(int row, int col) throws Exception {

		ExcelutilObject.setExcelFile(excelPath, "EditExpense");
		return ExcelutilObject.getCellData(row, col);
	}

}
