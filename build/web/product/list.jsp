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
        <title>Product List</title>
    </head>
    <body>
        <h1>Product List</h1>
        <table>
            <tbody>
                <tr>
                    <th>
                        Code
                    </th>
                     <th>
                        Name
                    </th>
                    <th>
                        State
                    </th>
                    <th>
                        Description
                    </th>
                    
                    <th>
                        Cost
                    </th>
                    
                     <th>
                        Price
                    </th>
                    <th>
                        Editar
                    </th>
                    <th>
                        Eliminar
                    </th>
                </tr>
                <% ArrayList<ProductContext> l = (ArrayList<ProductContext>) request.getAttribute("list"); 
                   for(ProductContext p : l){ %>
                   <tr>
                       <td><%= p.getCode() %></td>
                       <td><%= p.getName() %></td>
                       <td><%= p.getState() %></td>
                       <td><%= p.getDescription() %></td>
                       <td><%= String.format("%.2f", p.getCost()) %></td>
                       <td><%= String.format("%.2f", p.getPrice()) %></td>
                       <td><form action="<%=%>" method="put"><input type="text" hidden="" name="edit" value="<%= p.getCode() %>" /><button type="submit">Edit</button></form></td>
                       <td><form action="<%=%>" method="delete"><input type="text" hidden="" name="delete" value="<%= p.getCode() %>" /><button type="submit">Delete</button></form></td>

                   </tr>
                <% } %>
            </tbody>
        </table>
    </body>
</html>
