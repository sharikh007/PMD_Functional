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
	
	@Test(testName="Secure Communication Check",enabled=true,priority=2)
	public void SecureCommunicationCheck() throws InterruptedException, AWTException{
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
				              "rdPatients_ctl02_ImageButton1"))
				              .click();
		new WebDriverWait(dri, 50).until
        (ExpectedConditions.visibilityOfElementLocated
        (By.id("ctl00_ContentPlaceHolder1_ChkUseEmail")));
		boolean b=dri.findElement(By.id("ctl00_ContentPlaceHolder1" +
				                        "_ChkUseEmail")).isSelected();
		if(b==false){
			dri.findElement(By.id("ctl00_ContentPlaceHolder1_ChkUseEmail")).click();
		}
		new WebDriverWait(dri, 50).until
        (ExpectedConditions.visibilityOfElementLocated
        (By.id("ctl00_ContentPlaceHolder1_ChkUseEmail")));
		dri.findElement(By.id("ctl00_ContentPlaceHolder1_btnSecureComm")).click();
		new WebDriverWait(dri, 50).until
        (ExpectedConditions.visibilityOfElementLocated
        (By.id("ctl00_ContentPlaceHolder1_txtSubject")));
		dri.findElement(By.id("ctl00_ContentPlaceHolder1_txtSubject")).sendKeys("Test Mail");
		((JavascriptExecutor)dri).executeScript("tinyMCE.activeEditor.setContent('This is for test')");
		dri.findElement(By.id("ctl00_ContentPlaceHolder1_btnsend")).click();
		}
		}
	
