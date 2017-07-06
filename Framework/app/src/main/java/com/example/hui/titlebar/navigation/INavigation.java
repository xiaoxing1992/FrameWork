package com.example.hui.titlebar.navigation;

/**
 * Created by 曾辉 on 2016/9/9.
 * Email : 240336124@qq.com
 * Description :  导航条的规范
 */
public interface INavigation {
    // 获取布局Id
    public int bindNavigationId();

    // 创建和绑定布局
    public void createViewAndBind();

    // 绑定和设置参数
    public void applyView();
}
