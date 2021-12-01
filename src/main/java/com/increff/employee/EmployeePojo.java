package com.increff.employee;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EmployeePojo {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Id
    private int id;
    private int age;
    private String name;
}
