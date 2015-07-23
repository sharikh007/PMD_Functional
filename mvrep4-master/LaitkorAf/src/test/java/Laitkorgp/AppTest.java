package Laitkorgp;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AppTest {
	WebDriver dri;
@BeforeMethod
@Parameters("browser")
	public void bef(String browser){
		if(browser.equalsIgnoreCase("firefox")){
			dri=new FirefoxDriver();
		}
		else if(browser.equalsIgnoreCase("chrome")){
			System.getProperty("webdriver.chrome.driver","/path/to/chromedriver");
			dri=new ChromeDriver();
		}
		}
 @Test(testName="testone",invocationCount=2, priority=1)
	public void eppregcheck() throws IOException, InterruptedException {
	 dri.get("https://demo2.myemedfusion.com/epp");
	 new WebDriverWait(dri, 50).until
     (ExpectedConditions.visibilityOfElementLocated
     (By.id("Login1_HyperLink2")));
	 dri.findElement(By.id("Login1_HyperLink2")).click();
	 String strone=Uniquestring();
	 new WebDriverWait(dri, 50).until
     (ExpectedConditions.visibilityOfElementLocated
     (By.name("ctl00$ContentPlaceHolder1$txtUserName")));
	 dri.findElement(By.name("ctl00$ContentPlaceHolder1$txtUserName"))
		                  .sendKeys(strone);
	 dri.findElement(By.name("ctl00$ContentPlaceHolder1$txtpassword"))
		                  .sendKeys("Laitkorlko1");
	 dri.findElement(By.name("ctl00$ContentPlaceHolder1$txtrepassword"))
		                  .sendKeys("Laitkorlko1");
	 dri.findElement(By.name("ctl00$ContentPlaceHolder1$txtFirstName"))
		                  .sendKeys("Test");
	 dri.findElement(By.name("ctl00$ContentPlaceHolder1$txtmiddlename"))
		                  .sendKeys("User");
	 dri.findElement(By.name("ctl00$ContentPlaceHolder1$txtlastname"))
		                  .sendKeys("One");
	 dri.findElement(By.name("ctl00$ContentPlaceHolder1$txtprimaryphone"))
		                  .sendKeys("444-444-4444");
	 dri.findElement(By.name("ctl00$ContentPlaceHolder1$txtsecondaryphone"))
		                  .sendKeys("444-444-4444");
	 dri.findElement(By.name("ctl00$ContentPlaceHolder1$txtbillingphone"))
		                  .sendKeys("444-444-4444");
	 dri.findElement(By.name("ctl00$ContentPlaceHolder1$ddlmonth"))
		                  .sendKeys("05");
	 dri.findElement(By.name("ctl00$ContentPlaceHolder1$ddlday"))
		                  .sendKeys("05");
	 dri.findElement(By.name("ctl00$ContentPlaceHolder1$ddlyear"))
		                  .sendKeys("1995");
	 dri.findElement(By.id("ctl00_ContentPlaceHolder1_rdbMale"))
		                  .click();
	 dri.findElement(By.name("ctl00$ContentPlaceHolder1$txtssn"))
		                  .sendKeys("444-44-4444");
	 String str=Uniquestring();
	 dri.findElement(By.name("ctl00$ContentPlaceHolder1$txtemail1"))
		                  .sendKeys(str+"@mailinator.com");
	 System.out.print(str);
	 dri.findElement(By.name("ctl00$ContentPlaceHolder1$txtemail2"))
		                  .sendKeys(Uniquestring()+"@mailinator.com");
	 dri.findElement(By.name("ctl00$ContentPlaceHolder1$ddlrace"))
		                  .sendKeys("African american");
	 dri.findElement(By.name("ctl00$ContentPlaceHolder1$ddlmarital"))
		                  .sendKeys("Single");
	 dri.findElement(By.name("ctl00$ContentPlaceHolder1$ddltimezone"))
		                  .sendKeys("(GMT -5:00) Eastern " +
				                "Time (US &amp; Canada), Bogota, Lima");
	 dri.findElement(By.name("ctl00$ContentPlaceHolder1$ddlreferral"))
		                  .sendKeys("Compounding pharmacy referral");
	 dri.findElement(By.name("ctl00$ContentPlaceHolder1$ddlLocation"))
		                  .sendKeys("Anywhere USA");
	 dri.findElement(By.name("ctl00$ContentPlaceHolder1$txtbillingAddressline1"))
		                  .sendKeys("admin");
	 dri.findElement(By.name("ctl00$ContentPlaceHolder1$txtlbillingaddressline2"))
		                  .sendKeys("admin");
	 dri.findElement(By.name("ctl00$ContentPlaceHolder1$ddlbillingcountry"))
		                  .sendKeys("Afghanistan");
	 dri.findElement(By.name("ctl00$ContentPlaceHolder1$txtBillingState"))
		                  .sendKeys("admin");
	 dri.findElement(By.name("ctl00$ContentPlaceHolder1$txtbillingcity"))
		                  .sendKeys("admin");
	 dri.findElement(By.name("ctl00$ContentPlaceHolder1$txtbillzipcode"))
		                  .sendKeys("12345");
	 dri.findElement(By.id("ctl00_ContentPlaceHolder1_chkusebillingaddress"))
		                  .click();
	 dri.findElement(By.name("ctl00$ContentPlaceHolder1$ImageButton1"))
		                  .click();
	 File fli=((TakesScreenshot)dri).getScreenshotAs(OutputType.FILE);
	 FileUtils.copyFile(fli, new File("/home/sayed/Desktop/PMD_Screens/"+Uniquestring()+".png"));
	 dri.navigate().to("http://www.mailinator.com/inbox.jsp?to="+str);
	 try{
		dri.switchTo().alert().accept();
		}catch(org.openqa.selenium.NoAlertPresentException e){
			
		}
		new WebDriverWait(dri, 50).until
      (ExpectedConditions.visibilityOfElementLocated
      (By.xpath("//*[@id='mailcontainer']/li[1]/a/div[2]")));
	  dri.findElement(By.xpath("//*[@id='mailcontainer']/li[1]/a/div[2]"))
                       .click();
	  Thread.sleep(5000);
	  dri.switchTo().frame("rendermail");
	  WebElement lis=dri.findElement(By.tagName("p"));
	  List<WebElement> les=lis.findElements(By.tagName("a"));
	  dri.navigate().to(les.get(0).getText());
	  dri.findElement(By.id("ctl00_ContentPlaceHolder1_btnLogin"))
      .click();
	  new WebDriverWait(dri, 50).until
      (ExpectedConditions.visibilityOfElementLocated
      (By.name("Login1$UserName")));
	  dri.findElement(By.name("Login1$UserName")).sendKeys(strone);
	  dri.findElement(By.name("Login1$Password")).sendKeys("Laitkorlko1");
	  dri.findElement(By.name("Login1$LoginImageButton")).click();
	  new WebDriverWait(dri, 50).until
      (ExpectedConditions.visibilityOfElementLocated
      (By.id("ctl00_lblPracticeName")));
	  System.out.println(dri.findElement(By.id("ctl00_lblPracticeName")).getText());
	   
		}
  
 public static String Uniquestring(){
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
 
 @AfterMethod
 public void closebrow(){
	 dri.quit();
	 
 }
}