package com.globits.da.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.globits.core.service.GenericService;
import com.globits.da.domain.CampaignType;
import com.globits.da.dto.CampaignTypeDto;
import com.globits.da.dto.search.SearchDto;

@Service
public interface CampaignTypeService extends GenericService<CampaignType, UUID>{
	public Page<CampaignTypeDto> getPage(int pageSize, int pageIndex);
	public CampaignTypeDto saveOrUpdate(UUID id,CampaignTypeDto dto);
	public Boolean deleteKho(UUID id);
	public CampaignTypeDto getCertificate(UUID id);
	Page<CampaignTypeDto> searchByPage(SearchDto dto);
	Boolean checkCode (UUID id,String code);
	public Boolean deleteCheckById(UUID id);
}
