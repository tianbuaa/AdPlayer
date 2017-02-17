package com.zc741.xxx.ad.utils;

import com.zc741.xxx.ad.bean.DonateList;
import com.zc741.xxx.ad.bean.WSDonate;

/**
 * Created by xxx on 2017/1/9.
 */

public class DonateDataBeanConverter {
    public static DonateList.ListBean wsDonateDataBeanToListBean(WSDonate.DataBean dataBean) {
        if (dataBean == null) {
            throw new IllegalArgumentException("WSDonate dataBean can not be null");
        }
        DonateList.ListBean listBean = new DonateList.ListBean();

        listBean.setAmount(dataBean.getAmount());
        listBean.setContent(dataBean.getContent());
        listBean.setSalutation(dataBean.getSalutation());
        listBean.setHeadimgurl(dataBean.getHeadimgurl());
        return listBean;
    }
}
