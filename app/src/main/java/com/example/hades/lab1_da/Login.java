package com.example.hades.lab1_da;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hades.lab1_da.DAO.AccountDAO;
import com.example.hades.lab1_da.DAO.NguoiDungDAO;
import com.example.hades.lab1_da.model.Account;
import com.example.hades.lab1_da.model.NguoiDung;

public class Login extends AppCompatActivity {
    ImageButton login;
    TextView forgot;
    EditText etUser,etPass;
    AccountDAO accountDAO;
    NguoiDungDAO nguoiDungDAO;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        forgot=findViewById(R.id.forgot);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        accountDAO=new AccountDAO(Login.this);
        login=findViewById(R.id.imageButton4);
        etUser=findViewById(R.id.etUser);
        etPass=findViewById(R.id.etPass);
        etUser.setText("admin");
        etPass.setText("admin");
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String User=etUser.getText().toString();
                String Passwork=etPass.getText().toString();
                boolean validUser = accountDAO.checkLogin(User,Passwork);
                if( etUser.getText().toString().equalsIgnoreCase("admin")
                        && etPass.getText().toString().equalsIgnoreCase("admin")
                        || validUser==true){
                Intent intent=new Intent(Login.this,MainActivity.class);
                startActivity(intent);
                    Toast.makeText(Login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                finish();
                }
                 if(validUser==false){
                }
            }
        });
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog=new Dialog(Login.this);
                dialog.setContentView(R.layout.dialog_quen_mat_khau);
                final EditText et_sdt;
                Button bt_getPass;
                final TextView tv_showPass = null;
                et_sdt=dialog.findViewById(R.id.et_sdt);
                bt_getPass=dialog.findViewById(R.id.bt_getPass);
                bt_getPass.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String sdt=et_sdt.getText().toString();
                    }
                });
                dialog.show();
            }
        });
    }
}
