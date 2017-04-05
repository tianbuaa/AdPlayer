package com.zc741.xxx.ad.bean;

import java.util.List;

/**
 * Created by xxx on 2016/12/27.
 */

public class ClientAdNotice {

    /**
     * notice_delay : 60
     * noticeList : [{"summary":"summary"},{"summary":"123123"}]
     * name : 盐城净土寺在线功德箱
     * temple : {"qrcode_url":"http://img.siyuanzaixian.cn/temple/image/201604/25/c8a37b82-80c9-4f45-9828-bb6a668bbab5.jpg","temple_name":"盐城净土古寺","donate_url":"http://syzxbkt.oss-cn-hangzhou.aliyuncs.com/image/erweima/logo/syzx.png"}
     * adList : [{"res":{"videoCode":"http://syzxbkt.oss-cn-hangzhou.aliyuncs.com/video/ad/yns/%E5%BE%B7%E6%B8%85%E6%B0%B8%E5%AE%81%E5%AF%BA%E5%BE%B7%E6%B8%85%E6%B0%B8%E5%AE%81%E5%AF%BA.mp4","cname":"湖州永宁寺浏览"},"typeRef":"2"},{"res":{"videoCode":"http://7xncza.com1.z0.glb.clouddn.com/Coldplay.mp4","cname":"湖州永宁寺延秉大和尚采访"},"typeRef":"1"}]
     * id : 1
     * temple_id : 1
     * content : 盐城净土寺在线功德箱
     * siyuanzaixian : {"qrcode_url":"http://syzxbkt.oss-cn-hangzhou.aliyuncs.com/image/erweima/logo/syzx.png","temple_name":"寺院在线","donate_url":""}
     */

    private int notice_delay;
    private String name;
    private TempleBean temple;
    private int id;
    private int temple_id;
    private String content;
    private SiyuanzaixianBean siyuanzaixian;
    private List<NoticeListBean> noticeList;
    private List<AdListBean> adList;

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
         * qrcode_url : http://img.siyuanzaixian.cn/temple/image/201604/25/c8a37b82-80c9-4f45-9828-bb6a668bbab5.jpg
         * temple_name : 盐城净土古寺
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
         * summary : summary
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
         * res : {"videoCode":"http://syzxbkt.oss-cn-hangzhou.aliyuncs.com/video/ad/yns/%E5%BE%B7%E6%B8%85%E6%B0%B8%E5%AE%81%E5%AF%BA%E5%BE%B7%E6%B8%85%E6%B0%B8%E5%AE%81%E5%AF%BA.mp4","cname":"湖州永宁寺浏览"}
         * typeRef : 2
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
             * videoCode : http://syzxbkt.oss-cn-hangzhou.aliyuncs.com/video/ad/yns/%E5%BE%B7%E6%B8%85%E6%B0%B8%E5%AE%81%E5%AF%BA%E5%BE%B7%E6%B8%85%E6%B0%B8%E5%AE%81%E5%AF%BA.mp4
             * cname : 湖州永宁寺浏览
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
