package com.example.hui.ioc.view;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by 曾辉 on 2016/9/5.
 * Email : 240336124@qq.com
 * Description :
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)// 运行时  SOURCE 编译时
public @interface OnClick {
    int[] value();
}
