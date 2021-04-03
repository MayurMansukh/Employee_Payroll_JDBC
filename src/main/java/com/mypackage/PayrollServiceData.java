package com.mypackage;

import java.sql.Date;

public class PayrollServiceData {

    public int id;
    public String Name;
    public Date StartDate;
    public String Gender;
    public int Salary;
//
//    public int payroll_id;
//    public double basicpay;
//    public double deduction;
//    public double taxpay;
//    public double tax;
//    public double netpay;

    public PayrollServiceData(int id, String name, Date startDate, String gender, int salary) {
        this.id = id;
        Name = name;
        StartDate = startDate;
        Gender = gender;
        Salary = salary;
    }

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

    public Date getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = Date.valueOf(startDate);
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public int getSalary() {
        return Salary;
    }

    public void setSalary(int salary) {
        Salary = salary;
    }

    @Override
    public String toString() {
        return "PayrollServiceData{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", StartDate='" + StartDate + '\'' +
                ", Gender='" + Gender + '\'' +
                ", Salary=" + Salary +
                '}';
    }

    //    public PayrollServiceData(int payroll_id, double basicpay, double deduction, double taxpay, double tax, double netpay) {
//        this.payroll_id = payroll_id;
//        this.basicpay = basicpay;
//        this.deduction = deduction;
//        this.taxpay = taxpay;
//        this.tax = tax;
//        this.netpay = netpay;
//    }


}

