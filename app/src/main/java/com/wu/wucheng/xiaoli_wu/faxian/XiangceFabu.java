package com.wu.wucheng.xiaoli_wu.faxian;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wu.wucheng.xiaoli_wu.R;

public class XiangceFabu extends AppCompatActivity implements View.OnClickListener {

    private ImageView xiangce_fabu_back;
    private TextView xiangce_fabu_fasong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiangce_fabu);
        initView();
    }

    private void initView() {
        xiangce_fabu_back = (ImageView) findViewById(R.id.xiangce_fabu_back);
        xiangce_fabu_back.setOnClickListener(this);
        xiangce_fabu_fasong = (TextView) findViewById(R.id.xiangce_fabu_fasong);
        xiangce_fabu_fasong.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.xiangce_fabu_back:
                finish();
                break;
            case R.id.xiangce_fabu_fasong:
                Toast.makeText(this,"确认发布照片",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
