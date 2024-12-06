package Security;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.zaproxy.clientapi.core.ApiResponse;
import org.zaproxy.clientapi.core.ClientApi;
import org.zaproxy.clientapi.core.ClientApiException;

import CommonUtil.ExcelUtil;
import CommonUtil.JavaUtil;
import CommonUtil.PropertyFileUtil;
import CommonUtil.WebDriverUtil;
import CommonUtil.listenerImplementation;
import ObjectRepository_POM.RecruiterGear;
import ObjectRepository_POM.RecruiterhomePage;
import ObjectRepository_POM.ShortListed;
import ObjectRepository_POM.loginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

//@Listeners(listenerImplementation.class)
public class SecurityOWASP {
	
	
	PropertyFileUtil pfu = new PropertyFileUtil();
	WebDriverUtil wdu=new WebDriverUtil();
	ExcelUtil eu=new ExcelUtil();
	JavaUtil ju = new JavaUtil();

	static final String ZAP_PROXY_ADDRESS="localhost";
	static final int ZAP_PROXY_PORT=8081;
	static final String ZAP_API_KEY="1vp76t0v7tfi70ft6ffe414rau";

	private WebDriver driver;
	private ClientApi api;
	
	@BeforeMethod
	public void setup() {
		String proxyServerUrl=ZAP_PROXY_ADDRESS+":"+ZAP_PROXY_PORT;
		
		Proxy proxy=new Proxy();
		proxy.setHttpProxy(proxyServerUrl);
		proxy.setSslProxy(proxyServerUrl);
		
		ChromeOptions chrome=new ChromeOptions();
		
		chrome.setProxy(proxy);
		
		//secure network
		chrome.setAcceptInsecureCerts(true);
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver(chrome);
		api=new ClientApi(ZAP_PROXY_ADDRESS, ZAP_PROXY_PORT, ZAP_API_KEY);
		System.out.println("before method");
		
	}
	
	@Test
	public void RecruiterTest() throws IOException, InterruptedException {
		
		String USERNAME=pfu.getDataFromPropertyFile("username");
		String PASSWORD=pfu.getDataFromPropertyFile("password");
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		String URL="http://93.127.199.85/Dashboard/12/Recruiters";
		
		driver.get("http://93.127.199.85/");
		
		Thread.sleep(2000);
		RecruiterGear r = new RecruiterGear(driver);
		r.RecruiterPage(driver);
	
		Thread.sleep(2000);
		String LoginPageUrl=driver.getCurrentUrl();
		System.out.println(LoginPageUrl);
		
		//login
		loginPage lp = new loginPage(driver);
		lp.login(USERNAME, PASSWORD);

		
		Thread.sleep(1000);
		String RecPageUrl=driver.getCurrentUrl();
		System.out.println(RecPageUrl);
		
		//login if URL matches
		if (RecPageUrl.equals(LoginPageUrl)) {
			System.out.println("login failed");
		} else if(RecPageUrl.equals(URL)){
			System.out.println("login successfull");
			
			RecruiterhomePage hp = new RecruiterhomePage(driver);
			hp.home(driver);
			System.out.println("TEST");
			

			//count the number of candidate
			List<WebElement> initalrows = driver.findElements(By.xpath("//table[@class=\"attendance-table\"]/tbody/tr"));
			//wait for the visibility of candidate
			int inititalRowsCount=initalrows.size();
		    System.out.println("recruiter inital row count : "+inititalRowsCount);
					
			Thread.sleep(1000);
			
			if (inititalRowsCount!=0) {
				System.out.println("candidate data found");
			}else {
				System.out.println("candidate data not found");
			}
		}
		
		
		
	}
	
	@AfterMethod
	public void tearDown() throws ClientApiException {
		System.out.println("test-1");
		
		if (api != null) {
			String title="Recruiter ZAP Sercuity Report";
			String template="traditional-html";
			String description="this is recruiter zap security test report";
			String reportFileName="recruiter-report.html";
			String targetFolder=System.getProperty("user.dir");
			
			
			
				ApiResponse response = api.reports.generate(title, template, null, description, null, null, 
						null, null, null, reportFileName, null, targetFolder, null);
			System.out.println("REPORT GENERATED :"+response.toString());
			
		}
		//driver.quit();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
