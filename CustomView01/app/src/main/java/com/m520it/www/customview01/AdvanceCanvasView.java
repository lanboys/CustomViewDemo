package com.m520it.www.customview01;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by xmg on 2016/12/29.
 */

public class AdvanceCanvasView extends View {
    public AdvanceCanvasView(Context context) {
        super(context);
    }

    public AdvanceCanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        //保存
        canvas.save();
        canvas.rotate(45, 100, 100);
        canvas.drawLine(100, 100, 200, 200, paint);
        //旋转
        //读档
        canvas.restore();

        paint.setColor(Color.RED);
        canvas.drawLine(100, 100, 200, 200, paint);
        //旋转
        //画一个效果
        //移动
//        canvas.translate(-100,-100);
//        canvas.scale(2,2,200,200);
//        canvas.drawCircle(200,200,100,paint);
    }
}
