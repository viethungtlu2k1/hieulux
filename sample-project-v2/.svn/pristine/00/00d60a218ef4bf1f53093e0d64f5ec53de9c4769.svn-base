package com.globits.da.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.globits.da.domain.DetailNationalyAdSpace;

@Repository
public interface DetailAdSpaceNationalyRepository extends JpaRepository<DetailNationalyAdSpace, UUID> {
	List<DetailNationalyAdSpace> findAllByAdSpaceId(UUID id);

	@Transactional
	@Modifying
	@Query("Delete from DetailNationalyAdSpace entity WHERE entity.adSpace.id =?1")
	public void deleteByAdSpaceId(UUID id);
	
	@Transactional
	@Modifying
	@Query("Delete from DetailNationalyAdSpace entity WHERE entity.nationaly.id =?1")
	public void deleteByNationalyId(UUID id);
}
