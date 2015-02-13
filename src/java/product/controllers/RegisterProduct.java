/*
 * The MIT License
 *
 * Copyright 2015 Miguel.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package product.controllers;

import db.impl.DatabaseHandlerProduct;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import product.model.Product;
import product.model.ProductContext;

/**
 *
 * @author Miguel
 */
@WebServlet(name = "RegisterProduct", urlPatterns = {"/rp"}) 
public class RegisterProduct extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        String code = req.getParameter("code");
        String name = req.getParameter("name");
        String desc = req.getParameter("description");
        String cost = req.getParameter("cost");
        String price = req.getParameter("price");
        if(code.isEmpty() || name.isEmpty() || desc.isEmpty() || cost.isEmpty() || price.isEmpty()){
            req.setAttribute("message", "Debe introducir todos los parametros.");
            req.getRequestDispatcher("result.jsp").forward(req, resp);
            req.setAttribute("backPath", req.getPathInfo());
            return;
        }
        ProductContext p = new ProductContext(code);
        p.setDescription(desc);
        p.setCost(Double.valueOf(cost));
        p.setPrice(Double.valueOf(price));
        p.setName(name);
        Product pr = new Product(p);
        pr.create();
        req.setAttribute("message", String.format("El producto %s se ha ingresado exitosamente.", pr.getProduct().getName()));
        req.setAttribute("backPath", req.getPathInfo());
        req.getRequestDispatcher("result.jsp").forward(req, resp);
    }
}
