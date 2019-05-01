package com.example.hades.lab1_da;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
        ImageView loai,sach,hoadon,banchay,thongke,website;
        TextView tvloai,tvsach,tvhoadon,tvbanchay,tvthongke,tvwebsite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Quản lý sách");
        setContentView(R.layout.activity_main);
        loai=findViewById(R.id.imageView2);
        sach=findViewById(R.id.imageView4);
        hoadon=findViewById(R.id.imageView5);
        banchay=findViewById(R.id.imageView6);
        thongke=findViewById(R.id.imageView8);
        website=findViewById(R.id.imageView9);
        tvloai=findViewById(R.id.textView);
        tvsach=findViewById(R.id.textView3);
        tvhoadon=findViewById(R.id.textView4);
        tvbanchay=findViewById(R.id.textView5);
        tvthongke=findViewById(R.id.textView6);
        tvwebsite=findViewById(R.id.textView8);
        loai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte=new Intent(MainActivity.this,ActivityLoai.class);
                startActivity(inte);
            }
        });
        tvloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten=new Intent(MainActivity.this,ActivityLoai.class);
                startActivity(inten);
            }
        });
        sach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ActivitySach.class);
                startActivity(intent);
            }
        });
        tvsach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ActivitySach.class);
                startActivity(intent);
            }
        });
        hoadon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ActivityHoaDon.class);
                startActivity(intent);
            }
        });
        tvhoadon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ActivityHoaDon.class);
                startActivity(intent);
            }
        });
        banchay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ActivityBanChay.class);
                startActivity(intent);
            }
        });
        tvbanchay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ActivityBanChay.class);
                startActivity(intent);
            }
        });
        thongke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ActivityThongKe.class);
                startActivity(intent);
            }
        });
        tvthongke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ActivityThongKe.class);
                startActivity(intent);
            }
        });
        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ActivityWebsite.class);
                startActivity(intent);

            }
        });
        tvwebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ActivityWebsite.class);
                startActivity(intent);
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inf=getMenuInflater();
        inf.inflate(R.menu.optionmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.support){
            Dialog dialog;
            dialog=new Dialog(MainActivity.this);
            dialog.setContentView(R.layout.changepass);
            dialog.show();
        }
        if(item.getItemId()==R.id.report){
            Intent it=new Intent(MainActivity.this,Login.class);
            startActivity(it);
            finish();
        }
        if(item.getItemId()==R.id.about){
            Dialog dialog;
            dialog=new Dialog(MainActivity.this);
            dialog.setContentView(R.layout.about);
            dialog.show();

        }
        if (item.getItemId()==R.id.users){
            Intent i=new Intent(MainActivity.this,ActivityAddUser.class);
            startActivity(i);
        }
        if(item.getItemId()==R.id.thoat){
            System.exit(0);
        }


        return super.onOptionsItemSelected(item);
    }


}
