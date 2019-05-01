package com.example.hades.lab1_da.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.hades.lab1_da.model.TheLoai;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context) {
        super(context, "quanlisach", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String theloai_table = "create table theloai_table" + "("
                + "_id text primary key," +
                "tenloai text," +
                "mota text,"+
                "vitri integer"+
                ")";
        String sach_table = "create table sach_table" + "("
                + "_id text primary key," +
                "_idloai text," +
                "tensach text," +
                "tacgia text,"+
                "nhaxuatban text,"+
                "giabia integer,"+
                "soluong integer"+
                ")";
        String hoadon_table = "create table hoadon_table" + "("
                + "_id text primary key," +
                "ngaymua text" +
                ")";
        String hoadonchitiet_table = "create table hoadonchitiet_table" + "("
                + "_id integer primary key autoincrement," +
                "_idhoadon integer," +
                "_idsach integer,"+
                "soluongmua integer"+
                ")";
        String nguoidung_table = "create table nguoidung_table" + "("
                + "username text primary key," +
                "passwork text," +
                "sodienthoai text,"+
                "hoten text"+
                ")";
        String account_table = "create table account_table" + "("
                + "username text primary key," +
                "passwork text" +
                ")";
        db.execSQL(theloai_table);
        db.execSQL(sach_table);
        db.execSQL(hoadon_table);
        db.execSQL(hoadonchitiet_table);
        db.execSQL(nguoidung_table);
        db.execSQL(account_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists theloai_table");
        db.execSQL("drop table if exists sach_table");
        db.execSQL("drop table if exists hoadon_table");
        db.execSQL("drop table if exists hoadonchitiet_table");
        db.execSQL("drop table if exists nguoidung_table");
        db.execSQL("drop table if exists account_table");
        onCreate(db);
    }

}
