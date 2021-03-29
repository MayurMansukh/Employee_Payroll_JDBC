package com.payroll_service_jdbc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;
class EmployeeDatabaseTest {

    @Test
    void readData_And_ReturnCount() {
        EmployeeDatabase employeeDatabase=new EmployeeDatabase();
        List<PayrollServiceData>payrollServiceDataList=employeeDatabase.readData();
        Assertions.assertEquals(2,payrollServiceDataList.size());
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

}