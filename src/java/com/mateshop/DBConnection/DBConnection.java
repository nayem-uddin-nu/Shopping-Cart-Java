/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mateshop.DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author nayem
 */
public class DBConnection {
    
    private static Connection connection;
    
    
    public static Connection getConnection() throws ClassNotFoundException, SQLException{
        if(connection == null){
                
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mateshop", "root", "root");        
        }
        return connection;
    }
    
}
