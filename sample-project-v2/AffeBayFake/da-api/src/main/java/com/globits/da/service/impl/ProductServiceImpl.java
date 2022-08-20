package com.globits.da.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.UUID;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.globits.core.service.impl.GenericServiceImpl;
import com.globits.da.domain.Product;
import com.globits.da.dto.ProductDto;
import com.globits.da.dto.search.ProductSearchByPageDto;
import com.globits.da.dto.search.ProductSearchDto;
import com.globits.da.repository.ProductRepository;
import com.globits.da.service.ProductService;

@Service
public class ProductServiceImpl extends GenericServiceImpl<Product, UUID> implements ProductService {
	@Autowired
	ProductRepository productRepository;
	
	@Override
	public Page<ProductDto> getPage(Integer page, Integer pageSize) {
		Pageable pageable = PageRequest.of(page, pageSize);
		Page<ProductDto> pageProduct = productRepository.getListPage(pageable);
		return pageProduct;
	}
	
	@Override
	public ProductDto saveOrUpdate(UUID id, ProductDto dto) {
		
		if(dto != null ) {
			Product entity = null;
			if(dto.getId() !=null) {
				if (dto.getId() != null && !dto.getId().equals(id)) {
					return null;
				}
				entity = productRepository.getOne(dto.getId());
			}
			if(entity == null) {
				entity = new Product();
			}
			entity.setCode(dto.getCode());
			entity.setName(dto.getName());
			entity.setPrice(dto.getPrice());
			entity.setMainImageUrl(dto.getMainImageUrl());
			entity.setPayout(dto.getPayout());
			entity.setNationaly(dto.getNationaly());
			entity.setCampaignType(dto.getCampaignType());
			entity.setCategory(dto.getCategory());
			entity.setCurrencyPrice(dto.getCurrencyPrice());
			entity.setCurrencyPayout(dto.getCurrencyPayout());
			entity.setDescription(dto.getDescription());
			entity = productRepository.save(entity);
			if (entity != null) {
				return new ProductDto(entity);
			}
		}
		return null;
	}
	
	@Override
	public ProductDto getProductById(UUID id) {
		Product entity = productRepository.getOne(id);
		return new ProductDto(entity);
	}
	
	@Override
	public Boolean deleteProduct(UUID id) {
		productRepository.deleteById(id);;
		return true;
	}
	
	@Override
	  public Boolean deleteManyProduct(List<UUID> listProductId) {
	    productRepository.deleteProductWithIds(listProductId);
	    return true;
	  }
	
	@Override
	public Page<ProductDto> searchByPage(ProductSearchByPageDto dto) {
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
		
		String sqlCount = "select count(entity.id) from  Product as entity where (1=1)   ";
		String sql = "select new com.globits.da.dto.ProductDto(entity) from  Product as entity where (1=1)  ";

		if (dto.getKeyword() != null && StringUtils.hasText(dto.getKeyword())) {
			whereClause += " AND ( entity.name LIKE :text OR entity.code LIKE :text )";
		}

		
		sql += whereClause + orderBy;
		sqlCount += whereClause;

		Query q = manager.createQuery(sql, ProductDto.class);
		Query qCount = manager.createQuery(sqlCount);

		if (dto.getKeyword() != null && StringUtils.hasText(dto.getKeyword())) {
			q.setParameter("text", '%' + dto.getKeyword() + '%');
			qCount.setParameter("text", '%' + dto.getKeyword() + '%');
		}
		int startPosition = pageIndex * pageSize;
		q.setFirstResult(startPosition);
		q.setMaxResults(pageSize);
		List<ProductDto> entities = q.getResultList();
		long count = (long) qCount.getSingleResult();

		Pageable pageable = PageRequest.of(pageIndex, pageSize);
		Page<ProductDto> result = new PageImpl<ProductDto>(entities, pageable, count);
		return result;
	}
	
	@Override
	public Page<ProductDto> filterByPage(ProductSearchDto dto) {
		if (dto == null) {
			return null;
		}
		String sql = "select new com.globits.da.dto.ProductDto(p) from Product p";
		String sqlCount = "select count(p.id) from Product p";
		String orderBy = " order by p."+ dto.getSortBy() +" "+ dto.getSortDirection();
		StringJoiner whereClouse = new StringJoiner(" and ", " where ", "");
		if (dto.getKeyword() != null && StringUtils.hasText(dto.getKeyword())) {
			whereClouse.add("(p.name like :text or p.code like :text)");
		}
		if (dto.getCategory() != null) {
			whereClouse.add("exists (select ca from p.category ca where ca.id = :categoryId)");
		}
		if (dto.getNationaly() != null) {
			whereClouse.add("exists (select na from p.nationaly na where na.id = :nationalyId)");
		}
		if (dto.getCurrencyPrice() != null) {
			whereClouse.add("exists (select ce from p.currencyPrice ce where ce.id = :currencyPriceId)");
		}
		if (dto.getCurrencyPayout() != null) {
			whereClouse.add("exists (select ct from p.currencyPayout ct where ct.id = :currencyPayoutId)");
		}
		if (whereClouse.length() > 7) {
			sql += whereClouse.toString();
			sqlCount += whereClouse.toString();
		}
		sql += orderBy;		
		Query query = manager.createQuery(sql, ProductDto.class);
		Query queryCount = manager.createQuery(sqlCount);
		if (dto.getKeyword() != null && StringUtils.hasText(dto.getKeyword())) {
			query.setParameter("text", "%"+dto.getKeyword()+"%");
			queryCount.setParameter("text", "%"+dto.getKeyword()+"%");
		}
		if (dto.getCategory() != null) {
			query.setParameter("categoryId", dto.getCategory().getId());
			queryCount.setParameter("categoryId", dto.getCategory().getId());
		}
		if (dto.getNationaly() != null) {
			query.setParameter("nationalyId", dto.getNationaly().getId());
			queryCount.setParameter("nationalyId", dto.getNationaly().getId());
		}
		if (dto.getCurrencyPrice() != null) {
			query.setParameter("currencyPriceId", dto.getCurrencyPrice().getId());
			queryCount.setParameter("currencyPriceId", dto.getCurrencyPrice().getId());
		}
		if (dto.getCurrencyPayout() != null) {
			query.setParameter("currencyPayoutId", dto.getCurrencyPayout().getId());
			queryCount.setParameter("currencyPayoutId", dto.getCurrencyPayout().getId());
		}
		query.setFirstResult(dto.getPageIndex() * dto.getPageSize());
		query.setMaxResults(dto.getPageSize());
		List<ProductDto> productEntitys = query.getResultList();
		long countEntity = (long) queryCount.getSingleResult();
		Pageable pageable = PageRequest.of(dto.getPageIndex(), dto.getPageSize());
		Page<ProductDto> result = new PageImpl<ProductDto>(productEntitys, pageable, countEntity);
		return result;
	}
	
	@Override
	public Page<ProductDto> search(ProductSearchDto productSearchDto) {
		Page<ProductDto> productPage = productRepository.findByNameContaining(productSearchDto.getKeyword(), productSearchDto.pagingAndSorting());
		return productPage;
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
			if (id == null) return productRepository.existsByCode(code);
			Optional<Product> productOptional = productRepository.findById(id);
			if (productOptional.isPresent() && productOptional.get().getCode().equals(code)) {
				return false;
			}
			return productRepository.existsByCode(code);
		}
		return null;
	}
	
	@Override
	public ProductDto updateImage(String mainImageUrl, UUID id) {
		Product entity = productRepository.getOne(id);
		entity.setMainImageUrl(mainImageUrl);
		entity = productRepository.save(entity);

		return new ProductDto(entity);
	}
}
