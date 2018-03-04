package com.wu.wucheng.xiaoli_wu.wode;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.wu.wucheng.xiaoli_wu.R;

public class Qinglijilu extends AppCompatActivity implements View.OnClickListener {

    private ImageView wode_qinglijilu_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qinglijilu);
        initView();
    }

    private void initView() {
        wode_qinglijilu_back = (ImageView) findViewById(R.id.wode_qinglijilu_back);
        wode_qinglijilu_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.wode_qinglijilu_back:
                finish();
                break;
        }
    }
}
