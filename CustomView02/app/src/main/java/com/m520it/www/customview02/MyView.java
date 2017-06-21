package com.m520it.www.customview02;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by xmg on 2016/12/30.
 */

public class MyView extends View {

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        TouchLogUtil.logTouch(event,"MyView中的onTouchEvent");
        return super.onTouchEvent(event);
//        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        TouchLogUtil.logTouch(ev,"MyView中的dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

}
