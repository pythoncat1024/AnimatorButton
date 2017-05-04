package com.python.cat.animatebutton.base;

import android.app.Application;

import com.apkfuns.logutils.LogUtils;

/**
 * packageName: com.python.cat.animatebutton.base
 * Created on 2017/5/4.
 *
 * @author cat
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.getLogConfig().configShowBorders(false);
    }
}
