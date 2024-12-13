package recruiter;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
import ObjectRepository_POM.JobDescription;
import ObjectRepository_POM.RecruiterGear;
import ObjectRepository_POM.RecruiterhomePage;
import ObjectRepository_POM.loginPage;
import ObjectRepository_POM.logoutPage;

@Listeners(listenerImplementation.class)
public class jobDescriptionTestNG extends baseClass{

	PropertyFileUtil pfu = new PropertyFileUtil();
	WebDriverUtil wdu=new WebDriverUtil();
	ExcelUtil eu=new ExcelUtil();
	JavaUtil ju = new JavaUtil();
	public WebDriver sdriver;

	
	@Test(enabled = false)
	public void shareJobDescription() throws IOException, InterruptedException {
		
				JavascriptExecutor j=(JavascriptExecutor) driver;
				WebDriverWait w=new WebDriverWait(driver, Duration.ofSeconds(10));
				
				//get data from property file
				String USERNAME=pfu.getDataFromPropertyFile("username");
				String PASSWORD=pfu.getDataFromPropertyFile("password");
				String URL=pfu.getDataFromPropertyFile("rec_url");
				
				Thread.sleep(2000);
				RecruiterGear r = new RecruiterGear(driver);
				r.RecruiterPage(driver);

				
				Thread.sleep(2000);
				//login page URL
				String LoginPageUrl=driver.getCurrentUrl();
				System.out.println(LoginPageUrl);
				
				//login
				loginPage lp = new loginPage(driver);
				lp.login(USERNAME, PASSWORD);

				Thread.sleep(2000);
				String RecPageUrl=driver.getCurrentUrl();
				System.out.println(RecPageUrl);
				
				//is user login or not
				if (RecPageUrl.equals(LoginPageUrl)) {
					System.out.println("login failed");
				} else if(RecPageUrl.equals(URL)){
					System.out.println("login successfull");
					
					//click on job description
					RecruiterhomePage hp=new RecruiterhomePage(driver);
					hp.jobDescription(driver);
					
					//click on view job description 
					JobDescription jd=new JobDescription(driver);
					jd.viewJD(driver);
					Thread.sleep(1000);
					jd.viewbtn(driver);
					
					Thread.sleep(1000);
					
					JavascriptExecutor js=(JavascriptExecutor) driver;
					WebElement position=driver.findElement(By.xpath("//div[text()=\"Position Overview\"]"));
					WebElement responsibility=driver.findElement(By.xpath("//div[text()=\"Responsibilities\"]"));
					WebElement requirement = driver.findElement(By.xpath("//div[text()=\"Requirements\"]"));
					WebElement preferedQualification=driver.findElement(By.xpath("//div[text()=\"Preferred Qualifications\"]"));
					
					//scroll down if text heading available
					if (responsibility.isDisplayed()) {
						js.executeScript("arguments[0].scrollIntoView();", responsibility);
					} else if(requirement.isDisplayed()) {
						js.executeScript("arguments[0].scrollIntoView();", requirement);
					}else {
						js.executeScript("arguments[0].scrollIntoView();", preferedQualification);
					}
					
					//scroll up
					Thread.sleep(1000);
					js.executeScript("arguments[0].scrollIntoView();", position);
					
					//scroll down to number of position
					Thread.sleep(1000);
					WebElement noOfPosition=driver.findElement(By.xpath("//b[text()=\"Number of Positions : \"]"));
					js.executeScript("arguments[0].scrollIntoView();", noOfPosition);
					
					//scroll up to share video
					Thread.sleep(1000);
					WebElement shareVideo=driver.findElement(By.xpath("//div[text()=\" Search \"]"));
					js.executeScript("arguments[0].scrollIntoView();", shareVideo);
					
					//click on share button
					Thread.sleep(1000);
					WebElement share = driver.findElement(By.xpath("//button[text()=\"Share\"]"));
					share.click();
					
					//scroll down to shareJobDescription
					Thread.sleep(1000);
					WebElement shareJobDescription = driver.findElement(By.xpath("(//button[@class=\"apply-button-share\"])[2]"));
					js.executeScript("arguments[0].scrollIntoView();", shareJobDescription);
					
					//click on share job description
					Thread.sleep(1000);
					System.out.println(shareJobDescription.isEnabled());
					shareJobDescription.click();
					
					//click on close button
					Thread.sleep(1000);
					WebElement closeJobDescription = driver.findElement(By.xpath("(//button[@class=\"apply-button-share\"])[1]"));
					js.executeScript("arguments[0].scrollIntoView();", closeJobDescription);
					Thread.sleep(1000);
					closeJobDescription.click();
					
					Thread.sleep(500);
					driver.findElement(By.id("jd-cancle-btn")).click();
					
					//logout
					Thread.sleep(1000);
					logoutPage lo=new logoutPage(driver);
					lo.logout(driver, "Yes");

				}
				
			
			
	
	}
	
	@Test(enabled = false)
	public void searchAndShareJobDescription() throws InterruptedException, IOException {
		
		JavascriptExecutor j=(JavascriptExecutor) driver;
		WebDriverWait w=new WebDriverWait(driver, Duration.ofSeconds(10));
		
		//get data from property file
		String USERNAME=pfu.getDataFromPropertyFile("username");
		String PASSWORD=pfu.getDataFromPropertyFile("password");
		String URL=pfu.getDataFromPropertyFile("rec_url");
		String search="JAVA";
		
		RecruiterGear r = new RecruiterGear(driver);
		r.RecruiterPage(driver);

		
		Thread.sleep(2000);
		//login page URL
		String LoginPageUrl=driver.getCurrentUrl();
		System.out.println(LoginPageUrl);
		
		//login
		loginPage lp = new loginPage(driver);
		lp.login(USERNAME, PASSWORD);

		Thread.sleep(2000);
		String RecPageUrl=driver.getCurrentUrl();
		System.out.println(RecPageUrl);
		
		//is user login or not
		if (RecPageUrl.equals(LoginPageUrl)) {
			System.out.println("login failed");
		} else if(RecPageUrl.equals(URL)){
			System.out.println("login successfull");
					
			//click on job description
			RecruiterhomePage hp=new RecruiterhomePage(driver);
			hp.jobDescription(driver);
			
			//click on view job description 
			JobDescription jd=new JobDescription(driver);
			jd.viewJD(driver);
			
			Thread.sleep(1000);
			
			JavascriptExecutor js=(JavascriptExecutor) driver;
			
			WebElement designation=driver.findElement(By.cssSelector("input[name=\"designation\"]"));
			designation.click();
			Thread.sleep(500);
			designation.sendKeys(search);
			
			//click on search button
			Thread.sleep(500);
			WebElement searchBtn = driver.findElement(By.xpath("//div[text()=\" Search \"]"));
			searchBtn.click();
			
			//wait for jd to display
			List<WebElement> desg = driver.findElements(By.xpath("//div[text()=\"Data Analyst\"]"));
			for (WebElement heading : desg) {
				w.until(ExpectedConditions.visibilityOf(heading));
			}
			
			//click on view 
			jd.viewbtn(driver);
			
			//scroll down to number of position
			Thread.sleep(1000);
			WebElement noOfPosition=driver.findElement(By.xpath("//b[text()=\"Number of Positions : \"]"));
			js.executeScript("arguments[0].scrollIntoView();", noOfPosition);
			
			//scroll up to search button
			Thread.sleep(1000);
			js.executeScript("arguments[0].scrollIntoView();", searchBtn);
			
			//click on share button
			Thread.sleep(1000);
			WebElement share=driver.findElement(By.xpath("//button[text()=\"Share\"]"));
			share.click();
			
			//scroll down to shareJobDescription
			Thread.sleep(1000);
			WebElement shareJobDescription = driver.findElement(By.xpath("(//button[@class=\"apply-button-share\"])[2]"));
			js.executeScript("arguments[0].scrollIntoView();", shareJobDescription);
			
			//click on share job description
			Thread.sleep(1000);
			shareJobDescription.click();
			
			//click on close button
			Thread.sleep(1000);
			WebElement closeJobDescription = driver.findElement(By.xpath("(//button[@class=\"apply-button-share\"])[1]"));
			js.executeScript("arguments[0].scrollIntoView();", closeJobDescription);
			Thread.sleep(1000);
			closeJobDescription.click();
			
			Thread.sleep(500);
			driver.findElement(By.id("jd-cancle-btn")).click();
			
			//logout
			Thread.sleep(1000);
			logoutPage lo=new logoutPage(driver);
			lo.logout(driver, "Yes");
		}
	}
	
	@Test(enabled = false)
	public void shareVideoJobDescription() throws IOException, InterruptedException {
		
		JavascriptExecutor js=(JavascriptExecutor) driver;
		WebDriverWait w=new WebDriverWait(driver, Duration.ofSeconds(60));
		
		//get data from property file
		String USERNAME=pfu.getDataFromPropertyFile("username");
		String PASSWORD=pfu.getDataFromPropertyFile("password");
		String URL=pfu.getDataFromPropertyFile("rec_url");
		
		Thread.sleep(2000);
		RecruiterGear r = new RecruiterGear(driver);
		r.RecruiterPage(driver);

		
		Thread.sleep(2000);
		//login page URL
		String LoginPageUrl=driver.getCurrentUrl();
		System.out.println(LoginPageUrl);
		
		//login
		loginPage lp = new loginPage(driver);
		lp.login(USERNAME, PASSWORD);

		Thread.sleep(2000);
		String RecPageUrl=driver.getCurrentUrl();
		System.out.println(RecPageUrl);
		
		//is user login or not
		if (RecPageUrl.equals(LoginPageUrl)) {
			System.out.println("login failed");
		} else if(RecPageUrl.equals(URL)){
			System.out.println("login successfull");
		
			//click on job description
			RecruiterhomePage hp=new RecruiterhomePage(driver);
			hp.jobDescription(driver);
			
			//click on view job description 
			JobDescription jd=new JobDescription(driver);
			jd.viewJD(driver);
			Thread.sleep(1000);
			jd.viewbtn(driver);
			
			Thread.sleep(1000);
			
			
			WebElement position=driver.findElement(By.xpath("//div[text()=\"Position Overview\"]"));
			WebElement responsibility=driver.findElement(By.xpath("//div[text()=\"Responsibilities\"]"));
			WebElement requirement = driver.findElement(By.xpath("//div[text()=\"Requirements\"]"));
			WebElement preferedQualification=driver.findElement(By.xpath("//div[text()=\"Preferred Qualifications\"]"));
			
			//scroll down if text heading available
			if (responsibility.isDisplayed()) {
				js.executeScript("arguments[0].scrollIntoView();", responsibility);
			} else if(requirement.isDisplayed()) {
				js.executeScript("arguments[0].scrollIntoView();", requirement);
			}else {
				js.executeScript("arguments[0].scrollIntoView();", preferedQualification);
			}
			
			//scroll up
			Thread.sleep(1000);
			js.executeScript("arguments[0].scrollIntoView();", position);
			
			//scroll down to number of position
			Thread.sleep(1000);
			WebElement noOfPosition=driver.findElement(By.xpath("//b[text()=\"Number of Positions : \"]"));
			js.executeScript("arguments[0].scrollIntoView();", noOfPosition);
			
			//scroll up to search option
			Thread.sleep(1000);
			WebElement search=driver.findElement(By.xpath("//div[text()=\" Search \"]"));
			js.executeScript("arguments[0].scrollIntoView();", search);
			
			//click on share video button
			Thread.sleep(1000);
			WebElement share = driver.findElement(By.xpath("//button[text()=\"Share Video\"]"));
			share.click();
			
			//scroll down to shareJobDescription
			Thread.sleep(1000);
			WebElement shareJobDescription = driver.findElement(By.xpath("(//button[@class=\"apply-button-share\"])[1]"));
			js.executeScript("arguments[0].scrollIntoView();", shareJobDescription);
			
			//click on play video
			Thread.sleep(500);
			driver.findElement(By.cssSelector(".fas.fa-play")).click();
			
			//click on share job description
			w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".fas.fa-play")));
			Thread.sleep(1000);
			shareJobDescription.click();
			
			//click on close button
			Thread.sleep(1000);
			WebElement closeJobDescription = driver.findElement(By.xpath("(//button[@class=\"apply-button-share\"])[2]"));
			js.executeScript("arguments[0].scrollIntoView();", closeJobDescription);
			Thread.sleep(1000);
			closeJobDescription.click();
			
			Thread.sleep(1000);
			Actions a=new Actions(driver);
			a.moveToElement(share).sendKeys(Keys.PAGE_UP).perform();
			driver.findElement(By.id("jd-cancle-btn")).click();
		
			//logout
			Thread.sleep(1000);
			logoutPage lo=new logoutPage(driver);
			lo.logout(driver, "Yes");
		}
	
	}
	
	@Test(enabled = true)
	public void filterJobDescription() throws IOException, InterruptedException {
		
				JavascriptExecutor js=(JavascriptExecutor) driver;
				WebDriverWait w=new WebDriverWait(driver, Duration.ofSeconds(60));
		
				//get data from property file
				String USERNAME=pfu.getDataFromPropertyFile("username");
				String PASSWORD=pfu.getDataFromPropertyFile("password");
				String URL=pfu.getDataFromPropertyFile("rec_url");
				
				Thread.sleep(2000);
				RecruiterGear r = new RecruiterGear(driver);
				r.RecruiterPage(driver);

				
				Thread.sleep(2000);
				//login page URL
				String LoginPageUrl=driver.getCurrentUrl();
				System.out.println(LoginPageUrl);
				
				
				//login
				loginPage lp = new loginPage(driver);
				lp.login(USERNAME, PASSWORD);

				Thread.sleep(2000);
				String RecPageUrl=driver.getCurrentUrl();
				System.out.println(RecPageUrl);
				
				//is user login or not
				if (RecPageUrl.equals(LoginPageUrl)) {
					System.out.println("login failed");
				} else if(RecPageUrl.equals(URL)){
					System.out.println("login successfull");
					//click on job description
					RecruiterhomePage hp=new RecruiterhomePage(driver);
					hp.jobDescription(driver);
					
					//click on view job description 
					JobDescription jd=new JobDescription(driver);
					jd.viewJD(driver);
					Thread.sleep(1000);
					
					//click on company name drop down 
					WebElement CompanyDropdown = driver.findElement(By.xpath("(//button[@class=\"white-Btn\"])[2]"));
					//select one check box from the drop down
					//wdu.handleDropdown(CompanyDropdown, "157 industries pvt ltd");
					CompanyDropdown.click();
					WebElement dropdownValue = driver.findElement(By.xpath("//label[text()=\"157 industries pvt ltd\"]"));
					dropdownValue.click();
					Thread.sleep(500);
					//click on drop down to close the drop down
					CompanyDropdown.click();
					
					//wait if company is present
					try {
						WebElement companyName = driver.findElement(By.xpath("//div[contains(@class,\"job-header\")]"));
						w.until(ExpectedConditions.visibilityOf(companyName));
						
						boolean company=companyName.isDisplayed();
					
					if (company) {
						
						System.out.println(companyName.getText());
					
						//click on view button
						Thread.sleep(1000);
						jd.viewbtn(driver);
						
						Thread.sleep(500);
						WebElement position=driver.findElement(By.xpath("//div[text()=\"Position Overview\"]"));
						WebElement responsibility=driver.findElement(By.xpath("//div[text()=\"Responsibilities\"]"));
						WebElement requirement = driver.findElement(By.xpath("//div[text()=\"Requirements\"]"));
						WebElement preferedQualification=driver.findElement(By.xpath("//div[text()=\"Preferred Qualifications\"]"));
						
						//scroll down if text heading available
						if (responsibility.isDisplayed()) {
							js.executeScript("arguments[0].scrollIntoView();", responsibility);
						} else if(requirement.isDisplayed()) {
							js.executeScript("arguments[0].scrollIntoView();", requirement);
						}else {
							js.executeScript("arguments[0].scrollIntoView();", preferedQualification);
						}
						
						//scroll up
						Thread.sleep(1000);
						js.executeScript("arguments[0].scrollIntoView();", position);
						
						//scroll down to number of position
						Thread.sleep(1000);
						WebElement noOfPosition=driver.findElement(By.xpath("//b[text()=\"Number of Positions : \"]"));
						js.executeScript("arguments[0].scrollIntoView();", noOfPosition);
						
						//scroll up to search option
						Thread.sleep(1000);
						WebElement search=driver.findElement(By.xpath("//div[text()=\" Search \"]"));
						js.executeScript("arguments[0].scrollIntoView();", search);
						
						//click on share video button
						Thread.sleep(1000);
						WebElement share = driver.findElement(By.xpath("//button[text()=\"Share Video\"]"));
						share.click();
						
						//scroll down to shareJobDescription
						Thread.sleep(1000);
						WebElement shareJobDescription = driver.findElement(By.xpath("(//button[@class=\"apply-button-share\"])[1]"));
						js.executeScript("arguments[0].scrollIntoView();", shareJobDescription);
						
						//click on play video
						Thread.sleep(500);
						WebElement play = driver.findElement(By.cssSelector(".fas.fa-play"));
						play.click();
		
						//click on pause button
						WebElement pause = driver.findElement(By.cssSelector(".fas.fa-pause"));
						wdu.mouseHover(driver, shareJobDescription);
						//w.until(ExpectedConditions.invisibilityOf(pause));
						
					
						System.out.println("..........3............");
						while (true) {
							//pause=driver.findElement(By.xpath("//i[contains(@class,\"fas fa-pause\")]"));
							//play=driver.findElement(By.xpath("//i[contains(@class,\"fas fa-play\")]"));
							
							if (pause.isDisplayed()) {
								System.out.println("video is playing");	
							}
							w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".fas.fa-play")));
							
							if (play.isDisplayed()) {
								System.out.println("video is stopped");
								break;
							}
						}
						
						System.out.println("................");
						if (play.isDisplayed()) {
							//click on share job description
							Thread.sleep(1000);
							shareJobDescription.click();
							
							//click on close button
							Thread.sleep(1000);
							WebElement closeJobDescription = driver.findElement(By.xpath("(//button[@class=\"apply-button-share\"])[2]"));
							js.executeScript("arguments[0].scrollIntoView();", closeJobDescription);
							Thread.sleep(1000);
							closeJobDescription.click();
							
							Thread.sleep(500);
							js.executeScript("arguments[0].scrollIntoView();", search);
							Thread.sleep(500);
							driver.findElement(By.id("jd-cancle-btn")).click();
						}
						
					} else {
						System.out.println("............company not present...............");
					}
					}catch (TimeoutException e) {
					    // Handle timeout if the element is not found within the wait time
					    System.out.println("Timeout occurred: " + e.getMessage());
					} catch (NoSuchElementException e) {
					    // Handle the case where the element is not found
					    System.out.println("Element not found: " + e.getMessage());
					} catch (StaleElementReferenceException e) {
					    // Handle the case where the element is no longer in the DOM
					    System.out.println("Stale element: " + e.getMessage());
					} catch (Exception e) {
					    // Handle any other unexpected exceptions
					    System.out.println("An error occurred: " + e.getMessage());
					}
					
				
				}
	}
				
}
