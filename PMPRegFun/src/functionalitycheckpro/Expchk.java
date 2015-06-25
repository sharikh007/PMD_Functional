package functionalitycheckpro;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
	
	@Test(testName="Payment Check",enabled=true,priority=2)
	public void PaymentCheck() throws InterruptedException, AWTException{
		System.getProperty("webdriver.chrome.driver","/path/to/chromedriver");
		WebDriver dri=new FirefoxDriver();
		dri.get("https://staging.myemedfusion.com");
		dri.findElement(By.name("Login1$UserName")).sendKeys("admin");
		dri.findElement(By.name("Login1$Password")).sendKeys("Wellness58");
		dri.findElement(By.name("Login1$LoginButton")).click();
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
        selectone.selectByValue("Split Payments");
        dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_chkMail']")).click();
        dri.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_btnComplete" +
        		                 "Transaction']")).click();
        new WebDriverWait(dri, 50).until
        (ExpectedConditions.visibilityOfElementLocated
        (By.id("ctl00_ContentPlaceHolder1_lblsucc")));
        Boolean b=dri.findElement(By.id("ctl00_ContentPlaceHolder1_lblsucc"))
                                    .getText().contains("Thank you for your payment");
        Assert.assertTrue(b,"Payment is not taking place properly");
        }
	@Test(testName="Secure Communication Check",enabled=false,priority=2)
	public void SecureCommunicationCheckOne(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy");
        Date date = new Date();
        String dte=Integer.toString(Integer.parseInt(dateFormat.format(date))+1);
        
        
	}
		}
	
