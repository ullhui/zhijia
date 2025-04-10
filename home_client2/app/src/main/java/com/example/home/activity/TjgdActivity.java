package com.example.home.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.home.R;
import com.example.home.util.StatusBarUtil;
import com.githang.statusbar.StatusBarCompat;

public class TjgdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        StatusBarCompat.setStatusBarColor(this, Color.parseColor("#ffffff"), true);
        setContentView(R.layout.activity_tjgd);
        findViewById(R.id.button_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(TjgdActivity.this, "反馈成功，感谢您的支持！", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        setTitleBar("工单管理");
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

}