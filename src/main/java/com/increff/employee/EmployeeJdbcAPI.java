package com.increff.employee;

import javax.swing.plaf.nimbus.State;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

public class EmployeeJdbcAPI {
    private static final Logger logger = Logger.getLogger("MainLogger");
    private Connection con;

    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {

//        delete(con);
//        insert(con);
//        select(con);
//        delete(con);
//        select(con);
//        con.close();
    }

    public EmployeeJdbcAPI() throws IOException, SQLException, ClassNotFoundException {
        logger.info("Hello, World!");
        Properties props = new Properties();
        InputStream inputStream = new FileInputStream("employee.properties");
        props.load(inputStream);
        Class.forName(props.getProperty("jdbc.driver"));
        con = DriverManager.getConnection(props.getProperty("jdbc.url"),
                props.getProperty("jdbc.username"), props.getProperty("jdbc.password"));
    }

    public void insert() throws SQLException {
        logger.info("Inserting Employee");
        Statement stat = con.createStatement();
        for (int i = 0; i < 3; i++) {
            int id = i;
            int age = 20 + i;
            String name = "Kishan";
            String query = "insert into employee values(" + id + "," + '"' + name + '"' + "," + age + ")";
            logger.info(query);
            stat.executeUpdate(query);
        }
        stat.close();
    }

    public ResultSet select() throws SQLException {
        logger.info("Selecting employees");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from employee");
        return rs;
        //        while (rs.next())
//            logger.info(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getInt(3));
//        stmt.close();
    }


    public void delete() throws SQLException {
        logger.info("deleting all employee");
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
