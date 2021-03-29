package com.payroll_service_jdbc;

import java.util.Date;

public class PayrollServiceData {

    public int id;
    public String Name;
    public Date StartDate;
    public String Gender;
    public int Salary;

    public int payroll_id;
    public double basicpay;
    public double deduction;
    public double taxpay;
    public double tax;
    public double netpay;

    public PayrollServiceData(int id, String name, Date startDate, String gender, int salary) {
        this.id = id;
        Name = name;
        StartDate = startDate;
        Gender = gender;
        Salary = salary;
    }

    public PayrollServiceData(int payroll_id, double basicpay, double deduction, double taxpay, double tax, double netpay) {
        this.payroll_id = payroll_id;
        this.basicpay = basicpay;
        this.deduction = deduction;
        this.taxpay = taxpay;
        this.tax = tax;
        this.netpay = netpay;
    }
}

