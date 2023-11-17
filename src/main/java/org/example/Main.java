package org.example;

import org.example.models.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        Util util = new Util();
        List<String> userInput = util.getUserInput();
        XlsReader reader = util.createReader(userInput.get(0));
        Writer<Employee> writer = util.createWriter(userInput.get(2));
        RowMapper<Employee> employeeRowMapper = new EmployeeRowMapper();
        util.readAndWrite(userInput.get(1), reader, employeeRowMapper, writer, "Name","Employee Number", "Department");
    }
}
