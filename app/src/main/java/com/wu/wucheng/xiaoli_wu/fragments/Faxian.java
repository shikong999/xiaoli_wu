package com.wu.wucheng.xiaoli_wu.fragments;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.wu.wucheng.xiaoli_wu.R;
import com.wu.wucheng.xiaoli_wu.adapters.FaxianAdapter;
import com.wu.wucheng.xiaoli_wu.faxian.Duanxinmoban;
import com.wu.wucheng.xiaoli_wu.faxian.Guanhuaihuati;
import com.wu.wucheng.xiaoli_wu.faxian.Qingitixing;
import com.wu.wucheng.xiaoli_wu.faxian.Qunfazhushou;
import com.wu.wucheng.xiaoli_wu.faxian.Saoyisao;
import com.wu.wucheng.xiaoli_wu.faxian.Wodekaquan;
import com.wu.wucheng.xiaoli_wu.faxian.Xiangce;
import com.wu.wucheng.xiaoli_wu.model.FaxianList;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Faxian extends Fragment implements AdapterView.OnItemClickListener {
    public static final String TAG = Faxian.class.getSimpleName();
    private ListView faxianList;
    private FaxianAdapter adapter;
    private Context context ;

    public Faxian() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context=getContext();
        View view = inflater.inflate(R.layout.fragment_faxian, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        List<FaxianList> data = new ArrayList<>();
        data.add(new FaxianList(R.mipmap.list_icon_xiangce, "相册"));
        data.add(new FaxianList(R.mipmap.list_icon_qinglitixing, "情礼提醒"));
        data.add(new FaxianList(R.mipmap.list_icon_duanxinmoban, "短信模板"));
        data.add(new FaxianList(R.mipmap.list_icon_saoyisao, "扫一扫"));
        data.add(new FaxianList(R.mipmap.list_icon_wodekaquan, "我的卡券"));
        data.add(new FaxianList(R.mipmap.list_icon_guanhuaihuati, "关怀话题"));
        data.add(new FaxianList(R.mipmap.list_icon_qunfazhushou, "群发助手"));
        adapter.addRes(data);
    }

    private void initView(View view) {
        faxianList = ((ListView) view.findViewById(R.id.faxian_list));
        adapter = new FaxianAdapter(context, R.layout.faxian_item, null);
        faxianList.setAdapter(adapter);
        faxianList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0://相册
                startActivity(new Intent(context, Xiangce.class));
                break;
            case 1://情景提醒
                startActivity(new Intent(context, Qingitixing.class));
                break;
            case 2://短信模板
                startActivity(new Intent(context, Duanxinmoban.class));
                break;
            case 3://扫一扫
                startActivity(new Intent(context, Saoyisao.class));
//                Intent intent = new Intent();
//                ComponentName cName=new ComponentName("com.zijunlin.Zxing.Demo","com.zijunlin.Zxing.Demo.CaptureActivity");
//                intent.setComponent(cName);
//                startActivity(intent);
                break;
            case 4://我的卡券
                startActivity(new Intent(context, Wodekaquan.class));
                break;
            case 5://关怀话题
                startActivity(new Intent(context, Guanhuaihuati.class));
                break;
            case 6://群发助手
                startActivity(new Intent(context, Qunfazhushou.class));
                break;
        }
    }
}
