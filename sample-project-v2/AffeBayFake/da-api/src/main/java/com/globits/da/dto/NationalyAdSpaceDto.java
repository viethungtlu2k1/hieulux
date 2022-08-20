package com.globits.da.dto;

import com.globits.core.dto.BaseObjectDto;
import com.globits.da.domain.DetailNationalyAdSpace;

public class NationalyAdSpaceDto extends BaseObjectDto {

	private AdSpaceDto adSpace;
	private NationalyDto nationaly;

	public NationalyAdSpaceDto() {
		// TODO Auto-generated constructor stub
	}

	public NationalyAdSpaceDto(DetailNationalyAdSpace entity) {
		super();
		if(entity != null) {
			this.setId(entity.getId());
//			this.adSpace = new AdSpaceDto(entity.getAdSpace());
			this.nationaly = new NationalyDto(entity.getNationaly());
		}
	}

	public AdSpaceDto getAdSpace() {
		return adSpace;
	}

	public void setAdSpace(AdSpaceDto adSpace) {
		this.adSpace = adSpace;
	}

	public NationalyDto getNationaly() {
		return nationaly;
	}

	public void setNationaly(NationalyDto nationaly) {
		this.nationaly = nationaly;
	}

}
