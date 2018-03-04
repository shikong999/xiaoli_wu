package com.wu.wucheng.xiaoli_wu.model;

import java.util.List;

/**
 * Created by Wucheng on 2016/3/25.
 */
public class WodeInfoModel {

    /**
     * userid : 43
     * nickname : we
     * mobile : 123456
     * avatar : uploadfiles/member/43/43_785469.jpg
     * xingming :
     * sex : 0
     * year : 0
     * month : 0
     * day : 0
     * xingzuo : 0
     * address :
     */

    private List<UserinfoBean> userinfo;
    /**
     * id : 1324
     * impress : assadasdasd
     * impressnum : 1
     */

    private List<ImpresslistBean> impresslist;
    /**
     * id : 1
     * name : 魔羯座
     */

    private List<XzlistBean> xzlist;

    public List<UserinfoBean> getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(List<UserinfoBean> userinfo) {
        this.userinfo = userinfo;
    }

    public List<ImpresslistBean> getImpresslist() {
        return impresslist;
    }

    public void setImpresslist(List<ImpresslistBean> impresslist) {
        this.impresslist = impresslist;
    }

    public List<XzlistBean> getXzlist() {
        return xzlist;
    }

    public void setXzlist(List<XzlistBean> xzlist) {
        this.xzlist = xzlist;
    }

    public static class UserinfoBean {
        private String userid;
        private String nickname;
        private String mobile;
        private String avatar;
        private String xingming;
        private String sex;
        private String year;
        private String month;
        private String day;
        private String xingzuo;
        private String address;

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getXingming() {
            return xingming;
        }

        public void setXingming(String xingming) {
            this.xingming = xingming;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getXingzuo() {
            return xingzuo;
        }

        public void setXingzuo(String xingzuo) {
            this.xingzuo = xingzuo;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }

    public static class ImpresslistBean {
        private String id;
        private String impress;
        private String impressnum;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImpress() {
            return impress;
        }

        public void setImpress(String impress) {
            this.impress = impress;
        }

        public String getImpressnum() {
            return impressnum;
        }

        public void setImpressnum(String impressnum) {
            this.impressnum = impressnum;
        }
    }

    public static class XzlistBean {
        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
