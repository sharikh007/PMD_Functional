package comregpro;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NoSuchElementException;

import jxl.JXLException;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RegproclsOne {
	WebDriver dri;
	String d=null,a=null,b=null;
	
	@DataProvider(name = "myTest")
    public Object[][] createData1() throws JXLException, IOException {

	   FileInputStream fi = new FileInputStream(System.getProperty("user.home")+"/Desktop/PMR/URL/PractiseURLs.xls");
		Workbook w = Workbook.getWorkbook(fi);
		Sheet s = w.getSheet(0);
		Object[][] data = new Object[s.getRows()][4];
		for(int row=0; row <s.getRows();row++)
		{
		data[row][0]= s.getCell(0,row).getContents();
		data[row][1]= s.getCell(1,row).getContents();
		data[row][2]= s.getCell(2,row).getContents();
		data[row][3]= s.getCell(3,row).getContents();
		System.out.println("Value"+data[row][0]);
		
		String url=(String)data[row][0];

		String username=(String)data[row][1];
		
		String password=(String)data[row][2];
		
		String sheet=(String)data[row][3];
		
		 }
              return data;                     
     }
	@Test(testName="loginresponsecheck & login",dataProvider="myTest",enabled=true)
	public void LoginPageResTest(String url,String username,String password,String sheet) throws IOException, InterruptedException, RowsExceededException, BiffException, WriteException{
		//System.setProperty("webdriver.chrome.driver",System.getProperty("user.home")+"/Desktop/PMR/chromedriver");
		dri=new FirefoxDriver();
		dri.manage().window().setPosition(new Point(-2000, 0));
		dri.get(url);
		Boolean b ;
		if(url.contains("epp")){
		b=dri.getTitle().contains("ePatient Portal");
		}else{
	    b=dri.getTitle().contains("Patient Management System");
		}
		Assert.assertTrue(b,"Login page is broken");
		Logincheck(url,username,password,sheet);
		TestOne(url,username,password,sheet);
	}
	public void Logincheck(String url,String username,String password,String sheet) throws IOException, InterruptedException{
		if(url.contains("epp")){
		//dri.get(url);
		dri.findElement(By.name("Login1$UserName")).sendKeys(username);
		dri.findElement(By.name("Login1$Password")).sendKeys(password);
		dri.findElement(By.name("Login1$LoginImageButton")).click();
		dirchecktwo(password,username);
			     
		  }
		  else{
	      dircheck(url);	  
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
	 
	public void TestOne(String url,String username,String password,String sheet) throws BiffException, IOException, RowsExceededException, WriteException, InterruptedException{
		if(url!=null){
			int t=sheet.length();
			File dir = new File(System.getProperty("user.home")+"/Desktop/PMR/Results");
	        dir.mkdir();
		FileOutputStream fo=new FileOutputStream(System.getProperty("user.home")+"/Desktop/PMR/Results/"+sheet.substring(3,t)+".xls");
		WritableWorkbook wk=Workbook.createWorkbook(fo);
		WritableSheet so=wk.createSheet("Shariq",0);
		Label lb=new Label(0,0,url);
		so.addCell(lb);
	    FileInputStream fi = new FileInputStream(System.getProperty("user.home")+"/Desktop/PMR/URL/"+sheet+".xls");
		Workbook w = Workbook.getWorkbook(fi);
		Sheet s = w.getSheet(0);
		Object[][] data = new Object[s.getRows()][1];
		for(int row=1; row <s.getRows();row++)
		 {
			data[row][0]=s.getCell(0,row).getContents();
			String url1=(String)data[row][0];
			System.out.println("second waala"+url1);
			try{
		    dri.get(url.concat(url1));
			dri.switchTo().alert().accept();
			}catch(Exception e){
				
		 }
			Boolean b ;
			if(url.contains("epp")){
			b=dri.getTitle().contains("ePatient Portal");
			}else{
		    b=dri.getTitle().contains("Patient Management System");
			}
		 String sh=null;
		 if(b==true){
			sh="Pass";
			}
			else{
				sh="Fail";
			}
			
			Label lb1=new Label(1,row,sh);
			Label lb2=new Label(0,row,url1);
			so.addCell(lb1);
			so.addCell(lb2);
			}
			wk.write();
			wk.close();
		
		}
				
	}
	
	public void dircheck(String url) throws IOException, InterruptedException{
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
		 if(dri.findElement(By.id("ctl00_ContentPlaceHolder1_txtnewpassword")).isDisplayed()){
			dri.findElement(By.id("ctl00_ContentPlaceHolder1_txtnewpassword")).sendKeys(d);
			dri.findElement(By.id("ctl00_ContentPlaceHolder1_txtnewconfirm")).sendKeys(d);
			dri.findElement(By.id("ctl00_ContentPlaceHolder1_ImageButton1")).click();
			Thread.sleep(5000);
			dri.findElement(By.id("ctl00_ContentPlaceHolder1_lnkbacktologin")).click();
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
	
	@AfterMethod(enabled=true)
	public void AF(){
		dri.quit();
	}
	
	
	

}

