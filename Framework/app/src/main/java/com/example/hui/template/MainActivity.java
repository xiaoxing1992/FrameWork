package com.example.hui.template;

import android.widget.EditText;
import android.widget.TextView;

import com.example.hui.ioc.R;
import com.example.hui.ioc.view.OnClick;
import com.example.hui.ioc.view.ViewById;

/**
 * Created by 曾辉 on 2016/9/6.
 * Email : 240336124@qq.com
 * Description :
 */
public class MainActivity extends BaseActivity{
    @ViewById(R.id.text)
    private TextView mTextTv;
    @ViewById(R.id.text_et)
    private EditText mTextEt;
    private int mPage = 1;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mTextTv.setText("ViewById!");
        mTextEt.setText("ViewByIdEt");
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void setRootView() {
        // 设置布局
        setContentView(R.layout.activity_main);
    }

    @OnClick(R.id.click_bt1)
    private void clickClick(){
        startActivity(LoginActivity.class);
        // 调用该方法开启Activity的启动动画
        // 0 发现后面变成了黑色背景
        // overridePendingTransition(R.anim.activity_in_from_bottom,0);
        overridePendingTransition(R.anim.activity_in_from_bottom,R.anim.ativity_forbid_anim);
    }
}
