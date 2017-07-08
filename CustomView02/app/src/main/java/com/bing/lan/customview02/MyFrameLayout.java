package com.bing.lan.customview02;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * Created by xmg on 2016/12/30.
 */

public class MyFrameLayout extends FrameLayout {
    public MyFrameLayout(Context context) {
        super(context);
    }

    public MyFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        TouchLogUtil.logTouch(ev,"MyFrameLayout中的onInterceptTouchEvent");
        Log.d("xmg", "onInterceptTouchEvent: " + super.onInterceptTouchEvent(ev));
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        TouchLogUtil.logTouch(event,"MyFrameLayout中的onTouchEvent");
        return super.onTouchEvent(event);
//        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        TouchLogUtil.logTouch(ev,"MyFrameLayout中的dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }
}
