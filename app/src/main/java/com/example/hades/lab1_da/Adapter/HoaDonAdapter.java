package com.example.hades.lab1_da.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hades.lab1_da.ActivityHoaDon;
import com.example.hades.lab1_da.ActivitySach;
import com.example.hades.lab1_da.DAO.HoaDonDAO;
import com.example.hades.lab1_da.DAO.SachDAO;
import com.example.hades.lab1_da.R;
import com.example.hades.lab1_da.model.HoaDon;
import com.example.hades.lab1_da.model.Sach;

import java.util.ArrayList;

public class HoaDonAdapter extends BaseAdapter  {
    ArrayList<HoaDon> dshd=new ArrayList<HoaDon>();
    Context c;

    public HoaDonAdapter(ArrayList<HoaDon> dshd, Context c) {
        this.dshd = dshd;
        this.c = c;
    }

    @Override
    public int getCount() {
        return dshd.size();
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
        convertView=inflater.inflate(R.layout.one_item_hoadon,null);
        TextView tv=convertView.findViewById(R.id.oneitemhoadon);
        final HoaDon hoaDon=dshd.get(position);
        tv.setText( "Mã hóa đơn: "+hoaDon._id+"\n"+" Ngày mua: "+hoaDon.ngaymua);
        ImageView img=convertView.findViewById(R.id.deleteHoaDon);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HoaDon hoaDon1=dshd.get(position);
                String hoadon=hoaDon1._id;
                HoaDonDAO hoaDonDAO=new HoaDonDAO(c);
                hoaDonDAO.delete(hoadon);
                ((ActivityHoaDon)c).updateUI();
                Toast.makeText(c, "Delete "+hoaDon1._id, Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }

}
