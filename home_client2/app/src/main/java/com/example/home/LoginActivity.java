package com.example.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.home.activity.RegActivity;
import com.example.home.app.AppConstant;
import com.example.home.util.StatusBarUtil;
import com.githang.statusbar.StatusBarCompat;
import com.orhanobut.hawk.Hawk;

public class LoginActivity extends AppCompatActivity {
    boolean hiddenPass = false;
    EditText       edit_pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        StatusBarCompat.setStatusBarColor(this, Color.parseColor("#ffffff"), false);
        setContentView(R.layout.activity_login);
        edit_pass=findViewById(R.id.edit_pass);
        //登录
        findViewById(R.id.tv_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText edit_user = findViewById(R.id.edit_user);
                if (TextUtils.isEmpty(edit_user.getText().toString())) {
                    Toast.makeText(LoginActivity.this, "请输入用户名及手机号", Toast.LENGTH_SHORT).show();
                    return;
                }
                EditText edit_pass = findViewById(R.id.edit_pass);
                if (TextUtils.isEmpty(edit_pass.getText().toString())) {
                    Toast.makeText(LoginActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                String username = Hawk.get(AppConstant.HAWK_USER_USERNAME, "");
                String password = Hawk.get(AppConstant.HAWK_USER_PASSWORD, "");
                if (username.equals(edit_user.getText().toString()) && password.equals(edit_pass.getText().toString())) {
                    Hawk.put(AppConstant.HAWK_LOGIN_TYPE, edit_pass.getText().toString());
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                } else {
                    Toast.makeText(LoginActivity.this, "请输入正确的账号和密码！", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //立即注册
        findViewById(R.id.ic_login_getback).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegActivity.class));
            }
        });
        ImageView iv_hidden_pass=findViewById(R.id.iv_hidden_pass);
        //切换密码是否显示
        findViewById(R.id.iv_hidden_pass).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (hiddenPass) {
                    hiddenPass = false;
                    edit_pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.mipmap.icon_hidden_pass);
                    iv_hidden_pass.setImageDrawable(drawable);
                } else {
                    hiddenPass = true;
                    edit_pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.mipmap.icon_show_pass);
                    iv_hidden_pass.setImageDrawable(drawable);
                }
            }
        });
    }
}