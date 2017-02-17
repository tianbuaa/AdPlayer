package com.zc741.xxx.ad.bean;

/**
 * Created by xxx on 2017/1/5.
 */

public class AppVersion {

    /**
     * deviceType : 7
     * updateUrl :
     * systemType : android
     * id : 1
     * forceUpdate : 1
     * versionName : 1.1
     * version : 1
     * updateTitle : 121212
     * updateMessage : null
     * qrcodeUrl :
     */

    private String deviceType;
    private String updateUrl;
    private String systemType;
    private int id;
    private int forceUpdate;
    private String versionName;
    private String version;
    private String updateTitle;
    private Object updateMessage;
    private String qrcodeUrl;

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getUpdateUrl() {
        return updateUrl;
    }

    public void setUpdateUrl(String updateUrl) {
        this.updateUrl = updateUrl;
    }

    public String getSystemType() {
        return systemType;
    }

    public void setSystemType(String systemType) {
        this.systemType = systemType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getForceUpdate() {
        return forceUpdate;
    }

    public void setForceUpdate(int forceUpdate) {
        this.forceUpdate = forceUpdate;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUpdateTitle() {
        return updateTitle;
    }

    public void setUpdateTitle(String updateTitle) {
        this.updateTitle = updateTitle;
    }

    public Object getUpdateMessage() {
        return updateMessage;
    }

    public void setUpdateMessage(Object updateMessage) {
        this.updateMessage = updateMessage;
    }

    public String getQrcodeUrl() {
        return qrcodeUrl;
    }

    public void setQrcodeUrl(String qrcodeUrl) {
        this.qrcodeUrl = qrcodeUrl;
    }
}
