package com.example.hades.lab1_da;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.hades.lab1_da.Adapter.BanChayAdpater;
import com.example.hades.lab1_da.DAO.SachDAO;
import com.example.hades.lab1_da.model.Sach;

import java.util.ArrayList;

public class ActivityBanChay extends AppCompatActivity {
    SachDAO sachDAO;
    ArrayList<Sach> dss=new ArrayList<Sach>();
    ListView lv_BanChay;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Sách bán chạy");
        setContentView(R.layout.layout_banchay);
        lv_BanChay=findViewById(R.id.lv_BanChay);
        showTop10();
    }
    public void showTop10()
    {
        sachDAO = new SachDAO(ActivityBanChay.this);
        dss = sachDAO.top10Books();
        BanChayAdpater banChayAdpater = new BanChayAdpater(dss,ActivityBanChay.this);
        lv_BanChay.setAdapter(banChayAdpater);
    }
}
