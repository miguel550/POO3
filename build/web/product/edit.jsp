<%-- 
    Document   : edit
    Created on : 15-feb-2015, 22:12:44
    Author     : Miguel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editing ${product.getName()}</title>
    </head>
    <body>
        <form action="ph" method="put">
            <input type="hidden" name="_method" value="put"/>
            <input type="hidden" name="_action" value="form"/>
            <table>
                <thead></thead>
                <tbody>
                    <tr>
                        <td>
                            Codigo:
                        </td>
                        <td>
                            <input type="text" name="code" value="${product.getCode()}"/>
                        </td>
                    </tr>
                    <tr>
                        <td>Name:</td>
                        <td><input type="text" name="name" value="${product.getName()}"/></td>
                    </tr>
                    <tr>
                        <td>State:</td>
                        <td><input type="text" name="state" value="${product.getState()}"/></td>
                    </tr>
                    <tr>
                        <td>Description:</td>
                        <td><input type="text" name="description" value="${product.getDescription()}"/></td>
                    </tr>
                    <tr>
                        <td>Cost:</td>
                        <td><input type="text" name="cost" value="${product.getCost()}"/></td>
                    </tr>
                    <tr>
                        <td>Price:</td>
                        <td><input type="text" name="price" value="${product.getPrice()}"/></td>
                    </tr>
                    <tr>
                        <td colspan="2"><input type="submit"/></td>
                    </tr>
                </tbody>
            </table>
        </form>
    </body>
</html>
