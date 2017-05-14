package com.ai.opt.sol.api.apisearch.param;

import java.io.Serializable;

public class APIEnvSettings
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private String settingsId;
  private String ownertype;
  private String owner;
  private String env;
  private String zkcenter;
  private String resthttp;
  private String monitor;

  public String getSettingsId()
  {
    return this.settingsId;
  }

  public void setSettingsId(String settingsId) {
    this.settingsId = settingsId;
  }

  public String getOwnertype() {
    return this.ownertype;
  }

  public void setOwnertype(String ownertype) {
    this.ownertype = ownertype;
  }

  public String getOwner() {
    return this.owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  public String getEnv() {
    return this.env;
  }

  public void setEnv(String env) {
    this.env = env;
  }

  public String getZkcenter() {
    return this.zkcenter;
  }

  public void setZkcenter(String zkcenter) {
    this.zkcenter = zkcenter;
  }

  public String getResthttp() {
    return this.resthttp;
  }

  public void setResthttp(String resthttp) {
    this.resthttp = resthttp;
  }

  public String getMonitor() {
    return this.monitor;
  }

  public void setMonitor(String monitor) {
    this.monitor = monitor;
  }
}