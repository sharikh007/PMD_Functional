package Laitkorgp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;

public class Appmn
{
public static void main(String[] args) throws IOException,InterruptedException {
	List<String> suitesList = new ArrayList<String>();
	TestListenerAdapter tla = new TestListenerAdapter();
	TestNG testng = new TestNG();
	suitesList.add(Appmn.class.getResource("testng.xml").getFile());
	testng.setTestSuites(suitesList);
	testng.addListener(tla);
	testng.run();
}
}