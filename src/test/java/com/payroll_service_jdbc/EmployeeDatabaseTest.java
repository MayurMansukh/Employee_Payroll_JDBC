package com.payroll_service_jdbc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.sql.SQLException;
import java.util.List;
class EmployeeDatabaseTest {

    @Test
    void readData_And_ReturnCount() {
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
    void insert_New_Record_into_database_returnCount_Using_PreparedStatement(){
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
    public void insert_in_payroll_details_test() {
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


}