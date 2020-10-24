package com.cybersoft.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String url = "jdbc:mysql://localhost:3306/crm-app";
    private static final String username = "root";
    private static final String password = "long120601";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
           // System.out.println("Connect thành công!");
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            System.out.println("Không tìm thấy Driver!!");
            e.printStackTrace();
        }
        catch (SQLException e) {
            System.out.println("Không tìm thấy db!!");
            e.printStackTrace();
        }
        return null;
    }


}
