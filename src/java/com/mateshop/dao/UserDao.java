/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mateshop.dao;

import com.mateshop.models.User;
import java.sql.*;


/**
 *
 * @author nayem
 */
public class UserDao {
    private Connection connection = null;
    private String query;
    private PreparedStatement pstmt;
    private ResultSet res;
    
    public UserDao(Connection connection) {
        this.connection = connection;
    }
    
    public User login(String email, String password){
        User user = null;
        try {
            query = "select * from user where email=? and password=?";
            
            pstmt = this.connection.prepareStatement(query);
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            
            res = pstmt.executeQuery();
            
            if(res.next()){
                user = new User();
                user.setId(res.getInt("id"));
                user.setName(res.getString("name"));
                user.setEmail(res.getString("email"));
                user.setPassword(res.getString("password"));
            }
        } catch (Exception e) {}
        return user;
    }
    
}
