package Manager;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import CommonUtil.ExcelUtil;
import CommonUtil.JavaUtil;
import CommonUtil.PropertyFileUtil;
import CommonUtil.WebDriverUtil;
import CommonUtil.baseClass_M;
import ObjectRepository_POM.Manager;
import ObjectRepository_POM.TeamLeader;
import ObjectRepository_POM.TeamLeaderHomePage;
import ObjectRepository_POM.loginPage;
import ObjectRepository_POM.logoutPage;

public class interviewFeedbackTestNG extends baseClass_M{

	WebDriverUtil wdu=new WebDriverUtil();
	PropertyFileUtil pfu=new PropertyFileUtil();
	ExcelUtil eu=new ExcelUtil();
	JavaUtil ju = new JavaUtil();

	public WebDriver sdriver;
	
	@Test(enabled = true)
	public void sentProfile() throws IOException, InterruptedException {
		
		// TODO Auto-generated constructor stub
		String USERNAME_m=pfu.getDataFromPropertyFile("not_usernameM");
		String PASSWORD_m=pfu.getDataFromPropertyFile("not_passwordM");				
		String URL_m=pfu.getDataFromPropertyFile("not_urlM");
		SoftAssert softAssert = new SoftAssert();
		Manager m=new Manager(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
		
		
		Thread.sleep(2000);
		Manager manager=new Manager(driver);
		manager.managerPage(driver);
		
		Thread.sleep(2000);
		String LoginPageUrl=driver.getCurrentUrl();
		System.out.println(LoginPageUrl);
		
		//login
		loginPage lp = new loginPage(driver);
		lp.login(USERNAME_m, PASSWORD_m);

		//6-12-24 updated
		Thread.sleep(2000);
		String homePageUrl_m = driver.getCurrentUrl();
		System.out.println(homePageUrl_m);
		
				
		if (homePageUrl_m.equals(LoginPageUrl)) {
			System.out.println("login failed");
			WebElement error = driver.findElement(By.className("loginpage-error"));
			if (error.isDisplayed()) {
				System.out.println(error.getText());
			}
			//Assert.fail("Invalid login details");
		} else if(homePageUrl_m.equals(URL_m)) {
					System.out.println("login successfull");
					
					
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class=\"loader-container\"]")));
					wait.until(ExpectedConditions.visibilityOf(m.getManagerSection()));
					m.getManagerSection().click();
					m.getUpdateResponse().click();
					
					//click on edit action
					WebElement edit =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@class=\"attendance-table\"]/tbody/tr[5]/td[22]")));
					edit.click();
					
					// Find all rows in the table
					List<WebElement> round = driver.findElements(By.xpath("//table[@class='min-w-full border-collapse table-auto']/tbody/tr"));
					wait.until(ExpectedConditions.visibilityOfAllElements(round));
					int no_round = round.size();
					System.out.println("NUMBER OF ROUNDS: " + no_round);

					if (!round.isEmpty()) {
					    for (int i = 0; i <= no_round; i++) {
					        WebElement row = round.get(i);
					        System.out.println("Processing row: " + (i+1));

					        
					        if (i == no_round - 1) {  
					            System.out.println("Filling data in the last row..."+(i+1));
					            
					            List<WebElement> rowElement = driver.findElements(By.xpath("//table[@class=\"min-w-full border-collapse table-auto\"]/tbody/tr[1]/td"));
					            int rowEle= rowElement.size();
					            System.out.println("Number of columns in last row: " + rowEle);
					            
					            if (rowEle>7) {
									System.out.println("multiple round");
									
					                WebElement Iround = row.findElement(By.xpath(".//td[2]/select"));
					                String interviewRound = Iround.getText();
					                System.out.println("Interview Round: " + interviewRound);

					                WebElement response = row.findElement(By.xpath(".//td[3]/select"));
					                response.click();
					                wdu.handleDropdown(response, "Selected");
					                System.out.println("response :"+response.getText());
					                
					                Thread.sleep(1000);
					                WebElement comment=row.findElement(By.xpath(".//td[@class='p-2']//input[@name='commentForTl']"));
						            comment.sendKeys("good");
						            
					                // Click the "Update" button (modify XPath if needed)
					                WebElement saveButton = row.findElement(By.xpath("//button[text()='Update']"));
					                Thread.sleep(1000);
					                saveButton.click();
					                
					                
									
								} else {
									System.out.println("single round :"+i);
									
						            // Example: First row may not have input fields or may have additional columns
						            WebElement Iround = row.findElement(By.xpath(".//td[@class='p-2']/select")); // Modify as needed .//td[3]
						            Iround.click();
						            wdu.handleDropdown(Iround, "Hr Round");
						            
						            WebElement comment=row.findElement(By.xpath(".//td[@class='p-2']//input[@name='commentForTl']"));
						            comment.sendKeys("good");
						          
						            WebElement saveButton = row.findElement(By.xpath("//button[text()='Update']"));
						            Thread.sleep(1000);
					                saveButton.click();
									
								}

					            break;
					        }
					    }
					} else {
					    System.out.println("No rows found in the table.");
					}
					
					WebElement toastmsg =driver.findElement(By.xpath("//div[@class=\"Toastify__toast-body\"]"));
	        		String msg = toastmsg.getText();
	        		System.out.println(msg);
	        		Thread.sleep(1000);
	        		
	        			if(msg.contains("Please select an interview response.")) {
	        				System.out.println("INTERVIEW RESPONSE NOT SELECTED");
		                    wdu.ScreenShot(driver, "interviewFeedback");
		                    
		                    WebElement cancelButton = driver.findElement(By.xpath("//button[text()=\"Close\"]"));
		                    cancelButton.click();
		                    
	        		 	}else if (msg.contains("Response updated successfully.")) {
	        		 		System.out.println("UPDATE SUCCESSFULLY");
		                    wdu.ScreenShot(driver, "interviewFeedback"); // Optional: Capture success //button[text()="Close"]
		                } else {
		                    wdu.ScreenShot(driver, "interviewFeedback");
		                    System.out.println("....ERROR.....");
		                    
		                    WebElement cancelButton = driver.findElement(By.xpath("//button[text()=\"Close\"]"));
		                    cancelButton.click();		
		                }
	        			
	        		 
	        		//click on manager section
					m.getManagerSection().click();
	        		 
					//logout
					Thread.sleep(1000);
					logoutPage lo=new logoutPage(driver);
					lo.logout(driver, "Yes");
					
				}
	}

}
