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
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nayem
 */
public class CheckOutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
        try{
            ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
            User user = (User) request.getSession().getAttribute("auth");

            if(!cart_list.isEmpty() && user != null){
                for(Cart c:cart_list){
                    Order order = new Order();
                    order.setId(c.getId());
                    order.setUid(user.getId());
                    order.setQuantity(c.getQuantity());

                    OrderDao orderDao = new OrderDao(DBConnection.getConnection());
                    boolean result = orderDao.insertOrder(order);
                    if(!result) break;
                }
                cart_list.clear();
//                PrintWriter out = response.getWriter();
                response.sendRedirect("orders.jsp");
            }else{
                if(user == null){
                                        response.sendRedirect("login.jsp");
                                        return;
                }
//                if(cart_list == null)
                    response.sendRedirect("cart.jsp");
            }
        }catch(IOException | ClassNotFoundException | SQLException ex){
        }
  
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
