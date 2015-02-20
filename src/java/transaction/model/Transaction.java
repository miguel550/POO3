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
package transaction.model;

import db.ICRUD;
import db.impl.DatabaseHandlerTransaction;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miguel
 */
public class Transaction implements ICRUD{
    private TransactionContext tc;
    private DatabaseHandlerTransaction dht;
    public Transaction(TransactionContext tc){
        this.tc = tc;
        try {
            this.dht = new DatabaseHandlerTransaction();
        } catch (SQLException ex) {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Hubo un error inicializando DatabaseHandlerTransaction en Transaction");
        }
    }
    public TransactionContext getTransaction(){
        return this.tc;
    }
    @Override
    public void create() {
        try {
            dht.getDao().create(tc);
        } catch (SQLException ex) {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Hubo un error en create de transaction");
        }
    }

    @Override
    public void read() {
        try {
            tc = dht.getDao().queryForId(tc.getId());
        } catch (SQLException ex) {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Hubo un error en read de transaction");
        }
    }

    @Override
    public void update() {
                tc.edited();
        try {
            dht.getDao().update(tc);
        } catch (SQLException ex) {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Hubo un error en update de transaction");
        }
    }

    @Override
    public void delete() {
        try {
            dht.getDao().delete(tc);
        } catch (SQLException ex) {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Hubo un error en delete de transaction");
        }
    }
    
}
