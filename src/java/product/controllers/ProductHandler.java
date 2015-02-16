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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import product.model.Product;
import product.model.ProductContext;

/**
 *
 * @author Miguel
 */
@WebServlet(name = "ProductHandler", urlPatterns = {"/product/ph"}) 
public class ProductHandler extends HttpServlet{
    
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        
        String code = req.getParameter("code");
        String name = req.getParameter("name");
        String desc = req.getParameter("description");
        String cost = req.getParameter("cost");
        String price = req.getParameter("price");
        String state = req.getParameter("state");
        
        if(code.isEmpty() || name.isEmpty() || cost.isEmpty() 
                || price.isEmpty() || state.isEmpty()){
            req.setAttribute("messages", "Debe introducir todos los parametros.");
            req.setAttribute("backPath", req.getContextPath());
            req.getRequestDispatcher("/result.jsp").forward(req, resp);
            return;
        }
        
        ProductContext p = new ProductContext(code);
        p.setDescription(desc);
        p.setCost(Double.valueOf(cost));
        p.setPrice(Double.valueOf(price));
        p.setName(name);
        p.setState(state);
        
        new Product(p).create();
        
        req.setAttribute("messages", String.format("El producto %s se ha ingresado exitosamente.", p.getName()));
        req.setAttribute("backPath", req.getContextPath());
        req.getRequestDispatcher("/result.jsp").forward(req, resp);
    }
    
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        try {
            req.setAttribute("list", new DatabaseHandlerProduct().getAll());
            req.getRequestDispatcher("/product/list.jsp").forward(req, resp);
        } catch (SQLException ex) {
            Logger.getLogger(ProductHandler.class.getName()).log(Level.SEVERE, null, ex);
            req.setAttribute("list", "ERROR");
            req.getRequestDispatcher("/product/list.jsp").forward(req, resp);
        }
    }
    
    
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
    }
    
    
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
    }
    
}
