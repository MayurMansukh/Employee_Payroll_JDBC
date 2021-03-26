package com.payroll_service_jdbc;

import java.util.Date;

public class PayrollServiceData {

    public int id;
    public String Name;
    public Date StartDate;
    public String Gender;
    public int Salary;

    public PayrollServiceData(int id, String name, Date startDate, String gender, int salary) {
        this.id = id;
        Name = name;
        StartDate = startDate;
        Gender = gender;
        Salary = salary;
    }
}

