package CommonUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class JavaUtil {

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
	
	public int getRandomNumber() {
		Random r=new Random();
		int ran = r.nextInt(1000);
		
		return ran;
	}
	
	public String dateAndTime() {
		LocalDateTime now = LocalDateTime.now();
        String formattedDateTime = now.format(formatter);
		return formattedDateTime;
	}
	
	

	
}
