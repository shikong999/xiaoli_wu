package com.wu.wucheng.xiaoli_wu.model;

/**
 * Created by Wucheng on 2016/3/21.
 */
public class FaxianList {
    public FaxianList(int faxianImgSrc, String faxianTitle) {
        FaxianImgSrc = faxianImgSrc;
        FaxianTitle = faxianTitle;
    }


    private int FaxianImgSrc;
    private String FaxianTitle;

    public String getFaxianTitle() {
        return FaxianTitle;
    }

    public void setFaxianTitle(String faxianTitle) {
        FaxianTitle = faxianTitle;
    }

    public int getFaxianImgSrc() {

        return FaxianImgSrc;
    }

    public void setFaxianImgSrc(int faxianImgSrc) {
        FaxianImgSrc = faxianImgSrc;
    }
}
