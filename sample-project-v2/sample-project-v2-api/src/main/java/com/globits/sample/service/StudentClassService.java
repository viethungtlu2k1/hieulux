package com.globits.sample.service;

import java.util.List;
import java.util.UUID;

import com.globits.core.service.GenericService;
import com.globits.sample.domain.StudentClass;
import com.globits.sample.dto.StudentClassDto;

public interface StudentClassService extends GenericService<StudentClass, UUID> {
	public List<StudentClassDto> getAll();
	public StudentClassDto saveStudentClass(StudentClassDto dto);
	
	public void saveList(List<StudentClassDto> list);
}