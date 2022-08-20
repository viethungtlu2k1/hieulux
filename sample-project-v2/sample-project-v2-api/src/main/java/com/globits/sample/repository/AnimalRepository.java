package com.globits.sample.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.globits.sample.domain.Animal;
import com.globits.sample.dto.AnimalDto;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, UUID> {
	@Query("select new com.globits.sample.dto.AnimalDto(d) from Animal d")
	public List<AnimalDto> getAll();
}
