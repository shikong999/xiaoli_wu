package com.wu.wucheng.xiaoli_wu.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.wu.wucheng.xiaoli_wu.R;
import com.wu.wucheng.xiaoli_wu.adapters.DuanxinmobanAdapter;
import com.wu.wucheng.xiaoli_wu.model.duanxinmobanModel.DuanxinmobanData;
import com.wu.wucheng.xiaoli_wu.tools.HttpUtils;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DuanxinmobanOne extends Fragment implements Handler.Callback {

private static final String tag=DuanxinmobanOne.class.getSimpleName();
    private ListView duanxinmoban_list;
    private Context context;
    private SharedPreferences sharedPreferences;
    private Handler handler;
    private DuanxinmobanAdapter adapter;
    private String blessUrl = "http://www.1000phone.net:8088/qfxl/index.php?s=appapi&a=bless";//短信模板Url
    private List<DuanxinmobanData.BlessBean> data;

    public DuanxinmobanOne() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        sharedPreferences = context.getSharedPreferences("user", Context.MODE_APPEND);
        handler = new Handler(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_duanxinmoban_one, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        String userid = sharedPreferences.getString("userid", "1");
        final String blessParameter = "uid=" + userid;
        new Thread() {

            @Override
            public void run() {
                String json = HttpUtils.getStringByConnection(blessUrl, blessParameter);
                Log.e(tag, json);
                Message msg = Message.obtain();
                msg.what = 6;
                msg.obj = json;
                handler.sendMessage(msg);
            }
        }.start();
    }
    private void initView(View view) {
        duanxinmoban_list = (ListView) view.findViewById(R.id.duanxinmoban_list);
        //设置适配器
        adapter = new DuanxinmobanAdapter(context, null);
        duanxinmoban_list.setAdapter(adapter);
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case 6:
                String json = (String) msg.obj;
                Gson gson = new Gson();
                DuanxinmobanData duanxinmobanData = gson.fromJson(json, DuanxinmobanData.class);
                data = duanxinmobanData.getBless();
                adapter.addRes(data);
                break;
        }
        return true;
    }

}
