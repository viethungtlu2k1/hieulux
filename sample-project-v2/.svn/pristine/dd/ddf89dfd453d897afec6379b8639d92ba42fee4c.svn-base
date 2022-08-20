package com.globits.sample.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.globits.sample.domain.StudentClass;
import com.globits.sample.dto.StudentClassDto;
import com.globits.sample.repository.StudentClassRepository;
import com.globits.sample.service.StudentClassService;

@Transactional
@Service
public class StudentClassServiceImpl implements StudentClassService{
	@Autowired
	StudentClassRepository repository;
	@Override
	public StudentClass delete(UUID id) {
		StudentClass animal = repository.getOne(id);
		if(animal!=null) {
			repository.delete(animal);
			return animal;
		}
		return null;
	}

	@Override
	public StudentClass findById(UUID id) {
		return repository.getOne(id);
	}

	@Override
	public Page<StudentClass> getList(int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StudentClass save(StudentClass t) {
		return repository.save(t);
	}

	@Override
	public List<StudentClassDto> getAll() {
		return repository.getAll();
	}

	@Override
	public StudentClassDto saveStudentClass(StudentClassDto dto) {
		StudentClass animal =null;
		if(dto.getId()!=null)
			animal = repository.getOne(dto.getId());
		if(animal==null) {
			animal = new StudentClass();
			animal.setId(UUID.randomUUID());
		}
		animal.setCode(dto.getCode());
		animal = repository.save(animal);
		return new StudentClassDto(animal);
	}

	@Override
	public void saveList(List<StudentClassDto> list) {
		for(int i=0;i<list.size();i++) {
			StudentClassDto s = list.get(i);
			saveStudentClass(s);
		}
	}


}
