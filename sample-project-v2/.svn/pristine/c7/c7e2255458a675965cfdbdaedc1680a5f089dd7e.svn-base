package com.globits.da.service.impl;

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
import com.globits.da.domain.CampaignType;
import com.globits.da.dto.CampaignTypeDto;
import com.globits.da.dto.search.SearchDto;
import com.globits.da.repository.CampaignTypeRepository;
import com.globits.da.service.CampaignTypeService;

@Service
public class CampaignTypeServiceImpl extends GenericServiceImpl<CampaignType, UUID> implements CampaignTypeService{
	@Autowired
	CampaignTypeRepository repos;
	 
	@Override
	public Page<CampaignTypeDto> getPage(int pageSize, int pageIndex) {
		Pageable pageable = PageRequest.of(pageIndex-1, pageSize);
		return repos.getListPage(pageable);
	}
	@Override
	public CampaignTypeDto saveOrUpdate(UUID id, CampaignTypeDto dto) {
		if(dto != null ) {
			CampaignType entity = null;
			if(dto.getId() !=null) {
				if (dto.getId() != null && !dto.getId().equals(id)) {
					return null;
				}
				entity =  repos.getOne(dto.getId());
			}
			if(entity == null) {
				entity = new CampaignType();
			}
			entity.setCode(dto.getCode());
			entity.setName(dto.getName());
			entity.setDescription(dto.getDescription());
			
			entity = repos.save(entity);
			if (entity != null) {
				return new CampaignTypeDto(entity);
			}
			}
			return null;
	}

	@Override
	public Boolean deleteKho(UUID id) {
		if(id!=null) {
			repos.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public CampaignTypeDto getCertificate(UUID id) {
		CampaignType entity = repos.getOne(id);
		if(entity!=null) {
			return new CampaignTypeDto(entity);
		}
		return null;
	}

	@Override
	public Page<CampaignTypeDto> searchByPage(SearchDto dto) {
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
		
		String sqlCount = "select count(entity.id) from  CampaignType as entity where (1=1)   ";
		String sql = "select new com.globits.da.dto.CampaignTypeDto(entity) from  CampaignType as entity where (1=1)  ";

		if (dto.getKeyword() != null && StringUtils.hasText(dto.getKeyword())) {
			whereClause += " AND ( entity.name LIKE :text OR entity.code LIKE :text )";
		}

		
		sql += whereClause + orderBy;
		sqlCount += whereClause;

		Query q = manager.createQuery(sql, CampaignTypeDto.class);
		Query qCount = manager.createQuery(sqlCount);

		if (dto.getKeyword() != null && StringUtils.hasText(dto.getKeyword())) {
			q.setParameter("text", '%' + dto.getKeyword() + '%');
			qCount.setParameter("text", '%' + dto.getKeyword() + '%');
		}
		int startPosition = pageIndex * pageSize;
		q.setFirstResult(startPosition);
		q.setMaxResults(pageSize);
		List<CampaignTypeDto> entities = q.getResultList();
		long count = (long) qCount.getSingleResult();

		Pageable pageable = PageRequest.of(pageIndex, pageSize);
		Page<CampaignTypeDto> result = new PageImpl<CampaignTypeDto>(entities, pageable, count);
		return result;
	}

	@Override
	public Boolean checkCode(UUID id, String code) {
		if(code != null && StringUtils.hasText(code)) {
			Long count = repos.checkCode(code,id);
				return count != 0l;
			}
		return null;
	}

	@Override
	public Boolean deleteCheckById(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}
	 

}
