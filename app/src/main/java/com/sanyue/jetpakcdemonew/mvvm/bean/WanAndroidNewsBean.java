package com.sanyue.jetpakcdemonew.mvvm.bean;

import java.util.List;

/***
 * Create by Yip
 * Create Time 2020/9/28
 */
public class WanAndroidNewsBean {
    private ItemBean data;
    private int errorCode;
    private String errorMsg;

    public ItemBean getData() {
        return data;
    }

    public void setData(ItemBean data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public static class ItemBean{
        private int curPage;
        private int offset;
        private boolean over;
        private int pageCount;
        private int size;
        private int total;
        private List<ItemDetailsBean> datas;

        public int getCurPage() {
            return curPage;
        }

        public void setCurPage(int curPage) {
            this.curPage = curPage;
        }

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public boolean isOver() {
            return over;
        }

        public void setOver(boolean over) {
            this.over = over;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<ItemDetailsBean> getDatas() {
            return datas;
        }

        public void setDatas(List<ItemDetailsBean> datas) {
            this.datas = datas;
        }
    }
    public static class ItemDetailsBean{
//          "apkLink": "",
//                "audit": 1,
//                "author": "",
//                "canEdit": false,
//                "chapterId": 502,
//                "chapterName": "自助",
//                "collect": false,
//                "courseId": 13,
//                "desc": "",
//                "descMd": "",
//                "envelopePic": "",
//                "fresh": false,
//                "id": 15359,
//                "link": "https://juejin.im/post/6875898896082534407/",
//                "niceDate": "2020-09-24 12:02",
//                "niceShareDate": "2020-09-24 12:02",
//                "origin": "",
//                "prefix": "",
//                "projectLink": "",
//                "publishTime": 1600920164000,
//                "realSuperChapterId": 493,
//                "selfVisible": 0,
//                "shareDate": 1600920164000,
//                "shareUser": "renxhui",
//                "superChapterId": 494,
//                "superChapterName": "广场Tab",
//                "tags": [],
//                "title": "Android OpenGl Es 学习（三）：编译着色器",
//                "type": 0,
//                "userId": 27931,
//                "visible": 1,
//                "zan": 0

        private String title;
        private String shareUser;
        private String link;
        private String niceDate;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getShareUser() {
            return shareUser;
        }

        public void setShareUser(String shareUser) {
            this.shareUser = shareUser;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getNiceDate() {
            return niceDate;
        }

        public void setNiceDate(String niceDate) {
            this.niceDate = niceDate;
        }
    }
}
