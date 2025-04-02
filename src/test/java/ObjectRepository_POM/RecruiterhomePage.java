package ObjectRepository_POM;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v118.database.Database;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import lombok.Data;

@Data
public class RecruiterhomePage {

	public WebDriver driver;
	
	@FindBy(xpath = "//span[text()=\"Shortlisted \"]")
	private WebElement Shortlisted;
	
	@FindBy(xpath = "//span[text()=\"Add Candidate\"]")
	private WebElement AddCandidate;
	
	@FindBy(xpath = "//span[text()=\"Find Candidate\"]")
	private WebElement FindCandidate;
	
	@FindBy(xpath = "//span[text()=\"Job Description\"]")
	private WebElement JobDescription;
	
	@FindBy(xpath = "//span[text()=\"Database\"]")
	private WebElement DataBase;
	
	@FindBy(xpath = "//span[text()=\"Chat Section\"]")
	private WebElement ChatSection;
	
	@FindBy(xpath = "//span[text()=\"Note Pad\"]")
	private WebElement NotePad;
	
	@FindBy(xpath = "//span[text()=\"Portal\"]")
	private WebElement Portal;
	
	@FindBy(xpath = "//span[text()=\"About Us\"]")
	private WebElement AboutUs;
	
	@FindBy(xpath = "//span[text()=\"Help\"]")
	private WebElement Help;
	
	@FindBy(xpath = "//span[text()=\"Interview Questions\"]")
	private WebElement InterViewQuestion;
	
	@FindBy(xpath = "//span[text()=\"History Tracker\"]")
	private WebElement HistoryTracker;
	
	@FindBy(xpath = "//span[text()=\"Choose Colour\"]")
	private WebElement ChooseColor;
	
	@FindBy(xpath = "//span[text()='Logout']")
	private WebElement Logout;
   
	
	
	
	
    public RecruiterhomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
    
   public ShortListed home(WebDriver driver) {
	   Shortlisted.click();
	   return new ShortListed(driver);
   } 
   
   public AddCandidate addCan(WebDriver driver) {
	   AddCandidate.click();
	   return new AddCandidate(driver);
   }

   public FindCandidate FinCan(WebDriver driver) {
	 //  WebDriverWait w=new WebDriverWait(driver, Duration.ofSeconds(10));
	   //w.until(ExpectedConditions.visibilityOf(FindCandidate));
	   FindCandidate.click();
	   return new FindCandidate(driver);
   }
   
  public DataBase dataBase(WebDriver driver) {
	  DataBase.click();
	  return new DataBase(driver);
	  }
  
  public JobDescription jobDescription(WebDriver driver) {
	  JobDescription.click();
	  return new JobDescription(driver);
  }
 
  public WebDriver chooseColour(WebDriver driver) {
	  ChooseColor.click();
	  return driver;
  }
}
