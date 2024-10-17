package ObjectRepository_POM;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import CommonUtil.ExcelUtil;
import recruiter.AddCandidateTestNG;

public class AddCandidate {
	
	@FindBy(name = "candidateName")
	private WebElement candidateName;
	
	@FindBy(name = "candidateEmail")
	private WebElement candidateEmail;
	
	@FindBy(name="contactNumber")
	private WebElement candidateContact;
	
	@FindBy(name="alternateNumber")
	private WebElement candidateWhatapp;
	
	

	public WebElement getCandidateName() {
		return candidateName;
	}



	public WebElement getCandidateEmail() {
		return candidateEmail;
	}



	public WebElement getCandidateContact() {
		return candidateContact;
	}



	public WebElement getCandidateWhatapp() {
		return candidateWhatapp;
	}



	public AddCandidate(WebDriver driver) {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}
	
	public AddCandidateTestNG CandidateInfo(String candidatename,String candidateemail) throws InterruptedException {	
		
		candidateName.sendKeys(candidatename);
		Thread.sleep(1000);
		candidateEmail.sendKeys(candidateemail);
		
		return new AddCandidateTestNG();
	}
	
}
