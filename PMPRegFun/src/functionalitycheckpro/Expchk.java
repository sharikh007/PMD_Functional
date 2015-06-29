package functionalitycheckpro;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class Expchk {
	
	@Test(testName="Add Insurer Check",enabled=true,priority=2)
	public void AddGuarantorCheck() throws InterruptedException, AWTException{
		System.getProperty("webdriver.chrome.driver","/path/to/chromedriver");
		WebDriver dri=new FirefoxDriver();
		dri.get("https://staging.myemedfusion.com");
		dri.findElement(By.name("Login1$UserName")).sendKeys("admin");
		dri.findElement(By.name("Login1$Password")).sendKeys("Wellness58");
		dri.findElement(By.name("Login1$LoginButton")).click();
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
        (By.id("ctl00_ContentPlaceHolder1_grdGuarantorRecords")));
		WebElement lis=dri.findElement(By.id("ctl00_ContentPlaceHolder1_grdGuarantorRecords"));
		List<WebElement> liss=lis.findElements(By.tagName("tr"));
		System.out.print(liss.size());
		new WebDriverWait(dri, 50).until
        (ExpectedConditions.visibilityOfElementLocated
        (By.id("ctl00_ContentPlaceHolder1_btnAddNewGuarantorRecords")));
		dri.findElement(By.id("ctl00_ContentPlaceHolder1_btnAddNewGuaran" +
				              "torRecords")).click();
		new WebDriverWait(dri, 50).until
        (ExpectedConditions.visibilityOfElementLocated
        (By.id("ctl00_ContentPlaceHolder1_guarantorfirstname")));
		dri.findElement(By.id("ctl00_ContentPlaceHolder1_guarantorfirstname" +
				              "")).sendKeys("Tester");
		dri.findElement(By.id("ctl00_ContentPlaceHolder1_guarantorLastName" +
				              "")).sendKeys("Nameis");
		dri.findElement(By.id("ctl00_ContentPlaceHolder1_guarantorCompanyAddress" +
				              "Line1")).sendKeys("This is for the test");
		dri.findElement(By.id("ctl00_ContentPlaceHolder1_guarantorCompanyCountry" +
				              "")).sendKeys("INDIA");
		dri.findElement(By.id("ctl00_ContentPlaceHolder1_guarantorCompanyCity" +
				              "")).sendKeys("For Test");
		dri.findElement(By.id("ctl00_ContentPlaceHolder1_guarantorCompanyState" +
				              "")).sendKeys("for Test");
		dri.findElement(By.id("ctl00_ContentPlaceHolder1_txtptPrimaryPhone" +
        "")).clear();
		dri.findElement(By.id("ctl00_ContentPlaceHolder1_txtptPrimaryPhone" +
				              "")).sendKeys("555-555-5555");
		dri.findElement(By.id("ctl00_ContentPlaceHolder1_submitPatientGuaran" +
				              "torRecords")).click();
		new WebDriverWait(dri, 50).until
        (ExpectedConditions.visibilityOfElementLocated
        (By.id("ctl00_ContentPlaceHolder1_grdGuarantorRecords")));
		WebElement lit=dri.findElement(By.id("ctl00_ContentPlaceHolder1_grdGuarantorRecords"));
		List<WebElement> lisss=lit.findElements(By.tagName("tr"));
		boolean b=lisss.get(liss.size()).getText().contains("TesterNameis");
		Assert.assertTrue(b,"Guarantor Not Getting Added");
		}
	@Test(testName="Secure Communication Check",enabled=false,priority=2)
	public void SecureCommunicationCheckOne(){
		WebDriver dri=new FirefoxDriver();
		dri.get("https://staging.myemedfusion.com");
		dri.findElement(By.name("Login1$UserName")).sendKeys("admin");
		dri.findElement(By.name("Login1$Password")).sendKeys("Wellness58");
		dri.findElement(By.name("Login1$LoginButton")).click();
		dri.navigate().to("http://staging.myemedfusion.com/" +
				         "InsuranceGuarantor/InsuranceGuarantor.aspx?pid=10111");
		new WebDriverWait(dri, 50).until
        (ExpectedConditions.visibilityOfElementLocated
        (By.id("ctl00_ContentPlaceHolder1_grdGuarantorRecords")));
		WebElement lis=dri.findElement(By.id("ctl00_ContentPlaceHolder1_grdGuarantorRecords"));
		List<WebElement> liss=lis.findElements(By.tagName("tr"));
		System.out.print(liss.get(0).getText());		
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
	
