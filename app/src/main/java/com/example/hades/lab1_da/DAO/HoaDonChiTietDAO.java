package com.example.hades.lab1_da.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.hades.lab1_da.database.DbHelper;
import com.example.hades.lab1_da.model.HoaDon;
import com.example.hades.lab1_da.model.HoaDonChiTiet;
import com.example.hades.lab1_da.model.TheLoai;

import java.util.ArrayList;

public class HoaDonChiTietDAO {
    SQLiteDatabase db;
    DbHelper dbHelper;
    public HoaDonChiTietDAO(Context context) {
        dbHelper=new DbHelper(context);
        db=dbHelper.getWritableDatabase();
    }

    //Thêm hóa đơn chi tiết
    public void insert(HoaDonChiTiet hoaDonChiTiet){
        ContentValues values=new ContentValues();
        values.put("_idhoadon",hoaDonChiTiet._idhoadon);
        values.put("_idsach",hoaDonChiTiet._idsach);
        values.put("soluongmua",hoaDonChiTiet.soluongmua);
        db.insert("hoadonchitiet_table",null,values);
    }

    //Sửa hóa đơn chi tiết
    public void update(HoaDonChiTiet hoaDonChiTiet){
        ContentValues values=new ContentValues();
        values.put("_idhoadon",hoaDonChiTiet._idhoadon);
        values.put("_idsach",hoaDonChiTiet._idsach);
        values.put("soluongmua",hoaDonChiTiet.soluongmua);
        db.update("hoadonchitiet_table",values,"_id=?",new String[]{hoaDonChiTiet._id+""});
    }


    //Xóa hóa đơn chi tiết
    public void delete(HoaDonChiTiet hoaDonChiTiet){
        db.delete("hoadonchitiet_table","_id=?",new String[]{hoaDonChiTiet._id+""});
        db.close();
    }


    //Show hóa đơn chi tiết
    public ArrayList<HoaDonChiTiet> getHoaDonChiTiet(){
        db=dbHelper.getReadableDatabase();
        ArrayList<HoaDonChiTiet> dshdct=new ArrayList<HoaDonChiTiet>();
        Cursor cursor=db.query("hoadonchitiet_table",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                int _id= cursor.getInt(0);
                String _idhoadon= cursor.getString(1);
                String _idsach= cursor.getString(2);
                int soluongmua=cursor.getInt(3);
                HoaDonChiTiet hoaDonChiTiet=new HoaDonChiTiet(_id,_idhoadon,_idsach,soluongmua);
                dshdct.add(hoaDonChiTiet);
            }while (cursor.moveToNext());
        }
        return dshdct;
    }

    public HoaDonChiTiet getInfo(String id) {
        db = dbHelper.getReadableDatabase();
        String[] columns = new String[]{"_id","_idhoadon","_idsach","soluongmua"};
        String selection = "_idhoadon=?";
        String[] selectionArgs = new String[]{id};
        Cursor cursor = db.query("hoadonchitiet_table", columns , selection, selectionArgs, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getInt(3));
        return hoaDonChiTiet;
    }
    public int getSumByDate(){
        int sum = 0;
        db = dbHelper.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT SUM(tongtien) from (SELECT SUM(sach_table.giabia * hoadonchitiet_table.soluongmua) as 'tongtien' " +
                "FROM hoadon_table INNER JOIN hoadonchitiet_table on hoadon_table._id=hoadonchitiet_table._idhoadon " +
                "INNER JOIN sach_table on hoadonchitiet_table._idsach=sach_table._id where ngaymua = date('now'))",null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            sum = c.getInt(0);
            c.moveToNext();
        }
        c.close();

        return sum;
    }

    public int getSumByMonth(){
        int sum = 0;
        db = dbHelper.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT SUM(tongtien) from (SELECT SUM(sach_table.giabia * hoadonchitiet_table.soluongmua) as 'tongtien' FROM hoadon_table INNER JOIN hoadonchitiet_table on hoadon_table._id=hoadonchitiet_table._idhoadon INNER JOIN sach_table on hoadonchitiet_table._idsach=sach_table._id where strftime('%Y-%m',ngaymua) = strftime('%Y-%m','now'))",null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            sum = c.getInt(0);
            c.moveToNext();
        }
        c.close();
        return sum;
    }

    public int getSumByYear() {
        int sum = 0;
        db = dbHelper.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT SUM(tongtien) from (SELECT SUM(sach_table.giabia * hoadonchitiet_table.soluongmua) as 'tongtien' FROM hoadon_table INNER JOIN hoadonchitiet_table on hoadon_table._id=hoadonchitiet_table._idhoadon INNER JOIN sach_table on hoadonchitiet_table._idsach=sach_table._id where strftime('%Y',ngaymua) = strftime('%Y','now'))", null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            sum = c.getInt(0);
            c.moveToNext();
        }
        c.close();
        return sum;
    }
    public int tongTien(String _id)
    {
        db = dbHelper.getWritableDatabase();
        int total = 0;
        Cursor c = db.rawQuery("SELECT SUM(sach_table.giabia*hoadonchitiet_table.soluongmua) as 'total' FROM hoadon_table INNER JOIN hoadonchitiet_table ON hoadon_table._id=hoadonchitiet_table._idhoadon INNER JOIN sach_table ON hoadonchitiet_table._idsach=sach_table._id WHERE hoadon_table._id=?",new String[]{_id});
        if (c.moveToFirst())
            do {
                total = c.getInt(0);
            } while (c.moveToNext());
        return total;
    }

}
