package com.example.hades.lab1_da.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.hades.lab1_da.database.DbHelper;
import com.example.hades.lab1_da.model.HoaDon;
import com.example.hades.lab1_da.model.TheLoai;

import java.util.ArrayList;

public class LoaiDAO {
    SQLiteDatabase db;
    DbHelper dbHelper;
    public LoaiDAO(Context context) {
        dbHelper=new DbHelper(context);
        db=dbHelper.getWritableDatabase();
    }

    //Thêm loại
    public void insert(TheLoai theLoai){
        ContentValues values=new ContentValues();
        values.put("_id",theLoai._id);
        values.put("tenloai",theLoai.tenloai);
        values.put("mota",theLoai.mota);
        values.put("vitri",theLoai.vitri);
        db.insert("theloai_table",null,values);
    }

    //Sửa loại
    public void update(TheLoai theLoai, String _id){
        db=dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("_id",theLoai._id);
        values.put("tenloai",theLoai.tenloai);
        values.put("mota",theLoai.mota);
        values.put("vitri",theLoai.vitri);
        db.update("theloai_table",values,"_id=?",new String[]{_id});
    }


    //Xóa thể loại
    public void delete(String theLoai){
        db.delete("theloai_table","_id=?",new String[]{theLoai});
        db.close();
    }


    //Show thể loại
    public ArrayList<TheLoai> getTheLoai(){
        db=dbHelper.getReadableDatabase();
        ArrayList<TheLoai> dstl=new ArrayList<TheLoai>();
        Cursor cursor=db.query("theloai_table",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                String _id= cursor.getString(0);
                String tenloai=cursor.getString(1);
                String mota=cursor.getString(2);
                int vitri=cursor.getInt(3);
                TheLoai theLoai=new TheLoai(_id,tenloai,mota,vitri);
                dstl.add(theLoai);
            }while (cursor.moveToNext());
        }
        return dstl;
    }

    //tìm_theo_ID
    public ArrayList<TheLoai> timLoai(String _id)
    {
        db = dbHelper.getWritableDatabase();
        ArrayList<TheLoai> dstl = new ArrayList<TheLoai>();
        Cursor c = db.rawQuery("SELECT * FROM theloai_table WHERE _id=?",new String[]{_id});
        if (c.moveToFirst())
        {
            do {
                String id = c.getString(0);
                String tenloai = c.getString(1);
                String mota=c.getString(2);
                int viTri=c.getInt(3);
                TheLoai theLoai = new TheLoai(id,tenloai,mota,viTri);
                dstl.add(theLoai);
            } while (c.moveToNext());
        }
        return dstl;
    }
}
