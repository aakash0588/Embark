package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;

public class ExcelUtils {
    private static Workbook workbook;
    private static Sheet sheet;
    private static String filePath;

    public static void setExcelFile(String path, String sheetName) throws Exception {
        filePath = path;
        FileInputStream file = new FileInputStream(path);
        workbook = new XSSFWorkbook(file);
        sheet = workbook.getSheet(sheetName);
    }

    public static String getCellData(int row, int col) {
        return sheet.getRow(row).getCell(col).toString();
    }

    public static void setTestStatus(int row, int col, String status) throws Exception {
        Row resultRow = sheet.getRow(row);
        if (resultRow == null) {
            resultRow = sheet.createRow(row);
        }
        Cell cell = resultRow.getCell(col);
        if (cell == null) {
            cell = resultRow.createCell(col);
        }
        cell.setCellValue(status);

        FileOutputStream outFile = new FileOutputStream(filePath);
        workbook.write(outFile);
        outFile.close();
    }
}
