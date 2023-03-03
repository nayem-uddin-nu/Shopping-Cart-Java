<%-- 
    Document   : home
    Created on : Feb 22, 2023, 4:10:06 PM
    Author     : nayem
--%>

<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.mateshop.models.Cart"%>
<%@page import="java.util.List"%>
<%@page import="com.mateshop.models.Product"%>
<%@page import="com.mateshop.dao.ProductDao"%>
<%@page import="com.mateshop.models.User"%>
<%@page import="com.mateshop.DBConnection.DBConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    User auth = (User) request.getSession().getAttribute("auth");
    if(auth != null){
        request.setAttribute("auth", auth);
    }
    
    
    ProductDao pdao = new ProductDao(DBConnection.getConnection());
    
    List<Product> products = pdao.getProducts();
    
    ArrayList<Cart> cart_list =(ArrayList<Cart>) session.getAttribute("cart-list");
    
    if(cart_list != null){
        request.setAttribute("cart_list", cart_list);
    }
    
    DecimalFormat dcf = new DecimalFormat();
    request.setAttribute("dcf", dcf);
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>mateShop | Buy your product easy</title>
        <%@include  file="master/header.jsp"%>
    </head>
    <body>
        
        <%@include file="master/navbar.jsp" %>

        <div class="container">
            <div class="card-header mt-5 bg-warning">
                <h6>All Products</h6>
            </div>
            <div class="row mt-5">
                <%
//                    for(int i=0; i<products.size(); i++){
                    if(!products.isEmpty()){
                        for(Product product: products){
                %>
                    <div class="col-md-3">
                        <div class="card w-100" style="width:18rem;">
                            <img src="static/images/<%= product.getImage()%>" height="200px" alt="" class="card-img-top">
                            <div class="card-body">
                                <h5 class="card-title"><%= product.getName()%></h5>
                                <h6 class="price">Price: $ <%= dcf.format(product.getPrice())%></h6>
                                <h6 class="category">Category: <%= product.getCategory()%></h6>
                                <div class="mt-3 d-flex justify-content-between">
                                    <!-- <a href="add-to-cart?id=<%=product.getId()%>" type="button" id="add-to-cart" class="btn btn-primary btn-sm">Add to Cart</a> -->
                                    <!-- <a href="add-to-cart?id=<%= product.getId()%>" type="button" id="add-to-cart" value="<%=product.getId()%>" onclick="addInCart(this.value)" class="btn btn-primary btn-sm">Add to Cart</a> -->
                                    <!-- <a href="add-to-cart?id=<%= product.getId()%>" type="button" id="add-to-cart" value="<%=product.getId()%>" onclick="addInCart(this.value)" class="btn btn-primary btn-sm">Add to Cart</a> -->
                                    <button id="add-to-cart" value="<%=product.getId()%>" onclick="addInCart(this.value)" class="btn btn-primary btn-sm">Add to Cart</button>
                                    <a href="order-now?quantity=1&id=<%= product.getId()%>" class="btn btn-warning btn-sm">Buy Now</a>
                                </div>
                            </div>
                        </div>
                    </div>
                <%
                    }
                   }else{%>
                   <h1 class="text-center text-danger">No Item to show or This site is in developing mode</h1>
                <%}%>
            </div>
        </div>

        
        <%@include file="master/footer.jsp" %>
    </body>
</html>
