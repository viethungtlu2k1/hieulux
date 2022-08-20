package com.globits.da.dto.search;

public class PagingSearchDto {
	private Integer pageIndex = 0;
	private Integer pageSize = 10;
	private String keyword = "";
	
	public PagingSearchDto() {}
	
	public PagingSearchDto(Integer pageIndex, Integer pageSize, String keyword) {
		if (pageIndex!=null) this.setPageIndex(pageIndex);
		if (pageSize!=null) this.setPageSize(pageSize);
		if (keyword!=null) this.setKeyword(keyword);
	}

	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
}
