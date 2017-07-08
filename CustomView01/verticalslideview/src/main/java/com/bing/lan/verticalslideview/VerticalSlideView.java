package com.bing.lan.verticalslideview;

import android.content.Context;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by 520 on 2016/12/29.
 */

public class VerticalSlideView extends ViewGroup {

    private ImageView mImageView;
    private int mChildWidth;
    private int mChildHeight;
    private float mStartX;//起点的坐标X
    private float mStartY;//起点的坐标Y

    public VerticalSlideView(Context context) {
        super(context);
    }

    public VerticalSlideView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //初始化,往里面添加一个ImageView
        mImageView = new ImageView(getContext());
        mImageView.setImageResource(R.drawable.tabicon);
        addView(mImageView);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        measureChildren(widthMeasureSpec, heightMeasureSpec);
        mChildWidth = mImageView.getMeasuredWidth();
        mChildHeight = mImageView.getMeasuredHeight();
        switch (widthMode) {
            case MeasureSpec.AT_MOST:
                widthSize = mChildWidth;
                break;
        }
        setMeasuredDimension(widthSize, heightSize);
    }


    int mSumDY = 0;//y轴上面的偏移量之和
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //判断触摸的事件类型
        //1 获得类型
        int action = event.getAction();
        //2 判断
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mStartX = event.getX();
                mStartY = event.getY();
                                Log.d("520", "onTouchEvent: startX" + mStartX
                +" startY:"+ mStartY);
                //先判断一下手指是否落在滑块区域,没有就直接return false,不做后续逻辑了
                RectF rectF = new RectF(0, mSumDY, mChildWidth, mSumDY+mChildHeight);
                Log.d("520", "onTouchEvent: left" + 0
                        +" right:"+ mChildWidth
                +" top: "+mSumDY
                +" bottom: "+(mSumDY+mChildHeight));
                boolean contains = rectF.contains(mStartX, mStartY);
                if(!contains){
                    return false;
                }
                //2.1 按下    图片改变展示  获得起点坐标
                mImageView.setImageResource(R.drawable.tabicon_p);

                break;
            case MotionEvent.ACTION_MOVE:
                float stopX = event.getX();
                float stopY = event.getY();
                //2.2 移动    动起来(位移的距离  起点坐标,终点坐标)
                //把起点和终点的位移量计算出来
                float dx = stopX - mStartX;
                float dy = stopY - mStartY;
                mSumDY+=dy;
                Log.d("520", "onTouchEvent: mSumDY" +mSumDY);
                //因为触摸移动会反复的走进来,相当于绘制多段线条,起点和终点不断会变化
                //startX 作为起点的话,需要将先前那一段的终点的值赋给startX作为新起点
                mStartX = stopX;
                mStartY = stopY;

                //让控件重新去布局
                requestLayout();
                break;
            case MotionEvent.ACTION_UP:
                //2.3 抬起    图片改变展示
                mImageView.setImageResource(R.drawable.tabicon);
                break;
        }
        boolean b = super.onTouchEvent(event);
//        Log.d("520", "onTouchEvent: super " + b);
        return true;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //下边界
        int bottom = getMeasuredHeight() - mChildHeight;

        //给偏移量设置边界
        if(mSumDY<0){
            mSumDY = 0;
        }else if(mSumDY>bottom){
            mSumDY = bottom;
        }
        mImageView.layout(0, mSumDY, mChildWidth, mSumDY+mChildHeight);
    }
}
