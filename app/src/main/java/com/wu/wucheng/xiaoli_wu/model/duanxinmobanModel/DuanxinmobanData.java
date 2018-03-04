package com.wu.wucheng.xiaoli_wu.model.duanxinmobanModel;

import java.util.List;

/**
 * Created by Wucheng on 2016/3/23.
 */
public class DuanxinmobanData {

    /**
     * id : 4
     * title : 注册晓礼送话费
     * content : 您好,注册晓礼送话费啦,快来看看吧
     * status : 1
     * addtime : 1441679116
     * typeid : 2
     */

    private List<BlessBean> bless;

    public List<BlessBean> getBless() {
        return bless;
    }

    public void setBless(List<BlessBean> bless) {
        this.bless = bless;
    }

    public static class BlessBean {
        private String id;
        private String title;
        private String content;
        private String status;
        private String addtime;
        private String typeid;

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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getTypeid() {
            return typeid;
        }

        public void setTypeid(String typeid) {
            this.typeid = typeid;
        }
    }
}
