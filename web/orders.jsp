<%-- 
    Document   : orders
    Created on : Feb 22, 2023, 4:06:36 PM
    Author     : nayem
--%>

<%@page import="java.util.List"%>
<%@page import="com.mateshop.models.Order"%>
<%@page import="com.mateshop.DBConnection.DBConnection"%>
<%@page import="com.mateshop.dao.OrderDao"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.mateshop.models.Cart"%>
<%@page import="com.mateshop.models.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    User auth = (User) request.getSession().getAttribute("auth");
    List<Order> orders = null;
    if(auth != null){
        request.setAttribute("auth", auth);
        OrderDao oDao = new OrderDao(DBConnection.getConnection());
        orders = oDao.userOrders(auth.getId());
    }
    else{
        response.sendRedirect("login.jsp");
    }
    
    ArrayList<Cart> cart_list =(ArrayList<Cart>) session.getAttribute("cart-list");
    
    if(cart_list != null){
        request.setAttribute("cart_list", cart_list);
    }
    
    DecimalFormat dcf = new DecimalFormat("#.##");
    request.setAttribute("dcf", dcf);
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Orders | mateShop</title>
        <%@include  file="master/header.jsp"%>
    </head>
    <body>
        
        <%@include file="master/navbar.jsp" %>

        <div class="container mt-5">
         <table class="table table-loght">
                <thead>
                    <tr class="text-center">
                        <th scope="col">Image</th>
                        <th scope="col">Name</th>
                        <th scope="col">Category</th>
                        <th scope="col">Price</th>
                        <th scope="col">Quantity</th>
                        <th scope="col">Order Date</th>
                        <!--<th scope="col">Buy Now</th>-->
                        <th scope="col">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        if(orders != null){
                        String currDate[];
                            for(Order o:orders){
                            String date = o.getDate();
                            currDate = date.split(" ");
                            %>
                            <tr class="text-center">
                                    <td><img src="static/images/<%= o.getImage() %>" alt="product-image" height="70px" width="70px"></td>
                                    <td><%=o.getName()%></td>                                
                                    <td><%=o.getCategory()%></td>
                                    <td><%=dcf.format(o.getPrice())%></td>
                                    <td><%=o.getQuantity()%></td>
                                    <td><%=currDate[0]%></td>
                                    <td><a href="cancel-order?id=<%=o.getId()%>" class="btn btn-danger btn-sm">Cancel</a></td>
                            </tr>
                            <%}
                        }
                        %>
                </tbody>

            </table>
        </div>
        
        <%@include file="master/footer.jsp" %>
    </body>
</html>