package com.globits.da.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.globits.da.domain.Campaign;
import com.globits.da.domain.CampaignType;
import com.globits.da.dto.CampaignDto;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, UUID>{
	@Query("select new com.globits.da.dto.CampaignDto(ed) from Campaign ed")
	Page<CampaignDto> getListPage(Pageable pageable);
	@Query("select count(entity.id) from Campaign entity where entity.code =?1 and (entity.id <> ?2 or ?2 is null) ")
	Long checkCode(String code, UUID id);
	@Query("SELECT s FROM CampaignType s WHERE s.code = ?1")
	List<CampaignType> findCampaignTypeByCode(String code);
	@Query("SELECT s FROM CampaignType AS s JOIN Person AS p ON s.id = p.id "
			+ "JOIN User AS u ON p.user.id = u.id WHERE u.id =?1 ")
	CampaignType getCampaignType(Long id);
	
}
