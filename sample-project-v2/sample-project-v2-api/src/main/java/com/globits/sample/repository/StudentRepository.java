package com.globits.sample.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.globits.sample.domain.Student;
import com.globits.sample.dto.StudentDto;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {
	@Query("select new com.globits.sample.dto.StudentDto(d) from Student d")
	public List<StudentDto> getAll();
	
	@Query("select new com.globits.sample.dto.StudentDto(d) from Student d")
	public Page<StudentDto> getList(Pageable page);
}
