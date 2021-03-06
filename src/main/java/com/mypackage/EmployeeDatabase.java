package com.mypackage;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class EmployeeDatabase {
    private int ConnectionCounter=0;

    private synchronized Connection getConnection() throws IllegalAccessException {
        String JDBCURL = "jdbc:mysql://localhost:3306/payroll_service?useSSL=false";
        String UserName = "root";
        String Password = "P@ssw0rd1@2";
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Dreiver loaded");
        } catch (ClassNotFoundException e) {
            throw new IllegalAccessException(String.format("Driver not found in classpath%s", e));

        }
        try {
            //System.out.println("Connecting to database" + JDBCURL);
            System.out.println("Processing Thread "+Thread.currentThread().getName()+"Connecting to database with id "+ConnectionCounter);
            connection = DriverManager.getConnection(JDBCURL, UserName, Password);
            System.out.println("Processing Thread "+Thread.currentThread().getName()+" id "+ConnectionCounter+ " Connection was sucessfull!!! " +connection);
           // System.out.println("Connection succesfully" + connection);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return connection;

    }

    public List<PayrollServiceData> readData() throws IllegalAccessException, SQLException {
        String Sql_Query = "select * from Payroll_ServiceTable";
        List<PayrollServiceData> payrollServiceData = new ArrayList<>();
        Connection connection=null;

        try {
            connection = this.getConnection();
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

        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
        }finally {
            connection.close();
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

    public void deleteRecordFromPayroll_ServiceTable(String name) throws SQLException, IllegalAccessException {
        Connection connection = this.getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("delete from Payroll_ServiceTable where Name=?; ");
            preparedStatement.setString(1, name);
            int resultSet = preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            connection.rollback();
        }
    }

    public void insetRecordsUsingArrays(List<PayrollServiceData> payrollServiceData) throws SQLException, IllegalAccessException {
        Connection connection = this.getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("insert into payroll_detail(id,name,startdate,gender,salary) values(?,?,?,?,?); ");
            for (Iterator<PayrollServiceData> iterator = payrollServiceData.iterator(); iterator.hasNext(); ) {
                PayrollServiceData payrollServiceData1 = (PayrollServiceData) iterator.next();
                System.out.println("employee being added  " + payrollServiceData1.getName());
                preparedStatement.setInt(1, payrollServiceData1.getId());
                preparedStatement.setString(2, payrollServiceData1.getName());
                preparedStatement.setDate(3, payrollServiceData1.getStartDate());
                preparedStatement.setString(4, payrollServiceData1.getGender());
                preparedStatement.setDouble(5, payrollServiceData1.getSalary());
                System.out.println("employee Added  " + payrollServiceData1.Name);
                preparedStatement.addBatch();
            }
            int[] recordUpdateCounts = preparedStatement.executeBatch();
            connection.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            connection.rollback();
        }
    }

    private void insetRecordsUsingArraysUsingThread(int id, String firstname, Date startdate, String gender, double salary) {

    }

    public void insetRecordsUsingArraysUsingThread(List<PayrollServiceData> payrollServiceData) throws SQLException, IllegalAccessException {
        Map<Integer, Boolean> employeeAdditionsStatus = new HashMap<>();
        payrollServiceData.forEach(payrollServiceData1 -> {
            Runnable task = () -> {
                employeeAdditionsStatus.put(payrollServiceData.hashCode(), false);
                System.out.println("employee begin add " + Thread.currentThread().getName());
                this.insetRecordsUsingArraysUsingThread(payrollServiceData1.id, payrollServiceData1.Name, payrollServiceData1.StartDate, payrollServiceData1.Gender, payrollServiceData1.Salary);
                employeeAdditionsStatus.put(payrollServiceData.hashCode(), true);
            };
            Thread thread=new Thread(task , payrollServiceData1.Name);
            thread.start();
        });
        while (employeeAdditionsStatus.containsValue(false)){

            try {
                Thread.sleep(10);
            }catch (InterruptedException e){

            }
            System.out.println(this.readData());
        }
    }


}

