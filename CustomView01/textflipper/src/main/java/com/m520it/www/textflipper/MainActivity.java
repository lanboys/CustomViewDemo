package com.m520it.www.textflipper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextViewFlipper mViewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
//        设置一些控件进来,方便进行翻转
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            strings.add("这是第"+i+"朵发");
//            TextView textView = new TextView(getApplicationContext());
//            textView.setText("这是第"+i+"朵发");
//            textView.setTextSize(25);
//            textView.setTextColor(Color.RED);
//            mViewFlipper.addView(textView);
        }
        //setData
        mViewFlipper.setData(strings);
        //开始翻转
        mViewFlipper.startFlipping();
    }

    private void initView() {
        mViewFlipper = (TextViewFlipper) findViewById(R.id.viewFlipper);
        mViewFlipper.setFlipInterval(1500);

        //设置入场和离场动画
        //入场
        AnimationSet animSetIn = new AnimationSet(false);
        TranslateAnimation transAnimIn= new TranslateAnimation(TranslateAnimation.RELATIVE_TO_SELF,0,
                TranslateAnimation.RELATIVE_TO_SELF,0,
                TranslateAnimation.RELATIVE_TO_SELF,1,
                TranslateAnimation.RELATIVE_TO_SELF,0);
        AlphaAnimation alphaAnimIn = new AlphaAnimation(0, 1);
        animSetIn.addAnimation(transAnimIn);
        animSetIn.addAnimation(alphaAnimIn);
        animSetIn.setDuration(500);
        //离场
        Animation animOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_out);

        //设置给控件
        mViewFlipper.setInAnimation(animSetIn);
        mViewFlipper.setOutAnimation(animOut);
    }
}
