package recruiter;

import java.io.IOException;
import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import CommonUtil.ExcelUtil;
import CommonUtil.JavaUtil;
import CommonUtil.PropertyFileUtil;
import CommonUtil.WebDriverUtil;
import CommonUtil.baseClass;
import CommonUtil.convertNumberToWords;
import CommonUtil.listenerImplementation;
import ObjectRepository_POM.RecruiterGear;
import ObjectRepository_POM.RecruiterhomePage;
import ObjectRepository_POM.loginPage;
import ObjectRepository_POM.logoutPage;

@Listeners(listenerImplementation.class)
public class salaryCalculation extends baseClass {
	
	PropertyFileUtil pfu = new PropertyFileUtil();
	WebDriverUtil wdu=new WebDriverUtil();
	ExcelUtil eu=new ExcelUtil();
	JavaUtil ju = new JavaUtil();
	public WebDriver sdriver;
	
	@Test
	public void salaryCalculation() throws IOException, InterruptedException {
		
		String USERNAME=pfu.getDataFromPropertyFile("username");
		String PASSWORD=pfu.getDataFromPropertyFile("password");
		String URL="https://rg.157careers.in/Dashboard/12/Recruiters";
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		Thread.sleep(2000);
		RecruiterGear r = new RecruiterGear(driver);
		r.RecruiterPage(driver);
		
		Thread.sleep(2000);
		String LoginPageUrl=driver.getCurrentUrl();
		
		//login
		loginPage lp = new loginPage(driver);
		lp.login(USERNAME, PASSWORD);

		//6-12-24 updated
		Thread.sleep(2000);
		String RecPageUrl=driver.getCurrentUrl();
		System.out.println(RecPageUrl);
		
		//.............LOGIN................
		
		if (RecPageUrl.equals(LoginPageUrl)) {
			System.out.println("login failed");
			WebElement error = driver.findElement(By.className("loginpage-error"));
			if (error.isDisplayed()) {
				System.out.println(error.getText());
			}
			//Assert.fail("Invalid login details");
		} else if(RecPageUrl.equals(URL)) {
			
			System.out.println("login successfull");
			
			//click on add candidate
			RecruiterhomePage hp=new RecruiterhomePage(driver);
			wait.until(ExpectedConditions.visibilityOf(hp.getAddCandidate()));
			hp.addCan(driver);
			
			String CURRENT_CTC = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 1, 15);
			String EXPECTED_CTC = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 1, 16);
			
			//extract only number from current ctc
	   	    Pattern pattern = Pattern.compile("(\\d+)\\.?(\\d+)?");
	        Matcher matcher = pattern.matcher(CURRENT_CTC);
	        String CTC_lakhs="";
	        String CTC_thousand="";
	        if (matcher.find()) {
	        	CTC_lakhs = matcher.group(1); // Before decimal
	        	CTC_thousand = matcher.group(2) != null ? matcher.group(2) : ""; 
	            
//	            System.out.println("Original Input: " + CURRENT_CTC);
//	            System.out.println("Integer Part: " + CTC_lakhs);
//	            System.out.println("Decimal Part: " + CTC_thousand);
	        }
	        
	        //current CTC
	   		WebElement current_ctc_lakh = driver.findElement(By.name("currentCTCLakh"));
	   		current_ctc_lakh.sendKeys(CTC_lakhs);
	        
	   		WebElement current_ctc_thousand = driver.findElement(By.name("currentCTCThousand")); 
	   		current_ctc_thousand.sendKeys(CTC_thousand);
	   		
	        Matcher matcher_1= pattern.matcher(EXPECTED_CTC);
	        String ECTC_lakhs="";
	        String ECTC_thousand="";
	        if (matcher_1.find()) {
	        	ECTC_lakhs = matcher_1.group(1); // Before decimal
	        	ECTC_thousand = matcher_1.group(2) != null ? matcher_1.group(2) : ""; 
	            
//	            System.out.println("Original Input: " + EXPECTED_CTC);
//	            System.out.println("Integer Part: " + ECTC_lakhs);
//	            System.out.println("Decimal Part: " + ECTC_thousand);
	        }
	   
	   		//expected CTC
	   		WebElement expected_ctc = driver.findElement(By.name("expectedCTCLakh"));
	   		expected_ctc.sendKeys(ECTC_lakhs);
	   		
	   		WebElement expected_ctc_thousand = driver.findElement(By.name("expectedCTCThousand")); 
	   		expected_ctc_thousand.sendKeys(ECTC_thousand);
			 
			 //click on help button
			 Thread.sleep(1000);
			 driver.findElement(By.xpath("//button[text()=\"Help\"]")).click();  
			 
			driver.findElement(By.xpath("//p[text()=\"Salary Calculation\"]")).click();
			
			WebElement calcultedHike = driver.findElement(By.xpath("//table[@class=\"table table-bordered\"]/tbody/tr/td[3]/input"));
			String calculated_HIKE = calcultedHike.getAttribute("value");
			System.out.println("SOFTWARE CALCULATED HIKE : "+calculated_HIKE);
			String ONLY_NUMBERS = calculated_HIKE.replaceAll("[^0-9.]", "");
//			System.out.println(ONLY_NUMBERS);
			
			// Convert to integers
			int CTC_L = Integer.parseInt(CTC_lakhs);
			int CTC_T = Integer.parseInt(CTC_thousand);
			int CTC=CTC_L*100000+CTC_T*1000;
//			System.out.println(CTC);
			int ECTC_L = Integer.parseInt(ECTC_lakhs);
			int ECTC_T = Integer.parseInt(ECTC_thousand);
			int ECTC=ECTC_L*100000+ECTC_T*1000;
//			System.out.println(ECTC);
			
			double hike=((double)(ECTC-CTC)/CTC)*100;
			double CALCULATED_HIKE=Math.round(hike * 100.0) / 100.0;
			String hikeAsString = String.format("%.2f", CALCULATED_HIKE);
			System.out.println("AUTOMATION CALCULATED HIKE : "+CALCULATED_HIKE+" %");
			
			if (ONLY_NUMBERS.equals(hikeAsString)) {
				System.out.println("DISPLAYED HIKE % = CALCULATED_HIKE %");
			} else {
				System.out.println("DISPLAYED HIKE % != CALCULATED_HIKE %");
			}
			
			String hike_of="80";
			WebElement expexted_hike = driver.findElement(By.id("expectedHike"));
			expexted_hike.sendKeys(hike_of);
			int HIKE_OF = Integer.parseInt(hike_of);
			
			WebElement calc_exp_hike = driver.findElement(By.xpath("(//input[@class=\"help-form-control\"])[4]"));
			String calc_exp_hike_value=calc_exp_hike.getAttribute("value");
			System.out.println("CALCULATED EXPECTED HIKE :"+calc_exp_hike_value);
			
			double hike_amount=CTC*HIKE_OF/100;
			
			double expexted_ctc=CTC+hike_amount;
			long expexted_ctc_num = (long) expexted_ctc;
//			System.out.println("total expected CTC :"+expexted_ctc);
			
			convertNumberToWords ntw= new convertNumberToWords();
			String Automated_calc_expt_ctc = ntw.convertToIndianWords(expexted_ctc_num);
			System.out.println("AUTOMATED CALCULATED EXPECTED HIKE :"+Automated_calc_expt_ctc);
			
			if (Automated_calc_expt_ctc.equalsIgnoreCase(calc_exp_hike_value)) {
	            System.out.println("DISPLAYED EXPECTED CTC  = CALCULATED EXPECTED CTC");
	        } else {
	            System.out.println("DISPLAYED EXPECTED CTC  != CALCULATED EXPECTED CTC");
	        }
			
			
			WebElement close_btn = driver.findElement(By.className("callingTracker-popup-close-btn"));
			js.executeScript("arguments[0].scrollIntoView();", close_btn);
			close_btn.click();
			
			//logout
			Thread.sleep(1000);
			logoutPage lo=new logoutPage(driver);
			lo.logout(driver, "Yes");
			
			driver.close();
		}
	}

}
