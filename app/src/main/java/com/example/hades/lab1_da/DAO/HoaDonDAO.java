package com.example.hades.lab1_da.DAO;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.hades.lab1_da.database.DbHelper;
import com.example.hades.lab1_da.model.HoaDon;
import java.util.ArrayList;
public class HoaDonDAO {
    SQLiteDatabase db;
    DbHelper dbHelper;
    public HoaDonDAO(Context context) {
        dbHelper=new DbHelper(context);
        db=dbHelper.getWritableDatabase();
    }

    //Thêm hóa đơn
    public void insert(HoaDon hoaDon){
        ContentValues values=new ContentValues();
        values.put("_id",hoaDon._id);
        values.put("ngaymua",hoaDon.ngaymua);
        db.insert("hoadon_table",null,values);
    }

    //Sửa Hóa đơn
    public void update(HoaDon hoaDon){
        ContentValues values=new ContentValues();
        values.put("ngaymua",hoaDon.ngaymua);
        db.update("hoadon_table",values,"_id=?",new String[]{hoaDon._id+""});
    }


    //Xóa hóa đơn
    public void delete(String hoaDon){
        db.delete("hoadon_table","_id=?",new String[]{hoaDon});
        db.close();
    }


    //Show hóa đơn
    public ArrayList<HoaDon> getHoaDon(){
        db=dbHelper.getReadableDatabase();
        ArrayList<HoaDon> dshd=new ArrayList<HoaDon>();
        Cursor cursor=db.query("hoadon_table",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                String _id= cursor.getString(0);
                String ngaymua=cursor.getString(1);
                HoaDon hoaDon=new HoaDon(_id,ngaymua);
                dshd.add(hoaDon);
            }while (cursor.moveToNext());
        }
        return dshd;
    }
    //get_by_ID
    public ArrayList<HoaDon> timHD(String _id)
    {
        db = dbHelper.getWritableDatabase();
        ArrayList<HoaDon> dshd = new ArrayList<HoaDon>();
        Cursor c = db.rawQuery("SELECT * FROM hoadon_table WHERE _id=?",new String[]{_id});
        if (c.moveToFirst())
        {
            do {
                String id = c.getString(0);
                String ngaymua = c.getString(1);
                HoaDon hoaDon = new HoaDon(id,ngaymua);
                dshd.add(hoaDon);
            } while (c.moveToNext());
        }
        return dshd;
    }
}
