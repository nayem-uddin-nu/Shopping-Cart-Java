<%-- 
    Document   : login
    Created on : Feb 22, 2023, 4:06:36 PM
    Author     : nayem
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.mateshop.models.Cart"%>
<%@page import="com.mateshop.models.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    User auth = (User) request.getSession().getAttribute("auth");
    if(auth != null){
        request.setAttribute("auth", auth);
        response.sendRedirect("/mateShop");
    }
    
    ArrayList<Cart> cart_list =(ArrayList<Cart>) session.getAttribute("cart-list");
    
    if(cart_list != null){
        request.setAttribute("cart_list", cart_list);
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login | mateShop</title>
        <%@include  file="master/header.jsp"%>
    </head>
    <body>
        
        <%@include file="master/navbar.jsp" %>

        <!-- main content -->
        <div class="container my-5">
            <div class="row">
                <div class="col-md-6 mx-auto">
                    <!-- <h1>Hello</h1> -->
                    <div class="card">
                        <div class="card-header text-center">
                            <h2>Login | mateShop</h2>
                        </div>
                        <div class="card-body">
                            <form action="user-login" method="post">
                                
                                <div class="row my-2">
                                    <div class="col-md-4">
                                        <h6>Email Address</h6>
                                    </div>
                                    <div class="col-md-8">
                                        <input type="email" class="form-control" name="email" placeholder="Enter your e-mail" required/>
                                    </div>
                                </div>

                                <div class="row my-2">
                                    <div class="col-md-4">
                                        <h6>Password</h6>
                                        <!-- <label for="">Password</label> -->
                                    </div>
                                    <div class="col-md-8">
                                        <input type="password" class="form-control" name="password" placeholder="********" required/>
                                    </div>
                                </div>
                                <div class="row my-2">
                                    <div class="col-md-4">
                                        <!--<h6>Password</h6>-->
                                        <!-- <label for="">Password</label> -->
                                    </div>
                                    <div class="col-md-8">
                                        <input type="submit" class="btn btn-primary btn-sm" value="Login"/>
                                    </div>
                                </div>
                                
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        <%@include file="master/footer.jsp" %>
    </body>
</html>