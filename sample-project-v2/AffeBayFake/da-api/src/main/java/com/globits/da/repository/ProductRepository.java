package com.globits.da.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.globits.da.domain.Category;
import com.globits.da.domain.Currency;
import com.globits.da.domain.Nationaly;
import com.globits.da.domain.Product;
import com.globits.da.dto.ProductDto;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
	
	Page<ProductDto> findByNameOrCodeContaining(String search, Pageable paging);
	
	Page<ProductDto> findByNameContaining(String search, Pageable paging);
	
	Boolean existsByCode(String code);
	
	Boolean existsByIdAndCode(UUID id, String code);
	
	@Query("select count(entity.id) from Product entity where entity.code =?1 and (entity.id <> ?2 or ?2 is null) ")
	Long checkCode(String code, UUID id);
	
	@Query("select new com.globits.da.dto.ProductDto(ed) from Product ed")
	Page<ProductDto> getListPage(Pageable pageable);
	
	@Modifying
	@Query("delete from Product e where e.id in ?1")
	void deleteProductWithIds(List<UUID> listEmployeeId);
	
	
}
