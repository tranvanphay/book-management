package com.example.hades.lab1_da;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.hades.lab1_da.Adapter.SachAdapter;
import com.example.hades.lab1_da.Adapter.TheLoaiAdapter;
import com.example.hades.lab1_da.Adapter.TheLoaiSpinner;
import com.example.hades.lab1_da.DAO.LoaiDAO;
import com.example.hades.lab1_da.DAO.SachDAO;
import com.example.hades.lab1_da.database.DbHelper;
import com.example.hades.lab1_da.model.Sach;
import com.example.hades.lab1_da.model.TheLoai;

import java.util.ArrayList;

public class ActivitySach extends AppCompatActivity {
    FloatingActionButton flb;
    EditText etIdSach,etTenSach,etTacGia,etNhaXuatBan,etGiaBia,etSoLuong;
    Button btLuu,btHuy;
    ArrayList<TheLoai> dstl=new ArrayList<TheLoai>();
    ArrayList<Sach> dss=new ArrayList<Sach>();
    LoaiDAO loaiDAO;
    SachDAO sachDAO;
    Dialog dialog;
    ListView lv;
    DbHelper dbHelper=new DbHelper(ActivitySach.this);
    SearchView searchSach;
    SachAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_sach);
        setTitle("Sách");
        sachDAO=new SachDAO(ActivitySach.this);
        lv=findViewById(R.id.list_sach);
        flb=findViewById(R.id.flbsach);
        searchSach=findViewById(R.id.searchSach);
        updateUI();

        searchSach.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        flb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Spinner spnLoai;
                loaiDAO=new LoaiDAO(ActivitySach.this);
                dialog=new Dialog(ActivitySach.this);
                dialog.setContentView(R.layout.dialog_sach);
                etIdSach=dialog.findViewById(R.id.etIdSach);
                etTenSach=dialog.findViewById(R.id.etTenSach);
                spnLoai=dialog.findViewById(R.id.spnidloai);
                dstl= loaiDAO.getTheLoai();
                TheLoaiSpinner adapter=new TheLoaiSpinner(dstl,ActivitySach.this);
                spnLoai.setAdapter(adapter);
                etTacGia=dialog.findViewById(R.id.etTacGia);
                etNhaXuatBan=dialog.findViewById(R.id.etNhaXuatBan);
                etGiaBia=dialog.findViewById(R.id.etGiaBia);
                etSoLuong=dialog.findViewById(R.id.etSoLuong);
                btLuu=dialog.findViewById(R.id.btLuuSach);
                btHuy=dialog.findViewById(R.id.btHuySach);
                dialog.show();
                btLuu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String _idSach=etIdSach.getText().toString();
                        int i=spnLoai.getSelectedItemPosition();
                        String _idLoai=dstl.get(i)._id;
                        String TenSach=etTenSach.getText().toString();
                        String TacGia=etTacGia.getText().toString();
                        String NhaXuatBan=etNhaXuatBan.getText().toString();
                        int GiaBia=Integer.parseInt(etGiaBia.getText().toString());
                        int SoLuong=Integer.parseInt(etSoLuong.getText().toString());
                        Sach sach=new Sach(_idSach,_idLoai,TenSach,TacGia,NhaXuatBan,GiaBia,SoLuong);
                        sachDAO.insert(sach);
                        dialog.dismiss();
                        updateUI();
                    }
                });
                btHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                Sach sach=dss.get(position);
                final Dialog dialog=new Dialog(ActivitySach.this);
                dialog.setContentView(R.layout.dialog_sach);
                final EditText et_MaSach,et_TenSach,et_TacGia,et_NhaXuatBan,et_GiaBia,et_SoLuong;
                final Spinner spn_LoaiSach;
                Button bt_Them,bt_Huy;
                et_MaSach=dialog.findViewById(R.id.etIdSach);
                spn_LoaiSach=dialog.findViewById(R.id.spnidloai);
                loaiDAO=new LoaiDAO(ActivitySach.this);
                dstl= loaiDAO.getTheLoai();
                TheLoaiSpinner adapter=new TheLoaiSpinner(dstl,ActivitySach.this);
                spn_LoaiSach.setAdapter(adapter);
                et_TenSach=dialog.findViewById(R.id.etTenSach);
                et_TacGia=dialog.findViewById(R.id.etTacGia);
                et_NhaXuatBan=dialog.findViewById(R.id.etNhaXuatBan);
                et_GiaBia=dialog.findViewById(R.id.etGiaBia);
                et_SoLuong=dialog.findViewById(R.id.etSoLuong);
                bt_Huy=dialog.findViewById(R.id.btHuySach);
                bt_Them=dialog.findViewById(R.id.btLuuSach);
                dialog.show();
                et_MaSach.setText(sach._id);
                et_MaSach.setEnabled(false);
                et_TenSach.setText(sach.tensach);
                et_TacGia.setText(sach.tacgia);
                et_NhaXuatBan.setText(sach.nhaxuatban);
                et_GiaBia.setText(sach.giabia+"");
                et_SoLuong.setText(sach.soluong+"");
                bt_Them.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String _id=dss.get(position)._id;
                        int index=spn_LoaiSach.getSelectedItemPosition();
                        String _idLoai=dss.get(index)._idloai;
                        String tenSach=et_TenSach.getText().toString();
                        String tacGia=et_TacGia.getText().toString();
                        String nhaXuatBan=et_NhaXuatBan.getText().toString();
                        int giaBia=Integer.parseInt(et_GiaBia.getText().toString());
                        int soLuong=Integer.parseInt(et_SoLuong.getText().toString());
                        sachDAO=new SachDAO(ActivitySach.this);
                        Sach sach1=new Sach(_id,_idLoai,tenSach,tacGia,nhaXuatBan,giaBia,soLuong);
                        sachDAO.update(sach1,_id);
                        updateUI();
                        Toast.makeText(ActivitySach.this, "Thành công !", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                bt_Huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });





            }
        });
    }
    public void updateUI(){
        dss=sachDAO.getSach();
        adapter=new SachAdapter(dss,ActivitySach.this);
        lv.setAdapter(adapter);
    }
}
