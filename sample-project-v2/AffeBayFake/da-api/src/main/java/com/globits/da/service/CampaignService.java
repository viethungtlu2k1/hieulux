package com.globits.da.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.globits.core.service.GenericService;
import com.globits.da.domain.Campaign;
import com.globits.da.dto.CampaignDto;
import com.globits.da.dto.search.SearchDto;

@Service
public interface CampaignService extends GenericService<Campaign, UUID>{
	public Page<CampaignDto> getPage(int pageSize, int pageIndex);
	public CampaignDto saveOrUpdate(UUID id, CampaignDto dto);
	public Boolean deleteKho(UUID id);
	public CampaignDto getCertificate(UUID id);
	Page<CampaignDto> searchByPage(SearchDto dto);
	Boolean checkCode (UUID id,String code);
	public Boolean deleteCheckById(UUID id);
}
