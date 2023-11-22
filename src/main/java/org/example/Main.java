package org.example;

import org.example.models.*;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        ArgumentParser argumentParser = new ArgumentParser();
        UserInput userInput = argumentParser.getUserInput();

        XlsReaderFactory readerFactory = new XlsReaderFactory();
        XlsReader reader = readerFactory.createReader(userInput.getReaderOption());

        XlsWriterFactory writerFactory = new XlsWriterFactory();
        Writer<Employee> writer = writerFactory.createWriter(userInput.getWriterOption());

        RowMapper<Employee> employeeRowMapper = new EmployeeRowMapper();

        Util util = new Util();
        util.readAndWrite(userInput.getFilePath(), reader, employeeRowMapper, writer, "Name","Employee Number", "Department");
    }
}
