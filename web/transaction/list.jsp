<%-- 
    Document   : list
    Created on : 13-feb-2015, 12:54:26
    Author     : Miguel
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="transaction.model.TransactionContext"%>
<%@page import="transaction.model.TransactionContext"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Transaction List</title>
    </head>
    <body>
        <h1>Transaction List</h1>
        <table>
            <tbody>
                <tr>
                    <th>
                        Product Code
                    </th>
                     <th>
                        Transaction Type
                    </th>
                    <th>
                        Pucharse Type
                    </th>
                    <th>
                        Quantity
                    </th>
                    
                    <th>
                        When
                    </th>
                    <th>
                    </th>
                    <th>
                    </th>
                </tr>
                <% ArrayList<TransactionContext> l = (ArrayList<TransactionContext>) request.getAttribute("list");
                   if(l.isEmpty()){ %>
                   <tr >
                       <td colspan="5">No hay transaccion para mostrar.</td>
                   </tr>
                   <% }else{
                   for(TransactionContext t : l){ %>
                   <tr>
                       <td><%= t.getProductCode() %></td>
                       <td><%= t.getTransactionType() == 0? "Salida" : "Entrada" %></td>
                       <td><%= t.getPucharseType() %></td>
                       <td><%= t.getQuantity() %></td>
                       <td><%= t.getCreateTime() %></td>
                       <td>
                           <form action="th" method="put">
                               <input type="hidden" name="_method" value="put" />
                               <input type="hidden" name="_action" value="edit"/>
                               <input type="hidden" name="_id" value="<%= t.getId() %>" />
                               <button type="submit">Edit</button>
                           </form>
                       </td>
                       <td>
                           <form action="th" method="delete">
                               <input type="hidden" name="_method" value="delete" />
                               <input type="hidden" name="_id" value="<%= t.getId() %>" />
                               <button type="submit">Delete</button>
                           </form>
                       </td>

                   </tr>
                <% }} %>
            </tbody>
        </table>
    </body>
</html>
