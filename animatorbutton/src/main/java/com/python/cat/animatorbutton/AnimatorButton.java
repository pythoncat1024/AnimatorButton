package com.python.cat.animatorbutton;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.apkfuns.logutils.LogUtils;

/**
 * packageName: com.python.cat.animatorbutton
 * Created on 2017/5/4.
 *
 * @author cat
 */

public class AnimatorButton extends View {
    private int width;
    private int height;

    static {
        LogUtils.getLogConfig().configShowBorders(false);
    }

    private Paint outerPaint;
    private Paint textPaint;

    private String textStr = "确认完成";
    private RectF outerRectF;
    private PointF centerPoint;
    private int radiusMax;
    private float curRadius;
    private int rect2CircleDuration = 1000;
    private ValueAnimator rect2CircleAnimator;
    private AnimatorSet animatorSet;

    public AnimatorButton(Context context) {
        this(context, null);
    }

    public AnimatorButton(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AnimatorButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        outerPaint = new Paint();
        outerPaint.setColor(Color.parseColor("#3F51B5"));// TODO -->
        outerPaint.setStyle(Paint.Style.FILL);
        outerRectF = new RectF();
        animatorSet = new AnimatorSet();
    }

    private void setAnimator() {
        setRoundRect();
        animatorSet
                .play(rect2CircleAnimator);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.width = w;
        this.height = h;
        LogUtils.w("width = " + width + " , height = " + height);
        outerRectF.left = 0;
        outerRectF.right = width;
        outerRectF.top = 0;
        outerRectF.bottom = height;
        centerPoint = new PointF();
        centerPoint.x = Math.round(outerRectF.right / 2.0 - outerRectF.left / 2.0);
        centerPoint.y = Math.round(outerRectF.bottom / 2.0 - outerRectF.top / 2.0);
        LogUtils.d("centerPoint.x = " + centerPoint.x + " , .y = " + centerPoint.y);
        radiusMax = Math.min(width, height) / 2;
        LogUtils.d("radiusMax = " + radiusMax);
        setAnimator();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRoundRect(outerRectF, curRadius, curRadius, outerPaint);
    }

    private void setRoundRect() {
        rect2CircleAnimator = ValueAnimator.ofInt(0, radiusMax);
        rect2CircleAnimator.setDuration(rect2CircleDuration);
        rect2CircleAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                LogUtils.i("update curRadius--> " + animation.getAnimatedValue());
                curRadius = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
    }

    public void start() {
        animatorSet.start();
    }

}
