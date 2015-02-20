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
package product.model;

import db.ICRUD;
import db.impl.DatabaseHandlerProduct;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miguel
 */
public class Product implements ICRUD{
    private ProductContext pc;
    private DatabaseHandlerProduct dhp;
    public Product(ProductContext pc){
        this.pc = pc;
        try {
            this.dhp = new DatabaseHandlerProduct();
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Hubo un error inicializando DatabaseHandlerProduct");
        }
    }
    public ProductContext getProduct(){
        return this.pc;
    }
    public DatabaseHandlerProduct getDHP(){
        return this.dhp;
    }
    @Override
    public void create() {
        try {
            dhp.getDao().create(pc);
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Hubo un error en create de transaction");
        }
    }

    @Override
    public void read() {
        try {
            pc = dhp.getDao().queryForId(pc.getCode());
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Hubo un error en read de product");
        }
    }

    @Override
    public void update() {
        pc.edited();
        try {
            dhp.getDao().update(pc);
            if(pc.isCodeChange()) dhp.getDao().updateId(pc, pc.getNewCode());
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Hubo un error en update de transaction");
        }
    }

    @Override
    public void delete() {
        try {
            dhp.getDao().delete(pc);
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Hubo un error en delete de product");
        }
    }
}
