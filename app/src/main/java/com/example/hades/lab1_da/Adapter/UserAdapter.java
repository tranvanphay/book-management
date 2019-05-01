package com.example.hades.lab1_da.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hades.lab1_da.ActivityAddUser;
import com.example.hades.lab1_da.ActivityLoai;
import com.example.hades.lab1_da.DAO.LoaiDAO;
import com.example.hades.lab1_da.DAO.NguoiDungDAO;
import com.example.hades.lab1_da.R;
import com.example.hades.lab1_da.model.NguoiDung;
import com.example.hades.lab1_da.model.TheLoai;

import java.util.ArrayList;

public class UserAdapter extends BaseAdapter {
    ArrayList<NguoiDung> dsnd=new ArrayList<NguoiDung>();
    Context c;
    public UserAdapter(ArrayList<NguoiDung> dsnd, Context c) {
        this.dsnd = dsnd;
        this.c = c;
    }

    @Override
    public int getCount() {
        return dsnd.size();
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
        convertView=inflater.inflate(R.layout.one_item_user,null);
        TextView textView=convertView.findViewById(R.id.tv_User);
        final NguoiDung nguoiDung=dsnd.get(position);
        textView.setText(" Họ tên: "+nguoiDung.hoten+"\n"+" Số điện thoại: "+"\n"+nguoiDung.sodienthoai);
        ImageView img=convertView.findViewById(R.id.deleteUser);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NguoiDung nguoiDung1=dsnd.get(position);
                String nguoiDung=nguoiDung1.username;
                NguoiDungDAO nguoiDungDAO=new NguoiDungDAO(c);
                nguoiDungDAO.delete(nguoiDung);
                ((ActivityAddUser)c).updateUI();
                Toast.makeText(c, "Delete "+nguoiDung1.hoten, Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }
}
