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
public class QuantityUpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
        PrintWriter out = response.getWriter();
        
        try{
            String action = request.getParameter("action");       
            int id = Integer.parseInt(request.getParameter("id"));
            
            ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
            
            out.println(cart_list);
            
            if(action.equals("inc")){
                for(Cart c: cart_list){
                    if(c.getId() == id){
                        int quantity = c.getQuantity();
                        quantity++;
                        c.setQuantity(quantity);
                        break;
                    }
                }
            }else{
                for(Cart c: cart_list){
                    if(c.getId() == id && c.getQuantity() > 1 ) {
                        int quantity = c.getQuantity();
                        quantity--;
                        c.setQuantity(quantity);            
                    }
                    else{
                        response.sendRedirect("remove-cart-product?id="+id);
                    }
                }
            }
            response.sendRedirect("cart.jsp");


        }catch(Exception ex){
            
        }
    
    
    
    }

}
