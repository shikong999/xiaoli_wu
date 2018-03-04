package com.wu.wucheng.xiaoli_wu.faxian;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.wu.wucheng.xiaoli_wu.R;

public class Qunfazhushou extends AppCompatActivity implements View.OnClickListener {

    private ImageView faxian_qundazhushou_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qunfazhushou);
        initView();
    }

    private void initView() {
        faxian_qundazhushou_back = (ImageView) findViewById(R.id.faxian_qundazhushou_back);
        faxian_qundazhushou_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.faxian_qundazhushou_back:
                finish();
                break;
        }
    }
}
