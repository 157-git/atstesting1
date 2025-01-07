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
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


import CommonUtil.ExcelUtil;
import CommonUtil.JavaUtil;
import CommonUtil.PropertyFileUtil;
import CommonUtil.WebDriverUtil;
import CommonUtil.baseClass_TL;
import CommonUtil.listenerimplementation_TL;
import ObjectRepository_POM.FindCandidate;
import ObjectRepository_POM.Manager;
import ObjectRepository_POM.RecruiterGear;
import ObjectRepository_POM.RecruiterhomePage;
import ObjectRepository_POM.Superuser;
import ObjectRepository_POM.TeamLeader;
import ObjectRepository_POM.TeamLeaderHomePage;
import ObjectRepository_POM.loginPage;
import ObjectRepository_POM.logoutPage;

@Listeners(listenerimplementation_TL.class)
public class notificationTestNG extends baseClass_TL{

	WebDriverUtil wdu=new WebDriverUtil();
	PropertyFileUtil pfu=new PropertyFileUtil();
	ExcelUtil eu=new ExcelUtil();
	JavaUtil ju = new JavaUtil();
	
	public WebDriver sdriver;
	
	@Test(enabled = false)
	public void findCandidate() throws IOException, InterruptedException {
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
			hp.notification(driver);
			System.out.println("TEST");
			
			List<WebElement> msg = driver.findElements(By.className("motificationSubCont1"));
			int MsgCount = msg.size();
			System.out.println("number of Notification : "+MsgCount);
			
			
		}
	}
	
	//.................................team leader.........................................
	@Test
	public void notificationFlow() throws IOException, InterruptedException {
		// TODO Auto-generated constructor stub
	String USERNAME=pfu.getDataFromPropertyFile("not_username");
	String PASSWORD=pfu.getDataFromPropertyFile("not_password");
	String URL="http://93.127.199.85/Dashboard/19/Recruiters";
	
	RecruiterGear r = new RecruiterGear(driver);
	r.RecruiterPage(driver);
	
	Thread.sleep(2000);
	String LoginPageUrl=driver.getCurrentUrl();
	System.out.println(LoginPageUrl);
	

	loginPage lp = new loginPage(driver);
	lp.login(USERNAME, PASSWORD);
	
	Thread.sleep(2000);
	String RecPageUrl=driver.getCurrentUrl();
	System.out.println(RecPageUrl);
	
	if (RecPageUrl.equals(LoginPageUrl)) {
		System.out.println("login failed");
	} else if(RecPageUrl.equals(URL)){
		System.out.println("LOGIN successfull");
		
		RecruiterhomePage hp = new RecruiterhomePage(driver);
		hp.FinCan(driver);
		System.out.println("TEST");
		
		FindCandidate fc=new FindCandidate(driver);
		fc.lineUp(driver);
		
		
		//count the number of candidate
		List<WebElement> initalrows = driver.findElements(By.xpath("//table[@class=\"attendance-table\"]/tbody/tr"));
		Thread.sleep(1000);
		int inititalRowsCount=initalrows.size();
		System.out.println("recruiter inital row count : "+inititalRowsCount);
		
		if(!(initalrows.isEmpty())) {
			
			String id = driver.findElement(By.xpath("//table[@class=\"attendance-table\"]/tbody/tr[1]/td[2]")).getText();
			int ID=Integer.parseInt(id);
			System.out.println(ID);
			
			//click on edit action
			fc.actionBtn(driver);
			
			//.................fetch the data from the front end form in line up....................
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
	
			Connection connection=BS();
			if (connection == null) {
            System.out.println("Database connection is null, cannot insert data.");
	            return;
	        }
			
			String query = "INSERT INTO lineup (ID,candidateName, candidateEmail,contactNumber,whatsupNumber,source,"
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
			//...........................update the data in data base.........................
			
//			WebElement UpdateFinalStatus = driver.findElement(By.xpath("(//div[@class=\"update-calling-tracker-two-input-container\"])[8]/div/select"));
//			wdu.handleDropdown(UpdateFinalStatus, "Interview Schedule");
			
			String update = "UPDATE lineup SET finalStatus=? WHERE ID=?";

			String FINALSTATUS = null;
			try (PreparedStatement preparedStatement = connection.prepareStatement(update)) {
			    
			    preparedStatement.setString(1, "Interview Schedule");
			    preparedStatement.setInt(2, ID); 

			    // Execute the update query
			    int rowsUpdated = preparedStatement.executeUpdate();

			    if (rowsUpdated != 0) {
			       //fetch the data
			        String selectQuery = "SELECT finalStatus FROM lineup WHERE ID=?";
			        
			        try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {
			            selectStatement.setInt(1, ID); // Set the ID to fetch the update

			            try (ResultSet resultSet = selectStatement.executeQuery()) {
			                if (resultSet.next()) {
			                    FINALSTATUS = resultSet.getString("finalStatus");
			                    System.out.println("Updated finalStatus: " + FINALSTATUS);
			                }
			            }
			        }
			    } else {
			        System.out.println("No candidate found with ID: " + ID);
			    }
			} catch (SQLException e) {
			    e.printStackTrace();
			}

			//.................................................................................
			
			System.out.println("status type is :"+statusType);
			System.out.println("candidate Name :"+candidateName);
			Thread.sleep(2000);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
			
				WebElement final_Status=driver.findElement(By.xpath("(//div[@class=\"update-calling-tracker-two-input-container\"])[8]/div/select"));
				
				if (FINALSTATUS.equals("Interview Schedule")) {
					//final_Status.click();
					wdu.handleDropdown(final_Status, FINALSTATUS);
					System.out.println("candidate shorlisted");
				} else {
					//final_Status.click();
					wdu.handleDropdown(final_Status, FINALSTATUS);
					System.out.println("candidate selected");
				}

				
				WebElement update_data = driver.findElement(By.xpath("//button[text()=\"Update Data\"]"));
				Thread.sleep(1000);
				update_data.click();
				String dnt = ju.dateAndTime();
				System.out.println(dnt);
				Thread.sleep(500);
				wdu.ScreenShot(driver, "lineup");
				
				//logout from recruiter
				Thread.sleep(1000);
				logoutPage lo=new logoutPage(driver);
				lo.logout(driver, "Yes");
				
				Thread.sleep(2000);
				driver.navigate().back();
				Thread.sleep(2000);
				
				//.....................login as team leader to check notification.........................
				String USERNAME_tl=pfu.getDataFromPropertyFile("not_usernameTL");
				String PASSWORD_tl=pfu.getDataFromPropertyFile("not_passwordTL");				
				String URL_tl="http://93.127.199.85/Dashboard/977/TeamLeader";
				
				
				Thread.sleep(2000);
				TeamLeader tl=new TeamLeader(driver);
				tl.teamLeaderlogin(driver);
				
				Thread.sleep(2000);
				String LoginPageUrl_tl=driver.getCurrentUrl();
				System.out.println(LoginPageUrl);
				
				//login
				loginPage lp_tl = new loginPage(driver);
				lp_tl.login(USERNAME_tl, PASSWORD_tl);

				
				Thread.sleep(2000);
				String teamleadPageUrl_tl=driver.getCurrentUrl();
				System.out.println(teamleadPageUrl_tl);
				
						
				if (teamleadPageUrl_tl.equals(LoginPageUrl_tl)) {

					WebElement error_msg = driver.findElement(By.className("loginpage-error"));
					Assert.assertTrue(error_msg.isDisplayed(), "error msg not displayed");
					System.out.println("Login failed: " + error_msg.getText());
					
				} else if(teamleadPageUrl_tl.equals(URL_tl)) {
					System.out.println("login successfull");
					
					Thread.sleep(1000);
					WebElement notificationIcon = driver.findElement(By.cssSelector(".ant-badge.css-1kf000u"));
					notificationIcon.click();
					//System.out.println("No notification found in TEAM LEADER");
					
					//put the fixed text value present in every notification(recruiter Name) or id
					List<WebElement> notification = driver.findElements(By.xpath("//div[@class=\"motificationSubCont1\"]/p"));
					for (WebElement noti : notification) {
						
						if (noti.getText().contains(candidateName) && !noti.getText().isEmpty()) {
							System.out.println("notification PRESENT");
							Thread.sleep(2000);
							wdu.ScreenShot(driver, "managerNotification");
						} else {
							System.out.println("notification ABSENT");
						}	
						
					}	
					
					//logout
					Thread.sleep(2000);
					lo.logout(driver, "Yes");
				}
								
				
				Thread.sleep(2000);
				driver.navigate().back();
				System.out.println("back-1");
				Thread.sleep(2000);
				driver.navigate().back();
				System.out.println("back-2");
				Thread.sleep(2000);
				
				//..............................login as manager........................................
				String USERNAME_m=pfu.getDataFromPropertyFile("not_usernameM");
				String PASSWORD_m=pfu.getDataFromPropertyFile("not_passwordM");				
				String URL_m="http://93.127.199.85/Dashboard/1342/Manager";
				
				Thread.sleep(2000);
				System.out.println("on manager page");
				Manager manager=new Manager(driver);
				manager.managerLogin(driver);
				
				Thread.sleep(2000);
				String loginPageUrl_M = driver.getCurrentUrl();
				System.out.println(loginPageUrl_M);
				
				//login
				Thread.sleep(2000);
				loginPage lp_m = new loginPage(driver);
				lp_m.login(USERNAME_m, PASSWORD_m);
				
				Thread.sleep(2000);
				String homePageUrl_M = driver.getCurrentUrl();
				System.out.println(homePageUrl_M);
				
				if (homePageUrl_M.equals(loginPageUrl_M)) {
					
					WebElement error_msg = driver.findElement(By.className("loginpage-error"));
					Assert.assertTrue(error_msg.isDisplayed(), "error msg not displayed");
					System.out.println("Login failed: " + error_msg.getText());
					
				} else if(homePageUrl_M.equals(URL_m)) {
					System.out.println("login successfull");
					
					Thread.sleep(2000);
					WebElement notificationIcon = driver.findElement(By.cssSelector(".ant-badge.css-1kf000u"));
					notificationIcon.click();
					
					//put the fixed text value present in every notification(recruiter Name) or id
					Thread.sleep(2000);
					List<WebElement> notification = driver.findElements(By.xpath("//div[@class=\"motificationSubCont1\"]/p"));
					for (WebElement noti : notification) {
						
					if (noti.getText().contains(candidateName) && !noti.getText().isEmpty()) {
						System.out.println("notification PRESENT");
						Thread.sleep(2000);
						wdu.ScreenShot(driver, "managerNotification");
					} else {
						System.out.println("notification ABSENT");
					}	
				}
					
					//logout
					Thread.sleep(1000);
					lo.logout(driver, "Yes");
				}
				
				Thread.sleep(2000);
				driver.navigate().back();
				System.out.println("back-3");
				Thread.sleep(2000);
				driver.navigate().back();
				System.out.println("back-4");
				Thread.sleep(2000);
				
				//...........................login as super user.............................
				String USERNAME_su=pfu.getDataFromPropertyFile("not_usernameSU");
				String PASSWORD_su=pfu.getDataFromPropertyFile("not_passwordSU");				
				String URL_su="http://93.127.199.85/Dashboard/391/SuperUser";
				
				Thread.sleep(2000);
				Superuser superuser=new Superuser(driver);
				superuser.superuserLogin(driver);
				
				Thread.sleep(2000);
				String loginPageUrl_su = driver.getCurrentUrl();
				System.out.println(loginPageUrl_su);
				
				//login
				Thread.sleep(2000);
				loginPage lp_su = new loginPage(driver);
				lp_su.login(USERNAME_su, PASSWORD_su);
				
				Thread.sleep(2000);
				String homePageUrl_su = driver.getCurrentUrl();
				System.out.println(homePageUrl_su);
				
				if (homePageUrl_su.equals(loginPageUrl_su)) {
					
					WebElement error_msg = driver.findElement(By.className("loginpage-error"));
					Assert.assertTrue(error_msg.isDisplayed(), "error msg not displayed");
					System.out.println("Login failed: " + error_msg.getText());
					
				} else if(homePageUrl_su.equals(URL_su)) {
					System.out.println("login successfull");
				
					Thread.sleep(2000);
					WebElement notificationIcon = driver.findElement(By.cssSelector(".ant-badge.css-1kf000u"));
					notificationIcon.click();
					
					//put the fixed text value present in every notification(recruiter Name) or id
					Thread.sleep(2000);
					List<WebElement> notification = driver.findElements(By.xpath("//div[@class=\"motificationSubCont1\"]/p"));
					for (WebElement noti : notification) {
						
					if (noti.getText().contains(candidateName) && !noti.getText().isEmpty()) {
						System.out.println("notification PRESENT");
						Thread.sleep(2000);
						wdu.ScreenShot(driver, "superuserNotification");
					} else {
						System.out.println("notification ABSENT");
					}	
				}
					
					//logout
					Thread.sleep(1000);
					lo.logout(driver, "Yes");
					
					
				}	
					
		}else {
			System.out.println("No candidate present to be updated ");
			
			//logout
			Thread.sleep(1000);
			logoutPage lo=new logoutPage(driver);
			lo.logout(driver, "Yes");
		}
		
		
		
	}
	
	
}
	
	
	
	
	
}
