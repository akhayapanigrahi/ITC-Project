package com.itc.test;

import org.testng.annotations.Test;

import com.itc.pages.DeleteVendorPage;
import com.itc.util.BaseTestObject;

import junit.framework.Assert;


public class DeleteVendorPageTest extends BaseTestObject{

	DeleteVendorPage objDeleteVendorPage =null;
	LoginPageTest login=new LoginPageTest();
	boolean flag = false;
	boolean flag1 = false;
	String titleflag=null;
		
	//@Parameters({"browserType"})
	@Test(priority=0, enabled=true,groups="Regression")
	public void verifyDeleteVendor() throws Exception
	{
	try 
	{
		//login.verifyLoginPage();
		objDeleteVendorPage = new DeleteVendorPage(driver);
		objDeleteVendorPage.clickOnListVendor();
		objDeleteVendorPage.vendorDeletion1();
		objDeleteVendorPage.vendorDeletion2();
	    flag1=objDeleteVendorPage.isResultPageDisplayed();
	    Assert.assertTrue(flag1);
	        
	} 
	
	catch (Exception e) 
	{
		e.printStackTrace();
	}

	}
	
}
