package com.globits.da.dto;

import com.globits.core.dto.BaseObjectDto;
import com.globits.da.domain.Nationaly;

public class NationalyDto extends BaseObjectDto{
	

	private String name;
	private String code;
	
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
	public NationalyDto() {
		super();
	}
	public NationalyDto(Nationaly entity) {
		if(entity != null) {
			this.setId(entity.getId());
			this.code = entity.getCode();
			this.name = entity.getName();
		}
	}
}
