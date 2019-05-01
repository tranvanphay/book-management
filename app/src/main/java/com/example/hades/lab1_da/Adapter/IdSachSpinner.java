package com.example.hades.lab1_da.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hades.lab1_da.R;
import com.example.hades.lab1_da.model.Sach;
import com.example.hades.lab1_da.model.TheLoai;

import java.util.ArrayList;

public class IdSachSpinner extends BaseAdapter {
    ArrayList<Sach> dss=new ArrayList<Sach>();
    Context c;

    public IdSachSpinner(ArrayList<Sach> dss, Context c) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inf=((Activity)c).getLayoutInflater();
        convertView=inf.inflate(R.layout.one_item_masach_spiner,null);
        TextView tv=convertView.findViewById(R.id.tv_idSach);
        Sach sach=dss.get(position);
        tv.setText(sach._id);

        return convertView;
    }
}
