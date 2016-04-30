package com.ai.opt.sol.web.base.model;

import java.io.Serializable;
import java.util.List;

/**
 * 页面结果对象
 *
 * @param <T>
 * Date: 2015年9月1日 <br>
 * Copyright (c) 2015 asiainfo.com <br>
 * @author gucl
 */
public class PagerResult<T> implements Serializable {
	private static final long serialVersionUID = -4015257996854143368L;
	private Pager pager;
    private List<T> result;

    public Pager getPager() {
        return pager;
    }

    public void setPager(Pager pager) {
        this.pager = pager;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }
}
