package com.bing.lan.measureview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by xmg on 2016/12/29.
 */

public class MyView extends View {
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //测量方法
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d("xmg", "onMeasure: widthMeasureSpec " +widthMeasureSpec
        +" heightMeasureSpec: "+heightMeasureSpec);

        //mode   size
        int modeW = MeasureSpec.getMode(widthMeasureSpec);
        int sizeW = MeasureSpec.getSize(widthMeasureSpec);
        int modeH = MeasureSpec.getMode(heightMeasureSpec);
        int sizeH = MeasureSpec.getSize(heightMeasureSpec);

        //UNSPECIFIED 0        子控件的尺寸不受任何限制
        //AT_MOST   大的负数    wrap_content    尺寸会有一个最大的上限
        //EXACTLY   大的正数    200dp   或者父控件大小能确定时,使用了MatchParent
        Log.d("xmg", "onMeasure: modeW " +modeW
                +" sizeW: "+sizeW+" modeH:"+modeH+" sizeH:"+sizeH);

        //手动指定控件大小
//        setMeasuredDimension(50,50);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLUE);
    }
}
