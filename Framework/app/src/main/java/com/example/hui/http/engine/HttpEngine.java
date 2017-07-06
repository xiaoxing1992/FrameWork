package com.example.hui.http.engine;

import com.example.hui.http.callback.HttpCallBack;

import java.util.Map;

/**
 * Created by 曾辉 on 2016/9/7.
 * Email : 240336124@qq.com
 * Description :  定义引擎的规范
 */
public interface HttpEngine<T> {
    public void post(Map<String,String> params , String url, HttpCallBack<T> callBack);

    public void get(Map<String,String> params , String url, HttpCallBack<T> callBack);
}
