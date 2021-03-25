package com.JavaDatabase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
class EmployeeDatabaseTest {

    @Test
    void readData_And_ReturnCount() {
        EmployeeDatabase employeeDatabase=new EmployeeDatabase();
        List<PayrollServiceData>payrollServiceDataList=employeeDatabase.readData();
        Assertions.assertEquals(2,payrollServiceDataList.size());
    }

    @Test
    void UpdateTable(){
        EmployeeDatabase employeeDatabase=new EmployeeDatabase();
        employeeDatabase.UpdateData();
        List<PayrollServiceData>payrollServiceDataList=employeeDatabase.readData();
        Assertions.assertEquals(2,payrollServiceDataList.size());
    }
}