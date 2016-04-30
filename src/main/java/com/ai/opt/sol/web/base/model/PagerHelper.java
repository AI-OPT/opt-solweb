package com.ai.opt.sol.web.base.model;

import javax.servlet.http.HttpServletRequest;
/**
 * 分页工具类
 * @author gucl
 *
 */
public class PagerHelper
{

	public PagerHelper()
	{
	}
    
	public static Pager getPager(HttpServletRequest httpServletRequest, int totalRows)
	{
		Pager pager = new Pager(totalRows);
		//获取当前所在的页数
		String currentPage = httpServletRequest.getParameter("currentPage");
		if (currentPage != null)
			pager.refresh(Integer.parseInt(currentPage));
		//根据用户执行的翻页操作，决定执行的方法
		String pagerMethod = httpServletRequest.getParameter("pageMethod");
		if (pagerMethod != null)
			if (pagerMethod.equals("first"))
				pager.first();
			else
			if (pagerMethod.equals("prev"))
				pager.previous();
			else
			if (pagerMethod.equals("next"))
				pager.next();
			else
			if (pagerMethod.equals("last"))
				pager.last();
			else
			if (pagerMethod.equals("page"))
				pager.gogo();
		return pager;
	}
	
	public static Pager getPager(HttpServletRequest httpServletRequest, int totalRows, int pageSize)
	{
		Pager pager = new Pager(totalRows, pageSize);
		String currentPage = httpServletRequest.getParameter("currentPage");
		if (currentPage != null && !"".equals(currentPage))
			pager.refresh(Integer.parseInt(currentPage));
		String pagerMethod = httpServletRequest.getParameter("pageMethod");
		if (pagerMethod != null && !"".equals(pagerMethod))
			if (pagerMethod.equals("first"))
				pager.first();
			else
			if (pagerMethod.equals("prev"))
				pager.previous();
			else
			if (pagerMethod.equals("next"))
				pager.next();
			else
			if (pagerMethod.equals("last"))
				pager.last();
			else
			if (pagerMethod.equals("page"))
				pager.gogo();
		return pager;
	}
	
}
