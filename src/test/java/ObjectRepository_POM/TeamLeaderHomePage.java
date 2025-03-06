package ObjectRepository_POM;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import lombok.Data;

@Data
public class TeamLeaderHomePage{
	
	public WebDriver driver;
	
	@FindBy(xpath = "//span[text()=\"Find Candidate\"]")
	private WebElement findCandidate;
	
	@FindBy(xpath = "//span[text()=\"Calling Tracker\"]")
	private WebElement callingTracker;
	
	@FindBy(css  = ".ant-badge.css-nqoqt9")
	private WebElement notification;

	@FindBy(xpath = "//span[text()=\"Team Leader Section\"]")
	private WebElement teamLeaderSection;
	
	@FindBy(xpath = "//span[text()=\"Sent Profile\"]")
	private WebElement sentProfile;
	
	@FindBy(xpath = "//span[text()=\"Interview Feedback\"]")
	private WebElement updateResponse;
	
	@FindBy(xpath = "//span[text()=\"Shared Profiles\"]")
	private WebElement sharedProfile;

	@FindBy(xpath = "//span[text()=\"Pay Roll\"]")
	private WebElement payRoll;
	
	@FindBy(xpath = "//span[text()=\"Create Question paper\"]")
	private WebElement CreateQuestionPaper;
	
	@FindBy(xpath = "//span[text()=\"Schedule Interview\"]")
	private WebElement scheduledInterview;
	
	@FindBy(xpath = "//span[text()=\"Add Recruiters\"]")
	private WebElement addRecruiter;
	
	@FindBy(xpath = "//span[text()=\"Team Details\"]")
	private WebElement teamdetails;
	
	@FindBy(xpath = "//span[text()=\"Job Description\"]")
	private WebElement jobDescription;
	
	@FindBy(xpath = "//span[text()=\"View Job Descriptions\"]")
	private WebElement viewJobDescription;
	
	@FindBy(xpath = "//span[text()=\"Add Job Description\"]")
	private WebElement addJobDescription;

	@FindBy(xpath = "//span[text()=\"Reports\"]")
	private WebElement reports;
	

	public TeamLeaderHomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public TeamLeadSection TeamLeaderSection(WebDriver driver) {

		teamLeaderSection.click();
		return new TeamLeadSection(driver);
	}
	
	public FindCandidate callingTracker(WebDriver driver) throws InterruptedException {
		findCandidate.click();
		return new FindCandidate(driver);
	}
	
	public WebDriver notification(WebDriver driver) {
		notification.click();
		return driver;
	}
	
	public WebDriver addJobDescription(WebDriver driver) throws InterruptedException {
		jobDescription.click();
		Thread.sleep(2000);
		addJobDescription.click();
		return driver;
	}
	
	public WebDriver viewJobDescription(WebDriver driver) throws InterruptedException {
		viewJobDescription.click();
		return driver;
	}
	
	public WebDriver sentProfile(WebDriver driver) throws InterruptedException {
		teamLeaderSection.click();
		Thread.sleep(1000);
		sentProfile.click();
		return driver;
	}
	
	public WebDriver updateResponse(WebDriver driver) {
		updateResponse.click();
		return driver;
	}
}
