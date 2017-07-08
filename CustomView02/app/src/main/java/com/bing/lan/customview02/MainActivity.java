package com.bing.lan.customview02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        TouchLogUtil.logTouch(event,"MainActivity中的onTouchEvent");
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        TouchLogUtil.logTouch(ev,"MainActivity中的dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }


}
