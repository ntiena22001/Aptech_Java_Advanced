/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jdbc;

import Entity.Customer;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class JDBC {

//    static String loadDriverString = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
//    static String url = "jdbc:sqlserver://localhost;databaseName=master;;encrypt=true;trustServerCertificate=true;";
//    static String username = "sa";
//    static String password = "sa";
    private static Properties property;
    static Statement statement;
    static ResultSet resultSet;
    static PreparedStatement prepareStatement;
    private Connection connection;

    public JDBC() throws FileNotFoundException, IOException {
        property = new Properties();
        property.load(new FileInputStream("C:\\Users\\ADMIN\\Documents\\NetBeansProjects\\ConnectToDBMSJSW\\src\\jdbc\\database.properties"));
    }

    public void jdbcConnectDB() throws ClassNotFoundException, SQLException {
        String url = property.getProperty("url");
        String username = property.getProperty("username");
        String password = property.getProperty("password");
        String loadDriverString = property.getProperty("loadDriverString");
        Class.forName(loadDriverString);
        connection = DriverManager.getConnection(url, username, password);
        statement = connection.createStatement();

    }

    /**
     * Lấy tất cả các dòng trong bảng bỏ vào ArrayList để hiển thị lên JTable
     *
     * @return danh sách các lớp
     */
    public List<Customer> getAllCustomer() throws SQLException {

        List<Customer> listCustomer = new ArrayList<Customer>();
        resultSet = statement.executeQuery("SELECT*FROM CUSTOMERS");

        while (resultSet.next()) {
            Customer customer = new Customer(resultSet.getString("customerID"),
                    resultSet.getString("companyName"), resultSet.getString("contactName"),
                    resultSet.getString("contactTitle"), resultSet.getString("address"),
                    resultSet.getString("city"), resultSet.getString("region"),
                    resultSet.getString("postalCode"), resultSet.getString("country"),
                    resultSet.getString("phone"), resultSet.getString("fax"));
            listCustomer.add(customer);
        }
        return listCustomer;

    }

    public boolean updateByID(Customer customerID) throws SQLException {
        String slqStatement = "UPDATE CUSTOMERS SET CompanyName=?,ContactName=?"
                + ",ContactTitle=?,Address=?,city=?,Region=?,PostalCode=?"
                + ",Country=?,phone=?,fax=? WHERE CustomerID=?";
        prepareStatement = connection.prepareStatement(slqStatement);
        prepareStatement.setString(1, customerID.getCompanyName());
        prepareStatement.setString(2, customerID.getContactName());
        prepareStatement.setString(3, customerID.getContactTitle());
        prepareStatement.setString(4, customerID.getAddress());
        prepareStatement.setString(5, customerID.getCity());
        prepareStatement.setString(6, customerID.getRegion());
        prepareStatement.setString(7, customerID.getPostalCode());
        prepareStatement.setString(8, customerID.getCountry());
        prepareStatement.setString(9, customerID.getPhone());
        prepareStatement.setString(10, customerID.getFax());
        prepareStatement.setString(11, customerID.getCustomerID());

        int result = prepareStatement.executeUpdate();
        if (result == 1) {
            connection.close();
            return true;
        } else {
            connection.close();
            return false;
        }
    }

    public Customer getCustomerByID(String id) throws SQLException {
        String sql = "Select*from Customers where customerID=?";
        prepareStatement = connection.prepareStatement(sql);
        prepareStatement.setString(1, id);
        resultSet = prepareStatement.executeQuery();
        if (resultSet.next()) {
            Customer customer = new Customer(resultSet.getString("customerID"),
                    resultSet.getString("companyName"), resultSet.getString("contactName"),
                    resultSet.getString("contactTitle"), resultSet.getString("address"),
                    resultSet.getString("city"), resultSet.getString("region"),
                    resultSet.getString("postalCode"), resultSet.getString("country"),
                    resultSet.getString("phone"), resultSet.getString("fax"));
            return customer;
        } else {
            connection.close();
            return null;
        }
    }

    public boolean addNewCustomer(Customer newCustomer) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Customers VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        String url = property.getProperty("url");
        String username = property.getProperty("username");
        String password = property.getProperty("password");
        String loadDriverString = property.getProperty("loadDriverString");
        Class.forName(loadDriverString);
        connection = DriverManager.getConnection(url, username, password);
        prepareStatement = connection.prepareStatement(sql);
        prepareStatement.setString(1, newCustomer.getCustomerID());
        prepareStatement.setString(2, newCustomer.getCompanyName());
        prepareStatement.setString(3, newCustomer.getContactName());
        prepareStatement.setString(4, newCustomer.getContactTitle());
        prepareStatement.setString(5, newCustomer.getAddress());
        prepareStatement.setString(6, newCustomer.getCity());
        prepareStatement.setString(7, newCustomer.getRegion());
        prepareStatement.setString(8, newCustomer.getPostalCode());
        prepareStatement.setString(9, newCustomer.getCountry());
        prepareStatement.setString(10, newCustomer.getPhone());
        prepareStatement.setString(11, newCustomer.getFax());
        prepareStatement.addBatch();

        int result = prepareStatement.executeUpdate();
        if (result == 1) {
            connection.close();
            return true;
        } else {
            connection.close();
            return false;
        }
        
    }
    
    public boolean deleteCustomerByID(String customerId) throws SQLException, ClassNotFoundException {
        String url = property.getProperty("url");
        String username = property.getProperty("username");
        String password = property.getProperty("password");
        String loadDriverString = property.getProperty("loadDriverString");
        Class.forName(loadDriverString);
        connection = DriverManager.getConnection(url, username, password);
        Customer customerID = getCustomerByID(customerId);
        if (customerID == null) {
            return false;
        } else {
            String sql = " Delete from Customers where customerID=?";
            prepareStatement = connection.prepareCall(sql);
            prepareStatement.setString(1, customerId);
            int result = prepareStatement.executeUpdate();
            if (result == 1) {
                connection.close();
                return true;
            } else {
                connection.close();
                return false;
            }
        }
    }
}
