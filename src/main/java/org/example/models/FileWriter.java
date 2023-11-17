package org.example.models;

import org.example.Writer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class FileWriter implements Writer<Employee> {

    @Override
    public void write(List<Employee> employeeList) throws IOException {
        String filePath = "output.txt";
        try (PrintWriter writer = new PrintWriter(new java.io.FileWriter(filePath))) {


            writer.println("Name, ID, Department");

            for (Employee employee : employeeList) {
                writer.println(employee.getName() + ", " + employee.getId() + ", " + employee.getDepartment());
            }
        }
    }
}
