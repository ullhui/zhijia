package com.example.home.util;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.example.home.R;

public class SelectImageUtils {

    /**
     * 选择视频上传的方式dialog
     *
     */
    public static Dialog dialogMore;

    public static void getImageType(Activity mActivity,SelectImageInterface mSelectImageInterface) {
        dialogMore = new Dialog(mActivity, R.style.FullHeightDialog);
        dialogMore.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogMore.setContentView(R.layout.alert_setphoto_menu_layout2);
        Window dialogWindow = dialogMore.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.BOTTOM);
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT; // 高度
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度
        RelativeLayout relativelayout_1 = dialogMore.findViewById(R.id.relativelayout_1);
        RelativeLayout relativelayout_3 = dialogMore.findViewById(R.id.relativelayout_3);
        relativelayout_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogMore.dismiss();
                mSelectImageInterface.xiangce();

            }
        });
        //拍照
        relativelayout_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogMore.dismiss();
                mSelectImageInterface.xiangji();
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







   public static SelectImageInterface mSelectImageInterface;


    public static interface SelectImageInterface {
        void xiangji();

        void xiangce();

    }


}
