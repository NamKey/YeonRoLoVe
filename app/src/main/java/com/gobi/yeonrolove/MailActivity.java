package com.gobi.yeonrolove;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MailActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    public Button btn_close_mail;
    public ListView lv_mail;

    public Button btn_pre, btn_message;
    public TextView tv_message;
    public Dialog dialog_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);

        setTitle("Mail Box");

        lv_mail = findViewById(R.id.lv_mail);

        btn_close_mail = findViewById(R.id.btn_close_mail);
        btn_close_mail.setOnClickListener(this);

        String names[] = getResources().getStringArray(R.array.list_mail);
        ListView lv_mail = findViewById(R.id.lv_mail);
        lv_mail.setOnItemClickListener(this);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, names);
        lv_mail.setAdapter(adapter);

        //Dialog 부분
//        btn_pre = findViewById(R.id.btn_pre);
//        btn_pre.setOnClickListener(this);
//
//        btn_message = findViewById(R.id.btn_message);
//        btn_message.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_close_mail:
                finish();
                break;


            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, lv_mail.getItemAtPosition(position).toString() + "의 편지 다이얼로그", Toast.LENGTH_SHORT).show();
    }
}
