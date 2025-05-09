package recruiter;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import CommonUtil.ExcelUtil;
import CommonUtil.JavaUtil;
import CommonUtil.PropertyFileUtil;
import CommonUtil.WebDriverUtil;
import CommonUtil.baseClass;
import CommonUtil.listenerImplementation;
import ObjectRepository_POM.AddCandidate;
import ObjectRepository_POM.FindCandidate;
import ObjectRepository_POM.RecruiterGear;
import ObjectRepository_POM.RecruiterhomePage;
import ObjectRepository_POM.loginPage;
import ObjectRepository_POM.logoutPage;

@Listeners(listenerImplementation.class)
public class dataFlowOfAddCandidate extends baseClass{

	PropertyFileUtil pfu = new PropertyFileUtil();
	WebDriverUtil wdu=new WebDriverUtil();
	ExcelUtil eu=new ExcelUtil();
	JavaUtil ju = new JavaUtil();
	public WebDriver sdriver;
	
	@Test
	public void addCandidateVD() throws IOException, InterruptedException {
		//get data from property file
		String USERNAME=pfu.getDataFromPropertyFile("username");
		String PASSWORD=pfu.getDataFromPropertyFile("password");
		String URL="https://rg.157careers.in/Dashboard/12/Recruiters";
		FindCandidate fc=new FindCandidate(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		SoftAssert softAssert = new SoftAssert();
		
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
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("spinner-container")));
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
				String CURRENTLY_WORKING=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "AddCandidate", 1, 22);
				
				
				
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
				}else{
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
					} else {
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
			   System.out.println(JOB_ID);
			   
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
			   		WebElement totalExpMonth = driver.findElement(By.name("experienceMonth"));
			   		totalExpMonth.sendKeys(TOTAL_EXP_MONTH);
			   		
			   		//relevant experience
			   		WebElement releExp = driver.findElement(By.name("relevantExperience"));
			   		releExp.sendKeys(RELEVENT_EXP);
			   		//notice
			   		WebElement notice = driver.findElement(By.name("noticePeriod"));
			   		notice.sendKeys(NOTICE_PERIOD);
			   		
			   		//communication rating
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
			   		
			   		
			   		
			   		Thread.sleep(1000);
			   		driver.findElement(By.id("uploadbtn2")).click();
			   		
			   		Thread.sleep(1000);
			   		WebElement Question = driver.findElement(By.xpath("(//input[@class=\"radio-button-email-confirmation\"])[1]"));
			   		Question.click();		
			   		
			   		Thread.sleep(1000);
			   		driver.findElement(By.xpath("//button[text()=\"Save\"]")).click();
			   		
			   		//error message
			     	List<WebElement> error = driver.findElements(By.className("error-message"));
			     	if (!error.isEmpty()) {
						for (WebElement webElement : error) {
							wait.until(ExpectedConditions.visibilityOf(webElement));
							js.executeScript("window.scrollTo(0, arguments[0].getBoundingClientRect().top + window.scrollY - 100);",
						            webElement);
					   		wdu.ScreenShot(driver, "InterestedVD");
							//Assert.assertFalse(webElement.isDisplayed(), "All Required Field Value with Valid Data are required");
					   		softAssert.assertTrue(false, "All Required Field Value with Valid Data are required");
						}
					} else {
						
						System.out.println("candidate saved successfully");
						
						//click on find candidate
						Thread.sleep(3000);
						hp.getFindCandidate().click();
						
						//click on line up tracker
						Thread.sleep(1000);
						fc.getLineupTracker().click();
						
						//enter the value to be searched
						Thread.sleep(1000);
						WebElement search = driver.findElement(By.xpath("//input[@class=\"search-input removeBorderForSearchInput\"]"));
						search.click();
						search.sendKeys(CANDIDATE_EMAIL);
						
						List<WebElement> initialRows = driver.findElements(By.xpath("//table[@class=\"attendance-table\"]/tbody/tr"));
						Thread.sleep(1000);
//						
//						String candidateName = driver.findElement(By.xpath("//table[@class=\"attendance-table\"]/tbody/tr[1]/td[5]")).getText();
//						
//						String candiadteEmail = driver.findElement(By.xpath("//table[@class=\"attendance-table\"]/tbody/tr[1]/td[6]")).getText();
//						
//						String jobId = driver.findElement(By.xpath("//table[@class=\"attendance-table\"]/tbody/tr[1]/td[11]")).getText();
//						
//						if (CANDIDATE_NAME.equals(candidateName) && CANDIDATE_EMAIL.equals(candiadteEmail) && JOB_ID.equals(jobId)) {
//							System.out.println("candidate data found : "+"CANDIDATE DATA FLOW SUCCESSFULL");
//						} else {
//							System.out.println("no candidate data found : "+"NO DATA FLOW");
//						}
						
						// Check if rows are present in the table
						if (!initialRows.isEmpty()) {
						    boolean isDataFound = false;
						    for (WebElement row : initialRows) {
						        String candidateName = row.findElement(By.xpath("//table[@class=\"attendance-table\"]/tbody/tr[1]/td[5]")).getText();
						        System.out.println(candidateName);
						        String candidateEmail = row.findElement(By.xpath("//table[@class=\"attendance-table\"]/tbody/tr[1]/td[6]")).getText();
						        String jobId = row.findElement(By.xpath("//table[@class=\"attendance-table\"]/tbody/tr[1]/td[11]")).getText();
						        System.out.println(jobId);
						        String desigination = row.findElement(By.xpath("//table[@class=\"attendance-table\"]/tbody/tr[1]/td[10]")).getText();
						        System.out.println(desigination);
						        
						        if (
						        		CANDIDATE_NAME.equals(candidateName) &&
						        		CANDIDATE_EMAIL.equals(candidateEmail) && 
						        		JOB_ID.equals(jobId.concat(" - ").concat(desigination))
						        		) {
						            System.out.println("Candidate data found: " + candidateName + " | Email: " + candidateEmail);
						            isDataFound = true;
						            break;  
						        }
						    }
						    
						    if (!isDataFound) {
						        System.out.println("No matching candidate data found: NO DATA FLOW.");
						    }
						} else {
						    System.out.println("No rows found in the table: DATA TABLE IS EMPTY!");
						}


						//.............................................................................................................
						
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
			    wdu.handleDropdown(sourceName, SOURCE);
			    
			    //select job id	    
			  WebElement job_id = driver.findElement(By.id("requirementId"));
			   job_id.click();
			   Thread.sleep(1000);
			   wdu.handleDropdown(job_id, JOB_ID);
				   
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
						wait.until(ExpectedConditions.visibilityOf(webElement));
//						Assert.assertFalse(webElement.isDisplayed(), "All Required Field Value with Valid Data are required");
						softAssert.assertTrue(false, "All Required Field Value with Valid Data are required");
						Thread.sleep(1000);
						//js.executeScript("arguments[0].scrollIntoView();", error);
						
					}
				} else {
					System.out.println("candidate saved successfully");
					
					//click on calling tracker
					fc.getCallingTracker().click();
					
					//click on search field
					Thread.sleep(1000);
					WebElement search = driver.findElement(By.className("forxmarkdiv"));					
					search.sendKeys(CANDIDATE_EMAIL);
					
					List<WebElement> initalrows = driver.findElements(By.xpath("//table[@class=\"attendance-table\"]/tbody/tr"));
					int count=initalrows.size();
					System.out.println("number of candidate :"+count);
					Thread.sleep(1000);
					
					if (!initalrows.isEmpty()) {
					    boolean isDataFound = false;
					    for (WebElement row : initalrows) {
					        String candidateName = row.findElement(By.xpath("//table[@class=\"attendance-table\"]/tbody/tr[1]/td[5]")).getText();
					        System.out.println(candidateName);
					        String candidateEmail = row.findElement(By.xpath("//table[@class=\"attendance-table\"]/tbody/tr[1]/td[6]")).getText();
					        String jobId = row.findElement(By.xpath("//table[@class=\"attendance-table\"]/tbody/tr[1]/td[11]")).getText();
					        System.out.println(jobId);
					        String desigination = row.findElement(By.xpath("//table[@class=\"attendance-table\"]/tbody/tr[1]/td[10]")).getText();
					        System.out.println(desigination);
					        
					        if (
					        		CANDIDATE_NAME.equals(candidateName) &&
					        		CANDIDATE_EMAIL.equals(candidateEmail) && 
					        		JOB_ID.equals(jobId.concat(" - ").concat(desigination))
					        		) {
					            System.out.println("Candidate data found: " + candidateName + " | Email: " + candidateEmail);
					            isDataFound = true;
					            break;  
					        }
					    }
					    
					    if (!isDataFound) {
					        System.out.println("No matching candidate data found: NO DATA FLOW.");
					    }
					} else {
					    System.out.println("No rows found in the table: DATA TABLE IS EMPTY!");
					}
					
					
				}
			  
		     	
		   		wdu.ScreenShot(driver, "not-InterestedVD");
				System.out.println("yet to confirm");
					
			}
		
		  //logout............update:-12-9-24----338-341
			Thread.sleep(1000);
			logoutPage lo=new logoutPage(driver);
			lo.logout(driver, "Yes");
			
			softAssert.assertAll("ASSERTION OCCURED");
		}
		
	}

}
