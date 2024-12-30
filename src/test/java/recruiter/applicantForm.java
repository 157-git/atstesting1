package recruiter;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


import CommonUtil.ExcelUtil;
import CommonUtil.JavaUtil;
import CommonUtil.PropertyFileUtil;
import CommonUtil.WebDriverUtil;
import CommonUtil.baseClass;
import CommonUtil.dataBaseUtil;
import CommonUtil.listenerImplementation;

@Listeners(listenerImplementation.class)
public class applicantForm extends baseClass{

	PropertyFileUtil pfu = new PropertyFileUtil();
	WebDriverUtil wdu=new WebDriverUtil();
	ExcelUtil eu=new ExcelUtil();
	JavaUtil ju = new JavaUtil();
	public WebDriver sdriver;
	
	
	@BeforeClass
	public void BC() throws IOException {   
		String URL = pfu.getDataFromPropertyFile("applicantUrl");
		String BROWSER = pfu.getDataFromPropertyFile("browser");
		
		if (BROWSER.equals("chrome")) {
			 driver=new ChromeDriver();
		} else if(BROWSER.equals("Edge")){
		     driver=new EdgeDriver();
		}else {
			 driver=new FirefoxDriver();
		}
	
		wdu.maximize(driver);
		wdu.implicitWait(driver);
		driver.get(URL);
		sdriver=driver;
		System.out.println("before class");
	}
	
	@AfterClass			//close the browser
	public void AC() {
		System.out.println("after class");
		
	} 
	
	
	
	
	
	
	@Test
	public void ApplicantForm() throws IOException, InterruptedException {
		WebDriverWait w=new WebDriverWait(driver, Duration.ofSeconds(10));
		JavascriptExecutor j=(JavascriptExecutor) driver;
		System.out.println("applicant form");
		
		String applicantForm = driver.getCurrentUrl();
		
		String NAME = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "applicant", 1, 0);
		String CONTACT_NUMBER = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "applicant", 1, 1);
		String EMAIL = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "applicant", 1, 2);
		String LOCATION = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "applicant", 1, 3);
		String NOTICE_PERIOD = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "applicant", 1, 4);
		String CERTIFICATION = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "applicant", 1, 5);
	//	String DOB = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "applicant", 1, 6);
		String CTC=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "applicant", 1, 7);
		String EXP_CTC=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "applicant", 1, 8);
		String EDUCATION=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "applicant", 1, 9);
		String JOBDESIGNATION=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "applicant", 1, 10);
		String CURRENT_LOCATION=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "applicant", 1, 11);
		String TOTAL_EXPERIENCE=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "applicant", 1, 12);
		String RELEVENT_EXPERIENCE=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "applicant", 1, 13);
		String GENDER=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "applicant", 1, 14);
		String OFFER_LETTER=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "applicant", 1, 15);
		String COMPANYNAME=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "applicant", 1, 16);
		String OFFER_SALARY=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "applicant", 1, 17);
		String OFFER_DETAILS=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "applicant", 1, 18);
		String Q1=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "applicant", 1, 19);
		String Q2=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "applicant", 1, 20);
		
		WebElement Name = driver.findElement(By.name("candidateName"));
		Name.sendKeys(NAME);
		
		WebElement contact_number = driver.findElement(By.name("contactNumber"));
		contact_number.sendKeys(CONTACT_NUMBER);
		
		WebElement email = driver.findElement(By.name("candidateEmail"));
		email.sendKeys(EMAIL);
		
		WebElement location = driver.findElement(By.name("lineUp.preferredLocation"));
		location.sendKeys(LOCATION);
		
		WebElement notice_period = driver.findElement(By.name("lineUp.noticePeriod"));
		notice_period.sendKeys(NOTICE_PERIOD);
		
		WebElement certification = driver.findElement(By.name("lineUp.extraCertification"));
		certification.sendKeys(CERTIFICATION);
		
		WebElement dob = driver.findElement(By.name("lineUp.dateOfBirth"));
		dob.sendKeys("20-01-1999");
		
		String CTC_L = CTC.split(",")[0];
		WebElement ctcLakh = driver.findElement(By.name("lineUp.currentCTCLakh"));
		ctcLakh.sendKeys(CTC_L);
		WebElement ctcThousand = driver.findElement(By.name("lineUp.currentCTCThousand"));
		String ctc_T = "";
		if (CTC.contains(",")) {
			ctc_T=CTC.substring(CTC.indexOf(",")+1).trim();
		}
		ctcThousand.sendKeys(ctc_T);
		
		String EXP_CTC_L = EXP_CTC.split(",")[0];
		WebElement expCTC_L = driver.findElement(By.name("lineUp.expectedCTCLakh"));
		expCTC_L.sendKeys(EXP_CTC_L);
		WebElement expCTC_T = driver.findElement(By.name("lineUp.expectedCTCThousand"));
		String exp_T="";
		if (EXP_CTC.contains(",")) {
			exp_T=EXP_CTC.substring(EXP_CTC.indexOf(",")+1).trim();
		}
		expCTC_T.sendKeys(exp_T);
		
		LocalDate currentDate = LocalDate.now();
        LocalDate nextDate = currentDate.plusDays(1);
        // Format the next date to 'YYYY-MM-DD' format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = nextDate.format(formatter);
		WebElement interviewdate = driver.findElement(By.name("lineUp.availabilityForInterview"));
		interviewdate.sendKeys(formattedDate);
		
		WebElement joiningDate = driver.findElement(By.name("lineUp.expectedJoiningDate"));
		LocalDate joiningAfter = currentDate.plusDays(30);
		String joinDate = joiningAfter.format(formatter);
		joiningDate.sendKeys(joinDate);
		
		Thread.sleep(1000);
		WebElement education = driver.findElement(By.name("lineUp.qualification"));
		education.sendKeys(EDUCATION);
		
		Thread.sleep(1000);
		WebElement jobDesignation = driver.findElement(By.name("jobDesignation"));
		jobDesignation.sendKeys(JOBDESIGNATION);
		
		Thread.sleep(1000);
		WebElement currentLOC = driver.findElement(By.name("currentLocation"));
		currentLOC.sendKeys(CURRENT_LOCATION);
		
		Thread.sleep(1000);
		WebElement totalExp = driver.findElement(By.name("lineUp.experienceYear"));
		totalExp.sendKeys(TOTAL_EXPERIENCE);
		
		Thread.sleep(1000);
		WebElement releventExp = driver.findElement(By.name("lineUp.relevantExperience"));
		releventExp.sendKeys(RELEVENT_EXPERIENCE);
	
		if (GENDER.contains("Male")) {
			driver.findElement(By.xpath("//span[text()=\"Male\"]")).click();
		} else {
			driver.findElement(By.xpath("//span[text()=\"Female\"]")).click();
		}
		
		Thread.sleep(1000);
		if (OFFER_LETTER.contains("Yes")) {
			driver.findElement(By.xpath("//span[text()=\"Yes\"]")).click();
			
			WebElement CompanyName = driver.findElement(By.name("lineUp.companyName"));
			CompanyName.sendKeys(COMPANYNAME);
			
			driver.findElement(By.name("lineUp.offersalary")).sendKeys(OFFER_SALARY);
			
			driver.findElement(By.name("lineUp.offerdetails")).sendKeys(OFFER_DETAILS);
			
		} else {
			driver.findElement(By.xpath("//span[text()=\"No\"]")).click();
		}
		
		driver.findElement(By.name("questions[0].question1")).sendKeys(Q1);
		
		driver.findElement(By.name("questions[0].question3")).sendKeys(Q2);
		
		Thread.sleep(1000);
		File resume=new File("src\\test\\resources\\RecTesting1.pdf");
		WebElement resumeUpload = driver.findElement(By.id("resumeUpload"));
		resumeUpload.sendKeys(resume.getAbsolutePath());
		
		Thread.sleep(1000);
		File photo=new File("src\\test\\resources\\uploadPhotoProfile.jpeg");
		WebElement photoUpload = driver.findElement(By.id("photoUpload"));
		photoUpload.sendKeys(photo.getAbsolutePath());
		
		Thread.sleep(1000);
		WebElement submit = driver.findElement(By.xpath("//button[text()=\"Submit\"]"));
		j.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		Thread.sleep(2000);
		submit.click();
		
		String afterSubmitURL = driver.getCurrentUrl();
	
		WebElement toastmsg = w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"Toastify__toast-body\"]/div[2]")));
		String msg = toastmsg.getText();
		System.out.println(msg);
		Thread.sleep(1000);
		if (msg.equals("Please fill all required fields")) {
			Assert.fail("ALL REQUIRED FILLED DATA IS REQUIRED");
		} else if(msg.equals("Failed to submit details")){
			Assert.fail("FAILED TO SUBMIT DETAILS");
		}else {
			System.out.println("FORM SUBMITED SUCCESSFULLY");
		}		
	
	}
	
}
