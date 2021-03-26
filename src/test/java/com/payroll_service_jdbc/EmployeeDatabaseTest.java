package com.payroll_service_jdbc;

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
}