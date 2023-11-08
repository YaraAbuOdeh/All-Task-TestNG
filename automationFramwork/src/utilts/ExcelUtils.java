package utilts;

import java.io.FileInputStream;

import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;

import org.apache.poi.ss.usermodel.Row;



public class ExcelUtils {
	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static org.apache.poi.ss.usermodel.Cell Cell;
	private static XSSFRow Row;
	private static MissingCellPolicy xRow;

	public static void setExcelFile(String Path,String SheetName) throws Exception {
		try {
			FileInputStream ExcelFile =new FileInputStream(Path);
			ExcelWBook =new XSSFWorkbook(ExcelFile);
			ExcelWSheet=ExcelWBook.getSheet(SheetName);
		}catch(Exception e) {
			throw(e);
		}
	}
	public static String getCellData(int RowNum,int ColNum) throws Exception{
		try {
			Cell=ExcelWSheet.getRow(RowNum).getCell(ColNum);
			String CellData =Cell.getStringCellValue();
			return CellData;

		}catch(Exception e) {
			return " ";
		}}

	public static void setCellData(String Result ,int RowNum ,int ColNum )throws Exception{
		try {
			Row row =ExcelWSheet.getRow(RowNum);
			//backkkkk
			Cell=row.getCell(ColNum,xRow.RETURN_BLANK_AS_NULL);
			//Cell=Row.getCell(ColNum, null);
			if(Cell==null) {
				Cell =row.createCell(ColNum);
				Cell.setCellValue(Result);
			}else {
				Cell.setCellValue(Result);

			}
			FileOutputStream fileOut=new FileOutputStream(Constant.Path_TestData);
			ExcelWBook.write(fileOut);
			fileOut.flush();
			fileOut.close();

		}catch(Exception e) {
			throw(e);
		}
	}


}

