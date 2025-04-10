package com.example.home.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.home.R;
import com.example.home.app.AppConstant;
import com.githang.statusbar.StatusBarCompat;
import com.google.gson.Gson;
import com.orhanobut.hawk.Hawk;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class EditPassActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        StatusBarCompat.setStatusBarColor(this, Color.parseColor("#ffffff"), false);
        setContentView(R.layout.activity_edit_pass);
        setTitleBar("修改密码");
        initView();
        addView();

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


    EditText edit_1;
    EditText edit_2;
    Button button_1;


    protected void initView() {

        edit_1 = findViewById(R.id.edit_1);
        edit_2 = findViewById(R.id.edit_2);
        button_1 = findViewById(R.id.button_1);


    }

    protected void addView() {
        setTitleBar("忘记密码");
        button_1.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_1:
                if (TextUtils.isEmpty(edit_1.getText().toString())) {
                    Toast.makeText(this, "请输入密码！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(edit_1.getText().toString())) {
                    Toast.makeText(this, "请输入确认密码！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!edit_1.getText().toString().equals(edit_1.getText().toString())) {
                    Toast.makeText(this, "两次密码输入不一致！", Toast.LENGTH_SHORT).show();
                    return;
                }
                Hawk.put(AppConstant.HAWK_USER_PASSWORD, edit_1.getText().toString());
                Toast.makeText(this, "修改成功!", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }


}