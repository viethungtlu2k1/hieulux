package com.globits.da.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import javax.persistence.Query;

import com.globits.core.service.impl.GenericServiceImpl;
import com.globits.da.domain.Currency;
import com.globits.da.dto.CurrencyDTO;
import com.globits.da.dto.search.CurrencySearchDTO;
import com.globits.da.repository.CurrencyRepository;
import com.globits.da.service.CurrencyService;
import org.springframework.data.domain.Pageable;
@Service
public class CurrencyServiceImpl extends GenericServiceImpl<Currency, UUID> implements CurrencyService {

	@Autowired
	CurrencyRepository repo;
	@Override
	public Page<CurrencyDTO> getPage(int pageSize, int pageIndex) {
		Pageable pageable = PageRequest.of(pageIndex-1, pageSize);
		return repo.getListPage(pageable);
	}

	@Override
	public CurrencyDTO saveOrUpdate(UUID id, CurrencyDTO dto) {
		if (dto != null) {
			Currency entity = null;
			if (dto.getId()!= null) {
				if (dto.getId() != null && !dto.getId().equals(id)) {
					return null;
				
				}
				entity = repo.getOne(dto.getId());
			}
			if (entity == null) {
				entity = new Currency();
			}
			entity.setName(dto.getName());
			entity.setCode(dto.getCode());
			entity.setCurrencySymbols(dto.getCurrencySymbols());
			entity= repo.save(entity);
			if (entity != null) {
				return new CurrencyDTO(entity);
			}
		}
		return null;
	}

	@Override
	public Page<CurrencyDTO> searchByPage(CurrencySearchDTO dto) {
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
		
		String orderBy = " ORDER BY currency.code DESC";
		
		String sqlCount = "select count(currency.id) from  Currency as currency where (1=1)   ";
		String sql = "select new com.globits.da.dto.CurrencyDTO(currency) from  Currency as currency where (1=1)  ";

		if (dto.getKeyword() != null && StringUtils.hasText(dto.getKeyword())) {
			whereClause += " AND ( currency.name LIKE :text OR currency.code LIKE :text )";
		}
		sql += whereClause + orderBy;
		sqlCount += whereClause;

		Query q = manager.createQuery(sql, CurrencyDTO.class);
		Query qCount = manager.createQuery(sqlCount);

		if (dto.getKeyword() != null && StringUtils.hasText(dto.getKeyword())) {
			q.setParameter("text", '%' + dto.getKeyword() + '%');
			qCount.setParameter("text", '%' + dto.getKeyword() + '%');
		}
		int startPosition = pageIndex * pageSize;
		q.setFirstResult(startPosition);
		q.setMaxResults(pageSize);
		List<CurrencyDTO> entities = q.getResultList();
		long count = (long) qCount.getSingleResult();

		Pageable pageable = PageRequest.of(pageIndex, pageSize);
		Page<CurrencyDTO> result = new PageImpl<CurrencyDTO>(entities, pageable, count);
		return result;
	}

	@Override
	public Boolean checkCode(UUID id, String code) {
		if(code != null && StringUtils.hasText(code)) {
			Long count = repo.checkCode(code,id);
				return count != 0l;
			}
		return null;
	}

	@Override
	public Boolean deleteCheckById(UUID id) {
		if (id != null) {
			repo.deleteById(id);
			return true;
		}
		return false;
	}
	@Override
	public CurrencyDTO getCertificate(UUID id) {
		Currency entity = repo.getOne(id);
		if (entity != null) {
			return new CurrencyDTO(entity);
		}
		return null;
	}

	@Override
	public List<CurrencyDTO> getAll() {
		List<CurrencyDTO> currencyDTOs = repo.getAllCurrency();
		return currencyDTOs;
	}

}
