/*
 * The MIT License
 *
 * Copyright 2015 apsq.
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
package download.controllers;

import db.impl.DatabaseHandlerProduct;
import db.impl.DatabaseHandlerTransaction;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import product.model.ProductContext;
import transaction.model.TransactionContext;

/**
 *
 * @author apsq
 */
@WebServlet(name = "DownloadHandler", urlPatterns = {"/download"})
public class DownloadHandler extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         String fileName = "dump_",
                fileType = "txt",
                dateNow = new SimpleDateFormat("dd-MM-yyyy").format(new Date()),
                wholeName; 
         
         
         fileName += dateNow;
         wholeName = wholeFileName(fileName, fileType);
         
         response.setContentType(fileType);
         response.setHeader("Content-disposition","attachment; filename="+
                             wholeName);

        File my_file = new File(wholeName);
        
        try (BufferedWriter output = new BufferedWriter(new FileWriter(my_file))) {
            output.write(getTransationContent());
        }
         
         // This should send the file to the browser
         OutputStream out = response.getOutputStream();
        try (FileInputStream in = new FileInputStream(my_file)) {
            byte[] buffer = new byte[4096];
            int length;
            while ((length = in.read(buffer)) > 0){
                out.write(buffer, 0, length);
            }
        }
         out.flush();
        
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    private String wholeFileName(String fileName, String fileType){
        return fileName+"."+fileType;
    }

    private String getTransationContent() {
        String file = "";
        try {
            ArrayList<ProductContext> arrP = (ArrayList<ProductContext>) new DatabaseHandlerProduct().getAll();
            ArrayList<TransactionContext> arrT;
            for(ProductContext pc : arrP){
                file += String.format("Producto: %s  %s\n", pc.getCode(), pc.getName());
                file += String.format("%s %s %s %s\n", 
                        "Fecha", 
                        "Tipo compra", 
                        "Tipo transaccion", 
                        "Cantidad");
                arrT = (ArrayList<TransactionContext>)new DatabaseHandlerTransaction().getDao().queryForEq("productCode", pc.getCode());
                if(!arrT.isEmpty()){
                    for(TransactionContext tc : arrT){
                        file += String.format("%s %s %s %s\n", 
                                new SimpleDateFormat("dd-MM-yyyy").format(tc.getCreateTime()), 
                                tc.getPucharseType(), tc.getTransactionType(), 
                                tc.getQuantity());
                    }
                }else{
                    file += String.format("No hay transacciones con este producto.\n");
                }
                file += String.format("Existencia actual: %d\n", 0);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DownloadHandler.class.getName()).log(Level.SEVERE, null, ex);
            file += String.format("Hubo un error.");
        }
        return file;
    }


}