package cn.com.onlicebook.bean;

import java.util.List;

public class Pagetion {
private List list;//需要分页查询显示的列表
	
	private int pageNo;//当前页码
	
	private int pageSize;//每页显示的数量
	
	private int totalCount;//总记录数
	
	private int totalPage;//总页码

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public int getPageNo() {
		if(pageNo > this.getTotalPage()) {
			pageNo = this.getTotalPage();
		}
		if(pageNo < 1) {
			pageNo = 1;
		}
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		this.totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
		
		return this.totalPage;
	}

}
