package com.example.hui.titlebar;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hui.ioc.R;
import com.example.hui.ioc.view.OnClick;
import com.example.hui.ioc.view.ViewById;
import com.example.hui.ioc.view.ViewUtils;
import com.example.hui.template.ActivityManagerUtil;

/**
 * Created by 曾辉 on 2016/9/9.
 * Email : 240336124@qq.com
 * Description :
 */
public class TitleBar extends RelativeLayout{
    @ViewById(R.id.back_ll)
    private LinearLayout mBackLl;// 返回
    @ViewById(R.id.title_tv)
    private TextView mTitleTv;// 中间的文字
    @ViewById(R.id.right_tv)
    private TextView mRightTv;// 右边

    private Context mContext;

    public TitleBar(Context context) {
        this(context,null);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        inflate(context, R.layout.ui_default_navigation,this);
        // 修复一下注解的不足
        ViewUtils.bondInit(this);
    }

    /**
     * 设置标题文字
     */
    public void setTitleTv(String titleText){
        mTitleTv.setText(titleText);
    }

    /**
     * 设置右边的文字
     */
    public void setRightTv(String rightText){
        mRightTv.setVisibility(View.VISIBLE);// 设置右边显示
        mRightTv.setText(rightText);
    }

    /**
     * 设置返回不显示
     */
    public void setBackGone(){
        mBackLl.setVisibility(View.GONE);
    }

    @OnClick(R.id.back_ll)
    public void backClick() {
        // 按返回回调的方法，关闭当前所在Activity
        ActivityManagerUtil.getInstance().finishActivity((Activity) mContext);// new
    }
}
