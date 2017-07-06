package com.example.hui.dialog;

import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;

/**
 * ============================================================
 * <p/>
 * copyright ZENG　HUI (c) 2014
 * <p/>
 * author : HUI
 * <p/>
 * version : 1.0
 * <p/>
 * date created : On November 24, 2014 9:14:37
 * <p/>
 * description : 图片加载的耦合工具类
 * <p/>
 * revision history :
 * <p/>
 * ============================================================
 */
public class ImageLoadCouplingUtil {
    private static ImageLoadCouplingUtil mInstance;

    private static int mErrorResouce;

    public static void initErrorResouce(int errorResouce) {
        mErrorResouce = errorResouce;
    }

    private ImageLoadCouplingUtil() {

    }

    public static ImageLoadCouplingUtil getInstance() {
        if (mInstance == null) {
            synchronized (ImageLoadCouplingUtil.class) {
                if (mInstance == null) {
                    mInstance = new ImageLoadCouplingUtil();
                }
            }
        }
        return mInstance;
    }

    /**************************
     * 本地文件图片加载
     ****************************/
    public void loadImage(File file, ImageView imageView) {
        Glide.with(imageView.getContext()).load(file).error(mErrorResouce)
                .into(imageView);
    }

    public void loadImage(File file, ImageView imageView, int errorResouce) {
        Glide.with(imageView.getContext()).load(file).error(errorResouce)
                .into(imageView);
    }

    /**************************
     * 本地资源图片加载
     ****************************/
    public void loadImage(int resouceId, ImageView imageView) {
        Glide.with(imageView.getContext()).load(resouceId).error(mErrorResouce)
                .into(imageView);
    }

    public void loadImage(int resouceId, ImageView imageView, int errorResouce) {
        Glide.with(imageView.getContext()).load(resouceId).error(errorResouce)
                .into(imageView);
    }

    /**************************
     * url图片加载
     ****************************/
    public void loadImage(String url, ImageView imageView) {
        display(url, imageView, mErrorResouce);
    }

    public void loadImage(String url, ImageView imageView, int errorResouce) {
        display(url, imageView, errorResouce);
    }

    private void display(String url, ImageView imageView, int errorResouce) {
        Glide.with(imageView.getContext()).load(url).error(errorResouce)
                .into(imageView);
    }
}
