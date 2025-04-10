package com.example.home;

import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.home.fragment.MainFourFragment;
import com.example.home.fragment.MainOneFragment;
import com.example.home.fragment.MainThreeFragment;
import com.example.home.fragment.MainTwoFragment;
import com.example.home.util.StatusBarUtil;
import com.githang.statusbar.StatusBarCompat;

public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener {
        private RadioGroup radiogroup;
        private long exitTime = 0;// 用来计算返回键的点击间隔时间
        MainOneFragment mMainOneFragment = new MainOneFragment();
        FragmentManager fm;
        FragmentTransaction transaction;
        Fragment fragment1;
        Fragment fragment2;
        Fragment fragment3;
        Fragment fragment4;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
            StatusBarUtil.setRootViewFitsSystemWindows(this, false);
            StatusBarCompat.setStatusBarColor(this, Color.parseColor("#ffffff"), true);

            setContentView(R.layout.activity_main);
            initView();
            addView();
            if (savedInstanceState != null) {
                fm = getSupportFragmentManager();//重新创建Manager，防止此对象为空
                fm.popBackStackImmediate(null, 1);//弹出所有fragment
            }
        }


        protected void initView() {
            radiogroup = findViewById(R.id.radiogroup);
        }

        protected void addView() {
            radiogroup.setOnCheckedChangeListener(this);
            fragment4 = MainFourFragment.newInstance();
            fragment3 = MainThreeFragment.newInstance();
            fragment2 = MainTwoFragment.newInstance();
            fragment1 = MainOneFragment.newInstance();
            fm = getSupportFragmentManager();
            transaction = fm.beginTransaction();
            transaction.add(R.id.main_fragment, fragment4);
            transaction.add(R.id.main_fragment, fragment3);
            transaction.add(R.id.main_fragment, fragment2);
            transaction.add(R.id.main_fragment, fragment1);
            transaction.commit();


        }

        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            switch (i) {
                case R.id.tab_one:
                    transaction = fm.beginTransaction();
                    transaction.hide(fragment4);
                    transaction.hide(fragment3);
                    transaction.hide(fragment2);
                    transaction.show(fragment1);
                    transaction.commit();
                    break;
                case R.id.tab_two:
                    transaction = fm.beginTransaction();
                    transaction.hide(fragment4);
                    transaction.hide(fragment3);
                    transaction.hide(fragment1);
                    transaction.show(fragment2);
                    transaction.commit();

                    break;
                case R.id.tab_three:
                    transaction = fm.beginTransaction();
                    transaction.hide(fragment4);
                    transaction.hide(fragment2);
                    transaction.hide(fragment1);
                    transaction.show(fragment3);
                    transaction.commit();

                    break;
                case R.id.tab_four:
                    transaction = fm.beginTransaction();
                    transaction.hide(fragment2);
                    transaction.hide(fragment3);
                    transaction.hide(fragment1);
                    transaction.show(fragment4);
                    transaction.commit();
                    break;
            }
        }

        @Override
        public boolean onKeyDown(int keyCode, KeyEvent event) {
            if (keyCode == android.view.KeyEvent.KEYCODE_BACK
                    && event.getAction() == android.view.KeyEvent.ACTION_DOWN) {
                if ((System.currentTimeMillis() - exitTime) > 2000) {
                    Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                    exitTime = System.currentTimeMillis();
                } else {
                    finish();
                }
                return true;
            }
            return super.onKeyDown(keyCode, event);
        }


    }








