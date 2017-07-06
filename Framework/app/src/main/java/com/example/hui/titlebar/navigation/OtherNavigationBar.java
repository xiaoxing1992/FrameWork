package com.example.hui.titlebar.navigation;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.example.hui.ioc.R;
import com.example.hui.template.ActivityManagerUtil;

/**
 * Created by 曾辉 on 2016/9/9.
 * Email : 240336124@qq.com
 * Description :  默认的头部
 */
public class OtherNavigationBar extends AbsNavigationBar<OtherNavigationBar.Builder.OtherNavigationParam>{

    public OtherNavigationBar(Builder.OtherNavigationParam param) {
        super(param);
        applyView();
    }

    @Override
    public int bindNavigationId() {
        return R.layout.navigation_default;
    }

    /**
     * 给头部的布局设置参数
     */
    @Override
    public void applyView() {
        setTvText(R.id.title_tv,getParam().title);
        setTvText(R.id.right_tv,getParam().right);
        setClickListener(R.id.right_tv,getParam().rightClick);

        setClickListener(R.id.left_ll, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityManagerUtil.getInstance().finishActivity((Activity) getParam().mContext);
            }
        });

        setIvImage(R.id.iv_left,getParam().leftResourceId);
    }


    /**
     * 建造者Builder 给  DefaultNavigationParam 设置参数
     */
    public static class Builder extends AbsNavigationBar.Builder{
        OtherNavigationParam P;

        public Builder(Context context, ViewGroup parent){
            P = new OtherNavigationParam(context,parent);
        }

        @Override
        public OtherNavigationBar create() {
            OtherNavigationBar defaultNavigationBar = new OtherNavigationBar(P);
            return defaultNavigationBar;
        }

        /********************设置标题*********************/
        public Builder setTitle(String title){
            P.title = title;
            return this;
        }

        public Builder setTitle(int titleResourceId){
            // String titleStr =  P.mContext.getResources().getString(title);
            setTitle(P.getString(titleResourceId));
            return this;
        }

        /********************设置右边*********************/
        public Builder setRightTv(String title){
            P.right = title;
            return this;
        }

        public Builder setRightTv(int titleResourceId){
            setRightTv(P.getString(titleResourceId));
            return this;
        }

        /***************设置右边点击事件***************/
        public Builder setRightClick(View.OnClickListener rightClick){
            P.rightClick = rightClick;
            return this;
        }

        /***************设置左边是否显示***************/
        public Builder setHideLeftBack(){
            P.leftIsShow = View.GONE;
            return this;
        }

        /***************设置右边显示的图片***************/
        public Builder setLeftIv(int leftResourceId){
            P.leftResourceId = leftResourceId;
            return this;
        }

        /**
         * 放置参数
         */
        public class OtherNavigationParam extends AbsNavigationParam{
            public String title;
            public String right;
            public View.OnClickListener rightClick;
            public int leftIsShow = View.VISIBLE;
            public int leftResourceId;

            public OtherNavigationParam(Context context, ViewGroup parent) {
                super(context, parent);
            }
        }
    }
}
