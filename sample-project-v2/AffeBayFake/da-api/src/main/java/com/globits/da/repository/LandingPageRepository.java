package com.globits.da.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.globits.da.domain.LandingPage;
import com.globits.da.dto.LandingPageDto;

@Repository
public interface LandingPageRepository extends JpaRepository<LandingPage, UUID> {
	Page<LandingPageDto> findByNameOrCodeContaining(String search, Pageable paging);
	
	Page<LandingPageDto> findByNameContaining(String search, Pageable paging);
	
	Boolean existsByCode(String code);
	
	Boolean existsByIdAndCode(UUID id, String code);
	
	@Query("select count(entity.id) from LandingPage entity where entity.code =?1 and (entity.id <> ?2 or ?2 is null) ")
	Long checkCode(String code, UUID id);
	
	@Query("select new com.globits.da.dto.LandingPageDto(ed) from LandingPage ed")
	Page<LandingPageDto> getListPage(Pageable pageable);
}
