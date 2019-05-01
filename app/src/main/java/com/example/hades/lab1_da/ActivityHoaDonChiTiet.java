package com.example.hades.lab1_da;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.hades.lab1_da.DAO.HoaDonChiTietDAO;
import com.example.hades.lab1_da.model.HoaDonChiTiet;

import java.util.ArrayList;

public class ActivityHoaDonChiTiet extends AppCompatActivity {
    HoaDonChiTietDAO hoaDonChiTietDAO;
    ArrayList<HoaDonChiTiet> dshdct = new ArrayList<HoaDonChiTiet>();
    TextView tv1,tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don_chi_tiet);
        setTitle("Hóa đơn chi tiết");
        tv1 = findViewById(R.id.textView25);
        tv2 = findViewById(R.id.textView26);
        // Nhận intent
        String data = getIntent().getExtras().getString("idbill");

        hoaDonChiTietDAO = new HoaDonChiTietDAO(ActivityHoaDonChiTiet.this);

        HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietDAO.getInfo(data);

        tv1.setText("Mã sách : " + hoaDonChiTiet._idsach);
        tv2.setText("Số lượng mua : " + hoaDonChiTiet.soluongmua);


    }
}
