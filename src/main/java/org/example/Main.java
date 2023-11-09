package org.example;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) throws IOException {

        ClassLoader classLoader = Main.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("Maven.xlsx");

        if (inputStream == null) {
            throw new IOException("Resource not found: Maven.xlsx");
        }
        XSSFWorkbook wb = new XSSFWorkbook(inputStream);

        Sheet sheet = wb.getSheetAt(0);

        FormulaEvaluator formulaEvaluator = wb.getCreationHelper().createFormulaEvaluator();

        for (Row row : sheet) {
            for (Cell cell : row) {
                switch (cell.getCellType()) {
                    case NUMERIC:
                        System.out.print(cell.getNumericCellValue() + "\t\t");
                        break;
                    case STRING:
                        System.out.print(cell.getStringCellValue() + "\t\t");
                        break;
                    // Handle other cell types as needed
                }
            }
            System.out.println(); // Newline after each row
        }

        wb.close();
    }
}
