package com.globits.sample.service;

import java.util.List;
import java.util.UUID;

import com.globits.core.service.GenericService;
import com.globits.sample.domain.Animal;
import com.globits.sample.dto.AnimalDto;
import com.globits.sample.dto.StudentDto;

public interface AnimalService extends GenericService<Animal, UUID> {
	public List<AnimalDto> getAll();
	public AnimalDto saveAnimal(AnimalDto dto);
	
	public void saveList(List<AnimalDto> list);
}