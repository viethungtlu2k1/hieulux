package com.globits.da.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.globits.core.service.GenericService;
import com.globits.da.domain.AdSpace;
import com.globits.da.dto.AdSpaceDto;
import com.globits.da.dto.CategoryAdSpaceDto;
import com.globits.da.dto.NationalyAdSpaceDto;
import com.globits.da.dto.search.SearchDto;

@Service
public interface AdSpaceService extends GenericService<AdSpace, UUID> {
	Page<AdSpaceDto> searchByPage(SearchDto dto);

	public AdSpaceDto saveOrUpdate(UUID id, AdSpaceDto dto);

	public AdSpaceDto getAdSpaceById(UUID id);

	public Boolean checkName(UUID id, String code);

	public Boolean deleteById(UUID id);
	
	public List<NationalyAdSpaceDto> getNationalyAdSpaceByAdSpaceId(UUID id);
	
	public List<CategoryAdSpaceDto> getCategoryAdSpaceByAdSpaceId(UUID id);
}
