package com.bing.lan.customview01;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 520 on 2016/12/29.
 */

public class PaintView extends View {
    public PaintView(Context context) {
        super(context);
    }

    public PaintView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //画一个圆
    //该方法可让控件绘制一些效果出来
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();

        paint.setColor(Color.RED);
//        paint.setAlpha(10);
        paint.setAntiAlias(true);

        paint.setStrokeWidth(20);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(100,100,80,paint);

//        canvas.drawLine(100,100,200,100,paint);
    }
}
