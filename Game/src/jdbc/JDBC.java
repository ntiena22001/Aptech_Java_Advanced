/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jdbc;

import Entity.tanggiam;
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

    private static Properties property;
    static Statement statement;
    static ResultSet resultSet;
    static PreparedStatement prepareStatement;
    private Connection connection;
    private tanggiam tanggiam;

    public JDBC() throws FileNotFoundException, IOException {
        property = new Properties();
        property.load(new FileInputStream("C:\\Users\\ADMIN\\Documents\\NetBeansProjects\\Game\\src\\jdbc\\database.properties"));
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
    public int getTanggiam() throws SQLException {
        int tanggiam = 0;

        resultSet = statement.executeQuery("SELECT sotanggiam FROM tanggiam");
        if (resultSet.next()) {
            tanggiam = resultSet.getInt("sotanggiam");
        }

        return tanggiam;
    }

    public boolean updateByID(int sotanggiam) throws SQLException, ClassNotFoundException {
        jdbcConnectDB();
        String sql = "UPDATE tanggiam SET sotanggiam=? WHERE vitriID=?";
        prepareStatement = connection.prepareStatement(sql);
        prepareStatement.setInt(1, sotanggiam);
        prepareStatement.setInt(2, 1);

        int result = prepareStatement.executeUpdate();
        if (result == 1) {
            
            return true;
        } else {
           
            return false;
        }
    }

    public void jdbcDisconnectDB() throws SQLException {

        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
