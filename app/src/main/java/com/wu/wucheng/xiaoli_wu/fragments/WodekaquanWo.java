package com.wu.wucheng.xiaoli_wu.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.wu.wucheng.xiaoli_wu.R;
import com.wu.wucheng.xiaoli_wu.tools.HttpUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class WodekaquanWo extends Fragment implements View.OnClickListener {


    private TextView wodekaquan_rule;

    public WodekaquanWo() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wodekaquan_wo, container, false);
        initView(view);
        return view;
    }


    private void initView(View view) {
        wodekaquan_rule = (TextView) view.findViewById(R.id.wodekaquan_rule);
        wodekaquan_rule.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.wodekaquan_rule:
                Toast.makeText(getContext(),"\t1、晓礼所有卡券均为和商家合作,最终解释权归各商家所有.\n\t" +
                        "2、晓礼所有卡券均可以转赠,赠送成功的卡券将在过往卡券中出现.\n\t" +
                        "3、若受赠方拒绝接收卡券,则卡券会返还给赠送人.\n\t" +
                        "4、含有消费码的卡券请妥善保存,如被使用,则卡券失效\n\t" +
                        "5、需要绑定其他信息的卡券请按照商家说明进行操作.\n\t" +
                        "6、晓礼为卡券平台,卡券方面如有任何问题请联系卡券发行商.",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
