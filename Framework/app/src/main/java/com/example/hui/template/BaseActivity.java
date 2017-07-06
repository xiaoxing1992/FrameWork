package com.example.hui.template;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.WindowManager;

import com.example.hui.ioc.R;
import com.example.hui.ioc.view.ViewUtils;
import com.example.hui.loading.BaseResult;
import com.example.hui.loading.LoadingView;
import com.example.hui.loading.ScreenUtils;
import com.example.hui.loading.ShowLoadingView;
import com.example.hui.titlebar.TitleBar;

import java.lang.reflect.Method;

/**
 * Created by 曾辉 on 2016/9/6.
 * Email : 240336124@qq.com
 * Description :
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected TitleBar titleBar;
    private WindowManager.LayoutParams mLodingParams;
    private LoadingView mLoadingView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 统一管理Activity
        ActivityManagerUtil.getInstance().addActivity(this);
        // 设置根布局
        setRootView();
        titleBar = (TitleBar) findViewById(R.id.title_bar);
        // 捕捉异常  传到服务器
        // 使用沉浸式状态栏
        // 绑定View
        ViewUtils.bondInit(this);

        // 处理头部
        initTitle();
        // 初始化View
        initView();
        // 初始化数据
        initData();
        try {
            Method method = this.getClass().getDeclaredMethod("initData");
            ShowLoadingView annotation = method.getAnnotation(ShowLoadingView.class);
            if (annotation != null) {
                addLoadingView();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }


    protected abstract void initData();

    protected abstract void initView();

    protected abstract void initTitle();

    protected abstract void setRootView();

    private void addLoadingView() {
        // 获取窗体管理
        final WindowManager mWM = getWindowManager();
        mLodingParams = new WindowManager.LayoutParams();
        // 计算LoadingView的高度 = 屏幕高度 - 状态栏的高度 - 头部的高度
        mLodingParams.height = (int) (ScreenUtils.getScreenHeight(this)
                - ScreenUtils.getStatusHeight(this) - getResources().getDimension(R.dimen.title_height));
        mLodingParams.width = WindowManager.LayoutParams.MATCH_PARENT;
       mLodingParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        // 效果为背景透明
        mLodingParams.format = PixelFormat.RGBA_8888;
        // 在底部显示
        mLodingParams.gravity = Gravity.BOTTOM;
        mLoadingView = new LoadingView(this);
        // 动态的添加到窗体中
        mWM.addView(mLoadingView, mLodingParams);
    }

    /**
     * 移除数据加载动画
     */
    protected void dissmissLoadingView() {
        if (mLoadingView != null) {
            final WindowManager mWM = getWindowManager();
            mWM.removeView(mLoadingView);
            this.mLoadingView = null;
            this.mLodingParams = null;
        }
    }

    /**
     * 判断返回来的参数是否为1  1成功  -1失败
     * @param baseResult
     * @return
     */
    protected boolean isNetRequestOk(BaseResult baseResult) {
        if (baseResult.errcode == 1) {
            dissmissLoadingView();
        }
        return baseResult.errcode == 1;
    }

    /**
     * 启动activity
     */
    protected void startActivity(Class<? extends Activity> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    /**
     * 这个里面定义方法是有要求的：
     * 1.继承自它的子Activity进程使用的方法，可以写到这里面简化代码
     * 2.子Activity里面某些特定里面使用的方法就不要放到这里面来,因为类的加载会开辟方法的内存
     */
    /*public void cursorToEnd(){

    }*/
}
