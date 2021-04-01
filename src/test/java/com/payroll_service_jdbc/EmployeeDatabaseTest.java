package com.payroll_service_jdbc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

class EmployeeDatabaseTest {

    @Test
    void readData_And_ReturnCount() throws SQLException, IllegalAccessException {
        EmployeeDatabase employeeDatabase=new EmployeeDatabase();
        List<PayrollServiceData>payrollServiceDataList=employeeDatabase.readData();
        Assertions.assertEquals(3,payrollServiceDataList.size());
    }

    @Test
    void update_Record_into_database_returnCount_Using_PreparedStatement(){
        double salary=70000;
        int id=1;
        EmployeeDatabase employeeDatabase=new EmployeeDatabase();
        long result=employeeDatabase.update_Record_into_database_returnCount_Using_PreparedStatement(salary,id);
        Assertions.assertEquals(2,result);
    }



    @Test
    public void reurn_employee_datails_for_a_given_date_range(){
        String date = "2021-01-01";
        EmployeeDatabase employeeDatabase=new EmployeeDatabase();
        List<PayrollServiceData>employeePayrollDataList= employeeDatabase.Payroll_Data_From_Salary(date);
        Assertions.assertEquals(2,employeePayrollDataList.size());
    }
    @Test
    public void return_sum_avg_min_max_count(){
        EmployeeDatabase employeeDatabase=new EmployeeDatabase();
        List<String> list=employeeDatabase.dataManipulation();
        Assertions.assertEquals(4,list.size());
    }

    @Test
    void insert_New_Record_into_database_returnCount_Using_PreparedStatement() throws SQLException, IllegalAccessException {
        int id=3;
        String name="rajan";
        String date="2020-3-9";
        String gender="M";
        double salary=80000;
        EmployeeDatabase employeeDatabase=new EmployeeDatabase();
        employeeDatabase.insert_New_Record_into_database_returnCount_Using_PreparedStatement(id,name,date,gender,salary);
        List<PayrollServiceData>payrollServiceDataList=employeeDatabase.readData();
        Assertions.assertEquals(3,payrollServiceDataList.size());
    }

    @Test
    public void insert_in_payroll_details_test() throws SQLException, IllegalAccessException {
        int payroll_id=1;
        double basicpay=10000;
        double deduction=20000;
        double taxpay=1000;
        double tax=500;
        double netpay=7500;

        EmployeeDatabase employeeDatabase=new EmployeeDatabase();
        employeeDatabase.insert_Into_Payroll_Detail_Table(payroll_id,basicpay,deduction,taxpay,tax,netpay);
        List<PayrollServiceData>payrollServiceDataList=employeeDatabase.readData();
        Assertions.assertEquals(3,payrollServiceDataList.size());
    }

    @Test
    void insert_New_Record_into_Both_Tables_test() throws SQLException, IllegalAccessException {
        int id=3;
        String name="Rahul";
        String date="2020-4-10";
        String gender="M";
        double salary=50000;
        int payroll_id=4;

        EmployeeDatabase employeeDatabase=new EmployeeDatabase();
        employeeDatabase.insert_Values_Into_Both_Tables(id,name,date,gender,salary,payroll_id);
        List<PayrollServiceData>payrollServiceDataList=employeeDatabase.readData();
        Assertions.assertEquals(4,payrollServiceDataList.size());
    }

    @Test
    public void deleting_employee_from_Payroll_Service_Table_test() throws SQLException, IllegalAccessException {
        String name="rajan";
        EmployeeDatabase employeeDatabase=new EmployeeDatabase();
        employeeDatabase.deleteRecordFromPayroll_ServiceTable(name);
        List<PayrollServiceData> employeePayrollDataList=employeeDatabase.readData();
        Assertions.assertEquals(3,employeePayrollDataList.size());
    }

    @Test

    public void insert_multiple_values_into_a_table_at_a_time() throws SQLException, IllegalAccessException, SQLException {
        EmployeeDatabase employeeDatabase=new EmployeeDatabase();
        List<PayrollServiceData> list=new ArrayList<>();
        list.add(new PayrollServiceData(4,"Nath", Date.valueOf("2029-03-11"),"M",40000));
        list.add(new PayrollServiceData(5,"nilam",Date.valueOf("2029-03-11"),"F",50000));
        employeeDatabase.insetRecordsUsingArrays(list);
        List<PayrollServiceData> employeePayrollDataList=employeeDatabase.readData();
        Assertions.assertEquals(7,employeePayrollDataList.size());

    }

    @Test
    public void add_multiple_records_and_calculate_duration() throws SQLException, IllegalAccessException,SQLException {
        EmployeeDatabase employeeDatabase=new EmployeeDatabase();
        List<PayrollServiceData> list=new ArrayList<>();
        list.add(new PayrollServiceData(4,"raghu", Date.valueOf("2029-03-11"),"M",70000));
        list.add(new PayrollServiceData(5,"naryani",Date.valueOf("2029-03-11"),"F",90000));

        Instant start =Instant.now();
        employeeDatabase.insetRecordsUsingArrays(list);
        Instant end = Instant.now();
        System.out.println("Duration without thread"+ Duration.between(start,end));
        List<PayrollServiceData> employeePayrollDataList=employeeDatabase.readData();
        Assertions.assertEquals(15,employeePayrollDataList.size());


    }
    @Test
    public void add_multiple_records_using_thread_and_without_thread() throws SQLException, IllegalAccessException,SQLException {
        EmployeeDatabase employeeDatabase=new EmployeeDatabase();
        List<PayrollServiceData> list=new ArrayList<>();
        list.add(new PayrollServiceData(4,"raghu", Date.valueOf("2029-03-11"),"M",70000));
        list.add(new PayrollServiceData(5,"naryani",Date.valueOf("2029-03-11"),"F",90000));
        list.add(new PayrollServiceData(6,"yani",Date.valueOf("2029-03-11"),"F",90000));
        Instant start =Instant.now();
        employeeDatabase.insetRecordsUsingArrays(list);
        Instant end = Instant.now();
        System.out.println("Duration without thread: "+ Duration.between(start,end));

        Instant thredstart =Instant.now();
        employeeDatabase.insetRecordsUsingArraysUsingThread(list);
        Instant threadend = Instant.now();
        System.out.println("Duration with thread: "+ Duration.between(thredstart,threadend));

        List<PayrollServiceData> employeePayrollDataList=employeeDatabase.readData();
        Assertions.assertEquals(3,employeePayrollDataList.size());




    }



}