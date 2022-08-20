package com.globits.da.dto;

import java.util.ArrayList;
import java.util.List;

import com.globits.core.dto.BaseObjectDto;
import com.globits.da.domain.Category;
import com.globits.da.domain.Project;

public class ProjectDto extends BaseObjectDto {
	private String name;
	private String code;
	private String description;
	

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
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ProjectDto() {
		super();
	}

	public ProjectDto(Project entity) {
		if (entity != null) {
			this.setId(entity.getId());
			this.code = entity.getCode();
			this.name = entity.getName();
			this.description = entity.getDescription();
			
		}
	}

	

	public void add(ProjectDto dA) {
		// TODO Auto-generated method stub
		
	}

}
