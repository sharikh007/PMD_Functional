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
	
	@Test(testName="Patient Add Check",enabled=false,priority=2)
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
		Select select = new Select(dri.findElement(By.xpath("//*[@id='ctl00_" +
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
		dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_txtpt" +
				                 "Email1']")).sendKeys(Uniquestring()+"@mailinator.com");
		dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlptRace'" +
				                 "]")).sendKeys("African american");
		dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlptTimeZone']"))
		                  .sendKeys("(GMT -10:00) Hawaii");
		dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_txtptUsername']"))
        .sendKeys(Uniquestring());
		
		
        }
	@Test(testName="Secure Communication Check",enabled=true,priority=2)
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
	
