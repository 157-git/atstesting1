package CommonUtil;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class listenerImplementation implements ITestListener {
	
	ExtentReports reports = new ExtentReports();
	JavaUtil ju=new JavaUtil();
	ExtentTest test;
	WebDriverUtil wdu = new WebDriverUtil();

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		String methodName = result.getMethod().getMethodName();
		Reporter.log(methodName + "TestScript Execution is Started :", true);
		test = reports.createTest(methodName);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		String methodname = result.getMethod().getMethodName();
		test.log(Status.PASS, "TestScript Execution is Passed");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		String message = result.getThrowable().toString();
		String methodname = result.getMethod().getMethodName();
		Reporter.log(methodname + "TestScript Execution is Failure :" + message, true);
		test.log(Status.FAIL, "TestScript Execution is Failed");
		
        System.err.println("Test failed: " + result.getName());
        System.err.println("Error: " + result.getThrowable().getMessage());

		try {
			String path=wdu.ScreenShot(baseClass.sdriver, "failReport");
			test.addScreenCaptureFromPath(path);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		String methodName = result.getMethod().getMethodName();
		Reporter.log(methodName + "TestScript execution is skipped", true);
		test.log(Status.SKIP, "TestScript execution is skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		Reporter.log("TestScript execution is started", true);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		Reporter.log("TestScript execution is started", true);
		System.out.println("time out");
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		JavaUtil ju = new JavaUtil();
		Reporter.log(" execution is started", true);

		// use extentsparkReporter class just to configure extent report
		ExtentSparkReporter reporter = new ExtentSparkReporter(
				"./extentreport/report" + ju.getRandomNumber() + ".html");
		reporter.config().setDocumentTitle("Recruiter's Gear");
		reporter.config().setTheme(Theme.DARK);
		reporter.config().setReportName("ADD CANDIDATE");

		// use extentReporter class to generate extend report
		reports = new ExtentReports();
		reports.attachReporter(reporter);
		reports.setSystemInfo("OS", "Window 10");
		reports.setSystemInfo("Browser", "Chrome");
		reports.setSystemInfo("Chromeversion", "120");
		reports.setSystemInfo("Author", "Ameet Singh");
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		test.log(Status.PASS, "TestScript execution is success");
		
		reports.flush();
	}
//.........................................................................................................
	


	



	
	

	

	
	

}
