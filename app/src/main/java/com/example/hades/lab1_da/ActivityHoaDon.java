package com.example.hades.lab1_da;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hades.lab1_da.Adapter.HoaDonAdapter;
import com.example.hades.lab1_da.Adapter.IdSachSpinner;
import com.example.hades.lab1_da.Adapter.SachAdapter;
import com.example.hades.lab1_da.Adapter.TheLoaiSpinner;
import com.example.hades.lab1_da.DAO.HoaDonChiTietDAO;
import com.example.hades.lab1_da.DAO.HoaDonDAO;
import com.example.hades.lab1_da.DAO.SachDAO;
import com.example.hades.lab1_da.model.HoaDon;
import com.example.hades.lab1_da.model.HoaDonChiTiet;
import com.example.hades.lab1_da.model.Sach;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class ActivityHoaDon extends AppCompatActivity {
    FloatingActionButton flb;
    Button btdate,btthanhtoan,btHuy;
    Dialog dialog;
    ListView lv;
    EditText etMaHoaDon,etSoLuongMua,etSearchHD;
    Spinner spnIdSach;
    SachDAO sachDAO;
    HoaDonDAO hoaDonDAO;
    HoaDonAdapter adapter;
    HoaDonChiTietDAO hoaDonChiTietDAO;
    ArrayList<Sach> dss=new ArrayList<Sach>();
    ArrayList<HoaDon> dshd=new ArrayList<HoaDon>();
    Calendar calendar=Calendar.getInstance();
    private DatePickerDialog.OnDateSetListener datepicker;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Hóa đơn");
        setContentView(R.layout.layout_hoadon);
        lv=findViewById(R.id.listhoadon);
        adapter=new HoaDonAdapter(dshd,ActivityHoaDon.this);
        flb=findViewById(R.id.flb_hoadon);
        hoaDonDAO=new HoaDonDAO(ActivityHoaDon.this);
        hoaDonChiTietDAO=new HoaDonChiTietDAO(ActivityHoaDon.this);
        updateUI();
        flb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sachDAO=new SachDAO(ActivityHoaDon.this);
                dialog=new Dialog(ActivityHoaDon.this);
                dialog.setContentView(R.layout.dialog_hoadon);
                spnIdSach=dialog.findViewById(R.id.spnidsach);
                dss=sachDAO.getSach();
                IdSachSpinner adapter=new IdSachSpinner(dss,ActivityHoaDon.this);
                spnIdSach.setAdapter(adapter);
                etMaHoaDon=dialog.findViewById(R.id.etMaHoaDon);
                etSoLuongMua=dialog.findViewById(R.id.etSoLuongMua);
                btdate=(dialog).findViewById(R.id.btDate);
                btthanhtoan=(dialog).findViewById(R.id.btThanhToan);
                btHuy=dialog.findViewById(R.id.btHuyHoaDon);
                dialog.show();
                datepicker =new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(Calendar.YEAR,year);
                        calendar.set(Calendar.MONTH,month);
                        calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                        updateLabel();
                    }
                };
                btdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                         DatePickerDialog datePickerDialog= new DatePickerDialog(ActivityHoaDon.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,datepicker,
                                calendar.get(Calendar.YEAR),
                                calendar.get(Calendar.MONTH),
                                calendar.get(Calendar.DAY_OF_MONTH));
                         datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                         datePickerDialog.show();

                    }
                });
                btthanhtoan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String id=etMaHoaDon.getText().toString();
                        String ngaymua=btdate.getText().toString();
                        int index=spnIdSach.getSelectedItemPosition();
                        String masach=dss.get(index)._id;
                        int soluongmua=Integer.parseInt(etSoLuongMua.getText().toString());
                        HoaDon hoaDon=new HoaDon(id,ngaymua);
                        hoaDonDAO.insert(hoaDon);
                        HoaDonChiTiet hoaDonChiTiet=new HoaDonChiTiet(masach,id,soluongmua);
                        hoaDonChiTietDAO.insert(hoaDonChiTiet);
                        updateUI();
                        dialog.dismiss();
                        final Dialog dialog1;
                        dialog1=new Dialog(ActivityHoaDon.this);
                        dialog1.setContentView(R.layout.dialog_thanhtoan);
                        TextView tv_tongtien;
                        Button ok;
                        tv_tongtien=dialog1.findViewById(R.id.tv_tongtien);
                        int tien=dss.get(index).giabia;
                        int tongtien=tien*soluongmua;
                        tv_tongtien.setText("Tổng: "+tongtien);
                        ok=dialog1.findViewById(R.id.bt_ok);
                        ok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog1.dismiss();
                            }
                        });
                        dialog1.show();
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
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                HoaDon hoaDon=dshd.get(position);
//                String maHD=hoaDon._id;
//                Intent i = new Intent(ActivityHoaDon.this,ActivityHoaDonChiTiet.class);
//                i.putExtra("idbill", maHD);
//                Toast.makeText(ActivityHoaDon.this, "Click on "+maHD, Toast.LENGTH_SHORT).show();
//                startActivity(i);
                    final Dialog dialog2 =new Dialog(ActivityHoaDon.this);
                    dialog2.setContentView(R.layout.dialog_hoa_don_chi_tiet);
                    TextView tv_maSach,tv_soLuong,tv_giaBia,tv_tongCong;
                    Button bt_oK;
                    tv_maSach=dialog2.findViewById(R.id.tv_maSach);
                    tv_soLuong=dialog2.findViewById(R.id.tv_soLuong);
                    tv_giaBia=dialog2.findViewById(R.id.tv_giaBia);
                    tv_tongCong=dialog2.findViewById(R.id.tv_tongCong);
                    bt_oK=dialog2.findViewById(R.id.bt_oK);
                    HoaDon hoaDon=dshd.get(position);
                    String _id=hoaDon._id;
                    hoaDonChiTietDAO=new HoaDonChiTietDAO(ActivityHoaDon.this);
                    HoaDonChiTiet hoaDonChiTiet=hoaDonChiTietDAO.getInfo(_id);
                    tv_maSach.setText("Mã sách: "+hoaDonChiTiet._idsach);
                    tv_soLuong.setText("Số lượng: "+hoaDonChiTiet.soluongmua);
                    tv_giaBia.setText("Giá bìa: "+(hoaDonChiTietDAO.tongTien(_id))/(hoaDonChiTiet.soluongmua));
                    tv_tongCong.setText("Tổng cộng: "+hoaDonChiTietDAO.tongTien(_id));
                    dialog2.show();
                    bt_oK.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog2.dismiss();
                        }
                    });





            }
        });

    }
    private void updateLabel() {
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        btdate.setText(sdf.format(calendar.getTime()));
    }
    public void updateUI(){
        dshd=hoaDonDAO.getHoaDon();
        HoaDonAdapter adapter=new HoaDonAdapter(dshd,ActivityHoaDon.this);
        lv.setAdapter(adapter);
    }
}
