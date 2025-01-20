package teamLeader;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

public class addJobDescriptionTestNG extends baseClass_TL{

	WebDriverUtil wdu=new WebDriverUtil();
	PropertyFileUtil pfu=new PropertyFileUtil();
	ExcelUtil eu=new ExcelUtil();
	JavaUtil ju = new JavaUtil();
	JavascriptExecutor js = (JavascriptExecutor) driver;
	public WebDriver sdriver;
	
	@Test
	public void addJobDescription() throws IOException, InterruptedException {
		
		// TODO Auto-generated constructor stub
				String USERNAME=pfu.getDataFromPropertyFile("not_usernameTL");
				String PASSWORD=pfu.getDataFromPropertyFile("not_passwordTL");
				String URL="http://rg.157careers.in/Dashboard/977/TeamLeader";
				
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
					hp.addJobDescription(driver);
					
					String COMPANYNAME="Software Testing";
					String DESIGINATION="Manual Tester";
					String SALARY="3 lpa";
					String LOCATION="delhi";
					String EXPERIENCE="1 year";
					String SKILLS="manual";
					
					
					//.........................insert data in database..............................
					
					Connection connection=BS();
					if (connection == null) {
			            System.out.println("Database connection is null, cannot insert data.");
			            return;
			        }
					
					String query = "INSERT INTO addjobdescription(companyname,desigination,salary,location,experience,skills) "
							+ "VALUES (?,?,?,?,?,?)";
					
					try {
						PreparedStatement preparedStatement = connection.prepareStatement(query);
						preparedStatement.setString(1, COMPANYNAME);
						preparedStatement.setString(2, DESIGINATION);
						preparedStatement.setString(3, SALARY);
						preparedStatement.setString(4, LOCATION);
						preparedStatement.setString(5, EXPERIENCE);
						preparedStatement.setString(6, SKILLS);
					
						 int rowsAffected = preparedStatement.executeUpdate();
				            
				            if (rowsAffected > 0) {
				                System.out.println("Data successfully inserted into the database.");
				            } else {
				                System.out.println("Failed to insert data.");
				            }

				        } catch (SQLException e) {
				            e.printStackTrace();
				        }
						
					//..............................................................................
					
					WebElement companyname = driver.findElement(By.name("companyName"));
					companyname.sendKeys(COMPANYNAME);
					
					Thread.sleep(1000);
					WebElement desigination = driver.findElement(By.name("designation"));
					desigination.sendKeys(DESIGINATION);
					
					Thread.sleep(1000);
					WebElement salary = driver.findElement(By.name("salary"));
					salary.sendKeys(SALARY);
					
					Thread.sleep(1000);
					WebElement location = driver.findElement(By.name("location"));
					location.sendKeys(LOCATION);
					
					Thread.sleep(1000);
					WebElement experience = driver.findElement(By.name("experience"));
					experience.sendKeys(EXPERIENCE);
					
					Thread.sleep(1000);
					WebElement skills = driver.findElement(By.name("skills"));
					skills.sendKeys(SKILLS);
					
					//scroll down and click on submit
					Thread.sleep(1000);
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("window.scrollTo(0,document.body.scrollHeight);");
					WebElement submit = driver.findElement(By.xpath("//button[text()=\"Submit\"]"));
					Thread.sleep(1000);
					submit.click();
					Thread.sleep(2000);
					wdu.ScreenShot(driver, "AddJobDescription");
					
					//click on view job description
					Thread.sleep(3000);
					hp.viewJobDescription(driver);
					
					//click on company name drop down 
					WebElement company = driver.findElement(By.xpath("//button[text()=\"company Name\"]"));
					Thread.sleep(2000);
					company.click();
					Thread.sleep(1000);
					//list on company name
					List<WebElement> companyoption = driver.findElements(By.className("selfcalling-filter-value"));
					System.out.println("list :"+companyoption.size());
					for (WebElement list : companyoption) {
						
						if (list.getText().contains(COMPANYNAME)) {
							System.out.println("company name found in list");
							//select job check box
							driver.findElement(By.xpath("//input[@type=\"checkbox\"]")).click();
							Thread.sleep(1000);
							company.click();
							//list of job description
							List<WebElement> jobListing = driver.findElements(By.xpath("//div[@class=\"job-listing\"]"));
							//list of company
							List<WebElement> jobCompy = driver.findElements(By.xpath("//div[@class=\"job-company\"]"));
							System.out.println(jobListing.size());
								if (!jobListing.isEmpty()) {
									for (WebElement jobCompany : jobCompy) {
										if(jobCompany.getText().contains(COMPANYNAME)) {
											System.out.println("company found");
											break;
										}	
									}
								}
								break;
						} else {
							System.out.println("company not found");
							break;
						}
					
					}
					
					
					Thread.sleep(2000);
					WebElement desiginate = driver.findElement(By.xpath("//button[text()=\"designation\"]"));
					Thread.sleep(1000);
					desiginate.click();
					Thread.sleep(1000);
					List<WebElement> desioption = driver.findElements(By.className("selfcalling-filter-value"));
					System.out.println("list :"+desioption.size());
					for (WebElement list : desioption) {
						
						if (list.getText().contains(DESIGINATION)) {
							System.out.println("desigination name found in list");
							driver.findElement(By.xpath("//input[@type=\"checkbox\"]")).click();
							Thread.sleep(1000);
							desiginate.click();
							List<WebElement> jobListing = driver.findElements(By.xpath("//div[@class=\"job-listing\"]"));
							List<WebElement> jobtittle = driver.findElements(By.xpath("//div[@class=\"job-title\"]"));
							System.out.println(jobListing.size());
								if (!jobListing.isEmpty()) {
									for (WebElement jobtit : jobtittle) {
										if(jobtit.getText().contains(DESIGINATION)) {
											
											System.out.println("desigination found");
											break;
										}	
									}
								}
								break;
						} else {
							System.out.println("desigination not found");
							break;
						}
					
					}
					
					
					
					
					//logout
					Thread.sleep(1000);
					logoutPage lo=new logoutPage(driver);
					lo.logout(driver, "Yes");
					
					
				}
	}

}
