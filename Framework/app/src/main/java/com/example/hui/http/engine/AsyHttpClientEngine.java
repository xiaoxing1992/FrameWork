package com.example.hui.http.engine;

import android.os.Handler;
import android.util.Log;

import com.example.hui.http.callback.HttpCallBack;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.Map;

import cz.msebera.android.httpclient.Header;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by 曾辉 on 2016/9/7.
 * Email : 240336124@qq.com
 * Description :
 */
public class AsyHttpClientEngine<T> implements HttpEngine<T>{
    private Class<T> clazz;

    @Override
    public void post(Map<String, String> params, String url, final HttpCallBack<T> callBack) {
        // 获取HttpCallBack的泛型  ，然后再获取泛型的类
        ParameterizedType parameterizedType = (ParameterizedType) callBack.getClass().getGenericSuperclass();//获取当前new对象的泛型的父类类型
        clazz = (Class<T>) parameterizedType.getActualTypeArguments()[0];

        AsyncHttpClient  httpClient = new AsyncHttpClient();

        httpClient.post(url, new RequestParams(params), new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);

                Log.e("AsyHttpClientEngine",result);

                Gson gson = new Gson();

                T resultObj = gson.fromJson(result,clazz);

                callBack.onSccuess(resultObj);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                callBack.onFail();
            }
        });
    }

    @Override
    public void get(Map<String, String> params, String url, HttpCallBack<T> callBack) {

    }
}
