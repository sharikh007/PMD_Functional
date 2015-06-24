package comregpro;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jxl.read.biff.BiffException;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;


public class MnCl {
	
	public static void main(String[] args) throws IOException, BiffException, InterruptedException {
	  List<String> suitesList = new ArrayList<String>();
	  TestListenerAdapter tla = new TestListenerAdapter();
		TestNG testng = new TestNG();
		testng.setOutputDirectory(System.getProperty("user.home")+"/Desktop/PMR/Results");
		suitesList.add(MnCl.class.getResource("Rgrs.xml").getFile());
		testng.setTestSuites(suitesList);
		testng.addListener(tla);
		testng.run();
		Thread.sleep(10000);
		//Zipcls fl=new Zipcls();
		//fl.main(args);
		Thread.sleep(10000);
        Mailing cl=new Mailing();
		cl.MLS();
		
	}
}

