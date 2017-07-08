package com.bing.lan.customview01;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 520 on 2016/12/29.
 */

public class PathView extends View {
    public PathView(Context context) {
        super(context);
    }

    public PathView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //曲线
        // 1 圆弧
        RectF rectF = new RectF(100,100,300,300);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
//        canvas.drawArc(rectF,0,90,true,paint);

        //通过path来绘制曲线
        Path path = new Path();
        //设置path的起始点
        path.moveTo(100,0);
        //直线
        path.lineTo(100,100);
        path.lineTo(150,100);
        //绘制简单的曲线   贝塞尔曲线
//        path.quadTo(300,350,400,100);
        //三阶
        path.rCubicTo(300,350,200,600,400,100);

        Paint paint1 = new Paint();
        paint1.setStrokeWidth(5);
        paint1.setColor(Color.RED);

        canvas.drawPoint(150,100,paint1);
        canvas.drawPoint(300,350,paint1);
        canvas.drawPoint(200,600,paint1);
        canvas.drawPoint(400,100,paint1);

        canvas.drawPath(path,paint);
    }
}
