package com.globits.da.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.globits.da.domain.DetailCategoryAdSpace;

@Repository
public interface DetailAdSpaceCategoryRepository extends JpaRepository<DetailCategoryAdSpace, UUID> {
	List<DetailCategoryAdSpace> findAllByAdSpaceId(UUID id);
	
	@Transactional
	@Modifying
	@Query("Delete from DetailCategoryAdSpace entity WHERE entity.adSpace.id =?1")
	public void deleteByAdSpaceId(UUID id);
}
