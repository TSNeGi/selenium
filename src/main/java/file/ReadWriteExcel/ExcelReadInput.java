package file.ReadWriteExcel;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReadInput {
	public static void main(String[] args) throws IOException, InvalidFormatException {

//		FileInputStream pkg=new FileInputStream("C:\\Users\\tanegi\\Documents\\readExcel.xlsx");
		
		OPCPackage pkg = OPCPackage.open("C:\\Users\\tanegi\\Documents\\readExcel.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(pkg);
		XSSFSheet sheet = workbook.getSheet("Student");
		
		Map<String, Map<String, String>> data = new HashMap<String, Map<String, String>>();
		List<String> columnHeader = new ArrayList<String>();

		Row row = sheet.getRow(0);
		int rowCount = sheet.getLastRowNum();
		int columnCount = row.getLastCellNum();

		for (int j = 0; j < columnCount; j++) {
			Cell cell = row.getCell(j);
			columnHeader.add(getCellValueAsString(cell));
		}

//      Iterator cellIterator = row.cellIterator();
//      while (cellIterator.hasNext()) {
//      	
//          columnHeader.add( ((Cell) cellIterator.next()).getStringCellValue());
//      }

		for (int i = 1; i <= rowCount; i++) {
			Map<String, String> singleRowData = new HashMap<String, String>();
			Row row1 = sheet.getRow(i);
			for (int j = 0; j < columnCount; j++) {
				Cell cell = row1.getCell(j);

				singleRowData.put(columnHeader.get(j), getCellValueAsString(cell));
			}
			data.put(String.valueOf(i), singleRowData);
		}

//        System.out.println(data);
		System.out.print("Enter row number : ");
		Scanner scan = new Scanner(System.in);
		String runCount = scan.nextLine();
		if (data.get(runCount) != null) {
			System.out.println(runCount + " : " + data.get(runCount));
		} else {
			System.out.println("No records.\nOnly have " + rowCount + " rows.");
		}
		scan.close();
		pkg.close();
		workbook.close();
	}
	
	public static String getCellValueAsString(Cell cell) {
		String cellValue = null;
		switch (cell.getCellType()) {
		case NUMERIC:
			cellValue = String.valueOf(cell.getNumericCellValue());
			break;
		case STRING:
			cellValue = cell.getStringCellValue();
			break;
		case BOOLEAN:
			cellValue = String.valueOf(cell.getBooleanCellValue());
			break;
		case FORMULA:
			cellValue = cell.getCellFormula();
			break;
		case BLANK:
			cellValue = "BLANK";
			break; 
		default:
			cellValue = "DEFAULT";
			break;
		}
		return cellValue;
	}
}
