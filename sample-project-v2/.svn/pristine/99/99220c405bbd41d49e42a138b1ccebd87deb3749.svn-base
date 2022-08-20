package com.globits.da.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.globits.da.domain.AdSpace;

public interface AdSpaceRepository extends JpaRepository<AdSpace, UUID> {
	@Query("select count(entity.id) from AdSpace entity where entity.name =?1 and (entity.id <> ?2 or ?2 is null) ")
	Long checkName(String name, UUID id);
}
