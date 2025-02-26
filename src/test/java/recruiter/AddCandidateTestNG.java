package recruiter;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
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
import CommonUtil.listenerImplementation;
import ObjectRepository_POM.RecruiterGear;
import ObjectRepository_POM.RecruiterhomePage;
import ObjectRepository_POM.loginPage;
import ObjectRepository_POM.logoutPage;
import ObjectRepository_POM.AddCandidate;

@Listeners(listenerImplementation.class)
public class AddCandidateTestNG extends baseClass{

	PropertyFileUtil pfu = new PropertyFileUtil();
	WebDriverUtil wdu=new WebDriverUtil();
	ExcelUtil eu=new ExcelUtil();
	JavaUtil ju = new JavaUtil();
	public WebDriver sdriver;
	
	@Test(enabled = false)
	public void addCandidateVD() throws IOException, InterruptedException {
		//get data from property file
		String USERNAME=pfu.getDataFromPropertyFile("username");
		String PASSWORD=pfu.getDataFromPropertyFile("password");
		String URL="https://rg.157careers.in/Dashboard/12/Recruiters";
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		
		Thread.sleep(2000);
		RecruiterGear r = new RecruiterGear(driver);
		r.RecruiterPage(driver);
		
		Thread.sleep(2000);
		String LoginPageUrl=driver.getCurrentUrl();
		
		//login
		loginPage lp = new loginPage(driver);
		lp.login(USERNAME, PASSWORD);

		//6-12-24 updated
		Thread.sleep(1000);
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
				String TOTAL_EXP_YEAR = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 1, 10);
				String TOTAL_EXP_MONTH = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 1, 11);
				String RELEVENT_EXP=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 1, 12);
				String NOTICE_PERIOD=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 1, 13);
				String COMMUNICATION = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 1, 14);
				String CURRENT_CTC = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 1, 15);
				String EXPECTED_CTC = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 1, 16);
				String OFFER_LETTER = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 1, 17);
				String STATUS_TYPE = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 1, 18);
				
				
				
				
				//Scroll up to name field
				WebElement name = driver.findElement(By.name("recruiterName"));
				js.executeScript("arguments[0].scrollIntoView();", name);
				
				//enter candidateName and candidateEmail
			    AddCandidate ac=new AddCandidate(driver);
			    ac.CandidateInfo(CANDIDATE_NAME,CANDIDATE_EMAIL,CONTACT);
			    
			    
			    //enter candidate contact number
				//driver.findElement(By.cssSelector("input[name=\"contactNumber\"]")).sendKeys(CONTACT);
				
				//Source name select DropDown
			    Thread.sleep(1000);
			    WebElement sourceName = driver.findElement(By.name("sourceName"));
			    sourceName.click();
			    Thread.sleep(1000);
//			    wdu.handleDropdown(sourceName, SOURCE);
			    if (SOURCE.equals("linkedIn") || SOURCE.equals("Naukri") || SOURCE.equals("Indeed ") || SOURCE.equals("Times") ||
			    		SOURCE.equals("Social Media") || SOURCE.equals("Company Page") || SOURCE.equals("Excel") || SOURCE.equals("Friends")) {
					
					wdu.handleDropdown(sourceName, SOURCE);
					System.out.println("selected from dropdown :"+SOURCE);
				} else if(!(SOURCE.equals("Others"))){
					driver.findElement(By.xpath("(//option[text()=\"Others\"])[1]")).click();
					Thread.sleep(1000);
					WebElement other = driver.findElement(By.name("sourceNameOthers"));
					//other.click();
					other.sendKeys(SOURCE);
				}
			    
			    //select feedback 
			   WebElement feedback = driver.findElement(By.name("callingFeedback"));
			   feedback.click();
			   Thread.sleep(1000);
			   if (FEEDBACK.equals("Call Done") || FEEDBACK.equals("Asked for Call Back") || FEEDBACK.equals("No Answer") || FEEDBACK.equals("Network Issue") ||
			    		FEEDBACK.equals("Invalid Number") || FEEDBACK.equals("Need to call back") || FEEDBACK.equals("Do not call again")) {
					
					wdu.handleDropdown(feedback, FEEDBACK);
					System.out.println("selected from dropdown :"+FEEDBACK);
				} else if(!(FEEDBACK.equals("Others"))){
					driver.findElement(By.xpath("(//option[text()=\"Others\"])[2]")).click();
					Thread.sleep(1000);
					WebElement other = driver.findElement(By.name("callingFeedbackOthers"));
					//other.click();
					other.sendKeys(FEEDBACK);
				}
			  
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
//			   		if (EDUCATION.equals("Other")) {
//						wdu.handleDropdown(education, EDUCATION);
//						Thread.sleep(1000);
//						WebElement other = driver.findElement(By.cssSelector("input[name=\"qualification\"]"));
//						other.click();
//						other.sendKeys(OTHER_EDUCATION);
//					} else {
//						wdu.handleDropdown(education, EDUCATION);
//						System.out.println("select from dropdown");
//					}
			   		
			   		
			   		//total experience
			   		WebElement totalExpYear = driver.findElement(By.name("experienceYear"));
			   		totalExpYear.sendKeys(TOTAL_EXP_YEAR);
			   		System.out.println(TOTAL_EXP_YEAR);
			   		WebElement totalExpMonth = driver.findElement(By.name("experienceMonth"));
			   		totalExpMonth.sendKeys(TOTAL_EXP_MONTH);
			   		System.out.println(TOTAL_EXP_MONTH);
			   		
			   		//relevant experience
			   		WebElement releExp = driver.findElement(By.name("relevantExperience"));
			   		releExp.sendKeys(RELEVENT_EXP);
			   		//notice
			   		WebElement notice = driver.findElement(By.name("noticePeriod"));
			   		notice.sendKeys(NOTICE_PERIOD);
			   		
			   		//communication rating
			   		//WebElement communication = driver.findElement(By.name("communicationRating"));
			   		WebElement communication = driver.findElement(By.cssSelector(".plain-input.setwidthandmarginforratings"));
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
							wait.until(ExpectedConditions.visibilityOf(webElement));
							js.executeScript("window.scrollTo(0, arguments[0].getBoundingClientRect().top + window.scrollY - 100);",
						            webElement);
					   		wdu.ScreenShot(driver, "InterestedVD");
							Assert.assertFalse(webElement.isDisplayed(), "All Required Field Value with Valid Data are required");
					
						}
					} else {
						System.out.println("candidate saved successfully");
						Thread.sleep(3000);
				   		wdu.ScreenShot(driver, "InterestedVD");
					}
			     
			   		
			   		
			   	 //logout
					Thread.sleep(1000);
					logoutPage lo=new logoutPage(driver);
					lo.logout(driver, "Yes");
			   		
			   		
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
				String TOTAL_EXP_YEAR = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 1, 10);
				String TOTAL_EXP_MONTH = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 1, 11);
				String RELEVENT_EXP=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 1, 12);
				String NOTICE_PERIOD=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 1, 13);
				String COMMUNICATION = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 1, 14);
				String CURRENT_CTC = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 1, 15);
				String EXPECTED_CTC = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 1, 16);
				String OFFER_LETTER = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 1, 17);
				String STATUS_TYPE = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 1, 18);
				
				
				//Scroll up to name field
				WebElement name = driver.findElement(By.name("recruiterName"));
				js.executeScript("arguments[0].scrollIntoView();", name);
				Thread.sleep(1000);
				
				//enter candidateName and candidateEmail
			    AddCandidate ac=new AddCandidate(driver);
			    ac.CandidateInfo(CANDIDATE_NAME,CANDIDATE_EMAIL,CONTACT);
			    
			    //enter candidate contact number
				//driver.findElement(By.cssSelector("input[name=\"contactNumber\"]")).sendKeys(CONTACT);
				
				  //Source name select DropDown
			    Thread.sleep(1000);
			    WebElement sourceName = driver.findElement(By.name("sourceName"));
			    sourceName.click();
			    Thread.sleep(1000);
//			    wdu.handleDropdown(sourceName, SOURCE);
			    if (SOURCE.equals("linkedIn") || SOURCE.equals("Naukri") || SOURCE.equals("Indeed ") || SOURCE.equals("Times") ||
			    		SOURCE.equals("Social Media") || SOURCE.equals("Company Page") || SOURCE.equals("Excel") || SOURCE.equals("Friends")) {
					
					wdu.handleDropdown(sourceName, SOURCE);
					System.out.println("selected from dropdown :"+SOURCE);
				} else if(!(SOURCE.equals("Others"))){
					driver.findElement(By.xpath("(//option[text()=\"Others\"])[1]")).click();
					Thread.sleep(1000);
					WebElement other = driver.findElement(By.name("sourceNameOthers"));
					//other.click();
					other.sendKeys(SOURCE);
				}
			    
			    //select job id	    
			  WebElement job_id = driver.findElement(By.id("requirementId"));
			   job_id.click();
			   Thread.sleep(1000);
			   wdu.handleDropdown(job_id, JOB_ID);
				   
			    //select feedback 
			   WebElement feedback = driver.findElement(By.name("callingFeedback"));
			   feedback.click();
			   Thread.sleep(1000);
			   if (FEEDBACK.equals("Call Done") || FEEDBACK.equals("Asked for Call Back") || FEEDBACK.equals("No Answer") || FEEDBACK.equals("Network Issue") ||
			    		FEEDBACK.equals("Invalid Number") || FEEDBACK.equals("Need to call back") || FEEDBACK.equals("Do not call again")) {
					
					wdu.handleDropdown(feedback, FEEDBACK);
					System.out.println("selected from dropdown :"+FEEDBACK);
				} else if(!(FEEDBACK.equals("Others"))){
					driver.findElement(By.xpath("(//option[text()=\"Others\"])[2]")).click();
					Thread.sleep(1000);
					WebElement other = driver.findElement(By.name("callingFeedbackOthers"));
					//other.click();
					other.sendKeys(FEEDBACK);
				}
			   
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
						wait.until(ExpectedConditions.visibilityOf(webElement));
						js.executeScript("window.scrollTo(0, arguments[0].getBoundingClientRect().top + window.scrollY - 100);",
					            webElement);
				   		wdu.ScreenShot(driver, "not-InterestedVD");
						Assert.assertFalse(webElement.isDisplayed(), "All Required Field Value with Valid Data are required");
						
					}
				} else {
					System.out.println("candidate saved successfully");
					Thread.sleep(1000);
					wdu.ScreenShot(driver, "not-InterestedVD");
				}
			  
		     	
					
			}
		
		  //logout............update:-12-9-24----338-341
			Thread.sleep(1000);
			logoutPage lo=new logoutPage(driver);
			lo.logout(driver, "Yes");
		}
		
	}
//........................................................................................................
	
	@Test(enabled = true)
	public void addCandidateIVD() throws IOException, InterruptedException {
		//get data from property file
				String USERNAME=pfu.getDataFromPropertyFile("username");
				String PASSWORD=pfu.getDataFromPropertyFile("password");
				String URL="https://rg.157careers.in/Dashboard/12/Recruiters";
				String STATUS=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx","AddCandidate", 3, 2);
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
				
			//	String url = pfu.getDataFromPropertyFile("url");
			//	driver.get(url);
			//	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				
				Thread.sleep(2000);
				RecruiterGear r = new RecruiterGear(driver);
				r.RecruiterPage(driver);

				Thread.sleep(2000);
				String LoginPageUrl=driver.getCurrentUrl();
				System.out.println(LoginPageUrl);
				
				//login
				loginPage lp = new loginPage(driver);
				lp.login(USERNAME, PASSWORD);

				//6-12-24 updated
				Thread.sleep(3000);
				String RecPageUrl=driver.getCurrentUrl();
				System.out.println(RecPageUrl);
				
//				try {
//				    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("user-details")));  
//				    System.out.println("Dashboard Loaded Successfully!");
//				} catch (TimeoutException e) {
//				    System.out.println("Dashboard did not load within the expected time");
//				}
				
				Thread.sleep(1000);
				if (RecPageUrl.equals(LoginPageUrl)) {
					System.out.println("login failed");
					WebElement error = driver.findElement(By.className("loginpage-error"));
					if (error.isDisplayed()) {
						System.out.println(error.getText());
					}
				} else if(RecPageUrl.equals(URL)){
					System.out.println("login successfull");
					
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
							String TOTAL_EXP_YEAR = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 3, 10);
							String TOTAL_EXP_MONTH = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 3, 11);
							String RELEVENT_EXP=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 3, 12);
							String NOTICE_PERIOD=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 3, 13);
							String COMMUNICATION = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 3, 14);
							String CURRENT_CTC = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 3, 15);
							String EXPECTED_CTC = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 3, 16);
							String OFFER_LETTER = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 3, 17);
							String STATUS_TYPE = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 3, 18);
							String DOB=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 3, 19);
							
							
							
							//Scroll up to name field
							WebElement name = driver.findElement(By.name("recruiterName"));
							js.executeScript("arguments[0].scrollIntoView();", name);
							
							//enter candidateName and candidateEmail
						    AddCandidate ac=new AddCandidate(driver);
						    ac.CandidateInfo(CANDIDATE_NAME,CANDIDATE_EMAIL,CONTACT);
						    
						    
						    //enter candidate contact number
							//driver.findElement(By.cssSelector("input[name=\"contactNumber\"]")).sendKeys(CONTACT);
							
							  //Source name select DropDown
						    Thread.sleep(1000);
						    WebElement sourceName = driver.findElement(By.name("sourceName"));
						    sourceName.click();
						    Thread.sleep(1000);
//						    wdu.handleDropdown(sourceName, SOURCE);
						    if (SOURCE.equals("linkedIn") || SOURCE.equals("Naukri") || SOURCE.equals("Indeed ") || SOURCE.equals("Times") ||
						    		SOURCE.equals("Social Media") || SOURCE.equals("Company Page") || SOURCE.equals("Excel") || SOURCE.equals("Friends")) {
								
								wdu.handleDropdown(sourceName, SOURCE);
								System.out.println("selected from dropdown :"+SOURCE);
							} else if(!(SOURCE.equals("Others"))){
								driver.findElement(By.xpath("(//option[text()=\"Others\"])[1]")).click();
								Thread.sleep(1000);
								WebElement other = driver.findElement(By.name("sourceNameOthers"));
								//other.click();
								other.sendKeys(SOURCE);
							}
						    
						    //select feedback 
						   WebElement feedback = driver.findElement(By.name("callingFeedback"));
						   feedback.click();
						   Thread.sleep(1000);
						   if (FEEDBACK.equals("Call Done") || FEEDBACK.equals("Asked for Call Back") || FEEDBACK.equals("No Answer") || FEEDBACK.equals("Network Issue") ||
						    		FEEDBACK.equals("Invalid Number") || FEEDBACK.equals("Need to call back") || FEEDBACK.equals("Do not call again")) {
								
								wdu.handleDropdown(feedback, FEEDBACK);
								System.out.println("selected from dropdown :"+FEEDBACK);
							} else if(!(FEEDBACK.equals("Others"))){
								driver.findElement(By.xpath("(//option[text()=\"Others\"])[2]")).click();
								Thread.sleep(1000);
								WebElement other = driver.findElement(By.name("callingFeedbackOthers"));
								//other.click();
								other.sendKeys(FEEDBACK);
							}
						   
						   //select dob
//						  WebElement dob = driver.findElement(By.xpath("(//div[@class=\"calling-tracker-two-input\"])[9]"));
//						  dob.click();
//						  //dob.clear();
//						  Thread.sleep(1000);
//						  dob.sendKeys(DOB);
						  
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
//						   		if (EDUCATION.equals("Other")) {
//									wdu.handleDropdown(education, EDUCATION);
//									Thread.sleep(1000);
//									WebElement other = driver.findElement(By.cssSelector("input[name=\"qualification\"]"));
//									other.click();
//									other.sendKeys(OTHER_EDUCATION);
//								} else {
//									wdu.handleDropdown(education, EDUCATION);
//									System.out.println("select from dropdown");
//								}
						   		
						   		
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
						   		//WebElement communication = driver.findElement(By.name("communicationRating"));
						   		WebElement communication = driver.findElement(By.cssSelector(".plain-input.setwidthandmarginforratings"));
						   		communication.sendKeys(COMMUNICATION);
						   		
						   		
						    	//extract only number from current ctc
						   	    Pattern pattern = Pattern.compile("(\\d+)\\.?(\\d+)?");
						        Matcher matcher = pattern.matcher(CURRENT_CTC);
						        String CTC_lakhs="";
						        String CTC_thousand="";
						        if (matcher.find()) {
						        	CTC_lakhs = matcher.group(1); // Before decimal
						        	CTC_thousand = matcher.group(2) != null ? matcher.group(2) : ""; 
						            
						            System.out.println("Original Input: " + CURRENT_CTC);
						            System.out.println("Integer Part: " + CTC_lakhs);
						            System.out.println("Decimal Part: " + CTC_thousand);
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
						            
						            System.out.println("Original Input: " + EXPECTED_CTC);
						            System.out.println("Integer Part: " + ECTC_lakhs);
						            System.out.println("Decimal Part: " + ECTC_thousand);
						        }
						   
						   		//expected CTC
						   		WebElement expected_ctc = driver.findElement(By.name("expectedCTCLakh"));
						   		expected_ctc.sendKeys(ECTC_lakhs);
						   		
						   		WebElement expected_ctc_thousand = driver.findElement(By.name("expectedCTCThousand")); 
						   		expected_ctc_thousand.sendKeys(ECTC_thousand);
						   		
						   		
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
						   		
//						   		//interview slots
//						   		Thread.sleep(1000);
//						   		WebElement slot = driver.findElement(By.name("availabilityForInterview"));
//						   		slot.click();
						   		
						   		//time slot
//						   		WebElement time = driver.findElement(By.xpath("//div[@class=\"ant-picker-input\"]"));
//						   		time.click();
//						   		Thread.sleep(1500);
//						   		if (time.isEnabled()) {
//						   			System.out.println("Element is enabled.");
//						   			driver.findElement(By.xpath("(//li[@data-value='10'])[1]")).click();
//							   		driver.findElement(By.xpath("(//li[@data-value='00'])[2]")).click();
//							   		driver.findElement(By.cssSelector("//li[@data-value='am']")).click();
//						   		} else {
//						   		    System.out.println("Element is disabled.");
//						   		}
						   		
						   		
						   		Thread.sleep(1000);
						   		driver.findElement(By.id("uploadbtn2")).click();
						   		
						   		Thread.sleep(1000);
						   		driver.findElement(By.xpath("//button[text()=\"Yes\"]")).click();
						   		
						   		Thread.sleep(2000);
						   		
						   	//error message
						     	List<WebElement> error = driver.findElements(By.className("error-message"));
						     	if (error!=null) {
									for (WebElement webElement : error) {
										wait.until(ExpectedConditions.visibilityOf(webElement));
										js.executeScript("window.scrollTo(0, arguments[0].getBoundingClientRect().top + window.scrollY - 100);",
									            webElement);
								   		wdu.ScreenShot(driver, "InterestedIVD");
										Assert.assertFalse(webElement.isDisplayed(), "All Required Field Value with Valid Data are required");
										
									}
								} else {
									System.out.println("candidate saved successfully");
									Thread.sleep(1000);
							   		wdu.ScreenShot(driver, "InterestedIVD");
								}
					
						     	
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
							String TOTAL_EXP_YEAR = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 3, 10);
							String TOTAL_EXP_MONTH = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 3, 11);
							String RELEVENT_EXP=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 3, 12);
							String NOTICE_PERIOD=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 3, 13);
							String COMMUNICATION = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 3, 14);
							String CURRENT_CTC = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 3, 15);
							String EXPECTED_CTC = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 3, 16);
							String OFFER_LETTER = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 3, 17);
							String STATUS_TYPE = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 3, 18);
							
							//Scroll up to name field
							WebElement name = driver.findElement(By.name("recruiterName"));
							js.executeScript("arguments[0].scrollIntoView();", name);
							Thread.sleep(1000);
							
							//enter candidateName and candidateEmail
						    AddCandidate ac=new AddCandidate(driver);
						    ac.CandidateInfo(CANDIDATE_NAME,CANDIDATE_EMAIL,CONTACT);
						    
						    //enter candidate contact number
							//driver.findElement(By.cssSelector("input[name=\"contactNumber\"]")).sendKeys(CONTACT);
							
							  //Source name select DropDown
						    Thread.sleep(1000);
						    WebElement sourceName = driver.findElement(By.name("sourceName"));
						    sourceName.click();
						    Thread.sleep(1000);
//						    wdu.handleDropdown(sourceName, SOURCE);
						    if (SOURCE.equals("linkedIn") || SOURCE.equals("Naukri") || SOURCE.equals("Indeed ") || SOURCE.equals("Times") ||
						    		SOURCE.equals("Social Media") || SOURCE.equals("Company Page") || SOURCE.equals("Excel") || SOURCE.equals("Friends")) {
								
								wdu.handleDropdown(sourceName, SOURCE);
								System.out.println("selected from dropdown :"+SOURCE);
							} else if(!(SOURCE.equals("Others"))){
								driver.findElement(By.xpath("(//option[text()=\"Others\"])[1]")).click();
								Thread.sleep(1000);
								WebElement other = driver.findElement(By.name("sourceNameOthers"));
								//other.click();
								other.sendKeys(SOURCE);
							}
						    
						    //select feedback 
						   WebElement feedback = driver.findElement(By.name("callingFeedback"));
						   feedback.click();
						   Thread.sleep(1000);
						   if (FEEDBACK.equals("Call Done") || FEEDBACK.equals("Asked for Call Back") || FEEDBACK.equals("No Answer") || FEEDBACK.equals("Network Issue") ||
						    		FEEDBACK.equals("Invalid Number") || FEEDBACK.equals("Need to call back") || FEEDBACK.equals("Do not call again")) {
								
								wdu.handleDropdown(feedback, FEEDBACK);
								System.out.println("selected from dropdown :"+FEEDBACK);
							} else if(!(FEEDBACK.equals("Others"))){
								driver.findElement(By.xpath("(//option[text()=\"Others\"])[2]")).click();
								Thread.sleep(1000);
								WebElement other = driver.findElement(By.name("callingFeedbackOthers"));
								//other.click();
								other.sendKeys(FEEDBACK);
							}
						   
						   
						   
						   
						   
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
									js.executeScript("arguments[0].scrollIntoView(true);", webElement);
									Thread.sleep(1000);
							   		wdu.ScreenShot(driver, "not-InterestedIVD");
									Assert.assertFalse(webElement.isDisplayed(), "All Required Field Value with Valid Data are required");
								}
							} else {
								System.out.println("candidate saved successfully");
								Thread.sleep(1000);
						   		wdu.ScreenShot(driver, "not-InterestedIVD");
							}
						   
					   		
						}
				
				
					    //logout............update:-12-9-24----665-668
						Thread.sleep(1000);
						logoutPage lo=new logoutPage(driver);
						lo.logout(driver, "Yes");
				}
				 
	}
	
}
