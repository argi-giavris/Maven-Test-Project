package org.example.models;

public class Employee
{
    String name;
    Integer id;
    String department;

    public Employee(String name, Integer id, String department) {
        this.name = name;
        this.id = id;
        this.department = department;
    }

    public Employee(){}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }


}
