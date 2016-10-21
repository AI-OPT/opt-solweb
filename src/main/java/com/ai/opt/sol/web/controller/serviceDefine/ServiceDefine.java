package com.ai.opt.sol.web.controller.serviceDefine;

import com.ai.opt.sol.api.apisol.param.APISolServiceDefine;

public class ServiceDefine extends APISolServiceDefine {

	private static final long serialVersionUID = 1L;
	
	private String srvCategoryValue;
	
	private int prdlineCount;
	
	private int versionCount;

	public String getSrvCategoryValue() {
		return srvCategoryValue;
	}

	public void setSrvCategoryValue(String srvCategoryValue) {
		this.srvCategoryValue = srvCategoryValue;
	}

	public int getPrdlineCount() {
		return prdlineCount;
	}

	public void setPrdlineCount(int prdlineCount) {
		this.prdlineCount = prdlineCount;
	}

	public int getVersionCount() {
		return versionCount;
	}

	public void setVersionCount(int versionCount) {
		this.versionCount = versionCount;
	}

}
