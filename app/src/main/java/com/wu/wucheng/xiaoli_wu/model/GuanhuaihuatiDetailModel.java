package com.wu.wucheng.xiaoli_wu.model;

import java.util.List;

/**
 * Created by Wucheng on 2016/3/23.
 */
public class GuanhuaihuatiDetailModel {

    /**
     * id : 8
     * title : 中秋佳节回家的该买车票咯
     * catid : 6
     * content : &lt;p&gt;&lt;span style=&quot;color:#333333;font-family:fzlantinghei-el-gbk, &amp;#39;open sans&amp;#39;, &amp;#39;hiragino sans gb&amp;#39;, &amp;#39;microsoft yahei&amp;#39;, 微软雅黑, stheiti, &amp;#39;wenquanyi micro hei&amp;#39;, dengxian, arial, simsun, sans-serif;font-size:18px;line-height:31px;background-color:#ffffff;&quot;&gt;当当当~今天介绍的是药用水果：柠檬，它维他命非常丰富，所以经常吃对皮肤有好处哟，可能有些美眉会觉得柠檬很酸，难以下咽，没关系啊，小礼君今天推荐的不是纯柠檬，而是柠檬味道的小零食，保证你们都会爱上这个~初恋般酸甜的滋味的~快来看看吧~&lt;/span&gt;&lt;/p&gt;
     * fengmian : uploadfiles/image/201508/3.jpg
     * updatetime : 1441979851
     */

    private List<ShowtopicBean> showtopic;

    public List<ShowtopicBean> getShowtopic() {
        return showtopic;
    }

    public void setShowtopic(List<ShowtopicBean> showtopic) {
        this.showtopic = showtopic;
    }


    public static class ShowtopicBean {
        private String id;
        private String title;
        private String catid;
        private String content;
        private String fengmian;
        private String updatetime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCatid() {
            return catid;
        }

        public void setCatid(String catid) {
            this.catid = catid;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getFengmian() {
            return fengmian;
        }

        public void setFengmian(String fengmian) {
            this.fengmian = fengmian;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }
    }
}
