/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mateshop.servlets;

import com.mateshop.DBConnection.DBConnection;
import com.mateshop.dao.OrderDao;
import com.mateshop.models.Cart;
import com.mateshop.models.Order;
import com.mateshop.models.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nayem
 */
public class OrderNow extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try{
           
            
            User user = (User) request.getSession().getAttribute("auth");
            
            if(user != null){
                String productId = request.getParameter("id");        
                int productQty = Integer.parseInt(request.getParameter("quantity"));

                if(productQty <1){
                    productQty = 1;
                }
                
                Order order = new Order();
                order.setId(Integer.parseInt(productId));
                order.setUid(user.getId());
                order.setQuantity(productQty);
                
                OrderDao orderDao = new OrderDao(DBConnection.getConnection());
                boolean result = orderDao.insertOrder(order);
                
                if(result){
                    ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
                        if(cart_list != null){
                            for(Cart c: cart_list){
                                if(c.getId() == Integer.parseInt(productId)){
                                    response.sendRedirect("orders.jsp");
                                    cart_list.remove(cart_list.indexOf(c));
                                    break;
                                }
                            }
                        }
                    response.sendRedirect("orders.jsp");
                }else{
                    response.sendRedirect("cart.jsp");
                }
            }else{
                response.sendRedirect("login.jsp");
            }
        }catch(Exception ex){
            out.println(ex.getMessage());
            ex.printStackTrace();
            
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            doGet(request, response);
    }
}
