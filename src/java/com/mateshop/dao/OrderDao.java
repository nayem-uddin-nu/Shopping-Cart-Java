/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mateshop.dao;

import com.mateshop.models.Order;
import com.mateshop.models.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nayem
 */
public class OrderDao {
    private Connection connection = null;
    private String query;
    private PreparedStatement pstmt;
    private ResultSet res;

    public OrderDao(Connection con) {
        this.connection = con;
    }
    
    public boolean insertOrder(Order order){
        boolean result = false;
        try{
            query = "insert into orders (p_id, u_id, quantity) values(?,?,?)";
            
            pstmt = this.connection.prepareStatement(query);
            
            System.out.println(pstmt);
            
            pstmt.setInt(1, order.getId());
            pstmt.setInt(2, order.getUid());
            pstmt.setInt(3, order.getQuantity());
//            pstmt.setString(4, order.getDate());

//            result = true;
            pstmt.executeUpdate();
            result = true;
            
        }catch(SQLException ex){
        }
        return result;
    }
    
    public List<Order> userOrders(int id){
        List<Order> curr_order = new ArrayList<>();
        
        try{
            query  = "select * from orders where u_id=? order by orders.id desc";
            pstmt = this.connection.prepareStatement(query);
            pstmt.setInt(1, id);
            res = pstmt.executeQuery();
            
            while(res.next()){
                Order order = new Order();
                ProductDao pDao = new ProductDao(this.connection);
                int pId = res.getInt("p_id");
                
                Product product = pDao.getSingleProduct(pId);
                
                order.setOrderId(res.getInt("id"));
                order.setId(pId);
                order.setName(product.getName());
                order.setCategory(product.getCategory());
                order.setImage(product.getImage());
                order.setPrice(product.getPrice() * res.getInt("quantity"));
                order.setQuantity(res.getInt("quantity"));
                order.setDate(res.getString("order_date"));
                curr_order.add(order);                
            }
            
        }catch(Exception ex){
        }
        return curr_order;
    }
    
    public void cancleOrder(int id){
        try{
            query = "delete from orders where p_id=?";
            pstmt = this.connection.prepareStatement(query);
            pstmt.setInt(1, id);
            pstmt.execute();
        }catch(Exception ex){}
    }
}
