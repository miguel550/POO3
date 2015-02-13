<%-- 
    Document   : list
    Created on : 13-feb-2015, 13:05:48
    Author     : Miguel
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="product.model.ProductContext"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <table>
            <tbody>
                <tr>
                    <th>
                        Code
                    </th>
                     <th>
                        Code
                    </th>
                </tr>
                <% ArrayList<ProductContext> l = (ArrayList<ProductContext>) request.getAttribute("list"); 
                   for(ProductContext p : l){ %>
                   <tr>
                       
                   </tr>
                <% } %>
            </tbody>
        </table>
    </body>
</html>
