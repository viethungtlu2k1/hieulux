package com.globits.sample.dto;

import com.globits.core.dto.BaseObjectDto;
import com.globits.sample.domain.StudentClass;

public class StudentClassDto extends BaseObjectDto{
	
	private String code;
	private String name;

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public StudentClassDto() {
		
	}
	public StudentClassDto(StudentClass entity) {
		this.name = entity.getName();
		this.code = entity.getCode();
		this.setId(entity.getId());
	}
}
