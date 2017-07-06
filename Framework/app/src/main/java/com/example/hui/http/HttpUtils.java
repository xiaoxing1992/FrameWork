package com.example.hui.http;

import com.example.hui.http.callback.HttpCallBack;
import com.example.hui.http.engine.AsyHttpClientEngine;
import com.example.hui.http.engine.HttpEngine;

import java.util.Map;

/**
 * Created by 曾辉 on 2016/9/7.
 * Email : 240336124@qq.com
 * Description :
 */
public class HttpUtils<T> {
    private static HttpUtils mInstance;

    private HttpEngine mHttpEngine;

    private HttpUtils() {
        // 切换引擎
        mHttpEngine = new AsyHttpClientEngine();
    }

    public static HttpUtils getInstance() {
        if (mInstance == null) {
            synchronized (HttpUtils.class){
                if (mInstance == null){
                    mInstance = new HttpUtils();
                }
            }
        }
        return mInstance;
    }

    /**
     * 确定post提交的方式
     */
    public void post(Map<String,String> params , String url, HttpCallBack<T> callBack){
        params.put("appid","1");// 时间   公共的参数可以在这里添加
        // 切换引擎
        mHttpEngine.post(params,url,callBack);
    }

    public void get(Map<String,String> params , String url, HttpCallBack<T> callBack){
        // 切换引擎
        mHttpEngine.get(params,url,callBack);
    }
}
