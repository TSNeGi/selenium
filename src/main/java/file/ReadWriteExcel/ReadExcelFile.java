package file.ReadWriteExcel;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;


public class ReadExcelFile {
	public static void main(String[] args) {

		try {
			
			File file = new File("C:\\Users\\tanegi\\Documents\\readExcel.xlsx");
//			OPCPackage pkg = OPCPackage.open("C:\\Users\\tanegi\\Documents\\readExcel.xlsx");
			FileInputStream fis = new FileInputStream(file); 
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet = wb.getSheetAt(0); 
			Iterator<Row> itr = sheet.iterator(); 
			
//			HashMap<Integer, HashMap<String, String>> ExcelData = new HashMap<Integer, HashMap<String, String>>();
			
			while (itr.hasNext()) {
				
				
				Row row = itr.next();
				Iterator<Cell> cellIterator = row.cellIterator(); 
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					
					
					switch (cell.getCellType()) {
					case STRING:
						System.out.print(cell.getStringCellValue() + "\t\t\t");
						break;
					case NUMERIC:
						System.out.print(cell.getNumericCellValue() + "\t\t\t");
						break;
//					case BOOLEAN:
//						System.out.print(cell.getNumericCellValue() + "\t\t\t");
//						break;
					default:
					}
				}
				System.out.println("");
			}
			wb.close();
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	
	}
}
