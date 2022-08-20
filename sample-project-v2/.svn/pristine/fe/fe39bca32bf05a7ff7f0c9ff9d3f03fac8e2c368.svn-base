package com.globits.da.dto.search;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.globits.da.domain.Category;
import com.globits.da.domain.Currency;
import com.globits.da.domain.Nationaly;

public class ProductSearchDto {

	private Integer pageIndex = 0;
	private Integer pageSize = 10;
	private String sortBy = "createDate";
	private String sortDirection = "desc";
	private String keyword = "";
	private Nationaly nationaly;
	private Category category;
	private Currency currencyPrice;
	private Currency currencyPayout;

	public ProductSearchDto() {}
	
	public ProductSearchDto(Integer page, 
			  Integer rpp, 
			  String sort, 
			  String direction, 
			  String keyword) {
	    if (page!=null) this.setPageIndex(page);
	    if (rpp!=null) this.setPageSize(rpp);
	    if (sort!=null) this.setSortBy(sort);
	    if (direction!=null) this.setSortDirection(direction);
	    if (keyword!=null) this.setKeyword(keyword);
	  }

	public ProductSearchDto(Integer page, 
			  Integer rpp, 
			  String sort, 
			  String direction, 
			  String keyword, 
			  Nationaly nationaly, 
			  Category category, 
			  Currency currencyPrice, 
			  Currency currencyPayout) {
	    if (page!=null) this.setPageIndex(page);
	    if (rpp!=null) this.setPageSize(rpp);
	    if (sort!=null) this.setSortBy(sort);
	    if (direction!=null) this.setSortDirection(direction);
	    if (keyword!=null) this.setKeyword(keyword);
	    if (nationaly!=null) this.setNationaly(nationaly);
	    if (category!=null) this.setCategory(category);
	    if (currencyPrice!=null) this.setCurrencyPrice(currencyPrice);
	    if (currencyPayout!=null) this.setCurrencyPayout(currencyPayout);
	  }
	
	  public Pageable pagingAndSorting() {
	    return PageRequest.of(this.pageIndex, this.pageSize, Sort.by(this.sortDirection == "asc" ? Direction.ASC : Direction.DESC, this.sortBy));
	  }
	
	  public Integer getPageIndex() {
	    return this.pageIndex;
	  }
	
	  public void setPageIndex(Integer page) {
		  this.pageIndex = page > 0 ? page-1 : 0;
	  }
	
	  public Integer getPageSize() {
	    return this.pageSize;
	  }
	
	  public void setPageSize(Integer rpp) {
	    if (rpp > 0) {
	      this.pageSize = rpp;
	    }
	  }
	
	  public String getSortBy() {
	    return this.sortBy;
	  }
	
	  public void setSortBy(String sortBy) {
	    List<String> field = List.of("id", "code", "name", "price", "createDate");
	    this.sortBy = field.contains(sortBy) ? sortBy : "id";
	  }
	
	  public String getSortDirection() {
	    return this.sortDirection;
	  }
	
	  public void setSortDirection(String sortDirection) {
	    this.sortDirection = sortDirection.toLowerCase().equals("asc") ? "asc" : "desc";
	  }
	
	  public String getKeyword() {
	    return this.keyword;
	  }
	
	  public void setKeyword(String keyword) {
	    this.keyword = keyword;
	  }

	public Nationaly getNationaly() {
		return nationaly;
	}

	public void setNationaly(Nationaly nationaly) {
		this.nationaly = nationaly;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Currency getCurrencyPrice() {
		return currencyPrice;
	}

	public void setCurrencyPrice(Currency currencyPrice) {
		this.currencyPrice = currencyPrice;
	}

	public Currency getCurrencyPayout() {
		return currencyPayout;
	}

	public void setCurrencyPayout(Currency currencyPayout) {
		this.currencyPayout = currencyPayout;
	}
}
