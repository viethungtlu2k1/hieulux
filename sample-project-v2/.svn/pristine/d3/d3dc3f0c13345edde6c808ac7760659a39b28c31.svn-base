package com.globits.da.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.globits.da.domain.Project;
import com.globits.da.dto.ProjectDto;

@Repository
public interface ProjectRepository extends JpaRepository<Project, UUID>{
	@Query("select count(entity.id) from Project entity where entity.code =?1 and (entity.id <> ?2 or ?2 is null) ")
	Long checkCode(String code, UUID id);
	@Query("select new com.globits.da.dto.ProjectDto(ed) from Project ed")
	Page<ProjectDto> getListPage( Pageable pageable);
	
	@Query("select new com.globits.da.dto.ProjectDto(ed) from Project ed")
	List<ProjectDto> getAllProject();

}
