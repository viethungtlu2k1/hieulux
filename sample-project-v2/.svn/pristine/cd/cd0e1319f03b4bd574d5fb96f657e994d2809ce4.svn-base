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
import com.globits.da.domain.Nationaly;
import com.globits.da.dto.CategoryDto;
import com.globits.da.dto.NationalyDto;
import com.globits.da.dto.search.SearchDto;
import com.globits.da.repository.NationalyRepository;
import com.globits.da.service.NationalyService;

@Service
public class NationalyServiceImpl extends GenericServiceImpl<Nationaly, UUID> implements NationalyService{
	
	@Autowired
	NationalyRepository res;
	
	@Override
	public Page<NationalyDto> GetPage(int pagesize, int pageindex) {
		Pageable pageable = PageRequest.of(pageindex - 1, pagesize);
		return res.getListPage(pageable);
	}

	@Override
	public NationalyDto SaveOrUpdate(UUID id, NationalyDto dto) {
		if(dto != null) {
			Nationaly entity = null;
			if(dto.getId() != null) {
				if(dto.getId() != null && !dto.getId().equals(id))
					return null;
				entity = res.getOne(dto.getId());
			}
			if(entity == null) {
				entity = new Nationaly();
			}
			entity.setCode(dto.getCode());
			entity.setName(dto.getName());
			
			entity = res.save(entity);
			if (entity != null) {
				return new NationalyDto(entity);
			}
		}
			return null;
	}

	@Override
	public Boolean DeleteNation(UUID id) {
		if(id != null)
		{
			res.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public NationalyDto GetOneEntity(UUID id) {
		if(id != null) {
			Nationaly nation = res.getOne(id);
			return new NationalyDto(nation);
		}
		return null;
	}

	@Override
	public Page<NationalyDto> searchByPage(SearchDto dto) {
		if(dto == null)
			return null;
		int pageIndex = dto.getPageIndex();
		int pageSize = dto.getPageSize();
		if(pageIndex >0)
			pageIndex = pageIndex - 1;
		else
			pageIndex = 0;
		String whereClause = "";
		String orderBy = " ORDER BY entity.createDate DESC";
		String sqlCount = "select count(entity.id) from  Nationaly as entity where (1=1)   ";
		String sql = "select new com.globits.da.dto.NationalyDto(entity) from  Nationaly as entity where (1=1)  ";
		
		if (dto.getKeyword() != null && StringUtils.hasText(dto.getKeyword())) {
			whereClause += " AND ( entity.name LIKE :text OR entity.code LIKE :text )";
		}
		sql += whereClause + orderBy;
		sqlCount += whereClause;
		
		Query q = manager.createQuery(sql, NationalyDto.class);
		Query qCount = manager.createQuery(sqlCount);

		if (dto.getKeyword() != null && StringUtils.hasText(dto.getKeyword())) {
			q.setParameter("text", '%' + dto.getKeyword() + '%');
			qCount.setParameter("text", '%' + dto.getKeyword() + '%');
		}
		int startPosition = pageIndex * pageSize;
		q.setFirstResult(startPosition);
		q.setMaxResults(pageSize);
		List<NationalyDto> entities = q.getResultList();
		long count = (long) qCount.getSingleResult();

		Pageable pageable = PageRequest.of(pageIndex, pageSize);
		Page<NationalyDto> result = new PageImpl<NationalyDto>(entities, pageable, count);
		return result;
	}

	@Override
	public List<NationalyDto> getAllNation() {
		List<NationalyDto> getAllNation = res.getAllNation();
		return getAllNation;
	}

	@Override
	public Boolean checkCode(UUID id, String code) {
		if(code != null && StringUtils.hasText(code)) {
			Long count = res.checkCode(code,id);
				return count != 0l;
			}
		return null;
	}



}
