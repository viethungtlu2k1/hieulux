package com.globits.da.dto;

import com.globits.core.dto.BaseObjectDto;
import com.globits.da.domain.LandingPage;
import com.globits.da.domain.Product;

public class LandingPageDto extends BaseObjectDto {
	
	private String name;
	private String code;
	private Product product;
	private String url;
	private String type; // LANDINGPAGE, PRELANDINGPAGE
	private Boolean isShow;
	
	public LandingPageDto() {
		super();
	}
	
	public LandingPageDto(LandingPage entity) {
		if (entity !=  null) {
			this.setId(entity.getId());
			this.name = entity.getName();
			this.code = entity.getCode();
			this.product = entity.getProduct();
			this.url = entity.getUrl();
			this.type = entity.getType();
			this.isShow = entity.getIsShow();
		}
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getIsShow() {
		return isShow;
	}

	public void setIsShow(Boolean isShow) {
		this.isShow = isShow;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
