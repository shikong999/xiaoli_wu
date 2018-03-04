package com.wu.wucheng.xiaoli_wu.faxian;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

import com.wu.wucheng.xiaoli_wu.R;
import com.wu.wucheng.xiaoli_wu.adapters.XiangceDetailAdapter;
import com.wu.wucheng.xiaoli_wu.model.xiangceModel.Fengmian;

import java.util.List;

public class XiangceDetail extends AppCompatActivity {

    private TextView xiangceDetailTitle;
    private GridView xiangceDetailGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiangce_detail);
        initView();
    }

    private void initView() {
        Fengmian fengmian= (Fengmian) getIntent().getSerializableExtra("fengmian");
        List<String> imgurl = fengmian.getImgurl();
        String dir = getSharedPreferences("user", MODE_PRIVATE).getString("dir", "");
        XiangceDetailAdapter adapter=new XiangceDetailAdapter(this,R.layout.xiangce_detail_item,imgurl,dir);
        xiangceDetailGridView = ((GridView) findViewById(R.id.xiangce_detail_gridlist));
        xiangceDetailGridView.setAdapter(adapter);
    }
}
