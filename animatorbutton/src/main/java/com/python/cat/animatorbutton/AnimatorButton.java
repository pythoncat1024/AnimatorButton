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

    private final int DEFAULT_DURATION = 1000;
    private Paint outerPaint;
    private Paint textPaint;

    private String textStr = "确认完成";
    private RectF outerRectF;
    private PointF centerPoint;
    private int radiusMax;
    private float curRadius;
    private ValueAnimator rect2CircleAnimator;
    private AnimatorSet animatorSet;
    private RectF textRectF;
    private int divider;
    private int outerLength;
    private ValueAnimator roundRectF2CircleAnimator;

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
        // outerPaint
        outerPaint = new Paint();
        outerPaint.setColor(Color.parseColor("#3F51B5"));// TODO -->
        outerPaint.setStyle(Paint.Style.FILL);

        // textPaint
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(40); // TODO -->
        textPaint.setColor(Color.WHITE);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setAntiAlias(true);

        outerRectF = new RectF();
        textRectF = new RectF();
        animatorSet = new AnimatorSet();
    }

    private void setAnimator() {
        setRoundRect();
        setRoundRectF2Circle();
        animatorSet
                .play(rect2CircleAnimator)
                .before(roundRectF2CircleAnimator)
        ;
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
        outerLength = width;
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
        drawOuterRectF(canvas);
        drawText(canvas);
    }

    private void drawOuterRectF(Canvas canvas) {
        outerRectF.left = centerPoint.x - outerLength / 2;
        outerRectF.right = centerPoint.x + outerLength / 2;
        canvas.drawRoundRect(outerRectF, curRadius, curRadius, outerPaint);
    }

    private void setRoundRectF2Circle() {
        // max --> min
        final int min = radiusMax * 2;
        final int max = width;
        roundRectF2CircleAnimator = ValueAnimator.ofInt(width, radiusMax * 2);
        roundRectF2CircleAnimator.setDuration(DEFAULT_DURATION);// TODO -->
        roundRectF2CircleAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                LogUtils.d("rect2circle ---> " + value);
                outerLength = value;
                // max --> 255 min --> 0
                // 600 -> 150  599 598
                double alpha = 1.0 * (value - min) / (max - min) * 255;
                LogUtils.w("#### max = " + width + " , min = " + radiusMax * 2);
                LogUtils.e("alpha = " + alpha + " , value = " + value
                        + " max=" + max + " min = " + min
                        + " up = " + (value - min) + " down = " + (max - min));
                textPaint.setAlpha((int) alpha);
                invalidate();
            }
        });
    }

    /**
     * 绘制文字
     *
     * @param canvas 画布
     */
    private void drawText(Canvas canvas) {
        textRectF.left = 0;
        textRectF.top = 0;
        textRectF.right = width;
        textRectF.bottom = height;
        Paint.FontMetricsInt fontMetrics = textPaint.getFontMetricsInt();
        int baseline = (int) ((textRectF.bottom + textRectF.top - fontMetrics.bottom - fontMetrics.top) / 2);
        // 文字绘制到整个布局的中心位置
        canvas.drawText(textStr, textRectF.centerX(), baseline, textPaint);
    }

    private void setRoundRect() {
        rect2CircleAnimator = ValueAnimator.ofInt(0, radiusMax);
        rect2CircleAnimator.setDuration(DEFAULT_DURATION); // TODO=-->
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
