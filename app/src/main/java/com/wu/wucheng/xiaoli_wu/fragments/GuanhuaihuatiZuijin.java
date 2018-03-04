package com.wu.wucheng.xiaoli_wu.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wu.wucheng.xiaoli_wu.R;
import com.wu.wucheng.xiaoli_wu.adapters.GuanhuaihuatiZuijinAdapter;
import com.wu.wucheng.xiaoli_wu.faxian.GuanhuaihuatiDetail;
import com.wu.wucheng.xiaoli_wu.model.GuanhuaihuatiModel;
import com.wu.wucheng.xiaoli_wu.tools.HttpUtils;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GuanhuaihuatiZuijin extends Fragment implements Handler.Callback, AdapterView.OnItemClickListener {


    private ListView guanhuaihuati_zuijin_list;
    private Context context;
    private String topicURL = "http://www.1000phone.net:8088/qfxl/index.php?s=appapi&a=topic";
    private SharedPreferences sharedPreferences;
    private Handler handler;
    private GuanhuaihuatiZuijinAdapter adapter;
    private List<GuanhuaihuatiModel.TopiclistBean> data;

    public GuanhuaihuatiZuijin() {
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
        View view = inflater.inflate(R.layout.fragment_guanhuaihuati_zuijin, container, false);
        initView(view);
        initData();
        return view;

    }

    private void initData() {
        String userid = sharedPreferences.getString("userid", "1");
        final String topicParameter = "uid=" + userid;
        new Thread() {
            @Override
            public void run() {
                String json = HttpUtils.getStringByConnection(topicURL, topicParameter);
                Message msg=Message.obtain();
                msg.what=7;
                msg.obj=json;
                handler.sendMessage(msg);
            }
        }.start();
    }

    private void initView(View view) {
        String dir = sharedPreferences.getString("dir", "");
        adapter = new GuanhuaihuatiZuijinAdapter(context, R.layout.guanhuaihuati_item, null,dir);
        guanhuaihuati_zuijin_list = (ListView) view.findViewById(R.id.guanhuaihuati_zuijin_list);
        guanhuaihuati_zuijin_list.setAdapter(adapter);
        guanhuaihuati_zuijin_list.setOnItemClickListener(this);
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case 7:
                String json= (String) msg.obj;
                Gson gson=new Gson();
                GuanhuaihuatiModel guanhuaihuatiModel = gson.fromJson(json, GuanhuaihuatiModel.class);
                data = guanhuaihuatiModel.getTopiclist();
                adapter.addRes(data);
                break;
        }
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(context, GuanhuaihuatiDetail.class);
        String conid = data.get(position).getId();
        intent.putExtra("conid",conid);
//        Toast.makeText(context, conid,Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }
}
