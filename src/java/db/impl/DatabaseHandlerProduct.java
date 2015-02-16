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
package db.impl;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import db.DatabaseHandler;
import db.Database;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import product.model.ProductContext;

/**
 *
 * @author Miguel
 */
public class DatabaseHandlerProduct extends DatabaseHandler<ProductContext, String>{
    private static ConnectionSource cs;
    private static Dao<ProductContext, String> dao;
    public DatabaseHandlerProduct() throws SQLException{
        if(cs == null){
            if(debug)
                cs = new Database(dbURLDebug).getConnection();
            else
                cs = new Database(dbURL).getConnection();
            dao = DaoManager.createDao(cs, ProductContext.class);
        }
        //TableUtils.createTableIfNotExists(cs, ProductContext.class);
    }
    public Dao<ProductContext, String> getDao(){
        return dao;
    }
    @Override
    public List<ProductContext> getAll() {
        try {
            return dao.queryForAll();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandlerProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ProductContext get(String code) {
        try {
            return dao.queryForId(code);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandlerProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
