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
