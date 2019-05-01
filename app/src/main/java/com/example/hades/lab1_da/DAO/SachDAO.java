package com.example.hades.lab1_da.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.hades.lab1_da.database.DbHelper;
import com.example.hades.lab1_da.model.Sach;

import java.util.ArrayList;

public class SachDAO {
    SQLiteDatabase sqLiteDatabase;
    DbHelper dbHelper;
    public SachDAO(Context context) {
        dbHelper=new DbHelper(context);
        sqLiteDatabase=dbHelper.getWritableDatabase();
    }

    //Thêm sách
    public void insert(Sach sach){
        ContentValues values=new ContentValues();
        values.put("_id",sach._id);
        values.put("_idloai",sach._idloai);
        values.put("tensach",sach.tensach);
        values.put("tacgia",sach.tacgia);
        values.put("nhaxuatban",sach.nhaxuatban);
        values.put("giabia",sach.giabia);
        values.put("soluong",sach.soluong);
        sqLiteDatabase.insert("sach_table",null,values);
    }

    //Sửa sách
    public void update(Sach sach,String _id){
        ContentValues values=new ContentValues();
        values.put("_id",sach._id);
        values.put("_idloai",sach._idloai);
        values.put("tensach",sach.tensach);
        values.put("tacgia",sach.tacgia);
        values.put("nhaxuatban",sach.nhaxuatban);
        values.put("giabia",sach.giabia);
        values.put("soluong",sach.soluong);
        sqLiteDatabase.update("sach_table",values,"_id=?",new String[]{_id});
    }


    //Xóa sách
    public void delete(String sach){
        sqLiteDatabase.delete("sach_table","_id=?",new String[]{sach});
        sqLiteDatabase.close();
    }


    //Show sách
    public ArrayList<Sach> getSach(){
        sqLiteDatabase=dbHelper.getReadableDatabase();
        ArrayList<Sach> dss=new ArrayList<Sach>();
        Cursor cursor=sqLiteDatabase.query("sach_table",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                String _id= cursor.getString(0);
                String _idloai=cursor.getString(1);
                String tensach=cursor.getString(2);
                String tacgia=cursor.getString(3);
                String nhaxuatban=cursor.getString(4);
                int giabia=cursor.getInt(5);
                int soluong=cursor.getInt(6);
                Sach sach=new Sach(_id,_idloai,tensach,tacgia,nhaxuatban,giabia,soluong);
                dss.add(sach);
            }while (cursor.moveToNext());
        }
        return dss;
    }
    //top 10 sách
    public ArrayList<Sach> top10Books()
    {
        sqLiteDatabase = dbHelper.getWritableDatabase();
        ArrayList<Sach> dss = new ArrayList<Sach>();
        Cursor c = sqLiteDatabase.rawQuery("SELECT _idsach, SUM(soluongmua) as 'soluongmua' " +
                "FROM hoadonchitiet_table INNER JOIN hoadon_table ON hoadonchitiet_table._idhoadon=hoadon_table._id" +
                " WHERE strftime('%Y-%m',ngaymua)=strftime('%Y-%m',('now'))" +
                " GROUP BY _idsach ORDER BY soluongmua DESC LIMIT 10",null);
        if (c.moveToFirst())
        {
            do {
                Sach sach = new Sach();
                sach.set_id(c.getString(0));
                sach.setSoluong(c.getInt(1));
                dss.add(sach);
            } while (c.moveToNext());
        }
        return dss;
    }
}
