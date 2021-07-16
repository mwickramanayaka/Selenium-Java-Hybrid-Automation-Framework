package framework.utilities;
import java.io.IOException;
 
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import framework.BaseClass.BaseClass;

/**
 * @author manul.wickramanayaka
 *
 */

public class Reporting extends TestListenerAdapter{

	//create the htmlReporter object 
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;

	public void onStart(ITestContext testContext)
	{
		/*generate new extentReport with timeStamp
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
		String repName="Test-Report-"+timeStamp+".html";
        htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+ "\\ExtentReports\\"+repName);//specify location of the report
		*/
		
		//overwrite existing extentReport 
		htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+ "\\ExtentReports\\ExtentReport.html");//specify location of the report
		htmlReporter.loadXMLConfig(System.getProperty("user.dir")+ "/extent-config.xml");

		//create ExtentReports and attach reporter(s)
		extent=new ExtentReports();
		extent.attachReporter(htmlReporter);

		extent.setSystemInfo("Host name","localhost");
		extent.setSystemInfo("OS","Windows10");
		extent.setSystemInfo("Environemnt","QA");
		extent.setSystemInfo("user","manul");

	}

	public void onTestSuccess(ITestResult tr)
	{
		logger=extent.createTest(tr.getName()); // create new entry in the report
		logger.log(Status.PASS,MarkupHelper.createLabel(tr.getName(),ExtentColor.GREEN)); // send the passed information to the report with GREEN color highlighted
	}

	public void onTestFailure(ITestResult tr)
	{
		logger=extent.createTest(tr.getName()); // create new entry in the report
		logger.log(Status.FAIL,MarkupHelper.createLabel(tr.getName(),ExtentColor.RED)); // send the passed information to the report with RED color highlighted
		logger.log(Status.FAIL, "Test case Failed : " + tr.getThrowable());
		
		try {

			String screenshotPath = BaseClass.captureScreen(BaseClass.driver, "loginTest");
			logger.fail("Screenshot is below :  " + logger.addScreenCaptureFromPath(screenshotPath));

		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult tr)
	{
		logger=extent.createTest(tr.getName()); // create new entry in the report
		logger.log(Status.SKIP,MarkupHelper.createLabel(tr.getName(),ExtentColor.ORANGE));
	}

	public void onFinish(ITestContext testContext)
	{
		extent.flush();
	}

}
