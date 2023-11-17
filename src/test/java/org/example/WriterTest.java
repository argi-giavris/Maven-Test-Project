package org.example;

import org.example.models.ConsoleWriter;
import org.example.models.Employee;
import org.example.models.FileWriter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import java.io.ByteArrayOutputStream;

public class WriterTest {

    @Test
    void testConsoleWriter() {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Create a list of employees
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("John", 1234, "HR"));
        employeeList.add(new Employee("Alice", 2345, "IT"));

        ConsoleWriter consoleWriter = new ConsoleWriter();
        consoleWriter.write(employeeList);
        System.setOut(System.out);


        String expectedOutput = "John 1234 HR" + System.lineSeparator() + "Alice 2345 IT" + System.lineSeparator();
        Assertions.assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    void testFileWriter() throws  IOException {

        Path tempFilePath = Files.createTempFile("testOutput", ".txt");

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("John", 1234, "HR"));
        employeeList.add(new Employee("Alice", 2345, "IT"));

        FileWriter fileWriter = new FileWriter();

        fileWriter.write(employeeList);

        List<String> fileContent = Files.readAllLines(Path.of("output.txt"));

        //fileContent.forEach(System.out::println);
        Assertions.assertEquals("Name, ID, Department", fileContent.get(0));
        Assertions.assertEquals("John, 1234, HR", fileContent.get(1));
        Assertions.assertEquals("Alice, 2345, IT", fileContent.get(2));

    }
}
