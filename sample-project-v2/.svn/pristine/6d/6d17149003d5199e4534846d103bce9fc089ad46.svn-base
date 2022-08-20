package com.globits.sample.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;

import com.globits.core.service.GenericService;
import com.globits.sample.domain.Student;
import com.globits.sample.dto.StudentDto;

public interface StudentService extends GenericService<Student, UUID> {
	public List<StudentDto> getAll();
	public StudentDto saveStudent(StudentDto dto);
	
	public void saveList(List<StudentDto> list);
	public Page<StudentDto> getByPage(int pageIndex,int pageSize);
	Page<StudentDto> sqlQuerySample(int pageIndex, int pageSize);
	Page<StudentDto> sqlQuerySample(int pageIndex, int pageSize, String firstName);
	Page<StudentDto> sqlQueryByStudentClass(int pageIndex, int pageSize, String className);
}