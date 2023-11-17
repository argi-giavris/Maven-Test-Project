package org.example;

import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.models.*;
import org.example.models.FileWriter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.net.URL;
import java.util.zip.ZipInputStream;

public class Util {

    private static final int ZIP_FILE_HEADER_SIZE = 4;
    private static final byte[] ZIP_FILE_HEADER = {0x50, 0x4B, 0x03, 0x04}; // ZIP file header

    public <T> void readAndWrite(String filePath, XlsReader reader, RowMapper<T> mapper, Writer<T> writer, String... columns) {


        try (InputStream file = reader.read(filePath)) {
            final XlsParser parser = new XlsParser();
            List<T> employeeList = parser.readFile(file,mapper,columns);
            writer.write(employeeList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getUserInput(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Choose an option:\n1. Enter file path from the console\n2. Download file from the internet\nOption: ");
        String option = scanner.nextLine();



        List<String> userInput = new ArrayList<>();

        switch (option) {
            case "1","2" -> {
                userInput.add(option);
            }
            default -> throw new IllegalArgumentException("Invalid option");
        }

        if (userInput.get(0).equals("1")){
            System.out.print("Enter the Excel file path: ");
        }else {
            System.out.print("Enter the URL to download the file: ");
        }
        String filePath = scanner.nextLine();


        userInput.add(filePath);
        System.out.print("Choose an option:\n1. Print on console\n2. Print on file\nOption: ");
        option = scanner.nextLine();

        switch (option) {
            case "1","2" -> {
                userInput.add(option);
            }
            default -> throw new IllegalArgumentException("Invalid option");
        }

        return userInput;
    }

    public XlsReader createReader(String userOption){

        if (userOption.equals("1")){        //local path
            return new XlsFileReader();
        }else{                          //url link
            return new XlsUrlReader();
        }
    }

    public Writer<Employee> createWriter(String userOption){

        if (userOption.equals("1")){
            return new ConsoleWriter();
        }else{
            return new FileWriter();
        }
    }


    public boolean isExcelFile(Path filePath) throws IOException {
        // Open the file with a ZipInputStream to check for the ZIP file header
        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(filePath.toFile()))) {
            byte[] header = new byte[ZIP_FILE_HEADER_SIZE];
            zipInputStream.read(header);
            return Arrays.equals(header, ZIP_FILE_HEADER);
        }
    }
}