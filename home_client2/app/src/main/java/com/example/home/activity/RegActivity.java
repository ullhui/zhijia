package com.example.home.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.home.R;
import com.example.home.app.AppConstant;
import com.example.home.util.CountDownTimerUtils;
import com.example.home.util.r_l;
import com.githang.statusbar.StatusBarCompat;
import com.google.gson.Gson;
import com.orhanobut.hawk.Hawk;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class RegActivity extends AppCompatActivity implements View.OnClickListener {


    ImageView imageview_back;
    TextView text1;
    CountDownTimerUtils mCountDownTimerUtils;
    Button button_reg;
    EditText edit_phone;
    EditText edit_yzm;
    EditText edit_pass;
    EditText edit_oldPass;
    EditText edit_yqm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        StatusBarCompat.setStatusBarColor(this, Color.parseColor("#ffffff"), false);
        setContentView(R.layout.activity_reg);
        initView();
        addView();
    }

    protected void initView() {
        imageview_back = findViewById(R.id.imageview_back);
        text1 = findViewById(R.id.text1);
        button_reg = findViewById(R.id.button_reg);
        edit_phone = findViewById(R.id.edit_phone);
        edit_yzm = findViewById(R.id.edit_yzm);
        edit_pass = findViewById(R.id.edit_pass);
        edit_oldPass = findViewById(R.id.edit_oldPass);
        edit_yqm = findViewById(R.id.edit_yqm);
    }

    protected void addView() {
        imageview_back.setOnClickListener(this);
        text1.setOnClickListener(this);
        button_reg.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageview_back://返回
                finish();
                break;
            case R.id.text1://发送验证码

                if (edit_phone.getText().toString().trim().length() > 0 && r_l.PhoneYz(edit_phone.getText().toString().trim())) {
                    mCountDownTimerUtils = new CountDownTimerUtils(text1, 60000, 1000); //倒计时1分钟
                    mCountDownTimerUtils.start();
                    http_getYzm(edit_phone.getText().toString().trim());
                } else {
                    Toast.makeText(this, "请输入正确的手机号码!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.button_reg://注册
                if (edit_phone.getText().toString().trim().length() > 0) {
                    if (edit_yzm.getText().toString().trim().length() > 0||true) {
                        if (edit_pass.getText().toString().trim().length() > 0) {
                            if (r_l.isLetterDigit(edit_pass.getText().toString().trim())) {
                                if (edit_oldPass.getText().toString().trim().length() > 0) {
                                    if (edit_oldPass.getText().toString().trim().equals(edit_pass.getText().toString().trim())) {
                                        Hawk.put(AppConstant.HAWK_USER_USERNAME, edit_phone.getText().toString());
                                        Hawk.put(AppConstant.HAWK_USER_PASSWORD, edit_pass.getText().toString());
                                        finish();
                                    } else {
                                        edit_oldPass.setText("");
                                        Toast.makeText(this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(this, "请再次输入密码", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(this, "密码需要8-20个字符，且包含字母和数字", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


    /**
     * 获取手机短信验证码
     */
    public void http_getYzm(String phone) {
        mCountDownTimerUtils.onFinish();
        mCountDownTimerUtils.cancel();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}