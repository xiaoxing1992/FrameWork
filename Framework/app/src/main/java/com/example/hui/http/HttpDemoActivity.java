package com.example.hui.http;

import android.widget.Toast;

import com.example.hui.http.callback.CommonCallBack;
import com.example.hui.ioc.R;
import com.example.hui.ioc.view.OnClick;
import com.example.hui.template.BaseActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 曾辉 on 2016/9/7.
 * Email : 240336124@qq.com
 * Description :
 */
public class HttpDemoActivity extends BaseActivity{

/*
    @Override
    protected void initData() {
        // 1.创建一个OkhttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        // 2.构建参数的body  MultipartBody.FORM 表单形式
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        // 2.2封装参数
        builder.addFormDataPart("appid", "1");

        // 3. 构建一个请求  post 提交里面是参数的builder   url()请求路径
        Request request = new Request.Builder().url("http://v2.ffu365.com/index.php?m=Api&c=Team&a=teamList")
                .post(builder.build()).build();

        // 4.发送一个请求
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {

            }
            // 请求的回调
            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                // 成功  数据在response里面  获取后台给我们的JSON 字符串
                String result = response.body().string();
                Log.e("TAG", result);
                Gson gson = new Gson();
            }
        });
    }
*/

    @Override
    protected void initData() {
        Map<String,String> params = new HashMap<>();
        params.put("pagesize","8");
        HttpUtils.getInstance().post(params, "http://v2.ffu365.com/index.php?m=Api&c=Team&a=teamList",
                new CommonCallBack<TeamListResult>() {
            @Override
            public void onFail() {
                // 失败
            }

            @Override
            public void onSccuess(TeamListResult listResult) {
                // 成功   result  是后台返回的JSON  -->  Object
                Toast.makeText(HttpDemoActivity.this,listResult.errmsg,Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_login);
    }

    @OnClick(R.id.login_tv)
    private void getDataClick(){
        initData();
    }
}
