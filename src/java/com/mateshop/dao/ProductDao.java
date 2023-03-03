/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mateshop.dao;

import com.mateshop.models.Cart;
import com.mateshop.models.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

/**
 *
 * @author nayem
 */
public class ProductDao {
    private Connection connection;
    private String query;
    private PreparedStatement pstmt;
    private ResultSet res;

    public ProductDao(Connection connection) {
        this.connection = connection;
    }
    
    public List<Product> getProducts(){
        List<Product> products = new ArrayList<>();
        try {
            query = "select * from products";
            pstmt = this.connection.prepareStatement(query);
            res = pstmt.executeQuery();
            
            while(res.next()){
                Product product = new Product();
                product.setId(res.getInt("id"));
                product.setName(res.getString("name")); 
                product.setCategory(res.getString("category"));
                product.setPrice(res.getDouble("price")); 
                product.setImage(res.getString("image"));
                
                products.add(product);
            }
        }catch(Exception e){
            
        }
        return products;
    }
    
    
    
    public List<Cart> getCartProducts(ArrayList<Cart> cartList){
        List<Cart> products = new ArrayList<>();
        
        try{
            if(!cartList.isEmpty()){
                for(Cart item : cartList ){
                    query = "select * from products where id=?";
                    pstmt = this.connection.prepareStatement(query);
                    pstmt.setInt(1, item.getId());
                    
                    res = pstmt.executeQuery();
                    
                    while(res.next()){
                        Cart newCartItem = new Cart();
                        
                        newCartItem.setId(res.getInt("id"));
                        newCartItem.setImage(res.getString("image"));
                        newCartItem.setName(res.getString("name"));
                        newCartItem.setCategory(res.getString("category"));
                        newCartItem.setPrice(res.getDouble("price") * item.getQuantity());
                        newCartItem.setQuantity(item.getQuantity());
                        products.add(newCartItem);
                    }
                    
                }
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        System.out.println(products);
        return products;
    }
    
    public double totalCartPrice(ArrayList<Cart> cartList){
        double total = 0.0;
        
        try{
            if(!cartList.isEmpty()){
                for(Cart item : cartList ){
                    query = "select * from products where id=?";
                    pstmt = this.connection.prepareStatement(query);
                    pstmt.setInt(1, item.getId());
                    
                    res = pstmt.executeQuery();
                    
                    while(res.next()){
                        total += res.getDouble("price") * item.getQuantity();
                    }
                }
            }
        }catch(Exception e){
            
        }
        return total;
    }
    
    public Product getSingleProduct(int id){
        Product row = null;
        try{
            query = "select * from products where id=?";
            pstmt = this.connection.prepareStatement(query);
            pstmt.setInt(1, id);
            
            res = pstmt.executeQuery();
            
            while(res.next()){
                row = new Product();
                row.setId(res.getInt("id"));
                row.setName(res.getString("name"));
                row.setCategory(res.getString("category"));
                row.setImage(res.getString("image"));   
                row.setPrice(res.getDouble("price"));

                
            }
            
        }catch(Exception ex){}
        return row;
        
    }
}
