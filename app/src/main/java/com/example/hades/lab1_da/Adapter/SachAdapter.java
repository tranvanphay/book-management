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

import com.example.hades.lab1_da.ActivitySach;
import com.example.hades.lab1_da.DAO.SachDAO;
import com.example.hades.lab1_da.R;
import com.example.hades.lab1_da.model.Sach;
import com.example.hades.lab1_da.model.TheLoai;

import java.util.ArrayList;

public class SachAdapter extends BaseAdapter implements Filterable {
    ArrayList<Sach> dss = new ArrayList<Sach>();
    Context c;
    CustomFilter filter;
    ArrayList<Sach> filterListBook;

    public SachAdapter(ArrayList<Sach> dss, Context c) {
        this.dss = dss;
        this.c = c;
        this.filterListBook=dss;
    }

    @Override
    public int getCount() {
        return dss.size();
    }

    @Override
    public Object getItem(int position) {
        return dss.indexOf(getItem(position));
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity) c).getLayoutInflater();
        convertView = inflater.inflate(R.layout.one_item_sach, null);
        TextView tv = convertView.findViewById(R.id.oneitemsach);
        final Sach sach = dss.get(position);
        tv.setText(" Mã sách: " + sach.get_id() + "\n" + " Số Lượng: " + sach.getSoluong());
        ImageView img = convertView.findViewById(R.id.deleteSach);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sach sach1 = dss.get(position);
                String sach = sach1._id;
                SachDAO sachDAO = new SachDAO(c);
                sachDAO.delete(sach);
                ((ActivitySach) c).updateUI();
                Toast.makeText(c, "Delete " + sach1._id, Toast.LENGTH_SHORT).show();

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

    class CustomFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                constraint = constraint.toString().toUpperCase();

                ArrayList<Sach> filters = new ArrayList<Sach>();

                for (int i = 0; i < filterListBook.size(); i++) {
                    if (filterListBook.get(i)._id.toUpperCase().contains(constraint)) {
                        Sach sach = new Sach(filterListBook.get(i)._id, filterListBook.get(i)._idloai, filterListBook.get(i).tensach, filterListBook.get(i).tacgia,filterListBook.get(i).nhaxuatban,filterListBook.get(i).giabia,filterListBook.get(i).soluong);

                        filters.add(sach);
                    }
                }

                results.count = filters.size();
                results.values = filters;

            } else {
                results.count = filterListBook.size();
                results.values = filterListBook;
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            dss = (ArrayList<Sach>) results.values;
            notifyDataSetChanged();
        }
    }
}
