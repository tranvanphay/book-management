package com.example.hades.lab1_da;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hades.lab1_da.Adapter.TheLoaiAdapter;
import com.example.hades.lab1_da.Adapter.UserAdapter;
import com.example.hades.lab1_da.DAO.AccountDAO;
import com.example.hades.lab1_da.DAO.NguoiDungDAO;
import com.example.hades.lab1_da.model.Account;
import com.example.hades.lab1_da.model.NguoiDung;

import java.util.ArrayList;

public class ActivityAddUser extends AppCompatActivity {
    FloatingActionButton flb_addusers;
    Button btLuu,btHuy;
    EditText etUserName,etPasswork,etSoDienThoai,etHoTen;
    NguoiDungDAO nguoiDungDAO;
    AccountDAO accountDAO;
    ListView lv;
    ArrayList<NguoiDung> dsnd=new ArrayList<NguoiDung>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Thêm người dùng");
        setContentView(R.layout.layout_adduser);
        nguoiDungDAO=new NguoiDungDAO(ActivityAddUser.this);
        accountDAO=new AccountDAO(ActivityAddUser.this);
        lv=findViewById(R.id.listuser);
        updateUI();
        flb_addusers=findViewById(R.id.flb_adduser);
        flb_addusers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog=new Dialog(ActivityAddUser.this);
                dialog.setContentView(R.layout.dialog_adduser);
                dialog.show();

                etUserName=(dialog).findViewById(R.id.etUserName);
                etPasswork=(dialog).findViewById(R.id.etPasswork);
                etSoDienThoai=(dialog).findViewById(R.id.etSoDienThoai);
                etHoTen=(dialog).findViewById(R.id.etHoTen);
                btLuu=(dialog).findViewById(R.id.btLuuUser);
                btHuy=(dialog).findViewById(R.id.btHuyUser);
                btLuu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String UserName= etUserName.getText().toString();
                        String Passwork=etPasswork.getText().toString();
                        String SoDienThoai=etSoDienThoai.getText().toString();
                        String HoTen=etHoTen.getText().toString();
                        NguoiDung nguoiDung=new NguoiDung(UserName,Passwork,SoDienThoai,HoTen);
                        nguoiDungDAO.insert(nguoiDung);
                        Account account=new Account(UserName,Passwork);
                        accountDAO.insert(account);
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
                final NguoiDung nguoiDung=dsnd.get(position);
                final Dialog dialog=new Dialog(ActivityAddUser.this);
                dialog.setContentView(R.layout.dialog_adduser);
                final EditText et_UserName,et_Password,et_sdt,et_hoTen;
                Button bt_Huy,bt_Them;
                et_UserName=dialog.findViewById(R.id.etUserName);
                et_Password=dialog.findViewById(R.id.etPasswork);
                et_sdt=dialog.findViewById(R.id.etSoDienThoai);
                et_hoTen=dialog.findViewById(R.id.etHoTen);
                bt_Huy=dialog.findViewById(R.id.btHuyUser);
                bt_Them=dialog.findViewById(R.id.btLuuUser);
                dialog.show();
                et_UserName.setText(nguoiDung.username);
                et_UserName.setEnabled(false);
                et_Password.setText(nguoiDung.passwork);
                et_sdt.setText(nguoiDung.sodienthoai);
                et_hoTen.setText(nguoiDung.hoten);
                bt_Them.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String username=dsnd.get(position).username;
                        String password=et_Password.getText().toString();
                        String sdt=et_sdt.getText().toString();
                        String hoTen=et_hoTen.getText().toString();
                        nguoiDungDAO=new NguoiDungDAO(ActivityAddUser.this);
                        NguoiDung nguoiDung1=new NguoiDung(username,password,sdt,hoTen);
                        nguoiDungDAO.update(nguoiDung1,username);
                        updateUI();
                        Toast.makeText(ActivityAddUser.this, "Thành công !", Toast.LENGTH_SHORT).show();
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
        dsnd=nguoiDungDAO.getNguoiDung();
        UserAdapter adapter=new UserAdapter(dsnd,ActivityAddUser.this);
        lv.setAdapter(adapter);
    }
}
