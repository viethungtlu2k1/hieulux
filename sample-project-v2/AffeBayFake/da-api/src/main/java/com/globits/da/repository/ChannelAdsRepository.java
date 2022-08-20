package com.globits.da.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.globits.da.domain.ChannelAds;

@Repository
public interface ChannelAdsRepository extends JpaRepository<ChannelAds, UUID> {
	@Query("select count(entity.id) from ChannelAds entity where entity.code =?1 and (entity.id <> ?2 or ?2 is null) ")
	Long checkCode(String code, UUID id);
}
