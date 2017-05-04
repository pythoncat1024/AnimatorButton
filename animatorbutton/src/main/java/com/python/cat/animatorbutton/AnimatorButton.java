package com.python.cat.animatorbutton;

import android.content.Context;
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

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.width = w;
        this.height = h;
        LogUtils.e("width = " + width + " , height = " + height);
    }

    private void init() {

    }
}
