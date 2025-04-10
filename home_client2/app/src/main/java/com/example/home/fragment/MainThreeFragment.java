package com.example.home.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.home.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by renlin on 2018/8/25.f
 * 人的痛苦 大多数时候来自，对自己盲目自信，对现实力不从心，二者之间的冲突所导致的。
 */

public class MainThreeFragment extends Fragment  {

    View view;
    TabLayout tab_layout;
    ViewPager viewPager;
    static final int NUM_ITEMS = 2;
    private List<Fragment> fragmentList = new ArrayList<Fragment>();
    private String[] strings = new String[]{"Breaking", "其它"};
    ImageView image_heard;
    TextView textview_username;
    RelativeLayout relativelayout_search;
    ImageView image_xk;
    ImageView image_rz;
    RelativeLayout relativelayout_tx;
    LinearLayout line_login;

    public static Fragment newInstance() {
        MainThreeFragment f = new MainThreeFragment();
        return f;
    }



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.mainthreefragment, null);
        return view;
    }



}


