package org.example;

import org.example.models.*;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        ArgumentParser argumentParser = new ArgumentParser();
        List<String> userInput = argumentParser.getUserInput();

        XlsReaderFactory readerFactory = new XlsReaderFactory();
        XlsReader reader = readerFactory.createReader(userInput.get(0));

        XlsWriterFactory writerFactory = new XlsWriterFactory();
        Writer<Employee> writer = writerFactory.createWriter(userInput.get(2));

        RowMapper<Employee> employeeRowMapper = new EmployeeRowMapper();

        Util util = new Util();
        util.readAndWrite(userInput.get(1), reader, employeeRowMapper, writer, "Name","Employee Number", "Department");
    }
}
