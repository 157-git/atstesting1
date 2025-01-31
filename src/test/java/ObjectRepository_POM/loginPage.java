package ObjectRepository_POM;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import org.openqa.selenium.JavascriptExecutor;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import lombok.Data;

@Data
public class loginPage {

	public WebDriver driver;
	private WebDriverWait wait;
	
	@FindBy(name = "employeeId")
	private WebElement usernametf;
	
	@FindBy(name = "password")
	private WebElement passwordtf;
	
	@FindBy(xpath = "//button[text()=\"Login\"]")
	private WebElement loginButton;
	
	@FindBy(id = "newCanvasIdForTester")
	private WebElement canvas;
	
	@FindBy(xpath = "(//input[@class=\"loginpage-form-control\"])[4]")
	private WebElement Captcha;
	
	@FindBy(className = "loginpage-error")
	private WebElement loginError;
	
	@FindBy(className = "error")
	private WebElement captchaError;
	

	public loginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String extractCaptchaText() throws IOException, TesseractException {
		
		
		
        // Extract base64 image from canvas
        String base64Image = (String) ((JavascriptExecutor) driver).executeScript(
        		"var canvas = document.getElementsByTagName('canvas')[0];" +
        	    "return canvas ? canvas.toDataURL('image/png').substring(22) : null;"
        );
        
        if (base64Image == null) {
            throw new IOException("No CAPTCHA image found on the page.");
        }

        // Decode and save image
        byte[] imageBytes = Base64.getDecoder().decode(base64Image);
        String filePath = "./Captcha/captcha.png";
        Files.write(Paths.get(filePath), imageBytes);
        System.out.println("CAPTCHA image saved as: " + filePath);

        // Perform OCR using Tesseract
        ITesseract tesseract = new Tesseract();
        tesseract.setDatapath("C:\\Program Files\\Tesseract-OCR\\tessdata"); // Update the tessdata path
        tesseract.setLanguage("eng");
        tesseract.setTessVariable("tessedit_char_whitelist", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789");
        
        // Extract text from image
        String captchaText = tesseract.doOCR(new File(filePath)).trim();
        System.out.println("Extracted CAPTCHA Text: " + captchaText);

        return captchaText;
        
    }
	
	
	
	public RecruiterhomePage login(String usernameData,String passwordData) {
		boolean error = true;
		try {
			
			Thread.sleep(1000);
			usernametf.sendKeys(usernameData);
			passwordtf.sendKeys(passwordData);
			Thread.sleep(1000);
			String captcha = extractCaptchaText();
			System.out.println("captcha :"+captcha);
			Captcha.sendKeys(captcha);
			loginButton.click();
			
			while(error) {
			
			if(captchaError.isDisplayed()) {
				System.out.println("CAPTCHA ERROR");
				canvas.click();
				Thread.sleep(500);
				Captcha.clear();
				String re_captcha=extractCaptchaText();
				System.out.println("Re-captcha :"+re_captcha);
				Thread.sleep(500);
				Captcha.sendKeys(re_captcha);
				loginButton.click();
				error=false;
			}
			
		}//while loop end
			
		} 
		catch (Exception e) {
            System.out.println("Failed to login : " + e.getMessage());
        }
		return new RecruiterhomePage(driver);
	}
}
