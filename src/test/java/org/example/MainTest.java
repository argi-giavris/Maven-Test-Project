package org.example;


import org.example.models.Employee;
import org.example.Util;
import org.example.models.EmployeeRowMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    Util util ;

    void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }


    @BeforeEach
    void setUp() {
        util = new Util();

    }

    @Test
    void testExcelFileExists() throws IOException {

        provideInput("C:\\Users\\argig\\Desktop\\Maven.xlsx");
        FileInputStream file = util.openFileFromConsole();
        assertNotNull(file);

    }


    @Test
    void testExcelRead() throws IOException {
        provideInput("C:\\Users\\argig\\Desktop\\Maven.xlsx");
        try (FileInputStream file = util.openFileFromConsole()) {
            RowMapper<Employee> employeeRowMapper = new EmployeeRowMapper();
            List<Employee> employeeList = util.readFile(file, employeeRowMapper);
            assertNotNull(employeeList);
        }
    }

    @Test
    void expectedNumberOfRows() throws IOException{
        provideInput("C:\\Users\\argig\\Desktop\\Maven.xlsx");
        try (FileInputStream file = util.openFileFromConsole()) {
            RowMapper<Employee> employeeRowMapper = new EmployeeRowMapper();
            List<Employee> employeeList = util.readFile(file, employeeRowMapper);
            assertNotNull(employeeList);
            assertEquals(10, employeeList.size(), "Expected size of the list is 10");
        }
    }

}


