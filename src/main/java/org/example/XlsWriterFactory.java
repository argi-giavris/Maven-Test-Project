package org.example;

import org.example.models.ConsoleWriter;
import org.example.models.Employee;
import org.example.models.FileWriter;

public class XlsWriterFactory {

    public Writer<Employee> createWriter(String userOption){

        if (userOption.equals("1")){
            return new ConsoleWriter();
        }else{
            return new FileWriter();
        }
    }
}
