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
	
	@Test(testName="Patient Add Check",enabled=true,priority=2)
	public void PatientAddCheck() throws InterruptedException, AWTException{
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
		String str=Uniquestring();
		dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_" +
		                 		   "txtptFirstName']")).sendKeys(str);
		dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_" +
				              "txtptLastName']")).sendKeys(Uniquestring());
		Select select = new Select(dri.findElement
				                  (By.xpath("//*[@id='ctl00_" +
				                            "ContentPlaceHolder1_" +
				                            "ddlptMaritalStatus']")));
		select.selectByValue("S");
		dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_txtpt" +
				              "PrimaryPhone']")).sendKeys("444-444-4444");
		dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_txtpt" +
				              "BillingPhone']")).sendKeys("444-444-4444");
		dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlpt" +
				                 "DobMonth']")).sendKeys("02");
		dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlpt" +
				                 "DobDate']")).sendKeys("02");
		dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlpt" +
				                 "DobYear']")).sendKeys("1984");
		dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_rbtpt" +
				                 "SexM']")).click();
		String strone=Uniquestring()+"@mailinator.com";
		System.out.print("Generated Email Id"+strone);
		dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_txtpt" +
				                 "Email1']")).sendKeys(strone);
		dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlptRace'" +
				                 "]")).sendKeys("African american");
		dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlptTimeZone']"))
		                  .sendKeys("(GMT -10:00) Hawaii");
		dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_txtptUsername']"))
                          .sendKeys(Uniquestring());
		Select selectone = new Select(dri.findElement
				                     (By.xpath("//*[@id='ctl00_Content" +
				                               "PlaceHolder1_ddlptGroup']")));
		selectone.selectByValue("1");
		Select selecttwo = new Select(dri.findElement
				                     (By.xpath("//*[@id='ctl00_ContentPlace" +
				                               "Holder1_ddlptCategory']")));
		selecttwo.selectByValue("3");
		Select selectthree = new Select(dri.findElement
				                       (By.xpath("//*[@id='ctl00_ContentPlace" +
				                                 "Holder1_ddlptReferralsource']")));
		selectthree.selectByValue("1");
		dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_txtpt" +
				                 "BaddressL1']")).sendKeys("Test");
		dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlpt" +
				                 "BillingCountry']")).sendKeys("India");
		dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_txtpt" +
				                 "BillingCity']")).sendKeys("Test");
		dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_txtpt" +
				                 "BillingState']")).sendKeys("Test");
		dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_txtpt" +
				                 "BillingZip']")).sendKeys("12345");
		dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_txtpt" +
				                 "ShAddressL1']")).sendKeys("Test");
		dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlpt" +
				                 "ShippingCountry']")).sendKeys("India");
		dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_txtpt" +
				                 "ShippingCity']")).sendKeys("Test");
		dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_txtpt" +
				                 "ShippingState']")).sendKeys("Test");
		dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_txtpt" +
				                 "ShippingZip']")).sendKeys("12345");
		Select selectfour = new Select(dri.findElement
                (By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlLocation']")));
        selectfour.selectByValue("12");
		dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_btnpt" +
				                 "Save']")).click();
		Thread.sleep(5000);
		dri.findElement(By.id("ctl00_NavigationMenu1_LinkButton1")).click();
		new WebDriverWait(dri, 50).until
        (ExpectedConditions.visibilityOfElementLocated
        (By.xpath("//*[@id='ctl00_ContentPlaceHolder1_TxtEmail1']")));
		dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_Txt" +
				                 "Email1']")).sendKeys(strone);
		dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_Button1']" +
				                 "")).click();
		new WebDriverWait(dri, 50).until(ExpectedConditions.textToBePresentInElement
				         (By.xpath("//*[@id='ctl00_ContentPlaceHolder1_grdPatients']/tbody" +
		                           "/tr[2]/td[4]"), strone));
		boolean b=dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1" +
				                        "_grdPatients']/tbody/tr[2]/td[4]"))
				                    .getText().contains(strone);
		Assert.assertTrue(b,"Patient hasn't got added");
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
	
