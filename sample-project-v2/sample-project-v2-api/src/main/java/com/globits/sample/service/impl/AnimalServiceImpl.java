package com.globits.sample.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.globits.sample.domain.Animal;
import com.globits.sample.dto.AnimalDto;
import com.globits.sample.repository.AnimalRepository;
import com.globits.sample.service.AnimalService;

@Transactional
@Service
public class AnimalServiceImpl implements AnimalService{
	@Autowired
	AnimalRepository repository;
	@Override
	public Animal delete(UUID id) {
		Animal animal = repository.getOne(id);
		if(animal!=null) {
			repository.delete(animal);
			return animal;
		}
		return null;
	}

	@Override
	public Animal findById(UUID id) {
		return repository.getOne(id);
	}

	@Override
	public Page<Animal> getList(int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Animal save(Animal t) {
		return repository.save(t);
	}

	@Override
	public List<AnimalDto> getAll() {
		return repository.getAll();
	}

	@Override
	public AnimalDto saveAnimal(AnimalDto dto) {
		Animal animal =null;
		if(dto.getId()!=null)
			animal = repository.getOne(dto.getId());
		if(animal==null) {
			animal = new Animal();
			animal.setId(UUID.randomUUID());
		}
		animal.setCode(dto.getCode());
		animal = repository.save(animal);
		return new AnimalDto(animal);
	}

	@Override
	public void saveList(List<AnimalDto> list) {
		for(int i=0;i<list.size();i++) {
			AnimalDto s = list.get(i);
			saveAnimal(s);
		}
	}


}
