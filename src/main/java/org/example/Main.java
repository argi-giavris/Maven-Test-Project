package org.example;
import org.example.models.Employee;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        Util util = new Util();

        List<Employee> employeeList = util.read();
        util.write(employeeList);

    }
}
