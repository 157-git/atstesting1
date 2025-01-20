package teamLeader;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import CommonUtil.ExcelUtil;
import CommonUtil.JavaUtil;
import CommonUtil.PropertyFileUtil;
import CommonUtil.WebDriverUtil;
import CommonUtil.baseClass_TL;
import CommonUtil.listenerImplementation;
import CommonUtil.listenerimplementation_TL;
import ObjectRepository_POM.FindCandidate;
import ObjectRepository_POM.RecruiterGear;
import ObjectRepository_POM.RecruiterhomePage;
import ObjectRepository_POM.TeamLeader;
import ObjectRepository_POM.TeamLeaderHomePage;
import ObjectRepository_POM.loginPage;

@Listeners(listenerimplementation_TL.class)
public class FC_CallingTrackerTestNG extends baseClass_TL {
	
	WebDriverUtil wdu=new WebDriverUtil();
	PropertyFileUtil pfu=new PropertyFileUtil();
	ExcelUtil eu=new ExcelUtil();
	JavaUtil ju = new JavaUtil();
	JavascriptExecutor js = (JavascriptExecutor) driver;
	public WebDriver sdriver;
 //  public Connection connection;

//..............................INSERT...............................
	@Test(enabled = false)
	public void findCandidate() throws IOException, InterruptedException {
		// TODO Auto-generated constructor stub
		String USERNAME=pfu.getDataFromPropertyFile("username1");
		String PASSWORD=pfu.getDataFromPropertyFile("password1");
		String URL="http://rg.157careers.in/Dashboard/432/TeamLeader";
		
		//updated;3-1-25
		Thread.sleep(2000);
		TeamLeader tl=new TeamLeader(driver);
		tl.teamLeaderPage(driver);//
		
		Thread.sleep(2000);
		String LoginPageUrl=driver.getCurrentUrl();
		System.out.println(LoginPageUrl);
		
		//login
		loginPage lp = new loginPage(driver);
		lp.login(USERNAME, PASSWORD);

		//6-12-24 updated
		Thread.sleep(2000);
		String teamleadPageUrl=driver.getCurrentUrl();
		System.out.println(teamleadPageUrl);
		
				
		if (teamleadPageUrl.equals(LoginPageUrl)) {
			System.out.println("login failed");
			WebElement error = driver.findElement(By.className("loginpage-error"));
			if (error.isDisplayed()) {
				System.out.println(error.getText());
			}
			//Assert.fail("Invalid login details");
		} else if(teamleadPageUrl.equals(URL)) {
			System.out.println("login successfull");
						
			TeamLeaderHomePage hp=new TeamLeaderHomePage(driver);
			hp.callingTracker(driver);
			System.out.println("TEST");
			
			FindCandidate fc=new FindCandidate(driver);
			fc.callTrac(driver);
			
			
			//count the number of candidate
			List<WebElement> initalrows = driver.findElements(By.xpath("//table[@class=\"attendance-table\"]/tbody/tr"));
			Thread.sleep(1000);
			int inititalRowsCount=initalrows.size();
			System.out.println("team lead inital row count : "+inititalRowsCount);
			
			if(!(initalrows.isEmpty())) {
				
				String id = driver.findElement(By.xpath("//table[@class=\"attendance-table\"]/tbody/tr[1]/td[2]")).getText();
				int ID=Integer.parseInt(id);
				System.out.println(ID);
				
				//click on edit action
				fc.actionBtn(driver);
				
				//fetch the data from the front end form in calling tracker excel sheet
				String candidateName = driver.findElement(By.name("candidateName")).getAttribute("value");			
				String candidateEmail=driver.findElement(By.name("candidateEmail")).getAttribute("value");
				String contactNumber=driver.findElement(By.name("contactNumber")).getAttribute("value");
				String whatsupNumber = driver.findElement(By.name("alternateNumber")).getAttribute("value");
				String source = driver.findElement(By.name("sourceName")).getAttribute("value");
				String jobId = driver.findElement(By.name("requirementId")).getAttribute("value");				
				String company=driver.findElement(By.id("requirementCompany")).getAttribute("value");
				String callingFeedback = driver.findElement(By.name("callingFeedback")).getAttribute("value");				
				String dob = driver.findElement(By.name("lineUp.dateOfBirth")).getAttribute("value");
				WebElement gender = driver.findElement(By.name("lineUp.gender"));
				String g="";
				if(gender.isSelected()) {
					g=gender.getAttribute("value");
					
				}else {
					System.out.println("Gender data not available");
				}	
				String callSummary=driver.findElement(By.name("lineUp.feedBack")).getAttribute("value");	
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
		
			
				//.......................................................................................
				Connection connection=BS();
				if (connection == null) {
		            System.out.println("Database connection is null, cannot insert data.");
		            return;
		        }
				
				String query = "INSERT INTO callingtracker (ID,candidateName, candidateEmail,contactNumber,whatsupNumber,source,"
						+ "		jobId,company,callingFeedback,dob,gender,callSummary,education,passout,certification,Currentcompany,"
						+ "TotalExp,releventExp,noticePeriod,commRating,CurrentCTC,ExpectedCTC,offerLetter,OfferLetterMsg,"
						+ "messageForTL,statusType,finalStatus,Interviewdate,InterviewTime) "
						+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				
				try {
					PreparedStatement preparedStatement = connection.prepareStatement(query);
					preparedStatement.setInt(1, ID);
		            preparedStatement.setString(2, candidateName);
		            preparedStatement.setString(3, candidateEmail);
		            preparedStatement.setString(4, contactNumber);
		            preparedStatement.setString(5, whatsupNumber);
		            preparedStatement.setString(6, source);
		            preparedStatement.setString(7, jobId);
		            preparedStatement.setString(8, company);
		            preparedStatement.setString(9, callingFeedback);
		            preparedStatement.setString(10, dob);
		            preparedStatement.setString(11, g);
		            preparedStatement.setString(12, callSummary);
		            preparedStatement.setString(13, education);
		            preparedStatement.setString(14, passout);
		            preparedStatement.setString(15, certification);
		            preparedStatement.setString(16, Currentcompany);
		            preparedStatement.setString(17, TotalExpYear+"year"+TotalExpMonth+"months");
		            preparedStatement.setString(18, releventExp);
		            preparedStatement.setString(19, noticePeriod);
		            preparedStatement.setString(20, commRating);
		            preparedStatement.setString(21, CurrentCTCYear+"year"+CurrentCTCMonth+"months");
		            preparedStatement.setString(22, expectedCTCYear+"year"+expectedCTCMonth+"months");
		            preparedStatement.setString(23, offerLetter);
		            preparedStatement.setString(24, OfferLetterMsg);
		            preparedStatement.setString(25, messageForTL);
		            preparedStatement.setString(26, statusType);
		            preparedStatement.setString(27, finalStatus);
		            preparedStatement.setString(28, Interviewdate);
		            preparedStatement.setString(29, InterviewTime);
		            
		            
		            int rowsAffected = preparedStatement.executeUpdate();
		            
		            if (rowsAffected > 0) {
		                System.out.println("Data successfully inserted into the database.");
		            } else {
		                System.out.println("Failed to insert data.");
		            }

		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
				
				//.........................................................................................
			
				
				Thread.sleep(1000);
				WebElement cancel_btn = driver.findElement(By.xpath("//button[text()=\"Cancel\"]"));
				JavascriptExecutor js = (JavascriptExecutor) driver;
//				js.executeScript("arguments[0].scrollIntoView();",cancel_btn );
				js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
				Thread.sleep(1000);
				cancel_btn.click();
				
				
			}else {
				System.out.println("No data present in Calling Tracker");
			}
		}

	}
	
	//..............................UPDATE................................................
	
	@Test(enabled = true)
	public void findCandidate2() throws IOException, InterruptedException {
		// TODO Auto-generated constructor stub
		String USERNAME=pfu.getDataFromPropertyFile("username1");
		String PASSWORD=pfu.getDataFromPropertyFile("password1");
String URL="http://93.127.199.85/Dashboard/432/TeamLeader";
		
		//updated;3-1-25
		Thread.sleep(2000);
		TeamLeader tl=new TeamLeader(driver);
		tl.teamLeaderPage(driver);//
		
		Thread.sleep(2000);
		String LoginPageUrl=driver.getCurrentUrl();
		System.out.println(LoginPageUrl);
		
		//login
		loginPage lp = new loginPage(driver);
		lp.login(USERNAME, PASSWORD);

		//6-12-24 updated
		Thread.sleep(2000);
		String teamleadPageUrl=driver.getCurrentUrl();
		System.out.println(teamleadPageUrl);
		
				
		if (teamleadPageUrl.equals(LoginPageUrl)) {
			System.out.println("login failed");
			//Assert.fail("Invalid login details");
		} else if(teamleadPageUrl.equals(URL)) {
			System.out.println("login successfull");		
						
			TeamLeaderHomePage hp=new TeamLeaderHomePage(driver);
			hp.callingTracker(driver);
			System.out.println("TEST");
			
			FindCandidate fc=new FindCandidate(driver);
			fc.callTrac(driver);
			
			
			//count the number of candidate
			List<WebElement> initalrows = driver.findElements(By.xpath("//table[@class=\"attendance-table\"]/tbody/tr"));
			Thread.sleep(1000);
			int inititalRowsCount=initalrows.size();
			System.out.println("recruiter inital row count : "+inititalRowsCount);
			
			if(!(initalrows.isEmpty())) {
				
				String id = driver.findElement(By.xpath("//table[@class=\"attendance-table\"]/tbody/tr[1]/td[2]")).getText();
				int ID=Integer.parseInt(id);
				System.out.println(ID);
				
		
	
				//..................get the data from database..................
				
				Connection connection=BS();
				if (connection == null) {
		            System.out.println("Database connection is null, cannot fetch  data.");
		            return;
		        }
				
				String query = "SELECT * FROM callingtracker where ID=?";
				
				String db_candidateName= null;
				String db_candidateEmail= null;
				String db_contactNumber= null;
				String db_whatsupNumber= null;
				String db_source= null;
				String db_jobId= null;
				String db_company= null;
				String db_callingFeedback= null;
				String db_dob= null;
				String db_gender= null;
				String db_callSummary= null;
				String db_education= null;
				String db_passout= null;
				String db_certification= null;
				String db_currentCompany= null;
				String db_totalExp= null;
				String db_relevantExp= null;
				String db_noticePeriod= null;
				String db_commRating= null;
				String db_currentCTC= null;
				String db_expectedCTC= null;
				String db_offerLetter= null;
				String db_offerLetterMsg= null;
				String db_messageForTL= null;
				String db_statusType= null;
				String db_finalStatus= null;
				String db_interviewDate= null;
				String db_interviewTime= null;
				
				
				try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
		            preparedStatement.setInt(1, ID);  
		            
		            // Execute the query and get the result set
		            ResultSet resultSet = preparedStatement.executeQuery();
		            
		            
		            // Process the result set
		            if (resultSet.next()) {
		                // Fetch the data from the ResultSet
		            	db_candidateName = resultSet.getString("candidateName");
		                db_candidateEmail = resultSet.getString("candidateEmail");
		                db_contactNumber = resultSet.getString("contactNumber");
		                db_whatsupNumber = resultSet.getString("whatsupNumber");
		                db_source = resultSet.getString("source");
		                db_jobId = resultSet.getString("jobId");
		                db_company = resultSet.getString("company");
		                db_callingFeedback = resultSet.getString("callingFeedback");
		                db_dob = resultSet.getString("dob");
		                db_gender = resultSet.getString("gender");
		                db_callSummary = resultSet.getString("callSummary");
		                db_education = resultSet.getString("education");
		                db_passout = resultSet.getString("passout");
		                db_certification = resultSet.getString("certification");
		                db_currentCompany = resultSet.getString("Currentcompany");
		                db_totalExp = resultSet.getString("TotalExp");
		                db_relevantExp = resultSet.getString("releventExp");
		                db_noticePeriod = resultSet.getString("noticePeriod");
		                db_commRating = resultSet.getString("commRating");
		                db_currentCTC = resultSet.getString("CurrentCTC");
		                db_expectedCTC = resultSet.getString("ExpectedCTC");
		                db_offerLetter = resultSet.getString("offerLetter");
		                db_offerLetterMsg = resultSet.getString("OfferLetterMsg");
		                db_messageForTL = resultSet.getString("messageForTL");
		                db_statusType = resultSet.getString("statusType");
		                db_finalStatus = resultSet.getString("finalStatus");
		                db_interviewDate = resultSet.getString("Interviewdate");
		                db_interviewTime = resultSet.getString("InterviewTime");

		               

		            } else {
		                System.out.println("No candidate found with ID: " + ID);
		            }
		            
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
				
	
	//.............................get the data from front end...........................
				
				//click on edit action
				fc.actionBtn(driver);
				
				//fetch the data from the front end form in calling tracker excel sheet
				String candidateName = driver.findElement(By.name("candidateName")).getAttribute("value");			
				String candidateEmail=driver.findElement(By.name("candidateEmail")).getAttribute("value");
				String contactNumber=driver.findElement(By.name("contactNumber")).getAttribute("value");
				String whatsupNumber = driver.findElement(By.name("alternateNumber")).getAttribute("value");
				String source = driver.findElement(By.name("sourceName")).getAttribute("value");
				String jobId = driver.findElement(By.name("requirementId")).getAttribute("value");				
				String company=driver.findElement(By.id("requirementCompany")).getAttribute("value");
				String callingFeedback = driver.findElement(By.name("callingFeedback")).getAttribute("value");				
				String dob = driver.findElement(By.name("lineUp.dateOfBirth")).getAttribute("value");
				WebElement gender = driver.findElement(By.name("lineUp.gender"));
				String g=null;
				if(gender.isSelected()) {
					g=gender.getAttribute("value");
					
				}else {
					System.out.println("Gender data not available");
				}	
				String callSummary=driver.findElement(By.name("lineUp.feedBack")).getAttribute("value");	
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
				
	
	
				// Print out the fetched data
                System.out.println("Candidate Name - " + db_candidateName+" : "+candidateName);
                System.out.println("Candidate Email - " + db_candidateEmail+" : "+candidateEmail);
                System.out.println("Contact Number - " + db_contactNumber+" : "+contactNumber);
                System.out.println("Job ID - " + db_jobId+" : "+jobId);
                System.out.println("Company - " + db_company+" : "+company);
                
                if (db_candidateName.equals(candidateName) && db_candidateEmail.equals(candidateEmail) && 
                	db_contactNumber.equals(contactNumber) && db_whatsupNumber.equals(whatsupNumber) &&
                	db_source.equals(source) && db_jobId.equals(jobId) && db_gender.equals(gender) &&
                	db_callSummary.equals(callSummary) && db_education.equals(education)) {
					System.out.println("DATA INTRIGRATION TESTING SUCCESSFULL");
				} else {
					System.out.println("DATA INTRIGRATION TESTING IS NOT SUCCESSFULL");
				}
	
	
                
	
	
	
	
	
	
	
	
	
	
	
	
	
	
			}
		}
	
	}
}
