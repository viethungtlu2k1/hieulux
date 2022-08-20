package com.globits.da.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;

import com.globits.core.service.GenericService;
import com.globits.da.domain.Nationaly;
import com.globits.da.dto.NationalyDto;
import com.globits.da.dto.search.SearchDto;

public interface NationalyService extends GenericService<Nationaly, UUID> {
	public Page<NationalyDto> GetPage(int pagesize, int pageindex);
	public NationalyDto SaveOrUpdate(UUID id, NationalyDto dto);
	public Boolean DeleteNation(UUID id);
	public NationalyDto GetOneEntity(UUID id);
	public Page<NationalyDto> searchByPage(SearchDto dto);
	public List<NationalyDto> getAllNation();
	Boolean checkCode (UUID id,String code);
}
