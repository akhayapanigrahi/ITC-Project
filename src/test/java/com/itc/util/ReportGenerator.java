package com.itc.util;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.service.item.EmailMessage;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;
import microsoft.exchange.webservices.data.property.complex.MessageBody;

//import edu.apollogrp.logger.Logger;

public class ReportGenerator extends BasePageObject  {

	public ReportGenerator(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Run the Test report to Generate the jenkin report and send mail 
	 * @param REPORT_URL
	 * @param SUBJECT
	 * @param BROWSER_TESTED
	 */
	
	public void runTestReport(String REPORT_URL,String SUBJECT, String BROWSER_TESTED ) {
		//String browser = "FF";
		String suite = "regression";
		try{
	    if(suite.equalsIgnoreCase("sanity"))
		{
//	    	driver.get("http://10.96.52.54:8080/view/FPM/job/fima/");
		} else{
			driver.get(REPORT_URL);
	        Thread.sleep(2000);
	    	try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} 
			
       
        String build = driver.findElement(By.xpath(".//*[@id='buildHistory']/div[2]//tr[1]/td/div[1]/a")).getText();
        String build1 = build.trim();
        String build2 = build1.substring(1);
        
        String appUrl = "https://phoenix.edu";
        System.out.println("Build number = "+build2);
		
			String env = REPORT_URL+"ws/target/surefire-reports/emailable-report.html";
			
			String source = "";
			StringBuffer stringBuf = new StringBuffer(); 
			stringBuf.append("<html xmlns=\"http://www.w3.org/1999/xhtml\"><head><title>TestNG:  Unit Test</title><style type=\"text/css\">table caption,table.info_table,table.param,table.passed,table.failed {margin-bottom:10px;border:1px solid #000099;border-collapse:collapse;empty-cells:show;}table.info_table td,table.info_table th,table.param td,table.param th,table.passed td,table.passed th,table.failed td,table.failed th {border:1px solid #000099;padding:.25em .5em .25em .5em}table.param th {vertical-align:bottom}td.numi,th.numi,td.numi_attn {text-align:right}tr.total td {font-weight:bold}table caption {text-align:center;font-weight:bold;}table.passed tr.stripe td,table tr.passedodd td {background-color: #00AA00;}table.passed td,table tr.passedeven td {background-color: #33FF33;}table.passed tr.stripe td,table tr.skippedodd td {background-color: #cccccc;}table.passed td,table tr.skippedodd td {background-color: #dddddd;}table.failed tr.stripe td,table tr.failedodd td,table.param td.numi_attn {background-color: #FF3333;}table.failed td,table tr.failedeven td,table.param tr.stripe td.numi_attn {background-color: #DD0000;}tr.stripe td,tr.stripe th {background-color: #E6EBF9;}p.totop {font-size:85%;text-align:center;border-bottom:2px black solid}div.shootout {padding:2em;border:3px #4854A8 solid}</style></head><body>");


			stringBuf.append("Hi All,");
			stringBuf.append("<p>Please find the below reports</p>");
			
			stringBuf.append(drawPieChart(REPORT_URL,env));
			System.out.println("Pie chart completed ");
			stringBuf.append(getEnvironmentDetails(build2, appUrl, BROWSER_TESTED));
			System.out.println("Pie chart completed ");

			stringBuf.append(resultTable(REPORT_URL));
			System.out.println("Finished Resut Table");
			stringBuf.append("</body></html>"); 

			
		// Details Report				
			String body1="";
			driver.get(REPORT_URL+"com.aptimus.phxedu$phxedu-automation-tests/"+build2+"/testReport/");
			
			int mainReportSize = driver.findElements(By.xpath("//*[@id='testresult']/tbody[2]/tr")).size();
			System.out.println("Main Rpoert--"+mainReportSize);
			
			for(int mainReportTable=1; mainReportTable<=mainReportSize; mainReportTable++){
				
				driver.findElement(By.xpath("//*[@id='testresult']/tbody[2]/tr["+mainReportTable+"]/td[1]/a")).click();
				System.out.println(mainReportTable +"--Main Report Clicked");
				Thread.sleep(4000);
				int packageSize	 = driver.findElements(By.xpath("//*[@id='testresult']/tbody[2]/tr/td[1]/a/span")).size();
				
				if(packageSize==1)
				{
					driver.findElement(By.xpath("//*[@id='testresult']/tbody[2]/tr/td[1]/a")).click();
					Thread.sleep(8000);
					int  detailReportSize = driver.findElements(By.xpath("//*[@id='testresult']/tbody[2]/tr")).size();
					System.out.println("Details Rpeort-----"+ detailReportSize);
	
					for(int detailReportTable =1; detailReportTable<=detailReportSize; detailReportTable++)
					{	
						String TestCase = driver.findElement(By.xpath("//*[@id='testresult']/tbody[2]/tr["+detailReportTable+"]/td[1]/a")).getText();
						String mark = driver.findElement(By.xpath("//*[@id='testresult']/tbody[2]/tr["+detailReportTable+"]/td[3]")).getText();
	
						if(mark.equalsIgnoreCase("Passed")||mark.equalsIgnoreCase("Fixed")) {	         	 
			         	  mark = "<font color='green'>Pass</font>";
			            }
						if(mark.equalsIgnoreCase("Failed")||mark.equalsIgnoreCase("Regression")) {
			               mark = "<font color='red'>Fail</font>";
			            }
						if(mark.equalsIgnoreCase("Skipped")) {
				               mark = "<font color='olive'>Skip</font>";
				            }
						String ts = driver.findElement(By.xpath("//*[@id='testresult']/tbody[2]/tr["+detailReportTable+"]/td[2]")).getText();
						body1 = body1 + "<tr><td>"+detailReportTable+"</td><td>"+TestCase+"</td><td>"+mark+"</td><td>"+ts+"</td>"+"</tr>";
					}
					String  link = "<center><a href="+driver.getCurrentUrl()+">"+ driver.findElement(By.xpath(".//*[@id='main-panel-content']/h1")).getText() +"</a></center>";
					stringBuf.append("<br />"+ link
							+"<table BORDER='1'  WIDTH='70%' align='middle'   CELLPADDING='2' CELLSPACING='1' ><font size='2'><face='Calibri'>"					
							+ "<tr bgcolor='99CCFF'><td>S.No</td><td>TestCase</td><td>Result</td><td>Time</td></tr>"
							+ body1	
							+"</table>");
					body1="";
					driver.navigate().back();
					Thread.sleep(8000);
					
			} else 
			{	
				System.out.println("Actaul size = "+packageSize);
				
				for(int i=1; i<=packageSize; i++){
					
				driver.findElement(By.xpath("//*[@id='testresult']/tbody[2]/tr["+i+"]/td[1]/a")).click();
				System.out.println(i +"--Main Report Clicked");
				Thread.sleep(8000);
				
				int  detailReportSize = driver.findElements(By.xpath("//*[@id='testresult']/tbody[2]/tr")).size();
				System.out.println("Details Rpeort-----"+ detailReportSize);

				for(int detailReportTable =1; detailReportTable<=detailReportSize; detailReportTable++)
				{	
					String TestCase = driver.findElement(By.xpath("//*[@id='testresult']/tbody[2]/tr["+detailReportTable+"]/td[1]/a")).getText();
					String mark = driver.findElement(By.xpath("//*[@id='testresult']/tbody[2]/tr["+detailReportTable+"]/td[3]")).getText();

					if(mark.equalsIgnoreCase("Passed")||mark.equalsIgnoreCase("Fixed")) {	         	 
		         	  mark = "<font color='green'>Pass</font>";
		            }
					if(mark.equalsIgnoreCase("Failed")||mark.equalsIgnoreCase("Regression")) {
		               mark = "<font color='red'>Fail</font>";
		            }
					if(mark.equalsIgnoreCase("Skipped")) {
			               mark = "<font color='olive'>Skip</font>";
			            }
					String ts = driver.findElement(By.xpath("//*[@id='testresult']/tbody[2]/tr["+detailReportTable+"]/td[2]")).getText();
					body1 = body1 + "<tr><td>"+detailReportTable+"</td><td>"+TestCase+"</td><td>"+mark+"</td><td>"+ts+"</td>"+"</tr>";
				}
				String  link = "<center><a href="+driver.getCurrentUrl()+">"+ driver.findElement(By.xpath(".//*[@id='main-panel-content']/h1")).getText() +"</a></center>";
				stringBuf.append("<br />"+ link
						+"<table BORDER='1'  WIDTH='70%' align='middle'   CELLPADDING='2' CELLSPACING='1' ><font size='2'><face='Calibri'>"					
						+ "<tr bgcolor='99CCFF'><td>S.No</td><td>TestCase</td><td>Result</td><td>Time</td></tr>"
						+ body1	
						+"</table>");
				body1="";
				driver.navigate().back();
				Thread.sleep(8000);
				}
			
			}
				
				driver.navigate().back();
				Thread.sleep(10000);
			}
			

			stringBuf.append("</body></html>"); 
			source = stringBuf.toString();

//			source = stringBuf.toString();
			sendEmail(source,SUBJECT+" - "+generateDateFormat());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	
	

	

	
	public void sendEmail(String source,String subject) throws Exception {
		if(source.equals("")){
			//////System.out.println("Email will not send as there are parsing errors");
		}else{
		/*	HtmlEmail email = new HtmlEmail();
			email.setHostName("mailhost.apollogrp.edu");
			

			//email.addTo("vph@phoenix.edu");
			//email.addTo("vph@phoenix.edu");

			
			String to = "DGC-APO-Corporate-LPQA-SSProjects@phoenix.edu";
		//	String to = "vph@phoenix.edu";
			String[] toArr = to.split(",");
			for(String t:toArr){
				email.addTo(t);
			}

			String cc = "FinancialPlanManagementScrumTeam@phoenix.edu,FinancialPlanManagementDEVTeam@phoenix.edu";
		//	String cc = "vph@phoenix.edu";
			String[] ccArr = cc.split(",");
			for(String t:ccArr){
				email.addCc(t);
			}

			email.setFrom("vph@phoenix.edu");
			//email.setSubject("Daily Sanity - UoP-Prod - Classroom");
			
			email.setSubject(subject);
			
			email.setHtmlMsg(source);			
			
			email.send();
*/			
		    //String to = "Jagadish.Muniswamy@phoenix.edu";
			
			String to = "Jagadish.Muniswamy@phoenix.edu,Manoj.Kumar@phoenix.edu,Roopa.Jaganoor@phoenix.edu,Raja.Muthu@phoenix.edu,Satyajit.Samal@phoenix.edu,Sudheer.Ananthaiah@phoenix.edu,Parvatagouda.Patil@phoenix.edu,Amarnath.Buchireddygari@phoenix.edu,Bhagavan.Singireddy@phoenix.edu,Devesh.Rajput@phoenix.edu,Paramesha.GH@phoenix.edu,Srinivas.Myana@phoenix.edu,Vijayalakshmi.R@phoenix.edu,Mohit.Garg@phoenix.edu,Dharmapal.Panwar@phoenix.edu,Gaurav.Baijal@phoenix.edu";

			System.out.println("Enter sendMailViaExchnageService");

            ExchangeService service = new ExchangeService(ExchangeVersion.Exchange2010_SP2);
            ExchangeCredentials credentials = new WebCredentials("Jagadish.Muniswamy@phoenix.edu","apollo@19");
            service.setCredentials(credentials);
            service.setUrl(new URI("https://outlook.office365.com/EWS/Exchange.asmx"));

            EmailMessage msg = new EmailMessage(service);
            msg.setSubject(subject);
            msg.setBody(MessageBody.getMessageBodyFromText(source.toString()));
            List<String> toAddressList = Arrays.asList(to.split("\\s*,\\s*"));
            Iterator<String> mailList = toAddressList.iterator();

            msg.getToRecipients().addSmtpAddressRange(mailList);
          
            msg.send();
					
			System.out.println("\nMail was sent successfully.");

			System.out.println("Exit sendMailViaExchnageService");



		}
	}

	
	public String generateDateFormat(){

		String date="";
		Date dateNow = new Date();
//		SimpleDateFormat ft = new SimpleDateFormat("MM/dd/yyyy");
		DateFormat formatter= new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
		formatter.setTimeZone(TimeZone.getTimeZone("IST"));
		date = formatter.format(dateNow);
		return date;

	}


	public String parseHtmlSource(String reportURL) throws IOException, URISyntaxException{
		
		driver.get(reportURL);
		String str= driver.getPageSource(); 
	
		return str.toString();
	}

	public String getEnvironmentDetails(String build, String appUrl, String browserType) throws IOException, URISyntaxException{
		StringBuffer sb = new StringBuffer();
		
		sb.append( "<br/>" );
		sb.append( "<br/>" );
		sb.append( "<br/>" );

		sb.append("<table BORDER='2'   WIDTH='70%' align='middle'  CELLPADDING='2' CELLSPACING='1'> ");
		sb.append("<th bgcolor='#5D7B9D'  colspan=2><font color='#fff' size=3> Environment Details </font></th>");
		
		sb.append("<tr>");
		sb.append("<td ALIGN='CENTER'><font  size=2>APPICATION URL </font>	</td><td align='center' size=2> <a href="+ appUrl + "> " + appUrl + "</a></td></tr>");
		sb.append("<td ALIGN='CENTER'><font  size=2>BUILD NUMBER </td><td  align='center' size=2>  "
				+ build + " </td> </font></tr>");
		sb.append("<td ALIGN='CENTER'><font  size=2>BROWSER TESTED </td><td  align='center' size=2>  "
				+ browserType + "</td> </font></tr>");
		sb.append("<td ALIGN='CENTER'><font  size=2>DATE OF EXECUTION  </td><td  align='center' size=2>  "
				+ generateDateFormat() + " </td> </font></tr>");

		sb.append("</table>");

		return sb.toString();

	}
	
	public String resultTable(String REPORT_URL) throws Exception, URISyntaxException{
		
		
		StringBuffer sb = new StringBuffer();
		sb.append( "<br/>" );
		sb.append( "<br/>" );
		sb.append( "<br/>" );
		driver.get(REPORT_URL);
		Thread.sleep(5000);
		
		String build = driver.findElement(By.xpath(".//*[@id='buildHistory']/div[2]//tr[1]/td/div[1]/a")).getText();
	    String build1 = build.trim();//*[@id='buildHistory']/div[2]//tr[1]/td/div[1]/a.//*[@id='buildHistory']/div[2]//tr[1]/td/div[1]/a
	    String build2 = build1.substring(1);
	    
		driver.get(REPORT_URL+"com.aptimus.phxedu$phxedu-automation-tests/"+build2+"/testReport/");
		
		
		
		String header = "<tr bgcolor='99CCFF'><TH ALIGN='CENTER'>Test class</TH><TH ALIGN='CENTER'>Total </TH><TH ALIGN='CENTER' bgcolor='66FF33'>Passed</TH><TH ALIGN='CENTER' bgcolor='FF3333'>Failed</TH><TH ALIGN='CENTER' bgcolor='FFFF33'>Skipped</TH><TH ALIGN='CENTER'>Duration</TH></tr>";
		
		String body = "";
		int allTotal = 0;
		int allPassed = 0;
		int allFailed = 0;
		int allSkipped = 0;
		
		String totalDuration = driver.findElement(By.xpath("//*[@id='main-panel-content']/div[2]/a")).getText();
		totalDuration = totalDuration.replaceAll("Took ", "");

		String Total ="" ;
		String Failed ="" ;
		String Skipped ="" ;
		String Passed  ="" ;
		String Time ="" ;
		String TestPackage ="" ;
		
		String Total1 ="" ;
		String Failed1 ="" ;
		String Skipped1 ="" ;
		String Passed1  ="" ;
		String Time1 ="" ;
		String TestPackage1 ="" ;
		
		Thread.sleep(5000);
		List<WebElement> resultTable = driver.findElements(By.xpath("//*[@id='testresult']/tbody[2]/tr"));
		for(int k=1;k<=resultTable.size();k++)
		{
			driver.findElement(By.xpath("//*[@id='testresult']/tbody[2]/tr["+k+"]/td[1]/a/span")).click();
			Thread.sleep(7000);
			int packageSize = driver.findElements(By.xpath("//*[@id='testresult']/tbody[2]/tr/td[1]/a/span")).size();
			
			 TestPackage  = driver.findElement(By.xpath("//*[@id='testresult']/tbody[2]/tr/td[1]/a/span")).getText();			 
			 Total 	   	  = driver.findElement(By.xpath("//*[@id='testresult']/tbody[2]/tr/td[9]")).getText();			
			 Failed       = driver.findElement(By.xpath("//*[@id='testresult']/tbody[2]/tr/td[3]")).getText();
			 Skipped      = driver.findElement(By.xpath("//*[@id='testresult']/tbody[2]/tr/td[5]")).getText();
			 Passed       = driver.findElement(By.xpath("//*[@id='testresult']/tbody[2]/tr/td[7]")).getText();			
			 Time         = driver.findElement(By.xpath("//*[@id='testresult']/tbody[2]/tr/td[2]")).getText();
			
			
			if(packageSize>1){
				System.out.println("Package size ===>>>>> "+packageSize);
				List<WebElement> resultTable1 = driver.findElements(By.xpath("//*[@id='testresult']/tbody[2]/tr"));
				for(int j=2;j<=resultTable1.size();j++)
				{
				 TestPackage1  = driver.findElement(By.xpath("//*[@id='testresult']/tbody[2]/tr["+j+"]/td[1]/a/span")).getText();			 
				 Total1 	   = driver.findElement(By.xpath("//*[@id='testresult']/tbody[2]/tr["+j+"]/td[9]")).getText();			
				 Failed1   	   = driver.findElement(By.xpath("//*[@id='testresult']/tbody[2]/tr["+j+"]/td[3]")).getText();
				 Skipped1      = driver.findElement(By.xpath("//*[@id='testresult']/tbody[2]/tr["+j+"]/td[5]")).getText();
				 Passed1       = driver.findElement(By.xpath("//*[@id='testresult']/tbody[2]/tr["+j+"]/td[7]")).getText();			
				 Time1         = driver.findElement(By.xpath("//*[@id='testresult']/tbody[2]/tr["+j+"]/td[2]")).getText();
								 
					body = body + "<b><tr ><td >"+TestPackage1+"</td><td ALIGN='CENTER'>"+Total1+"</td><td ALIGN='CENTER'>"+Passed1+"</td><td ALIGN='CENTER'>"+Failed1+"</td><td ALIGN='CENTER'>"+Skipped1+"</td><td ALIGN='CENTER'>"+Time1+"</td>"+"</tr></b>";
					
					allTotal     = allTotal+ Integer.parseInt(Total1);
					allPassed    = allPassed+ Integer.parseInt(Passed1);
					allFailed    = allFailed+ Integer.parseInt(Failed1);
					allSkipped   = allSkipped+ Integer.parseInt(Skipped1);
					
				}

			}
			 
			driver.navigate().back();
			Thread.sleep(5000);
			
			body = body + "<b><tr ><td >"+TestPackage+"</td><td ALIGN='CENTER'>"+Total+"</td><td ALIGN='CENTER'>"+Passed+"</td><td ALIGN='CENTER'>"+Failed+"</td><td ALIGN='CENTER'>"+Skipped+"</td><td ALIGN='CENTER'>"+Time+"</td>"+"</tr></b>";
		
			allTotal     = allTotal+ Integer.parseInt(Total);
			allPassed    = allPassed+ Integer.parseInt(Passed);
			allFailed    = allFailed+ Integer.parseInt(Failed);
			allSkipped   = allSkipped+ Integer.parseInt(Skipped);
		
		}
		
		body = body + "<tr bgcolor='#FFDAB9'><b><td >"+""+"</td><td ALIGN='CENTER'>"+allTotal+"</td><td ALIGN='CENTER'>"+allPassed+"</td><td ALIGN='CENTER'>"+allFailed+"</td><td ALIGN='CENTER'>"+allSkipped+"</td><td ALIGN='CENTER'>"+totalDuration+"</td>"+"</b></tr>";
		
		sb.append("<table BORDER='2'   WIDTH='70%' align='middle'  CELLPADDING='2' CELLSPACING='1'> ");
		sb.append("<th bgcolor='#5D7B9D'  colspan=6><font color='#fff' size=3> Detailed report table </font></th>");
		
		sb.append(header);
		sb.append(body);
		sb.append("</table>");
		

		return sb.toString();

	}

	

	public String drawPieChart(String REPORT_URL,String EMAILABLE_REPORT) throws InterruptedException  {
        int passi,  skipi,  faili;
        driver.get(REPORT_URL);
		Thread.sleep(2500);
		String build = driver.findElement(By.xpath(".//*[@id='buildHistory']/div[2]//tr[1]/td/div[1]/a")).getText();
	    String build1 = build.trim();
	    String build2 = build1.substring(1);
		driver.get(REPORT_URL+"com.aptimus.phxedu$phxedu-automation-tests/"+build2+"/testReport/");
		
        
        int allTotal = 0;
		int allPassed = 0;
		int allFailed = 0;
		int allSkipped = 0;
		
		String Total ="" ;
		String Failed ="" ;
		String Skipped ="" ;
		String Passed  ="" ;
		
		Thread.sleep(2000);
		List<WebElement> resultTable = driver.findElements(By.xpath("//*[@id='testresult']/tbody[2]/tr"));
		for(int k=1;k<=resultTable.size();k++)
		{

						 
			 Total 	    = driver.findElement(By.xpath("//*[@id='testresult']/tbody[2]/tr["+k+"]/td[9]")).getText();			
			 Failed     = driver.findElement(By.xpath("//*[@id='testresult']/tbody[2]/tr["+k+"]/td[3]")).getText();
			 Skipped    = driver.findElement(By.xpath("//*[@id='testresult']/tbody[2]/tr["+k+"]/td[5]")).getText();
			 Passed     = driver.findElement(By.xpath("//*[@id='testresult']/tbody[2]/tr["+k+"]/td[7]")).getText();			
			
			
			allTotal     = allTotal+ Integer.parseInt(Total);
			allPassed    = allPassed+ Integer.parseInt(Passed);
			allFailed    = allFailed+ Integer.parseInt(Failed);
			allSkipped   = allSkipped+ Integer.parseInt(Skipped);
		
		}
		
        
        passi = allPassed;
        skipi = allSkipped;
        faili = allFailed;
        
        int total = passi+skipi+faili;
        
        String pass=Integer.toString(passi*100/total);
        String skip=Integer.toString(skipi*100/total);
        String fail=Integer.toString(faili*100/total);
     
        String alt="\" alt=";
        String googlepiechart="\"Google Chart";
        String icon="\"/></center>";    
        String urlimg="\"http://chart.apis.google.com/chart?";
        String img="<center><img src="+urlimg+"chtt=Pie+Chart&amp;chts=000000,12&amp;chs=300x150&amp;chf=bg,s,ffffff&amp;cht=p3&amp;chd=t:passtest,failtest,skiptest&amp;chl=Passed|Failed|Skipped&amp;chco=006600,ff0000,ffff00"+alt+googlepiechart+icon; 
        img=img.replaceAll("passtest", pass);
        img=img.replaceAll("failtest", fail);
        img=img.replaceAll("skiptest", skip);
        return img;
 }

}