        <nav class="navbar navbar-expand-md bg-dark navbar-dark">
            <a href="/mateShop" class="navbar-brand nav-link mx-5">
                mateShop
            </a>
            <button class="navbar-toggler me-3" type="button" data-bs-toggle="collapse" data-bs-target="#navbar"
            aria-controls="navbar" aria-expanded="false" aria-label="Toggle Navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbar">
                <ul class="navbar-nav ms-auto me-5">
                    <li><a href="/mateShop" class="nav-link">Home</a></li>
                    <li><a href="cart.jsp" class="nav-link">Cart 
                            <span class="badge bg-primary" id="cart-item-count">${(cart_list.size() > 0) ? cart_list.size() : 0}</span>
                        </a></li>
                    <%if( auth != null){%>
                        <li><a href="orders.jsp" class="nav-link">Orders</a></li>
                        <li><a href="log-out" class="nav-link">Logout</a></li> 
                        <li><a href="#" class="nav-link"><%= auth.getName() %></a></li>
                    <%}
                    else{%>
                    <li><a href="login.jsp" class="nav-link">Login</a></li>
                    <%}%>
                </ul>
            </div>
        </nav>