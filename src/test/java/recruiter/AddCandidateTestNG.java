package recruiter;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import CommonUtil.ExcelUtil;
import CommonUtil.JavaUtil;
import CommonUtil.PropertyFileUtil;
import CommonUtil.WebDriverUtil;
import CommonUtil.baseClass;
import CommonUtil.listenerImplementation;
import ObjectRepository_POM.RecruiterGear;
import ObjectRepository_POM.RecruiterhomePage;
import ObjectRepository_POM.loginPage;
import ObjectRepository_POM.AddCandidate;

@Listeners(listenerImplementation.class)
public class AddCandidateTestNG extends baseClass{

	PropertyFileUtil pfu = new PropertyFileUtil();
	WebDriverUtil wdu=new WebDriverUtil();
	ExcelUtil eu=new ExcelUtil();
	JavaUtil ju = new JavaUtil();
	public WebDriver sdriver;
	
	@Test(enabled  = true)
	public void addCandidateVD() throws IOException, InterruptedException {
		//get data from property file
		String USERNAME=pfu.getDataFromPropertyFile("username");
		String PASSWORD=pfu.getDataFromPropertyFile("password");
		
		
		Thread.sleep(2000);
		RecruiterGear r = new RecruiterGear(driver);
		r.RecruiterPage(driver);

		
		Thread.sleep(2000);
		
		//login
		loginPage lp = new loginPage(driver);
		lp.login(USERNAME, PASSWORD);

		
		//click on add candidate
		RecruiterhomePage hp=new RecruiterhomePage(driver);
		hp.addCan(driver);
		
		//get data from excel file
		String STATUS=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx","AddCandidate", 1, 2);

		

	    
	    //scrollDown to bottom to select status type
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    WebElement status = driver.findElement(By.name("selectYesOrNo"));
	    js.executeScript("arguments[0].scrollIntoView();", status);
	    
	    
	    //select status type from dropDown
	    Thread.sleep(1000);
	    WebElement statusType= driver.findElement(By.name("selectYesOrNo"));
	    statusType.click();
	    Thread.sleep(3000);
	    wdu.handleDropdown(statusType, STATUS);
	   
	   
	    
	    if (STATUS.equals("Interested")) {
			System.out.println("Interested");
			
			String CANDIDATE_NAME =eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx","AddCandidate", 1, 0);
			String CANDIDATE_EMAIL =eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx","AddCandidate", 1, 1);
			
			 String CONTACT= eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx","AddCandidate",1 , 3);
			 String SOURCE=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx","AddCandidate", 1, 4);
			 String FEEDBACK=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 1,5);
			String JOB_ID= eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx","AddCandidate", 1, 6);
			String LOCATION=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 1, 7);
			String OTHER_LOC = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 1, 8);
			String EDUCATION=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 1, 9);
			String OTHER_EDUCATION=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 1, 10);
			String TOTAL_EXP_YEAR = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 1, 11);
			String TOTAL_EXP_MONTH = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 1, 12);
			String RELEVENT_EXP=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 1, 13);
			String NOTICE_PERIOD=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 1, 14);
			String COMMUNICATION = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 1, 15);
			String CURRENT_CTC = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 1, 16);
			String EXPECTED_CTC = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 1, 17);
			String OFFER_LETTER = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 1, 18);
			String STATUS_TYPE = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 1, 19);
			
			
			
			
			//Scroll up to name field
			WebElement name = driver.findElement(By.name("recruiterName"));
			js.executeScript("arguments[0].scrollIntoView();", name);
			
			//enter candidateName and candidateEmail
		    AddCandidate ac=new AddCandidate(driver);
		    ac.CandidateInfo(CANDIDATE_NAME,CANDIDATE_EMAIL);
		    
		    
		    //enter candidate contact number
			driver.findElement(By.cssSelector("input[name=\"contactNumber\"]")).sendKeys(CONTACT);
			
			  //Source name select DropDown
		    Thread.sleep(1000);
		    WebElement sourceName = driver.findElement(By.name("sourceName"));
		    sourceName.click();
		    Thread.sleep(1000);
		    wdu.handleDropdown(sourceName, SOURCE);
		    
		    //select feedback 
		   WebElement feedback = driver.findElement(By.name("callingFeedback"));
		   feedback.click();
		   Thread.sleep(1000);
		   wdu.handleDropdown(feedback, FEEDBACK);
		  
		   //select job id	    
		   WebElement job_id = driver.findElement(By.id("requirementId"));
		   job_id.click();
		   Thread.sleep(1000);
		   wdu.handleDropdown(job_id, JOB_ID);
		   
		   //select location
		   WebElement location = driver.findElement(By.name("currentLocation"));
		   location.click();
		   Thread.sleep(1000);
		   		if (LOCATION.equals("PCMC") || LOCATION.equals("Pune City")) {
		   			wdu.handleDropdown(location, LOCATION);
		   			System.out.println("pune");
		   		} else if(LOCATION.equals("Other")) {
		   			wdu.handleDropdown(location, LOCATION);
		   			Thread.sleep(1000);
		   			WebElement other = driver.findElement(By.cssSelector("input[name=\"currentLocation\"]"));
		   			other.click();
		   			other.clear();
		   			other.sendKeys(OTHER_LOC);
		   			System.out.println("equal to other");
		   		}else if(!(LOCATION.equals("Other"))) {
		   			
		   			wdu.handleDropdown(location, OTHER_LOC);
		   			Thread.sleep(1000);
		   			WebElement other = driver.findElement(By.cssSelector("input[name=\"currentLocation\"]"));
		   			other.click();
		   			other.clear();
		   			other.sendKeys(OTHER_LOC);
		   			System.out.println("not equal");
		   		}
		   	
		   //select education
		   	WebElement education = driver.findElement(By.name("qualification"));
		   	education.click();
		   	Thread.sleep(1000);
		   	education.sendKeys(EDUCATION);
//		   		if (EDUCATION.equals("Other")) {
//					wdu.handleDropdown(education, EDUCATION);
//					Thread.sleep(1000);
//					WebElement other = driver.findElement(By.cssSelector("input[name=\"qualification\"]"));
//					other.click();
//					other.sendKeys(OTHER_EDUCATION);
//				} else {
//					wdu.handleDropdown(education, EDUCATION);
//					System.out.println("select from dropdown");
//				}
		   		
		   		
		   		//total experience
		   		WebElement totalExpYear = driver.findElement(By.name("experienceYear"));
		   		totalExpYear.sendKeys(TOTAL_EXP_YEAR);
		   		WebElement totalExpMonth = driver.findElement(By.name("experienceMonth"));
		   		totalExpMonth.sendKeys(TOTAL_EXP_MONTH);
		   		
		   		//relevant experience
		   		WebElement releExp = driver.findElement(By.name("relevantExperience"));
		   		releExp.sendKeys(RELEVENT_EXP);
		   		//notice
		   		WebElement notice = driver.findElement(By.name("noticePeriod"));
		   		notice.sendKeys(NOTICE_PERIOD);
		   		
		   		//communication rating
		   		WebElement communication = driver.findElement(By.name("communicationRating"));
		   		communication.sendKeys(COMMUNICATION);
		   		
		   		//current CTC
		   		WebElement current_ctc = driver.findElement(By.name("currentCTCLakh"));
		   		current_ctc.sendKeys(CURRENT_CTC);
		   		
		   		//expected CTC
		   		WebElement expected_ctc = driver.findElement(By.name("expectedCTCLakh"));
		   		expected_ctc.sendKeys(EXPECTED_CTC);
		   		
		   		//scroll
		   		js.executeScript("arguments[0].scrollIntoView();", status);
		   		
		   		//offer letter
		   		WebElement offer_letter = driver.findElement(By.name("holdingAnyOffer"));
		   		offer_letter.click();
		   		Thread.sleep(1000);
		   		wdu.handleDropdown(offer_letter, OFFER_LETTER);
		   		
		   		//status type
		   		WebElement status_type = driver.findElement(By.name("finalStatus"));
		   		offer_letter.click();
		   		Thread.sleep(1000);
		   		wdu.handleDropdown(status_type, STATUS_TYPE);
		   		
		   		
		   		
		   		Thread.sleep(1000);
		   		driver.findElement(By.id("uploadbtn2")).click();
		   		
		   		Thread.sleep(1000);
		   		driver.findElement(By.xpath("//button[text()=\"Yes\"]")).click();
		   		
		   	//error message
		     	List<WebElement> error = driver.findElements(By.className("error-message"));
		     	if (error!=null) {
					for (WebElement webElement : error) {
						Assert.assertFalse(webElement.isDisplayed(), "All Required Field Value with Valid Data are required");
					}
				} else {
					System.out.println("candidate saved successfully");
				}
		     
		   		Thread.sleep(2000);
		   		wdu.ScreenShot(driver, "InterestedVD");
		   		
		   		
		} else {
			
			String CANDIDATE_NAME =eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx","AddCandidate", 1, 0);
			String CANDIDATE_EMAIL =eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx","AddCandidate", 1, 1);
			 String CONTACT= eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx","AddCandidate",1 , 3);
			 String SOURCE=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx","AddCandidate", 1, 4);
			 String FEEDBACK=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 1,5);
			String JOB_ID= eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx","AddCandidate", 1, 6);
			String LOCATION=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 1, 7);
			String OTHER_LOC = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 1, 8);
			String EDUCATION=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 1, 9);
			String OTHER_EDUCATION=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 1, 10);
			String TOTAL_EXP_YEAR = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 1, 11);
			String TOTAL_EXP_MONTH = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 1, 12);
			String RELEVENT_EXP=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 1, 13);
			String NOTICE_PERIOD=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 1, 14);
			String COMMUNICATION = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 1, 15);
			String CURRENT_CTC = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 1, 16);
			String EXPECTED_CTC = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 1, 17);
			String OFFER_LETTER = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 1, 18);
			String STATUS_TYPE = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 1, 19);
			
			
			//Scroll up to name field
			WebElement name = driver.findElement(By.name("recruiterName"));
			js.executeScript("arguments[0].scrollIntoView();", name);
			Thread.sleep(1000);
			
			//enter candidateName and candidateEmail
		    AddCandidate ac=new AddCandidate(driver);
		    ac.CandidateInfo(CANDIDATE_NAME,CANDIDATE_EMAIL);
		    
		    //enter candidate contact number
			driver.findElement(By.cssSelector("input[name=\"contactNumber\"]")).sendKeys(CONTACT);
			
			  //Source name select DropDown
		    Thread.sleep(1000);
		    WebElement sourceName = driver.findElement(By.name("sourceName"));
		    sourceName.click();
		    Thread.sleep(1000);
		    wdu.handleDropdown(sourceName, SOURCE);
		    
		    //select feedback 
		   WebElement feedback = driver.findElement(By.name("callingFeedback"));
		   feedback.click();
		   Thread.sleep(1000);
		   wdu.handleDropdown(feedback, FEEDBACK);
		   
		   //scroll
	   	   js.executeScript("arguments[0].scrollIntoView();", status);
	  
		   
		   //click on add to calling
		   Thread.sleep(1000);
	   		driver.findElement(By.xpath("//button[text()=\"Add To Calling\"]")).click();
	   		
	   		//click on yes
	   		Thread.sleep(1000);
	   		driver.findElement(By.xpath("//button[text()=\"Yes\"]")).click();
	   		
	   	//error message
	     	List<WebElement> error = driver.findElements(By.className("error-message"));
	     	if (error!=null) {
				for (WebElement webElement : error) {
					Assert.assertFalse(webElement.isDisplayed(), "All Required Field Value with Valid Data are required");
					Thread.sleep(1000);
					js.executeScript("arguments[0].scrollIntoView();", error);
				}
			} else {
				System.out.println("candidate saved successfully");
			}
		  
	     	
	   		wdu.ScreenShot(driver, "not-InterestedVD");
			System.out.println("yet to confirm");
		}
	}
//........................................................................................................
	
	@Test(enabled = false)
	public void addCandidateIVD() throws IOException, InterruptedException {
		//get data from property file
				String USERNAME=pfu.getDataFromPropertyFile("username");
				String PASSWORD=pfu.getDataFromPropertyFile("password");
				String STATUS=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx","AddCandidate", 3, 2);
				
				String url = pfu.getDataFromPropertyFile("url");
				driver.get(url);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				
				RecruiterGear r = new RecruiterGear(driver);
				r.RecruiterPage(driver);

				Thread.sleep(2000);
				
				//login
				loginPage lp = new loginPage(driver);
				lp.login(USERNAME, PASSWORD);

				
				//click on add candidate
				RecruiterhomePage hp=new RecruiterhomePage(driver);
				hp.addCan(driver);
				
				
				//scroll down to status
				 JavascriptExecutor js = (JavascriptExecutor) driver;
				 WebElement status = driver.findElement(By.name("selectYesOrNo"));
				 js.executeScript("arguments[0].scrollIntoView();", status);
		      
				 //status type 
				  Thread.sleep(1000);
				    WebElement statusType= driver.findElement(By.name("selectYesOrNo"));
				    statusType.click();
				    Thread.sleep(3000);
				    wdu.handleDropdown(statusType, STATUS);
				    
				    if (STATUS.equals("Interested")) {
						System.out.println("Interested");
						
						String CANDIDATE_NAME =eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx","AddCandidate", 3, 0);
						String CANDIDATE_EMAIL =eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx","AddCandidate", 3, 1);
						
						 String CONTACT= eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx","AddCandidate",3 , 3);
						 String SOURCE=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx","AddCandidate", 3, 4);
						 String FEEDBACK=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 3,5);
						String JOB_ID= eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx","AddCandidate", 3, 6);
						String LOCATION=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 3, 7);
						String OTHER_LOC = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 3, 8);
						String EDUCATION=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 3, 9);
						String OTHER_EDUCATION=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 3, 10);
						String TOTAL_EXP_YEAR = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 3, 11);
						String TOTAL_EXP_MONTH = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 3, 12);
						String RELEVENT_EXP=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 3, 13);
						String NOTICE_PERIOD=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 3, 14);
						String COMMUNICATION = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 3, 15);
						String CURRENT_CTC = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 3, 16);
						String EXPECTED_CTC = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 3, 17);
						String OFFER_LETTER = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 3, 18);
						String STATUS_TYPE = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 3, 19);
						String DOB=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 3, 20);
						
						
						
						//Scroll up to name field
						WebElement name = driver.findElement(By.name("recruiterName"));
						js.executeScript("arguments[0].scrollIntoView();", name);
						
						//enter candidateName and candidateEmail
					    AddCandidate ac=new AddCandidate(driver);
					    ac.CandidateInfo(CANDIDATE_NAME,CANDIDATE_EMAIL);
					    
					    
					    //enter candidate contact number
						driver.findElement(By.cssSelector("input[name=\"contactNumber\"]")).sendKeys(CONTACT);
						
						  //Source name select DropDown
					    Thread.sleep(1000);
					    WebElement sourceName = driver.findElement(By.name("sourceName"));
					    sourceName.click();
					    Thread.sleep(1000);
					    wdu.handleDropdown(sourceName, SOURCE);
					    
					    //select feedback 
					   WebElement feedback = driver.findElement(By.name("callingFeedback"));
					   feedback.click();
					   Thread.sleep(1000);
					   wdu.handleDropdown(feedback, FEEDBACK);
					   Thread.sleep(1000);
					   
					   //select dob
//					  WebElement dob = driver.findElement(By.xpath("(//div[@class=\"calling-tracker-two-input\"])[9]"));
//					  dob.click();
//					  //dob.clear();
//					  Thread.sleep(1000);
//					  dob.sendKeys(DOB);
					  
					   //select job id	    
					   WebElement job_id = driver.findElement(By.id("requirementId"));
					   job_id.click();
					   Thread.sleep(1000);
					   wdu.handleDropdown(job_id, JOB_ID);
					   
					   //select location
					   WebElement location = driver.findElement(By.name("currentLocation"));
					   location.click();
					   Thread.sleep(1000);
					   		if (LOCATION.equals("PCMC") || LOCATION.equals("Pune City")) {
					   			wdu.handleDropdown(location, LOCATION);
					   			System.out.println("pune");
					   		} else if(LOCATION.equals("Other")) {
					   			wdu.handleDropdown(location, LOCATION);
					   			Thread.sleep(1000);
					   			WebElement other = driver.findElement(By.cssSelector("input[name=\"currentLocation\"]"));
					   			other.click();
					   			other.clear();
					   			other.sendKeys(OTHER_LOC);
					   			System.out.println("equal to other");
					   		}else if(!(LOCATION.equals("Other"))) {
					   			
					   			wdu.handleDropdown(location, OTHER_LOC);
					   			Thread.sleep(1000);
					   			WebElement other = driver.findElement(By.cssSelector("input[name=\"currentLocation\"]"));
					   			other.click();
					   			other.clear();
					   			other.sendKeys(OTHER_LOC);
					   			System.out.println("not equal");
					   		}
					   	
					   //select education
					   	WebElement education = driver.findElement(By.name("qualification"));
					   	education.click();
					   	Thread.sleep(1000);
					   	education.sendKeys(EDUCATION);
//					   		if (EDUCATION.equals("Other")) {
//								wdu.handleDropdown(education, EDUCATION);
//								Thread.sleep(1000);
//								WebElement other = driver.findElement(By.cssSelector("input[name=\"qualification\"]"));
//								other.click();
//								other.sendKeys(OTHER_EDUCATION);
//							} else {
//								wdu.handleDropdown(education, EDUCATION);
//								System.out.println("select from dropdown");
//							}
					   		
					   		
					   		//total experience
					   		WebElement totalExpYear = driver.findElement(By.name("experienceYear"));
					   		totalExpYear.sendKeys(TOTAL_EXP_YEAR);
					   		WebElement totalExpMonth = driver.findElement(By.name("experienceMonth"));
					   		totalExpMonth.sendKeys(TOTAL_EXP_MONTH);
					   		
					   		//relevant experience
					   		WebElement releExp = driver.findElement(By.name("relevantExperience"));
					   		releExp.sendKeys(RELEVENT_EXP);
					   		//notice
					   		WebElement notice = driver.findElement(By.name("noticePeriod"));
					   		notice.sendKeys(NOTICE_PERIOD);
					   		
					   		//communication rating
					   		WebElement communication = driver.findElement(By.name("communicationRating"));
					   		communication.sendKeys(COMMUNICATION);
					   		
					   		//current CTC
					   		WebElement current_ctc = driver.findElement(By.name("currentCTCLakh"));
					   		current_ctc.sendKeys(CURRENT_CTC);
					   		
					   		//expected CTC
					   		WebElement expected_ctc = driver.findElement(By.name("expectedCTCLakh"));
					   		expected_ctc.sendKeys(EXPECTED_CTC);
					   		
					   		//scroll
					   		js.executeScript("arguments[0].scrollIntoView();", status);
					   		
					   		//offer letter
					   		WebElement offer_letter = driver.findElement(By.name("holdingAnyOffer"));
					   		offer_letter.click();
					   		Thread.sleep(1000);
					   		wdu.handleDropdown(offer_letter, OFFER_LETTER);
					   		
					   		//status type
					   		WebElement status_type = driver.findElement(By.name("finalStatus"));
					   		offer_letter.click();
					   		Thread.sleep(1000);
					   		wdu.handleDropdown(status_type, STATUS_TYPE);
					   		
//					   		//interview slots
//					   		Thread.sleep(1000);
//					   		WebElement slot = driver.findElement(By.name("availabilityForInterview"));
//					   		slot.click();
					   		
					   		//time slot
//					   		WebElement time = driver.findElement(By.xpath("//div[@class=\"ant-picker-input\"]"));
//					   		time.click();
//					   		Thread.sleep(1500);
//					   		if (time.isEnabled()) {
//					   			System.out.println("Element is enabled.");
//					   			driver.findElement(By.xpath("(//li[@data-value='10'])[1]")).click();
//						   		driver.findElement(By.xpath("(//li[@data-value='00'])[2]")).click();
//						   		driver.findElement(By.cssSelector("//li[@data-value='am']")).click();
//					   		} else {
//					   		    System.out.println("Element is disabled.");
//					   		}
					   		
					   		
					   		Thread.sleep(1000);
					   		driver.findElement(By.id("uploadbtn2")).click();
					   		
					   		Thread.sleep(1000);
					   		driver.findElement(By.xpath("//button[text()=\"Yes\"]")).click();
					   		
					   		Thread.sleep(2000);
					   		
					   	//error message
					     	List<WebElement> error = driver.findElements(By.className("error-message"));
					     	if (error!=null) {
								for (WebElement webElement : error) {
									Assert.assertFalse(webElement.isDisplayed(), "All Required Field Value with Valid Data are required");
								}
							} else {
								System.out.println("candidate saved successfully");
							}
				
					     	Thread.sleep(1000);
					   		wdu.ScreenShot(driver, "InterestedIVD");
					   		System.out.println("candidate saved successfully");
					} else {
						
						String CANDIDATE_NAME =eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx","AddCandidate", 3, 0);
						String CANDIDATE_EMAIL =eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx","AddCandidate", 3, 1);
						 String CONTACT= eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx","AddCandidate",3 , 3);
						 String SOURCE=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx","AddCandidate", 3, 4);
						 String FEEDBACK=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 3,5);
						String JOB_ID= eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx","AddCandidate", 3, 6);
						String LOCATION=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 3, 7);
						String OTHER_LOC = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 3, 8);
						String EDUCATION=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 3, 9);
						String OTHER_EDUCATION=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 3, 10);
						String TOTAL_EXP_YEAR = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 3, 11);
						String TOTAL_EXP_MONTH = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 3, 12);
						String RELEVENT_EXP=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 3, 13);
						String NOTICE_PERIOD=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 3, 14);
						String COMMUNICATION = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 3, 15);
						String CURRENT_CTC = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 3, 16);
						String EXPECTED_CTC = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 3, 17);
						String OFFER_LETTER = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 3, 18);
						String STATUS_TYPE = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 3, 19);
						
						//Scroll up to name field
						WebElement name = driver.findElement(By.name("recruiterName"));
						js.executeScript("arguments[0].scrollIntoView();", name);
						Thread.sleep(1000);
						
						//enter candidateName and candidateEmail
					    AddCandidate ac=new AddCandidate(driver);
					    ac.CandidateInfo(CANDIDATE_NAME,CANDIDATE_EMAIL);
					    
					    //enter candidate contact number
						driver.findElement(By.cssSelector("input[name=\"contactNumber\"]")).sendKeys(CONTACT);
						
						  //Source name select DropDown
					    Thread.sleep(1000);
					    WebElement sourceName = driver.findElement(By.name("sourceName"));
					    sourceName.click();
					    Thread.sleep(1000);
					    wdu.handleDropdown(sourceName, SOURCE);
					    
					    //select feedback 
					   WebElement feedback = driver.findElement(By.name("callingFeedback"));
					   feedback.click();
					   Thread.sleep(1000);
					   wdu.handleDropdown(feedback, FEEDBACK);
					   
					   
					   
					   
					   
					   //scroll
				   	   js.executeScript("arguments[0].scrollIntoView();", status);
					   
					   //click on add to calling
					   Thread.sleep(1000);
				   		driver.findElement(By.xpath("//button[text()=\"Add To Calling\"]")).click();
				   		
				   		//click on yes
				   		Thread.sleep(1000);
				   		driver.findElement(By.xpath("//button[text()=\"Yes\"]")).click();
				   		
				   	//error message
				     	List<WebElement> error = driver.findElements(By.className("error-message"));
				     	if (error!=null) {
							for (WebElement webElement : error) {
								Assert.assertFalse(webElement.isDisplayed(), "All Required Field Value with Valid Data are required");
							}
						} else {
							System.out.println("candidate saved successfully");
						}
					   
				   		Thread.sleep(1000);
				   		wdu.ScreenShot(driver, "not-InterestedIVD");
						System.out.println("yet to confirm");
					}
				 
	}
	
}
