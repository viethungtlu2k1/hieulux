package com.globits.da.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.globits.da.dto.ChannelAdsDto;
import com.globits.da.dto.search.SearchDto;

@Service
public interface ChannelAdsService {
	public Page<ChannelAdsDto> getPage(int pageSize, int pageIndex);

	public ChannelAdsDto saveOrUpdate(UUID id, ChannelAdsDto dto);

	public Boolean deleteKho(UUID id);

	public ChannelAdsDto getCertificate(UUID id);

	Page<ChannelAdsDto> searchByPage(SearchDto dto);

	Boolean checkCode(UUID id, String code);

	public List<ChannelAdsDto> getAllChannelAds();

	public Boolean deleteCheckById(UUID id);
}
