package ObjectRepository_POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FindCandidate {

	public WebDriver driver;
	
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

	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getCallingTracker() {
		return callingTracker;
	}

	public WebElement getLineupTracker() {
		return lineupTracker;
	}

	public WebElement getSelectedCandidate() {
		return selectedCandidate;
	}

	public WebElement getHoldCandidate() {
		return holdCandidate;
	}

	public WebElement getRejectedCandidate() {
		return rejectedCandidate;
	}

	public WebElement getMasterTracker() {
		return masterTracker;
	}

	
	@FindBy(xpath = "(//i[@class=\"fa-regular fa-pen-to-square\"])[1]")
	private WebElement action;
	
	
	public WebElement getAction() {
		return action;
	}

	public FindCandidate(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	
	public WebDriver callTrac(WebDriver driver) {
		callingTracker.click();
		return driver;
	}
	public WebDriver actionBtn(WebDriver driver) {
		action.click();
		return driver;
	}
	
}
