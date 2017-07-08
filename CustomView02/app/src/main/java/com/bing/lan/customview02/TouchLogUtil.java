package com.bing.lan.customview02;

import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by xmg on 2016/12/30.
 */

public class TouchLogUtil {

    public static void logTouch(MotionEvent ev,String where){
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.d("xmg", where+": 按下");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("xmg", where+": 移动");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("xmg", where+": 抬起");
                break;
        }
    }
}
