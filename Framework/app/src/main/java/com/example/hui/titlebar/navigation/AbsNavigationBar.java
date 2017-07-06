package com.example.hui.titlebar.navigation;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hui.ioc.R;
import com.example.hui.titlebar.util.DensityUtil;

/**
 * Created by 曾辉 on 2016/9/9.
 * Email : 240336124@qq.com
 * Description :
 */
public abstract class AbsNavigationBar<P extends AbsNavigationBar.Builder.AbsNavigationParam> implements INavigation{
    private P param;
    private View mNavigationView;

    public AbsNavigationBar(P param){// 继承  封装   多态   泛型  注解  反射  AOP
        this.param = param;
        // 创建View并且绑定
        createViewAndBind();
    }

    public P getParam() {
        return param;
    }

    @Override
    public void createViewAndBind() {
        ViewGroup parent = param.mParent;
        mNavigationView = View.inflate(param.mContext,bindNavigationId(),null);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                DensityUtil.dip2px(param.mContext,48));
        mNavigationView.setLayoutParams(params);
        parent.addView(mNavigationView,0);
    }

    public <T extends View> T findViewById(int viewId){
        return (T) mNavigationView.findViewById(viewId);
    }

    /**
     * 设置TextView的文本
     */
    public void setTvText(int viewId,String text){
        TextView tv = findViewById(viewId);
        if(TextUtils.isEmpty(text)){
            tv.setVisibility(View.GONE);
        }else{
            tv.setVisibility(View.VISIBLE);
            tv.setText(text);
        }
    }

    /**
     * 设置点击事件
     */
    public void setClickListener(int viewId, View.OnClickListener clickListener){
        View view = findViewById(viewId);
        view.setOnClickListener(clickListener);
    }

    /**
     * 设置图片
     */
    public void setIvImage(int viewId, int resourceId) {
        ImageView imageView = findViewById(viewId);
        imageView.setVisibility(View.VISIBLE);
        imageView.setImageResource(resourceId);
    }

    public static abstract class Builder{
        public abstract AbsNavigationBar create();

        public class AbsNavigationParam{
            protected Context mContext;
            protected ViewGroup mParent;

            public AbsNavigationParam(Context context,ViewGroup parent){
                this.mContext = context;
                this.mParent = parent;
            }

            /**
             * 获取资源文件中的String
             */
            public String getString(int resourceId){
                return mContext.getResources().getString(resourceId);
            }
        }
    }
}
