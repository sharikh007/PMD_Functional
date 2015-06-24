package functionalitycheckpro;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

public class Expchk {
	
	@Test
	public void expcheck() throws InterruptedException, AWTException{
		System.getProperty("webdriver.chrome.driver","/path/to/chromedriver");
		WebDriver dri=new FirefoxDriver();
		dri.get("https://staging.myemedfusion.com");
		dri.findElement(By.name("Login1$UserName")).sendKeys("admin");
		dri.findElement(By.name("Login1$Password")).sendKeys("Wellness58");
		dri.findElement(By.name("Login1$LoginButton")).click();
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
		System.out.println(b);
		
	 }
		
		}
	
