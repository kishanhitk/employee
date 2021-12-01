package com.increff.employee;

import java.io.IOException;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        HibernateUtil.configure();
        EmployeeHibernateAPI employeeHibernateAPI = new EmployeeHibernateAPI();

        employeeHibernateAPI.deleteAll();

        for (int i = 0; i < 5; i++) {
            EmployeePojo pojo = new EmployeePojo();
            pojo.setAge(i + 20);
            pojo.setId(i);
            pojo.setName("kishan" + i);
            employeeHibernateAPI.insert(pojo);
        }

        employeeHibernateAPI.printAll();
    }
}
