package com.itc.test;

import org.testng.annotations.Test;

import com.itc.pages.EditAccountPage;
import com.itc.util.BaseTestObject;
import com.itc.util.ExcelutilObject;


public class EditAccountPageTest extends BaseTestObject{

	EditAccountPage objEditAccountPage=null ;
	LoginPageTest login=new LoginPageTest();
	boolean flag = false;
	boolean flag1 = false;
	String titleflag=null;
	public static String excelPath = System.getProperty("user.dir")+"\\src\\test\\resources\\testdata\\testDataSheet.xlsx";

	//@Parameters({"browserType"})
	//@Test(priority=0,groups="Regression")
	public void Login() throws Exception{
		login.verifyLoginPage();
	}
	
	
	@Test(priority=0, enabled=true,groups="Regression")
	public void verifyEditAccount1() throws Exception
	{
	try 
	{
		objEditAccountPage= new EditAccountPage(driver);
		objEditAccountPage.clickOnChartAccount();
		String accName =getValFromExcel(1,2);
	    String bal =getValFromExcel(1,3);
		objEditAccountPage.isTitleDisplayed();
		//Assert.assertTrue(title.contains("Edit Vendor:"));
		objEditAccountPage.clickOnAccountLink1(); 
		objEditAccountPage.editExpense(accName, bal);
	    objEditAccountPage.isResultPageDisplayed();
	        
	} 
	
	catch (Exception e) 
	{
		e.printStackTrace();
	}

	}
	@Test(priority=1, enabled=true,groups="Regression")
	public void verifyEditAccount2() throws Exception
	{
	try 
	{
		objEditAccountPage= new EditAccountPage(driver);
		objEditAccountPage.clickOnChartAccount();
		String accName =getValFromExcel(2,2);
	    String bal =getValFromExcel(2,3);
		objEditAccountPage.isTitleDisplayed();
		objEditAccountPage.clickOnAccountLink2(); 
		objEditAccountPage.editExpense(accName, bal);
	    objEditAccountPage.isResultPageDisplayed();
	        
	} 
	
	catch (Exception e) 
	{
		e.printStackTrace();
	}

	}
	
public static String getValFromExcel(int row,int col) throws Exception{
		
		ExcelutilObject.setExcelFile(excelPath, "EditAccountData");
		return ExcelutilObject.getCellData(row, col);
	}


}
