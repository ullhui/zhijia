package com.example.home.app;

import android.app.Activity;
import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.home.db.DaoMaster;
import com.example.home.db.DaoSession;
import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.LogInterceptor;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;

public class MyApplication extends Application {


    private DaoSession daoSession;



    /**
     * 初始化 GreenDao
     */

    private void initGreenDao() {
        //创建数据库
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "test.db");//表名
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    /**
     * 获取DaoSession
     */
    public DaoSession getDaoSession() {
        return daoSession;
    }


    public static List<Activity> allActivity = new ArrayList<>();

    public static void finishAllActivity() {
        try {
            for (int i = 0; i < allActivity.size(); i++) {
                allActivity.get(i).finish();
            }
        } catch (Exception e) {

        }
    }


    @Override
    public void onCreate() {
        super.onCreate();
        initGreenDao();

        //Hawk初始化
        Hawk.init(this).setLogInterceptor(new LogInterceptor() {
            @Override
            public void onLog(String message) {
            }
        }).build();
        //okHttpUtils初始化
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(300000L, TimeUnit.MILLISECONDS)
                .readTimeout(300000L, TimeUnit.MILLISECONDS)//默认10秒钟 改为30秒
                .addInterceptor(new LoggerInterceptor("chenxue", true))
                //其他配置
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }
}
