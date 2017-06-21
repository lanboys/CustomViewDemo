package com.m520it.www.textflipper;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.ArrayList;

/**
 * Created by xmg on 2016/12/29.
 */

public class TextViewFlipper extends ViewFlipper {

    private int mFontColor;
    private float mFontSize;

    public TextViewFlipper(Context context) {
        super(context);
    }

    public TextViewFlipper(Context context, AttributeSet attrs) {
        super(context, attrs);
        //获得属性
//        int attributeCount = attrs.getAttributeCount();
//        for (int i = 0; i < attributeCount; i++) {
//            String attributeValue = attrs.getAttributeValue(i);
//            String attributeName = attrs.getAttributeName(i);
//            Log.d("xmg", "TextViewFlipper: attributeName " +attributeName
//            +" attributeValue "+attributeValue);
//        }
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TextViewFlipper);
        mFontColor = typedArray.getColor(R.styleable.TextViewFlipper_fontColor, Color.BLACK);
        mFontSize = typedArray.getDimension(R.styleable.TextViewFlipper_fontSize, 20);

        typedArray.recycle();
    }

    public void setData(ArrayList<String> list){
        int size = list.size();
        for (int i = 0; i < size; i++) {
            TextView textView = new TextView(getContext());
            textView.setTextColor(mFontColor);
            textView.setTextSize(mFontSize);
            textView.setText(list.get(i));
            addView(textView);
        }
    }
}
