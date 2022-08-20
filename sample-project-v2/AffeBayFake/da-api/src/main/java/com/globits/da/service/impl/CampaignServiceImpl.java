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
import com.globits.da.domain.Campaign;
import com.globits.da.domain.CampaignType;
import com.globits.da.domain.Product;
import com.globits.da.dto.CampaignDto;
import com.globits.da.dto.search.SearchDto;
import com.globits.da.repository.CampaignRepository;
import com.globits.da.repository.CampaignTypeRepository;
import com.globits.da.repository.ProductRepository;
import com.globits.da.service.CampaignService;
import com.globits.security.service.UserService;

@Service
public class CampaignServiceImpl extends GenericServiceImpl<Campaign, UUID> implements CampaignService{
	@Autowired
	CampaignRepository repos;
	
	@Autowired
	UserService userService;
	
	@Autowired
	CampaignTypeRepository repo;
	
	@Autowired
	ProductRepository ropo;
	
	@Override
	public Page<CampaignDto> getPage(int pageSize, int pageIndex) {
		Pageable pageable = PageRequest.of(pageIndex-1, pageSize);
		return repos.getListPage(pageable);
	}
	@Override
	public CampaignDto saveOrUpdate(UUID id, CampaignDto dto) {
			if(dto !=null) {
				Campaign entity = null;
				CampaignType camT = null;
				Product pro = null;
				if(dto.getId() !=null) {
					if (dto.getId() != null && !dto.getId().equals(id)) {
						return null;
					}
					entity =  repos.getOne(dto.getId());
				}
//				if(id != null) {
//					entity =  repos.findById(id).get();
//				}
				
				if(entity == null) {
					entity = new Campaign();
					
				}
				entity.setCode(dto.getCode());
				entity.setName(dto.getName());
				
				if(dto.getCampaignType() != null && dto.getCampaignType().getId() != null) {
					camT = repo.findById(dto.getCampaignType().getId()).get();
				}
				entity.setCampaignType(camT);
				entity.setStartDate(dto.getStartDate());
				entity.setEndDate(dto.getEndDate());
				entity.setEpc(dto.getEpc());
				entity.setCvr(dto.getCvr());
				entity.setCommission(dto.getCommission());
				entity.setApproved_rate_30days(dto.getApproved_rate_30days());
				if(dto.getProduct() != null && dto.getProduct().getId() != null) {
					pro = ropo.findById(dto.getProduct().getId()).get();
				}
				entity.setProduct(pro);
				
				entity = repos.save(entity);
				if(entity != null) {
					return new CampaignDto(entity);
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
	public CampaignDto getCertificate(UUID id) {
		Campaign entity = repos.getOne(id);
		if(entity!=null) {
			return new CampaignDto(entity);
		}
		return null;
	}

	@Override
	public Page<CampaignDto> searchByPage(SearchDto dto) {
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
		
		String sqlCount = "select count(entity.id) from  Campaign as entity where (1=1)   ";
		String sql = "select new com.globits.da.dto.CampaignDto(entity) from  Campaign as entity where (1=1)  ";

		if (dto.getKeyword() != null && StringUtils.hasText(dto.getKeyword())) {
			whereClause += " AND ( entity.name LIKE :text OR entity.code LIKE :text )";
		}

		
		sql += whereClause + orderBy;
		sqlCount += whereClause;

		Query q = manager.createQuery(sql, CampaignDto.class);
		Query qCount = manager.createQuery(sqlCount);

		if (dto.getKeyword() != null && StringUtils.hasText(dto.getKeyword())) {
			q.setParameter("text", '%' + dto.getKeyword() + '%');
			qCount.setParameter("text", '%' + dto.getKeyword() + '%');
		}
		int startPosition = pageIndex * pageSize;
		q.setFirstResult(startPosition);
		q.setMaxResults(pageSize);
		List<CampaignDto> entities = q.getResultList();
		long count = (long) qCount.getSingleResult();

		Pageable pageable = PageRequest.of(pageIndex, pageSize);
		Page<CampaignDto> result = new PageImpl<CampaignDto>(entities, pageable, count);
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
