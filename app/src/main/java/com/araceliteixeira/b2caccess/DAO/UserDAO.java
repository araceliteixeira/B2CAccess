package com.araceliteixeira.b2caccess.DAO;

import com.araceliteixeira.b2caccess.model.User;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by araceliteixeira on 06/12/17.
 */

public class UserDAO extends SQLiteOpenHelper {
    public UserDAO(Context context) {
        super(context, "c0712150test1u", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Users (id INTEGER PRIMARY KEY, email TEXT NOT NULL, password TEXT NOT NULL)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Users";
        db.execSQL(sql);
        onCreate(db);
    }

    public void dbInsert(User user) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues userData = new ContentValues();
        userData.put("email", user.getEmail());
        userData.put("password", user.getPassword());

        db.insert("Users", null, userData);
    }

    public List<User> dbSearch() {
        SQLiteDatabase db = getReadableDatabase();

        String sql = "SELECT * FROM Users;";

        Cursor c = db.rawQuery(sql, null);
        List<User> users = new ArrayList<>();

        while (c.moveToNext()) {
            User user = new User();
            user.setId(c.getLong(c.getColumnIndex("id")));
            user.setEmail(c.getString(c.getColumnIndex("email")));
            user.setPassword(c.getString(c.getColumnIndex("password")));
            users.add(user);
        }
        c.close();
        return users;
    }
}
