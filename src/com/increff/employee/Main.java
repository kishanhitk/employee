package com.increff.employee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;

public class Main {
    public  static void main(String[] args) throws ClassNotFoundException, SQLException {
        System.out.println("Hello, World!");
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con=DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/increff","root","root");

        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("select * from employee");
        while(rs.next())
            System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getInt(3));
        con.close();
    }
}
