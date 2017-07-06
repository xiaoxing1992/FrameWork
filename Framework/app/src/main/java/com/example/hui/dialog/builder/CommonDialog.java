package com.example.hui.dialog.builder;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.example.hui.ioc.R;

/**
 * Created by 曾辉 on 2016/9/12.
 * Email : 240336124@qq.com
 * Description :
 */
public class CommonDialog extends Dialog{
    private View mDialogRootView;

    public CommonDialog(Context context) {
        super(context);
    }

    public CommonDialog(Context context, int themeResId) {
        super(context, themeResId);
    }


    /**
     * 设置点击
     */
    public void setOnClicklisenter(int viewId, View.OnClickListener listener){
        mDialogRootView.findViewById(viewId).setOnClickListener(listener);
    }

    /**
     * 设置文本
     */
    public void setText(int viewId, String text){
       TextView tv = (TextView) mDialogRootView.findViewById(viewId);
        tv.setText(text);
    }

    /**
     * 获取View
     */
    public <T extends View> T getView(int viewId){
        return (T) mDialogRootView.findViewById(viewId);
    }


    /**
     * 绑定和设置参数
     */
    public void apply(Builder.CommonParam P){
        if(P.mView != null){
            mDialogRootView = P.mView;
        }

        if(P.mViewLayoutResId != 0){
            mDialogRootView = View.inflate(P.context,P.mViewLayoutResId,null);
        }

        // 设置内容
        setContentView(mDialogRootView);

        // 设置动画
        Window window = getWindow();
        // 5.2 设置动画  resouceId 又是一个style
        if(P.animation != 0){
            window.setWindowAnimations(P.animation);
        }
        window.setGravity(P.gravity);


        // 设置宽高
        window.setLayout(P.width,P.height);

    }



    public static class Builder{
        private CommonParam P;

        public Builder(Context context){
            P = new CommonParam(context);
        }

        public Builder(Context context,int themeResId){
            P = new CommonParam(context,themeResId);
        }

        /**
         * Set a custom view to be the contents of the Dialog. If the supplied view is an instance
         * of a {@link ListView} the light background will be used.
         *
         * @param view The view to use as the contents of the Dialog.
         *
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public Builder setView(View view) {
            P.mView = view;
            P.mViewLayoutResId = 0;
            return this;
        }

        public Builder setView(int layoutId) {
            P.mView = null;
            P.mViewLayoutResId = layoutId;
            return this;
        }

        public CommonDialog create(){
            CommonDialog commonDialog = new CommonDialog(P.context,P.themeResId);
            commonDialog.apply(P);
            return commonDialog;
        }

        /**
         * 从底部弹出
         */
        public Builder fromBottom() {
            P.animation = R.style.main_menu_animstyle;
            P.gravity = Gravity.BOTTOM;
            return this;
        }

        /**
         * 设置宽度
         */
        public Builder setWidth(int width){
            P.width = width;
            return this;
        }

        /**
         * 设置高度
         */
        public Builder setHeight(int height){
            P.height = height;
            return this;
        }


        /**
         * 设置动画
         */
        public Builder setAnimations(int animations){
            P.animation = animations;
            return this;
        }

        public class CommonParam{
            public View mView;
            public int mViewLayoutResId = 0;
            public Context context; // 上下文
            public int themeResId = R.style.dialog; // 主题
            public int width = ViewGroup.LayoutParams.WRAP_CONTENT;
            public int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            public int animation =  0; // 执行动画
            public int gravity = Gravity.NO_GRAVITY;

            public CommonParam(Context context, int themeResId) {
                this.context = context;
                this.themeResId = themeResId;
            }

            public CommonParam(Context context) {
                this.context = context;
            }
        }
    }
}
