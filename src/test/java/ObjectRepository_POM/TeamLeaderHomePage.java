package ObjectRepository_POM;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TeamLeaderHomePage{
	
	public WebDriver driver;
	
	@FindBy(xpath = "//span[text()=\"Team Leader Section\"]")
	private WebElement teamLeaderSection;
	
	@FindBy(xpath = "(//span[@class=\"sidebar-text\"])[3]")
	private WebElement findCandidate;
	
	
	public WebElement getTeamLeaderSection() {
		return teamLeaderSection;
	}

	public TeamLeaderHomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public TeamLeadSection TeamLeaderSection(WebDriver driver) {

		teamLeaderSection.click();
		return new TeamLeadSection(driver);
	}
	
	
	
}
