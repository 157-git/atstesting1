package recruiter;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.util.Assert;

import CommonUtil.ExcelUtil;
import CommonUtil.JavaUtil;
import CommonUtil.PropertyFileUtil;
import CommonUtil.WebDriverUtil;
import CommonUtil.baseClass;
import CommonUtil.listenerImplementation;
import ObjectRepository_POM.FindCandidate;
import ObjectRepository_POM.RecruiterGear;
import ObjectRepository_POM.RecruiterhomePage;
import ObjectRepository_POM.TeamLeadSection;
import ObjectRepository_POM.TeamLeader;
import ObjectRepository_POM.TeamLeaderHomePage;
import ObjectRepository_POM.loginPage;

@Listeners(listenerImplementation.class)
public class FC_RejectedCandidateTestNG extends baseClass{


	PropertyFileUtil pfu = new PropertyFileUtil();
	WebDriverUtil wdu = new WebDriverUtil();
	ExcelUtil eu = new ExcelUtil();
	JavaUtil ju = new JavaUtil();
	public WebDriver sdriver;
	
	
	@Test(enabled = true)
	public void updateRejectedCandidate() throws IOException, InterruptedException {     //RECRUITER
		
		JavascriptExecutor j=(JavascriptExecutor) driver;
		WebDriverWait w=new WebDriverWait(driver, Duration.ofSeconds(10));
		
		String USERNAME = pfu.getDataFromPropertyFile("username");
		String PASSWORD = pfu.getDataFromPropertyFile("password");	
		

		RecruiterGear r = new RecruiterGear(driver);
		r.RecruiterPage(driver);
		Thread.sleep(2000);
		
		loginPage lp = new loginPage(driver);
		lp.login(USERNAME, PASSWORD);

		Thread.sleep(2000);
		
		//click on find candidate
		RecruiterhomePage hp = new RecruiterhomePage(driver);
		hp.FinCan(driver);
		System.out.println("TEST");
		
		//click on rejected candidate
		FindCandidate fc=new FindCandidate(driver);
		fc.rejectedCandidate(driver);
		
		//count the number of candidate
		List<WebElement> initalrows = driver.findElements(By.xpath("//table[@class=\"attendance-table\"]/tbody/tr"));
		//wait for the visibility of candidate
		//w.until(ExpectedConditions.visibilityOfAllElements(initalrows));
		int inititalRowsCount=initalrows.size();
	    System.out.println("recruiter inital row count : "+inititalRowsCount);
				
		Thread.sleep(1000);
		
		//if candidate with status REJECTED found 
		if(inititalRowsCount!=0) {
			
			//click on filter
			driver.findElement(By.className("rejectedcan-Filter-btn")).click();
			Thread.sleep(1000);
			WebElement status = driver.findElement(By.xpath("//button[text()=\"Final Status\"]"));
			status.click();
			Thread.sleep(500);
			//click on hold status
			WebElement currentType = driver.findElement(By.xpath("//label[contains(text(),\"rejected\")]"));
			currentType.click();
					 
			WebElement action = driver.findElement(By.xpath("(//i[@class=\"fa-regular fa-pen-to-square\"])[1]"));
			action.click();
					
					
					//column name
					eu.writeDataInExcel("Hold Candidate", 1, 0, "Candidate Name");
					eu.writeDataInExcel("Hold Candidate", 1, 1, "Candidate Email");
					eu.writeDataInExcel("Hold Candidate", 1, 2, "contact Number");
					eu.writeDataInExcel("Hold Candidate", 1, 3, "alternate Number");
					eu.writeDataInExcel("Hold Candidate", 1, 4, "source Name");
					eu.writeDataInExcel("Hold Candidate", 1, 5, "job Id");
					eu.writeDataInExcel("Hold Candidate", 1, 6, "job Designation");
					eu.writeDataInExcel("Hold Candidate", 1, 7, "requirement Company");
					eu.writeDataInExcel("Hold Candidate", 1, 8, "current Location");
					eu.writeDataInExcel("Hold Candidate", 1, 9, "full Address");
					eu.writeDataInExcel("Hold Candidate", 1, 10, "calling Feedback");
					eu.writeDataInExcel("Hold Candidate", 1, 11, "dob");
					eu.writeDataInExcel("Hold Candidate", 1, 12, "gender");
					eu.writeDataInExcel("Hold Candidate", 1, 13, "feedBack");
					eu.writeDataInExcel("Hold Candidate", 1, 14, "education");
					eu.writeDataInExcel("Hold Candidate", 1, 15, "passout");
					eu.writeDataInExcel("Hold Candidate", 1, 16, "certification");
					eu.writeDataInExcel("Hold Candidate", 1, 17, "Current Company");
					eu.writeDataInExcel("Hold Candidate", 1, 18, "Total Experience");
					eu.writeDataInExcel("Hold Candidate", 1, 19, "Relevent Experience");
					eu.writeDataInExcel("Hold Candidate", 1, 20, "Notice Period");
					eu.writeDataInExcel("Hold Candidate", 1, 21, "Communication Rating");
					eu.writeDataInExcel("Hold Candidate", 1, 22, "Current CTC");
					eu.writeDataInExcel("Hold Candidate", 1, 23, "Expected CTC");
					eu.writeDataInExcel("Hold Candidate", 1, 24, "offer Letter");
					eu.writeDataInExcel("Hold Candidate", 1, 25, "OfferLetter Msg");
					eu.writeDataInExcel("Hold Candidate", 1, 26, "messageFor TL");
					eu.writeDataInExcel("Hold Candidate", 1, 27, "statusType");
					eu.writeDataInExcel("Hold Candidate", 1, 28, "finalStatus");
					eu.writeDataInExcel("Hold Candidate", 1, 29, "Interviewdate");
					eu.writeDataInExcel("Hold Candidate", 1, 30, "InterviewTime");
					
	
					//updated
					String CandidateName=driver.findElement(By.name("candidateName")).getAttribute("value");
					String CandidateEmail=driver.findElement(By.name("candidateEmail")).getAttribute("value");
					String contactNumber=driver.findElement(By.name("contactNumber")).getAttribute("value");
					String alternateNumber=driver.findElement(By.name("alternateNumber")).getAttribute("value");
					String sourceName=driver.findElement(By.name("sourceName")).getAttribute("value");
				
					WebElement job=driver.findElement(By.name("requirementId"));
					String jobId=job.getAttribute("value");
					System.out.println(jobId);
					
					String jobDesignation=driver.findElement(By.name("jobDesignation")).getAttribute("value");
					String requirementCompany=driver.findElement(By.name("requirementCompany")).getAttribute("value");
					String currentLocation= driver.findElement(By.name("currentLocation")).getAttribute("value");
					String fullAddress=driver.findElement(By.name("fullAddress")).getAttribute("value");
					String callingFeedback=driver.findElement(By.name("callingFeedback")).getAttribute("value");
					String dob=driver.findElement(By.name("lineUp.dateOfBirth")).getAttribute("value");
					
					WebElement gender = driver.findElement(By.name("lineUp.gender"));
					if(gender.isSelected()) {
						String g=gender.getAttribute("value");
						System.out.println(eu.writeDataInExcel("Hold Candidate", 2, 12,g));
					}else {
						eu.writeDataInExcel("Hold Candidate", 2, 12,"");
					}
					
					String feedBack=driver.findElement(By.name("lineUp.feedBack")).getAttribute("value");
					String education=driver.findElement(By.name("qualification")).getAttribute("value");
					String passout=driver.findElement(By.name("lineUp.yearOfPassing")).getAttribute("value");
					String certification=driver.findElement(By.name("lineUp.extraCertification")).getAttribute("value");
					String Currentcompany=driver.findElement(By.name("lineUp.companyName")).getAttribute("value");
					
					String TotalExpYear=driver.findElement(By.name("lineUp.experienceYear")).getAttribute("value");
					String TotalExpMonth=driver.findElement(By.name("lineUp.experienceMonth")).getAttribute("value");	
					
					String releventExp=driver.findElement(By.name("lineUp.relevantExperience")).getAttribute("value");
					String noticePeriod=driver.findElement(By.name("lineUp.noticePeriod")).getAttribute("value");
					String commRating=driver.findElement(By.name("communicationRating")).getAttribute("value");
					
					String CurrentCTCYear=driver.findElement(By.name("lineUp.currentCTCLakh")).getAttribute("value");
					String CurrentCTCMonth=driver.findElement(By.name("lineUp.currentCTCThousand")).getAttribute("value");
					
					
					String expectedCTCYear=driver.findElement(By.name("lineUp.expectedCTCLakh")).getAttribute("value");
					String expectedCTCMonth=driver.findElement(By.name("lineUp.expectedCTCThousand")).getAttribute("value");
					
					String offerLetter=driver.findElement(By.name("lineUp.holdingAnyOffer")).getAttribute("value");
					String OfferLetterMsg=driver.findElement(By.name("lineUp.offerLetterMsg")).getAttribute("value");
					String messageForTL=driver.findElement(By.name("lineUp.msgForTeamLeader")).getAttribute("value");
					String statusType=driver.findElement(By.name("selectYesOrNo")).getAttribute("value");
					String finalStatus=driver.findElement(By.name("lineUp.finalStatus")).getAttribute("value");
					String Interviewdate=driver.findElement(By.name("lineUp.availabilityForInterview")).getAttribute("value");
					String InterviewTime=driver.findElement(By.cssSelector("div[class=\"ant-picker-input\"]")).getAttribute("value");
					
					
					
					//value extracted from candidate form
					eu.writeDataInExcel("Hold Candidate", 2, 0,CandidateName);
					eu.writeDataInExcel("Hold Candidate", 2, 1,CandidateEmail);
					eu.writeDataInExcel("Hold Candidate", 2, 2,contactNumber);
					eu.writeDataInExcel("Hold Candidate", 2, 3,alternateNumber);
					eu.writeDataInExcel("Hold Candidate", 2, 4,sourceName);
					eu.writeDataInExcel("Hold Candidate", 2, 5,jobId);
					eu.writeDataInExcel("Hold Candidate", 2, 6,jobDesignation);
					eu.writeDataInExcel("Hold Candidate", 2, 7,requirementCompany);
					eu.writeDataInExcel("Hold Candidate", 2, 8,currentLocation);
					eu.writeDataInExcel("Hold Candidate", 2, 9,fullAddress);
					eu.writeDataInExcel("Hold Candidate", 2, 10,callingFeedback);
					eu.writeDataInExcel("Hold Candidate", 2, 11,dob);
					eu.writeDataInExcel("Hold Candidate", 2, 13,feedBack);
					eu.writeDataInExcel("Hold Candidate", 2, 14,education);
					eu.writeDataInExcel("Hold Candidate", 2, 15,passout);
					eu.writeDataInExcel("Hold Candidate", 2, 16,certification);
					eu.writeDataInExcel("Hold Candidate", 2, 17,Currentcompany);
					eu.writeDataInExcel("Hold Candidate", 2, 18, TotalExpYear+" year"+","+TotalExpMonth+" month");
					eu.writeDataInExcel("Hold Candidate", 2, 19,releventExp);
					eu.writeDataInExcel("Hold Candidate", 2, 20,noticePeriod);
					eu.writeDataInExcel("Hold Candidate", 2, 21,commRating);
					eu.writeDataInExcel("Hold Candidate",2, 22, CurrentCTCYear+" Lakh"+","+CurrentCTCMonth+" Thousand");
					eu.writeDataInExcel("Hold Candidate",2, 23, expectedCTCYear+" Lakh"+","+expectedCTCMonth+" Thousand");
					eu.writeDataInExcel("Hold Candidate", 2, 24,offerLetter);
					eu.writeDataInExcel("Hold Candidate", 2, 25,OfferLetterMsg);
					eu.writeDataInExcel("Hold Candidate", 2, 26,messageForTL);
					eu.writeDataInExcel("Hold Candidate", 2, 27,statusType);
					eu.writeDataInExcel("Hold Candidate", 2, 28,finalStatus);
					eu.writeDataInExcel("Hold Candidate", 2, 29,Interviewdate);
					eu.writeDataInExcel("Hold Candidate", 2, 30,InterviewTime);
					
					
					WebElement cancelBtn = driver.findElement(By.id("uploadbtn2"));
					j.executeScript("arguments[0].scrollIntoView();", cancelBtn);
					Thread.sleep(500);
					
					//change status to interview schedule
					WebElement UpdatefinalStatus = driver.findElement(By.name("lineUp.finalStatus"));
					wdu.handleDropdown(UpdatefinalStatus, "Interview Schedule");
					
					//clear date
					Thread.sleep(500);
					WebElement updatedDate = driver.findElement(By.name("lineUp.availabilityForInterview"));
					j.executeScript("arguments[0].value = '';", updatedDate);
					
					j.executeScript("arguments[0].value = '2024-12-27';", updatedDate);
					
					//click on update 
					Thread.sleep(500);
					WebElement update = driver.findElement(By.xpath("//button[text()=\"Update Data\"]"));
					update.click();
					
					Thread.sleep(6000);
					
					//click on short listed
					hp.home(driver);
					
					//enter candidate name
					driver.findElement(By.className("form-control")).sendKeys(CandidateName);
					Thread.sleep(1000);
					
					List<WebElement> rows = driver.findElements(By.xpath("//table[@class=\"attendance-table\"]/tbody/tr"));
					int RowsCount=rows.size();
				    System.out.println("recruiter inital row count : "+RowsCount);
				    if (RowsCount!=0) {
						System.out.println("candidate with data found");
					} else {
						System.out.println("candidate not found");
					}
					
			}
			else 
			{
					System.out.println("No candidate are rejected");
					
					Thread.sleep(1000);
					//click on short listed
					hp.home(driver);
					
					//click on calendar
					driver.findElement(By.cssSelector(".fa-regular.fa-calendar")).click();
					
					//click on selected date
					driver.findElement(By.xpath("(//div[@class=\"highlighted-date\"])[1]")).click();
					
					//click on edit option
					driver.findElement(By.xpath("(//i[@class=\"fa-regular fa-pen-to-square\"])[1]")).click();
					
					//select interview rounds
					Thread.sleep(1000);
					WebElement interviewRound = driver.findElement(By.xpath("//table[@class=\"table table-bordered\"]/tbody/tr/td[2]/select"));
					wdu.handleDropdown(interviewRound, " L1 Round");
					
					//select interview response
					Thread.sleep(1000);
					WebElement interviewResponse = driver.findElement(By.xpath("//table[@class=\"table table-bordered\"]/tbody/tr/td[3]/select"));
					wdu.handleDropdown(interviewResponse, "Rejected");
					
					//click on update
					WebElement update = driver.findElement(By.xpath("//button[text()=\"Update\"]"));
					update.click();
					
					Thread.sleep(6000);
					//click on rejected candidate
					fc.rejectedCandidate(driver);
					System.out.println("TEST-1");
					
					//count the number of candidate
					List<WebElement> finalrows = driver.findElements(By.xpath("//table[@class=\"attendance-table\"]/tbody/tr"));
					//wait for the visibility of candidate
					//w.until(ExpectedConditions.visibilityOfAllElements(initalrows));
					int finalRowsCount=finalrows.size();
				    System.out.println("recruiter final row count : "+finalRowsCount);
				    
				    if (inititalRowsCount==finalRowsCount) {
						System.out.println("data not updated");
					} else {
						System.out.println("data updated");
					}
				    
			}
			
	}

	
	
	
	@Test(enabled = false)
	public void UpdaterejectedCandidateByTL() throws IOException, InterruptedException {
		
		JavascriptExecutor j=(JavascriptExecutor) driver;
		WebDriverWait w=new WebDriverWait(driver, Duration.ofSeconds(10));
		
		String USERNAME = pfu.getDataFromPropertyFile("username");
		String PASSWORD = pfu.getDataFromPropertyFile("password");	
		

		RecruiterGear r = new RecruiterGear(driver);
		r.RecruiterPage(driver);
		Thread.sleep(2000);
		
		loginPage lp = new loginPage(driver);
		lp.login(USERNAME, PASSWORD);

		Thread.sleep(2000);
		
		//click on find candidate
		RecruiterhomePage hp = new RecruiterhomePage(driver);
		hp.FinCan(driver);
		System.out.println("TEST");
		
		//click on hold candidate
		FindCandidate fc=new FindCandidate(driver);
		fc.rejectedCandidate(driver);
		
		//count the number of candidate
		List<WebElement> initalrows = driver.findElements(By.xpath("//table[@class=\"attendance-table\"]/tbody/tr"));
		//wait for the visibility of candidate
		//w.until(ExpectedConditions.visibilityOfAllElements(initalrows));
		int inititalRowsCount=initalrows.size();
	    System.out.println("recruiter inital row count : "+inititalRowsCount);
				
		Thread.sleep(1000);
		
		if(inititalRowsCount!=0) {
			
			//click on filter
			driver.findElement(By.className("rejectedcan-Filter-btn")).click();
			Thread.sleep(1000);
			WebElement status = driver.findElement(By.xpath("//button[text()=\"Final Status\"]"));
			status.click();
			Thread.sleep(500);
			
			//click on rejected status
			WebElement currentType = driver.findElement(By.xpath("//label[contains(text(),\"rejected\")]"));
			currentType.click();
					 
			//click on update
			WebElement action = driver.findElement(By.xpath("(//i[@class=\"fa-regular fa-pen-to-square\"])[1]"));
			action.click();
			
			//go to the short listed and update the data
			//get candidate name 
			Thread.sleep(500);
			String CandidateName = driver.findElement(By.name("candidateName")).getAttribute("value");
			
			//click on cancel
			Thread.sleep(500);
			driver.findElement(By.id("uploadbtn2")).click();
			
			//click on short listed
			hp.home(driver);
			
			//click on calendar
			driver.findElement(By.cssSelector(".fa-regular.fa-calendar")).click();
			
			//click on selected date
			driver.findElement(By.xpath("(//div[@class=\"highlighted-date\"])[1]")).click();
			
			//click on edit option
			driver.findElement(By.xpath("(//i[@class=\"fa-regular fa-pen-to-square\"])[1]")).click();
			
			//select interview rounds
			Thread.sleep(1000);
			WebElement interviewRound = driver.findElement(By.xpath("//table[@class=\"table table-bordered\"]/tbody/tr/td[2]/select"));
			wdu.handleDropdown(interviewRound, " L1 Round");
			
			//select interview response
			Thread.sleep(1000);
			WebElement interviewResponse = driver.findElement(By.xpath("//table[@class=\"table table-bordered\"]/tbody/tr/td[3]/select"));
			wdu.handleDropdown(interviewResponse, "Hold");
			
			//click on update
			WebElement update = driver.findElement(By.xpath("//button[text()=\"Update\"]"));
			update.click();
			
			//click on find candidate
			Thread.sleep(7000);
			hp.FinCan(driver);
			System.out.println("TEST-2");
			
			//click on rejected candidate
			fc.rejectedCandidate(driver);
			
			//count the number of candidate
			List<WebElement> finalrows = driver.findElements(By.xpath("//table[@class=\"attendance-table\"]/tbody/tr"));
			//wait for the visibility of candidate
			//w.until(ExpectedConditions.visibilityOfAllElements(initalrows));
			int finalRowsCount=finalrows.size();
		    System.out.println("recruiter final row count : "+finalRowsCount);
			
		}else {
			System.out.println("No candidate are rejected");
			
			
			//click on find candidate
			hp.FinCan(driver);
			
			//scroll down to sign out
			WebElement signout=driver.findElement(By.cssSelector(".fa-solid.fa-power-off"));
			j.executeScript("arguments[0].scollintoview", signout);
			Thread.sleep(500);
			signout.click();
			
			//select yes or no
			WebElement out = driver.findElement(By.className("modal-body"));
			 WebElement yes = driver.findElement(By.xpath("//button[text()=\"Yes\"]"));
			 WebElement no = driver.findElement(By.xpath("//button[text()=\"No\"]"));
			if (out.isDisplayed()) {
				
				String currenturl = driver.getCurrentUrl();
				System.out.println(currenturl);
				//click on yes
				yes.click();
				String teamleadurl =driver.getCurrentUrl();
				System.out.println(teamleadurl);
				
				if (currenturl!=teamleadurl) {
					
					System.out.println("RECRUITER LOGOUT");
					
					//click on window back button
					driver.navigate().back();
					
					//click on team leader
					Thread.sleep(1000);
					TeamLeader tl=new TeamLeader(driver);
					tl.teamLeaderlogin(driver);
				
					//login as team leader
					String TL_USERNAME=pfu.getDataFromPropertyFile("username1");
					String TL_PASSWORD=pfu.getDataFromPropertyFile("password1");
					
					lp.login(TL_USERNAME, TL_PASSWORD);
					System.out.println("TL LOGIN");
					
					//click on Team leader section
					TeamLeaderHomePage tl_hp=new TeamLeaderHomePage(driver);
					tl_hp.TeamLeaderSection(driver);
					
					//click on update response
					TeamLeadSection tl_section=new TeamLeadSection(driver);
					tl_section.updateResponse(driver);
					
					//click on search field
					WebElement search = driver.findElement(By.className("search-input"));
					search.sendKeys("rejected");
					
					//count the number of row count
					List<WebElement> tl_initalrows = driver.findElements(By.xpath("//table[@class=\"attendance-table\"]/tbody/tr"));
					int tl_initialRowCount=tl_initalrows.size();
					System.out.println("team lead inital rows count :"+tl_initialRowCount);
					
					if (tl_initialRowCount!=0) {
						System.out.println("rejected candidate found");
						
						//select rejected candidate and update data
						WebElement update = driver.findElement(By.xpath("(//button[@class=\"TeamLead-main-table-button\"])[1]"));
						w.until(ExpectedConditions.visibilityOf(update));
						update.click();
						
						//select interview round
						Thread.sleep(1000);
						WebElement interviewRound = driver.findElement(By.xpath("//table[@class=\"min-w-full border-collapse table-auto\"]/tbody/tr/td[2]/select"));
						wdu.handleDropdown(interviewRound, "Hold");
						
						//comments for team leads
						WebElement commentsforTL = driver.findElement(By.name("commentForTl"));
						commentsforTL.sendKeys("not intrested anymore");
					
						//click on update response
						Thread.sleep(2000);
						WebElement updateRes = driver.findElement(By.xpath("//button[text()=\"Update\"]"));
						updateRes.click();
						System.out.println("data updated");
						
						//take screenshot
						Thread.sleep(1000);
						wdu.ScreenShot(driver, "CandidateUpdateToHold");
						
						//click on Team leader section
						Thread.sleep(7000);
						tl_hp.TeamLeaderSection(driver);
						
						//sign out from team leader
						//scroll down to sign out
						Thread.sleep(1000);
						WebElement tl_signout=driver.findElement(By.xpath("//span[text()=\"Logout\"]"));
						j.executeScript("arguments[0].scollintoview", tl_signout);
						Thread.sleep(500);
						tl_signout.click();
						
						//select yes or no
						Thread.sleep(500);
						WebElement tl_yes = driver.findElement(By.xpath("//button[text()=\"Yes\"]"));
						WebElement tl_out = driver.findElement(By.className("modal-body"));
						w.until(ExpectedConditions.visibilityOf(tl_out));
						tl_yes.click();
						System.out.println("TL SIGNOUT");
						
						//navigate back 
						driver.navigate().back();
						
						//click on recruiter
						r.RecruiterLogin(driver);
						Thread.sleep(2000);
						
						//login as recruiter
						lp.login(USERNAME, PASSWORD);

						Thread.sleep(2000);
						
						//click on find candidate
						hp.FinCan(driver);
						System.out.println("TEST-3");
						
						//click on rejected candidate
						fc.rejectedCandidate(driver);
						
						//count the number of candidate
						List<WebElement> finalrows = driver.findElements(By.xpath("//table[@class=\"attendance-table\"]/tbody/tr"));
						//wait for the visibility of candidate
						//w.until(ExpectedConditions.visibilityOfAllElements(initalrows));
						int finalRowsCount=finalrows.size();
					    System.out.println("recruiter inital row count : "+finalRowsCount);
						
					} else {
						System.out.println("No Data found for Rejected Candidate");
						
						//clear search field
						search.sendKeys(Keys.CONTROL+"a");
						Thread.sleep(500);
						search.sendKeys(Keys.BACK_SPACE);
						
						//select first candidate and update data
						WebElement update = driver.findElement(By.xpath("(//button[@class=\"TeamLead-main-table-button\"])[1]"));
						w.until(ExpectedConditions.visibilityOf(update));
						update.click();
						
						//select interview round
						Thread.sleep(1000);
						WebElement interviewRound = driver.findElement(By.xpath("//table[@class=\"min-w-full border-collapse table-auto\"]/tbody/tr/td[2]/select"));
						wdu.handleDropdown(interviewRound, "Rejected");
						
						//comments for team leads
						WebElement commentsforTL = driver.findElement(By.name("commentForTl"));
						commentsforTL.sendKeys("not intrested anymore");
					
						//click on update response
						Thread.sleep(2000);
						WebElement updateRes = driver.findElement(By.xpath("//button[text()=\"Update\"]"));
						updateRes.click();
						System.out.println("data updated");
						
						//take screenshot
						Thread.sleep(1000);
						wdu.ScreenShot(driver, "RejectCandidateUpdate");
						
						//click on Team leader section
						Thread.sleep(7000);
						tl_hp.TeamLeaderSection(driver);
						
						//sign out from team leader
						//scroll down to sign out
						Thread.sleep(1000);
						WebElement tl_signout=driver.findElement(By.xpath("//span[text()=\"Logout\"]"));
						j.executeScript("arguments[0].scollintoview", tl_signout);
						Thread.sleep(500);
						tl_signout.click();
						
						//select yes or no
						Thread.sleep(500);
						WebElement tl_yes = driver.findElement(By.xpath("//button[text()=\"Yes\"]"));
						WebElement tl_out = driver.findElement(By.className("modal-body"));
						w.until(ExpectedConditions.visibilityOf(tl_out));
						tl_yes.click();
						System.out.println("TL SIGNOUT");
						
						//navigate back 
						driver.navigate().back();
						
						//click on recruiter
						r.RecruiterLogin(driver);
						Thread.sleep(2000);
						
						//login as recruiter
						lp.login(USERNAME, PASSWORD);
						System.out.println("RECRUITER LOGIN");

						Thread.sleep(2000);
						
						//click on find candidate
						hp.FinCan(driver);
						System.out.println("TEST-3");
						
						//click on rejected candidate
						fc.rejectedCandidate(driver);
						
						//count the number of candidate
						List<WebElement> finalrows = driver.findElements(By.xpath("//table[@class=\"attendance-table\"]/tbody/tr"));
						//wait for the visibility of candidate
						//w.until(ExpectedConditions.visibilityOfAllElements(initalrows));
						int finalRowsCount=finalrows.size();
					    System.out.println("recruiter final row count : "+finalRowsCount);
					
						
					}
					
					
				} else {
					System.out.println("NOT ABLE TO LOGOUT");
				}
				
				
				
			}else {
				System.out.println("options not found");
			}
		}
		
	
			

		
		
		
	}

}
