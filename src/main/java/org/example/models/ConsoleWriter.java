package org.example.models;

import org.example.Writer;
import java.util.List;

public class ConsoleWriter implements Writer<Employee>  {

    @Override
    public void write(List<Employee> employeeList) {
        for (Employee e : employeeList) {
            System.out.println(e.getName() + ' ' + e.getId() + ' ' + e.getDepartment());
        }
    }
}
