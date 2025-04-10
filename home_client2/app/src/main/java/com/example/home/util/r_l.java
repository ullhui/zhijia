package com.example.home.util;


import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.provider.MediaStore;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.format.Formatter;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.core.app.AppOpsManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;

/**
 * Created by baba on 2018/9/5.
 */

public class r_l {




    /**
     * 判断RecyclerView是否滚动到底部
     * */
    public static boolean isVisBottom(RecyclerView recyclerView){
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        //屏幕中最后一个可见子项的position
        int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
        //当前屏幕所看到的子项个数
        int visibleItemCount = layoutManager.getChildCount();
        //当前RecyclerView的所有子项个数
        int totalItemCount = layoutManager.getItemCount();
        //RecyclerView的滑动状态
        int state = recyclerView.getScrollState();
        if(visibleItemCount > 0 && lastVisibleItemPosition == totalItemCount - 1 && state == recyclerView.SCROLL_STATE_IDLE){
            return true;
        }else {
            return false;
        }
    }





    /**
     * 视图移入移除动画
     */
    public static void move(boolean dialogView, View mView, Context mContext) {
        if (dialogView) {
            // 向左边移入
            mView.setVisibility(View.VISIBLE);
            mView.setAnimation(AnimationUtils.makeInAnimation(mContext, false));
        } else {
            // 向右边移出
            mView.setVisibility(View.GONE);
            mView.setAnimation(AnimationUtils.makeOutAnimation(mContext, true));
        }
    }




    /***
     * 判断当前手机是否链接WIFI
     */

    public static boolean isWifi(Context mContext) {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info != null
                && info.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }




    /**
     * 判读时间
     * 是过去还是未来还是今天
     */
    public static String GetTime(String endTime,String startTime) {
        String time = "";
        // 按照传入的格式生成一个simpledateformate对象
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
        long nh = 1000 * 60 * 60;// 一小时的毫秒数
        long nm = 1000 * 60;// 一分钟的毫秒数
        long ns = 1000;// 一秒钟的毫秒数
        long diff;
        try {
            // 获得两个时间的毫秒时间差异
            diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
            long day = diff / nd;// 计算差多少天
            long hour = diff % nd / nh;// 计算差多少小时
            long min = diff % nd % nh / nm;// 计算差多少分钟
            long sec = diff % nd % nh % nm / ns;// 计算差多少秒
            // 输出结果
            time = day + "";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return time;
    }


    /**
     * 时间戳转换日期
     * 秒级别
     */
    public static String stampToTime(String cc_time) {
        String re_StrTime = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // 例如：cc_time=1291778220
        long lcc_time = Long.valueOf(cc_time);
        re_StrTime = sdf.format(new Date(lcc_time * 1000L));

        return re_StrTime;
    }




    /**
     * 时间戳转换日期
     */
    public static String stampToTime2(String stamp) {
        String sd = "";
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sd = sdf.format(new Date(Long.parseLong(stamp))); // 时间戳转换日期
        return sd;
    }


    /**
     * 时间戳转换日期
     * 秒级别
     */
    public static String stampToTime3(String cc_time) {
        String re_StrTime = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // 例如：cc_time=1291778220
        long lcc_time = Long.valueOf(cc_time);
        re_StrTime = sdf.format(new Date(lcc_time * 1000L));

        return re_StrTime.substring(5,10);
    }



    /**
     * 获取控件的高度或者宽度  isHeight=true则为测量该控件的高度，isHeight=false则为测量该控件的宽度
     *
     * @param view
     * @param isHeight
     * @return
     */
    public static int getViewHeight(View view, boolean isHeight) {
        int result;
        if (view == null) return 0;
        if (isHeight) {
            int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            view.measure(h, 0);
            result = view.getMeasuredHeight();
        } else {
            int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            view.measure(0, w);
            result = view.getMeasuredWidth();
        }
        return result;
    }

    // 两次点击按钮之间的点击间隔不能少于1000毫秒
    private static final int MIN_CLICK_DELAY_TIME = 1000;
    private static long lastClickTime;

    /**
     * 防止重复点击
     */
    public static boolean isFastClick() {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
            flag = true;
        }
        lastClickTime = curClickTime;
        return flag;
    }


    /**
     * 方法2
     * 获取状态栏高度
     * 直接获取属性的方式
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 字符串截取变红
     *
     * @param otherString  你要变色的字符，可为null
     * @param changeString 你要变色的字符
     * @param allString    整个字符串
     * @param number       要变色的控件
     */
    public static void stringChangeColor(TextView number, String allString, String changeString, String otherString) {
        int fstart = allString.indexOf(changeString);
        int fend = fstart + changeString.length();
        SpannableStringBuilder style = new SpannableStringBuilder(allString);
        if (!"".equals(otherString) && otherString != null) {
            int bstart = allString.indexOf(otherString);
            int bend = bstart + otherString.length();
            style.setSpan(new ForegroundColorSpan(Color.parseColor("#397AE7")), bstart, bend, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        }
        style.setSpan(new ForegroundColorSpan(Color.parseColor("#397AE7")), fstart, fend, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        number.setText(style);
    }



    public static UpdateMy mUpdateMy;

    public void setUpdateMy(UpdateMy mUpdateMy) {
        this.mUpdateMy = mUpdateMy;
    }

    public static interface UpdateMy {
        void gengxin();
    }


    /**
     * 包含大小写字母及数字且在6-12位
     * 是否包含 2020年12月22日14:23:28
     *
     * @param str
     * @return
     */
    public static boolean isLetterDigit(String str) {
        boolean isDigit = false;//定义一个boolean值，用来表示是否包含数字
        boolean isLetter = false;//定义一个boolean值，用来表示是否包含字母
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {   //用char包装类中的判断数字的方法判断每一个字符
                isDigit = true;
            } else if (Character.isLetter(str.charAt(i))) {  //用char包装类中的判断字母的方法判断每一个字符
                isLetter = true;
            }
        }
        String regex = "^[a-zA-Z0-9]{8,20}$";
        boolean isRight = isDigit && isLetter && str.matches(regex);
        return isRight;
    }




    public static String parseDate(String createTime) {
        return null;
    }

    /**
     * 两个时间间隔
     */
    public static String getDatePoor(String Starttime) {
        String outTime = "";
        //今日Date
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = df.format(new Date());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date nowDate = null;
        try {
            nowDate = simpleDateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date endDate = null;
        try {
            endDate = simpleDateFormat2.parse(Starttime);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        long sec = diff % nd % nh % nm / ns;
//        return day + "天" + hour + "小时" + min + "分钟";


        outTime = Math.abs(day) + "天" + Math.abs(hour) + "小时" + Math.abs(min) + "分钟" + Math.abs(sec) + "秒";
        return outTime;
    }


    /**
     * 两个时间间隔
     */
    public static String getDatePoor2(String Starttime) {

        //今日Date
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = df.format(new Date());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date nowDate = null;
        try {
            nowDate = simpleDateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date endDate = null;
        try {
            endDate = simpleDateFormat2.parse(Starttime);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day * 24 * 60 + hour * 60 + min + "";
    }


    /**
     * 获取本年本月
     */
    public static String getYue() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");//今天日期
        return df.format(new Date());
    }

    /**
     * 获取本年本月
     */
    public static String getYue2() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月");//今天日期
        return df.format(new Date());
    }


    /*
     ** 判断当前时间是否在传入的时间之后
     * 判断传入的时间是否过期
     */
    public static boolean isAfterTime(String time) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentTime = new Date(System.currentTimeMillis());
        Calendar curTime = Calendar.getInstance();
        Calendar scaTime = Calendar.getInstance();
        try {
            Date timeScale = df.parse(time);
            curTime.setTime(df.parse(df.format(currentTime)));
            scaTime.setTime(timeScale);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (curTime.after(scaTime)) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 获取当前年月日
     */
    public static String getData() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//今天日期
        return df.format(new Date());
    }


    /**
     * 获取月和年
     */
    public static String getYearAndMouth() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//今天日期
        return df.format(new Date()).toString().substring(0, 4) + "-" + df.format(new Date()).toString().substring(5, 7);
    }


    /**
     * 昨天天日期
     */
    public static String getYesterday() {
        SimpleDateFormat sf = new SimpleDateFormat("MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -1);
        return sf.format(c.getTime());
    }


    /**
     * 明天日期
     */
    public static String getTomorrow() {
        SimpleDateFormat sf = new SimpleDateFormat("MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 1);
        return sf.format(c.getTime());
    }



    /**
     * 15天后日期
     */
    public static String get15Tomorrow() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 14);
        return sf.format(c.getTime());
    }


    /**
     * 根据年 月 获取对应的月份 天数
     */
    public static int getDaysByYearMonth(String data) {
        int year = Integer.parseInt(data.substring(0, 4));
        int month = Integer.parseInt(data.substring(5, 7));

        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }


    /**
     * 根据日期获取当天是周几
     *
     * @param datetime 日期
     * @return 周几
     */
    public static String dateToWeek(String datetime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String[] weekDays = {"日", "一", "二", "三", "四", "五", "六"};
        Calendar cal = Calendar.getInstance();
        Date date;
        try {
            date = sdf.parse(datetime);
            cal.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        return weekDays[w];
    }


    //基姆拉尔森计算公式根据日期判断星期几
    public static String CalculateWeekDay(int y, int m, int d) {
        String xingqi = null;
        if (m == 1 || m == 2) {
            m += 12;
            y--;
        }
        int iWeek = (d + 2 * m + 3 * (m + 1) / 5 + y + y / 4 - y / 100 + y / 400) % 7;
        switch (iWeek) {
            case 0:
                xingqi = "周一";
                break;
            case 1:
                xingqi = "周二";
                break;
            case 2:
                xingqi = "周三";
                break;
            case 3:
                xingqi = "周四";
                break;
            case 4:
                xingqi = "周五";
                break;
            case 5:
                xingqi = "周六";
                break;
            case 6:
                xingqi = "周日";
                break;
        }
        return xingqi;
    }


    /**
     * 计算时间间隔
     */
    public static String GetdateDiff(String startTime, String endTime, String format) {
        String time = "";
        // 按照传入的格式生成一个simpledateformate对象
        SimpleDateFormat sd = new SimpleDateFormat(format);
        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
        long nh = 1000 * 60 * 60;// 一小时的毫秒数
        long nm = 1000 * 60;// 一分钟的毫秒数
        long ns = 1000;// 一秒钟的毫秒数
        long diff;
        try {
            // 获得两个时间的毫秒时间差异
            diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
            long day = diff / nd;// 计算差多少天
            long hour = diff % nd / nh;// 计算差多少小时
            long min = diff % nd % nh / nm;// 计算差多少分钟
            long sec = diff % nd % nh % nm / ns;// 计算差多少秒
            // 输出结果
            System.out.println("时间相差：" + day + "天" + hour + "小时" + min + "分钟");
            time = day + "天" + hour + "小时" + min + "分钟";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return time;
    }


    /**
     * 将带有算数运算符的字符串进行算数运算
     * 2018-09-13
     * 作者：康小岱
     */
    public static Double calculate(String num) {
        List<Double> a1 = new ArrayList<Double>();
        List<String> a2 = new ArrayList<String>();
        if (num.substring(0, 1).equals("-")) {
            a2.add("-");
            num = num.substring(1);
        } else {
            a2.add("+");
        }

        String shuzi = "";
        for (int i = 0; i < num.length(); i++) {
            if (!num.substring(i, i + 1).equals("+") && !num.substring(i, i + 1).equals("-")
                    && !num.substring(i, i + 1).equals("*") && !num.substring(i, i + 1).equals("/")) {
                shuzi = shuzi + num.substring(i, i + 1);
                if (i == num.length() - 1) {
                    a1.add(Double.parseDouble(shuzi));
                }
            } else {// 遇到非数字
                a1.add(Double.parseDouble(shuzi));
                a2.add(num.substring(i, i + 1));
                shuzi = "";
            }
        }
        System.out.println("a1字符串" + a1.toString());
        System.out.println("a2字符串" + a2.toString());
        Double zong = 0.0;
        for (int i = 0; i < a1.size(); i++) {
            switch (a2.get(i)) {
                case "+":
                    zong = zong + a1.get(i);
                    break;
                case "-":
                    zong = zong - a1.get(i);
                    break;
                case "*":
                    zong = zong * a1.get(i);
                    break;
                case "/":
                    zong = zong / a1.get(i);
                    break;
            }
        }
        System.out.println("最后的计算结果==" + zong);
        return zong;
    }


    /**
     * 返回当前程序版本号
     */
    public static String getAppVersionCode(Context context) {
        int versioncode = 0;
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            // versionName = pi.versionName;
            versioncode = pi.versionCode;
        } catch (Exception e) {
        }
        return versioncode + "";
    }

    /**
     * 返回当前程序版本名
     */
    public static String getAppVersionName(Context context) {
        String versionName = null;
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
        } catch (Exception e) {
        }
        return versionName;
    }


    /**
     * 判断手机是否开启位置服务
     */
    public static boolean isLocServiceEnable(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean network = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if (gps || network) {
            return true;
        }
        return false;
    }


    /**
     * 判断手机是否开启定位权限
     */

    public static int checkOp(Context context) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 19) {
            Object object = context.getSystemService(Context.APP_OPS_SERVICE);
//            Object object = context.getSystemService("appops");
            Class c = object.getClass();
            try {
                Class[] cArg = new Class[3];
                cArg[0] = int.class;
                cArg[1] = int.class;
                cArg[2] = String.class;
                Method lMethod = c.getDeclaredMethod("checkOp", cArg);
                return (Integer) lMethod.invoke(object, 2, Binder.getCallingUid(), context.getPackageName());
            } catch (Exception e) {
                e.printStackTrace();
                if (Build.VERSION.SDK_INT >= 23) {
                    return AppOpsManagerCompat.noteOp(context, "AppOpsManager.OPSTR_FINE_LOCATION", context.getApplicationInfo().uid,
                            context.getPackageName());
                }

            }
        }
        return -1;
    }


    /*
     * 获取时间戳
     */
    public static String dateToStamp() {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//今天日期
        String time2 = df.format(new Date());


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String res = "";
        if (!"".equals(time2)) {
            try {
                res = String.valueOf(sdf.parse(time2).getTime() / 1000);
            } catch (Exception e) {
                System.out.println("传入了null值");
            }
        } else {
            long time = System.currentTimeMillis();
            res = String.valueOf(time / 1000);
        }

        return res;
    }


    /**
     * MD5加密
     */
    public static String stringToMD5(String plainText) {
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(
                    plainText.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有这个md5算法！");
        }
        String md5code = new BigInteger(1, secretBytes).toString(16);
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }





    /**
     * 隐藏虚拟按键，并且全屏
     */
    public static void hideBottomUIMenu(Activity activity) {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = activity.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = activity.getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }


    /**
     * 验证手机号是否正确
     *
     * @return
     */
    public static boolean PhoneYz(String phone) {
//        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9])|(16[6]))\\d{8}$";
//        Pattern p = Pattern.compile(regex);
//        Matcher m = p.matcher(phone);  //registrant_phone  ====  电话号码字段
//        boolean isMatch = m.matches();
//        if (!isMatch) {
//            return false;
//        } else {
//            return true;
//        }

        if (phone.length() == 11) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 2020年12月22日15:50:20
     * 手机获取设备唯一码
     */
    public static String getAppToken() {
        String uuid = "";
        String serial = null;
        String m_szDevIDShort = "35" +
                Build.BOARD.length() % 10 + Build.BRAND.length() % 10 +
                Build.CPU_ABI.length() % 10 + Build.DEVICE.length() % 10 +
                Build.DISPLAY.length() % 10 + Build.HOST.length() % 10 +
                Build.ID.length() % 10 + Build.MANUFACTURER.length() % 10 +
                Build.MODEL.length() % 10 + Build.PRODUCT.length() % 10 +
                Build.TAGS.length() % 10 + Build.TYPE.length() % 10 +
                Build.USER.length() % 10; //13 位
        try {
            serial = Build.class.getField("SERIAL").get(null).toString();
            //API>=9 使用serial号
            uuid = new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
        } catch (Exception exception) {
            //serial需要一个初始化
            serial = "serial"; // 随便一个初始化
            //使用硬件信息拼凑出来的15位号码
            uuid = new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
        }
        return uuid;
    }

    /**
     * 获取下个月的年与月
     * 2020-09-19添加
     */
    public static String getNextYearMonth(int num) {
        String nextMonth = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar calendar = Calendar.getInstance();
        //获取下个月的年份月份
        calendar.add(Calendar.MONTH, num);
        nextMonth = sdf.format(calendar.getTime());
        System.out.println("增加后的年份：" + nextMonth);
        return nextMonth;
    }


    /**
     * 处理时间
     *
     * @param oldTime 原时间
     * @param add     增加时间
     * @return
     * @throws ParseException
     */
    public static String TimeAdd(String oldTime, String add) throws ParseException {
        int addMit = Integer.valueOf(add);
        DateFormat df = new SimpleDateFormat("HH:mm");
        Date date = df.parse(oldTime);
        Date expireTime = new Date(date.getTime() + addMit * 60 * 1000);
        String newTime = df.format(expireTime);
        return newTime;

    }


    /**
     * 两个时间间隔
     */
    public static String getDatePoor3(String Starttime) {

        //今日Date
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = df.format(new Date());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date nowDate = null;
        try {
            nowDate = simpleDateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date endDate = null;
        try {
            endDate = simpleDateFormat2.parse(Starttime);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day * 24 * 60 + hour * 60 + min + "";
    }


    public static void main(String[] args) {
        String time = parseDate("2021-01-06 08:30:00");
        System.out.println("时间间隔" + time);
    }

    /**
     * 两个时间间隔
     */
    public static String getDatePoor4(String Starttime) {
        String outTime = "";
        //今日Date
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = df.format(new Date());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date nowDate = null;
        try {
            nowDate = simpleDateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date endDate = null;
        try {
            endDate = simpleDateFormat2.parse(Starttime);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        long sec = diff % nd % nh % nm / ns;


        if (day > 0) {
            outTime = day + "天" + hour + "小时";
        } else if (hour > 0) {
            outTime = hour + "小时" + min + "分钟";
        } else if (min > 0) {
            outTime = min + "分钟" + sec + "秒";
        } else {
            outTime = "0分钟" + sec + "秒";
        }
        return outTime;
    }


    /**
     * 得到内置存储空间的总容量
     *
     * @param context
     * @return
     */
    public static String getInternalToatalSpace(Context context) {
        String path = Environment.getDataDirectory().getPath();
        StatFs statFs = new StatFs(path);
        long blockSize = statFs.getBlockSize();
        long totalBlocks = statFs.getBlockCount();
        long availableBlocks = statFs.getAvailableBlocks();
        long useBlocks = totalBlocks - availableBlocks;

        long rom_length = totalBlocks * blockSize;

        return Formatter.formatFileSize(context, rom_length);
    }









    /**
     * 两个时间间隔
     */
    public  static  void getDatePoor(String Starttime,String server_date_time,TextView tv_time_new_01,TextView tv_time_new_02,TextView tv_time_new_03,TextView tv_time_new_04) {
        //今日Date
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time11 = df.format(new Date());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date nowDate = null;
        try {
            nowDate = simpleDateFormat.parse(server_date_time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date endDate = null;
        try {
            endDate = simpleDateFormat2.parse(Starttime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        long sec = diff % nd % nh % nm / ns;
//        return day + "天" + hour + "小时" + min + "分钟";
        if (Math.abs(day) > 9) {
            tv_time_new_01.setText(Math.abs(day) + "");
        } else {
            tv_time_new_01.setText("0" + Math.abs(day));
        }

        if (Math.abs(hour) > 9) {
            tv_time_new_02.setText(Math.abs(hour) + "");
        } else {
            tv_time_new_02.setText("0" + Math.abs(hour) + "");
        }


        if (Math.abs(min) > 9) {
            tv_time_new_03.setText(Math.abs(min) + "");
        } else {
            tv_time_new_03.setText("0" + Math.abs(min) + "");
        }

        if (Math.abs(sec) > 9) {
            tv_time_new_04.setText(Math.abs(sec) + "");
        } else {
            tv_time_new_04.setText("0" + Math.abs(sec) + "");
        }

    }



}