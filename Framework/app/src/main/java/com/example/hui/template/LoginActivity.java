package com.example.hui.template;

import android.widget.TextView;

import com.example.hui.ioc.R;
import com.example.hui.ioc.view.ViewById;

/**
 * Created by 曾辉 on 2016/9/6.
 * Email : 240336124@qq.com
 * Description :
 */
public class LoginActivity extends BaseActivity{

    @ViewById(R.id.login_tv)
    private TextView mLoginTv;

    @Override
    protected void initData() {

    }
    @Override
    protected void initView() {
        mLoginTv.setText("登录");
    }
    @Override
    protected void initTitle() {

    }
    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_login);
    }

    @Override
    public void finish() {
        super.finish();
        // 第一个参数进入的Activity的动画
        overridePendingTransition(R.anim.ativity_forbid_anim,R.anim.activity_out_to_bottom);
    }
}
