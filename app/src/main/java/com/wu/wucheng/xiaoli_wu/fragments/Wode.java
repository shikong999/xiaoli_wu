package com.wu.wucheng.xiaoli_wu.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.wu.wucheng.xiaoli_wu.R;
import com.wu.wucheng.xiaoli_wu.adapters.FaxianAdapter;
import com.wu.wucheng.xiaoli_wu.faxian.Duanxinmoban;
import com.wu.wucheng.xiaoli_wu.faxian.Guanhuaihuati;
import com.wu.wucheng.xiaoli_wu.faxian.Qingitixing;
import com.wu.wucheng.xiaoli_wu.faxian.Saoyisao;
import com.wu.wucheng.xiaoli_wu.faxian.Wodekaquan;
import com.wu.wucheng.xiaoli_wu.faxian.Xiangce;
import com.wu.wucheng.xiaoli_wu.model.FaxianList;
import com.wu.wucheng.xiaoli_wu.wode.Defenjilu;
import com.wu.wucheng.xiaoli_wu.wode.Qinglijilu;
import com.wu.wucheng.xiaoli_wu.wode.WodeInfo;
import com.wu.wucheng.xiaoli_wu.wode.WodeShoucang;
import com.wu.wucheng.xiaoli_wu.wode.Wodegushi;
import com.wu.wucheng.xiaoli_wu.wode.Wodeshezhi;
import com.wu.wucheng.xiaoli_wu.wode.Yinxiangjilu;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Wode extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {
    public static final String TAG = Wode.class.getSimpleName();
    private ListView wode_list;
    private FaxianAdapter adapter;
    private Context context;
    private LinearLayout wode_userInfo;

    public Wode() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wode, container, false);
        context = getContext();
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        List<FaxianList> data = new ArrayList<>();
        data.add(new FaxianList(R.mipmap.list_icon_dafenjilu, "得分记录"));
        data.add(new FaxianList(R.mipmap.list_icon_yinxiangjilu, "印象记录"));
        data.add(new FaxianList(R.mipmap.list_icon_wodegushi, "我的故事"));
        data.add(new FaxianList(R.mipmap.list_icon_shoucang, "我的收藏"));
        data.add(new FaxianList(R.mipmap.list_icon_caogaoxiang, "情礼记录"));
        data.add(new FaxianList(R.mipmap.list_icon_shezhi, "设置"));
        adapter.addRes(data);
    }

    private void initView(View view) {
        wode_list = (ListView) view.findViewById(R.id.wode_list);
        adapter = new FaxianAdapter(context, R.layout.wode_item, null);
        wode_list.setAdapter(adapter);
        wode_list.setOnItemClickListener(this);
        wode_userInfo = (LinearLayout) view.findViewById(R.id.wode_userInfo);
        wode_userInfo.setOnClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0://得分记录
                startActivity(new Intent(context,Defenjilu.class));
                break;
            case 1://印象记录
                startActivity(new Intent(context, Yinxiangjilu.class));
                break;
            case 2://我的故事
                startActivity(new Intent(context, Wodegushi.class));
                break;
            case 3://我的收藏
                startActivity(new Intent(context, WodeShoucang.class));
                break;
            case 4://情礼记录
                startActivity(new Intent(context, Qinglijilu.class));
                break;
            case 5://设置
                startActivity(new Intent(context, Wodeshezhi.class));
                break;
        }
    }

    //我的信息
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.wode_userInfo:
                startActivity(new Intent(context, WodeInfo.class));
                break;
        }
    }
}
