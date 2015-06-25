package functionalitycheckpro;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import jxl.JXLException;
import jxl.Sheet;
import jxl.Workbook;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FunctionalityCheck {
	WebDriver dri;
	@DataProvider(name = "myTest")
      public Object[][] createData1() throws JXLException, IOException {

	   FileInputStream fi = new FileInputStream(System.getProperty("user.home")+"/Desktop/PMR/URL/PractiseURLs.xls");
		Workbook w = Workbook.getWorkbook(fi);
		Sheet s = w.getSheet(3);
		Object[][] data = new Object[s.getRows()][3];
		for(int row=0; row <s.getRows();row++)
		{
		data[row][0]= s.getCell(0,row).getContents();
		data[row][1]= s.getCell(1,row).getContents();
		data[row][2]= s.getCell(2,row).getContents();
		System.out.println("Value"+data[row][0]);
		
		String url=(String)data[row][0];

		String username=(String)data[row][1];
		
		String password=(String)data[row][2];
			
		 }
              return data;                     
     }
	@BeforeTest
	@Parameters("browser")
	  public void befmethod(String browser){
		if(browser.equalsIgnoreCase("firefox")){
			dri=new FirefoxDriver();
		}
		else if(browser.equalsIgnoreCase("chrome")){
			System.getProperty("webdriver.chrome.driver","/path/to/chromedriver");
			dri=new ChromeDriver();
		}
	}
	
	@Test(testName="logincheck",dataProvider="myTest",enabled=true,priority=1)
	  public void logincheck(String url,String username,String password) throws InterruptedException, IOException{
	   dri.get(url);
	   if(url.contains("epp")){
	   dri.findElement(By.name("Login1$UserName")).sendKeys(username);
	   dri.findElement(By.name("Login1$Password")).sendKeys(password);
	   dri.findElement(By.name("Login1$LoginImageButton")).click();
	   dirchecktwo(password,username);
		}
	   else{
		    dircheck();	  
			}
	   boolean bd=false;
	   if(url.contains("epp")){
	   try{
			bd=dri.findElement(By.xpath
					("//*[@id='aspnetForm']/table/tbody/tr[1]/td/table/tbody/tr[1]/td/table/tbody/tr/td[2]/span"))
					.getText().contains("ePatient Portal");
		   }
	   catch(org.openqa.selenium.NoSuchElementException e){
				
		   }
	   Assert.assertTrue(bd,"Either Login not working or PasswordChanged");
	   }else{
		   try{
				bd=dri.findElement(By.xpath
						(".//*[@id='aspnetForm']/table/tbody/tr[1]/td/table/tbody/tr[1]/td[2]/span"))
						.getText().contains("Patient Management Portal");
			   }
		   catch(org.openqa.selenium.NoSuchElementException e){
					
			   }
		   Assert.assertTrue(bd,"Either Login not working or PasswordChanged");
	   }
	}
	
	 @Test(testName="rxcreation_save_transmitcheck",dependsOnMethods="logincheck",enabled=false)
	   public void rxcreation() throws InterruptedException, AWTException, IOException{
		 String url=dri.getCurrentUrl();
		 if(url.contains("epp")){
			 throw new SkipException("On epp , Rx cannot be tested");
		 }
		dri.findElement(By.id("ctl00_NavigationMenu1_LinkButton1")).click();
		Thread.currentThread().sleep(5000);
		dri.findElement
		(By.id("ctl00_ContentPlaceHolder1_grdPatients_ctl02_ImageButton3")).click();
		Thread.currentThread().sleep(5000);
		dri.findElement(By.id("ctl00_ContentPlaceHolder1_trRx")).click();
		Thread.currentThread().sleep(5000);
		dri.findElement(By.xpath("(//a[contains(text(),'Rx')])[3]")).click();
		Thread.currentThread().sleep(5000);
		dri.findElement(By.xpath("//button[@type='button']")).click();
		dri.findElement(By.id("ui-active-menuitem")).click();
		dri.findElement(By.xpath("//button[@type='button']")).click();
		dri.findElement(By.id("ui-active-menuitem")).click();
		dri.findElement(By.id("txt_med")).sendKeys("test");
		Thread.currentThread().sleep(2000);
		dri.findElement(By.id("ui-active-menuitem")).click();
		Thread.currentThread().sleep(2000);
		dri.findElement(By.xpath("//button[@type='button']")).click();
		Thread.currentThread().sleep(2000);
		dri.findElement(By.id("ui-active-menuitem")).click();
		Thread.currentThread().sleep(2000);
		dri.findElement(By.xpath("(//button[@type='button'])[2]")).click();
		Thread.currentThread().sleep(2000);
		dri.findElement(By.id("ui-active-menuitem")).click();
		dri.findElement(By.cssSelector
				("#nexttodosage > span.ui-button-text")).click();
		Thread.currentThread().sleep(2000);
		dri.findElement
		        (By.xpath("//*[@id='txt_cmb_dosage']")).sendKeys("Cream");
		Thread.currentThread().sleep(2000);
		dri.findElement(By.id("ui-active-menuitem")).click();
		Thread.currentThread().sleep(2000);
		dri.findElement(By.id("txt35_0_0")).sendKeys("Test");
		dri.findElement(By.id("inst35")).sendKeys("Test");
		dri.findElement(By.cssSelector
				("#nexttoRefill > span.ui-button-text")).click();
		Thread.currentThread().sleep(2000);
		dri.findElement(By.id("txtQuantity")).sendKeys("1");
		dri.findElement(By.id("txtRefills")).sendKeys("1");
		dri.findElement(By.id("txtStartDate")).click();
		dri.findElement(By.xpath("//a[contains(text(),'19')]")).click();
		dri.findElement(By.xpath("//a[contains(text(),'19')]")).click();
		Thread.currentThread().sleep(2000);
		dri.findElement(By.cssSelector
				("#nexttofinalizeRx > span.ui-button-text")).click();
		Thread.currentThread().sleep(2000);
		dri.findElement(By.id("ImgFinalizeRx")).click();
		Thread.currentThread().sleep(2000);
		dri.findElement(By.id("btnSavePrescription")).click();
		dri.switchTo().alert().accept();
		Thread.currentThread().sleep(5000);
		boolean b=dri.findElement
		(By.xpath("//*[@id='mederr']/div")).getText().contains
		("Prescription has been saved");
		dri.findElement(By.xpath("//*[@id='mederr']/div/img")).click();
		Assert.assertTrue(b,"Prescription is not gettin saved");
		Thread.currentThread().sleep(2000);
		dri.findElement(By.xpath("//*[@id='btnTransmit']")).click();
		dri.switchTo().alert().accept();
		Thread.currentThread().sleep(30000);
		boolean c=dri.findElement
		(By.xpath("//*[@id='mederr']/div")).getText().contains
		("Prescription has been transmitted");
		dri.findElement(By.xpath("//*[@id='mederr']/div/img")).click();
		dri.findElement(By.xpath("//*[@id='btnClosePrescription']")).click();
		Assert.assertTrue(c);
		}
	 @Test(testName="SoapNoteSectionCheck",dependsOnMethods="logincheck",enabled=false)
	   public void soapnotesectioncheck() throws InterruptedException{
		 String url=dri.getCurrentUrl();
		 if(url.contains("epp")){
			 throw new SkipException("On epp , Soapnote cannot be tested");
		 }
		 dri.findElement(By.id("ctl00_NavigationMenu1_LinkButton1")).click();
		 new WebDriverWait(dri, 50).until
	                      (ExpectedConditions.visibilityOfElementLocated
		                  (By.id("ctl00_ContentPlaceHolder1_grdPatients_ctl02_ImageButton3")));
		 dri.findElement(By.id("ctl00_ContentPlaceHolder1_grdPatients_ctl02_ImageButton3")).click();
		 new WebDriverWait(dri, 50).until
                          (ExpectedConditions.visibilityOfElementLocated
		                  (By.xpath("//*[@id='ctl00_ContentPlaceHolder1_createsoapnotelink']")));
		 dri.findElement
		                (By.xpath("//*[@id='ctl00_ContentPlaceHolder1_createsoapnotelink']")).click();
		 new WebDriverWait(dri, 50).until
                          (ExpectedConditions.visibilityOfElementLocated
		                  (By.xpath("//*[@id='ctl00_ContentPlaceHolder1_btnReset']")));
		 dri.findElement
		                (By.xpath("//*[@id='ctl00_ContentPlaceHolder1_btnReset']")).click();
		 new WebDriverWait(dri, 50).until
                          (ExpectedConditions.visibilityOfElementLocated
		                  (By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlApptType']")));
	     Select select = new Select(dri.findElement
				        (By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlApptType']")));
	     select.selectByValue("1000");
	     Thread.currentThread().sleep(2000);
	     Select selectone = new Select(dri.findElement
				        (By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlProvider']")));
	     selectone.selectByVisibleText("Accupuncture Resource");
	     Thread.currentThread().sleep(2000);
	     dri.findElement(By.id("ctl00_ContentPlaceHolder1_txtEncDate")).click();
	     dri.findElement(By.xpath
	    		        ("//*[@id='ctl00_ContentPlaceHolder1_CalendarExtender4_day_2_3']")).click();
	     Thread.currentThread().sleep(7000);
	     Select selectthree = new Select(dri.findElement
				        (By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlSoapTemplate']")));
	     selectthree.selectByVisibleText("Test SOAP Note Template");
	     Thread.currentThread().sleep(5000);
	     dri.findElement(By.xpath
	    		        ("//*[@id='ctl00_ContentPlaceHolder1_SoapContentView_0']")).click();
	     dri.findElement(By.xpath
	    		        ("//*[@id='ctl00_ContentPlaceHolder1_btnviewselect']")).click();
	     Thread.currentThread().sleep(7000);
	     dri.findElement(By.id
	    		        ("ctl00_ContentPlaceHolder1_grdICD9_ctl02_imgBtnICD9Select"))
	    		 .click();
	     Thread.currentThread().sleep(5000);
	     dri.findElement(By.xpath
	    		        ("//*[@id='ctl00_ContentPlaceHolder1_btnGenerate']"))
	    		 .click();
	     Thread.currentThread().sleep(5000);
	     boolean c =dri.findElement
	                    (By.id("ctl00_ContentPlaceHolder1_btnSave")).isDisplayed();
	     Assert.assertTrue(c,"Soap Note is not getting generated");
	     dri.findElement(By.id("ctl00_ContentPlaceHolder1_btnSave")).click();
	     Thread.currentThread().sleep(5000);
	     boolean b=dri.findElement
	                    (By.cssSelector("td.UpperCenter")).getText()
	              .contains("SOAP Notes List");
	     System.out.print(dri.findElement
	    		        (By.cssSelector("td.UpperCenter")).getText());
	     Assert.assertTrue(b,"Soap Note is not getting saved ");
	     }
	 
	 @Test(testName="SoapNotePlanIntegration",enabled=false,priority=2)
	   public void SoapNotePlanIntegration() throws InterruptedException, AWTException{
		 String url=dri.getCurrentUrl();
		 if(url.contains("epp")){
			 throw new SkipException("On epp , Soapnote cannot be tested");
		 }
		 
		 dri.findElement(By.id("ctl00_NavigationMenu1_LinkButton1")).click();
		 new WebDriverWait(dri, 50).until
	                      (ExpectedConditions.visibilityOfElementLocated
		                  (By.id("ctl00_ContentPlaceHolder1_grdPatients_ctl02_ImageButton3")));
		 dri.findElement(By.id("ctl00_ContentPlaceHolder1_" +
		 		               "grdPatients_ctl02_ImageButton3")).click();
		 new WebDriverWait(dri, 50).until
                          (ExpectedConditions.visibilityOfElementLocated
		                  (By.xpath("//*[@id='ctl00_ContentPlaceHolder1_createsoapnotelink']")));
		 dri.findElement
		                (By.xpath("//*[@id='ctl00_ContentPlaceHolder1_" +
		                		  "createsoapnotelink']")).click();
		 new WebDriverWait(dri, 50).until
         (ExpectedConditions.visibilityOfElementLocated
         (By.xpath("//*[@id='ctl00_ContentPlaceHolder1_btnReset']")));
          dri.findElement
                         (By.xpath("//*[@id='ctl00_ContentPlaceHolder1_btnReset']")).click();
          new WebDriverWait(dri, 50).until
          (ExpectedConditions.visibilityOfElementLocated
          (By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlApptType']")));
		 Select select = new Select(dri.findElement
                                   (By.xpath("//*[@id='ctl00_ContentPlaceHolder1_" +
                                   		     "ddlApptType']")));
         select.selectByValue("1000");
         Thread.currentThread().sleep(5000);
         Select selectone = 
        	 new Select(dri.findElement
                                      (By.xpath("//*[@id='ctl00_ContentPlaceHolder1" +
                                      		    "_ddlProvider']")));
         selectone.selectByVisibleText("Accupuncture Resource");
         Thread.currentThread().sleep(2000);
         dri.findElement(By.id("ctl00_ContentPlaceHolder1_txtEncDate")).click();
         dri.findElement(By.xpath
                        ("//*[@id='ctl00_ContentPlaceHolder1_CalendarExtender4" +
                         "_day_2_3']")).click();
         Thread.currentThread().sleep(7000);
         Select selectthree = new Select(dri.findElement
                                        (By.xpath("//*[@id='ctl00_ContentPlaceHolder1" +
                                        		  "_ddlSoapTemplate']")));
         selectthree.selectByVisibleText("Test SOAP Note Template");
         Thread.currentThread().sleep(5000);
         dri.findElement(By.xpath
                        ("//*[@id='ctl00_ContentPlaceHolder1_SoapContentView_0']")).click();
         dri.findElement(By.xpath
                        ("//*[@id='ctl00_ContentPlaceHolder1_btnviewselect']")).click();
         Thread.currentThread().sleep(7000);
         dri.findElement(By.id
                        ("ctl00_ContentPlaceHolder1_grdICD9_ctl02_imgBtnICD9Select"))
                        .click();
        Thread.currentThread().sleep(5000);
		new WebDriverWait(dri, 50).until
                         (ExpectedConditions.visibilityOfElementLocated
                         (By.xpath("//li[2]/a")));
		dri.findElement(By.xpath("//li[2]/a")).click();
		dri.switchTo().frame("iframe1");
		new WebDriverWait(dri, 50).until
                         (ExpectedConditions.visibilityOfElementLocated
                         (By.id("chkVsblToPat")));
		dri.findElement(By.id("chkVsblToPat")).click();
		dri.findElement(By.cssSelector("#btnPlanElemt")).click();
		Thread.currentThread().sleep(7000);
		((JavascriptExecutor)dri).executeScript("tinyMCE.activeEditor.focus()");
		Robot robo = new Robot();
		robo.keyPress(KeyEvent.VK_E);
		robo.keyRelease(KeyEvent.VK_E);
		robo.keyPress(KeyEvent.VK_A);
		robo.keyRelease(KeyEvent.VK_A);
		robo.keyPress(KeyEvent.VK_S);
		robo.keyRelease(KeyEvent.VK_S);
		robo.keyPress(KeyEvent.VK_T);
		robo.keyRelease(KeyEvent.VK_T);
		Thread.currentThread().sleep(2000);
		dri.findElement(By.id("btnAddPlanToNote")).click();
		dri.switchTo().defaultContent();
		new WebDriverWait(dri, 50).until
        (ExpectedConditions.visibilityOfElementLocated
        (By.xpath("//*[@id='ctl00_ContentPlaceHolder1_btnGenerate']")));
        dri.findElement(By.xpath
                       ("//*[@id='ctl00_ContentPlaceHolder1_btnGenerate']"))
                       .click();
        new WebDriverWait(dri, 50).until
        (ExpectedConditions.visibilityOfElementLocated
        (By.xpath("//*[@id='ctl00_ContentPlaceHolder1_lblSoapNoteText']")));
        boolean b=dri.findElement(By.xpath
                ("//*[@id='ctl00_ContentPlaceHolder1_lblSoapNoteText']")).getText().contains("east");
        Assert.assertTrue(b,"Soapnote Plan Not Getting Associated");
        }
	 
	 @Test(testName="SoapNoteHistoryIntegration",enabled=false,priority=2)
	   public void SoapNotePatientHistoryIntegration() throws InterruptedException, AWTException{
		 String url=dri.getCurrentUrl();
		 if(url.contains("epp")){
			 throw new SkipException("On epp , Soapnote cannot be tested");
		 }
		 
		 dri.findElement(By.id("ctl00_NavigationMenu1_LinkButton1")).click();
		 new WebDriverWait(dri, 50).until
	                      (ExpectedConditions.visibilityOfElementLocated
		                  (By.id("ctl00_ContentPlaceHolder1_grdPatients_ctl02_ImageButton3")));
		 dri.findElement(By.id("ctl00_ContentPlaceHolder1_grdPatients_ctl02_ImageButton3")).click();
		 new WebDriverWait(dri, 50).until
                          (ExpectedConditions.visibilityOfElementLocated
		                  (By.xpath("//*[@id='ctl00_ContentPlaceHolder1_createsoapnotelink']")));
		 dri.findElement
		                (By.xpath("//*[@id='ctl00_ContentPlaceHolder1_createsoapnotelink']")).click();
		 new WebDriverWait(dri, 50).until
         (ExpectedConditions.visibilityOfElementLocated
         (By.xpath("//*[@id='ctl00_ContentPlaceHolder1_btnReset']")));
          dri.findElement
                         (By.xpath("//*[@id='ctl00_ContentPlaceHolder1_btnReset']")).click();
          new WebDriverWait(dri, 50).until
          (ExpectedConditions.visibilityOfElementLocated
          (By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlApptType']")));
		 Select select = new Select(dri.findElement
                                   (By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlApptType']")));
         select.selectByValue("1000");
         Thread.currentThread().sleep(5000);
         Select selectone = new Select(dri.findElement
                                      (By.xpath("//*[@id='ctl00_" +
                                      		    "ContentPlaceHolder1_ddlProvider']")));
         selectone.selectByVisibleText("Accupuncture Resource");
         Thread.currentThread().sleep(2000);
         dri.findElement(By.id("ctl00_ContentPlaceHolder1_txtEncDate")).click();
         dri.findElement(By.xpath
                        ("//*[@id='ctl00_ContentPlaceHolder1_CalendarExtender4_day_2_3']")).click();
         Thread.currentThread().sleep(7000);
         Select selectthree = new Select(dri.findElement
                                        (By.xpath("//*[@id='ctl00_" +
                                        		 "ContentPlaceHolder1_ddlSoapTemplate']")));
         selectthree.selectByVisibleText("Test SOAP Note Template");
         Thread.currentThread().sleep(5000);
         dri.findElement(By.xpath
                        ("//*[@id='ctl00_ContentPlaceHolder1_SoapContentView_0']")).click();
         dri.findElement(By.xpath
                        ("//*[@id='ctl00_ContentPlaceHolder1_btnviewselect']")).click();
         Thread.currentThread().sleep(7000);
         dri.findElement(By.id
                        ("ctl00_ContentPlaceHolder1_grdICD9_ctl02_imgBtnICD9Select"))
                        .click();
        Thread.currentThread().sleep(5000);
		new WebDriverWait(dri, 50).until
                         (ExpectedConditions.visibilityOfElementLocated
                         (By.xpath("//li[2]/a")));
		dri.findElement(By.xpath("//li[3]/a")).click();
		dri.switchTo().frame("iframe2");
		new WebDriverWait(dri, 50).until
        (ExpectedConditions.visibilityOfElementLocated
        (By.xpath("//*[@id='Txt_PriorMedicalHistory']")));
		dri.findElement(By.xpath
                ("//*[@id='Txt_PriorMedicalHistory']"))
                .clear();
		dri.findElement(By.xpath
                ("//*[@id='Txt_PriorMedicalHistory']"))
                .sendKeys("This is for the testing");
		dri.findElement(By.id
                ("Btn_PriorMedicalHistory"))
                .click();
		new WebDriverWait(dri, 50).until
        (ExpectedConditions.visibilityOfElementLocated
        (By.id("Txt_SocialMedicalHistory")));
		dri.findElement(By.id
                ("Txt_SocialMedicalHistory"))
                .clear();
		dri.findElement(By.id
                ("Txt_SocialMedicalHistory"))
                .sendKeys("This is for the testing");
		dri.findElement(By.id
                ("BtnSocialMedicalHistory"))
                .click();
		new WebDriverWait(dri, 50).until
        (ExpectedConditions.visibilityOfElementLocated
        (By.id("Txt_SocialMedicalHistory")));
		dri.findElement(By.id
                ("Txt_FamilyMedicalHistory"))
                .clear();
		dri.findElement(By.id
                ("Txt_FamilyMedicalHistory"))
                .sendKeys("This is for the testing");
		dri.findElement(By.id
                ("Btn_FamilyMedicalHistroy"))
                .click();
		new WebDriverWait(dri, 50).until
        (ExpectedConditions.visibilityOfElementLocated
        (By.id("chk_IncludeAllSection")));
		dri.findElement(By.id
                ("chk_IncludeAllSection"))
                .click();
		new WebDriverWait(dri, 50).until
        (ExpectedConditions.visibilityOfElementLocated
        (By.id("Btn_AddHistoryToNotes1")));
		dri.findElement(By.id
                ("Btn_AddHistoryToNotes1"))
                .click();
		dri.switchTo().defaultContent();
		new WebDriverWait(dri, 50).until
        (ExpectedConditions.visibilityOfElementLocated
        (By.xpath("//*[@id='ctl00_ContentPlaceHolder1_btnGenerate']")));
        dri.findElement(By.xpath
                       ("//*[@id='ctl00_ContentPlaceHolder1_btnGenerate']"))
                       .click();
        new WebDriverWait(dri, 50).until
        (ExpectedConditions.visibilityOfElementLocated
        (By.xpath("//*[@id='ctl00_ContentPlaceHolder1_lblSoapNoteText']")));
        boolean b=dri.findElement(By.xpath
            ("//*[@id='ctl00_ContentPlaceHolder1_lblSoapNoteText']"))
            .getText().contains("PMHx: [This is for the testing]");
        boolean c=dri.findElement(By.xpath
                ("//*[@id='ctl00_ContentPlaceHolder1_lblSoapNoteText']"))
                .getText().contains("SHx: [This is for the testing]");
        boolean d=dri.findElement(By.xpath
                ("//*[@id='ctl00_ContentPlaceHolder1_lblSoapNoteText']"))
                .getText().contains("FHx: [This is for the testing]");
        Assert.assertTrue(b & c & d,
        		                    "Patient History Not Getting " +
        		                    "Associated with Soapnote");
	 }
	 
	 @Test(testName="FollowupCreationCheck",enabled=false,priority=2)
	   public void FollowUpCreationCheck() throws InterruptedException, AWTException{
		 String url=dri.getCurrentUrl();
		 if(url.contains("epp")){
			 throw new SkipException("On epp , Soapnote cannot be tested");
		 }
		 dri.findElement(By.id("ctl00_NavigationMenu1_LinkButton1")).click();
		 new WebDriverWait(dri, 50).until
		                  (ExpectedConditions.visibilityOfElementLocated
			              (By.id("ctl00_ContentPlaceHolder1_" +
			                  	  "grdPatients_ctl02_ImageButton3")));
		dri.findElement(By.id("ctl00_ContentPlaceHolder1_" +
					              "grdPatients_ctl02_ImageButton3"))
					              .click();
		dri.navigate().to("http://staging.myemedfusion.com/EmrLite.aspx");
		dri.switchTo().alert().accept();
		new WebDriverWait(dri, 50).until
	                     (ExpectedConditions.visibilityOfElementLocated
	                     (By.id("ctl00_ContentPlaceHolder1_trfollowup")));
	    dri.findElement(By.id("ctl00_ContentPlaceHolder1_trfollowup")).click();
	    dri.switchTo().defaultContent();
	    new WebDriverWait(dri, 50).until
	                     (ExpectedConditions.visibilityOfElementLocated
	                     (By.id("ctl00_ContentPlaceHolder1_txtFollowDate")));
	    dri.findElement(By.id("ctl00_ContentPlaceHolder1_txtFollowDate"))
	                      .sendKeys("6/3/2015");
	    dri.findElement(By.id("ctl00_ContentPlaceHolder1_txtFollowComments"))
	                      .sendKeys("This is for the testing");
	    dri.findElement(By.id("ctl00_ContentPlaceHolder1_btnSave")).click();
	    new WebDriverWait(dri, 50).until
	                     (ExpectedConditions.visibilityOfElementLocated
	                     (By.id("ctl00_ContentPlaceHolder1_lblErr")));
	    System.out.print(dri.findElement(By.id("ctl00_ContentPlaceHolder1_lblErr")).getText());
	    boolean b=dri.findElement(By.id("ctl00_ContentPlaceHolder1" +
	        		                    "_lblErr"))
	        		                 .getText().contains("Record saved successfully.");
	    Assert.assertTrue(b,"Follow-up not getting created.");
	 }
	 
	 @Test(testName="ImageUploadingCheck",enabled=false,priority=2)
	   public void ImageUploadingCheck() throws InterruptedException, AWTException{
		 String url=dri.getCurrentUrl();
		 if(url.contains("epp")){
			 throw new SkipException("On epp , Soapnote cannot be tested");
		 }
		 dri.findElement(By.id("ctl00_NavigationMenu1_LinkButton1")).click();
		 new WebDriverWait(dri, 50).until
		                  (ExpectedConditions.visibilityOfElementLocated
			              (By.id("ctl00_ContentPlaceHolder1_" +
			                  		"grdPatients_ctl02_ImageButton3")));
		 dri.findElement(By.id("ctl00_ContentPlaceHolder1_" +
					              "grdPatients_ctl02_ImageButton3"))
					              .click();
		 dri.navigate().to("http://staging.myemedfusion.com/EmrLite.aspx");
		 dri.switchTo().alert().accept();
		 new WebDriverWait(dri, 50).until
	                      (ExpectedConditions.visibilityOfElementLocated
	                      (By.xpath("//li[13]/a")));
		 dri.findElement(By.xpath("//li[13]/a")).click();
		 new WebDriverWait(dri, 50).until
	                      (ExpectedConditions.visibilityOfElementLocated
	                      (By.id("ctl00_ContentPlaceHolder1_patientFile")));
		 dri.findElement(By.id("ctl00_ContentPlaceHolder1_patientFile"))
			               .sendKeys("/home/sayed/Desktop/" +
			                  		 "Screenshot from 2015-06-24 11:29:47.png");
		 dri.findElement(By.id("ctl00_ContentPlaceHolder1" +
					              "_txtDescription")).sendKeys("This is for the testing.");
		 dri.findElement(By.id("ctl00_ContentPlaceHolder1_btnUpload")).click();
		 new WebDriverWait(dri, 50).until
	                      (ExpectedConditions.visibilityOfElementLocated
	                      (By.id("ctl00_ContentPlaceHolder1_lblError")));
		 boolean b=dri.findElement(By.id("ctl00_" +
					                     "ContentPlaceHolder1_lblError"))
					                  .getText().contains("File Uploaded Successfully!");
		 Assert.assertTrue(b,"Image isn't getting uploaded");		
	 }
	 
	 @Test(testName="Secure Communication Check",enabled=false,priority=2)
		public void SecureCommunicationCheck() 
	    throws InterruptedException, AWTException{
		 String url=dri.getCurrentUrl();
		 if(url.contains("epp")){
			 throw new SkipException("On epp , PMP Secure Communication" +
			 		                 " cannot be tested");
		 }
			dri.findElement(By.id("ctl00_NavigationMenu1_LinkButton1")).click();
			new WebDriverWait(dri, 50).until
		                     (ExpectedConditions.visibilityOfElementLocated
			                 (By.id("ctl00_ContentPlaceHolder1_g" +
			                  		"rdPatients_ctl02_ImageButton1")));
			dri.findElement(By.id("ctl00_ContentPlaceHolder1_g" +
					              "rdPatients_ctl02_ImageButton1"))
					              .click();
			new WebDriverWait(dri, 50).until
	        (ExpectedConditions.visibilityOfElementLocated
	        (By.id("ctl00_ContentPlaceHolder1_ChkUseEmail")));
			boolean b=dri.findElement(By.id("ctl00_ContentPlaceHolder1" +
					                        "_ChkUseEmail")).isSelected();
			if(b==false){
				dri.findElement(By.id("ctl00_ContentPlaceHolder1" +
						              "_ChkUseEmail")).click();
				}
			new WebDriverWait(dri, 50).until
	        (ExpectedConditions.visibilityOfElementLocated
	        (By.id("ctl00_ContentPlaceHolder1_ChkUseEmail")));
			dri.findElement(By.id("ctl00_ContentPlaceHolder1_" +
					              "btnSecureComm")).click();
			new WebDriverWait(dri, 50).until
	        (ExpectedConditions.visibilityOfElementLocated
	        (By.id("ctl00_ContentPlaceHolder1_txtSubject")));
			dri.findElement(By.id("ctl00_ContentPlaceHolder1_txtSubject"))
			                  .sendKeys("Test Mail");
			((JavascriptExecutor)dri).executeScript("tinyMCE.activeEditor" +
					                                ".setContent('This is for test')");
			dri.findElement(By.id("ctl00_ContentPlaceHolder1_btnsend")).click();
			new WebDriverWait(dri, 50).until
	        (ExpectedConditions.visibilityOfElementLocated
	        (By.id("ctl00_ContentPlaceHolder1_Button4")));
			dri.findElement(By.id("ctl00_ContentPlaceHolder1_Button4")).click();
			new WebDriverWait(dri, 50).until
	        (ExpectedConditions.visibilityOfElementLocated
	        (By.xpath("//*[@id='ctl00_ContentPlaceHolder1_Fieldset4']" +
	        		  "/table/tbody/tr[1]/td[2]")));
			Boolean c=dri.findElement(By.xpath("//*[@id='ctl00_" +
					                           "ContentPlaceHolder1_DataListSecMsgs" +
					                           "_ctl02_lblMessage']")).getText()
					                           .contains("This is for test");
			Assert.assertTrue(c,"Secure Message isn't delivered");
			}
	 @Test(testName="CreditCardPaymentCheck",enabled=true,priority=2)
		public void CreditCardPaymentCheck() throws InterruptedException, AWTException{
		    String url=dri.getCurrentUrl();
		    if(url.contains("epp")){
			 throw new SkipException("On epp , PMP Secure Communication" +
			 		                 " cannot be tested");
			 }
			dri.findElement(By.id("ctl00_NavigationMenu1_LinkButton1")).click();
			new WebDriverWait(dri, 50).until
		                     (ExpectedConditions.visibilityOfElementLocated
			                 (By.xpath("//*[@id='ctl00_ContentPlaceHolder1_grd" +
			                 		"Patients_ctl02_imgbtnPayment']")));
			dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_grd" +
					              "Patients_ctl02_imgbtnPayment']")).click();
			new WebDriverWait(dri, 50).until
	        (ExpectedConditions.visibilityOfElementLocated
	        (By.xpath("//*[@id='ctl00_ContentPlaceHolder1_BtnProducts']")));
	        dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_" +
	        		                 "BtnProducts']")).click();
	        new Thread().currentThread().sleep(5000);
	        Select select=new Select(dri.findElement(By.id("ctl00_ContentPlace" +
	        		                                       "Holder1_ddlProgramNameTarge")));
	        select.selectByValue("12002");
	        new Thread().currentThread().sleep(5000);
	        Select selectone=new Select(dri.findElement(By.xpath("//*[@id='ctl00_ContentPlace" +
	        		                                             "Holder1_ddlCardTypes']")));
	        selectone.selectByValue("Visa");
	        DateFormat dateFormat = new SimpleDateFormat("yyyy");
	        Date date = new Date();
	        dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_" +
	        		                 "ddlMonth']")).sendKeys("5");
	        dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1" +
	        		                 "_ddlYear']")).sendKeys(Integer.toString
	        		                		        (Integer.parseInt(dateFormat
	        		                		        .format(date))+1));
	        dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_txtCardNumber']" +
	        		                 "")).sendKeys("4111111111111111");
	        dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_txtCVV']" +
	        "")).sendKeys("123");
	        dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_chkMail']")).click();
	        dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_btnComplete" +
	        		                 "Transaction']")).click();
	        new WebDriverWait(dri, 50).until
	        (ExpectedConditions.visibilityOfElementLocated
	        (By.id("ctl00_ContentPlaceHolder1_lblsucc")));
	        Boolean b=dri.findElement(By.id("ctl00_ContentPlaceHolder1_lblsucc"))
	                                    .getText().contains("Thank you for your payment" +
	                                    		            ". Transaction ID=");
	        Assert.assertTrue(b,"Payment is not taking place properly");
	        }
	 
	 @Test(testName="CashPaymentCheck",enabled=true,priority=2)
		public void CashPaymentCheck() throws InterruptedException, AWTException{
		    String url=dri.getCurrentUrl();
		    if(url.contains("epp")){
			 throw new SkipException("On epp , PMP Secure Communication" +
			 		                 " cannot be tested");
			 }
			dri.findElement(By.id("ctl00_NavigationMenu1_LinkButton1")).click();
			new WebDriverWait(dri, 50).until
		                     (ExpectedConditions.visibilityOfElementLocated
			                 (By.xpath("//*[@id='ctl00_ContentPlaceHolder1_grd" +
			                 		"Patients_ctl02_imgbtnPayment']")));
			dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_grd" +
					              "Patients_ctl02_imgbtnPayment']")).click();
			new WebDriverWait(dri, 50).until
	        (ExpectedConditions.visibilityOfElementLocated
	        (By.xpath("//*[@id='ctl00_ContentPlaceHolder1_BtnProducts']")));
	        dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_" +
	        		                 "BtnProducts']")).click();
	        new Thread().currentThread().sleep(5000);
	        Select select=new Select(dri.findElement(By.id("ctl00_ContentPlace" +
	        		                                       "Holder1_ddlProgramNameTarge")));
	        select.selectByValue("12002");
	        new Thread().currentThread().sleep(5000);
	        Select selectone=new Select(dri.findElement(By.xpath("//*[@id='ctl00_ContentPlace" +
	        		                                             "Holder1_ddlCardTypes']")));
	        selectone.selectByValue("Cash");
	        dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_chkMail']")).click();
	        dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_btnComplete" +
	        		                 "Transaction']")).click();
	        new WebDriverWait(dri, 50).until
	        (ExpectedConditions.visibilityOfElementLocated
	        (By.id("ctl00_ContentPlaceHolder1_lblsucc")));
	        Boolean b=dri.findElement(By.id("ctl00_ContentPlaceHolder1_lblsucc"))
	                                    .getText().contains("Thank you for your payment.");
	        Assert.assertTrue(b,"Payment is not taking place properly");
	        }
	 @Test(testName="CheckPaymentCheck",enabled=true,priority=2)
		public void CheckPaymentCheck() throws InterruptedException, AWTException{
		    String url=dri.getCurrentUrl();
		    if(url.contains("epp")){
			 throw new SkipException("On epp , PMP Secure Communication" +
			 		                 " cannot be tested");
			 }
			dri.findElement(By.id("ctl00_NavigationMenu1_LinkButton1")).click();
			new WebDriverWait(dri, 50).until
		                     (ExpectedConditions.visibilityOfElementLocated
			                 (By.xpath("//*[@id='ctl00_ContentPlaceHolder1_grd" +
			                 		"Patients_ctl02_imgbtnPayment']")));
			dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_grd" +
					              "Patients_ctl02_imgbtnPayment']")).click();
			new WebDriverWait(dri, 50).until
	        (ExpectedConditions.visibilityOfElementLocated
	        (By.xpath("//*[@id='ctl00_ContentPlaceHolder1_BtnProducts']")));
	        dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_" +
	        		                 "BtnProducts']")).click();
	        new Thread().currentThread().sleep(5000);
	        Select select=new Select(dri.findElement(By.id("ctl00_ContentPlace" +
	        		                                       "Holder1_ddlProgramNameTarge")));
	        select.selectByValue("12002");
	        new Thread().currentThread().sleep(5000);
	        Select selectone=new Select(dri.findElement(By.xpath("//*[@id='ctl00_ContentPlace" +
	        		                                             "Holder1_ddlCardTypes']")));
	        selectone.selectByValue("Check");
	        dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_chkMail']")).click();
	        dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_btnComplete" +
	        		                 "Transaction']")).click();
	        new WebDriverWait(dri, 50).until
	        (ExpectedConditions.visibilityOfElementLocated
	        (By.id("ctl00_ContentPlaceHolder1_lblsucc")));
	        Boolean b=dri.findElement(By.id("ctl00_ContentPlaceHolder1_lblsucc"))
	                                    .getText().contains("Thank you for your payment.");
	        Assert.assertTrue(b,"Payment is not taking place properly");
	        }
	 @Test(testName="SplitPaymentCheck",enabled=true,priority=2)
		public void SplitPaymentCheck() throws InterruptedException, AWTException{
		    String url=dri.getCurrentUrl();
		    if(url.contains("epp")){
			 throw new SkipException("On epp , PMP Secure Communication" +
			 		                 " cannot be tested");
			 }
			dri.findElement(By.id("ctl00_NavigationMenu1_LinkButton1")).click();
			new WebDriverWait(dri, 50).until
		                     (ExpectedConditions.visibilityOfElementLocated
			                 (By.xpath("//*[@id='ctl00_ContentPlaceHolder1_grd" +
			                 		"Patients_ctl02_imgbtnPayment']")));
			dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_grd" +
					              "Patients_ctl02_imgbtnPayment']")).click();
			new WebDriverWait(dri, 50).until
	        (ExpectedConditions.visibilityOfElementLocated
	        (By.xpath("//*[@id='ctl00_ContentPlaceHolder1_BtnProducts']")));
	        dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_" +
	        		                 "BtnProducts']")).click();
	        new Thread().currentThread().sleep(5000);
	        Select select=new Select(dri.findElement(By.id("ctl00_ContentPlace" +
	        		                                       "Holder1_ddlProgramNameTarge")));
	        select.selectByValue("12002");
	        new Thread().currentThread().sleep(5000);
	        Select selectone=new Select(dri.findElement(By.xpath("//*[@id='ctl00_ContentPlace" +
	        		                                             "Holder1_ddlCardTypes']")));
	        selectone.selectByValue("Check");
	        dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_chkMail']")).click();
	        dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_btnComplete" +
	        		                 "Transaction']")).click();
	        new WebDriverWait(dri, 50).until
	        (ExpectedConditions.visibilityOfElementLocated
	        (By.id("ctl00_ContentPlaceHolder1_lblsucc")));
	        Boolean b=dri.findElement(By.id("ctl00_ContentPlaceHolder1_lblsucc"))
	                                    .getText().contains("Thank you for your payment.");
	        Assert.assertTrue(b,"Payment is not taking place properly");
	        }
	 
	     public void dircheck() throws IOException, InterruptedException{
			boolean b=false,f=false;
			String a ="Wellness1",c = "Wellness58",d=null;
			dri.findElement(By.name("Login1$UserName")).sendKeys("admin");
			dri.findElement(By.name("Login1$Password")).sendKeys(a);
			dri.findElement(By.name("Login1$LoginButton")).click();
			Thread.sleep(2000);
			try{
			 if(dri.findElement(By.className("err")).isDisplayed()){
				d=a;
				a=c;
				dri.findElement(By.name("Login1$Password")).sendKeys(a);
				dri.findElement(By.name("Login1$LoginButton")).click();
			}
			 else{
				 d=c;
			 }
			}
			catch(org.openqa.selenium.NoSuchElementException e){
				
			}
			try{
			 if(dri.findElement
					 (By.id("ctl00_ContentPlaceHolder1_txtnewpassword")).isDisplayed()){
				dri.findElement
				     (By.id("ctl00_ContentPlaceHolder1_txtnewpassword")).sendKeys(d);
				dri.findElement
				      (By.id("ctl00_ContentPlaceHolder1_txtnewconfirm")).sendKeys(d);
				dri.findElement
				      (By.id("ctl00_ContentPlaceHolder1_ImageButton1")).click();
				Thread.sleep(5000);
				dri.findElement
				       (By.id("ctl00_ContentPlaceHolder1_lnkbacktologin")).click();
				Thread.sleep(5000);
				dri.findElement(By.name("Login1$UserName")).sendKeys("admin");
				dri.findElement(By.name("Login1$Password")).sendKeys(d);
				dri.findElement(By.name("Login1$LoginButton")).click();
				
				}
			}
			catch(org.openqa.selenium.NoSuchElementException e){
				
			}
			}
		public void dirchecktwo(String password,String username) throws InterruptedException{
			try{
			if(dri.findElement(By.id("ctl00_ContentPlaceHolder1_txtnewpassword")).isEnabled()){
				String bwp=password.substring(0,password.length()/2);
				String nwp=password.substring((password.length()/2)+1,password.length());
				dri.findElement(By.id("ctl00_ContentPlaceHolder1_txtnewpassword")).sendKeys(nwp+bwp);
				dri.findElement(By.id("ctl00_ContentPlaceHolder1_txtnewconfirm")).sendKeys(nwp+bwp);
				dri.findElement(By.id("ctl00_ContentPlaceHolder1_ImageButton1")).click();
				Thread.sleep(5000);
				dri.findElement(By.name("ctl00_ContentPlaceHolder1_lnkbacktologin")).click();
				Thread.sleep(5000);
				dri.findElement(By.name("Login1$UserName")).sendKeys(username);
				dri.findElement(By.name("Login1$Password")).sendKeys(nwp+bwp);
				dri.findElement(By.name("Login1$LoginImageButton")).click();
			}
			}
	        catch(org.openqa.selenium.NoSuchElementException e){
				
			}
			
				
		}
		@AfterTest
		public void aftertest(){
	    //dri.quit();
		}

}
