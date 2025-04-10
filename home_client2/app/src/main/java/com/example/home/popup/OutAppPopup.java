package com.example.home.popup;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;


import com.example.home.R;
import com.lxj.xpopup.core.CenterPopupView;
import com.lxj.xpopup.util.XPopupUtils;


/**
 * 确定取消 提示弹窗
 */
public class OutAppPopup extends CenterPopupView {


    private TextView tvHint;
    private TextView tvLeft;
    private TextView tvRight;
    private OnActionClickListener listener;
    String title;

    public OutAppPopup(String title, @NonNull Context context, OnActionClickListener listener) {
        super(context);
        this.listener = listener;
        this.title = title;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.sound_pop_lite;
    }

    @Override
    protected void onCreate() {
        super.onCreate();

        tvHint = (TextView) findViewById(R.id.tv_hint);
        tvLeft = findViewById(R.id.um_login_captcha_tv_pop);
        tvRight = (TextView) findViewById(R.id.tv_right);
        tvHint.setText(title);

        tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onRightClick();
                }
                dismiss();
            }
        });

        tvLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });


    }

    //完全可见执行
    @Override
    protected void onShow() {
        super.onShow();
    }

    //完全消失执行
    @Override
    protected void onDismiss() {
        if (listener != null) {
            listener.onDismiss();
        }
    }

    @Override
    protected int getMaxHeight() {
        return (int) (XPopupUtils.getScreenHeight(getContext()) * 1f);
    }

    public void setListener(OnActionClickListener listener) {
        this.listener = listener;
    }


    public interface OnActionClickListener {
        void onDismiss();

        void onLeftClick();

        void onRightClick();

    }

}