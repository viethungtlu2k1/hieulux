package com.globits.da.dto;

import com.globits.core.dto.BaseObjectDto;
import com.globits.da.domain.CampaignType;

public class CampaignTypeDto extends BaseObjectDto {
	private String name;
	private String code;
	private String description;
	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public CampaignTypeDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CampaignTypeDto(CampaignType entity) {
		if(entity != null) {
			this.setId(entity.getId());
			this.code = entity.getCode();
			this.name = entity.getName();
			this.description = entity.getDescription();
		}
	}
}
