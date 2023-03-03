/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mateshop.servlets;

import com.mateshop.models.Cart;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nayem
 */
public class RemoveCartProduct extends HttpServlet {
    
    int lastid;
    
    public void setId(int id){
        this.lastid = id;
    }
    
    public int getId(){
        return this.lastid;
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        try{
            ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
            if(id != null) {
                if(cart_list != null){
                    for(Cart c: cart_list){
                        if(c.getId() == Integer.parseInt(id)){
                            cart_list.remove(cart_list.indexOf(c));
                        }
                    }
                }
            }else{
                if(cart_list != null){
                    for(Cart c: cart_list){
                        if(c.getId() == lastid){
                            cart_list.remove(cart_list.indexOf(c));
                        }
                    }
                }
            }
        }catch(Exception e){
            
        }
        response.sendRedirect("cart.jsp");

    }

}
