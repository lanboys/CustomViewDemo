package com.bing.lan.slidebutton;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.textView);
        SlideButton slideButton = (SlideButton) findViewById(R.id.slideButton);

        mTextView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //打吐司
            }
        });

        slideButton.setOnChangeListener(new SlideButton.OnCloseOrOpenChangeListener() {
            @Override
            public void onOpenOrClose(boolean isClose) {
                //为true时,当前状态就为关闭状态
                //为flase时,当前状态就为开启
                if(isClose){
                    //解耦  耦合性
                    mTextView.setText("自动更新已关闭");
                }else{
                    mTextView.setText("自动更新已开启");
                }
            }
        });
//        mTextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

    }

    //创建一个方法,接收一个boolean值,根据boolean值来改文本
    private void setText(boolean isClose){

    }
}
