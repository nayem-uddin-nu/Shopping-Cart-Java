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
import javax.servlet.http.HttpSession;

/**
 *
 * @author nayem
 */
public class addToCart extends HttpServlet {
    
    ArrayList<Cart> cartList = new ArrayList<>();
    ArrayList<Cart> cart_list;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        
        

        try(PrintWriter out = response.getWriter()){
            int id = Integer.parseInt(request.getParameter("id"));
            
            Cart cart = new Cart();
            cart.setId(id);
            cart.setQuantity(1);
            
            HttpSession session = request.getSession();
            cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
            
            if(cart_list == null){
                cartList.add(cart);
                session.setAttribute("cart-list", cartList);
//                out.print("Session creater and added cart to cart-list");
//                response.sendRedirect("home.jsp");
//                response.getHeaders("cart-list");
                out.println(cartList.size());
            }
            else{
                cartList = cart_list;
                boolean exists = false;
                
                for( Cart c: cart_list){
                    if(c.getId() == id){
                        exists = true;
                        out.println("Product Exists");
                        response.sendRedirect("cart.jsp");
                    }
                }
                if(!exists){
                        cartList.add(cart);
                        response.sendRedirect("home.jsp");
                        out.println("Added new item in cart");
                    }
            }
        }catch(Exception e){  
        }
    }

//    public int getCartItemNumber(){
//        return cart_list.size();
//    }
}
