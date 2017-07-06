package com.example.hui.loading;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.example.hui.ioc.R;

/**
 * @类的用途:
 * @作者: 任正威
 * @date: 2017/7/6.
 */

public class ShapeView extends View {

    private int mWidth;
    private int mHeight;

    public enum Shape {
        CIRCULAR, RECTANGLE, TRIGON;
    }

    private Paint paint;
    private Shape mCurrentShape = Shape.CIRCULAR;

    public ShapeView(Context context) {
        this(context, null);
    }

    public ShapeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShapeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        paint.setAntiAlias(true);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //测量宽高
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //根据当前状态绘制不同的view
        switch (mCurrentShape) {
            case CIRCULAR:
                drawCircular(canvas);
                break;
            case RECTANGLE:
                drawRectangle(canvas);
                break;
            case TRIGON:
                drawTrigon(canvas);
                break;

        }
    }
    //绘制三角形
    private void drawTrigon(Canvas canvas) {
        paint.setColor(getResources().getColor(R.color.tongle_color));
        //因为是等边三角形所以用到的勾股定理
        Path path = new Path();
        path.moveTo(mWidth / 2, 0);
        path.lineTo(0, (float) (Math.sqrt(3)/2*mWidth));
        path.lineTo(mWidth, (float) (Math.sqrt(3)/2*mWidth));
        //很重要  必须关闭路径
        path.close();
        canvas.drawPath(path, paint);

    }
    //绘制矩形
    private void drawRectangle(Canvas canvas) {
        paint.setColor(getResources().getColor(R.color.colorAccent));
        canvas.drawRect(0, 0, mWidth, mHeight, paint);
    }
    //绘制圆形
    private void drawCircular(Canvas canvas) {
        paint.setColor(getResources().getColor(R.color.colorPrimary));
        canvas.drawCircle(mWidth / 2, mHeight / 2, mWidth / 2, paint);
    }
    //更改view的方法
    public void exChangShape() {
        switch (mCurrentShape) {
            case CIRCULAR:
                mCurrentShape = Shape.RECTANGLE;
                break;
            case RECTANGLE:
                mCurrentShape = Shape.TRIGON;
                break;
            case TRIGON:
                mCurrentShape = Shape.CIRCULAR;
                break;
        }
        invalidate();
        // 处理旋转
        ObjectAnimator rotationAnimation = null;
        switch (mCurrentShape){
            case CIRCULAR:
                rotationAnimation = ObjectAnimator.ofFloat(this,"rotation",0,180);
                break;
            case RECTANGLE:
                rotationAnimation = ObjectAnimator.ofFloat(this,"rotation",0,180);
                break;
            case TRIGON:
                rotationAnimation = ObjectAnimator.ofFloat(this,"rotation",0,-120);
                break;
        }
        rotationAnimation.setDuration(500);
        rotationAnimation.start();
    }
}
