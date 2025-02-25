package api.utilities;

import java.text.SimpleDateFormat;

import org.testng.ITestContext;
import org.testng.ITestListener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import java.lang.reflect.Method;
import java.time.Duration;

import org.apache.poi.hpsf.Date;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver; 
//import org.openqa.selenium.edge.EdgeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.internal.TestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener
{
	public ExtentSparkReporter sparkReporter;

	public ExtentReports extent;

	public ExtentTest test;

	String repName;


	public void onStart(ITestContext testContext)

	{

		//String timestamp =new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp 
				
		 repName="Test-Report.html";
		sparkReporter=new ExtentSparkReporter(".\\reports\\"+repName);//specify location of the report
       
		sparkReporter.config().setDocumentTitle("RestAssuredAutomationProject"); // Title of report 
		sparkReporter.config().setReportName("Pet Store Users API"); // name of the report 
		sparkReporter.config().setTheme (Theme. DARK);

		extent=new ExtentReports();

		extent.attachReporter (sparkReporter);

		extent.setSystemInfo("Application", "Pest Store Users API");

		extent.setSystemInfo("Operating System", System.getProperty("os.name"));

		extent.setSystemInfo("User Name", System.getProperty("user.name"));

		extent.setSystemInfo("Enviroment", "QA");
		extent.setSystemInfo("user", "pavan");
	}
	
	public void onTestSuccess (ITestResult result)
	{
	test=extent.createTest(result.getName()); 
	test.assignCategory(result.getMethod().getGroups());
    test.createNode(result.getName()); test.log(Status. PASS, "Test Passed");

	}


	public void onTestFailure (ITestResult result)

	{

	test=extent.createTest(result.getName());

	test.createNode(result.getName());

	test.assignCategory(result.getMethod().getGroups());

	test.log(Status. FAIL, "Test Failed");

	test.log(Status.FAIL, result.getThrowable().getMessage());
	}
	
	public void onTestSkipped(TestResult result)
	{
	test=extent.createTest(result.getName());

	test.createNode(result.getName());

	test.assignCategory(result.getMethod().getGroups());

	test.log (Status.SKIP, "Test Skipped");

	test.log(Status.SKIP, result.getThrowable().getMessage());
	}
	
	public void onFinish(ITestContext testContext)
	{
	extent.flush();	
	}
}