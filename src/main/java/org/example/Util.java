package org.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.models.Employee;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Util {

    public List<Employee> read() throws IOException{

        try (Scanner scanner = new Scanner(System.in)){

            System.out.print("Enter the Excel file path: "); // C:\Users\argig\Desktop\Maven.xlsx

            String filePath = scanner.nextLine();

            File file = new File(filePath);

            if (!file.exists()) {
                throw new RuntimeException("File not found: " + filePath);
            }

            try (FileInputStream fileInputStream = new FileInputStream(file);
                 XSSFWorkbook wb = new XSSFWorkbook(fileInputStream)) {

                Sheet sheet = wb.getSheetAt(0);

                if (excelHasCorrectFormat(sheet)) {

                    List<Employee> employees = new ArrayList<>();

                    DataFormatter dataFormatter = new DataFormatter();

                    for (int n = 1; n < sheet.getPhysicalNumberOfRows(); n++) {
                        Row row = sheet.getRow(n);
                        Employee employeeInfo = new Employee();
                        int i = row.getFirstCellNum();

                        employeeInfo.setName(dataFormatter.formatCellValue(row.getCell(i)));
                        employeeInfo.setId(dataFormatter.formatCellValue(row.getCell(++i)));
                        employeeInfo.setDepartment(dataFormatter.formatCellValue(row.getCell(++i)));


                        employees.add(employeeInfo);
                    }

                    return employees;

                }else{
                    throw new RuntimeException("Wrong excel format");
                }
            }
        }

    }

    public void write(List<Employee> employeeList){

        for (Employee e: employeeList) {
            System.out.println(e.getName() + ' ' + e.getId() + ' ' + e.getDepartment());
        }


    }

    public boolean excelHasCorrectFormat(Sheet sheet){
        String[] expectedHeaders = {"Name", "Employee Number", "Department"};

        Row headerRow = sheet.getRow(0);

        if (headerRow != null) {
            for (int i = 0; i < expectedHeaders.length; i++) {
                Cell cell = headerRow.getCell(i);
                if (cell != null) {
                    String actualHeader = cell.getStringCellValue();
                    if (!expectedHeaders[i].equals(actualHeader)) {
                        System.out.println("Header mismatch at column" + i);
                        return false;
                    }
                } else {
                    System.out.println("Header mismatch at column" + i);
                    return false;
                }
            }
        } else {
            System.out.println("Header row is missing");
            return false;
        }
        return true;
    }

}
