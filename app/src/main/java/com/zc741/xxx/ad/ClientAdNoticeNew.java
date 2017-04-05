package com.zc741.xxx.ad;

import java.util.List;

/**
 * Created by xxx on 2017/4/5.
 */

public class ClientAdNoticeNew {

    /**
     * wsDomain : ws://localhost:8080
     * notice_delay : 60
     * noticeList : [{"summary":"2342342"}]
     * name : 观音禅寺在线功德箱
     * temple : {"qrcode_url":"http://syzxbkt.oss-cn-hangzhou.aliyuncs.com/temple%2F2%2Ftemple%2Fqrcode.jpg","temple_name":"东台市观音禅寺","donate_url":"http://syzxbkt.oss-cn-hangzhou.aliyuncs.com/image/erweima/logo/syzx.png"}
     * adList : [{"res":{"videoCode":"http://7xncza.com1.z0.glb.clouddn.com/Coldplay.mp4","cname":"湖州永宁寺延秉大和尚采访"},"typeRef":"1"}]
     * id : 2
     * temple_id : 2
     * drPage : {"totalRow":1,"pageNumber":1,"firstPage":true,"lastPage":true,"totalPage":1,"pageSize":30,"list":[{"amount":1,"headimgurl":"http://wx.qlogo.cn/mmopen/Xy74ud6pV0iaRqIxW3MfVzhgPTj8buAw3BeVVQErZ1icUPnwrr4o7KDczlYFsP6ud0LARNqA0PC3LYwI6w9TdgQFqoaHiag8Z9F/0","salutation":"开心果679","content":"身体健康，万事如,意"}]}
     * content : 观音禅寺在线功德箱
     * siyuanzaixian : {"qrcode_url":"http://syzxbkt.oss-cn-hangzhou.aliyuncs.com/image/erweima/logo/syzx.png","temple_name":"寺院在线","donate_url":""}
     */

    private String wsDomain;
    private int notice_delay;
    private String name;
    private TempleBean temple;
    private int id;
    private int temple_id;
    private DrPageBean drPage;
    private String content;
    private SiyuanzaixianBean siyuanzaixian;
    private List<NoticeListBean> noticeList;
    private List<AdListBean> adList;

    public String getWsDomain() {
        return wsDomain;
    }

    public void setWsDomain(String wsDomain) {
        this.wsDomain = wsDomain;
    }

    public int getNotice_delay() {
        return notice_delay;
    }

    public void setNotice_delay(int notice_delay) {
        this.notice_delay = notice_delay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TempleBean getTemple() {
        return temple;
    }

    public void setTemple(TempleBean temple) {
        this.temple = temple;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTemple_id() {
        return temple_id;
    }

    public void setTemple_id(int temple_id) {
        this.temple_id = temple_id;
    }

    public DrPageBean getDrPage() {
        return drPage;
    }

    public void setDrPage(DrPageBean drPage) {
        this.drPage = drPage;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public SiyuanzaixianBean getSiyuanzaixian() {
        return siyuanzaixian;
    }

    public void setSiyuanzaixian(SiyuanzaixianBean siyuanzaixian) {
        this.siyuanzaixian = siyuanzaixian;
    }

    public List<NoticeListBean> getNoticeList() {
        return noticeList;
    }

    public void setNoticeList(List<NoticeListBean> noticeList) {
        this.noticeList = noticeList;
    }

    public List<AdListBean> getAdList() {
        return adList;
    }

    public void setAdList(List<AdListBean> adList) {
        this.adList = adList;
    }

    public static class TempleBean {
        /**
         * qrcode_url : http://syzxbkt.oss-cn-hangzhou.aliyuncs.com/temple%2F2%2Ftemple%2Fqrcode.jpg
         * temple_name : 东台市观音禅寺
         * donate_url : http://syzxbkt.oss-cn-hangzhou.aliyuncs.com/image/erweima/logo/syzx.png
         */

        private String qrcode_url;
        private String temple_name;
        private String donate_url;

        public String getQrcode_url() {
            return qrcode_url;
        }

        public void setQrcode_url(String qrcode_url) {
            this.qrcode_url = qrcode_url;
        }

        public String getTemple_name() {
            return temple_name;
        }

        public void setTemple_name(String temple_name) {
            this.temple_name = temple_name;
        }

        public String getDonate_url() {
            return donate_url;
        }

        public void setDonate_url(String donate_url) {
            this.donate_url = donate_url;
        }
    }

    public static class DrPageBean {
        /**
         * totalRow : 1
         * pageNumber : 1
         * firstPage : true
         * lastPage : true
         * totalPage : 1
         * pageSize : 30
         * list : [{"amount":1,"headimgurl":"http://wx.qlogo.cn/mmopen/Xy74ud6pV0iaRqIxW3MfVzhgPTj8buAw3BeVVQErZ1icUPnwrr4o7KDczlYFsP6ud0LARNqA0PC3LYwI6w9TdgQFqoaHiag8Z9F/0","salutation":"开心果679","content":"身体健康，万事如,意"}]
         */

        private int totalRow;
        private int pageNumber;
        private boolean firstPage;
        private boolean lastPage;
        private int totalPage;
        private int pageSize;
        private List<ListBean> list;

        public int getTotalRow() {
            return totalRow;
        }

        public void setTotalRow(int totalRow) {
            this.totalRow = totalRow;
        }

        public int getPageNumber() {
            return pageNumber;
        }

        public void setPageNumber(int pageNumber) {
            this.pageNumber = pageNumber;
        }

        public boolean isFirstPage() {
            return firstPage;
        }

        public void setFirstPage(boolean firstPage) {
            this.firstPage = firstPage;
        }

        public boolean isLastPage() {
            return lastPage;
        }

        public void setLastPage(boolean lastPage) {
            this.lastPage = lastPage;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * amount : 1
             * headimgurl : http://wx.qlogo.cn/mmopen/Xy74ud6pV0iaRqIxW3MfVzhgPTj8buAw3BeVVQErZ1icUPnwrr4o7KDczlYFsP6ud0LARNqA0PC3LYwI6w9TdgQFqoaHiag8Z9F/0
             * salutation : 开心果679
             * content : 身体健康，万事如,意
             */

            private int amount;
            private String headimgurl;
            private String salutation;
            private String content;

            public int getAmount() {
                return amount;
            }

            public void setAmount(int amount) {
                this.amount = amount;
            }

            public String getHeadimgurl() {
                return headimgurl;
            }

            public void setHeadimgurl(String headimgurl) {
                this.headimgurl = headimgurl;
            }

            public String getSalutation() {
                return salutation;
            }

            public void setSalutation(String salutation) {
                this.salutation = salutation;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }
    }

    public static class SiyuanzaixianBean {
        /**
         * qrcode_url : http://syzxbkt.oss-cn-hangzhou.aliyuncs.com/image/erweima/logo/syzx.png
         * temple_name : 寺院在线
         * donate_url :
         */

        private String qrcode_url;
        private String temple_name;
        private String donate_url;

        public String getQrcode_url() {
            return qrcode_url;
        }

        public void setQrcode_url(String qrcode_url) {
            this.qrcode_url = qrcode_url;
        }

        public String getTemple_name() {
            return temple_name;
        }

        public void setTemple_name(String temple_name) {
            this.temple_name = temple_name;
        }

        public String getDonate_url() {
            return donate_url;
        }

        public void setDonate_url(String donate_url) {
            this.donate_url = donate_url;
        }
    }

    public static class NoticeListBean {
        /**
         * summary : 2342342
         */

        private String summary;

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }
    }

    public static class AdListBean {
        /**
         * res : {"videoCode":"http://7xncza.com1.z0.glb.clouddn.com/Coldplay.mp4","cname":"湖州永宁寺延秉大和尚采访"}
         * typeRef : 1
         */

        private ResBean res;
        private String typeRef;

        public ResBean getRes() {
            return res;
        }

        public void setRes(ResBean res) {
            this.res = res;
        }

        public String getTypeRef() {
            return typeRef;
        }

        public void setTypeRef(String typeRef) {
            this.typeRef = typeRef;
        }

        public static class ResBean {
            /**
             * videoCode : http://7xncza.com1.z0.glb.clouddn.com/Coldplay.mp4
             * cname : 湖州永宁寺延秉大和尚采访
             */

            private String videoCode;
            private String cname;

            public String getVideoCode() {
                return videoCode;
            }

            public void setVideoCode(String videoCode) {
                this.videoCode = videoCode;
            }

            public String getCname() {
                return cname;
            }

            public void setCname(String cname) {
                this.cname = cname;
            }
        }
    }
}
