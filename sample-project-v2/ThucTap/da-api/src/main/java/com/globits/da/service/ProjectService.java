package com.globits.da.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.globits.core.service.GenericService;
import com.globits.da.domain.Project;
import com.globits.da.dto.ProjectDto;
import com.globits.da.dto.search.SearchDto;
@Service
public interface ProjectService extends GenericService<Project, UUID>{
	public Page<ProjectDto> getPage(int pageSize, int pageIndex);
	public ProjectDto saveOrUpdate(UUID id,ProjectDto dto);
	public Boolean deleteKho(UUID id);
	public ProjectDto getCertificate(UUID id);
	Page<ProjectDto> searchByPage(SearchDto dto);
	Boolean checkCode (UUID id,String code);
	public Boolean deleteCheckById(UUID id);
}
