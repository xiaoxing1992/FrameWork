package com.example.hui.ioc.view;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;

/**
 * Created by 曾辉 on 2016/9/9.
 * Email : 240336124@qq.com
 * Description :
 */
public class ViewAssist {

    private View view;
    private Activity activity;

    public ViewAssist(View view){
        this.view = view;
    }

    public ViewAssist(Activity activity){
        this.activity = activity;
    }

    public View findViewById(int viewId) {
        return activity == null?view.findViewById(viewId):activity.findViewById(viewId);
    }

    public Object getObj() {
        return activity==null?view:activity;
    }

    public Context getContext() {
        return activity==null?view.getContext():activity;
    }
}
