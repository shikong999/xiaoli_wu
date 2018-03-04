package com.wu.wucheng.xiaoli_wu.model.xiangceModel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Wucheng on 2016/3/21.
 */
public class Fengmian implements Serializable{
    private String fengmian;
    private List<String> imgurl;
    private String datettime;

    public List<String> getImgurl() {
        return imgurl;
    }

    public void setImgurl(List<String> imgurl) {
        this.imgurl = imgurl;
    }

    public String getFengmian() {
        return fengmian;
    }

    public void setFengmian(String fengmian) {
        this.fengmian = fengmian;
    }


    public String getDatettime() {
        return datettime;
    }

    public void setDatettime(String datettime) {
        this.datettime = datettime;
    }
}
