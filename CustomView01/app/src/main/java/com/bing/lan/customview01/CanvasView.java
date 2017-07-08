package com.bing.lan.customview01;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 520 on 2016/12/29.
 */

public class CanvasView extends View {

    public CanvasView(Context context) {
        super(context);
    }

    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
//        canvas.drawRect(100,100,300,200,paint);
//        paint.setTextSize(24);
//        canvas.drawText("我是一朵发",100,100,paint);
//        RectF rectF = new RectF(100, 300, 300, 500);
//        canvas.drawOval(rectF,paint);
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
        Bitmap bitmap = getBitmap();
        canvas.drawBitmap(bitmap,0,0,paint);
    }

    //创建一个新的Bitmap出来
    private Bitmap getBitmap() {
        //ALPHA_8   只有透明度
        //RGB_565   展示红绿蓝三原色
        //ARGB_4444
        //ARGB_8888
        Bitmap bitmap = Bitmap.createBitmap(300, 300, Bitmap.Config.ARGB_8888);
        //展示其他的效果出来
        //第一种方式
//        for (int i = 0; i < 300; i++) {
//            for (int j = 0; j < 300; j++) {
//                bitmap.setPixel(i,j, Color.RED);
//            }
//        }
        //第二种   用一个Canvas画布来进行修改
        Canvas canvas =  new Canvas(bitmap);
        RectF rectF = new RectF(20, 20, 120, 200);
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
//        canvas.drawOval(rectF,paint);
        canvas.drawRect(rectF,paint);

//        RectF rectF = new RectF(20, 20, 120, 200);
        paint.setColor(Color.RED);

        //设置画笔的叠加模式
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));

        canvas.drawRect(60,60,250,250,paint);
        return bitmap;
    }
}
