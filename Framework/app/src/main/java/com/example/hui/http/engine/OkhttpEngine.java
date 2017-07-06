package com.example.hui.http.engine;

import android.os.Handler;
import android.util.Log;

import com.example.hui.http.callback.HttpCallBack;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.Map;

import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by 曾辉 on 2016/9/7.
 * Email : 240336124@qq.com
 * Description :
 */
public class OkhttpEngine<T> implements HttpEngine<T>{
    private Class<T> clazz;

    private static Handler mHandler;

    public OkhttpEngine(){
        mHandler = new Handler();
    }

    @Override
    public void post(Map<String, String> params, String url, final HttpCallBack<T> callBack) {
        // 获取HttpCallBack的泛型  ，然后再获取泛型的类
        ParameterizedType parameterizedType = (ParameterizedType) callBack.getClass().getGenericSuperclass();//获取当前new对象的泛型的父类类型
        clazz = (Class<T>) parameterizedType.getActualTypeArguments()[0];

        Log.e("TAG",clazz+"");

        // 1.创建一个OkhttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        // 2.构建参数的body  MultipartBody.FORM 表单形式
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        // 2.2封装参数
        for (Map.Entry<String, String> entry : params.entrySet()) {
            builder.addFormDataPart(entry.getKey(), entry.getValue());
        }

        // 3. 构建一个请求  post 提交里面是参数的builder   url()请求路径
        Request request = new Request.Builder().url(url)
                .post(builder.build()).build();

        // 4.发送一个请求
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onFail();
                    }
                });
            }
            // 请求的回调
            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                // 成功  数据在response里面  获取后台给我们的JSON 字符串
                String result = response.body().string();
                Log.e("OkhttpEngine", result);
                Gson gson = new Gson();

                // result 字符串转为我们的   对象  第二个参数是class
                final T resultObj = gson.fromJson(result,clazz);

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onSccuess(resultObj);
                    }
                });
            }
        });
    }

    @Override
    public void get(Map<String, String> params, String url, HttpCallBack<T> callBack) {

    }
}
