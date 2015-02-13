<%-- 
    Document   : index
    Created on : 12-feb-2015, 18:41:58
    Author     : Miguel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="registerProduct" method="post">
            <table>
                <thead></thead>
                <tbody>
                    <tr>
                        <td>
                            Codigo:
                        </td>
                        <td>
                            <input type="text" name="code"/>
                        </td>
                    </tr>
                    <tr>
                        <td>Name:</td>
                        <td><input type="text" name="name"/></td>
                    </tr>
                    <tr>
                        <td>Description:</td>
                        <td><input type="text" name="description"/></td>
                    </tr>
                    <tr>
                        <td>Cost:</td>
                        <td><input type="text" name="cost"/></td>
                    </tr>
                    <tr>
                        <td>Price:</td>
                        <td><input type="text" name="price"/></td>
                    </tr>
                    <tr>
                        <td colspan="2"><input type="submit" /></td>
                    </tr>
                </tbody>
            </table>
        </form>
    </body>
</html>
