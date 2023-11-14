package org.example.models;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.example.RowMapper;
import org.example.models.Employee;

public class EmployeeRowMapper implements RowMapper<Employee> {

    @Override
    public Employee mapRow(Row row) {
        Employee employee = new Employee();

        // Assuming the first cell contains the name, the second cell contains the id, and the third cell contains the department
        Cell nameCell = row.getCell(0);
        Cell idCell = row.getCell(1);
        Cell departmentCell = row.getCell(2);

        if (nameCell != null) {
            employee.setName(nameCell.getStringCellValue());
        }

        if (idCell != null) {
            employee.setId((int) idCell.getNumericCellValue());
        }

        if (departmentCell != null) {
            employee.setDepartment(departmentCell.getStringCellValue());
        }

        return employee;
    }
}
