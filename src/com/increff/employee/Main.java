package com.increff.employee;

import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        System.out.println("Hello, World!");
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/increff", "root", "root");
        delete(con);
        insert(con);
        select(con);
        delete(con);
        select(con);
        con.close();
    }

    private static void insert(Connection con) throws SQLException {
        System.out.println("Inserting Employee");
        Statement stat = con.createStatement();
        for (int i = 0; i < 3; i++) {
            int id = i;
            int age = 20 + i;
            String name = "Kishan";
            String query = "insert into employee values(" + id + "," + '"' + name + '"' + "," + age + ")";
            System.out.println(query);
            stat.executeUpdate(query);
        }
        stat.close();
    }

    private static void select(Connection con) throws SQLException {
        System.out.println("Selecting employees");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from employee");
        while (rs.next())
            System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getInt(3));
        stmt.close();
    }

    private static void delete(Connection con) throws SQLException {
        System.out.println("deleting all employee");
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery("select * from employee");
        List<Integer> idList = new ArrayList<Integer>();
        while (rs.next()) {
            idList.add(rs.getInt(1));
        }
        for (int i = 0; i < idList.size(); i++) {
            stat.executeUpdate("delete from employee where id = " + idList.get(i));
        }
        stat.close();
    }
}
