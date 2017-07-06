package com.example.hui.loading;

import android.content.Context;

/**
 * @类的用途:
 * @作者: 任正威
 * @date: 2017/7/6.
 */

public class DensityUtil {
    public static float dp2px(Context context, float dip) {
        float density = context.getResources().getDisplayMetrics().density;
        return density * dip + 0.5f;
    }
}
