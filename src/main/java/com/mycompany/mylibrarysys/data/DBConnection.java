/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mylibrarysys.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Reverside
 */
public class DBConnection {
    
    
  private static Connection connect;

    public void getDBConnect() {
        synchronized ("") {
            try {
                if (this.getConnect() == null || this.getConnect().isClosed()) {
                    try {

                        String jdbcUrlPath = "jdbc:postgresql://localhost:5432/MyLibrarySys";
                        String username = "postgres";
                        String password = "Pikedhats+50";

                        // String url = "jdbc:mysql://localhost/student_list";     
                        Class.forName("org.postgresql.Driver");
                        
                        System.out.println("Database Connected Successfully!");

                        setConnect(DriverManager.getConnection(jdbcUrlPath, username, password));
                    } catch (Exception e) {
                        System.out.println("Database Connection Failed!");
                        e.printStackTrace();
                    }
                } else {
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * @return the con
     */
    public static Connection getConnect() {
        return connect;
    }

    /**
     * @param aConnect the con to set
     */
    public static void setConnect(Connection aConnect) {
        connect = aConnect;
    }

    public static void closeConnection() {
        try {
            connect.close();
             System.out.println("Database Connection Closed.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

