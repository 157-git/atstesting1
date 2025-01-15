package ObjectRepository_POM;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import lombok.Data;

@Data
public class Manager {

	//public WebDriver driver;
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    
    @FindBy(className = "main-homepage-btn")
    private WebElement letBegin;
    
    @FindBy(xpath = "(//button[@class=\"login1\"])[1]")
    private WebElement employeeButton;
    
    @FindBy(xpath = "(//button[@class=\"recpage-login1\"])[1]")
    private WebElement managerBtn;
    
    
    @FindBy(xpath = "//span[text()=\"Shortlisted \"]")
    private WebElement shortlisted;
    
    @FindBy(xpath = "//span[text()=\"Find Candidate\"]")
    private WebElement findCandidate;
    
    @FindBy(xpath = "//span[text()=\"Calling Tracker\"]")
    private WebElement callingTracker;
    
    @FindBy(xpath = "//span[text()=\"Lineup Tracker\"]")
    private WebElement lineupTracker;
    
    @FindBy(xpath = "//span[text()=\"Selected Candidate\"]")
    private WebElement selectedCandidate;
    
    @FindBy(xpath = "//span[text()=\"Hold Candidate\"]")
    private WebElement holdCandidate;
    
    @FindBy(xpath = "//span[text()=\"Rejected Candidate\"]")
    private WebElement rejectedCandidate;
    
    @FindBy(xpath = "//span[text()=\"Master Tracker\"]")
    private WebElement masterTracker;
    
    @FindBy(xpath = "//span[text()=\"Job Description\"]")
    private WebElement jobDescription;

    @FindBy(xpath = "//span[text()=\"View Job Descriptions\"]")
    private WebElement viewJobDescription;
    
    @FindBy(xpath = "//span[text()=\"Add Job Description\"]")
    private WebElement addJobDescription;
    
    @FindBy(xpath = "//span[text()=\"Manager Section\"]")
    private WebElement managerSection;
    
    @FindBy(xpath = "//span[text()=\"Billing Dashboard\"]")
    private WebElement billingDashboard;
    
    @FindBy(xpath = "//span[text()=\"Pay Roll\"]")
    private WebElement payRoll;
    
    @FindBy(xpath = "//span[text()=\" Make Invoice\"]")
    private WebElement makeInvoice;
    
    @FindBy(xpath = "//span[text()=\"Invoice Report\"]")
    private WebElement invoiceReport;
    
    @FindBy(xpath = "//span[text()=\"Update Response\"]")
    private WebElement updateResponse;
    
    @FindBy(xpath = "//span[text()=\"Shared Profiles\"]")
    private WebElement sharedProfile;
    
    @FindBy(xpath = "//span[text()=\"Schedule Interview\"]")
    private WebElement scheduledInterview;
    
    @FindBy(xpath = "//span[text()=\"Assign Columns\"]")
    private WebElement assignColumn;
    
    @FindBy(xpath = "//span[text()=\"Team Details\"]")
    private WebElement teamDetails;
    
    @FindBy(xpath = "//span[text()=\"Add Team Leader\"]")
    private WebElement addTeamLeader;
    
    @FindBy(xpath = "//span[text()=\"Sent Profile\"]")
    private WebElement sentProfile;
    
    @FindBy(xpath = "//span[text()=\"Add Client Details\"]")
    private WebElement addClientDetails;
    
    @FindBy(xpath = "//span[text()=\"Capex\"]")
    private WebElement Capex;
    
    
    
    
    
    
    
    
	public static ThreadLocal<WebDriver> getDriver() {
		return driver;
	}


	
	public Manager(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
    
	public TeamLeader managerPage(WebDriver driver) throws InterruptedException {
		
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	        try {
	            // Wait and click "Let Begin" button
	            wait.until(ExpectedConditions.elementToBeClickable(letBegin)).click();
	            System.out.println("Clicked on 'Let Begin' button successfully.");
	        } catch (Exception e) {
	            System.out.println("Failed to click 'Let Begin' button: " + e.getMessage());
	        }

	        try {
	            // Wait and click "Employee Login" button
	            wait.until(ExpectedConditions.elementToBeClickable(employeeButton)).click();
	            System.out.println("Clicked on 'Employee Login' button successfully.");
	        } catch (Exception e) {
	            System.out.println("Failed to click 'Employee Login' button: " + e.getMessage());
	        }

	        try {
	            // Wait and click "Team Leader" button
	            wait.until(ExpectedConditions.elementToBeClickable(managerBtn)).click();
	            System.out.println("Clicked on 'Manager' button successfully.");
	        } catch (Exception e) {
	            System.out.println("Failed to click 'Manager' button: " + e.getMessage());
	        }
	
		return new TeamLeader(driver);
	}
	
	
	public Manager managerLogin(WebDriver driver) {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        try {
	            // Wait and click "Team Leader" button
	            wait.until(ExpectedConditions.visibilityOf(managerBtn));
	            managerBtn.click();
	            System.out.println("Clicked on 'Manager' button successfully.");
	        } catch (Exception e) {
	            System.out.println("Failed to click 'Manager' button: " + e.getMessage());
	        }
		return new Manager(driver);
		
	}

	public WebDriver findCandidate(WebDriver driver) throws InterruptedException {
		findCandidate.click();
		Thread.sleep(10000);
		lineupTracker.click();
		return driver;
	}
	
}
