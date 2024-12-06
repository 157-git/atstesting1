package recruiter;

import org.testng.Assert;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.internal.AbstractParallelWorker.Arguments;


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
public class FC_HoldCandidateTestNG extends baseClass{

	PropertyFileUtil pfu = new PropertyFileUtil();
	WebDriverUtil wdu = new WebDriverUtil();
	ExcelUtil eu = new ExcelUtil();
	JavaUtil ju = new JavaUtil();
	public WebDriver sdriver;
	
	
	@Test
	public void holdCandidateStatusUpdate() throws IOException, InterruptedException {
		
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
		fc.holdCandidate(driver);
		
		
		//count the number of candidate
		List<WebElement> initalrows = driver.findElements(By.xpath("//table[@class=\"attendance-table\"]/tbody/tr"));
		Thread.sleep(1000);
		int inititalRowsCount=initalrows.size();
		System.out.println("recruiter inital row count : "+inititalRowsCount);
		//if candidate with status HOLD found 
		if(!(initalrows.isEmpty())) {
			
			//wait for the visibility of candidate
			
			
//			//click on filter
//			driver.findElement(By.className("lineUp-Filter-btn")).click();
//			Thread.sleep(1000);
//			WebElement status = driver.findElement(By.xpath("//button[text()=\"Final Status\"]"));
//			status.click();
//			Thread.sleep(500);
//			
//			//click on hold status
//			WebElement currentType = driver.findElement(By.xpath("//label[contains(text(),\"hold\")]"));
//			currentType.click();
			
			Thread.sleep(1000);
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
			String InterviewTime=driver.findElement(By.cssSelector("div[class=\"ant-picker-input\"]")).getAttribute("value");    //updated
			
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
			cancelBtn.click();
			
			//click on short listed
			hp.home(driver);
			
			//click on filter
			Thread.sleep(1000);
			driver.findElement(By.className("lineUp-share-btn")).click();
			
			//click on status
			WebElement status = driver.findElement(By.xpath("//button[text()=\"Final Status\"]"));
			status.click();
			Thread.sleep(500);
			
			WebElement statusValue=driver.findElement(By.xpath("//label[contains(text(),\"hold\")]"));
			statusValue.click();
			
			List<WebElement> initalRows = driver.findElements(By.xpath("//table[@class=\"attendance-table\"]/tbody/tr"));
			int initalRowsCount=initalRows.size();
			System.out.println("inital rows count :"+initalRowsCount);
			
			if (initalRows.isEmpty()) {
				System.out.println("candidate data not found");
			} else {
				System.out.println("candidate data on hold found");
			}
			
		}else {
			System.out.println("No candidate are kept on Hold");
		}
		
		
		
		
		//click on find candidate
		Thread.sleep(500);
		hp.FinCan(driver);
		
		//scroll to logout
		WebElement logout = driver.findElement(By.cssSelector(".fa-solid.fa-power-off"));
		j.executeScript("arguments[0].scrollIntoView();", logout);
		Thread.sleep(500);
		//click on logout
		logout.click();
		
		WebElement out = driver.findElement(By.className("modal-body"));
		if (out.isDisplayed()) {
			//click on yes
			driver.findElement(By.xpath("//button[text()=\"Yes\"]")).click();
		}else {
			System.out.println("options not found");
		}
		
		//move backward
		Thread.sleep(500);
		driver.navigate().back();
		
		//click on team leader
		Thread.sleep(1000);
		TeamLeader tl=new TeamLeader(driver);
		tl.teamLeaderlogin(driver);
	
		//login as team leader
		String TL_USERNAME=pfu.getDataFromPropertyFile("username1");
		String TL_PASSWORD=pfu.getDataFromPropertyFile("password1");
		
		lp.login(TL_USERNAME, TL_PASSWORD);
		
		//click on Team leader section
		TeamLeaderHomePage tl_hp=new TeamLeaderHomePage(driver);
		tl_hp.TeamLeaderSection(driver);
		
		//click on update response
		TeamLeadSection tl_section=new TeamLeadSection(driver);
		tl_section.updateResponse(driver);
		
		
		//count the number of row count
		List<WebElement> tl_initalrows = driver.findElements(By.xpath("//table[@class=\"attendance-table\"]/tbody/tr"));
		int initialRowCount=0;
		
		
		
		if(!(tl_initalrows.isEmpty())) {
			
			w.until(ExpectedConditions.visibilityOfAllElements(tl_initalrows));
			initialRowCount=tl_initalrows.size();
			System.out.println("team lead inital rows count : "+initialRowCount);
			
			//click o filter button
			WebElement filter = driver.findElement(By.className("lineUp-share-btn"));
			w.until(ExpectedConditions.visibilityOf(filter));
			filter.click();
			Thread.sleep(1000);
			
			//click on status
			WebElement tl_status = driver.findElement(By.xpath("//button[text()=\"Final Status\"]"));
			tl_status.click();
			Thread.sleep(500);
			
			try {
			//click on hold status
			WebElement tl_statusValue=driver.findElement(By.xpath("//label[contains(text(),\"hold\")]"));
			System.out.println("....Candidate Displayed :"+tl_statusValue.isDisplayed());
			
			if (tl_statusValue.isDisplayed()) {
				
				System.out.println("Candidate Displayed :"+tl_statusValue.isDisplayed());
				
				//click on hold
				tl_statusValue.click();
				
				//click on edit action
				WebElement update = driver.findElement(By.xpath("//button[text()=\"Update\"]"));
				update.click();
				
				//select interview round
				Thread.sleep(1000);
				WebElement interviewRound = driver.findElement(By.xpath("//table[@class=\"min-w-full border-collapse table-auto\"]/tbody/tr/td[2]/select"));
				wdu.handleDropdown(interviewRound, "Hr Round");
				
				//comments for team leads
				WebElement commentsforTL = driver.findElement(By.name("commentForTl"));
				commentsforTL.sendKeys("looking for a job");
				
				//select date of update
//				WebElement updatedDate = driver.findElement(By.name("responseUpdatedDate"));
//				updatedDate.clear();
//				updatedDate.sendKeys("30 -11 -2024 ");

				//click on update response
				Thread.sleep(2000);
				WebElement updateRes = driver.findElement(By.xpath("//button[text()=\"Update\"]"));
				updateRes.click();
				
				//take screenshot
				Thread.sleep(1000);
				wdu.ScreenShot(driver, "holdCandidateUpdate");
				
				//data updated in team lead
				List<WebElement> tl_finalrows = driver.findElements(By.xpath("//table[@class=\"attendance-table\"]/tbody/tr"));
				w.until(ExpectedConditions.visibilityOfAllElements(tl_finalrows));
				int finalRowCount=tl_finalrows.size();
				System.out.println("team lead final rows count : "+finalRowCount);
				
				if(finalRowCount==(initialRowCount-1)) {
					System.out.println("data updated sucessfully ");
				} else {
					System.out.println("data not updated");
					Assert.fail("DATA IS SHOWING UPDATED BUT ,THE DATA IS NOT GETTING UPDATED");
				}
				
				
			} else {
				System.out.println("Candidate Displayed :"+tl_statusValue.isDisplayed());
			}
			
			} catch (NoSuchElementException e) {
			    System.out.println("........Element not found: " + e.getMessage());
			    e.printStackTrace();
			//    Assert.fail("Test failed due to NoSuchElementException: " + e.getMessage());
			} catch (TimeoutException e) {
			    System.out.println("Timeout waiting for element: " + e.getMessage());
			    e.printStackTrace();
			    Assert.fail("Test failed due to TimeoutException: " + e.getMessage());
			} catch (Exception e) {
			    // Catch any other unexpected exceptions
			    System.out.println("Unexpected error: " + e.getMessage());
			    e.printStackTrace();
			 //   Assert.fail("Test failed due to unexpected error: " + e.getMessage());
			}
			
		}else {
			System.out.println("No candidate present to be updated ");
		}
		
		
		
		
		
		
		
		//click on team leader section
		Thread.sleep(1000);
		tl_hp.TeamLeaderSection(driver);
		
		//scroll to logout
		Thread.sleep(1000);
		WebElement logoutTL = driver.findElement(By.cssSelector(".fa-solid.fa-power-off"));
		j.executeScript("arguments[0].scrollIntoView();", logoutTL);
		Thread.sleep(500);
		//click on logout
		logoutTL.click();
		
		
		//select yes or no
		Thread.sleep(1000);
		WebElement outTL = driver.findElement(By.className("modal-body"));
		if (outTL.isDisplayed()) {
			//click on yes
			driver.findElement(By.xpath("//button[text()=\"Yes\"]")).click();
		}else {
			System.out.println("options not found");
		}
		
		//move backward
		Thread.sleep(1000);
		driver.navigate().back();

		//click on recruiter
		Thread.sleep(1000);
		WebElement reclg = driver.findElement(By.xpath("(//button[@class=\"recpage-login\"])[1]"));
		w.until(ExpectedConditions.visibilityOf(reclg));
		Thread.sleep(1000);
		r.RecruiterLogin(driver);
		
		//login as recruiter
		Thread.sleep(1000);
		lp.login(USERNAME, PASSWORD);
		
		//click on find candidate
		Thread.sleep(500);
		hp.FinCan(driver);
		System.out.println("TEST-1");
		
		//click on hold candidate
		fc.holdCandidate(driver);
		
		//click on filter
		driver.findElement(By.className("lineUp-Filter-btn")).click();
		Thread.sleep(1000);
		WebElement status_1 = driver.findElement(By.xpath("//button[text()=\"Final Status\"]"));
		status_1.click();
		Thread.sleep(500);
		
		try {
		//click on hold status
			WebElement currentType_1 = driver.findElement(By.xpath("//label[contains(text(),\"hold\")]"));
			
			if (w.until(ExpectedConditions.visibilityOf(currentType_1)).isDisplayed()) {
				Thread.sleep(500);
				currentType_1.click();
			}else {
				 System.out.println("Status hold not found");
			}
		
		} catch (NoSuchElementException e) {
			 System.out.println("Element not found: " + e.getMessage());
			 e.printStackTrace();
		}
		
		
		//count the number of candidate
		List<WebElement> finalrows = driver.findElements(By.xpath("//table[@class=\"attendance-table\"]/tbody/tr"));
		//wait for the visibility of candidate
		w.until(ExpectedConditions.visibilityOfAllElements(finalrows));
		int finalRowsCount=finalrows.size();
		System.out.println("recruiter final row count : "+finalRowsCount);
		
		int result=inititalRowsCount-finalRowsCount;
		if (finalRowsCount==(initialRowCount-1)) {
			System.out.println("data updated sucessfully by : "+result);
		} else {
			System.out.println("data not updated");
			Assert.fail("DATA IS SHOWING UPDATED BUT ,THE DATA IS NOT GETTING UPDATED");
		}
		
		driver.findElement(By.className("lineUp-Filter-btn")).click();
	}
}