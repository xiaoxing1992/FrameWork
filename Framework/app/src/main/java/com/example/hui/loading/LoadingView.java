package com.example.hui.loading;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.hui.ioc.R;
import com.example.hui.ioc.view.ViewById;
import com.example.hui.ioc.view.ViewUtils;

/**
 * @类的用途:
 * @作者: 任正威
 * @date: 2017/7/6.
 */

public class LoadingView extends LinearLayout {
    @ViewById(R.id.icon_loading)
    private ShapeView iocn_loading;
    @ViewById(R.id.icon_ovel)
    private ImageView iocn_ovel;
    private Context mContext;

    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView() {
        //添加布局到容器中
        inflate(mContext, R.layout.activity_loading_layout, this);
        //view注解初始化
        ViewUtils.bondInit(this);
        //执行下落动画
        executeFallAnimation();
    }

    private void executeFallAnimation() {
        //平移动画
        ObjectAnimator fallobjectAnimator = ObjectAnimator.ofFloat(iocn_loading,
                "translationY", 0, DensityUtil.dp2px(mContext, 82));
        fallobjectAnimator.setDuration(500);
        fallobjectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        fallobjectAnimator.start();

        //缩放动画
        ObjectAnimator zoomobjectAnimator = ObjectAnimator.ofFloat(iocn_ovel, "scaleX", 1.0f, 0.2f);
        zoomobjectAnimator.setDuration(500);
        fallobjectAnimator.setInterpolator(new DecelerateInterpolator());
        zoomobjectAnimator.start();
        //监听动画结束后执行上抛动画
        zoomobjectAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                executeUpAnimation();
                //执行自定义更改view
                iocn_loading.exChangShape();
            }
        });
    }

    private void executeUpAnimation() {
        ObjectAnimator fallobjectAnimator = ObjectAnimator.ofFloat(iocn_loading, "translationY",
                DensityUtil.dp2px(mContext, 82), 0);
        fallobjectAnimator.setDuration(500);
        //设置插值器
        fallobjectAnimator.setInterpolator(new DecelerateInterpolator());
        fallobjectAnimator.start();


        ObjectAnimator zoomobjectAnimator = ObjectAnimator.ofFloat(iocn_ovel, "scaleX", 0.2f, 1.0f);
        zoomobjectAnimator.setDuration(500);
        zoomobjectAnimator.start();

        zoomobjectAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                executeFallAnimation();
            }
        });

    }
}
