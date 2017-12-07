package com.araceliteixeira.b2caccess.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.araceliteixeira.b2caccess.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by araceliteixeira on 07/12/17.
 */

public class ProductDAO extends SQLiteOpenHelper {
    public ProductDAO(Context context) {
        super(context, "c0712150test1", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Products (id INTEGER PRIMARY KEY, description TEXT NOT NULL, price REAL NOT NULL, discount REAL)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Products";
        db.execSQL(sql);
        onCreate(db);
    }
    public void dbInsert(Product product) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues productData = new ContentValues();
        productData.put("description", product.getDescription());
        productData.put("price", product.getPrice());
        productData.put("discount", product.getDiscount());

        db.insert("Products", null, productData);
    }

    public List<Product> dbSearch() {
        SQLiteDatabase db = getReadableDatabase();

        String sql = "SELECT * FROM Products;";

        Cursor c = db.rawQuery(sql, null);
        List<Product> products = new ArrayList<>();

        while (c.moveToNext()) {
            Product product = new Product();
            product.setId(c.getLong(c.getColumnIndex("id")));
            product.setDescription(c.getString(c.getColumnIndex("description")));
            product.setPrice(c.getDouble(c.getColumnIndex("price")));
            product.setDiscount(c.getDouble(c.getColumnIndex("discount")));
            products.add(product);
        }
        c.close();
        return products;
    }
}
