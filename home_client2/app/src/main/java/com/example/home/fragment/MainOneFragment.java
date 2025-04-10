package com.example.home.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.example.home.R;
import com.example.home.adapter.HomeAdapter;
import com.example.home.adapter.ImageAdapter;
import com.example.home.app.MyApplication;
import com.example.home.bean.EquipmentBean;
import com.example.home.bean.ImageBean;
import com.example.home.db.DaoSession;
import com.example.home.db.EquipmentBeanDao;
import com.example.home.popup.OutAppPopup;
import com.google.android.material.tabs.TabLayout;
import com.lxj.xpopup.XPopup;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by renlin on 2018/8/25.f
 * 人的痛苦 大多数时候来自，对自己盲目自信，对现实力不从心，二者之间的冲突所导致的。
 */

public class MainOneFragment extends Fragment {

    View view;
    private List<ImageBean> mList = new ArrayList<>();
    TabLayout tabLayout;
    Banner banner;
    SwipeRecyclerView mSwipeRecyclerView;
    EquipmentBeanDao mEquipmentBeanDao;
    List<EquipmentBean> adapterData = new ArrayList<>();
    HomeAdapter myAdapter;

    public static Fragment newInstance() {
        MainOneFragment f = new MainOneFragment();
        return f;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.mainonefragment, null);
        init();
        initView();
        addTabView();
        return view;
    }

    private void addTabView() {

        TabLayout.Tab tab0 = tabLayout.newTab().setText("全部");
        TabLayout.Tab tab1 = tabLayout.newTab().setText("客厅");
        TabLayout.Tab tab2 = tabLayout.newTab().setText("卧室");
        TabLayout.Tab tab3 = tabLayout.newTab().setText("厨房");
        TabLayout.Tab tab4 = tabLayout.newTab().setText("卫生间");
        tabLayout.addTab(tab0);
        tabLayout.addTab(tab1);
        tabLayout.addTab(tab2);
        tabLayout.addTab(tab3);
        tabLayout.addTab(tab4);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                int position = tab.getPosition();
                List<EquipmentBean> adapterData_Temp=new ArrayList<>();
                adapterData=new ArrayList<>();
                adapterData_Temp.addAll(queryAll());
                Log.e("对比内容",adapterData.size()+"");
                String filterContent = "";
                switch (position) {
                    case 0:
                        filterContent = "";
                        break;
                    case 1:
                        filterContent = "客厅";
                        break;
                    case 2:
                        filterContent = "卧室";
                        break;
                    case 3:
                        filterContent = "厨房";
                        break;
                    case 4:
                        filterContent = "卫生间";
                        break;
                }
                if (filterContent.length() > 0) {
                    for (int i = 0; i < adapterData_Temp.size(); i++) {
                        if (adapterData_Temp.get(i).getCatgeory().equals(filterContent)) {
                            adapterData.add(adapterData_Temp.get(i));
                        }
                    }
                }else{
                    adapterData.addAll(adapterData_Temp);
                }
                addListView();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });


        ImageAdapter mImageAdapter = new ImageAdapter(getActivity(), mList);
        mList.add(new ImageBean(R.drawable.jiaju1));
        mList.add(new ImageBean(R.drawable.jiaju2));
        banner.setAdapter(mImageAdapter);
        banner.isAutoLoop(true);
        banner.setIndicator(new CircleIndicator(getActivity()));
        banner.start();


    }

    @Override
    public void onStart() {
        super.onStart();
        adapterData = queryAll();
        addListView();
    }

    //获取UserDao
    private void init() {
        MyApplication myApp = (MyApplication) getActivity().getApplication();
        DaoSession daoSession = myApp.getDaoSession();
        mEquipmentBeanDao = daoSession.getEquipmentBeanDao();
    }


    private List<EquipmentBean> queryAll() {
        return mEquipmentBeanDao.loadAll();
    }

    private void addListView() {
        if (adapterData != null) {
            myAdapter = new HomeAdapter(adapterData,getActivity());
            myAdapter.addChildClickViewIds(R.id.tv_del);
            myAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
                @Override
                public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                    switch (view.getId()) {
                        case R.id.tv_del:
                            showVerifyPopup(position);
                            break;

                    }
                }
            });
            mSwipeRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            mSwipeRecyclerView.setAdapter(myAdapter);


        }


    }


    private void initView() {
        mSwipeRecyclerView = view.findViewById(R.id.mSwipeRecyclerView);
        tabLayout = view.findViewById(R.id.tabLayout);
        banner = view.findViewById(R.id.banner);

    }


    public void showVerifyPopup(int position) {
        OutAppPopup soundVerifyCenterPopup = new OutAppPopup("是否删除？", getActivity(), new OutAppPopup.OnActionClickListener() {
            @Override
            public void onDismiss() {
            }

            @Override
            public void onLeftClick() {
            }

            @Override
            public void onRightClick() {
                mEquipmentBeanDao.delete(adapterData.get(position));
                Toast.makeText(getActivity(), "删除成功！", Toast.LENGTH_SHORT).show();
                adapterData.remove(position);
                myAdapter.notifyDataSetChanged();
            }
        });
        new XPopup.Builder(getActivity())
                .asCustom(soundVerifyCenterPopup)
                .show();
    }


}


