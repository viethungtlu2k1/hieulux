package com.globits.da.dto;

import com.globits.core.dto.BaseObjectDto;
import com.globits.da.domain.DetailCategoryAdSpace;

public class CategoryAdSpaceDto extends BaseObjectDto {
	
	private AdSpaceDto adSpace;
	private CategoryDto category;

	public CategoryAdSpaceDto() {
		// TODO Auto-generated constructor stub
	}

	public CategoryAdSpaceDto(DetailCategoryAdSpace entity) {
		super();
		if(entity != null) {
			this.setId(entity.getId());
			this.category = new CategoryDto(entity.getCategory());
		}
	}

	public AdSpaceDto getAdSpace() {
		return adSpace;
	}

	public void setAdSpace(AdSpaceDto adSpace) {
		this.adSpace = adSpace;
	}

	public CategoryDto getCategory() {
		return category;
	}

	public void setCategory(CategoryDto category) {
		this.category = category;
	}
	
	

}
