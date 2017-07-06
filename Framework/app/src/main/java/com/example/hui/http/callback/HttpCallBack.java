package com.example.hui.http.callback;

/**
 * Created by 曾辉 on 2016/9/7.
 * Email : 240336124@qq.com
 * Description :
 */
public interface  HttpCallBack<T> {
    public void onFail();
    public void onSccuess(T t);
}
