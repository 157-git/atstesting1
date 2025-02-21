package teamLeader;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import CommonUtil.ExcelUtil;
import CommonUtil.JavaUtil;
import CommonUtil.PropertyFileUtil;
import CommonUtil.WebDriverUtil;
import CommonUtil.baseClass_TL;
import ObjectRepository_POM.TeamLeader;
import ObjectRepository_POM.TeamLeaderHomePage;
import ObjectRepository_POM.loginPage;
import ObjectRepository_POM.logoutPage;


public class ReportsTestNG extends baseClass_TL{

	WebDriverUtil wdu=new WebDriverUtil();
	PropertyFileUtil pfu=new PropertyFileUtil();
	ExcelUtil eu=new ExcelUtil();
	JavaUtil ju = new JavaUtil();
	public WebDriver sdriver;
	
	@Test
	public void reports() throws IOException, InterruptedException, TesseractException {
		
		
		String USERNAME=pfu.getDataFromPropertyFile("username1");
		String PASSWORD=pfu.getDataFromPropertyFile("password1");
		String URL=pfu.getDataFromPropertyFile("tl_url");	
		
		Thread.sleep(2000);
		TeamLeader tl=new TeamLeader(driver);
		tl.teamLeaderPage(driver);
		
		Thread.sleep(2000);
		String LoginPageUrl=driver.getCurrentUrl();
		System.out.println(LoginPageUrl);
		
		//login
		loginPage lp = new loginPage(driver);
		lp.login(USERNAME, PASSWORD);

		
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
						
			Thread.sleep(2000);
			TeamLeaderHomePage hp=new TeamLeaderHomePage(driver);
			//click on report
			hp.getReports().click();
			
			//click on candidate reports
			Thread.sleep(2000);
			WebElement candidateReport = driver.findElement(By.xpath("(//div[text()=\"Candidate Report\"])[1]"));
			candidateReport.click();
			
			//click on current months
			Thread.sleep(2000);
			WebElement currentMonth = driver.findElement(By.id("Last3Months"));
			currentMonth.click();
			
			//number recruiters
			Thread.sleep(2000);
			List<WebElement> recruiters = driver.findElements(By.xpath("//div[@class=\"ant-spin-container\"]/ul/li"));
			for (WebElement rec : recruiters) {
				System.out.println("RECRUITER NAME : "+rec.getText());
				
			}
			
			if (!recruiters.isEmpty()) {
				
				//select the check box 
				WebElement checkbox = driver.findElement(By.xpath("(//input[@type=\"checkbox\"])[1]"));
				checkbox.click();
				
				//click on OK
				Thread.sleep(2000);
				WebElement ok = driver.findElement(By.xpath("//span[text()=\"OK\"]"));
				ok.click();
				
				WebElement lineup_colour=driver.findElement(By.xpath("(//li[@class=\"listOfIndex\"])[2]/div"));				
				String colour1 = lineup_colour.getCssValue("background-color");
				System.out.println(colour1);
				
				WebElement Number_lineup = driver.findElement(By.xpath("(//td[@class=\"tabledata\"])[3]"));
				String number1 = Number_lineup.getText();
				System.out.println(number1);
				
				WebElement canvas = driver.findElement(By.xpath("//canvas"));

				        // JavaScript to find coordinates of a specific color in the canvas
				        JavascriptExecutor js = (JavascriptExecutor) driver;
				        @SuppressWarnings("unchecked")
						ArrayList<Number>  result = (ArrayList<Number>) js.executeScript(
						                "var canvas = document.querySelector('canvas');" +
						                "var ctx = canvas.getContext('2d');" +
						                "var imgData = ctx.getImageData(0, 0, canvas.width, canvas.height);" +
						                "var count = 0;" +
						                "for (var y = 0; y < canvas.height; y++) {" +
						                "    for (var x = 0; x < canvas.width; x++) {" +
						                "        var i = (y * canvas.width + x) * 4;" +
						                "        var r = imgData.data[i];" +
						                "        var g = imgData.data[i+1];" +
						                "        var b = imgData.data[i+2];" +
						                "        if (r == 180 && g == 180 && b == 184) { " + // Target color
						                "            count++;" +
						                "            if (count == 2) { return [x, y]; }" +  // Return second occurrence
						                "        }" +
						                "    }" +
						                "}" +
						                "return null;"
						            );
				      
				        
				        if (result != null) {
				        	Number[] coords = result.toArray(new Number[0]);
				        	int x = coords[0].intValue();
				            int y = coords[1].intValue();
				            
				            System.out.println("Second occurrence found at: x=" + x + ", y=" + y);

				            //scroll to the bottom of canvas
				            js.executeScript(
				                    "var canvas = arguments[0];" +
				                    "window.scrollTo(0, canvas.offsetTop + canvas.height);", canvas
				                );
				            

				            // Move mouse to detected color coordinates
				            Thread.sleep(2000);
				            js.executeScript(
				            	    "var evt1 = new MouseEvent('mouseenter', {clientX: arguments[0], clientY: arguments[1], bubbles: true});" +
				            	    "var evt2 = new MouseEvent('mousemove', {clientX: arguments[0], clientY: arguments[1], bubbles: true});" +
				            	    "var canvas = document.querySelector('canvas');" +
				            	    "canvas.dispatchEvent(evt1);" +
				            	    "canvas.dispatchEvent(evt2);",
				            	    x, y
				            	);


				           // actions.moveToLocation(x, y).perform();
				            
				            Thread.sleep(2000);		           
		                    File fullCanvasScreenshot = canvas.getScreenshotAs(OutputType.FILE);
		                    BufferedImage fullImg = ImageIO.read(fullCanvasScreenshot);

		                    // Define the region to crop (hovered area)
		                    int hoverSize = 500; // Capture a 50x50 px area around the hover
		                    int cropX = Math.max(0, x - hoverSize / 2);
		                    int cropY = Math.max(0, y - hoverSize / 2);
		                    int cropWidth = Math.min(hoverSize, fullImg.getWidth() - cropX);
		                    int cropHeight = Math.min(hoverSize, fullImg.getHeight() - cropY);

		                    // Crop the screenshot to get only the hovered region
		                    BufferedImage croppedImage = fullImg.getSubimage(cropX, cropY, cropWidth, cropHeight);
		                    
		                    // Take Screenshot of the Canvas
		                 
		                    File destination = new File("canvas_screenshot.png");
		                    ImageIO.write(croppedImage, "png", destination);

		                    // OCR: Extract text using Tesseract
		                    ITesseract tesseract = new Tesseract();
		                    tesseract.setDatapath("C:\\Program Files\\Tesseract-OCR\\tessdata"); // Set Tesseract OCR path
		                    tesseract.setLanguage("eng");

		                    try {
		                        String extractedText = tesseract.doOCR(destination);
		                        System.out.println("Extracted Tooltip Text: " + extractedText);
		                    } catch (TesseractException e) {
		                        System.out.println("OCR extraction failed: " + e.getMessage());
		                    }
				        } else {
				            System.out.println("Color not found in canvas!");
				        }

				 
				

				
			}else {
				
				System.out.println("no recruiter present to show report");
				
				//click on cancel
				driver.findElement(By.xpath("//span[text()=\"Cancel\"]")).click();
			
			}
			
			//logout
			Thread.sleep(1000);
			logoutPage lo=new logoutPage(driver);
			lo.logout(driver, "Yes");
		}
	}

}
