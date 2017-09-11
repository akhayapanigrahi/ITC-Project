package com.itc.test;

import org.testng.annotations.Test;

import com.itc.pages.NewAccountPage;
import com.itc.util.BaseTestObject;
import com.itc.util.ExcelutilObject;


public class NewAccountPageTest extends BaseTestObject{

	NewAccountPage objNewAccountPage = null;
	LoginPageTest login=new LoginPageTest();
	boolean flag = false;
	boolean flag1 = false;
	boolean flag2=false;
	boolean titleflag=false;
	public static String excelPath = System.getProperty("user.dir")+"\\src\\test\\resources\\testdata\\testDataSheet.xlsx";
	
	//@Parameters({"browserType"})
	//@Test(priority=0, enabled=true,groups="Regression")
	public void login() throws Exception{
		login.verifyLoginPage();
		
	}
	
	@Test(priority=0, enabled=true,groups="Regression")
	public void verifyNewAccount1() throws Exception
	{
	try 
	{
		objNewAccountPage = new NewAccountPage(driver);
		objNewAccountPage.clickOnNewAccount();
		String accName =getValFromExcel(1,2);
	    String bal =getValFromExcel(1,3);
		objNewAccountPage.newExpense(accName, bal);
	    flag1=objNewAccountPage.isResultPageDisplayed();
	    titleflag= objNewAccountPage.isTitleDisplayed();
	} 
	
	catch (Exception e) 
	{
		e.printStackTrace();
	}

	}
	@Test(priority=1, enabled=true,groups="Regression")
	public void verifyNewAccount2() throws Exception
	{
	try 
	{
		objNewAccountPage = new NewAccountPage(driver);
		objNewAccountPage.clickOnNewAccount();
		String accName =getValFromExcel(2,2);
	    String bal =getValFromExcel(2,3);
		objNewAccountPage.newExpense(accName, bal);
		objNewAccountPage.isResultPageDisplayed();
	    titleflag= objNewAccountPage.isTitleDisplayed();
	} 
	
	catch (Exception e) 
	{
		e.printStackTrace();
		//throw new Exception("FAILED CLICK ON SITELOGO AND VERFIY PAGETITLE TESTCASE" + "\n clickOnSiteLogoAndCheckThePageTitle" +e.getLocalizedMessage());
	}

	}
	@Test(priority=2, enabled=true,groups="Regression")
	public void verifyNewAccount3() throws Exception
	{
	try 
	{
		objNewAccountPage = new NewAccountPage(driver);
		objNewAccountPage.clickOnNewAccount();
		String accName =getValFromExcel(2,8);
	    String bal =getValFromExcel(2,3);
		objNewAccountPage.newExpense(accName, bal);
	    objNewAccountPage.isResultPageDisplayed();
	    titleflag= objNewAccountPage.isTitleDisplayed();
	} 
	
	catch (Exception e) 
	{
		e.printStackTrace();
	}

	}
public static String getValFromExcel(int row,int col) throws Exception{
		
		ExcelutilObject.setExcelFile(excelPath, "Account");
		return ExcelutilObject.getCellData(row, col);
	}
	
}
