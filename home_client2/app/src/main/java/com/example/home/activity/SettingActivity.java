package com.example.home.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.home.R;
import com.example.home.util.StatusBarUtil;
import com.githang.statusbar.StatusBarCompat;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {


    RelativeLayout relativelayout_1;
    RelativeLayout relativelayout_2;
    RelativeLayout relativelayout_3;
    RelativeLayout relativelayout_4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        StatusBarCompat.setStatusBarColor(this, Color.parseColor("#ffffff"), true);
        setContentView(R.layout.szactivity);
        initView();
        addView();
    }


    protected void initView() {
        relativelayout_1 = findViewById(R.id.relativelayout_1);
        relativelayout_2 = findViewById(R.id.relativelayout_2);
        relativelayout_3 = findViewById(R.id.relativelayout_3);
        relativelayout_4 = findViewById(R.id.relativelayout_4);
        setTitleBar("编辑资料");
    }


    public void setTitleBar(String title) {
        RelativeLayout relativelayout_back = findViewById(R.id.relativelayout_back);
        relativelayout_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        TextView textview_title = findViewById(R.id.textview_title);
        textview_title.setText(title);
    }


    protected void addView() {
        relativelayout_1.setOnClickListener(this);
        relativelayout_2.setOnClickListener(this);
        relativelayout_3.setOnClickListener(this);
        relativelayout_4.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.relativelayout_1://编辑资料
//                startActivity(new Intent(SettingActivity.this, BjzlActivity.class));
                break;
            case R.id.relativelayout_2://设置密码
                startActivity(new Intent(SettingActivity.this, EditPassActivity.class));
                break;
            case R.id.relativelayout_3://换绑手机
                break;
            case R.id.relativelayout_4://关于我们
                break;

        }
    }


}