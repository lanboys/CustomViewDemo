package com.bing.lan.clockview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Calendar;

/**
 * Created by xmg on 2016/12/30.
 */

public class ClockView extends View {

    private Paint mPaint;
    private Bitmap mBmpDial;
    private Bitmap mBmpHour;
    private Bitmap mBmpSec;
    private Bitmap mBmpMinute;
    private Bitmap mBmpCenter;
    private Calendar mCalendar;
    private Thread mThread;

    public ClockView(Context context) {
        this(context,null);
    }

    public ClockView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    //附加到window上,就开启线程
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        isAttach = true;
        mThread.start();
    }

    //不再附加时,就停掉线程
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        isAttach = false;
    }

    private boolean isAttach = false;
    private void init() {
        //画笔
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        //准备图片
        mBmpDial = BitmapFactory.decodeResource(getResources(), R.drawable.clock_dial);
        mBmpHour = BitmapFactory.decodeResource(getResources(), R.drawable.hour_hand);
        mBmpMinute = BitmapFactory.decodeResource(getResources(), R.drawable.minute_hand);
        mBmpSec = BitmapFactory.decodeResource(getResources(), R.drawable.sec_hand);
        mBmpCenter = BitmapFactory.decodeResource(getResources(), R.drawable.hand_center);

        //准备一个日历对象
        mCalendar = Calendar.getInstance();
        //Calendar.get(Calendar.HOUR_OF_DAY)} instead.

        //需要重新获得当前时间
//                    invalidate();
        mThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (isAttach){
                    SystemClock.sleep(1000);
                    //需要重新获得当前时间
                    mCalendar.setTimeInMillis(System.currentTimeMillis());

                    int hour = mCalendar.get(Calendar.HOUR_OF_DAY);
                    int minute = mCalendar.get(Calendar.MINUTE);
                    int second = mCalendar.get(Calendar.SECOND);

                    Log.d("xmg", "onDraw: hour " + hour
                            +" minute "+minute+" second "+second);
//                    invalidate();
                    postInvalidate();
                }
            }
        });

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        switch (widthMode) {
            case MeasureSpec.AT_MOST:
                widthSize = mBmpDial.getWidth();
                break;
        }
        switch (heightMode) {
            case MeasureSpec.AT_MOST:
                heightSize = mBmpDial.getHeight();
                break;
        }
        setMeasuredDimension(widthSize,heightSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        int hour = mCalendar.get(Calendar.HOUR_OF_DAY);
        int minute = mCalendar.get(Calendar.MINUTE);
        int second = mCalendar.get(Calendar.SECOND);

        super.onDraw(canvas);
        int height = getMeasuredHeight();
        int width = getMeasuredWidth();
        int dialWidth = mBmpDial.getWidth();
        int dialHeight = mBmpDial.getHeight();
        //判断是否需要缩放
        if(width< dialWidth ||height<dialHeight){
            //需要缩放
            float scaleW = (width*1f) / dialWidth;
            float scaleH = (height*1f) / dialHeight;
            //为了保证缩放比例不会导致拉伸变形,取最小的缩放比
            float minScale = Math.min(scaleW, scaleH);
            canvas.scale(minScale,minScale,width/2,height/2);
        }
        //绘制表盘
        canvas.drawBitmap(mBmpDial,width/2- dialWidth /2,
                height/2-dialHeight/2,mPaint);
        //绘制时针
        canvas.save();
        //旋转
        //  5/12 = 0    整数除整数,直接抛弃后面的小数部分了
        canvas.rotate((hour%12)/12f*360,width/2,height/2);
        canvas.drawBitmap(mBmpHour,width/2-mBmpHour.getWidth()/2,
                height/2-mBmpHour.getHeight()/2-30,mPaint);
        canvas.restore();

        //绘制分针
        canvas.save();
        canvas.rotate(minute/60f*360,width/2,height/2);
        canvas.drawBitmap(mBmpMinute,width/2-mBmpMinute.getWidth()/2,
                height/2-mBmpMinute.getHeight()/2-30,mPaint);
        canvas.restore();
        //绘制秒针
        canvas.save();
        canvas.rotate(second/60f*360,width/2,height/2);
        canvas.drawBitmap(mBmpSec,width/2-mBmpSec.getWidth()/2,
                height/2-mBmpSec.getHeight()/2-30,mPaint);
        canvas.restore();
        //绘制中间点
        canvas.drawBitmap(mBmpCenter,width/2-mBmpCenter.getWidth()/2,
                height/2-mBmpCenter.getHeight()/2,mPaint);
    }
}
