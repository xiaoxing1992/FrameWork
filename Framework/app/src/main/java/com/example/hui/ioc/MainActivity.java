package com.example.hui.ioc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hui.ioc.view.CheckNet;
import com.example.hui.ioc.view.OnClick;
import com.example.hui.ioc.view.ViewById;
import com.example.hui.ioc.view.ViewUtils;
import com.example.hui.template.ActivityManagerUtil;


public class MainActivity extends AppCompatActivity {
    @ViewById(R.id.text)
    private TextView mTextTv;
    @ViewById(R.id.text_et)
    private EditText mTextEt;
    private int mPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 统一管理Activity
        ActivityManagerUtil.getInstance().addActivity(this);
        // 设置布局
        setContentView(R.layout.activity_main);
        // 绑定View
        ViewUtils.bondInit(this);
        initTitle();
        initView();
        initData();
    }

    /**
     * 处理头部
     */
    private void initTitle() {

    }

    /**
     * 请求数据
     */
    private void initData() {
        // 请求数据
    }

    /**
     * 初始化View
     */
    private void initView() {
        mTextTv.setText("ViewById!");
        mTextEt.setText("ViewByIdEt");
    }

    /*@OnClick({R.id.click_bt1,R.id.click_bt2})
    private void login(){
        if(NetManagerUtil.cureentNetIsAvailable(this)){// 当前有没有网
            Toast.makeText(this,"请求后台数据",Toast.LENGTH_LONG).show();
            //
        }else{
            Toast.makeText(this,"当前无网络",Toast.LENGTH_LONG).show();
        }
    }*/

    @OnClick({R.id.click_bt1,R.id.click_bt2})
    @CheckNet
    private void login(){
        Toast.makeText(this,"请求后台数据",Toast.LENGTH_LONG).show();
    }
}
