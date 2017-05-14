package com.ai.opt.sol.core.mapper.bo;

public class ApiEnvSettings {
    private String settingsId;

    private String ownertype;

    private String owner;

    private String env;

    private String zkcenter;

    private String resthttp;

    private String monitor;

    public String getSettingsId() {
        return settingsId;
    }

    public void setSettingsId(String settingsId) {
        this.settingsId = settingsId == null ? null : settingsId.trim();
    }

    public String getOwnertype() {
        return ownertype;
    }

    public void setOwnertype(String ownertype) {
        this.ownertype = ownertype == null ? null : ownertype.trim();
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner == null ? null : owner.trim();
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env == null ? null : env.trim();
    }

    public String getZkcenter() {
        return zkcenter;
    }

    public void setZkcenter(String zkcenter) {
        this.zkcenter = zkcenter == null ? null : zkcenter.trim();
    }

    public String getResthttp() {
        return resthttp;
    }

    public void setResthttp(String resthttp) {
        this.resthttp = resthttp == null ? null : resthttp.trim();
    }

    public String getMonitor() {
        return monitor;
    }

    public void setMonitor(String monitor) {
        this.monitor = monitor == null ? null : monitor.trim();
    }
}