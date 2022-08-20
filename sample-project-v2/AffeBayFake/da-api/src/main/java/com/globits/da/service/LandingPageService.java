package com.globits.da.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.globits.core.service.GenericService;
import com.globits.da.domain.LandingPage;
import com.globits.da.dto.LandingPageDto;
import com.globits.da.dto.search.PagingSearchDto;

@Service
public interface LandingPageService extends GenericService<LandingPage, UUID> {
	public Page<LandingPageDto> getPage(Integer page, Integer pageSize);
	public LandingPageDto saveOrUpdate(UUID id,LandingPageDto dto);
	public LandingPageDto getById(UUID id);
	public Boolean deleteById(UUID id);
	public Page<LandingPageDto> searchByPage(PagingSearchDto productSearchDto);
	Boolean checkCode (UUID id,String code);
}
