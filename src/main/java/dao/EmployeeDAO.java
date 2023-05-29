package dao;

import model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    public static final String JDBC_URL = "jdbc:mysql://localhost:3306/md3?useSSL=false";
    public static final String JDBC_USER = "root";
    public static final String JDBC_PASSWORD = "123456";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }


    public static void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

    public List<Employee> getAllEmployee(){
        String SELECT_ALL_SQL = "SELECT employee.*, name_department from employee join department on employee.id_department = department.id_department;;";
        List<Employee> employeeList = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SQL)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int employee_id = resultSet.getInt("id_employee");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phone_number");
                double salary = resultSet.getDouble("salary");
                int id_department = resultSet.getInt("id_department");
                String department = resultSet.getString("name_department");
                employeeList.add(new Employee(employee_id,name,email,address,phoneNumber,salary,department,id_department));
            }
        }
        catch (SQLException ex){
            printSQLException(ex);
        }
        return employeeList;
    }

    public void insertEmployee(Employee employee){
        String INSERT_EMPLOYEE_SQL = "INSERT INTO employee VALUES (default,?,?,?,?,?,?);";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEE_SQL)){
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getEmail());
            preparedStatement.setString(3, employee.getAddress());
            preparedStatement.setString(4, employee.getPhoneNumber());
            preparedStatement.setDouble(5, employee.getSalary());
            preparedStatement.setInt(6, employee.getDepartment_id());
            preparedStatement.executeUpdate();
        }catch (SQLException ex){
            printSQLException(ex);
        }
    }
    public void updateEmployee(Employee employee){
        String UPDATE_EMPLOYEE_SQL = "UPDATE employee SET name=?,email=?,address=?,phone_number=?,salary=?,id_department=? WHERE id_employee = ?;";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EMPLOYEE_SQL)){
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getEmail());
            preparedStatement.setString(3, employee.getAddress());
            preparedStatement.setString(4, employee.getPhoneNumber());
            preparedStatement.setDouble(5, employee.getSalary());
            preparedStatement.setInt(6, employee.getDepartment_id());
            preparedStatement.setInt(7, employee.getEmployee_id());
            preparedStatement.executeUpdate();
        }catch (SQLException ex){
            printSQLException(ex);
        }
    }

    public void deleteEmployee(String id_employee){
        String DELETE_EMPLOYEE_SQL = "DELETE FROM employee WHERE id_employee = ?;";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EMPLOYEE_SQL)){
            preparedStatement.setString(1, id_employee);
            preparedStatement.executeUpdate();
        }
        catch (SQLException ex){
            printSQLException(ex);
        }
    }

    public List<Employee> searchEmployee(String searchKey){
        String SELECT_EMPLOYEE_BY_ID = "SELECT*FROM employee LEFT JOIN department On employee.id_department = department.id_department WHERE name like ?;";
        List<Employee> employeeList = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPLOYEE_BY_ID)){
            preparedStatement.setString(1, "%"+ searchKey + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int employee_id = resultSet.getInt("id_employee");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phone_number");
                double salary = resultSet.getDouble("salary");
                String department_name = resultSet.getString("name_department");
                employeeList.add(new Employee(employee_id,name,email,address,phoneNumber,salary,department_name));
            }
        }
        catch (SQLException ex){
            printSQLException(ex);
        }
        return employeeList;
    }
}