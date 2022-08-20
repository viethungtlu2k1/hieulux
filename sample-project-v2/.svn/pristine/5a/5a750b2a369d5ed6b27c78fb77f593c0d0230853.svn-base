package com.globits.da.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.globits.core.service.impl.GenericServiceImpl;
import com.globits.da.domain.ChannelAds;
import com.globits.da.dto.ChannelAdsDto;
import com.globits.da.dto.search.SearchDto;
import com.globits.da.repository.ChannelAdsRepository;
import com.globits.da.service.ChannelAdsService;

@Service
public class ChannelAdsServiceImpl extends GenericServiceImpl<ChannelAds, UUID> implements ChannelAdsService {

	@Autowired
	private ChannelAdsRepository channelRepos;

	@Override
	public Page<ChannelAdsDto> getPage(int pageSize, int pageIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChannelAdsDto saveOrUpdate(UUID id, ChannelAdsDto dto) {
		if (dto != null) {
			ChannelAds entity = null;
			if (dto.getId() != null) {
				entity = channelRepos.getOne(id);
			}
			if (entity == null) {
				entity = new ChannelAds();
			}

			entity.setCode(dto.getCode());
			entity.setName(dto.getName());

			entity = channelRepos.save(entity);

			if (entity == null) {
				return new ChannelAdsDto(entity);
			}
		}
		return null;
	}

	@Override
	public Boolean deleteKho(UUID id) {
		if (id != null) {
			channelRepos.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public ChannelAdsDto getCertificate(UUID id) {
		ChannelAds entity = channelRepos.getOne(id);
		if (entity != null) {
			return new ChannelAdsDto(entity);
		}
		return null;
	}

	@Override
	public Page<ChannelAdsDto> searchByPage(SearchDto dto) {
		if (dto == null) {
			return null;
		}

		int pageIndex = dto.getPageIndex();
		int pageSize = dto.getPageSize();

		if (pageIndex > 0) {
			pageIndex--;
		} else {
			pageIndex = 0;
		}

		String whereClause = "";

		String orderBy = " ORDER BY entity.createDate DESC";

		String sqlCount = "select count(entity.id) from  ChannelAds as entity where (1=1)   ";
		String sql = "select new com.globits.da.dto.ChannelAdsDto(entity) from ChannelAds as entity where (1=1)  ";

		if (dto.getKeyword() != null && StringUtils.hasText(dto.getKeyword())) {
			whereClause += " AND ( entity.name LIKE :text OR entity.code LIKE :text )";
		}

		sql += whereClause + orderBy;
		sqlCount += whereClause;

		Query q = manager.createQuery(sql, ChannelAdsDto.class);
		Query qCount = manager.createQuery(sqlCount);

		if (dto.getKeyword() != null && StringUtils.hasText(dto.getKeyword())) {
			q.setParameter("text", '%' + dto.getKeyword() + '%');
			qCount.setParameter("text", '%' + dto.getKeyword() + '%');
		}

		int startPosition = pageIndex * pageSize;
		q.setFirstResult(startPosition);
		q.setMaxResults(pageSize);

		@SuppressWarnings("unchecked")
		List<ChannelAdsDto> entities = q.getResultList();
		long count = (long) qCount.getSingleResult();
		Pageable pageable = PageRequest.of(pageIndex, pageSize);
		Page<ChannelAdsDto> result = new PageImpl<ChannelAdsDto>(entities, pageable, count);
		return result;
	}

	@Override
	public Boolean checkCode(UUID id, String code) {
		if (code != null && StringUtils.hasText(code)) {
			Long count = channelRepos.checkCode(code, id);
			return count != 0l;
		}
		return null;
	}

	@Override
	public List<ChannelAdsDto> getAllChannelAds() {
		List<ChannelAds> list = channelRepos.findAll();
		List<ChannelAdsDto> dtos = new ArrayList<>();
		for (ChannelAds item : list) {
			ChannelAdsDto dto = new ChannelAdsDto(item);
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public Boolean deleteCheckById(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

}
