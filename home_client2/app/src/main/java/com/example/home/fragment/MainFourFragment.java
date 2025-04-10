package com.example.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.home.LoginActivity;
import com.example.home.R;
import com.example.home.activity.BjzlActivity;
import com.example.home.activity.EditPassActivity;
import com.example.home.activity.TjgdActivity;
import com.example.home.app.AppConstant;
import com.example.home.popup.OutAppPopup;
import com.lxj.xpopup.XPopup;
import com.orhanobut.hawk.Hawk;


/**
 * Created by renlin on 2018/8/25.f
 * 人的痛苦 大多数时候来自，对自己盲目自信，对现实力不从心，二者之间的冲突所导致的。
 */

public class MainFourFragment extends Fragment {

    View view;

    public static Fragment newInstance() {
        MainFourFragment f = new MainFourFragment();
        return f;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.mainfourfragment, null);
        addView();
        addOnclick();
        return view;
    }

    private void addOnclick() {

        //个人信息设置
        view.findViewById(R.id.relativelayout_syjt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startActivity(new Intent(getActivity(), BjzlActivity.class));
            }
        });
        //工单管理
        view.findViewById(R.id.relativelayout_tjgd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startActivity(new Intent(getActivity(), TjgdActivity.class));
            }
        });
        //修改密码
        view.findViewById(R.id.relativelayout_syhz).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startActivity(new Intent(getActivity(), EditPassActivity.class));
            }
        });
        //退出登录
        view.findViewById(R.id.relativelayout_lxwm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showVerifyPopup();
            }
        });
    }

    private void addView() {
        TextView textview_username = view.findViewById(R.id.textview_username);
        textview_username.setText(Hawk.get(AppConstant.HAWK_USER_USERNAME));
    }


    public void showVerifyPopup() {
        OutAppPopup soundVerifyCenterPopup = new OutAppPopup("是否退出登录？", getActivity(), new OutAppPopup.OnActionClickListener() {
            @Override
            public void onDismiss() {
            }

            @Override
            public void onLeftClick() {
            }

            @Override
            public void onRightClick() {
//                Hawk.deleteAll();
                Hawk.delete(AppConstant.HAWK_LOGIN_TYPE);
                Hawk.delete(AppConstant.HAWK_USER_USERNAME);
                Hawk.delete(AppConstant.HAWK_USER_PASSWORD);

                getActivity().finish();
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });
        new XPopup.Builder(getActivity())
                .asCustom(soundVerifyCenterPopup)
                .show();
    }


}


