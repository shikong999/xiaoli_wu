package com.wu.wucheng.xiaoli_wu.adapters;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.wu.wucheng.xiaoli_wu.R;
import com.wu.wucheng.xiaoli_wu.model.FaxianList;

import java.util.List;

/**
 * 发现适配器
 */
public class FaxianAdapter extends MyBaseAdapter<FaxianList> {

    private ImageView faxianImg;
    private TextView faxianTitle;

    public FaxianAdapter(Context context, int layoutRes, List<FaxianList> data) {
        super(context, layoutRes, data);
    }

    @Override
    public void bindData(ViewHolder holder, FaxianList faxianList) {
        faxianImg = ((ImageView) holder.getView(R.id.faxian_img));
        faxianImg.setImageResource(faxianList.getFaxianImgSrc());
        faxianTitle = ((TextView) holder.getView(R.id.faxian_title));
        faxianTitle.setText(faxianList.getFaxianTitle());
    }
}
