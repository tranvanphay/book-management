package com.example.hades.lab1_da;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hades.lab1_da.Adapter.TheLoaiAdapter;
import com.example.hades.lab1_da.DAO.LoaiDAO;
import com.example.hades.lab1_da.database.DbHelper;
import com.example.hades.lab1_da.model.TheLoai;

import java.util.ArrayList;

public class ActivityLoai extends AppCompatActivity {
    FloatingActionButton flb;
    ListView listView;
    EditText etLoai,etTenLoai,etMoTa,etViTri;
    Button btLuu,btHuy;
    LoaiDAO loaiDAO;
    SearchView searchLoai;
    ArrayList<TheLoai> dstl=new ArrayList<TheLoai>();
    TheLoaiAdapter adapter=new TheLoaiAdapter(dstl,ActivityLoai.this);
    DbHelper dbHelper=new DbHelper(ActivityLoai.this);
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Loại sách");
        setContentView(R.layout.layout_loai);
        loaiDAO=new LoaiDAO(ActivityLoai.this);
        listView=findViewById(R.id.listLoai);
        searchLoai=findViewById(R.id.searchLoai);
        flb=findViewById(R.id.flbloai);
        updateUI();
        searchLoai.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
                final Dialog dialog=new Dialog(ActivityLoai.this);
                dialog.setContentView(R.layout.dialog_loai);
                etLoai=(dialog).findViewById(R.id.etidLoai);
                etTenLoai=(dialog).findViewById(R.id.ettenLoai);
                etMoTa=(dialog).findViewById(R.id.etmota);
                etViTri=(dialog).findViewById(R.id.etvitri);
                btHuy=(dialog).findViewById(R.id.btHuyLoai);
                btLuu=(dialog).findViewById(R.id.btThemLoai);
                dialog.show();
                dstl=loaiDAO.getTheLoai();
                btLuu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String _idLoai=etLoai.getText().toString();
                        String tenloai= etTenLoai.getText().toString();
                        String mota=etMoTa.getText().toString();
                        int vitri=Integer.parseInt(etViTri.getText().toString());
                        TheLoai theLoai=new TheLoai(_idLoai,tenloai,mota,vitri);
                        loaiDAO.insert(theLoai);
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
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
              TheLoai theLoai=dstl.get(position);
                final Dialog dialog=new Dialog(ActivityLoai.this);
              dialog.setContentView(R.layout.dialog_loai);
              final EditText et_idLoai,et_TenLoai,et_ViTri,et_MoTa;
              Button bt_Huy,bt_Them;
              et_idLoai=dialog.findViewById(R.id.etidLoai);
              et_TenLoai=dialog.findViewById(R.id.ettenLoai);
              et_ViTri=dialog.findViewById(R.id.etvitri);
              et_MoTa=dialog.findViewById(R.id.etmota);
              bt_Huy=dialog.findViewById(R.id.btHuyLoai);
              bt_Them=dialog.findViewById(R.id.btThemLoai);
              dialog.show();
              et_idLoai.setText(theLoai._id);
              et_idLoai.setEnabled(false);
              et_TenLoai.setText(theLoai.tenloai);
              et_ViTri.setText(theLoai.vitri+"");
              et_MoTa.setText(theLoai.mota);

              bt_Them.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      String _id=dstl.get(position)._id;
                      String tenLoai=et_TenLoai.getText().toString();
                      int viTri=Integer.parseInt(et_ViTri.getText().toString());
                      String moTa=et_MoTa.getText().toString();
                      loaiDAO=new LoaiDAO(ActivityLoai.this);
                      TheLoai theLoai1=new TheLoai(_id,tenLoai,moTa,viTri);
                      loaiDAO.update(theLoai1,_id);
                      updateUI();
                      Toast.makeText(ActivityLoai.this, "Thành công !", Toast.LENGTH_SHORT).show();
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
        dstl=loaiDAO.getTheLoai();
        adapter=new TheLoaiAdapter(dstl,ActivityLoai.this);
        listView.setAdapter(adapter);
    }
}
