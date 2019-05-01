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

import com.example.hades.lab1_da.ActivityLoai;
import com.example.hades.lab1_da.DAO.LoaiDAO;
import com.example.hades.lab1_da.R;
import com.example.hades.lab1_da.database.DbHelper;
import com.example.hades.lab1_da.model.TheLoai;

import java.util.ArrayList;

public class TheLoaiAdapter extends BaseAdapter implements Filterable {
    ArrayList<TheLoai> dstl=new ArrayList<TheLoai>();
    Context c;
    CustomFilter filter;
    ArrayList<TheLoai> filterListBook;
    public TheLoaiAdapter(ArrayList<TheLoai> dstl, Context c) {
        this.dstl = dstl;
        this.c = c;
        this.filterListBook=dstl;
    }

    @Override
    public int getCount() {
        return dstl.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return dstl.indexOf(getItem(position));
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=((Activity)c).getLayoutInflater();
        convertView=inflater.inflate(R.layout.one_item_theloai,null);
        TextView textView=convertView.findViewById(R.id.tvTheLoai);
        final TheLoai theLoai=dstl.get(position);
        textView.setText(" Mã loại: "+theLoai._id+"\n"+" Tên loại: "+theLoai.tenloai);
        ImageView img=convertView.findViewById(R.id.imgdelete);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TheLoai theLoai1=dstl.get(position);
                String theloai=theLoai1._id;
                LoaiDAO loaiDAO=new LoaiDAO(c);
                loaiDAO.delete(theloai);
                ((ActivityLoai)c).updateUI();
                Toast.makeText(c, "delete "+theLoai._id, Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }

    @Override
    public Filter getFilter() {
        if (filter == null)
        {
            filter = new CustomFilter();
        }
        return filter;
    }
    class CustomFilter extends Filter
    {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length()>0)
            {
                constraint = constraint.toString().toUpperCase();

                ArrayList<TheLoai> filters = new ArrayList<TheLoai>();

                for (int i=0;i<filterListBook.size();i++)
                {
                    if (filterListBook.get(i)._id.toUpperCase().contains(constraint))
                    {
                        TheLoai theLoai = new TheLoai(filterListBook.get(i)._id,filterListBook.get(i).tenloai,filterListBook.get(i).mota,filterListBook.get(i).vitri);

                        filters.add(theLoai);
                    }
                }

                results.count=filters.size();
                results.values=filters;

            } else
            {
                results.count=filterListBook.size();
                results.values=filterListBook;
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            dstl = (ArrayList<TheLoai>) results.values;
            notifyDataSetChanged();
        }
    }
}
