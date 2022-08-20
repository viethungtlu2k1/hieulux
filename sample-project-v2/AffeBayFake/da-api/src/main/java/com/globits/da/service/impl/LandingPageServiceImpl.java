package com.globits.da.service.impl;

import java.util.List;
import java.util.Optional;
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
import com.globits.da.domain.LandingPage;
import com.globits.da.dto.LandingPageDto;
import com.globits.da.dto.search.PagingSearchDto;
import com.globits.da.repository.LandingPageRepository;
import com.globits.da.service.LandingPageService;

@Service
public class LandingPageServiceImpl extends GenericServiceImpl<LandingPage, UUID> implements LandingPageService {
	@Autowired
	LandingPageRepository repos;
	
	@Override
	public Page<LandingPageDto> getPage(Integer page, Integer pageSize) {
		Pageable pageable = PageRequest.of(page, pageSize);
		Page<LandingPageDto> pageProduct = repos.getListPage(pageable);
		return pageProduct;
	}
	
	@Override
	public LandingPageDto saveOrUpdate(UUID id, LandingPageDto dto) {
		
		if(dto != null ) {
			LandingPage entity = null;
			if(dto.getId() !=null) {
				if (dto.getId() != null && !dto.getId().equals(id)) {
					return null;
				}
				entity = repos.getOne(dto.getId());
			}
			if(entity == null) {
				entity = new LandingPage();
			}
			entity.setCode(dto.getCode());
			entity.setName(dto.getName());
			entity.setProduct(dto.getProduct());
			entity.setUrl(dto.getUrl());
			entity.setType(dto.getType());
			entity.setIsShow(dto.getIsShow());
			entity = repos.save(entity);
			if (entity != null) {
				return new LandingPageDto(entity);
			}
		}
		return null;
	}
	
	@Override
	public LandingPageDto getById(UUID id) {
		LandingPage entity = repos.getOne(id);
		return new LandingPageDto(entity);
	}
	
	@Override
	public Boolean deleteById(UUID id) {
		repos.deleteById(id);;
		return true;
	}
	
	@Override
	public Page<LandingPageDto> searchByPage(PagingSearchDto dto) {
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
		
		String sqlCount = "select count(entity.id) from  LandingPage as entity where (1=1)   ";
		String sql = "select new com.globits.da.dto.LandingPageDto(entity) from  LandingPage as entity where (1=1)  ";

		if (dto.getKeyword() != null && StringUtils.hasText(dto.getKeyword())) {
			whereClause += " AND ( entity.name LIKE :text OR entity.code LIKE :text )";
		}

		
		sql += whereClause + orderBy;
		sqlCount += whereClause;

		Query q = manager.createQuery(sql, LandingPageDto.class);
		Query qCount = manager.createQuery(sqlCount);

		if (dto.getKeyword() != null && StringUtils.hasText(dto.getKeyword())) {
			q.setParameter("text", '%' + dto.getKeyword() + '%');
			qCount.setParameter("text", '%' + dto.getKeyword() + '%');
		}
		int startPosition = pageIndex * pageSize;
		q.setFirstResult(startPosition);
		q.setMaxResults(pageSize);
		List<LandingPageDto> entities = q.getResultList();
		long count = (long) qCount.getSingleResult();

		Pageable pageable = PageRequest.of(pageIndex, pageSize);
		Page<LandingPageDto> result = new PageImpl<LandingPageDto>(entities, pageable, count);
		return result;
	}
	
//	@Override
//	public Boolean checkCode(UUID id, String code) {
//		if(code != null && StringUtils.hasText(code)) {
//			Long count = productRepository.checkCode(code,id);
//				return count != 0l;
//			}
//		return null;
//	}
	
	@Override
	public Boolean checkCode(UUID id, String code) {
		if(code != null && StringUtils.hasText(code)) {
			if (id == null) return repos.existsByCode(code);
			Optional<LandingPage> productOptional = repos.findById(id);
			if (productOptional.isPresent() && productOptional.get().getCode().equals(code)) {
				return false;
			}
			return repos.existsByCode(code);
		}
		return null;
	}
}
