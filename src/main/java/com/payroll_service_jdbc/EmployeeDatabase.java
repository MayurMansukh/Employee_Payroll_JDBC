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
            PreparedStatement preparedStatement=connection.prepareStatement("select * from Payroll_ServiceTable");

            ResultSet resultSet = preparedStatement.executeQuery(Sql_Query);

            while (resultSet.next()) {

                int id = resultSet.getInt(1);
                String Name = resultSet.getString(2);
                Date StartDate = resultSet.getDate(3);
                String Gender=resultSet.getString(4);
                int Salary = resultSet.getInt(5);

                PayrollServiceData payrollServiceData1 = new PayrollServiceData(resultSet.getInt(1), resultSet.getString(2), resultSet.getDate(3), resultSet.getString(4),resultSet.getInt(5));
                payrollServiceData.add(payrollServiceData1);
            }
            System.out.println(payrollServiceData.toString());
            preparedStatement.close();
            connection.close();
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return payrollServiceData;
    }

    public long update_Record_into_database_returnCount_Using_PreparedStatement(double salary,int id){
        try {
            Connection connection=this.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("Update Payroll_ServiceTable set salary=? where id=? ; ");
            preparedStatement.setDouble(1,salary);
            preparedStatement.setInt(2,id);
            long resultSet=preparedStatement.executeUpdate();
            System.out.println(resultSet);
            return resultSet;
        } catch (SQLException | IllegalAccessException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    public long insert_New_Record_into_database_returnCount_Using_PreparedStatement(int id, String name, String date,String gender,double salary){
        try {
            Connection connection=this.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("insert into Payroll_ServiceTable(ID,Name,StartDate,Gender,Salary) values(?,?,?,?,?); ");
            preparedStatement.setInt(1,id);
            preparedStatement.setString(2,name);
            preparedStatement.setString(3, String.valueOf(Date.valueOf(date)));
            preparedStatement.setString(4,gender);
            preparedStatement.setDouble(5,salary);
            int resultSet=preparedStatement.executeUpdate();
            System.out.println(resultSet);
            return resultSet;
        } catch (SQLException | IllegalAccessException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    public List<PayrollServiceData> Payroll_Data_From_Salary(String Date) {
        String Sql_Query = "select * from Payroll_ServiceTable";
        List<PayrollServiceData> payrollServiceData = new ArrayList<>();
        try {
            Connection connection = this.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("select * from Payroll_ServiceTable where StartDate>=?");
            preparedStatement.setDate(1, java.sql.Date.valueOf(Date));
            ResultSet resultSet = preparedStatement.executeQuery(Sql_Query);

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String Name = resultSet.getString(2);
                Date StartDate = resultSet.getDate(3);
                String Gender=resultSet.getString(4);
                int Salary = resultSet.getInt(5);

                PayrollServiceData payrollServiceData1 = new PayrollServiceData(resultSet.getInt(1), resultSet.getString(2), resultSet.getDate(3), resultSet.getString(4),resultSet.getInt(5));
                payrollServiceData.add(payrollServiceData1);
            }
            System.out.println(payrollServiceData.toString());
            preparedStatement.close();
            connection.close();
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return payrollServiceData;
    }

    public List<String> dataManipulation(){
        List<String> list=new ArrayList();
        try {
            Connection connection=this.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("select gender,sum(salary), avg(Salary),min(Salary),max(Salary),count(Salary) from Payroll_ServiceTable group by Gender ");
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                int index=1;
                System.out.println("Gender: "+resultSet.getString(1));
                System.out.println("Salary: "+resultSet.getString(2));
                for (int i=0;i<10;i++){
                    if(index<5) {
                        list.add(i, resultSet.getString(index));
                        index++;
                    }
                }
                System.out.println(list);
            }
        }catch (SQLException | IllegalAccessException throwables){
            throwables.printStackTrace();
        }
        return list;
    }
    public int insert_Into_Payroll_Detail_Table(int payroll_id,double basicpay,double deduction,double taxpay,double tax,double netpay){
        try{
            Connection connection=this.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("insert into Payroll_Detail_Table(payroll_id,basicpay,deduction,taxpay,tax,netpay) values(?,?,?,?,?,?); ");

            preparedStatement.setInt(1,payroll_id);
            preparedStatement.setDouble(2, basicpay);
            preparedStatement.setDouble(3,deduction);
            preparedStatement.setDouble(4,taxpay);
            preparedStatement.setDouble(5,tax);
            preparedStatement.setDouble(6,netpay);
            int resultSet=preparedStatement.executeUpdate();
            return resultSet;
        }catch (SQLException | IllegalAccessException throwables){
            throwables.printStackTrace();
        }
        return 0;
    }

    public void insert_Values_Into_Both_Tables(int id, String name, String date,String gender,double salary,int payroll_id) throws IllegalAccessException, SQLException {
        Connection connection = this.getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement=connection.prepareStatement("insert into Payroll_ServiceTable(ID,Name,StartDate,Gender,Salary) values(?,?,?,?,?); ");
            preparedStatement.setInt(1,id);
            preparedStatement.setString(2,name);
            preparedStatement.setString(3, String.valueOf(Date.valueOf(date)));
            preparedStatement.setString(4,gender);
            preparedStatement.setDouble(5,salary);
            int resultSet=preparedStatement.executeUpdate();


            PreparedStatement preparedStatement1=connection.prepareStatement("insert into Payroll_Detail_Table(payroll_id,basicpay,deduction,taxpay,tax,netpay) values(?,?,?,?,?,?); ");
            preparedStatement1.setInt(1,payroll_id);
            preparedStatement1.setDouble(2, salary/20);
            preparedStatement1.setDouble(3,salary/5);
            preparedStatement1.setDouble(4,salary/2);
            preparedStatement1.setDouble(5,salary/20);
            preparedStatement1.setDouble(6,salary/10);
            int resultSet1=preparedStatement1.executeUpdate();
            connection.commit();
        }catch (SQLException throwables){
            throwables.printStackTrace();
            connection.rollback();
        }finally {
            connection.close();
        }
    }

}