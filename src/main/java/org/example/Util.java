package org.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.models.Employee;
import org.example.models.EmployeeRowMapper;

import java.io.*;
import java.nio.channels.Channels;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.net.URL;
import java.nio.channels.ReadableByteChannel;
import java.util.zip.ZipInputStream;

public class Util {

    private static final int ZIP_FILE_HEADER_SIZE = 4;
    private static final byte[] ZIP_FILE_HEADER = {0x50, 0x4B, 0x03, 0x04}; // ZIP file header

    public void readAndWrite() {
        Util util = new Util();

        try (FileInputStream file = util.openFile()) {
            RowMapper<Employee> employeeRowMapper = new EmployeeRowMapper();
            List<Employee> employeeList = util.readFile(file, employeeRowMapper);
            util.write(employeeList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public FileInputStream openFile() throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Choose an option:\n1. Enter file path from the console\n2. Download file from the internet\nOption: ");
        int option = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        return switch (option) {
            case 1 -> openFileFromConsole();
            case 2 -> downloadFileFromInternet();
            default -> throw new IllegalArgumentException("Invalid option");
        };
    }

    public FileInputStream openFileFromConsole() throws IOException {
        System.out.print("Enter the Excel file path: ");
        String filePath = new Scanner(System.in).nextLine();

        File file = new File(filePath);

        if (!file.exists()) {
            throw new RuntimeException("File not found: " + filePath);
        }

        try {
            return new FileInputStream(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private FileInputStream downloadFileFromInternet() throws IOException {
        System.out.print("Enter the URL to download the file: ");
        String fileUrl = new Scanner(System.in).nextLine();

        URL url = new URL(fileUrl);

        Path tempFilePath = Files.createTempFile("downloadedFile", ".tmp");
        try (InputStream inputStream = url.openStream()) {
            Files.copy(inputStream, tempFilePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Error downloading file from the internet", e);
        }

        // Check if the file is a valid Excel file
        if (isExcelFile(tempFilePath)) {
            // Convert the temporary file to a FileInputStream
            return new FileInputStream(tempFilePath.toFile());
        } else {
            throw new RuntimeException("The downloaded file is not a valid Excel file.");
        }
    }

    private boolean isExcelFile(Path filePath) throws IOException {
        // Open the file with a ZipInputStream to check for the ZIP file header
        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(filePath.toFile()))) {
            byte[] header = new byte[ZIP_FILE_HEADER_SIZE];
            zipInputStream.read(header);
            return Arrays.equals(header, ZIP_FILE_HEADER);
        }
    }

    public <T> List<T> readFile(FileInputStream file, RowMapper<T> rowMapper) throws IOException {


        try (XSSFWorkbook wb = new XSSFWorkbook(file)) {

            Sheet sheet = wb.getSheetAt(0);

            if (excelHasCorrectFormat(sheet)) {

                List<T> dataList = new ArrayList<>();

                for (int n = 1; n < sheet.getPhysicalNumberOfRows(); n++) {
                    Row row = sheet.getRow(n);
                    T data = rowMapper.mapRow(row);
                    dataList.add(data);
                }
                file.close();
                return dataList;
            } else {
                file.close();
                throw new RuntimeException("Wrong excel format");
            }
        }
    }

    public void write(List<Employee> employeeList) throws IOException {

        System.out.print("Choose an option:\n1. Print on console\n2. Print on file\nOption: ");
        Scanner scanner = new Scanner(System.in);

        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1 -> printOnConsole(employeeList);
            case 2 -> writeOnFile(employeeList);
            default -> throw new IllegalArgumentException("Invalid option");
        }
    }

    public void printOnConsole(List<Employee> employeeList) {
        for (Employee e : employeeList) {
            System.out.println(e.getName() + ' ' + e.getId() + ' ' + e.getDepartment());
        }
    }

    public void writeOnFile(List<Employee> employeeList) throws IOException {
        String filePath = "output.txt";
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {


            writer.println("Name, ID, Department");

            for (Employee employee : employeeList) {
                writer.println(employee.getName() + ", " + employee.getId() + ", " + employee.getDepartment());
            }
        }

    }

    public boolean excelHasCorrectFormat(Sheet sheet) {
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
