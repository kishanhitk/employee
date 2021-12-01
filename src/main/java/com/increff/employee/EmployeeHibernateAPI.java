package com.increff.employee;

import org.hibernate.Query;
import org.hibernate.Session;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class EmployeeHibernateAPI {
    private static final Logger logger = Logger.getLogger("MainLogger");
    private HibernateUtil hibernateUtil;

    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {


    }

    public EmployeeHibernateAPI() throws IOException, SQLException, ClassNotFoundException {
        hibernateUtil = new HibernateUtil();
        logger.info("Hello, World!");
    }

    public void insert(EmployeePojo pojo) throws SQLException {
        logger.info("Inserting Employee");
        hibernateUtil.createSession();
        hibernateUtil.beginTransaction();
        Session s = hibernateUtil.getSession();
        s.save(pojo);
        hibernateUtil.commitTransaction();
        hibernateUtil.closeSession();
    }

    public EmployeePojo selectById(int id) throws SQLException {
        logger.info("Selecting employees");
        hibernateUtil.createSession();
        hibernateUtil.beginTransaction();
        Session s = hibernateUtil.getSession();
        EmployeePojo pojo = s.find(EmployeePojo.class, id);
        hibernateUtil.commitTransaction();
        hibernateUtil.closeSession();
        return pojo;
    }

    public List<EmployeePojo> selectAll() throws SQLException {
        logger.info("Selecting employees");
        hibernateUtil.createSession();
        hibernateUtil.beginTransaction();
        Session s = hibernateUtil.getSession();
        Query q = s.createQuery("select a from EmployeePojo a", EmployeePojo.class);

        List<EmployeePojo> employeePojoList = q.getResultList();
        hibernateUtil.commitTransaction();
        hibernateUtil.closeSession();
        return employeePojoList;
    }

    public void deleteByID(int id) throws SQLException {
        hibernateUtil.createSession();
        hibernateUtil.beginTransaction();
        Session s = hibernateUtil.getSession();
        EmployeePojo pojo = s.find(EmployeePojo.class, id);
        s.delete(pojo);
        hibernateUtil.commitTransaction();
        hibernateUtil.closeSession();

        logger.info("deleting all employee");
    }

    public void deleteAll() throws SQLException {
        hibernateUtil.createSession();
        hibernateUtil.beginTransaction();
        Session s = hibernateUtil.getSession();

        Query q = s.createQuery("select a from EmployeePojo a", EmployeePojo.class);
        List<EmployeePojo> employeePojoList = q.getResultList();
        for (EmployeePojo pojo : employeePojoList) {
            s.delete(pojo);
        }
        hibernateUtil.commitTransaction();
        hibernateUtil.closeSession();

        logger.info("deleting all employee");
    }

    public void printAll() throws SQLException {
        List<EmployeePojo> employeePojoList = selectAll();
        for (EmployeePojo pojo : employeePojoList) {
            logger.info("employee name " + pojo.getName());
        }
    }
}
