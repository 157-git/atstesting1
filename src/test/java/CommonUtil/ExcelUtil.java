package CommonUtil;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {

	public String getDataFromExcel(String SheetName,int RowNum,int ColNum) throws EncryptedDocumentException, IOException {
		FileInputStream file=new FileInputStream("src\\test\\resources\\shortListed.xlsx");
		Workbook wbf=WorkbookFactory.create(file);
		Sheet sheet = wbf.getSheet(SheetName);
		Row row = sheet.getRow(RowNum);
		Cell column = row.getCell(ColNum);
		String value=column.getStringCellValue();
		return value;	
	}
}
