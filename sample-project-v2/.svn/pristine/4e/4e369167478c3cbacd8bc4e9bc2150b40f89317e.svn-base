package com.globits.sample.service.impl;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.globits.sample.domain.Student;
import com.globits.sample.domain.StudentClass;
import com.globits.sample.dto.StudentDto;
import com.globits.sample.repository.StudentClassRepository;
import com.globits.sample.repository.StudentRepository;
import com.globits.sample.service.StudentService;

@Transactional
@Service
public class StudentServiceImpl implements StudentService{
	@Autowired
	StudentRepository repository;
	@Autowired
	StudentClassRepository studentClassRepository;
	
	 @Autowired 
	 private EntityManager entityManager;
	 
	@Override
	public Student delete(UUID id) {
		Student student = repository.getOne(id);
		if(student!=null) {
			repository.delete(student);
			return student;
		}
		return null;
	}

	@Override
	public Student findById(UUID id) {
		// TODO Auto-generated method stub
		return repository.getOne(id);
	}

	@Override
	public Page<StudentDto> getByPage(int pageIndex, int pageSize) {
		Pageable pageable = PageRequest.of(pageIndex - 1, pageSize);
		return repository.getList(pageable);
	}

	@Override
	public Student save(Student t) {
		return repository.save(t);
	}

	@Override
	public List<StudentDto> getAll() {
		return repository.getAll();
	}

	@Override
	public StudentDto saveStudent(StudentDto dto) {
		Student student =null;
		if(dto.getId()!=null)
			student = repository.getOne(dto.getId());
		if(student==null) {
			student = new Student();
			student.setId(UUID.randomUUID());
		}
		student.setCode(dto.getCode());
		student.setFirstName(dto.getFirstName());
		student.setLastName(dto.getLastName());
		student.setLocation(dto.getLocation());
		student.setPhoneNumber(dto.getPhoneNumber());
		StudentClass studentClass =  null;
		if(dto.getStudentClass()!=null) {//Tim studentClass trong DB 
			studentClass = studentClassRepository.getOne(dto.getStudentClass().getId());
		}
		//Gan studentClass vao sinh vien, neu la NULL thì coi nhu sinh vien chua co lop
		student.setStudentClass(studentClass);
		
		student = repository.save(student);
		return new StudentDto(student);
	}

	@Override
	public void saveList(List<StudentDto> list) {
		for(int i=0;i<list.size();i++) {
			StudentDto s = list.get(i);
			saveStudent(s);
		}
	}

	@Override
	public Page<Student> getList(int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Page<StudentDto> sqlQuerySample(int pageIndex, int pageSize) {
		int startPosition = (pageIndex-1)*pageSize;
		Query q = entityManager.createQuery("select new com.globits.sample.dto.StudentDto(d) from Student d");
		Query countQuery = entityManager.createQuery("select count(d.id) from Student d");
		q.setFirstResult(startPosition);
		q.setMaxResults(pageSize);
		List<StudentDto> list = q.getResultList();
		Pageable pageable = PageRequest.of(pageIndex - 1, pageSize);
		long total = (long)countQuery.getSingleResult();
		PageImpl<StudentDto> result = new PageImpl<StudentDto>(list, pageable, total);
		return result;
	}
	@Override
	public Page<StudentDto> sqlQuerySample(int pageIndex, int pageSize, String firstName) {
		int startPosition = (pageIndex-1)*pageSize;
		Query q = entityManager.createQuery("select new com.globits.sample.dto.StudentDto(d) from Student d where d.firstName like :firstName");
		Query countQuery = entityManager.createQuery("select count(d.id) from Student d where d.firstName like :firstName");
		q.setFirstResult(startPosition);
		q.setMaxResults(pageSize);
		q.setParameter("firstName", firstName);
		countQuery.setParameter("firstName", firstName);
		List<StudentDto> list = q.getResultList();
		Pageable pageable = PageRequest.of(pageIndex - 1, pageSize);
		long total = (long)countQuery.getSingleResult();
		PageImpl<StudentDto> result = new PageImpl<StudentDto>(list, pageable, total);
		return result;
	}
	
	@Override
	public Page<StudentDto> sqlQueryByStudentClass(int pageIndex, int pageSize, String className) {
		int startPosition = (pageIndex-1)*pageSize;
		Query q = entityManager.createQuery("select new com.globits.sample.dto.StudentDto(d) from Student d where d.studentClass.name like :className");
		Query countQuery = entityManager.createQuery("select count(d.id) from Student d where d.studentClass.name like :className");
		q.setFirstResult(startPosition);
		q.setMaxResults(pageSize);
		q.setParameter("className", className);
		countQuery.setParameter("className", className);
		List<StudentDto> list = q.getResultList();
		Pageable pageable = PageRequest.of(pageIndex - 1, pageSize);
		long total = (long)countQuery.getSingleResult();
		PageImpl<StudentDto> result = new PageImpl<StudentDto>(list, pageable, total);
		return result;
	}
}
