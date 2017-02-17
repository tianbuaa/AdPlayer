package com.zc741.xxx.ad.bean;

/**
 * Created by xxx on 2016/11/15.
 */

public class Version {

    /**
     * version : 1
     * versionName : 1.1
     * downloadUrl : http://www.zc741.com/weather/ad.apk
     */

    private int version;
    private String versionName;
    private String downloadUrl;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
}
