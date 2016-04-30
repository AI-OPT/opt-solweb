package com.ai.opt.sol.web.base.model;

/**
 * 分页对象
 * @author gucl
 *
 */
public class Pager
{
    //查询的到的记录的总条数
	private int totalRows;
	//每页显示的记录条数
	private int pageSize;
	//当前显示的页数
	private int currentPage;
	//总页数
	private int totalPages;
	//起始的记录条数
	private int startRow;
	//结束的记录条数
	private int endRow;

	public Pager()
	{
		pageSize = 10;
	}

	public Pager(int _totalRows)
	{
		pageSize = 10;
		totalRows = _totalRows;
		totalPages = totalRows / pageSize;
		int mod = totalRows % pageSize;
		if (mod > 0)
			totalPages++;
		currentPage = 1;
		startRow = 0;
		endRow=startRow+pageSize;
	}
	
	public Pager(int _totalRows, int _pageSize)
	{
		totalRows = _totalRows;
		pageSize = _pageSize;
		totalPages = totalRows / pageSize;
		int mod = totalRows % pageSize;
		if (mod > 0)
			totalPages++;
		currentPage = 1;
		startRow = 0;
		endRow=startRow+pageSize;
	}

	public int getStartRow()
	{
		return (currentPage - 1) * pageSize;
	}

	public int getEndRow() {
		return (currentPage - 1) * pageSize+pageSize;
	}

	public int getTotalPages()
	{
		return totalPages;
	}

	public int getCurrentPage()
	{
		return currentPage;
	}

	public int getPageSize()
	{
		return pageSize;
	}

	public void setTotalRows(int totalRows)
	{
		this.totalRows = totalRows;
	}

	public void setStartRow(int startRow)
	{
		this.startRow = startRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	public void setTotalPages(int totalPages)
	{
		this.totalPages = totalPages;
	}

	public void setCurrentPage(int currentPage)
	{
		this.currentPage = currentPage;
	}

	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}

	public int getTotalRows()
	{
		return totalRows;
	}
    //显示第一页
	public void first()
	{
		currentPage = 1;
		startRow = 0;
		endRow=startRow+pageSize;
	}
    //显示上一页
	public void previous()
	{
		if (currentPage == 1)
		{
			return;
		} else
		{
			currentPage--;
			startRow = (currentPage - 1) * pageSize;
			endRow=startRow+pageSize;
			return;
		}
	}
    //显示下一页
	public void next()
	{
		if (currentPage < totalPages)
			currentPage++;
		startRow = (currentPage - 1) * pageSize;
		endRow=startRow+pageSize;
	}
    //显示最后一页
	public void last()
	{
		currentPage = totalPages;
		startRow = (currentPage - 1) * pageSize;
		endRow=startRow+pageSize;
	}

	public void gogo()
	{
		startRow = (currentPage - 1) * pageSize;
		endRow=startRow+pageSize;
	}

	public void refresh(int _currentPage)
	{
		currentPage = _currentPage;
		if (currentPage > totalPages)
			last();
	}
}
