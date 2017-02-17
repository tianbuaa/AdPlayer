package com.zc741.xxx.ad.bean;

/**
 * Created by xxx on 2017/1/4.
 */

public class WSDonate {


    /**
     * type : donation
     * data : {"amount":"1.00","temple_id":2,"content":"身体健康，万事如,意","wechat_user_id":1191,"donation_item_id":41,"temple_user_id":1760,"createTime":1483691392000,"party_id":null,"headimgurl":"http://wx.qlogo.cn/mmopen/Xy74ud6pV0iaRqIxW3MfVzhgPTj8buAw3BeVVQErZ1icUPnwrr4o7KDczlYFsP6ud0LARNqA0PC3LYwI6w9TdgQFqoaHiag8Z9F/0","trade_no":"donation1474948538145UjXG","tel":"18768360621","location":"浙江 湖州","id":314,"tag":"日行一善","salutation":"开心果679"}
     */

    private String type;
    private DataBean data;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * amount : 1.00
         * temple_id : 2
         * content : 身体健康，万事如,意
         * wechat_user_id : 1191
         * donation_item_id : 41
         * temple_user_id : 1760
         * createTime : 1483691392000
         * party_id : null
         * headimgurl : http://wx.qlogo.cn/mmopen/Xy74ud6pV0iaRqIxW3MfVzhgPTj8buAw3BeVVQErZ1icUPnwrr4o7KDczlYFsP6ud0LARNqA0PC3LYwI6w9TdgQFqoaHiag8Z9F/0
         * trade_no : donation1474948538145UjXG
         * tel : 18768360621
         * location : 浙江 湖州
         * id : 314
         * tag : 日行一善
         * salutation : 开心果679
         */

        private Float amount;
        private int temple_id;
        private String content;
        private int wechat_user_id;
        private int donation_item_id;
        private int temple_user_id;
        private long createTime;
        private Object party_id;
        private String headimgurl;
        private String trade_no;
        private String tel;
        private String location;
        private int id;
        private String tag;
        private String salutation;

        public float getAmount() {
            return amount;
        }

        public void setAmount(Float amount) {
            this.amount = amount;
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

        public int getWechat_user_id() {
            return wechat_user_id;
        }

        public void setWechat_user_id(int wechat_user_id) {
            this.wechat_user_id = wechat_user_id;
        }

        public int getDonation_item_id() {
            return donation_item_id;
        }

        public void setDonation_item_id(int donation_item_id) {
            this.donation_item_id = donation_item_id;
        }

        public int getTemple_user_id() {
            return temple_user_id;
        }

        public void setTemple_user_id(int temple_user_id) {
            this.temple_user_id = temple_user_id;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public Object getParty_id() {
            return party_id;
        }

        public void setParty_id(Object party_id) {
            this.party_id = party_id;
        }

        public String getHeadimgurl() {
            return headimgurl;
        }

        public void setHeadimgurl(String headimgurl) {
            this.headimgurl = headimgurl;
        }

        public String getTrade_no() {
            return trade_no;
        }

        public void setTrade_no(String trade_no) {
            this.trade_no = trade_no;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getSalutation() {
            return salutation;
        }

        public void setSalutation(String salutation) {
            this.salutation = salutation;
        }
    }
}
