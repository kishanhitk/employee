package com.increff.employee;


import org.junit.Test;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import static junit.framework.TestCase.assertEquals;


public class EmployeeTest {
    @Test
    public void sayHello() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Hello!");
        EmployeeJdbcAPI employeeAPI = new EmployeeJdbcAPI();
        employeeAPI.delete();
        employeeAPI.insert();
        ResultSet rs = employeeAPI.select();
        int i = 0;
        while (rs.next()) {
            i++;
        }
        assertEquals(3, i);

    }
}
