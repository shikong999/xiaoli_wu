package com.wu.wucheng.xiaoli_wu.faxian;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.wu.wucheng.xiaoli_wu.R;


public class Saoyisao extends Activity {
    /**
     * 显示扫描结果
     */
    private TextView mTextView ;
    /**
     * 显示扫描拍的图片
     */
    private ImageView mImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saoyisao);
        mTextView = (TextView) findViewById(R.id.result);

        //点击按钮跳转到二维码扫描界面，这里用的是startActivityForResult跳转
        //扫描完了之后调到该界面
        Button mButton = (Button) findViewById(R.id.button1);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                ComponentName cName=new ComponentName("com.zijunlin.Zxing.Demo","com.zijunlin.Zxing.Demo.CaptureActivity");
                intent.setComponent(cName);
                startActivity(intent);
            }
        });
    }
}
