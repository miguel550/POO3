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
package transaction.controllers;

import db.impl.DatabaseHandlerTransaction;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import transaction.model.Transaction;
import transaction.model.TransactionContext;

/**
 *
 * @author Miguel
 */
@WebServlet(name = "TransactionHandler", urlPatterns = {"/transaction/th"}) 
public class TransactionHandler extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
        throws ServletException, IOException {
        
        String pCode = req.getParameter("pCode");
        String pucharseType = req.getParameter("pucharseType");
        String TransactionType = req.getParameter("TransactionType");
        String quantity = req.getParameter("quantity");
        
        if(pCode.isEmpty() || pucharseType.isEmpty() || TransactionType.isEmpty() 
                || quantity.isEmpty()){
            req.setAttribute("messages", "Debe introducir todos los parametros.");
            req.setAttribute("backPath", req.getContextPath());
            req.getRequestDispatcher("/result.jsp").forward(req, resp);
            return;
        }
        
        TransactionContext tc = new TransactionContext();
        tc.setProductCode(pCode);
        tc.setPucharseType(pucharseType);
        tc.setTransactionType(Integer.valueOf(TransactionType));
        tc.setQuantity(Integer.parseInt(quantity));
        
        new Transaction(tc).create();
        
        req.setAttribute("messages", String.format("La transaccion %s se ha hecho exitosamente.", tc.getId()));
        req.setAttribute("backPath", req.getContextPath());
        req.getRequestDispatcher("/result.jsp").forward(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                String method;
        if((method = req.getParameter("_method")) != null){
            
            switch (method) {
                case "put":
                    this.doPut(req, resp);
                    break;
                case "delete":
                    this.doDelete(req, resp);
                    break;
            }
            
        } else {
            
            try {
                req.setAttribute("list", new DatabaseHandlerTransaction().getAll());
                req.getRequestDispatcher("/transaction/list.jsp").forward(req, resp);
            } catch (SQLException ex) {
                Logger.getLogger(TransactionHandler.class.getName()).log(Level.SEVERE, null, ex);
                req.setAttribute("list", "ERROR");
                req.getRequestDispatcher("/transaction/list.jsp").forward(req, resp);
            }
            
        }
    }
    
}
