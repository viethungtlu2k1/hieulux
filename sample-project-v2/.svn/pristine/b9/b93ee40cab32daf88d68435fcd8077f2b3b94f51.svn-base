package com.globits.sample.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.globits.sample.domain.StudentClass;
import com.globits.sample.dto.StudentClassDto;

@Repository
public interface StudentClassRepository extends JpaRepository<StudentClass, UUID> {
	@Query("select new com.globits.sample.dto.StudentClassDto(c) from StudentClass c")
	public List<StudentClassDto> getAll();
}
