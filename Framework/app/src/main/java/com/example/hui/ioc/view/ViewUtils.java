package com.example.hui.ioc.view;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.hui.ioc.NetManagerUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by 曾辉 on 2016/9/5.
 * Email : 240336124@qq.com
 * Description :
 */
public class ViewUtils {
    public static void bondInit(Activity activity){
        ViewAssist viewAssist = new ViewAssist(activity);
        // 绑定注解
        Class<?> clazz =  activity.getClass();
        findViewById(clazz,viewAssist);
        onClickListener(clazz,viewAssist);
    }

    public static void bondInit(View view){
        ViewAssist viewAssist = new ViewAssist(view);
        // 绑定注解
        Class<?> clazz =  view.getClass();
        findViewById(clazz,viewAssist);
        onClickListener(clazz,viewAssist);
    }

    /**
     * 处理点击注解
     */
    private static void onClickListener(Class<?> clazz, final ViewAssist viewAssist) {
        // 绑定注解

        // 1.获取所有的方法
        Method[] methods = clazz.getDeclaredMethods();

        for (final Method method:methods){
            // 2.遍历所有的方法  有没有OnClick注解
            OnClick onClick = method.getAnnotation(OnClick.class);
            // 判断方法上面需不需要检测网络
            final boolean isCheckNet = method.getAnnotation(CheckNet.class) != null;

            if(onClick!=null){
                // 3.获取所有方法上面的viewId值
                int[] viewIds = onClick.value();

                // 4.循环遍历找出所有的View
                for (int viewId:viewIds){
                    View view = viewAssist.findViewById(viewId);

                    // 4.设置点击事件
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // 先判断一下需不需要检测网络
                            if(isCheckNet){
                                    if(!NetManagerUtil.cureentNetIsAvailable(viewAssist.getContext())){
                                        Toast.makeText(viewAssist.getContext(),"当前无网络",Toast.LENGTH_LONG).show();
                                        return;
                                    }
                            }
                            // 5.利用反射执行方法
                            method.setAccessible(true); // 设置可以执行私有的方法
                            try {
                                method.invoke(viewAssist.getObj());
                            } catch (Exception e) {
                                e.printStackTrace();
                                try {
                                    method.invoke(viewAssist.getObj(),view);
                                } catch (Exception e1) {
                                    e1.printStackTrace();
                                }
                            }
                        }
                    });
                }
            }
        }
    }

    private static void findViewById(Class<?> clazz,ViewAssist viewAssist){

        // 1.获取Activity中的所有属性
        // clazz.getFields();  只会获取公共
        Field[] files = clazz.getDeclaredFields();

        // 2.遍历所有的属性看属性上面有没有ViewById的注解
        for (Field field:files){
            ViewById viewById = field.getAnnotation(ViewById.class);
            if(viewById != null){
                // 3.如果不是空我们获取注解的viewId
                int viewId = viewById.value();

                // 4.findViewById
                View view = viewAssist.findViewById(viewId);

                // 5.把当前View动态注入到属性中
                try {
                    field.setAccessible(true);// 我申请操作私有的对象
                    field.set(viewAssist.getObj(),view);// arg1  arg2
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
