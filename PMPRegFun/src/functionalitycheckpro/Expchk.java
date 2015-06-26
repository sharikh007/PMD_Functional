package functionalitycheckpro;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class Expchk {
	
	@Test(testName="Add Guarantor Check",enabled=true,priority=2)
	public void AddGuarantorCheck() throws InterruptedException, AWTException{
		System.getProperty("webdriver.chrome.driver","/path/to/chromedriver");
		WebDriver dri=new FirefoxDriver();
		dri.get("https://staging.myemedfusion.com");
		dri.findElement(By.name("Login1$UserName")).sendKeys("admin");
		dri.findElement(By.name("Login1$Password")).sendKeys("Wellness58");
		dri.findElement(By.name("Login1$LoginButton")).click();
		dri.findElement(By.xpath("//*[@id='ctl00_NavigationMenu1_" +
				                 "lnkNewPatient']")).click();
		new WebDriverWait(dri, 50).until
	                     (ExpectedConditions.visibilityOfElementLocated
		                 (By.xpath("//*[@id='ctl00_ContentPlaceHolder1_" +
		                 		   "txtptFirstName']")));
		dri.findElement(By.id("ctl00_NavigationMenu1_LinkButton1")).click();
		new WebDriverWait(dri, 50).until
	                     (ExpectedConditions.visibilityOfElementLocated
		                 (By.id("ctl00_ContentPlaceHolder1_g" +
		                  		"rdPatients_ctl02_ImageButton1")));
		dri.findElement(By.id("ctl00_ContentPlaceHolder1_g" +
				              "rdPatients_ctl02_ImageButton1")).click();
		new WebDriverWait(dri, 50).until
        (ExpectedConditions.visibilityOfElementLocated
        (By.xpath("//*[@id='ctl00_ContentPlaceHolder1" +
				  "_imgPatGuabtn']")));
		dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1" +
				                 "_imgPatGuabtn']")).click();
		new WebDriverWait(dri, 50).until
        (ExpectedConditions.visibilityOfElementLocated
        (By.xpath("//*[@id='ctl00_ContentPlaceHolder1_btnAddNewInsuranceRecords']")));
        dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_" +
        		                 "btnAddNewInsuranceRecords']")).click();
        dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_" +
        		                 "txtCarrierList']")).sendKeys("test");
        Thread.sleep(5000);
        Robot robo =new Robot();
        robo.keyPress(KeyEvent.VK_DOWN);
        robo.keyRelease(KeyEvent.VK_DOWN);
        robo.keyPress(KeyEvent.VK_ENTER);
        robo.keyRelease(KeyEvent.VK_ENTER);
        dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1" +
        		                 "_btnProceed']")).click();
        dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_" +
        		                 "TextBox1']")).sendKeys("test");
        dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_" +
        		                 "TextBox3']")).sendKeys("test");
        dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1" +
        		                 "_insuredRelationshiptoPatient']"))
        		                 .sendKeys("SELF");
        dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_" +
        "txtptPrimaryPhone']")).clear();
        dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_" +
        		                 "txtptPrimaryPhone']")).sendKeys("444-444-4444");
        dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_" +
        		                 "patientGroupNo']")).sendKeys("5555555555555555");
        dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_" +
        		                 "patientInsuredGroupEmpName']")).sendKeys("wewewe");
        dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1" +
        		                 "_patientPlanType']")).sendKeys("MEDICAID");
        dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1" +
        		                 "_typeofAgreementCode_0']")).click();
        dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1" +
        		                 "_companyPlanCode']")).sendKeys("44444444444444");
        dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1" +
        		                 "_policyNumber']")).sendKeys("44444444444444");
        dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1" +
        		                 "_isPrimary']")).click();
        dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1" +
        		                 "_submitPatientInsuranceRecords']")).click();
        Thread.sleep(5000);
        boolean bc=dri.findElement(By.xpath("//*[@id='guarantorCreator']/table" +
        		                            "/tbody/tr[1]/td")).getText().contains("Is" +
        		                            " the person who is financially responsible the" +
        		                            " same as named insured?");
        Assert.assertTrue(bc,"Guarantor getting added successfully");
        }
	@Test(testName="Secure Communication Check",enabled=false,priority=2)
	public void SecureCommunicationCheckOne(){
		
		
       }
	
public String Uniquestring(){
	    Random ran = new Random();
		int top = 5;
		char data = ' ';
		String dat = "";

		for (int i=0; i<=top; i++) {
		  data = (char)(ran.nextInt(25)+97);
		  dat = data + dat;
		}
        return dat ;
        
	}
		}
	
