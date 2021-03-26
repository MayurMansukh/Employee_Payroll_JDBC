package com.payroll_service_jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class EmployeeDatabase {

    private Connection getConnection() throws IllegalAccessException {
        String JDBCURL = "jdbc:mysql://localhost:3306/payroll_service?useSSL=false";
        String UserName = "root";
        String Password = "P@ssw0rd1@2";
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Dreiver loaded");
        } catch (ClassNotFoundException e) {
            throw new IllegalAccessException(String.format("Driver not found in classpath%s", e));

        }
        try {
            System.out.println("Connecting to database" + JDBCURL);
            connection = DriverManager.getConnection(JDBCURL, UserName, Password);
            System.out.println("Connection succesfully" + connection);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return connection;

    }

    public List<PayrollServiceData> readData() {
        String Sql_Query = "select * from Payroll_ServiceTable";
        List<PayrollServiceData> payrollServiceData = new ArrayList<>();
        try {
            Connection connection = this.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(Sql_Query);

            while (resultSet.next()) {

                int id = resultSet.getInt(1);
                String Name = resultSet.getString(2);
                Date StartDate = resultSet.getDate(3);
                String Gender=resultSet.getString(4);
                int Salary = resultSet.getInt(5);

                System.out.println();
                System.out.println("id=" + id);
                System.out.println("Name=" + Name);
                System.out.println("StartDate=" + StartDate);
                System.out.println("Salary=" + Salary);

                PayrollServiceData payrollServiceData1 = new PayrollServiceData(resultSet.getInt(1), resultSet.getString(2), resultSet.getDate(3), resultSet.getString(4),resultSet.getInt(5));
                payrollServiceData.add(payrollServiceData1);


            }
            statement.close();
            connection.close();


        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return payrollServiceData;
    }

    public void update_Record_into_database_returnCount(){
        String SqlQuery="update PayrollServiceTable set Salary=40000 where id=1";
        try {
            Connection connection=this.getConnection();
            Statement statement=connection.createStatement();
            long resultset=statement.executeUpdate(SqlQuery);
        }catch (SQLException | IllegalAccessException e){
            e.printStackTrace();
        }
    }

}