package file.ReadWriteExcel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class HashMapToExcelFile {

	public static void main(String[] args) throws IOException {
		
		XSSFWorkbook workbook=new XSSFWorkbook();
		XSSFSheet sheet= workbook.createSheet("Subject3");
		
		
		Map<String,String> data=new HashMap<String,String>();
		data.put("CS101","Computer Science");
		data.put("CS102","HTML CSS");
		data.put("CS103","JS");
//		data.put("CS104","JS Framework");
//		data.put("CS105","Project");
//		
		
		int rowno=0;
				
		
		for(Map.Entry entry:data.entrySet())
		{
//			XSSFRow row0=sheet.createRow(0);
//			row0.createCell(0).setCellValue("Code");
//			row0.createCell(1).setCellValue("Subject");
			
			XSSFRow row=sheet.createRow(rowno++);
			
			row.createCell(0).setCellValue((String)entry.getKey());
			row.createCell(1).setCellValue((String)entry.getValue());
		}
		
		
		FileOutputStream fos=new FileOutputStream(".\\subject.xlsx");
		
		workbook.write(fos); 
		fos.close();
		System.out.println("Excel written succesfully.");
		workbook.close();
	}

}
