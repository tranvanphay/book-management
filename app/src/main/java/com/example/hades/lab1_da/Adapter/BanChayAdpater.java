package com.example.hades.lab1_da.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hades.lab1_da.ActivityHoaDon;
import com.example.hades.lab1_da.DAO.HoaDonDAO;
import com.example.hades.lab1_da.R;
import com.example.hades.lab1_da.model.HoaDon;
import com.example.hades.lab1_da.model.Sach;

import java.util.ArrayList;

public class BanChayAdpater extends BaseAdapter  {
    ArrayList<Sach> dss=new ArrayList<Sach>();
    Context c;
    ListView lv_banchay;

    public BanChayAdpater(ArrayList<Sach> dss, Context c) {
        this.dss = dss;
        this.c = c;
    }

    @Override
    public int getCount() {
        return dss.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=((Activity)c).getLayoutInflater();
        convertView=inflater.inflate(R.layout.one_item_banchay,null);
        ImageView iv = convertView.findViewById(R.id.iv_item_sell);
        TextView tv_bookname = convertView.findViewById(R.id.tv1Bestsell);
        TextView tv_amount = convertView.findViewById(R.id.tv2Bestsell);
        Sach sach = dss.get(position);
        lv_banchay=convertView.findViewById(R.id.lv_BanChay);
        iv.setImageResource(R.drawable.cupgold);
        tv_bookname.setText(" Mã sách : " + sach.get_id());
        tv_amount.setText(" Số lượng bán :  " + sach.getSoluong());
        return convertView;
    }

}
