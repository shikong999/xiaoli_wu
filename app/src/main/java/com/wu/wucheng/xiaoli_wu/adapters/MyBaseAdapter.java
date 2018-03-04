package com.wu.wucheng.xiaoli_wu.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wucheng on 2016/3/21.
 */
public abstract class MyBaseAdapter<T>  extends BaseAdapter{
    private List<T> data;
    //布局导入器
    private LayoutInflater inflater;
    //布局ID
    private int layoutRes;

    public MyBaseAdapter(Context context, int layoutRes,List<T> data) {
        inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.layoutRes = layoutRes;
        if (data==null){
            this.data=new ArrayList<>();
        }else {
            this.data = data;
        }
    }

    /**
     * 更新数据源(传递进来的数据源不为null,并且size>0)
     * @param data
     */
    public void updateRes(List<T> data){
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
    public void addRes(List<T> data){
        if (data!=null&&data.size()>0) {
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }


    @Override
    public int getCount() {
        return data!=null?data.size():0;
    }

    @Override
    public T getItem(int position) {
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
            convertView=inflater.inflate(layoutRes,parent,false);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        //数据加载
        bindData(holder,getItem(position));
        return convertView;
    }

    public abstract void bindData(ViewHolder holder,T t);

    protected static class ViewHolder{
        //convertView的引用
        private View convertView;
        //定义一个Map用来缓存
        private Map<Integer,View> cacheView;
        // 通过构造将convertView传递进来
        public ViewHolder(View convertView) {
            this.convertView = convertView;
            cacheView=new HashMap<>();
        }
        //对外提供获取convertView中子View的方法
        public View getView(int resId) {

            View view=null;
            //如果Map中包含当前获取的资源ID
            if (cacheView.containsKey(resId)){
                //直接从map中根据Key获取所需view
                view= cacheView.get(resId);
            }else {
                //不包含的话,实例化id所对应的view,并添加到map缓存中
                view=convertView.findViewById(resId);
                cacheView.put(resId,view);
            }
            return view;
        }
    }
}
