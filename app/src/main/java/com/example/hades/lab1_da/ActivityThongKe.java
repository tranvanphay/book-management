package com.example.hades.lab1_da;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.hades.lab1_da.DAO.HoaDonChiTietDAO;

public class ActivityThongKe extends AppCompatActivity {
    TextView tv_Ngay,tv_Thang,tv_Nam;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Thống kê");
        setContentView(R.layout.layout_thongke);
        tv_Ngay=findViewById(R.id.tv_Ngay);
        tv_Thang=findViewById(R.id.tv_Thang);
        tv_Nam=findViewById(R.id.tv_Nam);
        HoaDonChiTietDAO hoaDonChiTietDAO=new HoaDonChiTietDAO(ActivityThongKe.this);
        tv_Ngay.setText("Hôm nay:" + hoaDonChiTietDAO.getSumByDate());
        tv_Thang.setText("Tháng này:" + hoaDonChiTietDAO.getSumByMonth());
        tv_Nam.setText("Năm này:" + hoaDonChiTietDAO.getSumByYear());

    }

}
