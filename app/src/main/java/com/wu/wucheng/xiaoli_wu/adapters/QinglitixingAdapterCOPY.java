package com.wu.wucheng.xiaoli_wu.adapters;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.wu.wucheng.xiaoli_wu.R;
import com.wu.wucheng.xiaoli_wu.model.qinglitixingModel.RemindItem;
import com.wu.wucheng.xiaoli_wu.tools.HttpUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wucheng on 2016/3/22.
 */
public class QinglitixingAdapterCOPY extends BaseAdapter implements View.OnClickListener {
    private List<RemindItem> data;
    private static final String tag = QinglitixingAdapterCOPY.class.getSimpleName();
    //布局导入器
    private LayoutInflater inflater;
    private Context context;
    //    private OnItemHandleListener listener;
    private String uid;
    private Handler handler;


//    public void setOnItemHandleListener(OnItemHandleListener listener) {
//        this.listener = listener;
//    }

    public QinglitixingAdapterCOPY(Context context, List<RemindItem> data, String uid, Handler handler) {
        this.data = data;
        this.context = context;
        this.uid = uid;
        this.handler = handler;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (data == null) {
            this.data = new ArrayList<>();
        } else {
            this.data = data;
        }
    }

    /**
     * 更新数据源(传递进来的数据源不为null,并且size>0)
     *
     * @param data
     */
    public void updateRes(List<RemindItem> data) {
        if (data != null && data.size() > 0) {
            this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    /**
     * 添加数据源(传递进来的数据源不为null,并且size>0)
     *
     * @param data
     */
    public void addRes(List<RemindItem> data) {
        if (data != null && data.size() > 0) {
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public RemindItem getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.qinglitixing_item, parent, false);
            holder.qinglitixingListTitle = ((TextView) convertView.findViewById(R.id.qinglitixinf_list_title));
            holder.qinglitixingListTime = (TextView) convertView.findViewById(R.id.qinglitixinf_list_time);
            holder.qinglitixingListDelete = (Button) convertView.findViewById(R.id.qinglitixing_list_delete);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //设置标题
        holder.qinglitixingListTitle.setText(getItem(position).getTitle());
        //设置剩余时间
        long starttime = Long.parseLong(getItem(position).getStarttime());
//        Calendar calendar = Calendar.getInstance();
//        long currectTimeInMillis = calendar.getTimeInMillis();
//        long lastTime =   currectTimeInMillis-starttime;
        int time = (int) (starttime / (24 * 60 * 60 * 1000));
        holder.qinglitixingListTime.setText(time+"天");
        //删除监听
        holder.qinglitixingListDelete.setTag(position);
        holder.qinglitixingListDelete.setOnClickListener(this);
        return convertView;
    }

    @Override
    public void onClick(View v) {
        final int position = (Integer) v.getTag();
        Log.e(tag, "" + position);
        switch (v.getId()) {
            case R.id.qinglitixing_list_delete:
//                listener.qinglitixingDelete(position, getItem(position));
                final String deleteUrl = "http://www.1000phone.net:8088/qfxl/index.php?s=appapi&a=delremind";
                final String deleteParameter = "uid=" + uid + "&rid=" + getItem(position).getId();
                new Thread() {
                    @Override
                    public void run() {
                        String json = HttpUtils.getStringByConnection(deleteUrl, deleteParameter);
                        if (json.contains("删除成功")) {
                            handler.sendEmptyMessage(4);
                        } else {
                            Toast.makeText(context, "删除失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                }.start();
                break;
        }
    }

//    public interface OnItemHandleListener {
//        void qinglitixingDelete(int position, RemindItem remindItem);
//    }

    private static class ViewHolder {
        TextView qinglitixingListTitle;
        TextView qinglitixingListTime;
        Button qinglitixingListDelete;
    }

//时间格式化
//    public String getStandardTime(long timestamp) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
//        Date date = new Date(timestamp);
//        return sdf.format(date);
//    }
}
