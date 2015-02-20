<%-- 
    Document   : edit
    Created on : 19-feb-2015, 20:08:40
    Author     : Miguel
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="product.model.ProductContext"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editing transaction #${transaction.getId()}</title>
    </head>
    <body>
        <form action="th" method="put">
            <input type="hidden" name="_method" value="put"/>
            <input type="hidden" name="_action" value="form"/>
            <input type="hidden" name="_id" value="${transaction.getId()}"/>
            <table>
                <thead></thead>
                <tbody>
                    <tr>
                        <td>
                            Codigo del producto:
                        </td>
                        <td>
                            <select name="pCode" >
                                <% ArrayList<ProductContext> l = (ArrayList<ProductContext>) request.getAttribute("list");
                                    for(ProductContext p : l){
                                %>
                                <option value="<%= p.getCode() %>"><%= p.getCode() %></option>
                                <%}%>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Tipo de compra:</td>
                        <td><input type="text" name="pucharseType" value="${transaction.getPucharseType()}"/></td>
                    </tr>
                    <tr>
                        <td>Tipo de transaccion:</td>
                        <td><select name="TransactionType">
                                <option value="0">Salida</option>
                                <option value="1">Entrada</option>
                            </select></td>
                    </tr>
                    <tr>
                        <td>Cantidad:</td>
                        <td><input type="number" name="quantity" value="${transaction.getQuantity()}"/></td>
                    </tr>
                    <tr>
                        <td colspan="2"><input type="submit" /></td>
                    </tr>
                </tbody>
            </table>
        </form>
    </body>
</html>
