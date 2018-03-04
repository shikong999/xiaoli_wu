package com.wu.wucheng.xiaoli_wu.adapters;

import android.content.Context;
import android.widget.TextView;

import com.wu.wucheng.xiaoli_wu.R;
import com.wu.wucheng.xiaoli_wu.model.qinglitixingModel.RemindItem;

import java.util.List;

/**
 * Created by Wucheng on 2016/3/22.
 */
public class QinglitixingAdapter extends MyBaseAdapter<RemindItem> {
    private TextView qinglitixingListTitle;
    private TextView qinglitixingListTime;


    public QinglitixingAdapter(Context context, int layoutRes, List<RemindItem> data) {
        super(context, layoutRes, data);
    }

    @Override
    public void bindData(ViewHolder holder, RemindItem remindItem) {
        qinglitixingListTitle = ((TextView) holder.getView(R.id.qinglitixinf_list_title));
        qinglitixingListTitle.setText(remindItem.getTitle());
        qinglitixingListTime = ((TextView) holder.getView(R.id.qinglitixinf_list_time));
        qinglitixingListTime.setText(remindItem.getTitle());
    }
}
