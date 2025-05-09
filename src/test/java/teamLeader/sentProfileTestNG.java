package teamLeader;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

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
import CommonUtil.baseClass_TL;
import CommonUtil.listenerimplementation_TL;
import ObjectRepository_POM.TeamLeader;
import ObjectRepository_POM.TeamLeaderHomePage;
import ObjectRepository_POM.loginPage;
import ObjectRepository_POM.logoutPage;

@Listeners(listenerimplementation_TL.class)
public class sentProfileTestNG extends baseClass_TL{

	WebDriverUtil wdu=new WebDriverUtil();
	PropertyFileUtil pfu=new PropertyFileUtil();
	ExcelUtil eu=new ExcelUtil();
	JavaUtil ju = new JavaUtil();

	public WebDriver sdriver;
	
	@Test(enabled = false)
	public void sentProfile() throws IOException, InterruptedException {
		
		// TODO Auto-generated constructor stub
				String USERNAME=pfu.getDataFromPropertyFile("username1");
				String PASSWORD=pfu.getDataFromPropertyFile("password1");
				String URL=pfu.getDataFromPropertyFile("tl_url");
				SoftAssert softAssert = new SoftAssert();
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
				
				//updated;3-1-25
				Thread.sleep(2000);
				TeamLeader tl=new TeamLeader(driver);
				tl.teamLeaderPage(driver);
				
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
					wait.until(ExpectedConditions.visibilityOf(hp.getFindCandidate()));
					hp.sentProfile(driver);
					
					WebElement rows = driver.findElement(By.xpath("//table[@class=\"attendance-table\"]/tbody/tr[2]"));
					if (rows.isDisplayed()) {
						System.out.println("rows present to share");
						
						WebElement profleStatus_beforeUpdate = driver.findElement(By.xpath("//table[@class=\"attendance-table\"]/tbody/tr[2]/td[37]/button"));
						String status_beforeUpdate = profleStatus_beforeUpdate.getText();
						
						//status_beforeUpdate=status_beforeUpdate.toUpperCase();
						System.out.println("STATUS BEFORE PROFILE SENT :"+status_beforeUpdate);
						
						//click on share button
						WebElement share = driver.findElement(By.className("SCE-share-btn"));
						share.click();
						
						//click on selectAll check box
						Thread.sleep(1000);
//						WebElement selectAll = driver.findElement(By.name("selectAll"));
//						selectAll.click();
							
						//select 1st candidate	
						WebElement row1 = driver.findElement(By.xpath("//table[@class=\"attendance-table\"]/tbody/tr[1]/td[1]/input"));
						row1.click();	
						
						//click on send button
						Thread.sleep(1000);
						WebElement send = driver.findElement(By.className("SCE-forward-btn"));
						send.click();
						Thread.sleep(1000);
						
						//wait till the email body is display 
						 WebDriverWait w=new WebDriverWait(driver, Duration.ofSeconds(30));
						WebElement emailBody = driver.findElement(By.className("modal-content"));
						w.until(ExpectedConditions.visibilityOf(emailBody));
						if (emailBody.isDisplayed()) {
							
							
							//enter email)
							WebElement email = driver.findElement(By.xpath("(//input[@class=\"text-secondary form-control\"])[1]"));
							email.sendKeys("ameetsingh2000@gmail.com");
							Thread.sleep(1000);
							
							//enter carbon copy 
							WebElement cc = driver.findElement(By.xpath("(//input[@class=\"text-secondary form-control\"])[2]"));
							cc.sendKeys("");
							Thread.sleep(1000);
							
							//enter BCC
							WebElement bcc = driver.findElement(By.xpath("//input[@class=\"form-control\"]"));
							bcc.sendKeys("");
							Thread.sleep(1000);
							
							//enter subject 
							WebElement subject = driver.findElement(By.xpath("(//input[@class=\"text-secondary form-control\"])[3]"));
							subject.sendKeys("CANDIDATE DETAILS");
							Thread.sleep(1000);
							
							List<WebElement> list = driver.findElements(By.xpath("//table[@class=\"mt-4 text-secondary table table-sm table-striped table-bordered table-hover\"]/tbody/tr/td[4]"));
							int noOfCandidate=list.size();
							System.out.println("number of candidate :"+noOfCandidate);
							for (WebElement number : list) {
								System.out.println("CANDIDATE NAME :"+number.getText());
							}
							
							
							//scroll down to send email
							JavascriptExecutor js = (JavascriptExecutor) driver;
							js.executeScript("window.scrollTo(0,document.body.scrollHeight);");
							driver.findElement(By.xpath("//button[text()=\"Send Email\"]")).click();
//							w.until(ExpectedConditions.visibilityOfElementLocated(By.className("Toastify")));
//							
//							WebElement tostify = driver.findElement(By.xpath("//div[text()=\"Failed to send email\"]"));
//							if (tostify.isDisplayed()) {
//								System.out.println("unabled to send data");
//							} else {
//								System.out.println("data send successfully");
//							}
							
							WebElement toastmsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"Toastify__toast-body\"]/div[2]")));
			        		String msg = toastmsg.getText();
			        		System.out.println(msg);
			        		Thread.sleep(1000);
			                msg = msg.toLowerCase();

			                if(msg.contains("Failed to send email")) {
			                    wdu.ScreenShot(driver, "sent_profile_team");
			                    softAssert.assertTrue(false, "ERROR: FAILED TO SEND EMAIL");
			                    //softAssert.assertEquals(msg, "Failed to send email", "ERROR: FAILED TO SEND EMAIL");
			                   // Assert.fail("ERROR: Failed to send email");
			                } else if (msg.contains("Profile(s) Shared successfully")) {
			                    System.out.println("PROFILE SHARED SUCCESSFULLY");
			                    wdu.ScreenShot(driver, "sent_profile_team"); // Optional: Capture success
			                } else {
			                    wdu.ScreenShot(driver, "sent_profile_team");
			                    softAssert.assertTrue(false, "ERROR: All profile(s) Already Shared");
			                   // softAssert.assertEquals(msg, "all "+noOfCandidate+" profile(s) already shared", "ERROR: All profile(s) Already Shared");
			                   // Assert.fail("ERROR: All profile(s) Already Shared");
			                }
			               

						} else {
							System.out.println("select atleast one row");
						}
						
						
						
						WebElement profleStatus_afterUpdate = driver.findElement(By.xpath("//table[@class=\"attendance-table\"]/tbody/tr[2]/td[37]/button"));
						String status_afterUpdate = profleStatus_afterUpdate.getText();
						
						//status_afterUpdate=status_afterUpdate.toUpperCase();
						System.out.println("STATUS AFTER PROFILE SENT :"+status_afterUpdate);
						
						if (status_afterUpdate.equals("Profile Sent")) {
							System.out.println("PROFILE SENT");
						} else {
							System.out.println("PROFILE NOT SENT");
						}
						
					} else {
						System.out.println("rows not present");
					}
					
					//click on team leader section
					Thread.sleep(1000);
					hp.getTeamLeaderSection().click();
					
					//logout
					Thread.sleep(1000);
					logoutPage lo=new logoutPage(driver);
					lo.logout(driver, "Yes");
					
					softAssert.assertAll("ASSERTION OCCURED");
				}
	}
	
	@Test(enabled = true)
	public void sentMultipleProfile() throws IOException, InterruptedException {
		
				// TODO Auto-generated constructor stub
				String USERNAME=pfu.getDataFromPropertyFile("username1");
				String PASSWORD=pfu.getDataFromPropertyFile("password1");
				String URL=pfu.getDataFromPropertyFile("tl_url");
				SoftAssert softAssert = new SoftAssert();
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
				
				//updated;3-1-25
				Thread.sleep(2000);
				TeamLeader tl=new TeamLeader(driver);
				tl.teamLeaderPage(driver);
				
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
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class=\"loader-container\"]")));
					wait.until(ExpectedConditions.visibilityOf(hp.getFindCandidate()));
					hp.sentProfile(driver);
					
					WebElement rows = driver.findElement(By.xpath("//table[@class=\"attendance-table\"]/tbody/tr[2]"));
					if (rows.isDisplayed()) {
						System.out.println("rows present to share");
						
						List<WebElement> profleStatus_beforeUpdate = driver.findElements(By.xpath("//table[@class=\"attendance-table\"]/tbody/tr/td[37]/button"));
						int status_beforeUpdate = profleStatus_beforeUpdate.size();
						System.out.println(status_beforeUpdate);
						
						int count = 1;
						for (WebElement status : profleStatus_beforeUpdate) {
							System.out.println("STATUS BEFORE PROFILE SENT :"+count+" :"+status.getText());// Print the text of each element
							count++;
						}
						
						//status_beforeUpdate=status_beforeUpdate.toUpperCase();
						
						
						//click on share button
						WebElement share = driver.findElement(By.className("SCE-share-btn"));
						share.click();
						
						//click on selectAll check box
						Thread.sleep(1000);
						WebElement selectAll = driver.findElement(By.name("selectAll"));
						selectAll.click();	
						
						//click on send button
						Thread.sleep(1000);
						WebElement send = driver.findElement(By.className("SCE-forward-btn"));
						send.click();
						Thread.sleep(1000);
						
						//wait till the email body is display 
//						 WebDriverWait w=new WebDriverWait(driver, Duration.ofSeconds(30));
						WebElement emailBody = driver.findElement(By.className("modal-content"));
						wait.until(ExpectedConditions.visibilityOf(emailBody));
						if (emailBody.isDisplayed()) {
							
							
							//enter email)
							WebElement email = driver.findElement(By.xpath("(//input[@class=\"text-secondary form-control\"])[1]"));
							email.sendKeys("ameetsingh2000@gmail.com");
							Thread.sleep(1000);
							
							//enter carbon copy 
							WebElement cc = driver.findElement(By.xpath("(//input[@class=\"text-secondary form-control\"])[2]"));
							cc.sendKeys("");
							Thread.sleep(1000);
							
							//enter BCC
							WebElement bcc = driver.findElement(By.xpath("//input[@class=\"form-control\"]"));
							bcc.sendKeys("");
							Thread.sleep(1000);
							
							//enter subject 
							WebElement subject = driver.findElement(By.xpath("(//input[@class=\"text-secondary form-control\"])[3]"));
							subject.sendKeys("CANDIDATE DETAILS");
							Thread.sleep(1000);
							
							List<WebElement> list = driver.findElements(By.xpath("//table[@class=\"mt-4 text-secondary table table-sm table-striped table-bordered table-hover\"]/tbody/tr/td[4]"));
							int noOfCandidate=list.size();
							System.out.println("number of candidate :"+noOfCandidate);
							for (WebElement number : list) {
								System.out.println("CANDIDATE NAME :"+number.getText());
							}
							
							
							//scroll down to send email
							JavascriptExecutor js = (JavascriptExecutor) driver;
							js.executeScript("window.scrollTo(0,document.body.scrollHeight);");
							driver.findElement(By.xpath("//button[text()=\"Send Email\"]")).click();
//							w.until(ExpectedConditions.visibilityOfElementLocated(By.className("Toastify")));
//							
//							WebElement tostify = driver.findElement(By.xpath("//div[text()=\"Failed to send email\"]"));
//							if (tostify.isDisplayed()) {
//								System.out.println("unabled to send data");
//							} else {
//								System.out.println("data send successfully");
//							}
							
							//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class=\"loader-container\"]")));
							
							wait.until(driver -> (Boolean) js.executeScript("return document.querySelector('.loader-container') === null;"));

							
							WebElement toastmsg =driver.findElement(By.xpath("//div[@class=\"Toastify__toast-body\"]"));
			        		String msg = toastmsg.getText();
			        		System.out.println(msg);
			        		Thread.sleep(1000);
//			                msg = msg.toLowerCase();

			                if(msg.contains("Failed to send email")) {
			                    wdu.ScreenShot(driver, "sent_all_profile_Team");
			                    softAssert.assertTrue(false, "ERROR: FAILED TO SEND EMAIL");
			                    //softAssert.assertEquals(msg, "Failed to send email", "ERROR: FAILED TO SEND EMAIL");
			                   // Assert.fail("ERROR: Failed to send email");
			                    
			                    //click on close
			                    driver.findElement(By.xpath("(//button[text()=\"Close\"])[2]")).click();	
			                    
			                    //De_select all the candidate
			                    WebElement closeSelect = driver.findElement(By.xpath("(//button[text()=\"Close\"])[1]"));
			                    wait.until(ExpectedConditions.visibilityOf(closeSelect));
			                    js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", closeSelect);
			                    closeSelect.click();
			                    
			                    
			                } else if (msg.contains("Profile(s) Shared successfully")) {
			                    System.out.println("PROFILE SHARED SUCCESSFULLY");
			                    wdu.ScreenShot(driver, "sent_all_profile_Team"); // Optional: Capture success
			                } else {
			                    wdu.ScreenShot(driver, "sent_all_profile_Team");
			                    softAssert.assertTrue(false, "ERROR: All profile(s) Already Shared");
			                   // softAssert.assertEquals(msg, "all "+noOfCandidate+" profile(s) already shared", "ERROR: All profile(s) Already Shared");
			                   // Assert.fail("ERROR: All profile(s) Already Shared");
			                }
			               

						} else {
							System.out.println("select atleast one row");
						}
						
						
						
						List<WebElement> profleStatus_afterUpdate = driver.findElements(By.xpath("//table[@class=\"attendance-table\"]/tbody/tr/td[37]/button"));
						int status_afterUpdate = profleStatus_afterUpdate.size();
						System.out.println(status_afterUpdate);
						
						int count_au = 1;
						for (WebElement status : profleStatus_afterUpdate) {
							System.out.println("STATUS AFTER PROFILE SENT :"+count_au+" :"+status.getText());// Print the text of each element
							count_au++;
						}
						
						
						if (status_beforeUpdate == status_afterUpdate) {
						    for (int i = 0; i < status_beforeUpdate; i++) {
						        String beforeText = profleStatus_beforeUpdate.get(i).getText();
						        String afterText = profleStatus_afterUpdate.get(i).getText();
						        
						        if (!beforeText.equals(afterText)) {
						            System.out.println("STATUS CHANGED at index " + (i + 1) + ": " + beforeText + " → " + afterText);
						        } else {
						            System.out.println("STATUS UNCHANGED at index " + (i + 1) + ": " + beforeText);
						        }
						    }
						} else {
						    System.out.println("❌ Status count mismatch! Check if elements are dynamically changing.");
						}
						
						
						
						
					} else {
						System.out.println("rows not present");
					}
					
					//click on team leader section
					hp.getTeamLeaderSection().click();
					
					//logout
					Thread.sleep(1000);
					logoutPage lo=new logoutPage(driver);
					lo.logout(driver, "Yes");
					
					softAssert.assertAll("ASSERTION OCCURED");
				}
	}
	
	
	@Test(enabled = false)
	public void sentProfileUpdateResponse() throws IOException, InterruptedException {
		
		// TODO Auto-generated constructor stub
		String USERNAME=pfu.getDataFromPropertyFile("username1");
		String PASSWORD=pfu.getDataFromPropertyFile("password1");
		String URL=pfu.getDataFromPropertyFile("tl_url");
		SoftAssert softAssert = new SoftAssert();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		
		//updated;3-1-25
		Thread.sleep(2000);
		TeamLeader tl=new TeamLeader(driver);
		tl.teamLeaderPage(driver);
		
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
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class=\"loader-container\"]")));
			wait.until(ExpectedConditions.visibilityOf(hp.getFindCandidate()));
			hp.sentProfile(driver);
			
			WebElement rows = driver.findElement(By.xpath("//table[@class=\"attendance-table\"]/tbody/tr[2]"));
			if (rows.isDisplayed()) {
				System.out.println("rows present to share");
				
				WebElement profleStatus_beforeUpdate= driver.findElement(By.xpath("//table[@class=\"attendance-table\"]/tbody/tr[2]/td[37]/button"));
				String status_beforeUpdate = profleStatus_beforeUpdate.getText();
				System.out.println("STATUS BEFORE UPDATE :"+status_beforeUpdate);
				
				JavascriptExecutor js = (JavascriptExecutor) driver; 
				
				WebElement edit = driver.findElement(By.xpath("(//i[@class=\"fa-regular fa-pen-to-square\"])[2]"));	
				//js.executeScript("arguments[0].scrollIntoView(true);", edit);
				wait.until(ExpectedConditions.elementToBeClickable(edit));
				edit.click();
				
				WebElement select_status=driver.findElement(By.className("update-profile-drop-down"));
				select_status.click();		
				
				List<WebElement> options=driver.findElements(By.xpath("//select[@class=\"update-profile-drop-down\"]/option"));
				if (!options.isEmpty()) { 
				    System.out.println("Selected option: " + options.get(1).getText());
				    options.get(1).click(); 
				    
				   //click on update button
				   driver.findElement(By.xpath("//button[text()=\"Update\"]")).click();
				   
				   
				   WebElement toastmsg =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"Toastify__toast-body\"]")));
	        	   String msg = toastmsg.getText();
	        	   System.out.println(msg);
	        	    
	        	   
	        	   WebElement profleStatus_afterUpdate= driver.findElement(By.xpath("//table[@class=\"attendance-table\"]/tbody/tr[2]/td[37]/button"));
	        	   String status_afterUpdate = profleStatus_afterUpdate.getText();
	        	   System.out.println("STATUS AFTER UPDATE :"+status_afterUpdate);
	        	   
	        	   if (!status_beforeUpdate.equals(status_afterUpdate)) {
						System.out.println("STATUS UPDATE SUCCESSFULLY");
					} else {
						System.out.println("STATUS NOT UPDATE SUCCESSFULLY");
					}
	        	   
	        	   
	        	   
				} else {
				    System.out.println("No options found in the dropdown!");
				    
				    driver.findElement(By.xpath("//button[@class=\"btn-close\"]")).click();			    
				}	
			
			} else {
				System.out.println("rows not present");
			}
			
			//click on team leader section
			hp.getTeamLeaderSection().click();
			
			//logout
			Thread.sleep(1000);
			logoutPage lo=new logoutPage(driver);
			lo.logout(driver, "Yes");
			
		}
	}

}
