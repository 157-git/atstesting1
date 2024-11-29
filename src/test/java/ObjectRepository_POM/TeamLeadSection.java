package ObjectRepository_POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TeamLeadSection {

	public WebDriver driver;
	
	@FindBy(xpath = "//span[text()=\"Sent Profile\"]")
	private WebElement sentProfile;
	
	@FindBy(xpath = "//span[text()=\"Update Response\"]")
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

	public WebElement getSentProfile() {
		return sentProfile;
	}

	public WebElement getUpdateResponse() {
		return updateResponse;
	}

	public WebElement getSharedProfile() {
		return sharedProfile;
	}

	public WebElement getPayRoll() {
		return payRoll;
	}

	public WebElement getCreateQuestionPaper() {
		return CreateQuestionPaper;
	}

	public WebElement getScheduledInterview() {
		return scheduledInterview;
	}

	public WebElement getAddRecruiter() {
		return addRecruiter;
	}

	public WebElement getTeamdetails() {
		return teamdetails;
	}

	public TeamLeadSection(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public TeamLeadSection updateResponse(WebDriver driver) {
		updateResponse.click();
		return new TeamLeadSection(driver);
	}
}
