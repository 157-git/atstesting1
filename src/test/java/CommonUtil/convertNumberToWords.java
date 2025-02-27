package CommonUtil;

public class convertNumberToWords {
			
	    private static final String[] units = {
	        "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", 
	        "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", 
	        "Eighteen", "Nineteen"
	    };

	    private static final String[] tens = {
	        "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
	    };

	    public static String convertToIndianWords(long number) {
	        if (number == 0) {
	            return "Zero";
	        }

	        StringBuilder words = new StringBuilder();

	        // Extract parts
	        long lakh = number / 100000;
	        number %= 100000;
	        long thousand = number / 1000;
	        number %= 1000;
	        long hundred = number / 100;
	        number %= 100;

	        // Construct the words
	        if (lakh > 0) {
	            words.append(lakh).append(" lakh ");
	        }
	        if (thousand > 0) {
	            words.append(thousand).append(" thousand ");
	        }
	        if (hundred > 0) {
	            words.append(hundred).append(" hundred ");
	        }

	        return words.toString().trim();
	    }
	

}
