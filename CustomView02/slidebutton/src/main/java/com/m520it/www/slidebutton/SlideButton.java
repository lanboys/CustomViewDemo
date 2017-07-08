package com.bing.lan.slidebutton;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by xmg on 2016/12/30.
 */

public class SlideButton extends View {

    private Bitmap mBmpBg;
    private Bitmap mBmpBtn;
    private Paint mPaint;
    private float mStartX;
    private float mStartY;
    private int mDiffWidth;

    public SlideButton(Context context) {
        super(context);
    }

    public SlideButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mBmpBg = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        mBmpBtn = BitmapFactory.decodeResource(getResources(), R.drawable.slide_button);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mDiffWidth = mBmpBg.getWidth() - mBmpBtn.getWidth();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //如果是AT_MOST,就让控件大小为背景图bitmap的大小
//        Toast.makeText(getContext(),"aa",Toast.LENGTH_SHORT).show();
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        Log.d("xmg", "onMeasure: mBmpBg.getWidth() " + mBmpBg.getWidth()
                + " mBmpBg.getHeight() " + mBmpBg.getHeight());
        switch (widthMode) {
            case MeasureSpec.AT_MOST:
                widthSize = mBmpBg.getWidth();
                break;
        }
        switch (heightMode) {
            case MeasureSpec.AT_MOST:
                heightSize = mBmpBg.getHeight();
                break;
        }
        setMeasuredDimension(widthSize, heightSize);
    }

    int mSumDx = 0;//总的偏移量
    boolean mIsClose = true;//默认就是关闭状态,为true


//    public 提供一个TextView的方法,让你调用
    //开启服务
    //提供一个开启服务的方法
    //灵活性特别差

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                //记录起点
                mStartX = event.getX();
                mStartY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                //获得终点
                float stopX = event.getX();
                float stopY = event.getY();
                //两个点相减,得到偏移量
                float dx = stopX - mStartX;
                mSumDx+=dx;
                float dy = stopY - mStartY;
                //终点赋值给起点,做下一次偏移量计算的准备
                mStartX = stopX;
                mStartY = stopY;
                break;
            case MotionEvent.ACTION_UP:
                //手指抬起时,判断一下滑块是偏向左边还是右边
                //如果偏移量超过mDiffWidth的一半,偏右边,否则左边
                if(mSumDx>mDiffWidth/2){

                    //按钮处于开启状态
                    mSumDx = mDiffWidth;
                    //判断一下,如果先前的状态是关闭状态,就认为发生变化了
                    if(mIsClose){
                        //发生变化了
//                        Toast.makeText(getContext(),"按钮发生变化,现在为开启状态",Toast.LENGTH_SHORT).show();
                        if(mChangeListener!=null){
                            mChangeListener.onOpenOrClose(false);
                        }
                        mIsClose = false;
                    }
                }else{
                    //按钮处于关闭状态
                    mSumDx = 0;
                    //判断一下,如果先前的状态是开启状态,就认为发生变化了
                    if(!mIsClose){
                        //发生变化了
                        if(mChangeListener!=null){
                            mChangeListener.onOpenOrClose(true);
                        }
//                        Toast.makeText(getContext(),"按钮发生变化,现在为关闭状态",Toast.LENGTH_SHORT).show();
                        mIsClose = true;
                    }
                }
                break;
        }
        //重新绘制
        invalidate();

        return true;
    }


    private OnCloseOrOpenChangeListener mChangeListener = null;

    public void setOnChangeListener(OnCloseOrOpenChangeListener changeListener){
        this.mChangeListener = changeListener;
    }

    //创建一个方法
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //边界

        if(mSumDx<0){
            mSumDx = 0;
        }else if(mSumDx>mDiffWidth){
            mSumDx = mDiffWidth;
        }
        canvas.drawBitmap(mBmpBg, 0, 0, mPaint);
        canvas.drawBitmap(mBmpBtn, mSumDx, 0, mPaint);
    }

    public interface OnCloseOrOpenChangeListener{
        /**
         *
         * @param isClose   当前状态是否为关闭
         */
       void onOpenOrClose(boolean isClose);
    }
}
