package com.example.nguyenthiennhan_1570_project_karaoke;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class subactivity extends AppCompatActivity {
    TextView txtmaso2,txtbaihat,txtloibaihat,txttacgia;
    ImageButton btnthich,btnkhongthich;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subactivity);
        txtmaso2 = findViewById(R.id.txtmaso2);
        txtbaihat = findViewById(R.id.txtbaihat);
        txtloibaihat = findViewById(R.id.txtloibaihat);
        txttacgia = findViewById(R.id.txttacgia);
        btnthich = findViewById(R.id.btnthich);
        btnkhongthich = findViewById(R.id.btnkhongthich);
        //Nhận Intent từ myarrayAdapter, lấy dữ liệu khỏi Bundle
        Intent callerIntent1 = getIntent();
        Bundle backagecaller1 = callerIntent1.getBundleExtra("package");
        String maso = backagecaller1.getString("maso");
        //Truy vấn dữ liệu từ maso nhận được; Hiển thị dữ liệu Mã bài hát, Tên bài hát, Lời bài
        //hát, Tác giả, Trạng thái Thích lên activitysub
        Cursor c = MainActivity.database.rawQuery("SELECT * FROM ArirangSongList WHERE MABH LIKE '"+maso+"'", null);
        txtmaso2.setText(maso);
        c.moveToFirst();
        txtbaihat.setText(c.getString(2));
        txtloibaihat.setText(c.getString(3));
        txttacgia.setText(c.getString(4));
        if (c.getInt(6)==0) {
            btnthich.setVisibility(View.INVISIBLE);
            btnkhongthich.setVisibility(View.VISIBLE);
        } else {
            btnkhongthich.setVisibility(View.INVISIBLE);
            btnthich.setVisibility(View.VISIBLE);
        }
        c.close();
        //Xử lý sự kiện khi click vào Button btnthich và btnkhongthich
        //Cập nhật dữ liệu vào CSDL, thay đổi trạng thái hiển thị cho Button btnthich và
        //btnkhongthich
        btnthich.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                values.put("YEUTHICH", 0);
                MainActivity.database.update("ArirangSongList", values, "MABH=?", new String[]{txtmaso2.getText().toString()});
                btnthich.setVisibility(View.INVISIBLE);
                btnkhongthich.setVisibility(View.VISIBLE);
            }
        });
        btnkhongthich.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                values.put("YEUTHICH", 1);
                MainActivity.database.update("ArirangSongList", values, "MABH=?", new String[]{txtmaso2.getText().toString()});
                btnkhongthich.setVisibility(View.INVISIBLE);
                btnthich.setVisibility(View.VISIBLE);
            }
        });


    }
}