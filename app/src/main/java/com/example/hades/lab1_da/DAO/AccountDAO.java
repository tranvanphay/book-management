package com.example.hades.lab1_da.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.hades.lab1_da.database.DbHelper;
import com.example.hades.lab1_da.model.Account;
import com.example.hades.lab1_da.model.NguoiDung;

import java.util.ArrayList;

public class AccountDAO {
    SQLiteDatabase db;
    DbHelper dbHelper;

    public AccountDAO(Context context) {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    //Thêm người dùng
    public void insert(Account account) {
        ContentValues values = new ContentValues();
        values.put("username", account.username);
        values.put("passwork", account.passwork);
        db.insert("account_table", null, values);
    }

    //Sửa người dùng
    public void update(Account account) {
        ContentValues values = new ContentValues();
        values.put("username", account.username);
        values.put("passwork", account.passwork);
        db.update("account_table", values, "_id=?", new String[]{account.username});
    }


    //Xóa người dùng
    public void delete(Account account) {
        db.delete("account_table", "_id=?", new String[]{account.username});
        db.close();
    }


    //Show người dùng
    public ArrayList<Account> getAccount() {
        db = dbHelper.getReadableDatabase();
        ArrayList<Account> dsac = new ArrayList<Account>();
        Cursor cursor = db.query("account_table", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String username = cursor.getString(0);
                String passwork = cursor.getString(1);
                Account account = new Account(username, passwork);
                dsac.add(account);
            } while (cursor.moveToNext());
        }
        return dsac;
    }

    public boolean checkLogin(String username, String passwork) {
        String[] columns = {"username"};
        db = dbHelper.getReadableDatabase();

        String selection = "username=? and passwork=?";
        String[] selectionArgs = {username, passwork};

        Cursor cursor = db.query("account_table", columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();

        cursor.close();

        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }
}
