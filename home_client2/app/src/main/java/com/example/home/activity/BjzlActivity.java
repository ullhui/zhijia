package com.example.home.activity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.home.R;
import com.example.home.util.StatusBarUtil;
import com.githang.statusbar.StatusBarCompat;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * create By 琳哥快跑
 * on 2020/12/20
 * instructions:设置
 */
public class BjzlActivity extends AppCompatActivity implements View.OnClickListener {

    RelativeLayout relativelayout_1;
    RelativeLayout relativelayout_2;
    RelativeLayout relativelayout_3;
    RelativeLayout relativelayout_4;
    RelativeLayout relativelayout_5;
    RelativeLayout relativelayout_6;
    ImageView image_heard;
    ImageView image_bj;
    TextView textview1;
    TextView textview2;
    TextView textview3;
    TextView textview4;
    TextView textview5;
    private List<String> options1Items = new ArrayList<>(); //省
    private List<String> options1ItemsID = new ArrayList<>(); //省
    private List<List<String>> options2Items = new ArrayList<>();//市
    private List<List<String>> options2ItemsID = new ArrayList<>();//市
    private List<List<List<String>>> options3Items = new ArrayList<>();//县
    private List<List<List<String>>> options3ItemsID = new ArrayList<>();//县
    private String provinceName;
    private String cityName;
    private String countyName;
    private String provinceNameID;
    private String cityNameID;
    private String countyNameID;
    String avatar;//头像
    String files_id;//头像ID
    String sex;//
    String birthday;//
    String country_code;//
    String province_code;//
    String city_code;//
    String autograph;//
    String back_img;//
    String back_file_id;//
    String nick_name;//

    String logoPath;//用户头像物理地址
    String backPath;//背景图片物理地址
    private Dialog dialogMore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        StatusBarCompat.setStatusBarColor(this, Color.parseColor("#ffffff"), true);
        setContentView(R.layout.activity_bjzl);
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


    @Override
    protected void onStart() {
        super.onStart();
        initView();
        addView();
    }

    protected void initView() {
        relativelayout_1 = findViewById(R.id.relativelayout_1);
        relativelayout_2 = findViewById(R.id.relativelayout_2);
        relativelayout_3 = findViewById(R.id.relativelayout_3);
        relativelayout_4 = findViewById(R.id.relativelayout_4);
        relativelayout_5 = findViewById(R.id.relativelayout_5);
        relativelayout_6 = findViewById(R.id.relativelayout_6);
        image_heard = findViewById(R.id.image_heard);
        image_bj = findViewById(R.id.image_bj);
        textview1 = findViewById(R.id.textview1);
        textview2 = findViewById(R.id.textview2);
        textview3 = findViewById(R.id.textview3);
        textview4 = findViewById(R.id.textview4);
        textview5 = findViewById(R.id.textview5);
    }

    protected void addView() {
        relativelayout_1.setOnClickListener(this);
        relativelayout_2.setOnClickListener(this);
        relativelayout_3.setOnClickListener(this);
        relativelayout_4.setOnClickListener(this);
        relativelayout_5.setOnClickListener(this);
        relativelayout_6.setOnClickListener(this);
        image_heard.setOnClickListener(this);


    }


    /**
     * 修改背景图dialog
     */
    public void showPop_two() {
        dialogMore = new Dialog(BjzlActivity.this, R.style.FullHeightDialog);
        dialogMore.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogMore.setContentView(R.layout.alert_setphoto_menu_layout1);
        Window dialogWindow = dialogMore.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.BOTTOM);
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT; // 高度
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度

        RelativeLayout relativelayout_1 = dialogMore.findViewById(R.id.relativelayout_1);
        relativelayout_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogMore.dismiss();


            }
        });
        RelativeLayout relativelayout_2 = dialogMore.findViewById(R.id.relativelayout_2);
        relativelayout_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogMore.dismiss();
            }
        });

        dialogMore.show();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.relativelayout_1://修改昵称
                break;
            case R.id.relativelayout_2://性别
                break;
            case R.id.relativelayout_3://生日
                createTime();
                break;
            case R.id.relativelayout_4://地址
                break;
            case R.id.relativelayout_5://修改简介
                break;
            case R.id.relativelayout_6://修改背景图
                showPop_two();
                break;
            case R.id.image_heard://修改头像
//                selectPic();
                break;

        }
    }
//import com.tbruyelle.rxpermissions2.RxPermissions;

//    private void selectPic() {
//        RxPermissions rxPermissions = new RxPermissions(BjzlActivity.this);
//        rxPermissions.request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE).subscribe(new io.reactivex.functions.Consumer<Boolean>() {
//            @Override
//            public void accept(Boolean aBoolean) throws Exception {
//                if (aBoolean) {
//                    SelectImageUtils.getImageType(BjzlActivity.this, new SelectImageUtils.SelectImageInterface() {
//                        @Override
//                        public void xiangji() {
//                            PictureSelector.create(BjzlActivity.this)
//                                    .openCamera(SelectMimeType.ofImage())
//                                    .forResult(new OnResultCallbackListener<LocalMedia>() {
//                                        @Override
//                                        public void onResult(ArrayList<LocalMedia> selectList) {
//                                            List<String> carmPath = new ArrayList<>();
//                                            for (int i = 0; i < selectList.size(); i++) {
//                                                carmPath.add(selectList.get(i).getRealPath());
//                                            }
////                                            zipPhotos(carmPath, getPath());
//                                        }
//
//                                        @Override
//                                        public void onCancel() {
//                                        }
//                                    });
//                        }
//
//                        @Override
//                        public void xiangce() {
//                            ImagePicker.withMulti(new WXPickerPresenter())
//                                    .setMaxCount(1)
//                                    .setColumnCount(3)//设置列数
//                                    .mimeTypes(MimeType.ofImage())
//                                    .filterMimeTypes(MimeType.GIF)
//                                    .showCamera(false)//显示拍照
//                                    .setPreview(true)//开启预览
//                                    .setSinglePickWithAutoComplete(false)
//                                    .setOriginal(true)  //显示原图
//                                    .setSelectMode(SelectMode.MODE_SINGLE)
//                                    .setVideoSinglePick(false)//设置视频单选
//                                    .setSinglePickImageOrVideoType(true)//设置图片和视频单一类型选择
//                                    .setShieldList(null)//设置需要屏蔽掉的图片列表，下次选择时已屏蔽的文件不可选择
//                                    .pick(BjzlActivity.this, new OnImagePickCompleteListener() {
//                                        @Override
//                                        public void onImagePickComplete(ArrayList<ImageItem> items) {
//                                            ArrayList<String> pathList = new ArrayList<>();
//                                            for (int i = 0; i < items.size(); i++) {
//                                                pathList.add(items.get(i).path);
//                                            }
////                                            zipPhotos(pathList, getPath());
//                                        }
//                                    });
//                        }
//                    });
//                } else {
//                    ToastyUtil.showWarning(BjzlActivity.this, "请开启权限");
//                }
//            }
//        });
//
//    }


    /**
     * 修改生日
     */

    private void createTime() {

    }


    //把读取出来的json数据进行解析
    private void getJsonText(String jsonText) {
        try {
            String text = jsonText;
            try {
                if (true) {
                    JSONArray jsonArray0 = new JSONArray(text);
                    for (int i = 0; i < jsonArray0.length(); i++) {
                        String Sheng = jsonArray0.getJSONObject(i).getString("label");
                        String ShengID = jsonArray0.getJSONObject(i).getString("value");

                        List<String> list_shi = new ArrayList<>();
                        List<String> list_shiID = new ArrayList<>();
                        List<List<String>> list_xian2 = new ArrayList<List<String>>();
                        List<List<String>> list_xian2ID = new ArrayList<List<String>>();

                        if (jsonArray0.getJSONObject(i).optString("children").length() > 0) {
                            JSONArray jsonArray1 = jsonArray0.getJSONObject(i).getJSONArray("children");//市
                            for (int y = 0; y < jsonArray1.length(); y++) {
                                List<String> list_xian = new ArrayList<String>();
                                List<String> list_xianID = new ArrayList<String>();
                                if (jsonArray1.getJSONObject(y).optString("children").length() > 0) {
                                    JSONArray jsonArray2 = jsonArray1.getJSONObject(y).getJSONArray("children");//县
                                    for (int t = 0; t < jsonArray2.length(); t++) {
                                        String Xian = jsonArray2.getJSONObject(t).getString("label");
                                        list_xian.add(Xian);
                                        String XianID = jsonArray2.getJSONObject(t).getString("value");
                                        list_xianID.add(XianID);
                                    }
                                    list_xian2.add(list_xian);
                                    list_xian2ID.add(list_xianID);
                                    String Shi = jsonArray1.getJSONObject(y).getString("label");
                                    list_shi.add(Shi);
                                    String ShiID = jsonArray1.getJSONObject(y).getString("value");
                                    list_shiID.add(ShiID);
                                } else {
                                    list_xian.add("");
                                    list_xianID.add("");
                                    list_xian.add("");
                                    list_xianID.add("");
                                    list_xian2.add(list_xian);
                                    list_xian2ID.add(list_xianID);
                                    list_shi.add("");
                                    list_shiID.add("");
                                }

                            }

                        } else {
                            List<String> list_xian = new ArrayList<String>();
                            List<String> list_xianID = new ArrayList<String>();
                            list_xian.add("");
                            list_xianID.add("");
                            list_xian.add("");
                            list_xianID.add("");
                            list_xian2.add(list_xian);
                            list_xian2ID.add(list_xianID);
                            list_shi.add("");
                            list_shiID.add("");
                        }

                        options3Items.add(list_xian2);
                        options3ItemsID.add(list_xian2ID);
                        options2Items.add(list_shi);
                        options2ItemsID.add(list_shiID);
                        options1Items.add(Sheng);
                        options1ItemsID.add(ShengID);

                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        } catch (Exception e) {
        }
    }


}







