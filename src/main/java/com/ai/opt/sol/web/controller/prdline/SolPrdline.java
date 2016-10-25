package com.ai.opt.sol.web.controller.prdline;

import com.ai.opt.sol.api.apisol.param.APISolPrdline;

public class SolPrdline extends APISolPrdline {

	private static final long serialVersionUID = 1L;

	private String industryName;

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}
}
