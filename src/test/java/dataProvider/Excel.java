package dataProvider;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {
	static OPCPackage pkg;
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;

	static String[][] keywordsList = null;

	public static Object[][] getArray(String FilePath, String SheetName)
			throws IOException {

		try {
			pkg = OPCPackage.open(System.getProperty("user.dir") + FilePath);

			workbook = new XSSFWorkbook(pkg);
			sheet = workbook.getSheet(SheetName);

			int rowCount = sheet.getLastRowNum();
			keywordsList = new String[rowCount][1];
			for (int i = 0; i <rowCount; i++) {
				Row row = sheet.getRow(i);
				Cell cell = row.getCell(0);

				keywordsList[i][0] = getCellValueAsString(cell);
			}

			workbook.close();
	
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		}

		return keywordsList;

	}
	
	
	

	public static String addinExcel(String FilePath, String SheetName,
			Map<String, String> result) {
		try {
			workbook = new XSSFWorkbook();
			sheet = workbook.createSheet(SheetName);
			int rowno = 0;

			for (Map.Entry entry : result.entrySet()) {
				XSSFRow row = sheet.createRow(rowno++);
				row.createCell(0).setCellValue((String) entry.getKey());
				row.createCell(1).setCellValue((String) entry.getValue());
			}

			FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir") +FilePath);

			workbook.write(fos);
			fos.close();
			System.out.println("Excel written succesfully.");
			workbook.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

		return "Excel File done";

	}

	public static String getCellValueAsString(Cell cell) {
		String cellValue = null;
		switch (cell.getCellType()) {
			case NUMERIC :
				cellValue = String.valueOf(cell.getNumericCellValue());
				break;
			case STRING :
				cellValue = cell.getStringCellValue();
				break;
			case BOOLEAN :
				cellValue = String.valueOf(cell.getBooleanCellValue());
				break;
			case FORMULA :
				cellValue = cell.getCellFormula();
				break;
			case BLANK :
				cellValue = "BLANK";
				break;
			default :
				cellValue = "DEFAULT";
				break;
		}
		return cellValue;
	}
}
