package com.wu.wucheng.xiaoli_wu.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.wu.wucheng.xiaoli_wu.R;
import com.wu.wucheng.xiaoli_wu.model.duanxinmobanModel.DuanxinmobanData;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wucheng on 2016/3/23.
 */
public class DuanxinmobanAdapter extends BaseAdapter implements View.OnClickListener {
    private List<DuanxinmobanData.BlessBean> data;
    private LayoutInflater inflater;
    private Context context;

    public DuanxinmobanAdapter(Context context, List<DuanxinmobanData.BlessBean> data) {
        if (data==null){
            this.data=new ArrayList<>();
        }
        else {
        this.data = data;
        }
        this.context = context;
        inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    /**
     * 更新数据源(传递进来的数据源不为null,并且size>0)
     * @param data
     */
    public void updateRes(List<DuanxinmobanData.BlessBean> data){
        if (data!=null&&data.size()>0){
            this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    /**
     * 添加数据源(传递进来的数据源不为null,并且size>0)
     * @param data
     */
    public void addRes(List<DuanxinmobanData.BlessBean> data){
        if (data!=null&&data.size()>0) {
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }
    @Override
    public int getCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public DuanxinmobanData.BlessBean getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if (convertView==null){
            holder=new ViewHolder();
            convertView=inflater.inflate(R.layout.duanxinmoban_item,null);
            holder.duanxinmobanItemContent= (TextView) convertView.findViewById(R.id.duanxinmobanItemContent);
            holder.duanxinmobanItemSend= (Button) convertView.findViewById(R.id.duanxinmobanItemSend);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.duanxinmobanItemContent.setText(getItem(position).getContent());
        holder.duanxinmobanItemSend.setTag(position);
        holder.duanxinmobanItemSend.setOnClickListener(this);
        return convertView;
    }

    @Override
    public void onClick(View v) {
        int position= (int) v.getTag();
        switch (v.getId()) {
            case R.id.duanxinmobanItemSend:
                Toast.makeText(context,"点击--"+position,Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private class ViewHolder {
        TextView duanxinmobanItemContent;
        Button duanxinmobanItemSend;
    }
}
