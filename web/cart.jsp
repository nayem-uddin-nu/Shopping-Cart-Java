<%-- 
    Document   : cart
    Created on : Feb 22, 2023, 9:25:33 PM
    Author     : nayem
--%>

<%@page import="java.text.DecimalFormat"%>
<%@page import="com.mateshop.DBConnection.DBConnection"%>
<%@page import="com.mateshop.dao.ProductDao"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.mateshop.models.Cart"%>
<%@page import="com.mateshop.models.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    User auth = (User) request.getSession().getAttribute("auth");
    if(auth != null){
        request.setAttribute("auth", auth);
    }
    
    ArrayList<Cart> cart_list =(ArrayList<Cart>) session.getAttribute("cart-list");
    List<Cart> cartProducts = null;
    
    ProductDao pdao = new ProductDao(DBConnection.getConnection());
    double total = pdao.totalCartPrice(cart_list);

    
    if(cart_list != null){
        cartProducts = pdao.getCartProducts(cart_list);
        request.setAttribute("cart_list", cart_list);
        request.setAttribute("totalPrice", total);
    }
    
    DecimalFormat dcf = new DecimalFormat("#.##");
    request.setAttribute("dcf", dcf);
%>
<!DOCTYPE html>
<html>
   <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart Products | mateShop</title>
        <%@include  file="master/header.jsp"%>
    </head>
    <body>
        
        <%@include file="master/navbar.jsp" %>

        <div class="container">
            <div class="py-3 d-flex align-items-center">
                <div>
                    <h3>Total Price: $ ${(totalPrice>0) ? dcf.format(totalPrice) : 0} </h3>
                </div>
                <div>
                    <a href="check-out" class="mx-5 btn btn-warning btn-sm">Check Out</a>
                </div>
            </div>
            <table class="table table-loght">
                <thead>
                    <tr class="text-center">
                        <th scope="col">Image</th>
                        <th scope="col">Name</th>
                        <th scope="col">Category</th>
                        <th scope="col">Unit Price</th>
                        <th scope="col">Quantity</th>
                        <th scope="col">Price</th>
                        <!--<th scope="col">Buy Now</th>-->
                        <th scope="col">Action</th>
                    </tr>
                </thead>

                <tbody>
                    <% 
//                        out.println(cart_list);                      
//                        out.println(cartProducts);
                        if(cart_list != null){
                            for(Cart cartItem: cartProducts){%>
                                <tr class="text-center">
                                    <td><img src="static/images/<%= cartItem.getImage() %>" alt="product-image" height="70px" width="70px"></td>
                                    <td><%= cartItem.getName()%></td>
                                    <td><%= cartItem.getCategory() %></td>
                                    <td>$ <%= dcf.format(cartItem.getPrice() / cartItem.getQuantity())%></td>

                                    <td class="">
                                        <form action="order-now" method="post" class="form-inline">
                                            <input type="hidden" name="id" value="<%= cartItem.getId()%>" class="form-input w-50">
                                            <div class="form-group d-flex justify-content-between align-items-center">
                                                <a href="quantity-update?action=dec&id=<%= cartItem.getId()%>" type="button" class="btn btn-sm btn-decre text-danger"><i class="fas fa-minus-square"></i></a>
                                                <input type="number" name="quantity" class="form-control w-50"  value="<%= cartItem.getQuantity() %>" readonly min="1">
                                                <a href="quantity-update?action=inc&id=<%= cartItem.getId()%>" type="button" class="btn btn-sm btn-incre text-primary"><i class="fa fa-plus-square"></i></a>
                                                <button type="submit" class="btn btn-primary btn-sm">Buy</button>

                                            </div>
                                        </form>
                                    </td>
                                    <td>$<%= dcf.format(cartItem.getPrice())%></td>
                                    <!--<td><a herf="#" class="btn btn-primary btn-sm">Buy</a></td>-->
                                    <td><a href="remove-cart-product?id=<%= cartItem.getId() %>" class="btn btn-danger btn-sm" >Remove</a></td>
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
