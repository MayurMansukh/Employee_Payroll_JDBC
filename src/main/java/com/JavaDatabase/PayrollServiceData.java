package com.JavaDatabase;

public class PayrollServiceData {

    public int id;
    public String Name;
    public String StartDate;
    public int Salary;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public int getSalary() {
        return Salary;
    }

    public void setSalary(int salary) {
        Salary = salary;
    }

    public PayrollServiceData(int id, String name, String startDate, int salary) {
        this.id = id;
        Name = name;
        StartDate = startDate;
        Salary = salary;


    }
}
