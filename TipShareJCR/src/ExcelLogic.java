import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ExcelLogic {
	
	private static File FILE_NAME = new File("C:\\Users\\Ashlee\\eclipse-workspace\\TipShareJCR\\Employees.xlsx");
	
	public void AddEmployee(String jobTitle, String employeeName) throws IOException, InvalidFormatException{
		
		FileInputStream input = new FileInputStream(FILE_NAME);
		OPCPackage pkg = OPCPackage.open(input);
		XSSFWorkbook  wb = new XSSFWorkbook(pkg);
		XSSFSheet sheet = wb.getSheet(jobTitle);
		int numOfRows = sheet.getLastRowNum();
			
			if(sheet.getRow(0) == null) {
				
				XSSFRow row = sheet.createRow(0);
					row.createCell(0).setCellValue(new XSSFRichTextString(employeeName));
			
			}else {

				Row row = sheet.createRow(++numOfRows);
					row.createCell(0).setCellValue(new XSSFRichTextString(employeeName));
			}
			
		FileOutputStream fileOut = new FileOutputStream(FILE_NAME);
			wb.write(fileOut);
			fileOut.flush();
			fileOut.close();
			pkg.close();
			wb.close();
		
	}
	
	public void deleteEmployee(String jobTitle, String employeeName) throws IOException, InvalidFormatException{
		
		FileInputStream input = new FileInputStream(FILE_NAME);
		OPCPackage pkg = OPCPackage.open(input);
		XSSFWorkbook wb = new XSSFWorkbook(pkg);
		XSSFSheet sheet = wb.getSheet(jobTitle);
		int numOfRows = sheet.getLastRowNum();
		
		for(int i = 0; i <= numOfRows; i++) {
			
			XSSFRow row = sheet.getRow(i);
			
			if(row.getCell(0).toString().equals(employeeName)) {
				
				row.getCell(0).setCellValue("");
			}	
		}
		
		FileOutputStream fileOut = new FileOutputStream(FILE_NAME);
			wb.write(fileOut);
			fileOut.flush();
			fileOut.close();
			pkg.close();
			wb.close();
	}
	
	public void removeEmptyRows(String jobTitle) throws IOException, InvalidFormatException{
		
		FileInputStream input = new FileInputStream(FILE_NAME);
		OPCPackage pkg = OPCPackage.open(input);
		XSSFWorkbook wb = new XSSFWorkbook(pkg);
		XSSFSheet sheet = wb.getSheet(jobTitle);
		
		int numOfRows = sheet.getLastRowNum();
		
		for(int i = 0; i < numOfRows; i++) {

			XSSFRow row = sheet.getRow(i);
			
			if(row.getCell(0).toString().trim().equals("")) {
				
				if(sheet.getRow(i + 1) != null) {
					
					XSSFRow rowBelow = sheet.getRow(i + 1);
					String rowBelowString = rowBelow.getCell(0).getStringCellValue();
					XSSFRichTextString theString = new XSSFRichTextString(rowBelowString);
					
					row.getCell(0).setCellValue(theString);
					numOfRows = sheet.getLastRowNum();
					
					if(sheet.getRow(i).getRowNum() < numOfRows) {
						
						rowBelow.getCell(0).setCellValue("");
					
				}
			}				
		}	
	}
		sheet.removeRow(sheet.getRow(numOfRows));
		
	    FileOutputStream fileOut = new FileOutputStream(FILE_NAME);
			wb.write(fileOut);
			fileOut.flush();
			fileOut.close();
			pkg.close();
			wb.close();

} 
	public static ArrayList<String> read(String theSheet) throws IOException, InvalidFormatException {
		
		ArrayList<String>empNames = new ArrayList<String>();
		FileInputStream input = new FileInputStream(FILE_NAME);
	    OPCPackage pkg = OPCPackage.open(input);
	    XSSFWorkbook wb = new XSSFWorkbook(pkg);
	    XSSFSheet sheet = wb.getSheet(theSheet);
	    Iterator<Row> iterator = sheet.iterator();

	    while (iterator.hasNext()) {
	
	    	Row currentRow = iterator.next();
	    	Iterator<Cell> cellIterator = currentRow.iterator();

	        while (cellIterator.hasNext()) {

	        	Cell currentCell = cellIterator.next();
	        	empNames.add(currentCell.toString());
	         }
	    }
	    
	    FileOutputStream fileOut = new FileOutputStream(FILE_NAME);
		wb.write(fileOut);
		fileOut.flush();
		fileOut.close();
		pkg.close();
		wb.close();
		
		return empNames;  		
	}	
	
public void exportDailyTipshare(ObservableList<Person>theList) throws IOException, InvalidFormatException{
		
		FileInputStream input = new FileInputStream(FILE_NAME);
		OPCPackage pkg = OPCPackage.open(input);
		XSSFWorkbook  wb = new XSSFWorkbook(pkg);
		XSSFSheet sheet = wb.getSheet("Tipshare");
			
		if(sheet.getRow(1).getCell(0) == null || sheet.getRow(1).getCell(0).getCellType() == org.apache.poi.ss.usermodel.CellType.BLANK || 
		(sheet.getRow(1).getCell(0).getCellType().equals(org.apache.poi.ss.usermodel.CellType.STRING) && sheet.getRow(1).getCell(0).getStringCellValue().trim().isEmpty())) {
			
			for(int i = 0; i < theList.size(); i++) {
				XSSFRow row = sheet.createRow(i + 1);
				row.createCell(0).setCellValue(new XSSFRichTextString(theList.get(i).getDepartment()));
				row.createCell(1).setCellValue(new XSSFRichTextString(theList.get(i).getFirstName()));
				row.createCell(2).setCellValue(theList.get(i).getHoursWorked());
				row.createCell(3).setCellValue(theList.get(i).getDailyTipout());			
			}
			
			}else {
				
				for(int i = 0; i < theList.size(); i++) {
					
					XSSFRow row = sheet.getRow(i + 1);
						row.getCell(0).setCellType(org.apache.poi.ss.usermodel.CellType.STRING);
						row.getCell(0).setCellValue(new XSSFRichTextString(theList.get(i).getDepartment()));
						row.getCell(1).setCellType(org.apache.poi.ss.usermodel.CellType.STRING);
						row.getCell(1).setCellValue(new XSSFRichTextString(theList.get(i).getFirstName()));
						row.getCell(2).setCellType(org.apache.poi.ss.usermodel.CellType.NUMERIC);
						row.getCell(2).setCellValue(theList.get(i).getHoursWorked());
						row.getCell(3).setCellType(org.apache.poi.ss.usermodel.CellType.NUMERIC);
						row.getCell(3).setCellValue(theList.get(i).getDailyTipout());
							
					}
				}
						
		FileOutputStream fileOut = new FileOutputStream(FILE_NAME);
			wb.write(fileOut);
			fileOut.flush();
			fileOut.close();
			pkg.close();
			wb.close();
		
	}

	public static void generateDailyReport() throws IOException, InvalidFormatException {
	
		ArrayList<String>tipoutArray = new ArrayList<String>();
		FileInputStream input = new FileInputStream(FILE_NAME);
		OPCPackage pkg = OPCPackage.open(input);
		XSSFWorkbook wb = new XSSFWorkbook(pkg);
		XSSFSheet sheet = wb.getSheet("Tipshare");
		Iterator<Row> iterator = sheet.iterator();
		ObservableList<Person>tipoutList = FXCollections.observableArrayList();

		while (iterator.hasNext()) {
		
			Row currentRow = iterator.next();
			
			if(currentRow.getRowNum() != 0) {
				
				Iterator<Cell> cellIterator = currentRow.iterator();
				tipoutArray.clear();
				
				while (cellIterator.hasNext()) {
					
					Cell currentCell = cellIterator.next();
					String currentCellValue = currentCell.toString();
					tipoutArray.add(currentCellValue);	
				}
			
			}
			
			if(currentRow.getRowNum() != 0) {
			
				Person theEmployee = new Person(tipoutArray.get(0), tipoutArray.get(1), Double.parseDouble(tipoutArray.get(2)), Double.parseDouble(tipoutArray.get(3)));
				tipoutList.add(theEmployee);
			
			}
		}
    
		PopulateDailyTipoutTableView tableViewObj = new PopulateDailyTipoutTableView();
		tableViewObj.displayResults(tipoutList);
				
			pkg.close();
			wb.close();
  		
	}	
}
	

