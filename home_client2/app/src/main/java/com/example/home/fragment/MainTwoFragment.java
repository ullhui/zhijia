package com.example.home.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.home.R;
import com.example.home.app.App;
import com.example.home.app.MyApplication;
import com.example.home.bean.DeviceBean;
import com.example.home.bean.EquipmentBean;
import com.example.home.db.DaoSession;
import com.example.home.db.EquipmentBeanDao;
import com.example.home.popup.OutAppPopup;
import com.google.gson.Gson;
import com.lxj.xpopup.XPopup;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;


/**
 * Created by renlin on 2018/8/25.f
 * 人的痛苦 大多数时候来自，对自己盲目自信，对现实力不从心，二者之间的冲突所导致的。
 */

public class MainTwoFragment extends Fragment {

    View view;
    EquipmentBeanDao mEquipmentBeanDao;
    RadioGroup radiogroup_1;
    RadioGroup radiogroup_2;
    RadioGroup radiogroup_3;
    TextView tv_status_content;



    String name = "灯";
    String catgeory = "客厅";


    public static Fragment newInstance() {
        MainTwoFragment f = new MainTwoFragment();
        return f;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.maintwofragment, null);
        initView();
        init();
        setOnclick();
        return view;
    }

    private void initView() {
        radiogroup_1 = view.findViewById(R.id.radiogroup_1);
        radiogroup_2 = view.findViewById(R.id.radiogroup_2);
        radiogroup_3 = view.findViewById(R.id.radiogroup_3);
         tv_status_content=view.findViewById(R.id.tv_status_content);

    }







    /**
     * 开启睡眠模式
     */
    public void http_Sleep() {
        List<EquipmentBean> adapterData = new ArrayList<>();
        adapterData.addAll(queryAll());
        String pathUrl = App.HTTP_BASE_URL+ "/home/goSellp";
        OkHttpUtils.get()
                .url(pathUrl)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("第一次请求失败",e.getMessage());

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("第一次请求成功",response);
                        Toast.makeText(getActivity(), "已为您关闭室内所有灯光！", Toast.LENGTH_SHORT).show();

                    }
                });
    }




    /**
     * 开始回家模式
     */
    public void http_goHome() {
        List<EquipmentBean> adapterData = new ArrayList<>();
        adapterData.addAll(queryAll());
        String pathUrl =App.HTTP_BASE_URL+ "/home/goHomeClose";
        OkHttpUtils.get()
                .url(pathUrl)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("第一次请求失败",e.getMessage());

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("第一次请求成功",response);
                        Toast.makeText(getActivity(), "已为您开启所有客厅灯光！", Toast.LENGTH_SHORT).show();

                    }
                });
    }






    /**
     * 同步所有设备
     */
    public void http_get_data() {
        List<EquipmentBean> adapterData = new ArrayList<>();
        adapterData.addAll(queryAll());
        String pathUrl = App.HTTP_BASE_URL+"/home/addAll";
        List<DeviceBean> data=new ArrayList<>();
        for (int i = 0; i <adapterData.size() ; i++) {
            DeviceBean bean1=new DeviceBean();
            bean1.setName(adapterData.get(i).getName());
            bean1.setCatgeory(adapterData.get(i).getCatgeory());
            bean1.setDriveId(adapterData.get(i).getId()+"");
            bean1.setOnOrOff((adapterData.get(i).getStatus()!=null&&adapterData.get(i).getStatus().equals("1"))?"1":"0");
            data.add(bean1);
        }
        OkHttpUtils.postString()
                .url(pathUrl)

                .content(new Gson().toJson(data))
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("第一次请求失败",e.getMessage());

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("第一次请求成功",response);
                        Toast.makeText(getActivity(), "同步成功，可以进行演示！", Toast.LENGTH_SHORT).show();

                    }
                });
    }





    private void setOnclick() {
        //上传全部设备
        view.findViewById(R.id.btn_post_all).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                http_get_data();




            }
        });

        radiogroup_1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.mRadioButton1:
                        catgeory = "客厅";
                        break;
                    case R.id.mRadioButton2:
                        catgeory = "卧室";
                        break;
                    case R.id.mRadioButton3:
                        catgeory = "厨房";
                        break;
                    case R.id.mRadioButton4:
                        catgeory = "卫生间";
                        break;
                }
            }
        });


        radiogroup_2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.mRadioButtonType1:
                        name = "灯";
                        break;
                    case R.id.mRadioButtonType2:
                        name = "音响";
                        break;
                    case R.id.mRadioButtonType3:
                        name = "空调";
                        break;
                    case R.id.mRadioButtonType4:
                        name = "电视机";
                        break;
                    case R.id.mRadioButtonType5:
                        name = "监控";
                        break;
                }
            }
        });

        radiogroup_3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.mRadioButtonMs0:
                        tv_status_content.setText("常规模式:正常手动操作");

                        break;
                    case R.id.mRadioButtonMs1:
                        tv_status_content.setText("回家模式:开启所有客厅灯光");
                        http_goHome();
                        break;
                    case R.id.mRadioButtonMs2:
                        tv_status_content.setText("睡眠模式:关闭室内所有灯光");
                        http_Sleep();
                        break;
                }
            }
        });
        view.findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showVerifyPopup();
            }
        });
    }



    private List<EquipmentBean> queryAll() {
        return mEquipmentBeanDao.loadAll();
    }


    //获取UserDao
    private void init() {
        MyApplication myApp = (MyApplication) getActivity().getApplication();
        DaoSession daoSession = myApp.getDaoSession();
        mEquipmentBeanDao = daoSession.getEquipmentBeanDao();
    }




    /**
     * 添加单一设备
     */
    public void http_PostOne(String name,String id,String catgeory) {
        List<EquipmentBean> adapterData = new ArrayList<>();
        adapterData.addAll(queryAll());
        String pathUrl =App.HTTP_BASE_URL+ "/home/addOne";
        DeviceBean bean1=new DeviceBean();
        bean1.setName(name);
        bean1.setCatgeory(catgeory);
        bean1.setDriveId(id);
        bean1.setOnOrOff("0");
        OkHttpUtils.postString()
                .url(pathUrl)
                .content(new Gson().toJson(bean1))
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("第一次请求失败",e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("第一次请求成功",response);

                    }
                });
    }




    public void showVerifyPopup() {
        OutAppPopup soundVerifyCenterPopup = new OutAppPopup("是否添加？", getActivity(), new OutAppPopup.OnActionClickListener() {
            @Override
            public void onDismiss() {
            }

            @Override
            public void onLeftClick() {
            }

            @Override
            public void onRightClick() {

                EquipmentBean mEquipmentBean = new EquipmentBean();
                long timestamp = System.currentTimeMillis();
                mEquipmentBean.setId(timestamp);
                mEquipmentBean.setName(name);
                mEquipmentBean.setCatgeory(catgeory);
                mEquipmentBeanDao.insert(mEquipmentBean);
                http_PostOne(name,timestamp+"",catgeory);
                Toast.makeText(getActivity(), "添加成功！", Toast.LENGTH_SHORT).show();
            }

        });
        new XPopup.Builder(getActivity())
                .asCustom(soundVerifyCenterPopup)
                .show();
    }






}


