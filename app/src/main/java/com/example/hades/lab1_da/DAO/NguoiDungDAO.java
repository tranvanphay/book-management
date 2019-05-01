package com.example.hades.lab1_da.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.hades.lab1_da.database.DbHelper;
import com.example.hades.lab1_da.model.NguoiDung;

import java.util.ArrayList;

public class NguoiDungDAO {
    SQLiteDatabase db;
    DbHelper dbHelper;
    public NguoiDungDAO(Context context) {
        dbHelper=new DbHelper(context);
        db=dbHelper.getWritableDatabase();
    }

    //Thêm người dùng
    public void insert(NguoiDung nguoiDung){
        ContentValues values=new ContentValues();
        values.put("username",nguoiDung.username);
        values.put("passwork",nguoiDung.passwork);
        values.put("sodienthoai",nguoiDung.sodienthoai);
        values.put("hoten",nguoiDung.hoten);
        db.insert("nguoidung_table",null,values);
    }

    //Sửa người dùng
    public void update(NguoiDung nguoiDung,String username){
        ContentValues values=new ContentValues();
        values.put("username",nguoiDung.username);
        values.put("passwork",nguoiDung.passwork);
        values.put("sodienthoai",nguoiDung.sodienthoai);
        values.put("hoten",nguoiDung.hoten);
        db.update("nguoidung_table",values,"username=?",new String[]{username});
    }


    //Xóa người dùng
    public void delete(String username){
        db.delete("nguoidung_table","username=?",new String[]{username});
        db.close();
    }


    //Show người dùng
    public ArrayList<NguoiDung> getNguoiDung(){
        db=dbHelper.getReadableDatabase();
        ArrayList<NguoiDung> dsnd=new ArrayList<NguoiDung>();
        Cursor cursor=db.query("nguoidung_table",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                String username= cursor.getString(0);
                String passwork= cursor.getString(1);
                String sodienthoai= cursor.getString(2);
                String hoten= cursor.getString(3);
                NguoiDung nguoiDung=new NguoiDung(username,passwork,sodienthoai,hoten);
                dsnd.add(nguoiDung);
            }while (cursor.moveToNext());
        }
        return dsnd;
    }
    //get pass
    public String getPass(String sodienthoai) {
        db = dbHelper.getReadableDatabase();
        String[] columns = new String[]{"passwork"};
        String selection = "sodienthoai=?";
        String[] selectionArgs = new String[]{sodienthoai};
        Cursor cursor = db.query("nguoidung_table", columns , selection, selectionArgs, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        NguoiDung nguoiDung = new NguoiDung(
                cursor.getString(1)
        );

        return nguoiDung.passwork;
    }
}
